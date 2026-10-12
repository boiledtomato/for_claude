"""キリスト教美術の持物（アトリビュート）× Android 標準アプリのアイコン叩き台。

各アプリに「その機能と結びつく理由がある」聖人・持物を 1 つ割り当て、
金地の祭壇画パネル（箔の継ぎ目・打ち出しの点刻・彩色の円光）の上に描く。
1024px で描き 192px（xxxhdpi）に縮小する。Pillow + NumPy のみ、生成 AI 不使用。

    python icon-pack/tools/render_attributes.py

出力: icon-pack/samples/attributes/*.png
"""
from __future__ import annotations

import math
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont

S = 1024
OUT = 192
C = S // 2
ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "attributes"
JP_FONT = "/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc"
SERIF_B = "/usr/share/fonts/truetype/dejavu/DejaVuSerif-Bold.ttf"

Y, X = np.mgrid[0:S, 0:S].astype(float)


# ── 基本ユーティリティ ───────────────────────────────────────────

def blur(a, sigma):
    if sigma <= 0:
        return a
    fy = np.fft.fftfreq(S)[:, None]
    fx = np.fft.fftfreq(S)[None, :]
    g = np.exp(-2 * math.pi ** 2 * sigma ** 2 * (fx ** 2 + fy ** 2))
    return np.real(np.fft.ifft2(np.fft.fft2(a) * g))


def noise(rng, scale):
    n = S // scale + 3
    im = Image.fromarray(rng.random((n, n)).astype(np.float32), "F")
    big = np.asarray(im.resize((S + 2 * scale, S + 2 * scale), Image.BICUBIC))
    return big[scale:scale + S, scale:scale + S]


def fbm(rng, scales=(256, 128, 64, 32)):
    out, amp, tot = np.zeros((S, S)), 1.0, 0.0
    for sc in scales:
        out += amp * noise(rng, sc)
        tot += amp
        amp *= 0.5
    return out / tot


def hexrgb(h):
    h = h.lstrip("#")
    return np.array([int(h[i:i + 2], 16) for i in (0, 2, 4)], float)


def ramp(stops, t):
    cols = np.array([hexrgb(s) for s in stops])
    t = np.clip(t, 0, 1) * (len(cols) - 1)
    i = np.clip(np.floor(t).astype(int), 0, len(cols) - 2)
    f = (t - i)[..., None]
    return cols[i] * (1 - f) + cols[i + 1] * f


def mix(a, b, t):
    t = np.asarray(t, float)
    if t.ndim == 2:
        t = t[..., None]
    return a + (b - a) * t


def canvas():
    im = Image.new("L", (S, S), 0)
    return im, ImageDraw.Draw(im)


def M(im, soft=1.2):
    return np.asarray(im.filter(ImageFilter.GaussianBlur(soft)), float) / 255


def lit(m, sigma, depth, light=(-0.5, -0.7, 0.5), extra=None):
    h = blur(m, sigma)
    if extra is not None:
        h = h + extra
    gy, gx = np.gradient(h * depth)
    L = np.array(light, float)
    L /= np.linalg.norm(L)
    return np.clip((-gx * L[0] - gy * L[1] + L[2]) / np.sqrt(gx ** 2 + gy ** 2 + 1), 0, 1)


def shade(m, stops, sigma=8, depth=60, k=2.2, bias=0.0, extra=None):
    d = lit(m, sigma, depth, extra=extra)
    return ramp(stops, (d - 0.5) * k + 0.5 + bias)


def edge(m, w):
    b = blur(m, w)
    return np.clip(1 - np.abs(b - 0.5) * 2.2, 0, 1) ** 1.4


def paint(base, m, rgb, shadow=(10, 16, 12, 0.45), line=(3.0, "#2a1a0c", 0.75)):
    """マスク m の部分を rgb で塗る。影と輪郭線つき。"""
    if shadow:
        dx, dy, sg, k = shadow
        sh = np.roll(np.roll(blur(m, sg), dy, 0), dx, 1)
        base = base * (1 - sh * (1 - m) * k)[..., None]
    base = mix(base, rgb, m)
    if line:
        w, col, k = line
        base = mix(base, hexrgb(col), edge(m, w) * k)
    return base


def petal_poly(x0, y0, ang, length, width, curl=0.0, n=24, power=0.8):
    """付け根 (x0,y0) から ang 方向へ伸びる尖った葉・花弁の多角形。"""
    left, right = [], []
    x, y, a = x0, y0, ang
    step = length / n
    for i in range(n + 1):
        t = i / n
        w = width * (math.sin(math.pi * min(t * 1.05, 1)) ** power) / 2
        nx, ny = -math.sin(a), math.cos(a)
        left.append((x + nx * w, y + ny * w))
        right.append((x - nx * w, y - ny * w))
        x += step * math.cos(a)
        y += step * math.sin(a)
        a += curl / n
    return left + right[::-1]


def trumpet_poly(x0, y0, ang, length, width, curl=0.0, n=28):
    """付け根が細く、先で広がって反り返るラッパ形の花弁（マドンナリリー）。"""
    left, right = [], []
    x, y, a = x0, y0, ang
    step = length / n
    for i in range(n + 1):
        t = i / n
        w = width * (0.12 + 0.88 * t ** 1.1) * (1 - t ** 6) ** 0.5 / 2
        nx, ny = -math.sin(a), math.cos(a)
        left.append((x + nx * w, y + ny * w))
        right.append((x - nx * w, y - ny * w))
        x += step * math.cos(a)
        y += step * math.sin(a)
        a += curl * (t ** 2) * 3 / n
    return left + right[::-1]


def squircle(n=4.6, pad=0.02):
    c = (S - 1) / 2
    r = c * (1 - pad)
    d = (np.abs((X - c) / r) ** n + np.abs((Y - c) / r) ** n) ** (1 / n)
    return np.clip((1 - d) * S / 4, 0, 1)


SHAPE = squircle()


# ── 金地パネル（全アイコン共通の下地） ─────────────────────────────

def gold_ground(rng, field_hex, field_r=372):
    """箔を正方形に貼り継いだ金地 + 打ち出しの点刻 + 彩色の円光（ハロー）。"""
    base_t = 0.55 + (fbm(rng) - 0.5) * 0.35
    # 箔の一枚ごとのわずかな色差と継ぎ目
    sq = 150
    off = rng.uniform(0, sq, 2)
    ix = np.floor((X + off[0]) / sq).astype(int)
    iy = np.floor((Y + off[1]) / sq).astype(int)
    tile = (np.sin(ix * 12.9898 + iy * 78.233) * 43758.5453) % 1.0
    base_t = base_t + (tile - 0.5) * 0.12
    seam = (np.minimum((X + off[0]) % sq, (Y + off[1]) % sq) < 2.2).astype(float)
    burnish = np.exp(-(((X - 330) ** 2 + (Y - 300) ** 2) / (2 * 360 ** 2)))
    gold = ramp(["#5e4210", "#9a7424", "#c9a043", "#e9cc78", "#fbecb0"], base_t + burnish * 0.25)
    gold = gold * (1 - blur(seam, 0.8) * 0.18)[..., None]

    # 縁の二重の刻線と点刻の列（スクワークルに沿って）
    def ring_pts(pad, count):
        n = 4.6
        cc = (S - 1) / 2
        r = cc * (1 - pad)
        pts = []
        for k in range(count):
            t = k / count * math.tau
            ct, st = math.cos(t), math.sin(t)
            pts.append((cc + r * math.copysign(abs(ct) ** (2 / n), ct),
                        cc + r * math.copysign(abs(st) ** (2 / n), st)))
        return pts

    tool, td = canvas()
    for pad in (0.055, 0.075):
        td.line(ring_pts(pad, 400) + ring_pts(pad, 400)[:1], fill=255, width=3)
    for px, py in ring_pts(0.093, 120):
        td.ellipse([px - 6, py - 6, px + 6, py + 6], fill=255)
    tm = M(tool, 0.8)
    gold = gold * (1 - tm * 0.42)[..., None]
    gold = mix(gold, hexrgb("#fff3c4"), np.roll(np.roll(tm, 2, 0), 2, 1) * (1 - tm) * 0.5)

    # 円光: テンペラの色面 + 金の縁 + 点刻
    r = np.hypot(X - C, Y - C)
    field = hexrgb(field_hex) * (0.82 + 0.3 * fbm(rng, (64, 32, 16)))[..., None]
    field = field * (1.12 - 0.35 * np.clip(r / field_r, 0, 1) ** 2)[..., None]
    disk = np.clip((field_r - r) / 1.5, 0, 1)
    out = mix(gold, field, disk)
    band = np.clip((field_r + 22 - r) / 1.5, 0, 1) * np.clip((r - field_r) / 1.5, 0, 1)
    out = mix(out, ramp(["#7a5618", "#e8c873", "#8a6420"], (r - field_r) / 22), band)
    dots, dd = canvas()
    for k in range(72):
        a = k / 72 * math.tau
        px, py = C + (field_r + 11) * math.cos(a), C + (field_r + 11) * math.sin(a)
        dd.ellipse([px - 4.5, py - 4.5, px + 4.5, py + 4.5], fill=255)
    out = out * (1 - M(dots, 0.7) * 0.5)[..., None]
    out = out * (1 - np.clip(1 - np.abs(r - field_r) / 2.5, 0, 1) * 0.55)[..., None]
    return out


def finish(base, rng):
    """ワニスの黄味、ごく細かな地のざらつき、スクワークルで切り抜き 192px へ。"""
    base = base * (0.97 + 0.06 * noise(rng, 4))[..., None]
    base = base * np.array([1.02, 1.0, 0.95])
    a = (SHAPE * 255).astype(np.uint8)
    im = Image.fromarray(np.dstack([np.clip(base, 0, 255).astype(np.uint8), a]), "RGBA")
    return im


# ── 1. 電話: 大天使ガブリエルの百合 ─────────────────────────────────

def phone_lily(rng):
    base = gold_ground(rng, "#1d3a8a")
    # 茎と葉
    stem_pts = [(522 + 18 * math.sin(t / 60), 900 - t) for t in range(0, 650, 10)]
    st, sd = canvas()
    sd.line(stem_pts, fill=255, width=18, joint="curve")
    leaves, ld = canvas()
    for i, yy in enumerate(range(860, 470, -48)):
        sx = 522 + 18 * math.sin((900 - yy) / 60)
        side = 1 if i % 2 == 0 else -1
        ang = -math.pi / 2 + side * (0.95 - i * 0.05)
        ld.polygon(petal_poly(sx, yy, ang, 150 - i * 8, 30, curl=side * 0.5), fill=255)
    green_m = np.maximum(M(st), M(leaves))
    green = shade(green_m, ["#173d1c", "#2f6b2c", "#5c9a45", "#a8cf78"], sigma=4, depth=50)
    base = paint(base, green_m, green, line=(2.5, "#0f2410", 0.6))
    # 花: 付け根の位置、向き
    flowers = [(498, 360, -2.2), (566, 470, -0.5), (468, 600, -2.75)]
    for fx, fy, ang in flowers:
        sx = 522 + 18 * math.sin((900 - fy - 30) / 60)
        stalk, kd = canvas()
        kd.line([(sx, fy + 30), (fx, fy)], fill=255, width=11)
        km = M(stalk)
        base = paint(base, km, shade(km, ["#1f4a22", "#5c9a45"], 3, 40), shadow=None, line=None)
        back, bd = canvas()
        for da in (-0.36, 0.36, 0.0):
            bd.polygon(trumpet_poly(fx, fy, ang + da, 225, 96, curl=-da * 2.2 if da else 0.0), fill=255)
        bm = M(back)
        base = paint(base, bm, shade(bm, ["#7d8594", "#b6bcc7", "#dfe2e7"], 6, 60),
                     shadow=(8, 12, 10, 0.4), line=(2.5, "#5d6370", 0.75))
        # 雄しべ（花の口から少し突き出る）
        stam, smd = canvas()
        anth, ad = canvas()
        for da in (-0.16, -0.07, 0.02, 0.1, 0.18):
            L_ = 228 + 10 * math.cos(da * 9)
            ex = fx + L_ * math.cos(ang + da)
            ey = fy + L_ * math.sin(ang + da)
            smd.line([(fx, fy), (ex, ey)], fill=255, width=4)
            ad.ellipse([ex - 12, ey - 7, ex + 12, ey + 7], fill=255)
        front, fd = canvas()
        for da in (-0.2, 0.2):
            fd.polygon(trumpet_poly(fx, fy, ang + da, 235, 100, curl=-da * 3.4), fill=255)
        fm = M(front)
        base = paint(base, fm, shade(fm, ["#8d95a3", "#d2d6dd", "#f7f7f5", "#ffffff"], 7, 70),
                     shadow=(6, 10, 8, 0.35), line=(2.5, "#666c78", 0.75))
        # 花弁の中筋（緑がかった筋が根元から）
        vein, vd = canvas()
        for da in (-0.2, 0.2):
            pts = trumpet_poly(fx, fy, ang + da, 235, 2, curl=-da * 3.4)
            vd.line(pts[:22], fill=255, width=4)
        base = mix(base, hexrgb("#a9bd84"), M(vein, 1.5) * fm * 0.7)
        throat = np.exp(-(((X - fx) ** 2 + (Y - fy) ** 2) / (2 * 40 ** 2)))
        base = mix(base, hexrgb("#c6d39a"), throat * fm * 0.55)
        base = paint(base, M(stam), np.zeros((S, S, 3)) + hexrgb("#e2e8c0"), shadow=None, line=None)
        base = paint(base, M(anth), shade(M(anth), ["#7a2e08", "#d2691e", "#f2a14a"], 2, 30),
                     shadow=None, line=(1.2, "#4a1a05", 0.6))
    # 先端のつぼみ
    bud, bdd = canvas()
    bdd.polygon(petal_poly(520, 268, -1.62, 150, 54), fill=255)
    bm = M(bud)
    base = paint(base, bm, shade(bm, ["#9aa86e", "#d9e0bc", "#f6f4e6"], 6, 60), line=(2.5, "#5d6a40", 0.7))
    return finish(base, rng)


# ── 2. メッセージ: 聖霊の鳩（ペンテコステ） ───────────────────────────

def message_dove(rng):
    base = gold_ground(rng, "#9e1f1a")
    r = np.hypot(X - C, Y - 470)
    th = np.arctan2(Y - 470, X - C)
    # 金の光条（直線と波線を交互に）
    rays = np.zeros((S, S))
    for k in range(32):
        a = k / 32 * math.tau
        wav = 0.0 if k % 2 == 0 else 0.03 * np.sin(r / 18)
        d = np.abs(np.angle(np.exp(1j * (th - a - wav))))
        rays = np.maximum(rays, np.clip(1 - d * r / (5 if k % 2 == 0 else 3.5), 0, 1))
    rays = rays * (r > 120) * (r < 365)
    base = mix(base, ramp(["#c89a3c", "#f6dc8a"], 1 - r / 365), rays * 0.85)
    # 翼（羽根を一枚ずつ）
    wing_m = np.zeros((S, S))
    feather_lines = np.zeros((S, S))
    for side in (-1, 1):
        for row, (length, count, spread) in enumerate(((310, 9, 0.8), (225, 8, 0.7), (140, 7, 0.6))):
            fm_im, fdd = canvas()
            ln_im, lnd = canvas()
            for k in range(count):
                t = k / (count - 1)
                ang = -math.pi / 2 - side * (0.25 + t * spread) - side * 0.15
                ang = math.pi + ang if side < 0 else ang
                a = (-math.pi / 2) + side * (0.35 + t * spread * 1.15)
                bx = C + side * (40 + row * 10)
                by = 470 + row * 6
                pts = petal_poly(bx, by, a, length - t * 40, 92 - row * 14, curl=side * 0.2, power=0.6)
                fdd.polygon(pts, fill=255)
                lnd.line(pts[:25], fill=255, width=2)
                lnd.polygon(pts, outline=255)
            fmm = M(fm_im)
            col = shade(fmm, ["#9aa0ab", "#d6d9de", "#fbfbf8"], 4, 40, bias=-0.05 * row)
            base = paint(base, fmm, col, shadow=(8, 12, 10, 0.3), line=None)
            base = mix(base, hexrgb("#7d8390"), M(ln_im, 0.8) * fmm * 0.65)
            wing_m = np.maximum(wing_m, fmm)
    # 尾羽（上へ扇形）
    tail, tdd = canvas()
    for k in range(7):
        a = -math.pi / 2 + (k - 3) * 0.13
        tdd.polygon(petal_poly(C, 430, a, 170, 46), fill=255)
    tm = M(tail)
    base = paint(base, tm, shade(tm, ["#9aa0ab", "#e1e3e6", "#ffffff"], 4, 40), line=(2, "#7d8390", 0.7))
    # 胴と頭（下向き）
    body, bd = canvas()
    bd.ellipse([C - 70, 400, C + 70, 640], fill=255)
    bd.ellipse([C - 52, 600, C + 52, 710], fill=255)
    bm = M(body)
    base = paint(base, bm, shade(bm, ["#a3a8b2", "#e6e7ea", "#ffffff"], 14, 70), line=(2.5, "#7d8390", 0.75))
    beak, bkd = canvas()
    bkd.polygon([(C - 14, 700), (C + 14, 700), (C, 742)], fill=255)
    base = paint(base, M(beak), np.zeros((S, S, 3)) + hexrgb("#c97b6a"), shadow=None, line=(1.5, "#5a2a20", 0.8))
    for ex in (C - 26, C + 26):
        eye, ed = canvas()
        ed.ellipse([ex - 7, 650, ex + 7, 664], fill=255)
        base = mix(base, hexrgb("#1a1010"), M(eye, 0.8))
    # くちばしのオリーブの小枝
    olive, od = canvas()
    od.line([(C - 120, 760), (C + 110, 720)], fill=255, width=6)
    for k in range(6):
        t = k / 5
        ox, oy = C - 110 + t * 210, 758 - t * 36
        od.polygon(petal_poly(ox, oy, -1.2 if k % 2 else 1.9, 62, 18), fill=255)
    om = M(olive)
    base = paint(base, om, shade(om, ["#2c4a1c", "#5f7f38", "#a8bf74"], 3, 30), line=(1.5, "#1a2a10", 0.7))
    return finish(base, rng)


# ── 3. カメラ: 聖ヴェロニカの布（ヴェラ・イコン） ───────────────────

def camera_veil(rng):
    base = gold_ground(rng, "#4a2864")
    top = lambda x: 268 + 46 * np.sin(np.clip((x - 290) / 444, 0, 1) * math.pi)
    cloth = ((X > 290 - (Y - 280) * 0.04) & (X < 734 + (Y - 280) * 0.04) & (Y > top(X)) & (Y < 800)).astype(float)
    hem = 800 + 10 * np.sin(X / 23)
    cloth = cloth * (Y < hem)
    cloth_m = blur(cloth, 1.2)
    folds = 0.08 * np.sin((X - C) / 26 + 2 * np.sin(Y / 140)) * blur(cloth, 10)
    folds += 0.05 * np.sin((X + Y * 0.6) / 40) * np.clip((330 - np.abs(X - C)) / 330, 0, 1) * cloth
    linen = shade(cloth_m, ["#8f8577", "#cfc6b4", "#f1ebdc", "#fffcf2"], 6, 60, k=2.6, extra=folds)
    base = paint(base, cloth_m, linen, shadow=(12, 18, 14, 0.5), line=(2.5, "#5a4a36", 0.7))
    # 房飾り
    fringe, frd = canvas()
    for fx in range(300, 728, 9):
        yy = 800 + 10 * math.sin(fx / 23)
        frd.line([(fx, yy - 4), (fx + rng.uniform(-3, 3), yy + 30)], fill=255, width=3)
    fm = M(fringe, 0.8)
    base = paint(base, fm, np.zeros((S, S, 3)) + hexrgb("#e2d8c2"), shadow=None, line=(1, "#6a5a44", 0.5))
    # 吊るす結び目
    for kx in (300, 724):
        knot, kd = canvas()
        kd.ellipse([kx - 26, 248, kx + 26, 300], fill=255)
        km = M(knot)
        base = paint(base, km, shade(km, ["#9a8f7c", "#efe7d6"], 6, 50), line=(2, "#5a4a36", 0.7))
    # 浮かび出た聖顔（セピアの写し）: 十字の円光、髪、顔、髭、茨の冠
    imprint = np.zeros((S, S))
    face, fd = canvas()
    fd.ellipse([C - 150, 360, C + 150, 660], outline=255, width=8)
    for a in (0, 90, 180):
        aa = math.radians(a - 90 + 0)
        pass
    fd.line([(C, 362), (C, 395)], fill=255, width=16)
    fd.line([(C - 148, 510), (C - 112, 510)], fill=255, width=16)
    fd.line([(C + 112, 510), (C + 148, 510)], fill=255, width=16)
    imprint = np.maximum(imprint, M(face, 1.5) * 0.45)
    hair, hd = canvas()
    hd.ellipse([C - 112, 395, C + 112, 640], fill=255)
    hd.polygon([(C - 112, 520), (C - 120, 690), (C - 60, 650)], fill=255)
    hd.polygon([(C + 112, 520), (C + 120, 690), (C + 60, 650)], fill=255)
    imprint = np.maximum(imprint, M(hair, 4) * 0.55)
    skin, skd = canvas()
    skd.ellipse([C - 70, 430, C + 70, 610], fill=255)
    sk = M(skin, 3)
    imprint = imprint * (1 - sk * 0.6)
    beard, brd = canvas()
    brd.polygon([(C - 62, 560), (C + 62, 560), (C + 30, 660), (C, 690), (C - 30, 660)], fill=255)
    imprint = np.maximum(imprint, M(beard, 3) * 0.5)
    feat, ftd = canvas()
    for s_ in (-1, 1):
        ftd.arc([C + s_ * 38 - 22, 500, C + s_ * 38 + 22, 524], 10, 170, fill=255, width=4)
        ftd.arc([C + s_ * 38 - 26, 478, C + s_ * 38 + 26, 506], 200, 340, fill=255, width=4)
    ftd.line([(C, 505), (C - 6, 560), (C + 8, 564)], fill=255, width=4)
    ftd.line([(C - 22, 588), (C + 22, 588)], fill=255, width=4)
    imprint = np.maximum(imprint, M(feat, 1) * 0.75)
    thorns, thd = canvas()
    pts = [(C - 118 + k * 12, 455 + (8 if k % 2 else -8) + 0.02 * (k * 12 - 118) ** 2 / 4) for k in range(21)]
    thd.line(pts, fill=255, width=5)
    imprint = np.maximum(imprint, M(thorns, 1) * 0.7)
    imprint = imprint * cloth_m
    base = mix(base, hexrgb("#6e3d1f"), imprint * 0.85)
    return finish(base, rng)


# ── 4. ギャラリー: 聖ルカの描いた聖母 ────────────────────────────────

def gallery_luke(rng):
    base = gold_ground(rng, "#3c5a3c")
    def panel_mask(x0, y0, x1, y1, arch):
        im, d = canvas()
        d.rectangle([x0, y0 + arch, x1, y1], fill=255)
        d.pieslice([x0, y0, x1, y0 + arch * 2], 180, 360, fill=255)
        return im
    rot = -7
    outer = M(panel_mask(316, 220, 708, 790, 196).rotate(rot, center=(C, 505), resample=Image.BICUBIC))
    inner = M(panel_mask(346, 252, 678, 760, 166).rotate(rot, center=(C, 505), resample=Image.BICUBIC))
    frame = shade(outer, ["#4a300c", "#a07a2c", "#e7c873", "#fff0b8"], 5, 60)
    base = paint(base, outer, frame, shadow=(16, 22, 16, 0.55), line=(2.5, "#3a2408", 0.8))
    ground = ramp(["#b8902f", "#e5c46c", "#f6dfa0"], 0.5 + (fbm(rng, (64, 32)) - 0.5) * 0.6)
    base = mix(base, ground, inner)
    base = mix(base, hexrgb("#6e4c14"), edge(inner, 3) * 0.6)

    fig_layers = []
    def R(im):
        return M(im.rotate(rot, center=(C, 505), resample=Image.BICUBIC))
    # 聖母: 円光、青いマフォリオン、顔、幼子
    halo, hd = canvas()
    hd.ellipse([C - 70, 320, C + 70, 460], fill=255)
    hm = R(halo)
    base = mix(base, ramp(["#c99a38", "#f8e2a0"], 0.5 + (Y - 320) / 600), hm)
    base = mix(base, hexrgb("#8a6420"), edge(hm, 2) * 0.8)
    mant, md = canvas()
    md.polygon([(C, 340), (C + 70, 380), (C + 150, 740), (C - 150, 740), (C - 70, 380)], fill=255)
    mm = R(mant)
    base = paint(base, mm, shade(mm, ["#0b1a4a", "#1f3b8c", "#4268c0"], 8, 50,
                 extra=0.06 * np.sin((X - Y * 0.3) / 18) * mm), shadow=None, line=(2.5, "#08102e", 0.8))
    trim, trd = canvas()
    trd.line([(C - 70, 380), (C, 340), (C + 70, 380)], fill=255, width=7)
    trd.line([(C - 70, 380), (C - 150, 740)], fill=255, width=6)
    base = mix(base, hexrgb("#e9c45a"), R(trim) * 0.9)
    star, std = canvas()
    std.regular_polygon((C - 26, 405, 9), 8, fill=255)
    base = mix(base, hexrgb("#f6e3a0"), R(star))
    faceim, fd = canvas()
    fd.ellipse([C - 34, 362, C + 34, 448], fill=255)
    fm = R(faceim)
    base = paint(base, fm, shade(fm, ["#9a6a48", "#d8a882", "#f2d0b0"], 6, 40), shadow=None, line=(1.5, "#5a3420", 0.8))
    feats, ftd = canvas()
    for s_ in (-1, 1):
        ftd.ellipse([C + s_ * 14 - 5, 396, C + s_ * 14 + 5, 402], fill=255)
    ftd.line([(C, 400), (C - 3, 422)], fill=255, width=2)
    ftd.line([(C - 8, 432), (C + 8, 432)], fill=255, width=3)
    base = mix(base, hexrgb("#3a2010"), R(feats) * 0.8)
    child, cd = canvas()
    cd.ellipse([C + 6, 470, C + 64, 528], fill=255)
    chalo, chd = canvas()
    chd.ellipse([C - 4, 458, C + 74, 536], fill=255)
    base = mix(base, hexrgb("#f1d488"), R(chalo) * 0.9)
    base = mix(base, hexrgb("#8a6420"), edge(R(chalo), 1.5) * 0.7)
    cm = R(child)
    base = paint(base, cm, shade(cm, ["#9a6a48", "#e0b08a", "#f6d6b8"], 5, 40), shadow=None, line=(1.5, "#5a3420", 0.8))
    robe, rd = canvas()
    rd.polygon([(C + 10, 520), (C + 70, 516), (C + 96, 610), (C + 4, 616)], fill=255)
    rm = R(robe)
    base = paint(base, rm, shade(rm, ["#8a3a10", "#d0782a", "#f0b060"], 5, 40), shadow=None, line=(1.5, "#4a1a05", 0.8))
    # 絵筆（右下から斜めに）
    brush, bd = canvas()
    bd.line([(860, 880), (640, 640)], fill=255, width=26)
    bm = M(brush)
    base = paint(base, bm, shade(bm, ["#4a2a10", "#8a5428", "#c48a52"], 4, 50), shadow=(10, 14, 10, 0.5), line=(2, "#2a1606", 0.8))
    fer, fed = canvas()
    fed.line([(660, 662), (610, 607)], fill=255, width=30)
    fm2 = M(fer)
    base = paint(base, fm2, shade(fm2, ["#5a5a62", "#b9bcc4", "#f2f2f4"], 4, 50), shadow=None, line=(2, "#2a2a30", 0.8))
    tip, tpd = canvas()
    tpd.polygon(petal_poly(612, 609, math.atan2(-55, -50), 80, 34), fill=255)
    tm = M(tip)
    base = paint(base, tm, shade(tm, ["#5a0e0a", "#b3261c", "#e0503a"], 3, 40), shadow=None, line=(1.5, "#3a0806", 0.8))
    return finish(base, rng)


# ── 5. メール: 聖パウロの剣と書簡 ────────────────────────────────────

def mail_paul(rng):
    base = gold_ground(rng, "#6a1420")
    a = math.radians(-50)
    ux, uy = math.cos(a), math.sin(a)
    px, py = -uy, ux
    hx, hy = 290, 820  # 柄頭側
    def seg(t0, t1, w0, w1):
        return [(hx + ux * t0 + px * w0, hy + uy * t0 + py * w0), (hx + ux * t1 + px * w1, hy + uy * t1 + py * w1),
                (hx + ux * t1 - px * w1, hy + uy * t1 - py * w1), (hx + ux * t0 - px * w0, hy + uy * t0 - py * w0)]
    blade, bd = canvas()
    bd.polygon(seg(150, 760, 30, 22) + [], fill=255)
    bd.polygon([(hx + ux * 760 + px * 22, hy + uy * 760 + py * 22), (hx + ux * 830, hy + uy * 830),
                (hx + ux * 760 - px * 22, hy + uy * 760 - py * 22)], fill=255)
    bm = M(blade)
    side = ((X - hx) * px + (Y - hy) * py) > 0
    steel = mix(ramp(["#5a6068", "#c6ccd4", "#f4f6f8"], 0.55 + 0 * X), hexrgb("#7a828c"), side.astype(float) * 0.6)
    base = paint(base, bm, steel, shadow=(12, 16, 12, 0.5), line=(2.5, "#262a30", 0.85))
    fuller, fd = canvas()
    fd.line([(hx + ux * 170, hy + uy * 170), (hx + ux * 640, hy + uy * 640)], fill=255, width=6)
    base = mix(base, hexrgb("#4a5058"), M(fuller) * 0.7)
    grip, gd = canvas()
    gd.polygon(seg(30, 130, 16, 16), fill=255)
    gm = M(grip)
    wrap = 0.5 + 0.5 * np.sin(((X - hx) * ux + (Y - hy) * uy) / 5)
    base = paint(base, gm, ramp(["#3a1a0a", "#7a4020"], wrap), shadow=(8, 10, 8, 0.4), line=(2, "#1a0a04", 0.8))
    guard, gud = canvas()
    gud.polygon(seg(125, 150, 95, 95), fill=255)
    pom, pd = canvas()
    pd.ellipse([hx + ux * 15 - 30, hy + uy * 15 - 30, hx + ux * 15 + 30, hy + uy * 15 + 30], fill=255)
    gm2 = np.maximum(M(guard), M(pom))
    base = paint(base, gm2, shade(gm2, ["#5a3c0c", "#b48a32", "#f2d27a", "#fff2c0"], 5, 60), shadow=(8, 10, 8, 0.4), line=(2, "#3a2406", 0.85))
    # 書簡（巻物）
    sheet_m = np.zeros((S, S))
    body, bdd = canvas()
    bdd.rectangle([290, 440, 734, 650], fill=255)
    bm2 = M(body)
    parch = ramp(["#b89a62", "#e6d3a4", "#f6ead0"], 0.6 + (fbm(rng, (64, 32)) - 0.5) * 0.5 + (Y - 452) / 1400)
    base = paint(base, bm2, parch, shadow=(14, 18, 14, 0.5), line=(2.5, "#6a4a20", 0.8))
    lines_im, lid = canvas()
    for k, ly in enumerate(range(470, 620, 22)):
        x = 330
        while x < 694:
            w = rng.uniform(18, 60)
            lid.line([(x, ly), (min(x + w, 694), ly)], fill=255, width=5)
            x += w + rng.uniform(8, 16)
    base = mix(base, hexrgb("#4a2a14"), M(lines_im, 0.8) * 0.7)
    for rx in (290, 734):
        roll, rld = canvas()
        rld.rounded_rectangle([rx - 36, 420, rx + 36, 672], 30, fill=255)
        rm = M(roll)
        cyl = ramp(["#8a6a3a", "#e8d5a6", "#fff4dc", "#a4844e"], (X - rx + 34) / 68)
        base = paint(base, rm, cyl, shadow=(10, 12, 10, 0.45), line=(2.5, "#5a3a14", 0.85))
        end, ed = canvas()
        ed.ellipse([rx - 36, 408, rx + 36, 438], fill=255)
        em = M(end)
        base = paint(base, em, np.zeros((S, S, 3)) + hexrgb("#c8ae7a"), shadow=None, line=(2, "#5a3a14", 0.8))
        spiral, spd = canvas()
        spd.arc([rx - 22, 414, rx + 22, 432], 0, 300, fill=255, width=3)
        base = mix(base, hexrgb("#6a4a20"), M(spiral, 0.6) * 0.7)
    # 封蝋とリボン
    rib, rbd = canvas()
    rbd.polygon([(C - 20, 660), (C - 58, 770), (C - 30, 752), (C - 10, 780), (C, 680)], fill=255)
    rbd.polygon([(C + 20, 660), (C + 58, 770), (C + 30, 752), (C + 10, 780), (C, 680)], fill=255)
    rbm = M(rib)
    base = paint(base, rbm, shade(rbm, ["#5a0a0a", "#a8201a", "#d84a3a"], 3, 30), shadow=(6, 8, 6, 0.4), line=(1.5, "#3a0505", 0.8))
    seal, sd = canvas()
    pts = [(C + (62 + 6 * math.sin(k * 1.7)) * math.cos(k / 24 * math.tau),
            640 + (62 + 6 * math.sin(k * 1.7)) * math.sin(k / 24 * math.tau)) for k in range(24)]
    sd.polygon(pts, fill=255)
    sm = M(seal)
    stamp, std = canvas()
    f = ImageFont.truetype(SERIF_B, 72)
    std.text((C, 642), "Π", font=f, fill=255, anchor="mm")
    seal_h = sm * 1.0 - M(stamp, 1) * 0.35 * sm
    wax = shade(seal_h, ["#4a0606", "#a01818", "#e05040", "#ffb0a0"], 3, 70)
    base = paint(base, sm, wax, shadow=(8, 12, 8, 0.5), line=(2, "#300404", 0.8))
    return finish(base, rng)


# ── 6. ブラウザ: 救世主の水晶の宝珠（グロブス・クルキゲル） ─────────────

def browser_orb(rng):
    base = gold_ground(rng, "#16203f")
    cx, cy, R = C, 560, 236
    r = np.hypot(X - cx, Y - cy)
    orb = np.clip((R - r) / 1.5, 0, 1)
    # 水晶の中: 背景を上下反転してにじませ、縁ほど明るく（フレネル）
    inv = base[::-1, ::-1] * 0.55 + hexrgb("#2a3a6a") * 0.25
    inv = np.stack([blur(inv[..., c], 6) for c in range(3)], -1)
    fres = np.clip(r / R, 0, 1) ** 4
    crystal = mix(inv, hexrgb("#dfe8ff"), fres * 0.55)
    caust = np.exp(-(((X - cx - 110) ** 2 + (Y - cy - 120) ** 2) / (2 * 60 ** 2)))
    crystal = mix(crystal, hexrgb("#fff6d8"), caust * 0.55)
    base = paint(base, orb, crystal, shadow=(16, 24, 20, 0.55), line=(2.5, "#0c1430", 0.7))
    # 金の帯: 赤道（楕円）と前面の子午線
    def band_mask(fn):
        im, d = canvas()
        fn(d)
        return M(im)
    eq_front = band_mask(lambda d: d.arc([cx - R, cy - 70, cx + R, cy + 70], 0, 180, fill=255, width=34))
    eq_back = band_mask(lambda d: d.arc([cx - R, cy - 70, cx + R, cy + 70], 180, 360, fill=255, width=26))
    mer = band_mask(lambda d: d.arc([cx - 70, cy - R, cx + 70, cy + R], 270, 450, fill=255, width=32)) * (Y < cy + 60)
    base = mix(base, hexrgb("#9a7a3a"), eq_back * orb * 0.45)
    gold_st = ["#5a3c0c", "#b48a32", "#f2d27a", "#fff4c8"]
    for bmk in (mer, eq_front):
        base = paint(base, bmk, shade(bmk, gold_st, 4, 50), shadow=(4, 6, 6, 0.4), line=(2, "#3a2406", 0.85))
    # 帯の宝石
    for k, a in enumerate(np.linspace(0.25, math.pi - 0.25, 5)):
        gx, gy = cx + R * math.cos(a), cy + 70 * math.sin(a)
        gem, gd = canvas()
        gd.ellipse([gx - 13, gy - 13, gx + 13, gy + 13], fill=255)
        gm = M(gem)
        col = ["#b3141e", "#1e48b3", "#b3141e", "#1e48b3", "#b3141e"][k]
        base = paint(base, gm, shade(gm, ["#200404", col, "#ffffff"], 3, 40), shadow=None, line=(1.2, "#200404", 0.8))
    # 十字架
    cross, crd = canvas()
    crd.rectangle([cx - 20, 180, cx + 20, 332], fill=255)
    crd.rectangle([cx - 80, 216, cx + 80, 254], fill=255)
    for ex, ey in ((cx, 180), (cx - 80, 235), (cx + 80, 235)):
        crd.ellipse([ex - 24, ey - 24, ex + 24, ey + 24], fill=255)
    crd.ellipse([cx - 40, 310, cx + 40, 352], fill=255)
    cm = M(cross)
    base = paint(base, cm, shade(cm, gold_st, 5, 60), shadow=(10, 14, 10, 0.5), line=(2.5, "#3a2406", 0.85))
    for ex, ey in ((cx, 180), (cx - 80, 235), (cx + 80, 235)):
        p, pd = canvas()
        pd.ellipse([ex - 13, ey - 13, ex + 13, ey + 13], fill=255)
        pm = M(p)
        base = paint(base, pm, shade(pm, ["#8a8476", "#efeadf", "#ffffff"], 3, 40), shadow=None, line=(1, "#4a4436", 0.6))
    # 窓の映り込み
    win, wd = canvas()
    wd.rounded_rectangle([cx - 150, cy - 170, cx - 92, cy - 70], 14, fill=255)
    wd.rounded_rectangle([cx - 84, cy - 192, cx - 30, cy - 92], 14, fill=255)
    wm = M(win.filter(ImageFilter.GaussianBlur(2)), 1) * orb
    base = mix(base, hexrgb("#ffffff"), wm * 0.55)
    return finish(base, rng)


# ── 7. マップ: 聖ヤコブの帆立貝（サンティアゴ巡礼路の道標） ─────────────

def maps_shell(rng):
    base = gold_ground(rng, "#1f4fa0")
    hx, hy = C, 800
    th = np.arctan2(Y - hy, X - hx)
    r = np.hypot(X - hx, Y - hy)
    a0, a1 = -math.pi + 0.5, -0.5
    t = (th - a0) / (a1 - a0)
    ribs = 16
    phase = t * ribs
    rib = 0.5 - 0.5 * np.cos(phase * math.tau)
    # 外形: 蝶番の少し上に中心を置いた円まで（両脇は蝶番へ向かって直線に絞れる）
    k_ = -340 * np.sin(th)
    reach = k_ + np.sqrt(np.clip(k_ ** 2 - 340 ** 2 + 352 ** 2, 0, None))
    Rr = reach * (0.982 + 0.018 * np.cos(phase * math.tau))
    fan = ((t > 0) & (t < 1) & (r < Rr)).astype(float)
    fan_m = blur(fan, 1.3)
    height = 0.06 * rib * fan * np.clip(r / 200, 0, 1)
    col = shade(fan_m, ["#7a4a06", "#d0901a", "#f5c63a", "#ffe998"], 6, 60, k=2.4, extra=height)
    growth = (np.sin(r / 11) > 0.93) * fan
    col = col * (1 - growth * 0.07)[..., None]
    col = col * (0.84 + 0.2 * rib)[..., None]
    base = paint(base, fan_m, col, shadow=(14, 20, 16, 0.55), line=(3, "#4a2a04", 0.85))
    # 肋の溝の線
    groove = np.clip(1 - np.abs(((phase + 0.5) % 1) - 0.5) * 2 * 14, 0, 1) * fan * (r > 70)
    base = mix(base, hexrgb("#6a3a04"), blur(groove, 0.8) * 0.4)
    # 蝶番の耳
    ears, ed = canvas()
    ed.polygon([(hx - 20, hy + 6), (hx - 92, hy - 52), (hx - 96, hy + 12), (hx - 26, hy + 30)], fill=255)
    ed.polygon([(hx + 20, hy + 6), (hx + 92, hy - 52), (hx + 96, hy + 12), (hx + 26, hy + 30)], fill=255)
    ed.ellipse([hx - 34, hy - 16, hx + 34, hy + 36], fill=255)
    em = M(ears)
    base = paint(base, em, shade(em, ["#7a4a06", "#c8861a", "#f2c240"], 4, 40, extra=0.02 * np.sin(X / 6) * em), shadow=(10, 12, 10, 0.5), line=(2.5, "#4a2a04", 0.85))
    return finish(base, rng)


# ── 8. ミュージック: 聖チェチーリアの携帯オルガン ──────────────────────

def music_organ(rng):
    base = gold_ground(rng, "#8a3048")
    # ふいご（背後右）
    bel, bd = canvas()
    bel_pts = [(700, 520)]
    for k in range(7):
        bel_pts.append((760 + (22 if k % 2 == 0 else 0), 540 + k * 30))
    bel_pts += [(700, 760)]
    bd.polygon(bel_pts, fill=255)
    bm = M(bel)
    base = paint(base, bm, shade(bm, ["#3a1a0a", "#7a4424", "#b07040"], 4, 50,
                 extra=0.04 * np.sin(Y / 4.8) * bm), line=(2, "#1a0a04", 0.8))
    # パイプ（左から右へ短くなる）
    n = 9
    for k in range(n):
        x0 = 330 + k * 40
        top = 210 + k * 34
        bottom = 640
        pipe, pd = canvas()
        pd.rectangle([x0, top, x0 + 32, bottom - 50], fill=255)
        pd.polygon([(x0, bottom - 50), (x0 + 32, bottom - 50), (x0 + 22, bottom), (x0 + 10, bottom)], fill=255)
        pd.ellipse([x0, top - 6, x0 + 32, top + 6], fill=255)
        pm = M(pipe, 1)
        tin = ramp(["#4c5058", "#9aa0aa", "#e8ecf2", "#b0b6c0", "#5a5e66"], (X - x0) / 32)
        tin = tin * (0.85 + 0.15 * (Y - top) / (bottom - top))[..., None]
        base = paint(base, pm, tin, shadow=(8, 10, 8, 0.4), line=(1.8, "#24262c", 0.85))
        mouth, md = canvas()
        my = bottom - 110
        md.rectangle([x0 + 9, my, x0 + 23, my + 18], fill=255)
        md.pieslice([x0 + 4, my - 14, x0 + 28, my + 14], 180, 360, fill=255)
        mm = M(mouth, 0.8)
        base = mix(base, hexrgb("#14161a"), mm * 0.85)
        base = mix(base, hexrgb("#f8fafc"), np.roll(edge(mm, 1), -2, 0) * (Y < my) * 0.5)
    # 胴の木箱と彫刻の冠
    case, cd = canvas()
    cd.rectangle([300, 630, 720, 790], fill=255)
    cm = M(case)
    wood = ramp(["#3a1a08", "#6e3a18", "#9a5a2a"], 0.5 + 0.25 * np.sin(Y / 5 + fbm(rng, (64, 32)) * 10))
    wood = wood * (0.75 + 0.35 * lit(cm, 6, 40))[..., None]
    base = paint(base, cm, wood, shadow=(14, 18, 14, 0.5), line=(2.5, "#1a0a04", 0.85))
    rail, rd = canvas()
    rd.rectangle([290, 616, 730, 640], fill=255)
    rd.rectangle([290, 780, 730, 800], fill=255)
    rm = M(rail)
    base = paint(base, rm, shade(rm, ["#5a3c0c", "#b48a32", "#f2d27a"], 3, 40), shadow=None, line=(1.5, "#3a2406", 0.8))
    # 鍵盤
    for k in range(14):
        kx = 322 + k * 27
        key, kd = canvas()
        kd.rectangle([kx, 690, kx + 24, 768], fill=255)
        km = M(key, 0.8)
        base = paint(base, km, shade(km, ["#b8b0a0", "#f4efe2", "#ffffff"], 2, 20), shadow=None, line=(1, "#3a3024", 0.8))
    for k in range(14):
        if k % 7 in (2, 6):
            continue
        kx = 322 + k * 27 + 17
        bk, bkd = canvas()
        bkd.rectangle([kx, 690, kx + 14, 736], fill=255)
        base = mix(base, hexrgb("#141010"), M(bk, 0.8))
    # 箱の正面の四つ葉の透かし
    for qx in (340, 680):
        q, qd = canvas()
        for dx, dy in ((0, -10), (0, 10), (-10, 0), (10, 0)):
            qd.ellipse([qx + dx - 9, 655 + dy - 9 + 0, qx + dx + 9, 655 + dy + 9], fill=255)
        base = mix(base, hexrgb("#e2c06a"), M(q, 0.8) * 0.8)
    return finish(base, rng)


# ── 9. 設定: アレクサンドリアの聖カタリナの車輪 ───────────────────────

def settings_wheel(rng):
    base = gold_ground(rng, "#1f4a48")
    cx, cy = C, 520
    r = np.hypot(X - cx, Y - cy)
    th = np.arctan2(Y - cy, X - cx)
    R0, R1 = 210, 268
    # 折れた箇所（右上）
    brk = np.abs(np.angle(np.exp(1j * (th + 0.62)))) < (0.075 + 0.03 * np.sin(r / 4))
    rim = ((r > R0) & (r < R1) & ~brk).astype(float)
    spokes = np.zeros((S, S))
    for k in range(8):
        a = k * math.pi / 4 + 0.2
        d = np.abs((X - cx) * math.sin(a) - (Y - cy) * math.cos(a))
        along = (X - cx) * math.cos(a) + (Y - cy) * math.sin(a)
        spokes = np.maximum(spokes, ((d < 20 - along / 60) & (along > 0) & (along < R0 + 5)).astype(float))
    hub = (r < 72).astype(float)
    wood_m = blur(np.maximum(np.maximum(rim, spokes), hub), 1.3)
    grain = 0.5 + 0.3 * np.sin(th * 40 + r / 4 + fbm(rng, (32, 16)) * 6)
    wood = ramp(["#3a1c08", "#7a4420", "#b07040", "#d4a070"], grain * 0.6 + lit(wood_m, 6, 50) * 0.5 - 0.1)
    # 鉄の棘
    spk, sd = canvas()
    for k in range(18):
        a = k / 18 * math.tau + 0.1
        if abs(math.atan2(math.sin(a + 0.62), math.cos(a + 0.62))) < 0.15:
            continue
        bx, by = cx + (R1 - 6) * math.cos(a), cy + (R1 - 6) * math.sin(a)
        tx, ty = cx + (R1 + 72) * math.cos(a + 0.05), cy + (R1 + 72) * math.sin(a + 0.05)
        px, py = -math.sin(a) * 20, math.cos(a) * 20
        sd.polygon([(bx + px, by + py), (tx, ty), (bx - px, by - py)], fill=255)
    sm = M(spk)
    base = paint(base, sm, shade(sm, ["#202428", "#6a727c", "#c4ccd4", "#f4f8fc"], 3, 50), shadow=(10, 14, 10, 0.5), line=(1.8, "#101418", 0.85))
    base = paint(base, wood_m, wood, shadow=(14, 18, 14, 0.55), line=(2.5, "#1a0a02", 0.85))
    # 鉄の箍と鋲
    band = ((np.abs(r - R1 + 6) < 7) | (np.abs(r - R0 - 6) < 6) | (np.abs(r - 70) < 8)) & ~brk
    band_m = blur(band.astype(float), 1)
    base = paint(base, band_m, shade(band_m, ["#24282c", "#7a828c", "#d0d6dc"], 2, 30), shadow=None, line=(1, "#101418", 0.7))
    rv, rvd = canvas()
    for k in range(24):
        a = k / 24 * math.tau
        if abs(math.atan2(math.sin(a + 0.62), math.cos(a + 0.62))) < 0.1:
            continue
        x_, y_ = cx + (R0 + 29) * math.cos(a), cy + (R0 + 29) * math.sin(a)
        rvd.ellipse([x_ - 7, y_ - 7, x_ + 7, y_ + 7], fill=255)
    rm = M(rv)
    base = paint(base, rm, shade(rm, ["#202428", "#8a929c", "#f0f4f8"], 2, 40), shadow=None, line=(1, "#101418", 0.7))
    axle, ad = canvas()
    ad.ellipse([cx - 30, cy - 30, cx + 30, cy + 30], fill=255)
    am = M(axle)
    base = paint(base, am, shade(am, ["#101418", "#5a626c", "#c0c8d0"], 4, 40), shadow=None, line=(1.5, "#08090a", 0.9))
    # 折れ口の木口（裂け目のささくれ）
    splint = brk & (r > R0 - 4) & (r < R1 + 4)
    base = mix(base, hexrgb("#d9b07a"), blur(edge(blur(splint.astype(float), 1), 1.5), 0.5) * 0.5)
    return finish(base, rng)


# ── 10. カレンダー: 復活の大ろうそく（その年の数字を刻む） ──────────────

def calendar_paschal(rng):
    base = gold_ground(rng, "#0e1428")
    # 闇の中の灯りの暈
    glow = np.exp(-(((X - C) ** 2 + (Y - 205) ** 2) / (2 * 120 ** 2)))
    disk = np.hypot(X - C, Y - C) < 372
    base = mix(base, hexrgb("#f6c860"), glow * 0.55 * disk)
    x0, x1, top, bot = 428, 596, 268, 790
    candle, cd = canvas()
    cd.rectangle([x0, top, x1, bot], fill=255)
    cd.ellipse([x0, top - 18, x1, top + 18], fill=255)
    cm = M(candle)
    wax = ramp(["#9a8a6a", "#e2d6b8", "#fbf4e2", "#fffaf0", "#c8b896"], (X - x0) / (x1 - x0))
    wax = wax * (1 - 0.18 * np.clip((Y - top) / (bot - top), 0, 1))[..., None]
    base = paint(base, cm, wax, shadow=(14, 18, 14, 0.5), line=(2.5, "#5a4a2a", 0.75))
    drip, dd = canvas()
    dd.rounded_rectangle([x0 + 18, top, x0 + 36, top + 70], 9, fill=255)
    dd.rounded_rectangle([x1 - 52, top, x1 - 38, top + 44], 7, fill=255)
    dm = M(drip)
    base = paint(base, dm, shade(dm, ["#c8b896", "#fff8e8"], 3, 30), shadow=None, line=(1.2, "#8a7a5a", 0.6))
    # 赤い十字、Α・Ω、その年の数字
    cross, crd = canvas()
    crd.rectangle([C - 11, 362, C + 11, 650], fill=255)
    crd.rectangle([C - 68, 452, C + 68, 474], fill=255)
    crm = M(cross, 0.9)
    base = paint(base, crm, shade(crm, ["#5a0606", "#b8181a", "#e8483a"], 2, 30), shadow=None, line=(1.2, "#3a0404", 0.7))
    txt, td = canvas()
    fA = ImageFont.truetype(SERIF_B, 64)
    td.text((C, 318), "Α", font=fA, fill=255, anchor="mm")
    td.text((C, 702), "Ω", font=fA, fill=255, anchor="mm")
    fd = ImageFont.truetype(SERIF_B, 46)
    for ch, (tx, ty) in zip("2026", ((C - 40, 420), (C + 40, 420), (C - 40, 512), (C + 40, 512))):
        td.text((tx, ty), ch, font=fd, fill=255, anchor="mm")
    tm = M(txt, 0.9)
    base = paint(base, tm, shade(tm, ["#5a0606", "#b8181a", "#e8483a"], 2, 30), shadow=None, line=(1.2, "#3a0404", 0.7))
    # 五つの香粒（キリストの五つの傷）
    for gx, gy in ((C, 463), (C, 380), (C, 630), (C - 54, 463), (C + 54, 463)):
        g, gd = canvas()
        gd.ellipse([gx - 13, gy - 13, gx + 13, gy + 13], fill=255)
        gm = M(g)
        base = paint(base, gm, shade(gm, ["#4a0a04", "#a8301a", "#f2b060"], 3, 40), shadow=(3, 4, 3, 0.4), line=(1.2, "#2a0602", 0.85))
        pin, pid = canvas()
        pid.ellipse([gx - 4, gy - 4, gx + 4, gy + 4], fill=255)
        base = mix(base, hexrgb("#f8e090"), M(pin, 0.6))
    # 芯と炎
    wick, wd = canvas()
    wd.line([(C, 262), (C + 2, 232)], fill=255, width=5)
    base = mix(base, hexrgb("#1a1008"), M(wick))
    flame = np.clip(1 - np.hypot((X - C) / 26, (Y - 205) / (60 + (Y < 205) * 10)) ** 1.5, 0, 1)
    flame = flame * np.clip(1 - ((Y - 150) < 0) * 1.0, 0, 1)
    fl_col = ramp(["#c24a10", "#f6a020", "#ffe27a", "#fffbe8"], flame * 1.3)
    base = mix(base, fl_col, np.clip(flame * 2.2, 0, 1))
    base = mix(base, hexrgb("#4a6ad0"), np.clip(1 - np.hypot((X - C) / 9, (Y - 238) / 10), 0, 1) * 0.5)
    # 燭台
    stand, sd = canvas()
    sd.ellipse([C - 140, 774, C + 140, 818], fill=255)
    sd.polygon([(C - 40, 810), (C + 40, 810), (C + 26, 860), (C - 26, 860)], fill=255)
    sd.ellipse([C - 46, 846, C + 46, 878], fill=255)
    sd.polygon([(C - 26, 870), (C + 26, 870), (C + 110, 930), (C - 110, 930)], fill=255)
    stm = M(stand)
    base = paint(base, stm, shade(stm, ["#4a300a", "#a07a28", "#e8c870", "#fff2c0"], 5, 60), shadow=(12, 14, 12, 0.5), line=(2.5, "#2a1a04", 0.85))
    return finish(base, rng)


# ── 11. 時計: 聖ヒエロニムスの書斎の砂時計（デューラー 1514） ────────────

def clock_hourglass(rng):
    base = gold_ground(rng, "#8a1a1a")
    top, neck, bot = 280, 528, 776
    def width(y):
        y = np.asarray(y, float)
        upper = 150 * np.clip(np.sin(np.clip((y - top) / (neck - top), 0, 1) * math.pi * 0.5 + math.pi * 0.5), 0, 1) ** 0.7
        lower = 150 * np.clip(np.sin(np.clip((y - neck) / (bot - neck), 0, 1) * math.pi * 0.5), 0, 1) ** 0.7
        return np.where(y < neck, upper, lower) + 9
    glass = ((np.abs(X - C) < width(Y)) & (Y > top) & (Y < bot)).astype(float)
    gm = blur(glass, 1.2)
    # 砂
    sand_top = (Y < neck) & (Y > 400 + 40 * np.clip(1 - np.abs(X - C) / 70, 0, 1) ** 2)
    pile = (Y > 690 - 70 * np.clip(1 - np.abs(X - C) / 150, 0, 1) ** 1.4) & (Y > neck)
    stream = (np.abs(X - C) < 2.5) & (Y >= neck) & (Y < 690)
    sand = blur(((sand_top | pile | stream).astype(float)) * glass, 1)
    sand_col = ramp(["#8a6420", "#d6aa58", "#f2d290"], 0.4 + 0.5 * lit(sand, 4, 40) - 0.2 + (noise(rng, 3) - 0.5) * 0.3)
    behind = base.copy()
    base = mix(base, sand_col, sand)
    tint = mix(behind, hexrgb("#e8f0f4"), 0.18)
    base = mix(base, tint, gm * (1 - sand) * 0.6)
    hl, hd = canvas()
    for side in (-1, 1):
        for yy0, yy1 in ((top + 30, neck - 40), (neck + 40, bot - 30)):
            pts = [(C + side * (float(width(y)) - 22), y) for y in range(yy0, yy1, 6)]
            hd.line(pts, fill=255, width=7 if side < 0 else 3)
    base = mix(base, hexrgb("#ffffff"), M(hl, 1.5) * gm * 0.75)
    base = mix(base, hexrgb("#3a2a2a"), edge(gm, 2) * 0.6)
    # 木の枠: 上下の板と挽物の柱
    wood_st = ["#2a1206", "#6a3a18", "#a8683a", "#d8a070"]
    for y0 in (228, 776):
        plate, pd = canvas()
        pd.rounded_rectangle([318, y0, 706, y0 + 52], 10, fill=255)
        pm = M(plate)
        base = paint(base, pm, shade(pm, wood_st, 5, 60), shadow=(12, 14, 12, 0.5), line=(2.5, "#1a0802", 0.85))
        bead, bdd = canvas()
        bdd.rectangle([330, y0 + 22, 694, y0 + 30], fill=255)
        base = mix(base, hexrgb("#e0b070"), M(bead, 0.8) * 0.35)
    for px in (348, 676):
        post, pod = canvas()
        for y in range(280, 776, 4):
            w = 13 + 7 * math.sin((y - 280) / 496 * math.pi * 6) ** 2
            pod.rectangle([px - w, y, px + w, y + 4], fill=255)
        pm = M(post)
        turned = ramp(wood_st, 0.25 + 0.6 * np.clip(1 - np.abs(X - px + 5) / 18, 0, 1))
        base = paint(base, pm, turned, shadow=(10, 12, 10, 0.45), line=(2, "#1a0802", 0.85))
    # 吊り輪（壁に掛かった砂時計）
    ring, rd = canvas()
    rd.ellipse([C - 34, 168, C + 34, 236], outline=255, width=10)
    rm = M(ring)
    base = paint(base, rm, shade(rm, ["#202428", "#7a828c", "#d0d6dc"], 2, 30), shadow=(6, 8, 6, 0.4), line=(1.2, "#101418", 0.8))
    return finish(base, rng)


# ── 12. 天気: ノアの契約の虹（創世記 9:13） ──────────────────────────

def weather_rainbow(rng):
    base = gold_ground(rng, "#3a4a5e")
    disk = np.hypot(X - C, Y - C) < 372
    after = np.exp(-(((X - C) ** 2 + (Y - 760) ** 2) / (2 * 300 ** 2)))
    base = mix(base, hexrgb("#c8d8e0"), after * 0.45 * disk)
    cx, cy = C, 740
    r = np.hypot(X - cx, Y - cy)
    bands = ["#c8241e", "#ee7a1e", "#f2cc2a", "#4aa04a", "#2a6ac0", "#5a3a9a"]
    R_out, bw = 400, 26
    bow = np.zeros((S, S))
    col = np.zeros((S, S, 3))
    for k, hexc in enumerate(bands):
        r0 = R_out - k * bw
        m_ = np.clip((r0 - r) / 2, 0, 1) * np.clip((r - (r0 - bw)) / 2, 0, 1) * (Y < cy)
        bow = np.maximum(bow, m_)
        col = col + m_[..., None] * hexrgb(hexc)
    col = col / np.maximum(bow, 1e-6)[..., None]
    base = mix(base, col, bow * 0.88 * disk)
    # 雲（両端に）
    for ccx, ccy, w in ((260, 640, 190), (770, 620, 200), (520, 300, 120)):
        cl, cd = canvas()
        for k in range(9):
            px = ccx + rng.uniform(-w / 2, w / 2)
            py = ccy - abs(rng.normal(0, w * 0.16))
            rr = rng.uniform(w * 0.22, w * 0.36)
            cd.ellipse([px - rr, py - rr, px + rr, py + rr], fill=255)
        cd.rectangle([0, ccy + w * 0.12, S, S], fill=0)
        cm = M(cl, 1.5)
        cloud = shade(cm, ["#8a96a4", "#d4dbe2", "#ffffff"], 10, 60)
        base = paint(base, cm, cloud, shadow=(8, 12, 10, 0.35), line=(2, "#5a6676", 0.6))
    # 水面と箱舟
    water = ((Y > 760 + 6 * np.sin(X / 30)) & disk).astype(float)
    base = mix(base, ramp(["#3a6a8a", "#1e3e5a"], (Y - 760) / 120), blur(water, 1))
    waves, wvd = canvas()
    for wy in range(790, 900, 26):
        pts = [(x, wy + 6 * math.sin(x / 22 + wy)) for x in range(160, 870, 8)]
        wvd.line(pts, fill=255, width=3)
    base = mix(base, hexrgb("#a8c8dc"), M(waves, 0.8) * water * 0.5)
    ark, ad = canvas()
    ad.polygon([(390, 740), (634, 740), (604, 800), (420, 800)], fill=255)
    am = M(ark)
    plank = ramp(["#3a1a08", "#7a4420", "#a86a3a"], 0.5 + 0.3 * np.sin(Y / 4))
    base = paint(base, am, plank * (0.8 + 0.3 * lit(am, 4, 40))[..., None], shadow=(8, 6, 8, 0.4), line=(2, "#1a0a02", 0.85))
    house, hd = canvas()
    hd.rectangle([450, 690, 574, 742], fill=255)
    hd.polygon([(436, 694), (C, 650), (588, 694)], fill=255)
    hm = M(house)
    base = paint(base, hm, shade(hm, ["#4a2a10", "#8a5a30", "#c08a50"], 4, 40), shadow=None, line=(2, "#1a0a02", 0.85))
    win, wd = canvas()
    wd.rectangle([496, 704, 528, 728], fill=255)
    base = mix(base, hexrgb("#1a0e06"), M(win, 0.8) * 0.9)
    return finish(base, rng)


# ── 対応表 ───────────────────────────────────────────────────────

ICONS = [
    ("電話", "百合", "大天使ガブリエル", phone_lily,
     "受胎告知で神の言葉を伝えた「告げる者」。1951年、教皇ピウス12世が電信・電話・放送の守護聖人に定めた。持物は告知の白百合。"),
    ("メッセージ", "聖霊の鳩", "ペンテコステ（使徒言行録 2章）", message_dove,
     "聖霊が降り、使徒たちの言葉が各国の人にそれぞれの言語で通じた日。くわえたオリーブの枝はノアに陸地を知らせた鳩の便り。"),
    ("カメラ", "聖顔布", "聖ヴェロニカ", camera_veil,
     "布で顔を拭うとそのまま像が写った「真の像（vera icon）」。手で描かずに写し取られた像として、写真家の守護聖人とされる。"),
    ("ギャラリー", "聖母の板絵と絵筆", "聖ルカ", gallery_luke,
     "最初に聖母子を描いたと伝わる福音記者で、画家の守護聖人。欧州の画家組合は「聖ルカ組合」と名乗った。"),
    ("メール", "書簡と剣", "使徒パウロ", mail_paul,
     "ローマ、コリント、ガラテヤ…各地の教会へ宛てた書簡が新約聖書に収められた、手紙の人。剣は殉教の印。"),
    ("ブラウザ", "十字架つき水晶宝珠", "サルヴァトール・ムンディ（世界の救世主）", browser_orb,
     "手のひらに「世界」を載せる宝珠。レオナルドの作では透明な水晶。（インターネットの守護聖人の候補にはセビリャの聖イシドルスも）"),
    ("マップ", "帆立貝", "聖ヤコブ（サンティアゴ巡礼）", maps_shell,
     "サンティアゴ巡礼路では、青地に黄の帆立貝が今も道標。放射状の筋は、各地からの道が一つの目的地に集まる様にたとえられる。"),
    ("ミュージック", "携帯オルガン", "聖チェチーリア", music_organ,
     "婚礼の楽の音の中でも心の中で神に歌ったと伝わる、音楽の守護聖人。持物はオルガン。"),
    ("設定", "棘の車輪（折れた車輪）", "アレクサンドリアの聖カタリナ", settings_wheel,
     "処刑の車輪は彼女に触れて砕けた。歯車のような形から車大工・機械工の守護聖人。花火の「キャサリン・ホイール」の語源。"),
    ("カレンダー", "復活の大ろうそく", "復活徹夜祭", calendar_paschal,
     "司祭が十字とΑ・Ω、そして「その年の数字」を刻み「時も世々も主のもの」と唱える。教会の暦そのものを刻む蝋燭。"),
    ("時計", "砂時計", "聖ヒエロニムス（デューラーの版画 1514）", clock_hourglass,
     "デューラー『書斎の聖ヒエロニムス』で壁に掛かる砂時計は、時が流れ去ることの印（メメント・モリ）。背後の赤はヒエロニムスの枢機卿の赤。"),
    ("天気", "虹", "ノアの契約（創世記 9:13）", weather_rainbow,
     "大洪水の後、二度と世を水で滅ぼさない約束として神が雲の中に置いた虹。雨上がりを告げる最初の「天気のしるし」。"),
]


# ── シート ──────────────────────────────────────────────────────

def font(sz):
    return ImageFont.truetype(JP_FONT, sz)


def wrap(d, text, f, width):
    lines, cur = [], ""
    for ch in text:
        if d.textlength(cur + ch, font=f) > width and ch not in "。、）」』・…":
            lines.append(cur)
            cur = ch
        else:
            cur += ch
    if cur:
        lines.append(cur)
    return lines


def sheet(rendered):
    cols, cw, chh, pad, top = 2, 800, 270, 40, 150
    rows = math.ceil(len(rendered) / cols)
    W, H = pad * 2 + cols * cw, top + rows * chh + pad
    im = Image.new("RGB", (W, H), (246, 242, 233))
    d = ImageDraw.Draw(im)
    d.text((pad, 30), "聖なる持物（アトリビュート）アイコンパック　叩き台 v3", font=font(40), fill=(30, 26, 20))
    d.text((pad, 88), "Android 標準アプリ 12 種 × キリスト教美術の聖人・持物。金地の祭壇画パネルに、各アプリと結びつく持物を描く。",
           font=font(20), fill=(110, 100, 86))
    for i, (app, attr, who, icon, why) in enumerate(rendered):
        x0 = pad + (i % cols) * cw
        y0 = top + (i // cols) * chh
        ic = icon.resize((OUT, OUT), Image.LANCZOS)
        sh = Image.new("RGBA", (OUT, OUT), (0, 0, 0, 0))
        sh.putalpha(ic.split()[3].filter(ImageFilter.GaussianBlur(7)).point(lambda v: v * 0.35))
        im.paste((0, 0, 0), (x0 + 3, y0 + 8), sh)
        im.paste(ic, (x0, y0), ic)
        tx = x0 + OUT + 28
        d.text((tx, y0 + 2), app, font=font(30), fill=(30, 26, 20))
        d.text((tx, y0 + 46), f"{attr} ／ {who}", font=font(19), fill=(150, 40, 30))
        for k, line in enumerate(wrap(d, why, font(17), cw - OUT - 60)[:5]):
            d.text((tx, y0 + 84 + k * 26), line, font=font(17), fill=(80, 74, 64))
    im.save(DEST / "attributes_sheet.png", optimize=True)


def homescreen(rendered):
    """実寸感の確認: 壁紙の上に 4 列で並べる（端末上の見え方）。"""
    W, H = 720, 660
    bg = np.zeros((H, W, 3))
    yy, xx = np.mgrid[0:H, 0:W]
    bg[:] = ramp(["#1a2238", "#2a2440", "#3a2a3a"], yy / H)
    im = Image.fromarray(bg.astype(np.uint8))
    d = ImageDraw.Draw(im)
    size = 132
    for i, (app, _, _, icon, _) in enumerate(rendered):
        x = 44 + (i % 4) * 166
        y = 50 + (i // 4) * 200
        ic = icon.resize((size, size), Image.LANCZOS)
        im.paste(ic, (x, y), ic)
        tw = d.textlength(app, font=font(20))
        d.text((x + (size - tw) / 2, y + size + 12), app, font=font(20), fill=(240, 236, 228))
    im.save(DEST / "homescreen.png", optimize=True)


def detail(rendered):
    """細部の確認用に 3 点を 480px で。"""
    picks = [0, 2, 9]
    W, H = 3 * 500 + 40, 560
    im = Image.new("RGB", (W, H), (246, 242, 233))
    d = ImageDraw.Draw(im)
    for j, i in enumerate(picks):
        app, attr, *_ = rendered[i]
        ic = rendered[i][3].resize((480, 480), Image.LANCZOS)
        im.paste(ic, (20 + j * 500, 20), ic)
        d.text((30 + j * 500, 510), f"{app}（{attr}）拡大", font=font(22), fill=(60, 54, 44))
    im.save(DEST / "detail.png", optimize=True)


def main():
    import sys
    DEST.mkdir(parents=True, exist_ok=True)
    only = set(sys.argv[1:])
    rendered = []
    for i, (app, attr, who, fn, why) in enumerate(ICONS):
        if only and fn.__name__ not in only:
            continue
        print(f"[RENDER] {fn.__name__}")
        icon = fn(np.random.default_rng(2000 + i))
        icon.resize((OUT, OUT), Image.LANCZOS).save(DEST / f"{fn.__name__}.png")
        icon.resize((432, 432), Image.LANCZOS).save(DEST / f"{fn.__name__}_432.png")
        rendered.append((app, attr, who, icon, why))
    if not only:
        sheet(rendered)
        homescreen(rendered)
        detail(rendered)
    print(f"[DONE] {DEST}")


if __name__ == "__main__":
    main()
