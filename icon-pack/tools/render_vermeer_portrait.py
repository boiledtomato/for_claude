"""聖なる持物パック v7 — フェルメール版を縦長の額装の油彩画にする（『真珠の耳飾りの少女』の質感）。

render_vermeer の室内を、持物を左へ寄せて描き直し、窓を含む左側を 3:4 前後の縦長に切り出す。
それを黒檀の波形額に入れ、壁の釘から紐で吊るし、壁へ影を落とした透過 PNG にする。
アイコンの外形は四角いキャンバスの中の縦長の額縁で、額の外は透明（壁紙が透ける）。

    python icon-pack/tools/render_vermeer_portrait.py

出力: icon-pack/samples/vermeer_portrait/*.png
"""
from __future__ import annotations

import math
import sys
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont

sys.path.insert(0, str(Path(__file__).resolve().parent))
import render_attributes as ra  # noqa: E402
import render_vermeer as rv  # noqa: E402
import render_northern as rn  # noqa: E402
from render_attributes import S, OUT, hexrgb, ramp, fbm, noise  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "vermeer_portrait"
CROP_W = 744          # 1024 の高さに対して 0.727（ほぼ 3:4）
SHIFT = -180          # 持物を切り出し範囲の中央へ寄せる
CROP_X = {"weather_rainbow": 170}


# ── render_vermeer を縦長の構図に合わせて差し替える ─────────────────────

def _patch():
    for k, (cloth, deco, s, dx, dy) in list(rv.SETUP.items()):
        rv.SETUP[k] = (cloth, deco, s, dx + SHIFT, dy)
    cloth, deco, s, dx, dy = rv.SETUP["maps_shell"]
    rv.SETUP["maps_shell"] = (cloth, deco, 0.9, dx, dy + 30)
    orig_nail = rv.nail
    rv.nail = lambda base, x, y: orig_nail(base, x - 260 if x > 600 else x, y)
    orig_painting = rv.framed_painting
    rv.framed_painting = lambda base, x0, y0, x1, y1, rng: orig_painting(base, 520, 96, 712, 280, rng)
    rv.MAP_BOX = (226, 730, 96, 500)
    rv.plaster_wall = dark_ground
    rv.leaded_window = lambda base, rng: base
    rv.wall_map = lambda base, rng: base
    rv.framed_painting = lambda base, *a_, **k_: base
    rv.nail = lambda base, x, y: base
    rv.turkish_carpet = dark_cloth
    rv.blue_cloth = dark_cloth
    rv.finish = lambda base, rng, a=None: _raw_finish(base, rng, a)


_LAST_ALPHA = {}


def _raw_finish(base, rng, a=None):
    """筆で塗り直す前の素の画面を返す（持物のマスクは光の粒用に控えておく）。"""
    _LAST_ALPHA["a"] = a
    return base


# ── 油彩の物質感（筆跡は見せない） ─────────────────────────────────

def _quadrant_means(img, r):
    """Kuwahara 用: 各画素の四象限の平均色と輝度分散。"""
    H, W = img.shape[:2]
    lum = img.mean(-1)
    def integ(a):
        a = np.pad(a, ((r, r), (r, r)), mode="edge")
        P = np.zeros((a.shape[0] + 1, a.shape[1] + 1) + a.shape[2:])
        P[1:, 1:] = a.cumsum(0).cumsum(1)
        return P
    chans = [integ(img[..., c]) for c in range(3)] + [integ(lum), integ(lum ** 2)]
    n = (r + 1) ** 2
    res = []
    for y0, x0 in ((-r, -r), (-r, 0), (0, -r), (0, 0)):
        y1, x1 = y0 + r, x0 + r
        def rect(P):
            return (P[r + y1 + 1:r + y1 + 1 + H, r + x1 + 1:r + x1 + 1 + W]
                    - P[r + y0:r + y0 + H, r + x1 + 1:r + x1 + 1 + W]
                    - P[r + y1 + 1:r + y1 + 1 + H, r + x0:r + x0 + W]
                    + P[r + y0:r + y0 + H, r + x0:r + x0 + W]) / n
        m = np.stack([rect(chans[c]) for c in range(3)], -1)
        var = rect(chans[4]) - rect(chans[3]) ** 2
        res.append((m, var))
    return res


def paint_surface(img, r=6):
    """輪郭を保ったまま色面をまとめる（Kuwahara）。油絵具で塗り込めた面になる。"""
    res = _quadrant_means(img, r)
    var = np.stack([v for _, v in res], 0)
    idx = var.argmin(0)
    out = np.zeros_like(img)
    for k, (m, _) in enumerate(res):
        out = np.where((idx == k)[..., None], m, out)
    return np.stack([ra.blur(out[..., c], 0.9) for c in range(3)], -1)


def lost_edges(base, a):
    """輪郭線を消し、陰の側（右・下）で物を闇に溶かす。"""
    if a is None:
        return base
    band = np.clip(1 - np.abs(ra.blur(a, 3) - 0.5) * 2.4, 0, 1)
    shadow_side = np.clip((ra.X - 300) / 500, 0.25, 1)
    soft = np.stack([ra.blur(base[..., c], 3.5) for c in range(3)], -1)
    return ra.mix(base, soft, np.clip(band * (0.7 + 0.6 * shadow_side), 0, 1))


def crack_network(rng, cell=30):
    """『真珠の耳飾りの少女』に見える網目状の亀裂。ジッタ格子のボロノイ境界。"""
    n = S // cell + 2
    sx = (np.arange(n)[None, :] + rng.uniform(0.15, 0.85, (n, n))) * cell
    sy = (np.arange(n)[:, None] + rng.uniform(0.15, 0.85, (n, n))) * cell
    gy, gx = (ra.Y // cell).astype(int), (ra.X // cell).astype(int)
    d = []
    for oy in (-1, 0, 1):
        for ox in (-1, 0, 1):
            cy, cx = np.clip(gy + oy, 0, n - 1), np.clip(gx + ox, 0, n - 1)
            d.append(np.hypot(ra.X - sx[cy, cx], ra.Y - sy[cy, cx]))
    d = np.sort(np.stack(d, 0), 0)
    crack = np.clip(1 - (d[1] - d[0]) / 1.4, 0, 1)
    gaps = np.clip((fbm(rng, (64, 32)) - 0.38) * 4, 0, 1)   # ところどころ途切れる
    return crack * gaps


def canvas_weave(rng, period=7.0):
    """亜麻布の平織り。経糸と緯糸が交互に浮く。"""
    jx = (noise(rng, 24) - 0.5) * 1.6
    jy = (noise(rng, 24) - 0.5) * 1.6
    u = (ra.X + jx) / period
    v = (ra.Y + jy) / period
    warp = 0.5 + 0.5 * np.cos(2 * math.pi * u)
    weft = 0.5 + 0.5 * np.cos(2 * math.pi * v)
    checker = ((np.floor(u) + np.floor(v)) % 2).astype(float)
    return warp * checker + weft * (1 - checker)


def oil_finish(base, rng, a=None):
    base = lost_edges(base, a)
    base = paint_surface(base, 4)
    lum = np.clip(base.mean(-1) / 255, 0, 1)
    # 明部ほど鉛白が厚い: 低い盛り上がりに左上の光
    h = ra.blur(lum, 4) * np.clip(lum * 1.8 - 0.3, 0, 1)
    gy, gx = np.gradient(h * 30)
    relief = np.clip(-gx * -0.6 - gy * -0.7, -1, 1)
    base = base * (1 + relief * 0.18)[..., None]
    # 布目（明部でよく見える）
    weave = canvas_weave(rng)
    base = base * (1 + (weave - 0.5) * (0.05 + 0.07 * lum))[..., None]
    # 網目の亀裂: 暗い溝と、めくれた縁の小さな光
    cr = crack_network(rng)
    base = base * (1 - cr * (0.22 + 0.25 * lum))[..., None]
    lip = np.roll(np.roll(cr, -1, 0), -1, 1) * (1 - cr)
    base = ra.mix(base, hexrgb("#f4e6c0"), lip * lum * 0.18)
    # 黄ばんだワニスのむらと、左上から斜めに走る艶
    varn = 0.94 + 0.08 * fbm(rng, (128, 64))
    base = base * varn[..., None] * np.array([1.04, 1.0, 0.86])
    sheen = np.exp(-(((ra.X + ra.Y - 520) / 160) ** 2)) * np.clip(1 - ra.Y / 900, 0, 1)
    return base + sheen[..., None] * 14


def pearl_highlights(base, rng, a, n=6):
    """真珠の耳飾りのように、ごく少数の澄んだ光の点だけを置く。"""
    if a is None or not (a > 0.5).any():
        return base
    lum = base.mean(-1)
    thr = np.quantile(lum[a > 0.5], 0.995)
    pts = np.argwhere((lum >= thr) & (a > 0.5))
    if not len(pts):
        return base
    pts = pts[rng.choice(len(pts), min(n, len(pts)), replace=False)]
    dots = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(dots)
    for y, x in pts:
        r = rng.uniform(4, 7)
        d.ellipse([x - r, y - r * 0.8, x + r, y + r * 0.8], fill=255)
    m = ra.blur(np.asarray(dots, float) / 255, 1.6)
    return ra.mix(base, hexrgb("#fffaf0"), m * 0.85)


def grade(base):
    """明暗を深め（S 字の階調）、光は鉛白のクリーム、影は温かい褐色に寄せる。"""
    lum0 = np.clip(base.mean(-1) / 255, 0, 1)
    curve = lum0 + 0.18 * np.sin((lum0 - 0.5) * math.pi) * 0.5 - 0.0
    base = base * (np.clip(curve, 0, 1) / np.maximum(lum0, 1e-3))[..., None] * 0.5 + base * 0.5
    lum = np.clip(base.mean(-1) / 255, 0, 1)[..., None]
    out = base * (1 - (1 - lum) ** 2 * 0.1) + hexrgb("#3a2a1c") * (1 - lum) ** 2 * 0.1
    out = out * (1 - lum ** 3 * 0.06) + hexrgb("#fff0cc") * lum ** 3 * 0.06
    return out


def dark_ground(rng):
    """『真珠の耳飾りの少女』の地: 緑の釉が暗く沈んだ黒。左上の光がわずかに地を起こす。"""
    X, Y = ra.X, ra.Y
    glow = np.exp(-(((X - 300) ** 2 + (Y - 330) ** 2) / (2 * 300 ** 2)))
    base = ramp(["#0b0d09", "#181a12", "#2a2a1e", "#3a3424"], glow * 0.9 + (fbm(rng, (128, 64)) - 0.5) * 0.2)
    # 透ける下塗り: 温かい褐色と冷たい緑の斑（暗い地にも絵具の層がある）
    under = fbm(rng, (96, 48, 24))
    base = ra.mix(base, base * 0.7 + hexrgb("#3a2414") * 0.3, np.clip((under - 0.5) * 3, 0, 1) * 0.6)
    base = ra.mix(base, base * 0.8 + hexrgb("#1a2a1e") * 0.2, np.clip((0.5 - under) * 3, 0, 1) * 0.5)
    return base


def dark_cloth(base, rng):
    """卓に掛けた布。『真珠の耳飾りの少女』のターバンの群青と、右へ垂れる鉛錫黄の布。"""
    X, Y = ra.X, ra.Y
    fold = np.sin(X / 52 + np.sin(X / 150) * 2.2 + fbm(rng, (128,)) * 4)
    t = np.where(Y < rv.FRONT, 0.6 + 0.1 * fold, 0.42 + 0.26 * fold - (Y - rv.FRONT) / 520)
    blue = ramp(["#0a0e22", "#1c2a62", "#3c56a8", "#7e92cc", "#c4cce4"], t)
    yellow = ramp(["#2a1e0a", "#6a5220", "#b89a48", "#e6d08c", "#f6ecc4"], t + 0.05)
    tail = np.clip((X - 560 - 40 * np.sin(Y / 60)) / 30, 0, 1) * (Y > rv.FRONT - 10)
    col = ra.mix(blue, yellow, tail)
    fall = np.clip(1.15 - X / 950, 0.3, 1) * (1 - tail) + np.clip(1.25 - X / 1300, 0.5, 1) * tail
    col = col * fall[..., None]
    return ra.mix(base, col, ra.blur((Y >= rv.TABLE).astype(float), 1.5))


def chiaroscuro(base, a):
    """左上の一灯。持物の右側と下は闇へ溶ける。"""
    X, Y = ra.X, ra.Y
    light = np.clip(np.exp(-(((X - 180) ** 2 + (Y - 120) ** 2) / (2 * 620 ** 2))) * 1.35, 0.3, 1.15)
    return base * light[..., None]


# ── 額装と吊り下げ ─────────────────────────────────────────────

def ebony_frame(img: np.ndarray, fw=52) -> np.ndarray:
    """17 世紀オランダの黒檀の額。外の面取り・波形（リップル）の帯・内側の段。光は左上から。"""
    h, w = img.shape[:2]
    H, W = h + 2 * fw, w + 2 * fw
    yy, xx = np.mgrid[0:H, 0:W].astype(float)
    d = np.minimum.reduce([xx, yy, W - 1 - xx, H - 1 - yy])  # 外周からの距離
    t = d / fw
    # 位置ごとに辺を判定し、光（左上）に向く辺を明るく
    side_l = (xx <= yy) & (xx <= H - 1 - yy) & (xx <= W - 1 - xx)
    side_t = (yy < xx) & (yy <= W - 1 - xx) & ~side_l
    side_r = (W - 1 - xx < yy) & (W - 1 - xx < H - 1 - yy) & ~side_l & ~side_t
    facing = np.where(side_l | side_t, 1.0, np.where(side_r, -0.4, -0.7))
    along = np.where(side_l | side_r, yy, xx)
    profile = np.where(t < 0.18, 0.55 - (t / 0.18) * 0.25,           # 外の面取り
              np.where(t < 0.72, 0.42 + 0.22 * np.sin(along * 0.62 + t * 9),  # 波形の帯
              np.where(t < 0.82, 0.25, 0.6 - (t - 0.82) * 2.5)))        # 内側の段と縁
    tone = np.clip(profile * (0.55 + 0.45 * facing) + 0.08, 0, 1)
    frame = ramp(["#050505", "#141210", "#2c2824", "#5a544e", "#8a847c"], tone)
    out = frame.copy()
    # 絵は額の奥に沈む: 上と左の内縁に額の落とす影
    inner = img.copy()
    iy, ix = np.mgrid[0:h, 0:w].astype(float)
    sh = np.exp(-ix / 14) * 0.55 + np.exp(-iy / 16) * 0.6
    inner = inner * (1 - np.clip(sh, 0, 0.75))[..., None]
    out[fw:fw + h, fw:fw + w] = inner
    return out


def hang(framed: np.ndarray, rng, canvas_px=1024, top=86) -> Image.Image:
    """額を正方形の透過キャンバスに置き、釘・紐・環と壁への影を描く。"""
    fh, fw_ = framed.shape[:2]
    scale = (canvas_px - top - 26) / fh
    im = Image.fromarray(np.clip(framed, 0, 255).astype(np.uint8)).resize(
        (int(fw_ * scale), int(fh * scale)), Image.LANCZOS)
    w, h = im.size
    x0 = (canvas_px - w) // 2
    cv = Image.new("RGBA", (canvas_px, canvas_px), (0, 0, 0, 0))
    # 壁への影（額は紐で吊られて上が壁から離れるので、上ほど影が大きい）
    shadow = Image.new("L", (canvas_px, canvas_px), 0)
    sd = ImageDraw.Draw(shadow)
    sd.polygon([(x0 + 18, top + 30), (x0 + w + 30, top + 30), (x0 + w + 22, top + h + 18), (x0 + 14, top + h + 14)], fill=150)
    nail = (canvas_px // 2, 26)
    eyes = [(x0 + w * 0.22, top + 6), (x0 + w * 0.78, top + 6)]
    for ex, ey in eyes:
        sd.line([(nail[0] + 6, nail[1] + 8), (ex + 10, ey + 16)], fill=110, width=5)
    shadow = shadow.filter(ImageFilter.GaussianBlur(16))
    cv.paste((0, 0, 0, 255), (0, 0), shadow)
    # 紐（2 本の撚り糸が釘で交わる）
    cord = ImageDraw.Draw(cv)
    for ex, ey in eyes:
        cord.line([nail, (ex, ey)], fill=(70, 52, 34, 255), width=6)
        cord.line([nail, (ex, ey)], fill=(140, 110, 72, 255), width=2)
    # 額
    cv.alpha_composite(im.convert("RGBA"), (x0, top))
    # 額の上辺の吊り環（真鍮）
    for ex, ey in eyes:
        cord.ellipse([ex - 11, ey - 14, ex + 11, ey + 8], outline=(110, 84, 34, 255), width=5)
        cord.arc([ex - 11, ey - 14, ex + 11, ey + 8], 200, 290, fill=(230, 200, 120, 255), width=2)
    # 釘
    cord.ellipse([nail[0] - 11, nail[1] - 11, nail[0] + 11, nail[1] + 11], fill=(40, 36, 32, 255))
    cord.ellipse([nail[0] - 6, nail[1] - 8, nail[0] + 2, nail[1] - 1], fill=(120, 112, 100, 255))
    return cv


def render(fn, seed):
    name = fn.__name__
    if name == "weather_rainbow":
        rng = np.random.default_rng(seed + 501)
        scene = _raw_finish(rv.view_of_delft(rng), rng)
    else:
        scene = rv.scene(fn, seed)
    a = _LAST_ALPHA.get("a")
    prng = np.random.default_rng(seed + 9)
    if name != "weather_rainbow":
        scene = chiaroscuro(scene, a)
    scene = pearl_highlights(scene, prng, a)
    scene = grade(scene)
    scene = oil_finish(scene, prng, a)
    x0 = CROP_X.get(name, 0)
    crop = scene[:, x0:x0 + CROP_W]
    framed = ebony_frame(crop)
    return hang(framed, np.random.default_rng(seed))


# ── シートとホーム画面 ──────────────────────────────────────────

def font(sz):
    return ImageFont.truetype(ra.JP_FONT, sz)


def homescreen(rendered, path, wall=False):
    W, H = 760, 760
    yy, xx = np.mgrid[0:H, 0:W]
    if wall:
        rng = np.random.default_rng(3)
        n = np.asarray(Image.fromarray(rng.random((H // 8 + 2, W // 8 + 2)).astype(np.float32), "F")
                       .resize((W + 16, H + 16), Image.BICUBIC))[:H, :W]
        bg = ramp(["#b8b0a0", "#d8d0bc", "#e6dfcc"], 0.75 - xx / 2200 - yy / 2600 + (n - 0.5) * 0.08)
        text_col = (50, 44, 36)
    else:
        bg = ramp(["#1e2430", "#262a34", "#30302e"], yy / H)
        text_col = (240, 236, 228)
    im = Image.fromarray(bg.astype(np.uint8)).convert("RGBA")
    d = ImageDraw.Draw(im)
    size = 150
    for i, (app, icon) in enumerate(rendered):
        x, y = 30 + (i % 4) * 182, 26 + (i // 4) * 240
        ic = icon.resize((size, size), Image.LANCZOS)
        im.alpha_composite(ic, (x, y))
        tw = d.textlength(app, font=font(20))
        d.text((x + (size - tw) / 2, y + size + 8), app, font=font(20), fill=text_col)
    im.convert("RGB").save(path, optimize=True)


def detail(rendered, picks=(0, 6, 11)):
    W, H = 3 * 520 + 40, 600
    im = Image.new("RGBA", (W, H), (222, 214, 196, 255))
    d = ImageDraw.Draw(im)
    for j, i in enumerate(picks):
        app, icon = rendered[i]
        im.alpha_composite(icon.resize((500, 500), Image.LANCZOS), (20 + j * 520, 20))
        d.text((40 + j * 520, 540), f"{app} 拡大（壁の色の上）", font=font(22), fill=(60, 54, 44))
    im.convert("RGB").save(DEST / "detail.png", optimize=True)


def main():
    _patch()
    DEST.mkdir(parents=True, exist_ok=True)
    only = set(sys.argv[1:])
    rendered = []
    for i, (app, attr, who, fn, why) in enumerate(ra.ICONS):
        if only and fn.__name__ not in only:
            continue
        print(f"[RENDER] {fn.__name__}")
        icon = render(fn, 2000 + i)
        icon.resize((OUT, OUT), Image.LANCZOS).save(DEST / f"{fn.__name__}.png")
        icon.resize((432, 432), Image.LANCZOS).save(DEST / f"{fn.__name__}_432.png")
        rendered.append((app, icon))
    if not only:
        homescreen(rendered, DEST / "homescreen_dark.png")
        homescreen(rendered, DEST / "homescreen_wall.png", wall=True)
        detail(rendered)
    print(f"[DONE] {DEST}")


if __name__ == "__main__":
    main()
