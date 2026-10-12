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


# ── 共通の描画部品 ───────────────────────────────────────────────

def scale_mask(m: np.ndarray, f: float) -> np.ndarray:
    """図柄を中心基準で f 倍にする（鏡の中などに収めるため）。"""
    n = int(S * f)
    small = np.asarray(Image.fromarray((m * 255).astype(np.uint8)).resize((n, n), Image.LANCZOS), float) / 255
    out = np.zeros((S, S))
    o = (S - n) // 2
    out[o:o + n, o:o + n] = small
    return out


def lit(m: np.ndarray, sigma: float, depth: float, light=(-0.55, -0.65, 0.52), extra=None):
    """マスクを高さとみなして斜光の拡散光を返す（0..1）。"""
    h = blur(m, sigma)
    if extra is not None:
        h = h + extra
    gy, gx = np.gradient(h * depth)
    L = np.array(light, float)
    L /= np.linalg.norm(L)
    return np.clip((-gx * L[0] - gy * L[1] + L[2]) / np.sqrt(gx ** 2 + gy ** 2 + 1), 0, 1)


def drop_shadow(base, m, dx, dy, sigma, k):
    sh = np.roll(np.roll(blur(m, sigma), dy, 0), dx, 1)
    return base * (1 - sh * (1 - m) * k)[..., None]


def poly_mask(points, blur_px=1.0) -> np.ndarray:
    im = Image.new("L", (S, S), 0)
    ImageDraw.Draw(im).polygon(points, fill=255)
    return np.asarray(im.filter(ImageFilter.GaussianBlur(blur_px)), float) / 255


def vortex(x, y, cx, cy, R, sign=1.0):
    """渦の接線ベクトルと影響の重み。"""
    dx, dy = x - cx, y - cy
    r = np.sqrt(dx ** 2 + dy ** 2) + 1e-6
    w = np.exp(-(r / R) ** 2)
    return -dy / r * sign * w, dx / r * sign * w, r, w


# ── 1. ゴッホ『星月夜』(1889) ─────────────────────────────────────

STARS = [(62, 96, 15), (168, 52, 12), (330, 44, 11), (420, 182, 13), (96, 250, 10)]
MOON = (440, 74, 28)


def starry_night(m, rng):
    """群青の夜空を横切る大きな渦、同心円の光輪をまとった星と三日月、
    左端に燃え上がる糸杉、下に眠る村。図柄は星と同じ黄で塗り、外側に光輪を巡らせる。"""
    x, y = grid()
    wav = fbm(rng, (96, 48))
    a0 = 0.25 * np.sin(x / 55 + wav * 7)
    vx, vy = np.cos(a0) * 0.5, np.sin(a0) * 0.5

    sky = ramp(["#13306a", "#20488c", "#2f63a6", "#203f78"], np.clip(y / S * 1.1 + (wav - 0.5) * 0.35, 0, 1))
    base = sky.copy()
    # 空を横切る二重の渦（絵の中央の「大きな波」）
    for cx, cy, R, sg in ((250, 215, 135, 1.0), (118, 150, 70, -1.0)):
        tx, ty, r, w = vortex(x, y, cx, cy, R, sg)
        vx, vy = vx + tx * 2.2, vy + ty * 2.2
        th = np.arctan2(y - cy, x - cx)
        band = 0.5 + 0.5 * np.sin(th * sg + r / 13)
        base = lerp(base, ramp(["#7fb0d4", "#d6e5c8"], band), np.clip(w * band * 1.1, 0, 0.85))
    # 星と月: 黄の芯 + 白と水色が交互に巡る光輪
    for cx, cy, R in STARS + [MOON]:
        d = np.hypot(x - cx, y - cy)
        tx, ty, _, _ = vortex(x, y, cx, cy, R * 2.8)
        near = d < R * 3.0
        vx = np.where(near, tx * 5, vx)
        vy = np.where(near, ty * 5, vy)
        ring = np.sin(d / 3.6) > 0
        halo = np.clip(1 - (d - R * 0.7) / (R * 2.2), 0, 1) * (d > R * 0.7)
        base = lerp(base, np.where(ring[..., None], hexrgb("#f5efb4"), hexrgb("#86b8d6")), halo * 0.9)
        core = np.clip((R * 0.75 - d) / 2, 0, 1)
        base = lerp(base, ramp(["#fff6c4", "#f3c93a"], d / (R * 0.75)), core)
    mx, my, mr = MOON
    cut = np.clip((mr * 0.62 - np.hypot(x - mx - 9, y - my + 6)) / 2, 0, 1)
    base = lerp(base, hexrgb("#e8a32a"), cut * 0.85)

    # 下の村と丘
    hill = y > 432 + 14 * np.sin(x / 70) + 8 * np.sin(x / 23)
    base = np.where(hill[..., None], ramp(["#26446e", "#142a4a"], (y - 430) / 80), base)
    vx = np.where(hill, 1.0, vx)
    vy = np.where(hill, 0.15 * np.sin(x / 30), vy)
    win = Image.new("L", (S, S), 0)
    wd = ImageDraw.Draw(win)
    for _ in range(14):
        wx, wy = rng.uniform(130, 500), rng.uniform(452, 490)
        wd.rectangle([wx, wy, wx + 5, wy + 4], fill=255)
    win_m = np.asarray(win, float) / 255

    # 左端の糸杉（揺らめく炎の形）
    pts_l, pts_r = [], []
    for k in range(13):
        yy = 512 - k * 38
        half = 52 * (1 - k / 13) ** 0.8 + 4
        sway = 12 * math.sin(k * 0.9)
        pts_l.append((48 + sway - half, yy))
        pts_r.append((48 + sway + half * 0.9, yy))
    cyp = poly_mask(pts_l + pts_r[::-1] + [], 2) * (1 - m)
    base = lerp(base, ramp(["#1a2416", "#33401f", "#4d4a26"], fbm(rng, (32, 16))), cyp)
    vx = np.where(cyp > 0.5, 0.15 * np.sin(y / 18), vx)
    vy = np.where(cyp > 0.5, -1.0, vy)

    # 図柄: 星と同じ黄、外側には等高線に沿った光輪
    ang_t, _ = flow_tangent(m, 12)
    outer = np.clip(blur(m, 16) * 1.6, 0, 1) * (1 - m)
    rings = np.sin(blur(m, 16) * 46) > 0
    base = lerp(base, np.where(rings[..., None], hexrgb("#f2eab0"), hexrgb("#7eb0d2")), np.clip(outer * 1.4, 0, 0.9))
    fg = ramp(["#fff4b8", "#f5d240", "#eaa526", "#d27a1c"], np.clip(y / S + (fbm(rng) - 0.5) * 0.5, 0, 1))
    base = lerp(base, fg, m)
    ang = np.arctan2(vy, vx)
    ang = np.where((m > 0.05) | (outer > 0.12), ang_t, ang)

    canvas = to_img(blur_rgb(base, 3))
    strokes(canvas, base, ang, rng, 7000, length=(12, 24), width=(5, 8), jitter=14)
    strokes(canvas, base, ang_t, rng, 2600, length=(10, 18), width=(4, 6), jitter=12, where=m)
    navy = np.zeros_like(base) + hexrgb("#13234a")
    strokes(canvas, navy, ang_t, rng, 2400, length=(10, 16), width=(3, 5), jitter=8,
            highlight=0.12, where=edge_of(m, 2.5))
    out = np.asarray(canvas, float)
    out = lerp(out, hexrgb("#ffd24a"), blur(win_m, 0.6) * (1 - cyp))
    return finish(out)


# ── 2. スーラ『グランド・ジャット島の日曜日の午後』(1884–86) ─────────

SEURAT_PIGMENTS = np.array([hexrgb(h) for h in (
    "#e9502e", "#f39b33", "#f5d64e", "#b9cf54", "#5fa451", "#2f7556",
    "#5f9fd6", "#3b62b0", "#7b55a6", "#f3ead0", "#d9658e", "#2a2236")])


def grande_jatte(m, rng):
    """奥にセーヌ川と帆、日なたの黄緑の芝、手前を横切る青緑の木陰、右に木の幹。
    図柄は画中の散策する人々のように暗い菫色のシルエット、光の当たる縁だけ橙。"""
    x, y = grid()
    wav = fbm(rng, (64, 32))
    shore = 104 + 8 * np.sin(x / 60)
    water = ramp(["#6f9fd2", "#4f7fbf"], y / 110)
    water = lerp(water, hexrgb("#b9a7d6"), np.clip(np.sin(y / 4 + x / 40) * 0.5, 0, 0.4))
    bank = hexrgb("#3e6b3a")
    grass = ramp(["#a9b85a", "#86a64a", "#6e9444"], np.clip((y - 120) / 280 + (wav - 0.5) * 0.6, 0, 1))
    grass = lerp(grass, hexrgb("#d9c46a"), np.clip((fbm(rng, (48, 24)) - 0.58) * 4, 0, 0.6))
    shadow_line = 404 + 22 * np.sin(x / 90) - np.clip(150 - x, 0, 150) * 0.9
    shade = ramp(["#2e6a50", "#25523f"], np.clip((y - 380) / 130, 0, 1))
    shade = lerp(shade, hexrgb("#5d4c8a"), np.clip((fbm(rng, (32,)) - 0.6) * 3, 0, 0.5))
    base = np.where((y < shore)[..., None], water, grass)
    base = np.where(((y >= shore) & (y < shore + 14))[..., None], bank, base)
    base = np.where((y > shadow_line)[..., None], shade, base)
    # 右の木の幹と葉影
    trunk = (np.abs(x - 462 - 6 * np.sin(y / 50)) < 13) & (y < 430)
    base = np.where(trunk[..., None], hexrgb("#4a3a4c"), base)
    foliage = np.clip((fbm(rng, (40, 20)) - 0.45) * 4, 0, 1) * (y < 95) * (x > 330)
    base = lerp(base, hexrgb("#2c4f2e"), foliage)
    # 川の白い帆
    sail = poly_mask([(118, 30), (118, 84), (88, 84)], 1)
    base = lerp(base, hexrgb("#f7f1de"), sail)

    halo = np.clip(blur(m, 14) - m, 0, 1)
    base = lerp(base, hexrgb("#f5eccb"), np.clip(halo * 2.0, 0, 0.6))
    rim = lit(m, 4, 40) ** 6 * m
    fg = ramp(["#2a2238", "#3a2c4a"], np.clip(y / S, 0, 1))
    fg = lerp(fg, hexrgb("#e5733a"), np.clip(rim * 2.2, 0, 0.85))
    base = lerp(base, fg, m)

    canvas = Image.new("RGB", (S, S), (236, 226, 200))
    canvas.paste(to_img(lerp(blur_rgb(base, 4), hexrgb("#f1e6c8"), 0.35)))
    d = ImageDraw.Draw(canvas)
    n = 34000
    ys, xs = rng.integers(0, S, n), rng.integers(0, S, n)
    tgt = base[ys, xs]
    dist = np.linalg.norm(SEURAT_PIGMENTS[None, :, :] - tgt[:, None, :], axis=2)
    order = np.argsort(dist, axis=1)
    roll = rng.random(n)
    pick = np.where(roll < 0.62, 0, np.where(roll < 0.88, 1, 2))
    cols = SEURAT_PIGMENTS[order[np.arange(n), pick]]
    cols = np.where((rng.random(n) < 0.4)[:, None], tgt + rng.normal(0, 10, (n, 3)), cols)
    rads = rng.uniform(3.2, 4.4, n)
    for yy, xx, c, r in zip(ys, xs, cols, rads):
        d.ellipse([xx - r, yy - r, xx + r, yy + r], fill=tuple(int(v) for v in np.clip(c, 0, 255)))
    return finish(canvas)


# ── 3. ルーベンス『キリスト降架』(1612–14, アントワープ大聖堂) ─────────

def descent_from_cross(m, rng):
    """嵐の暗い空、画面を対角に貫く光。左下には聖ヨハネの真紅の衣が斜めに走り、
    図柄はキリストを包む白い亜麻布として、冷たい影と温かい光の襞で描く。"""
    x, y = grid()
    storm = fbm(rng, (128, 64, 32))
    glow = np.exp(-(((x - 300) ** 2 + (y - 190) ** 2) / (2 * 170 ** 2)))
    bg = ramp(["#0b0805", "#1d140c", "#3b2c1a", "#6e5631"], np.clip(storm * 0.6 + glow * 0.55 - 0.1, 0, 1))
    # 十字架の横木と梯子
    beam = (np.abs(y - 70 - (x - 256) * 0.12) < 13)
    bg = np.where(beam[..., None], ramp(["#2a1a0e", "#4a3018"], (y % 26) / 26), bg)
    post = np.abs(x - 256) < 15
    bg = np.where((post & (y < 140))[..., None], hexrgb("#3a2614"), bg)
    for lx in (420, 466):
        rail = np.abs(x - lx - (y - 512) * 0.18) < 4
        bg = np.where(rail[..., None], hexrgb("#2b1c10"), bg)
    # 左下の真紅の衣（対角線）
    robe = np.clip(((256 - x) + (y - 256) - 150) / 6, 0, 1)
    folds = np.sin((x + y) / 22 + blur(storm, 6) * 16 + np.sin((x - y) / 40) * 2)
    red = ramp(["#3a0406", "#8e1016", "#c8261f", "#e9604a"], np.clip(0.45 + folds * 0.35 + (y - 300) / 900, 0, 1))
    bg = lerp(bg, red, robe)
    # 図柄: 白い亜麻布。斜めの襞を高さに足して陰影に表す
    creases = np.sin((x * 0.75 - y * 0.66) / 10 + fbm(rng, (64, 32)) * 5) * 0.05 * blur(m, 6)
    diff = lit(m, 8, 40, extra=creases)
    linen = ramp(["#4b4c58", "#8c8d96", "#cfcbc0", "#f3ecdc", "#fffbef"],
                 np.clip((diff - 0.55) * 2.6 + 0.45, 0, 1))
    base = lerp(bg, linen, m)
    base = drop_shadow(base, m, 10, 14, 10, 0.6)
    tex = fbm(rng, (8, 4))
    base = base * (0.93 + tex * 0.14)[..., None]
    canvas = to_img(base)
    strokes(canvas, blur_rgb(base, 2), np.full((S, S), -0.75) + (fbm(rng, (64,)) - 0.5),
            rng, 1600, length=(18, 32), width=(5, 9), jitter=6, highlight=0.06,
            where=np.clip(1 - m * 0.7, 0, 1))
    out = np.asarray(canvas, float) * 0.5 + base * 0.5
    vign = 1 - 0.5 * (((x - 280) ** 2 + (y - 230) ** 2) / (2 * 256 ** 2))
    out = out * vign[..., None] * np.array([1.05, 0.99, 0.86])
    return finish(out)


# ── 4. ファン・エイク『アルノルフィーニ夫妻像』(1434) ───────────────

def _craquelure(rng):
    im = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(im)
    for _ in range(150):
        x, y = rng.uniform(0, S, 2)
        a = rng.uniform(0, math.tau)
        pts = [(x, y)]
        for _ in range(rng.integers(3, 7)):
            a += rng.normal(0, 0.7)
            x += 13 * math.cos(a)
            y += 13 * math.sin(a)
            pts.append((x, y))
        d.line(pts, fill=255, width=1)
    return np.asarray(im, float) / 255


def arnolfini(m, rng):
    """左に夫人の深緑のドレス、右に寝台の緋色の帳。中央に十の円形装飾を巡らせた凸面鏡、
    図柄は鏡に映るシャンデリアと同じ真鍮として鏡の中に収める。"""
    x, y = grid()
    wav = fbm(rng, (64, 32))
    dress = ramp(["#05211a", "#0d4730", "#2a7848", "#7fb06a"],
                 np.clip(0.4 + np.sin(x / 13 + wav * 4) * 0.35 + (y - 256) / 1200, 0, 1))
    bed = ramp(["#2c0305", "#6e0b10", "#a81d24", "#d75246"],
               np.clip(0.45 + np.sin(x / 11 + wav * 3) * 0.35, 0, 1))
    side = np.clip((x - 256) / 30 + 0.5 + (wav - 0.5) * 2, 0, 1)
    base = lerp(dress, bed, side)
    # 凸面鏡
    cx = cy = 256
    r = np.hypot(x - cx, y - cy)
    R_in, R_out = 172, 214
    glass_t = r / R_in
    glass = ramp(["#7c776a", "#5c5a52", "#38383a", "#1f2024"], np.clip(glass_t ** 1.5, 0, 1))
    win = np.exp(-(((x - 196) ** 2) / (2 * 34 ** 2) + ((y - 180) ** 2) / (2 * 60 ** 2)))
    glass = lerp(glass, hexrgb("#d9e0dc"), win * 0.55)
    glass = lerp(glass, hexrgb("#b3341f"), np.clip((x - 320) / 40, 0, 1) * np.clip((r - 120) / 50, 0, 1) * 0.45)
    frame_t = (r - R_in) / (R_out - R_in)
    frame = ramp(["#c9a24a", "#3a230e", "#5a3a18", "#2a170a"], np.clip(frame_t, 0, 1))
    base = np.where((r < R_in)[..., None], glass, base)
    base = lerp(base, frame, np.clip((R_out - r) / 1.5, 0, 1) * np.clip((r - R_in) / 1.5, 0, 1))
    gold_line = np.clip(1 - np.abs(r - R_in) / 2.2, 0, 1) + np.clip(1 - np.abs(r - R_out) / 2.0, 0, 1)
    base = lerp(base, hexrgb("#e6c66a"), np.clip(gold_line, 0, 1))
    # 十の円形装飾（受難の場面）
    for k in range(10):
        a = -math.pi / 2 + k * math.tau / 10
        mx, my = cx + 193 * math.cos(a), cy + 193 * math.sin(a)
        d = np.hypot(x - mx, y - my)
        medal = ramp(["#f2e8c8", "#3f66b5", "#2a3f80"], np.clip(d / 13, 0, 1))
        medal = lerp(medal, hexrgb("#b3302a"), ((np.sin((x - mx) / 2.5) > 0.6) & (d < 7)).astype(float) * 0.7)
        base = lerp(base, medal, np.clip((14 - d) / 1.5, 0, 1))
        base = lerp(base, hexrgb("#e3c065"), np.clip(1 - np.abs(d - 14.5) / 1.6, 0, 1))
    # 図柄: 真鍮。精密な面取りと鋭い光点
    gm = scale_mask(m, 0.76)
    diff = lit(gm, 2.8, 34)
    brass = ramp(["#3a2406", "#7e5a16", "#c9a24a", "#f5dc8c", "#fff7d8"], np.clip((diff - 0.5) * 2.2 + 0.4, 0, 1))
    brass = brass + np.clip((diff - 0.95) * 25, 0, 1)[..., None] * 120
    inside = np.clip((R_in - r) / 3, 0, 1)
    base = drop_shadow(base, gm * inside, 5, 7, 4, 0.55)
    base = lerp(base, brass, gm)
    # 鏡のふくらみの反射、亀裂、油の黄変
    spec = np.exp(-(((x - 190) ** 2 + (y - 160) ** 2) / (2 * 26 ** 2))) * inside
    base = lerp(base, hexrgb("#ffffff"), spec * 0.35)
    base = base * (1 - blur(_craquelure(rng), 0.5) * 0.35)[..., None]
    base = base * np.array([1.03, 1.0, 0.9])
    return finish(base)


# ── 5. マグリット『人の子』(1964) ─────────────────────────────────

def _cumulus(rng):
    """マグリットの雲: 輪郭のはっきりした丸い塊の集まり。上が明るく底が平らで灰色。"""
    x, y = grid()
    mask = np.zeros((S, S))
    light = np.zeros((S, S))
    for _ in range(5):
        cx, cy = rng.uniform(-20, 530), rng.uniform(30, 300)
        w = rng.uniform(70, 140)
        im = Image.new("L", (S, S), 0)
        d = ImageDraw.Draw(im)
        for _ in range(9):
            px = cx + rng.uniform(-w / 2, w / 2)
            py = cy - abs(rng.normal(0, w * 0.18))
            r = rng.uniform(w * 0.2, w * 0.36)
            d.ellipse([px - r, py - r, px + r, py + r], fill=255)
        d.rectangle([cx - w / 2 - 10, cy, cx + w / 2 + 10, cy + 400], fill=0)  # 底を平らに
        one = np.asarray(im.filter(ImageFilter.GaussianBlur(1.5)), float) / 255
        sh = np.clip((cy - y) / (w * 0.5), 0, 1) ** 0.6
        light = np.where(one > mask, sh, light)
        mask = np.maximum(mask, one)
    return mask, light


def son_of_man(m, rng):
    """曇り空と柔らかな雲、水平線の灰青の海、手前の低い石壁。
    図柄は顔を隠す青林檎そのもの — 滑らかな緑の量感に小さな斑点、上に葉と軸。"""
    x, y = grid()
    horizon, wall_top = 362, 424
    sky = ramp(["#5f778b", "#8aa0b1", "#b9c6cd"], np.clip(y / horizon, 0, 1))
    cl, cl_light = _cumulus(rng)
    clouds = ramp(["#a3adb3", "#dfe4e4", "#f7f8f5"], cl_light)
    sky = lerp(sky, clouds, cl * (y < horizon - 6))
    sea = ramp(["#3e5a6d", "#2c4557"], np.clip((y - horizon) / (wall_top - horizon), 0, 1))
    sea = sea * (0.96 + 0.06 * np.sin(y * 1.1 + np.sin(x / 30)))[..., None]
    stone = ramp(["#b4ae9e", "#8e887a", "#6f6a5f"], np.clip((y - wall_top) / 88 + (fbm(rng, (16, 8)) - 0.5) * 0.4, 0, 1))
    joints = (np.abs(y - 466) < 1.5) | ((np.abs(((x + (y > 466) * 40) % 96) - 48) < 1.2) & (y > wall_top + 8))
    stone = np.where(joints[..., None], hexrgb("#5a554c"), stone)
    stone = np.where((np.abs(y - wall_top - 4) < 4)[..., None], hexrgb("#cfc8b6"), stone)
    base = np.where((y < horizon)[..., None], sky, np.where((y < wall_top)[..., None], sea, stone))
    base = lerp(base, hexrgb("#c9d3d8"), np.clip(1 - np.abs(y - horizon) / 2, 0, 1) * 0.6)

    # 図柄: 青林檎
    diff = lit(m, 10, 46)
    apple = ramp(["#2a4a12", "#4f7e22", "#7fb236", "#b4d860", "#e4f2a8"], np.clip((diff - 0.45) * 1.9 + 0.35, 0, 1))
    speck = Image.new("L", (S, S), 0)
    sd = ImageDraw.Draw(speck)
    for _ in range(260):
        px, py = rng.uniform(0, S, 2)
        sd.ellipse([px - 1.6, py - 1.6, px + 1.6, py + 1.6], fill=255)
    apple = lerp(apple, hexrgb("#e9f3b6"), np.asarray(speck, float) / 255 * 0.55)
    apple = apple * (1 - edge_of(m, 2) * 0.25)[..., None]
    base = drop_shadow(base, m, 8, 12, 9, 0.35)
    base = lerp(base, apple, m)

    out = to_img(base)
    pts = np.argwhere(m > 0.5)
    if len(pts):
        top = pts[np.argmin(pts[:, 0] * 1.0 - pts[:, 1] * 0.35)]
        ty, tx = int(top[0]), int(top[1])
        d = ImageDraw.Draw(out)
        d.line([(tx, ty + 6), (tx + 8, ty - 34)], fill=(74, 52, 30), width=9)
        for ang, col in ((-38, (52, 96, 30)), (12, (80, 132, 42))):
            leaf = Image.new("L", (S, S), 0)
            lx, ly = tx + 8, ty - 28
            ImageDraw.Draw(leaf).ellipse([lx, ly - 17, lx + 86, ly + 17], fill=255)
            leaf = leaf.rotate(ang, center=(lx, ly), resample=Image.BICUBIC)
            out.paste(col, mask=leaf)
            vein = Image.new("L", (S, S), 0)
            ImageDraw.Draw(vein).line([(lx, ly), (lx + 80, ly)], fill=255, width=3)
            out.paste((170, 200, 110), mask=vein.rotate(ang, center=(lx, ly), resample=Image.BICUBIC))
    return finish(out)


# ── 6. ピカソ『ゲルニカ』(1937) ─────────────────────────────────

def guernica(m, rng):
    """白・灰・黒だけのモノクローム。鋭い斜めの面、上部に電球の目を持つギザギザの光、
    下端から上がる炎。図柄は馬の胴のように新聞の活字を思わせる縦の刻みで埋め、太い黒で縁取る。"""
    x, y = grid()
    region = np.zeros((S, S), dtype=np.int64)
    for k in range(8):
        a = rng.uniform(0, math.pi)
        px, py = rng.uniform(90, 420, 2)
        region = region * 2 + (((x - px) * math.cos(a) + (y - py) * math.sin(a)) > 0)
    darks = [hexrgb(h) for h in ("#121212", "#242424", "#363634", "#4c4b48", "#66645f")]
    lights = [hexrgb(h) for h in ("#f4f0e5", "#e4e0d4", "#d2cec2")]
    out = np.zeros((S, S, 3))
    hatch_regions = np.zeros((S, S), bool)
    for rid in np.unique(region):
        sel = region == rid
        r2 = np.random.default_rng(int(rid) * 104729 + 17)
        a = r2.uniform(0, math.tau)
        g = ((x * math.cos(a) + y * math.sin(a)) / S) % 1.0
        c_bg = darks[r2.integers(len(darks))]
        c_fg = lights[r2.integers(len(lights))]
        blk = lerp(c_bg, c_fg, m) * (0.86 + 0.24 * g)[..., None]
        out[sel] = blk[sel]
        if r2.random() < 0.5:
            hatch_regions |= sel
    # 新聞活字のような縦の刻み（図柄の中、半分の面だけ）
    hatch = ((y % 11) < 7) & ((x % 5) < 1.6)
    out = out * (1 - (hatch & hatch_regions) * m * 0.32)[..., None]
    # 上部の電球の目: ギザギザの光
    spikes = []
    for k in range(18):
        a = k * math.tau / 18
        rr = 92 if k % 2 == 0 else 52
        spikes.append((256 + rr * math.cos(a), 54 + rr * 0.42 * math.sin(a)))
    star = poly_mask(spikes, 0.8) * (1 - m)
    out = lerp(out, hexrgb("#ece7da"), star)
    bulb = np.clip((16 - np.hypot((x - 256) / 1.3, y - 56)) / 1.5, 0, 1) * (1 - m)
    out = lerp(out, hexrgb("#fbf8ee"), bulb)
    out = out * (1 - edge_of(star, 1.4) * 0.9)[..., None]
    out = out * (1 - np.clip(1 - np.abs(np.hypot((x - 256) / 1.3, y - 56) - 16) / 1.6, 0, 1) * (1 - m) * 0.9)[..., None]
    # 下端から上がる炎
    flames = []
    for k in range(6):
        fx = 30 + k * 90 + rng.uniform(-20, 20)
        flames += [(fx - 26, 512), (fx, 512 - rng.uniform(50, 90)), (fx + 26, 512)]
    fl = np.zeros((S, S))
    for k in range(0, len(flames), 3):
        fl = np.maximum(fl, poly_mask(flames[k:k + 3], 0.8))
    fl = fl * (1 - m)
    out = lerp(out, hexrgb("#e2ddd0"), fl)
    out = out * (1 - edge_of(fl, 1.2) * 0.85)[..., None]
    # 面の境界、図柄の太い黒い輪郭と、ずらした第二の視点
    gx = np.abs(np.diff(region, axis=1, prepend=region[:, :1])) > 0
    gy = np.abs(np.diff(region, axis=0, prepend=region[:1, :])) > 0
    out = out * (1 - np.clip(blur((gx | gy).astype(float), 0.8) * 1.5, 0, 0.6))[..., None]
    out = out * (1 - edge_of(m, 4.0) * 0.97)[..., None]
    m2 = np.roll(np.roll(m, -18, 1), 12, 0)
    out = out * (1 - edge_of(m2, 1.6) * 0.55)[..., None]
    out = out * (0.92 + fbm(rng, (6, 3)) * 0.16)[..., None]
    out = out * np.array([1.0, 0.99, 0.96])
    return finish(out)


PAINTERS = [
    ("van_gogh", "フィンセント・ファン・ゴッホ", "Vincent van Gogh",
     "『星月夜』1889", "渦巻く夜空・光輪をまとう星と月・糸杉・眠る村", starry_night),
    ("seurat", "ジョルジュ・スーラ", "Georges Seurat",
     "『グランド・ジャット島の日曜日の午後』1884–86", "点描・セーヌ川と帆・日なたと木陰の芝・人々の暗いシルエット", grande_jatte),
    ("rubens", "ピーテル・パウル・ルーベンス", "Peter Paul Rubens",
     "『キリスト降架』1612–14", "嵐の暗い空・十字架と梯子・対角を走る真紅の衣・白い亜麻布", descent_from_cross),
    ("van_eyck", "ヤン・ファン・エイク", "Jan van Eyck",
     "『アルノルフィーニ夫妻像』1434", "緑のドレスと緋の帳・十の円形装飾の凸面鏡・真鍮", arnolfini),
    ("magritte", "ルネ・マグリット", "René Magritte",
     "『人の子』1964", "曇り空と雲・灰青の海・低い石壁・顔を隠す青林檎", son_of_man),
    ("picasso", "パブロ・ピカソ", "Pablo Picasso",
     "『ゲルニカ』1937", "白黒灰のみ・鋭い面分割・電球の目・炎・活字の刻み", guernica),
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


def fallback(painter_fn, back: Image.Image) -> Image.Image:
    back = back.resize((S, S), Image.LANCZOS)
    icon = foreign_icon().resize((260, 260), Image.LANCZOS)
    out = back.copy()
    out.alpha_composite(icon, (126, 126))
    tex = back.copy()
    tex.putalpha(Image.fromarray((np.asarray(tex.split()[3], float) * 0.25).astype(np.uint8)))
    out.alpha_composite(tex)
    return out.resize((OUT, OUT), Image.LANCZOS)


# ── シート組版 ───────────────────────────────────────────────────

def font(sz):
    return ImageFont.truetype(FONT, sz)


def _place(sheet, d, icon, cx, cy, label, color=(60, 60, 60), size=20):
    sh = Image.new("RGBA", (OUT, OUT), (0, 0, 0, 0))
    sh.putalpha(icon.split()[3].filter(ImageFilter.GaussianBlur(6)).point(lambda v: v * 0.35))
    sheet.paste((0, 0, 0), (cx + 3, cy + 6), sh)
    sheet.paste(icon, (cx, cy), icon)
    tw = d.textlength(label, font=font(size))
    d.text((cx + (OUT - tw) / 2, cy + OUT + 6), label, font=font(size), fill=color)


def painter_sheet(key, jp, en, work, desc, fn, icons):
    cols, cell, pad, top = 4, 220, 36, 176
    rows = 4
    W = pad * 2 + cols * cell
    H = top + rows * (cell + 34) + pad
    sheet = Image.new("RGB", (W, H), (246, 243, 236))
    d = ImageDraw.Draw(sheet)
    d.text((pad, 26), jp, font=font(38), fill=(30, 30, 30))
    d.text((pad, 76), f"参考作品: {work}", font=font(24), fill=(140, 60, 40))
    d.text((pad, 114), f"{en} — {desc}", font=font(19), fill=(100, 96, 90))
    for i, ((label, _), icon) in enumerate(zip(GLYPHS, icons)):
        cx = pad + (i % cols) * cell + (cell - OUT) // 2
        cy = top + (i // cols) * (cell + 34)
        _place(sheet, d, icon, cx, cy, label)
    back = fn(np.zeros((S, S)), np.random.default_rng(7))
    cy = top + 3 * (cell + 34)
    _place(sheet, d, back, pad + (cell - OUT) // 2, cy, "下地（構図のみ）", (90, 86, 80), 18)
    _place(sheet, d, fallback(fn, back), pad + cell + (cell - OUT) // 2, cy, "未対応アプリ（自動）", (150, 60, 40), 18)
    d.text((pad + cell * 2 + 6, cy + 30),
           "専用アイコンがないアプリは、\n下地（iconback）に元アイコンを\n縮小して載せ、同じ質感（iconupon）\nを薄く重ねて馴染ませる。",
           font=font(18), fill=(90, 86, 80), spacing=8)
    sheet.save(SAMPLES / f"{key}.png", optimize=True)


def overview(rendered):
    cell, pad, lab_w = 112, 24, 330
    n = len(GLYPHS)
    W = pad * 2 + lab_w + n * cell
    H = 120 + len(rendered) * (cell + 18) + pad
    sheet = Image.new("RGB", (W, H), (246, 243, 236))
    d = ImageDraw.Draw(sheet)
    d.text((pad, 24), "画家スタイル・アイコンパック 叩き台 v2（参考作品つき）", font=font(34), fill=(30, 30, 30))
    for j, (label, _) in enumerate(GLYPHS):
        tw = d.textlength(label, font=font(15))
        d.text((pad + lab_w + j * cell + (cell - tw) / 2, 84), label, font=font(15), fill=(90, 90, 90))
    for r, (jp, work, icons) in enumerate(rendered):
        y0 = 112 + r * (cell + 18)
        d.text((pad, y0 + 22), jp, font=font(20), fill=(30, 30, 30))
        d.text((pad, y0 + 52), work, font=font(14), fill=(140, 60, 40))
        for j, ic in enumerate(icons):
            small = ic.resize((cell - 12, cell - 12), Image.LANCZOS)
            sheet.paste(small, (pad + lab_w + j * cell + 6, y0 + 6), small)
    sheet.save(SAMPLES / "overview.png", optimize=True)


def main():
    import sys
    only = set(sys.argv[1:])
    SAMPLES.mkdir(parents=True, exist_ok=True)
    masks = [(label, mask_of(fn)) for label, fn in GLYPHS]
    rendered = []
    for key, jp, en, work, desc, fn in PAINTERS:
        if only and key not in only:
            continue
        print(f"[RENDER] {key}")
        icons = [fn(m, np.random.default_rng(1000 + i)) for i, (_, m) in enumerate(masks)]
        painter_sheet(key, jp, en, work, desc, fn, icons)
        rendered.append((jp, work, icons))
    if not only:
        overview(rendered)
    print(f"[DONE] {SAMPLES}")


if __name__ == "__main__":
    main()
