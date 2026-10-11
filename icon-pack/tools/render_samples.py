"""画家スタイル・アイコンパックの叩き台サンプルを手続き的に描画する。

各アイコンは「グリフのマスク（白=図柄）」を作り、画家ごとのレンダラーが
そのマスクから筆致・点描・明暗法・キュビスム的分割などを描き起こす。
生成 AI は使っていない — 風合いの方向性を見るためのモックアップ。

    python icon-pack/tools/render_samples.py

出力: icon-pack/samples/*.png
"""
from __future__ import annotations

import math
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont

S = 512            # 作業解像度
OUT = 192          # 出力アイコン解像度 (xxxhdpi)
ROOT = Path(__file__).resolve().parents[1]
SAMPLES = ROOT / "samples"
FONT = "/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc"


# ── 数値ユーティリティ ─────────────────────────────────────────────

def blur(a: np.ndarray, sigma: float) -> np.ndarray:
    """FFT によるガウスぼかし（float 配列用。Pillow は F モードをぼかせない）。"""
    h, w = a.shape
    fy = np.fft.fftfreq(h)[:, None]
    fx = np.fft.fftfreq(w)[None, :]
    g = np.exp(-2 * (math.pi ** 2) * (sigma ** 2) * (fx ** 2 + fy ** 2))
    return np.real(np.fft.ifft2(np.fft.fft2(a) * g))


def blur_rgb(a: np.ndarray, sigma: float) -> np.ndarray:
    return np.stack([blur(a[..., c], sigma) for c in range(a.shape[2])], -1)


def noise(rng, scale: int, size: int = S) -> np.ndarray:
    n = size // scale + 3
    small = Image.fromarray(rng.random((n, n)).astype(np.float32), "F")
    big = small.resize((size + 2 * scale, size + 2 * scale), Image.BICUBIC)
    return np.asarray(big)[scale:scale + size, scale:scale + size]


def fbm(rng, scales=(128, 64, 32, 16), size: int = S) -> np.ndarray:
    out = np.zeros((size, size))
    amp, tot = 1.0, 0.0
    for sc in scales:
        out += amp * noise(rng, sc, size)
        tot += amp
        amp *= 0.5
    return out / tot


def hexrgb(h: str) -> np.ndarray:
    h = h.lstrip("#")
    return np.array([int(h[i:i + 2], 16) for i in (0, 2, 4)], float)


def lerp(a, b, t):
    t = np.asarray(t)[..., None] if np.ndim(t) else t
    return a + (b - a) * t


def ramp(stops: list[str], t: np.ndarray) -> np.ndarray:
    """t ∈ [0,1] を色の段階で塗る。"""
    cols = np.array([hexrgb(s) for s in stops])
    t = np.clip(t, 0, 1) * (len(stops) - 1)
    i = np.clip(np.floor(t).astype(int), 0, len(stops) - 2)
    f = (t - i)[..., None]
    return cols[i] * (1 - f) + cols[i + 1] * f


def grid():
    y, x = np.mgrid[0:S, 0:S].astype(float)
    return x, y


def to_img(rgb: np.ndarray) -> Image.Image:
    return Image.fromarray(np.clip(rgb, 0, 255).astype(np.uint8), "RGB")


def squircle_mask(size: int = S, n: float = 4.5, pad: float = 0.02) -> np.ndarray:
    """アダプティブアイコン相当のスクワークル。端は 2px のアンチエイリアス。"""
    y, x = np.mgrid[0:size, 0:size].astype(float)
    c = (size - 1) / 2
    r = c * (1 - pad)
    d = (np.abs((x - c) / r) ** n + np.abs((y - c) / r) ** n) ** (1 / n)
    return np.clip((1 - d) * size / 4, 0, 1)


SHAPE = squircle_mask()


# ── グリフ（汎用アプリ図柄） ─────────────────────────────────────

def _canvas():
    im = Image.new("L", (S, S), 0)
    return im, ImageDraw.Draw(im)


def g_phone():
    im, d = _canvas()
    d.rounded_rectangle([196, 112, 316, 182], 30, fill=255)
    d.rounded_rectangle([196, 330, 316, 400], 30, fill=255)
    d.rounded_rectangle([196, 140, 252, 372], 26, fill=255)
    return im.rotate(-38, resample=Image.BICUBIC, center=(256, 256))


def g_message():
    im, d = _canvas()
    d.rounded_rectangle([110, 130, 402, 340], 70, fill=255)
    d.polygon([(170, 320), (150, 404), (250, 330)], fill=255)
    for cx in (186, 256, 326):
        d.ellipse([cx - 20, 215, cx + 20, 255], fill=0)
    return im


def g_camera():
    im, d = _canvas()
    d.rounded_rectangle([200, 140, 312, 200], 18, fill=255)
    d.rounded_rectangle([104, 170, 408, 382], 44, fill=255)
    d.ellipse([174, 196, 338, 360], fill=0)
    d.ellipse([200, 222, 312, 334], fill=255)
    d.ellipse([340, 196, 372, 228], fill=0)
    return im


def g_gallery():
    im, d = _canvas()
    d.rounded_rectangle([104, 126, 408, 386], 34, fill=255)
    d.rounded_rectangle([134, 156, 378, 356], 14, fill=0)
    d.polygon([(134, 356), (220, 240), (290, 320), (330, 280), (378, 340), (378, 356)], fill=255)
    d.ellipse([306, 176, 350, 220], fill=255)
    return im


def g_mail():
    im, d = _canvas()
    d.rounded_rectangle([100, 150, 412, 362], 28, fill=255)
    d.line([(118, 172), (256, 280), (394, 172)], fill=0, width=24, joint="curve")
    d.line([(118, 344), (214, 262)], fill=0, width=18)
    d.line([(394, 344), (298, 262)], fill=0, width=18)
    return im


def g_browser():
    im, d = _canvas()
    d.ellipse([110, 110, 402, 402], fill=255)
    w = 14
    d.ellipse([196, 116, 316, 396], outline=0, width=w)
    d.line([(256, 112), (256, 400)], fill=0, width=w)
    d.line([(112, 256), (400, 256)], fill=0, width=w)
    d.arc([130, 70, 382, 220], 20, 160, fill=0, width=w)
    d.arc([130, 292, 382, 442], 200, 340, fill=0, width=w)
    return im


def g_maps():
    im, d = _canvas()
    d.ellipse([146, 96, 366, 316], fill=255)
    d.polygon([(160, 250), (352, 250), (256, 420)], fill=255)
    d.ellipse([212, 162, 300, 250], fill=0)
    return im


def g_music():
    im, d = _canvas()
    d.polygon([(206, 140), (380, 106), (380, 160), (206, 194)], fill=255)
    d.rectangle([206, 150, 234, 342], fill=255)
    d.rectangle([352, 116, 380, 312], fill=255)
    for cx, cy in ((186, 346), (332, 316)):
        e = Image.new("L", (S, S), 0)
        ImageDraw.Draw(e).ellipse([cx - 52, cy - 36, cx + 52, cy + 36], fill=255)
        im.paste(255, mask=e.rotate(20, center=(cx, cy)))
    return im


def g_settings():
    im, d = _canvas()
    c, r_out, r_in = 256, 150, 118
    for k in range(8):
        a = k * math.pi / 4
        pts = []
        for da, rr in ((-0.20, r_in), (-0.14, r_out), (0.14, r_out), (0.20, r_in)):
            pts.append((c + rr * math.cos(a + da), c + rr * math.sin(a + da)))
        d.polygon(pts, fill=255)
    d.ellipse([c - r_in - 4, c - r_in - 4, c + r_in + 4, c + r_in + 4], fill=255)
    d.ellipse([c - 52, c - 52, c + 52, c + 52], fill=0)
    return im


def g_calendar():
    im, d = _canvas()
    d.rounded_rectangle([110, 130, 402, 400], 30, fill=255)
    d.rectangle([110, 196, 402, 212], fill=0)
    for x in (180, 332):
        d.rounded_rectangle([x - 14, 100, x + 14, 166], 14, fill=255)
        d.ellipse([x - 22, 142, x + 22, 186], fill=0)
        d.rounded_rectangle([x - 12, 100, x + 12, 164], 12, fill=255)
    for r in range(3):
        for c in range(4):
            x0, y0 = 142 + c * 64, 236 + r * 52
            d.rounded_rectangle([x0, y0, x0 + 38, y0 + 32], 6, fill=0)
    return im


def g_clock():
    im, d = _canvas()
    d.ellipse([106, 106, 406, 406], fill=255)
    d.ellipse([134, 134, 378, 378], fill=0)
    d.line([(256, 256), (256, 168)], fill=255, width=24)
    d.line([(256, 256), (326, 296)], fill=255, width=24)
    d.ellipse([238, 238, 274, 274], fill=255)
    for k in range(12):
        a = k * math.pi / 6
        x, y = 256 + 104 * math.cos(a), 256 + 104 * math.sin(a)
        d.ellipse([x - 7, y - 7, x + 7, y + 7], fill=255)
    return im


def g_weather():
    im, d = _canvas()
    c = (210, 206)
    for k in range(8):
        a = k * math.pi / 4
        d.line([(c[0] + 78 * math.cos(a), c[1] + 78 * math.sin(a)),
                (c[0] + 112 * math.cos(a), c[1] + 112 * math.sin(a))], fill=255, width=20)
    d.ellipse([c[0] - 58, c[1] - 58, c[0] + 58, c[1] + 58], fill=255)
    # 雲は太陽の前に重ね、境目に隙間を作る
    cloud = Image.new("L", (S, S), 0)
    cd = ImageDraw.Draw(cloud)
    cd.ellipse([196, 236, 316, 356], fill=255)
    cd.ellipse([270, 200, 406, 336], fill=255)
    cd.ellipse([150, 280, 240, 370], fill=255)
    cd.rounded_rectangle([150, 300, 420, 372], 36, fill=255)
    gap = cloud.filter(ImageFilter.MaxFilter(17))
    im.paste(0, mask=gap)
    im.paste(255, mask=cloud)
    return im


GLYPHS = [
    ("電話", g_phone), ("メッセージ", g_message), ("カメラ", g_camera),
    ("ギャラリー", g_gallery), ("メール", g_mail), ("ブラウザ", g_browser),
    ("マップ", g_maps), ("ミュージック", g_music), ("設定", g_settings),
    ("カレンダー", g_calendar), ("時計", g_clock), ("天気", g_weather),
]


def mask_of(fn) -> np.ndarray:
    return np.asarray(fn().filter(ImageFilter.GaussianBlur(1.2)), float) / 255


def edge_of(m: np.ndarray, width: float) -> np.ndarray:
    b = blur(m, width)
    return np.clip(1 - np.abs(b - 0.5) * 2.2, 0, 1) ** 1.5


def flow_tangent(m: np.ndarray, sigma: float = 10):
    b = blur(m, sigma)
    gy, gx = np.gradient(b)
    mag = np.hypot(gx, gy)
    return np.arctan2(gx, -gy), mag  # 等高線に沿う向き


# ── 筆致・点の描画プリミティブ ───────────────────────────────────

def strokes(canvas: Image.Image, base: np.ndarray, angle: np.ndarray, rng, n: int,
            length=(12, 24), width=(5, 8), jitter=18, highlight=0.25, where=None):
    """base 色を拾いながら angle 方向に短い筆致を置く（インパスト風に縁取り）。"""
    d = ImageDraw.Draw(canvas)
    ys, xs = rng.integers(0, S, n), rng.integers(0, S, n)
    if where is not None:
        keep = where[ys, xs] > rng.random(n)
        ys, xs = ys[keep], xs[keep]
    for y, x in zip(ys, xs):
        a = angle[y, x] + rng.normal(0, 0.12)
        L = rng.uniform(*length) / 2
        w = int(rng.uniform(*width))
        dx, dy = L * math.cos(a), L * math.sin(a)
        col = base[y, x] + rng.normal(0, jitter, 3)
        dark = tuple(int(v) for v in np.clip(col * 0.72, 0, 255))
        main = tuple(int(v) for v in np.clip(col, 0, 255))
        lite = tuple(int(v) for v in np.clip(col + (255 - col) * highlight, 0, 255))
        p0, p1 = (x - dx, y - dy), (x + dx, y + dy)
        d.line([p0, p1], fill=dark, width=w + 2)
        d.line([p0, p1], fill=main, width=w)
        off = (-math.sin(a) * w * 0.25, math.cos(a) * w * 0.25)
        d.line([(p0[0] - off[0], p0[1] - off[1]), (p1[0] - off[0], p1[1] - off[1])],
               fill=lite, width=max(1, w // 3))


def finish(rgb: np.ndarray | Image.Image, shape=SHAPE) -> Image.Image:
    if isinstance(rgb, Image.Image):
        rgb = np.asarray(rgb.convert("RGB"), float)
    a = (shape * 255).astype(np.uint8)
    im = Image.fromarray(np.dstack([np.clip(rgb, 0, 255).astype(np.uint8), a]), "RGBA")
    return im.resize((OUT, OUT), Image.LANCZOS)


# ── 画家ごとのレンダラー ─────────────────────────────────────────

def van_gogh(m, rng):
    """星月夜: 渦巻く群青の空に、黄〜橙の厚塗りの図柄。輪郭を濃紺の筆で縁取る。"""
    x, y = grid()
    ang_t, mag = flow_tangent(m, 12)
    # 渦: 2〜3 個のうず中心から接線方向の流れ
    sw = np.zeros((S, S)) + fbm(rng, (128, 64)) * 2.5
    vx, vy = np.cos(sw), np.sin(sw)
    for _ in range(3):
        cx, cy = rng.uniform(60, 450, 2)
        r2 = (x - cx) ** 2 + (y - cy) ** 2 + 400
        k = 9000 / r2
        vx += -(y - cy) / np.sqrt(r2) * k
        vy += (x - cx) / np.sqrt(r2) * k
    ang_bg = np.arctan2(vy, vx)
    wgt = np.clip(mag * 60, 0, 1)
    ang = np.where(wgt > 0.15, ang_t, ang_bg)

    t = np.clip(y / S * 0.7 + fbm(rng) * 0.5 - 0.1, 0, 1)
    bg = ramp(["#7fb4d8", "#3a6fb5", "#1d3d82", "#14275c"], t)
    swirl = fbm(rng, (48, 24))
    bg = lerp(bg, hexrgb("#a9d3e0"), np.clip((swirl - 0.62) * 4, 0, 0.8))
    fg = ramp(["#fff1a8", "#f6cf3a", "#ec9a24", "#c9661b"], np.clip(y / S + fbm(rng) * 0.4 - 0.2, 0, 1))
    base = lerp(bg, fg, m)

    canvas = to_img(blur_rgb(base, 3))
    strokes(canvas, base, ang, rng, 5200)
    strokes(canvas, base, ang_t, rng, 2400, length=(10, 18), width=(4, 6), where=m)
    edge = edge_of(m, 2.5)
    navy = np.zeros_like(base) + hexrgb("#16264f")
    strokes(canvas, navy, ang_t, rng, 2600, length=(10, 18), width=(4, 6), jitter=10,
            highlight=0.15, where=edge)
    return finish(canvas)


SEURAT_PIGMENTS = [hexrgb(h) for h in (
    "#e94b2b", "#f39a2b", "#f7d548", "#9ccc54", "#3e9e5c",
    "#4fa3d9", "#2f5fae", "#7a4fa8", "#f2e7cf", "#d75b8c")]


def seurat(m, rng):
    """グランド・ジャット: 純色の小点を並置し、目の中で混色させる。図柄の周りには明るい暈。"""
    x, y = grid()
    halo = np.clip(blur(m, 14) - m, 0, 1)
    sky = ramp(["#bfe0ef", "#9fcfdc", "#8fc27e", "#5f9e5a"], np.clip(y / S + fbm(rng) * 0.3 - 0.1, 0, 1))
    sky = lerp(sky, hexrgb("#fbf1d2"), np.clip(halo * 2.4, 0, 0.8))
    fg = ramp(["#f4a23a", "#e2572f", "#a83a5e"], np.clip(y / S * 1.2 - 0.1 + fbm(rng) * 0.3, 0, 1))
    base = lerp(sky, fg, m)

    canvas = Image.new("RGB", (S, S), (240, 230, 205))
    d = ImageDraw.Draw(canvas)
    pig = np.array(SEURAT_PIGMENTS)
    n = 26000
    ys, xs = rng.integers(0, S, n), rng.integers(0, S, n)
    for yy, xx in zip(ys, xs):
        target = base[yy, xx]
        # 最も近い 3 色の顔料から 1 つ（近いほど選ばれやすく）+ 目標色そのもの
        dist = np.linalg.norm(pig - target, axis=1)
        idx = np.argsort(dist)[:3]
        if rng.random() < 0.45:
            col = target + rng.normal(0, 14, 3)
        else:
            col = pig[idx[min(int(rng.exponential(0.8)), 2)]]
        r = rng.uniform(3.0, 4.6)
        d.ellipse([xx - r, yy - r, xx + r, yy + r], fill=tuple(int(v) for v in np.clip(col, 0, 255)))
    return finish(canvas)


def rubens(m, rng):
    """バロック: 暗い琥珀の地に斜め上からの強い光。図柄は深紅の布のような量感と金の照り返し。"""
    x, y = grid()
    h = blur(m, 9) * 0.6 + blur(m, 22) * 0.4
    gy, gx = np.gradient(h * 40)
    nz = np.ones_like(gx)
    nl = np.sqrt(gx ** 2 + gy ** 2 + nz ** 2)
    L = np.array([-0.55, -0.65, 0.52])
    L = L / np.linalg.norm(L)
    diff = np.clip((-gx * L[0] - gy * L[1] + nz * L[2]) / nl, 0, 1)
    spec = diff ** 24

    glow = np.exp(-(((x - 150) ** 2 + (y - 120) ** 2) / (2 * 190 ** 2)))
    cloud = fbm(rng, (96, 48, 24))
    bg = ramp(["#140a05", "#3b2210", "#7a4a1e", "#c48a3e"], np.clip(glow * 0.9 + (cloud - 0.5) * 0.5, 0, 1))
    shade = np.clip((diff - 0.6) * 2.4 + 0.42 + (fbm(rng, (24, 12)) - 0.5) * 0.25, 0, 1)
    fg = ramp(["#3a0a0c", "#8c1a1a", "#c43a2a", "#e7a27a", "#f7dcb4"], np.clip(shade * 1.05, 0, 1))
    fg = fg + spec[..., None] * 90
    base = lerp(bg, fg, m)
    # 地の上に図柄が落とす柔らかな影
    sh = np.roll(np.roll(blur(m, 10), 14, 0), 10, 1)
    base = base * (1 - (sh * (1 - m) * 0.55))[..., None]
    # 筆の流れ（斜めのモーションブラー状のテクスチャ）
    tex = fbm(rng, (8, 4))
    base = base * (0.92 + tex * 0.16)[..., None]
    canvas = to_img(base)
    ang = np.full((S, S), -0.7) + (fbm(rng, (64,)) - 0.5)
    strokes(canvas, blur_rgb(base, 2), ang, rng, 1500, length=(18, 34), width=(6, 10),
            jitter=8, highlight=0.08)
    out = np.asarray(canvas, float) * 0.55 + base * 0.45
    vign = 1 - 0.45 * (((x - 256) ** 2 + (y - 256) ** 2) / (2 * 256 ** 2))
    out *= vign[..., None]
    # ワニスの黄ばみ
    out = out * np.array([1.04, 0.98, 0.86])
    return finish(out)


def _damask(rng):
    """ゲントの祭壇画の背後にあるような錦織り文様。"""
    im = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(im)
    step = 64
    for j in range(-1, S // step + 2):
        for i in range(-1, S // step + 2):
            cx = i * step + (step // 2 if j % 2 else 0)
            cy = j * step
            d.ellipse([cx - 9, cy - 16, cx + 9, cy + 16], outline=255, width=2)
            d.ellipse([cx - 3, cy - 3, cx + 3, cy + 3], fill=255)
            for s in (-1, 1):
                d.arc([cx + s * 6 - 16, cy - 6, cx + s * 6 + 16, cy + 26], 200 if s > 0 else 280,
                      260 if s > 0 else 340, fill=255, width=2)
            d.line([(cx, cy + 16), (cx, cy + 30)], fill=255, width=2)
    return np.asarray(im, float) / 255


def _craquelure(rng):
    im = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(im)
    for _ in range(140):
        x, y = rng.uniform(0, S, 2)
        a = rng.uniform(0, math.tau)
        pts = [(x, y)]
        for _ in range(rng.integers(3, 7)):
            a += rng.normal(0, 0.7)
            x += 14 * math.cos(a)
            y += 14 * math.sin(a)
            pts.append((x, y))
        d.line(pts, fill=255, width=1)
    return np.asarray(im, float) / 255


def van_eyck(m, rng):
    """初期フランドル: 深緑の錦織り、ウルトラマリンと金の宝飾のような図柄、細密な光、古いパネルの亀裂。"""
    x, y = grid()
    dam = blur(_damask(rng), 0.8)
    cloth = ramp(["#0c2a1f", "#174a35", "#0f3326"], np.clip(y / S + (fbm(rng) - 0.5) * 0.4, 0, 1))
    gold = hexrgb("#c9a24a")
    bg = lerp(cloth, gold, dam * 0.7)
    # 図柄: 精密な面取り。ウルトラマリンの釉薬、縁は金
    h = blur(m, 3.5)
    gy, gx = np.gradient(h * 30)
    nl = np.sqrt(gx ** 2 + gy ** 2 + 1)
    L = np.array([-0.5, -0.6, 0.62])
    L /= np.linalg.norm(L)
    diff = np.clip((-gx * L[0] - gy * L[1] + L[2]) / nl, 0, 1)
    spec = np.clip((diff - 0.93) * 30, 0, 1)
    inner = np.clip(blur(m, 6) * 2 - 1, 0, 1)  # 図柄の内側ほど 1
    enamel = ramp(["#0b1c5a", "#1f3fa8", "#4c74d6"], np.clip(diff * 0.9 + (fbm(rng, (32, 16)) - 0.5) * 0.3, 0, 1))
    rim = ramp(["#6e4a12", "#c9a24a", "#fff0b8"], diff)
    fg = lerp(rim, enamel, np.clip(inner * 1.6, 0, 1))
    fg = fg + spec[..., None] * 200
    # 真珠のような小さな光点を縁に並べる
    pearls = np.zeros((S, S))
    e = edge_of(m, 1.5)
    pts = np.argwhere(e > 0.8)
    if len(pts):
        sel = pts[rng.choice(len(pts), min(40, len(pts)), replace=False)]
        pim = Image.new("L", (S, S), 0)
        pd = ImageDraw.Draw(pim)
        for yy, xx in sel:
            pd.ellipse([xx - 4, yy - 4, xx + 4, yy + 4], fill=255)
        pearls = blur(np.asarray(pim, float) / 255, 0.9)
    base = lerp(bg, fg, m)
    sh = np.roll(np.roll(blur(m, 5), 8, 0), 6, 1)
    base = base * (1 - (sh * (1 - m) * 0.6))[..., None]
    base = lerp(base, hexrgb("#f6f1e4"), pearls * 0.9)
    # 金の額縁
    fr = np.clip((SHAPE < 0.999).astype(float) + 0, 0, 1)
    ring = blur(squircle_mask(S, 4.5, 0.02) - squircle_mask(S, 4.5, 0.075), 1.0)
    frame = ramp(["#5a3a0c", "#d6b25a", "#fff1bf", "#a87d2a"], np.clip((x + y) / (2 * S) + (fbm(rng, (16, 8)) - 0.5) * 0.5, 0, 1))
    base = lerp(base, frame, np.clip(ring * 1.4, 0, 1))
    del fr
    crack = blur(_craquelure(rng), 0.5)
    base = base * (1 - crack * 0.35)[..., None]
    base = base * np.array([1.02, 1.0, 0.92])  # 油の黄変
    return finish(base)


def _clouds(rng, density=0.55):
    c = fbm(rng, (96, 48, 24, 12))
    return np.clip((c - density) * 5, 0, 1)


def magritte(m, rng):
    """大家族（La Grande Famille）: 嵐の暮れ空と海の前に、昼の青空と白い雲で満たされた図柄。"""
    x, y = grid()
    horizon = 360
    sky = ramp(["#2b3440", "#46525f", "#6c7781"], np.clip(y / horizon, 0, 1))
    storm = fbm(rng, (128, 64, 32))
    sky = lerp(sky, hexrgb("#1c232c"), np.clip((storm - 0.5) * 2, 0, 0.8))
    sea = ramp(["#3c4e5a", "#1f2c35"], np.clip((y - horizon) / (S - horizon), 0, 1))
    wave = (np.sin(x / 9 + np.sin(y / 5) * 2) * 0.5 + 0.5) * np.clip((y - horizon) / 100, 0, 1)
    sea = sea * (0.9 + 0.12 * wave)[..., None]
    bg = np.where((y < horizon)[..., None], sky, sea)
    foam = np.clip(1 - np.abs(y - horizon - 3) / 3, 0, 1)
    bg = lerp(bg, hexrgb("#c8d0d4"), foam * 0.5)
    # 図柄の中は昼の空
    day = ramp(["#2f77c9", "#5fa0e0", "#a9d0f2"], np.clip(y / S * 1.1, 0, 1))
    cl = _clouds(rng, 0.52)
    cl_shade = blur(cl, 6)
    clouds = ramp(["#c9d4e0", "#ffffff"], np.clip(cl_shade * 1.2 - np.roll(cl_shade, -6, 0) * 0.4 + 0.3, 0, 1))
    inside = lerp(day, clouds, cl)
    # 縁にごく細い光のリム
    rim = edge_of(m, 1.2) * m
    base = lerp(bg, inside, m)
    base = lerp(base, hexrgb("#f4f1e6"), rim * 0.35)
    return finish(base)


CUBIST = [hexrgb(h) for h in (
    "#b9894a", "#8a5a2b", "#d8c08a", "#6f6a5e", "#a9a493", "#4d5b5a",
    "#c4643a", "#e2d6b4", "#3a3631", "#7d8c7c")]


def picasso(m, rng):
    """キュビスム: 画面を斜めの面に割り、各面を別の向きに陰影。図柄は二つの視点からずらして重ねる。"""
    x, y = grid()
    region = np.zeros((S, S), dtype=np.int64)
    lines = []
    for k in range(7):
        a = rng.uniform(0, math.pi)
        px, py = rng.uniform(100, 412, 2)
        nx, ny = math.cos(a), math.sin(a)
        side = ((x - px) * nx + (y - py) * ny) > 0
        region = region * 2 + side
        lines.append((px, py, nx, ny))
    # ずらした第二視点のグリフ
    m2 = np.roll(np.roll(m, -22, 1), 14, 0)
    m2 = np.asarray(Image.fromarray((m2 * 255).astype(np.uint8)).rotate(8, center=(256, 256)), float) / 255
    out = np.zeros((S, S, 3))
    for rid in np.unique(region):
        sel = region == rid
        r2 = np.random.default_rng(int(rid) * 7919 + int(rng.integers(1 << 30)))
        c_bg = lerp(CUBIST[r2.integers(len(CUBIST))], hexrgb("#8f8676"), 0.35)
        c_fg = [hexrgb("#d0552a"), hexrgb("#f0e2b8"), hexrgb("#24456e"), hexrgb("#e3a92f")][r2.integers(4)]
        a = r2.uniform(0, math.tau)
        g = ((x * math.cos(a) + y * math.sin(a)) / S) % 1.0
        shade = 0.78 + 0.3 * g
        blk = lerp(c_bg, c_fg, np.clip(m + m2 * 0.25, 0, 1))
        out[sel] = (blk * shade[..., None])[sel]
    # 面の境界の細い線と、図柄の太い黒い輪郭
    edges = np.zeros((S, S))
    gx = np.abs(np.diff(region, axis=1, prepend=region[:, :1])) > 0
    gy = np.abs(np.diff(region, axis=0, prepend=region[:1, :])) > 0
    edges = blur((gx | gy).astype(float), 0.8)
    out = out * (1 - np.clip(edges * 1.4, 0, 0.55) * (1 - m * 0.6))[..., None]
    out = out * (1 - edge_of(m, 4.0) * 0.95)[..., None]
    out = out * (1 - edge_of(m2, 1.8) * 0.6)[..., None]
    tex = fbm(rng, (6, 3))
    out = out * (0.9 + tex * 0.2)[..., None]
    return finish(out)


PAINTERS = [
    ("van_gogh", "フィンセント・ファン・ゴッホ", "Vincent van Gogh", "うねる厚塗りの筆致・群青と黄", van_gogh),
    ("seurat", "ジョルジュ・スーラ", "Georges Seurat", "純色の点描・視覚混色", seurat),
    ("rubens", "ピーテル・パウル・ルーベンス", "Peter Paul Rubens", "バロックの明暗・深紅と金", rubens),
    ("van_eyck", "ヤン・ファン・エイク", "Jan van Eyck", "細密な宝飾・錦織り・金の額", van_eyck),
    ("magritte", "ルネ・マグリット", "René Magritte", "嵐の空に昼の青空を切り抜く", magritte),
    ("picasso", "パブロ・ピカソ", "Pablo Picasso", "キュビスムの面分割と多視点", picasso),
]


# ── 未対応アプリのフォールバック（iconback + 元アイコン + iconupon） ──

def foreign_icon() -> Image.Image:
    """パックに専用アイコンがない架空のサードパーティアプリ。"""
    im = Image.new("RGBA", (S, S), (0, 0, 0, 0))
    d = ImageDraw.Draw(im)
    d.rounded_rectangle([40, 40, 472, 472], 110, fill=(52, 199, 140, 255))
    d.polygon([(256, 120), (390, 256), (256, 392), (122, 256)], fill=(255, 255, 255, 255))
    d.ellipse([206, 206, 306, 306], fill=(52, 199, 140, 255))
    return im


def fallback(painter_fn, rng) -> Image.Image:
    back = painter_fn(np.zeros((S, S)), rng).resize((S, S), Image.LANCZOS)
    icon = foreign_icon().resize((300, 300), Image.LANCZOS)
    out = back.copy()
    out.alpha_composite(icon, (106, 106))
    # iconupon: 同じ画家の質感を薄く重ねる
    tex = painter_fn(np.zeros((S, S)), np.random.default_rng(99)).resize((S, S), Image.LANCZOS)
    tex.putalpha(Image.fromarray((np.asarray(tex.split()[3], float) * 0.28).astype(np.uint8)))
    out.alpha_composite(tex)
    return out.resize((OUT, OUT), Image.LANCZOS)


# ── シート組版 ───────────────────────────────────────────────────

def font(sz):
    return ImageFont.truetype(FONT, sz)


def painter_sheet(key, jp, en, desc, fn, masks):
    cols, cell, pad = 4, 220, 36
    rows = 4  # 12 アイコン + フォールバック
    W = pad * 2 + cols * cell
    H = 150 + rows * (cell + 34) + pad
    sheet = Image.new("RGB", (W, H), (246, 243, 236))
    d = ImageDraw.Draw(sheet)
    d.text((pad, 30), jp, font=font(40), fill=(30, 30, 30))
    d.text((pad, 82), f"{en} — {desc}", font=font(22), fill=(100, 96, 90))
    for i, (label, m) in enumerate(masks):
        icon = fn(m, np.random.default_rng(1000 + i))
        cx = pad + (i % cols) * cell + (cell - OUT) // 2
        cy = 140 + (i // cols) * (cell + 34)
        sh = Image.new("RGBA", (OUT, OUT), (0, 0, 0, 0))
        sh.putalpha(icon.split()[3].filter(ImageFilter.GaussianBlur(6)).point(lambda v: v * 0.35))
        sheet.paste((0, 0, 0), (cx + 3, cy + 6), sh)
        sheet.paste(icon, (cx, cy), icon)
        tw = d.textlength(label, font=font(20))
        d.text((cx + (OUT - tw) / 2, cy + OUT + 6), label, font=font(20), fill=(60, 60, 60))
    i = len(masks)
    cx = pad + (i % cols) * cell + (cell - OUT) // 2
    cy = 140 + (i // cols) * (cell + 34)
    fb = fallback(fn, np.random.default_rng(7))
    sheet.paste(fb, (cx, cy), fb)
    lab = "未対応アプリ（自動）"
    tw = d.textlength(lab, font=font(18))
    d.text((cx + (OUT - tw) / 2, cy + OUT + 6), lab, font=font(18), fill=(150, 60, 40))
    d.text((pad + cell * 1 + 10, cy + 40),
           "← パックに専用アイコンがないアプリは、\n   画家風の下地（iconback）に元アイコンを縮小して載せ、\n   同じ質感（iconupon）を薄く重ねて馴染ませる。\n   Nova / Lawnchair など多くのランチャーが対応。",
           font=font(19), fill=(90, 86, 80), spacing=8)
    sheet.save(SAMPLES / f"{key}.png", optimize=True)
    return sheet


def overview(rendered):
    cell, pad, lab_w = 112, 24, 300
    n = len(GLYPHS)
    W = pad * 2 + lab_w + n * cell
    H = 120 + len(rendered) * (cell + 18) + pad
    sheet = Image.new("RGB", (W, H), (246, 243, 236))
    d = ImageDraw.Draw(sheet)
    d.text((pad, 24), "画家スタイル・アイコンパック 叩き台", font=font(36), fill=(30, 30, 30))
    for j, (label, _) in enumerate(GLYPHS):
        tw = d.textlength(label, font=font(15))
        d.text((pad + lab_w + j * cell + (cell - tw) / 2, 84), label, font=font(15), fill=(90, 90, 90))
    for r, (jp, en, icons) in enumerate(rendered):
        y0 = 112 + r * (cell + 18)
        d.text((pad, y0 + 26), jp, font=font(21), fill=(30, 30, 30))
        d.text((pad, y0 + 56), en, font=font(15), fill=(110, 106, 100))
        for j, ic in enumerate(icons):
            small = ic.resize((cell - 12, cell - 12), Image.LANCZOS)
            sheet.paste(small, (pad + lab_w + j * cell + 6, y0 + 6), small)
    sheet.save(SAMPLES / "overview.png", optimize=True)


def main():
    SAMPLES.mkdir(parents=True, exist_ok=True)
    masks = [(label, mask_of(fn)) for label, fn in GLYPHS]
    rendered = []
    for key, jp, en, desc, fn in PAINTERS:
        print(f"[RENDER] {key}")
        painter_sheet(key, jp, en, desc, fn, masks)
        icons = [fn(m, np.random.default_rng(1000 + i)) for i, (_, m) in enumerate(masks)]
        rendered.append((jp, en, icons))
    overview(rendered)
    print(f"[DONE] {SAMPLES}")


if __name__ == "__main__":
    main()
