"""聖なる持物パック v6 — フェルメール版を縦長の額装絵画にする。

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


# ── 筆致: 画面を筆の線で塗り直し、絵具の盛り上がりに光を当てる ──────────────

def _orientation(src, rng):
    lum = src.mean(-1)
    b = ra.blur(lum, 3.0)
    gy, gx = np.gradient(b)
    mag = np.hypot(gx, gy)
    along = np.arctan2(gx, -gy)                        # 輪郭に沿う向き
    free = 1.15 + (fbm(rng, (160, 80)) - 0.5) * 1.6    # 平らな所は斜めに刷く
    w = np.clip(mag / 3.0, 0, 1)
    vx = np.cos(along) * w + np.cos(free) * (1 - w)
    vy = np.sin(along) * w + np.sin(free) * (1 - w)
    return np.arctan2(vy, vx), mag


def painterly(src, rng, a=None):
    """3 層の筆致（大・中・小）。各筆は毛先の筋を持ち、高さ図にも同じ形で盛る。"""
    ang, mag = _orientation(src, rng)
    src_soft = np.stack([ra.blur(src[..., c], 5) for c in range(3)], -1)
    src_mid = np.stack([ra.blur(src[..., c], 1.6) for c in range(3)], -1)
    color = Image.fromarray(np.clip(src_soft, 0, 255).astype(np.uint8))
    height = Image.new("L", (S, S), 90)
    cd, hd = ImageDraw.Draw(color), ImageDraw.Draw(height)
    detail = np.clip(mag / 2.5, 0, 1)
    layers = (
        (src_soft, 4200, (34, 60), (12, 18), None),            # 下塗りの大きな筆
        (src_mid, 12000, (18, 32), (7, 11), "lit"),            # 光の当たる面を起こす中筆
        (src, 14000, (8, 14), (3, 5), "detail"),                # 輪郭と細部の小筆
    )
    for img, n, (l0, l1), (w0, w1), where in layers:
        ys, xs = rng.integers(0, S, n), rng.integers(0, S, n)
        if where == "detail":
            keep = detail[ys, xs] * 1.5 + (a[ys, xs] * 0.8 if a is not None else 0) > rng.random(n)
            ys, xs = ys[keep], xs[keep]
        elif where == "lit":
            lumv = img.mean(-1)[ys, xs] / 255
            keep = rng.random(n) < (lumv * 1.4 + detail[ys, xs])
            ys, xs = ys[keep], xs[keep]
        elif where is not None:
            keep = rng.random(n) < (where + detail[ys, xs])
            ys, xs = ys[keep], xs[keep]
        for y, x in zip(ys, xs):
            t = ang[y, x] + rng.normal(0, 0.1)
            L = rng.uniform(l0, l1) / 2
            w = int(rng.uniform(w0, w1))
            dx, dy = L * math.cos(t), L * math.sin(t)
            p0, p1 = (x - dx, y - dy), (x + dx, y + dy)
            c = img[y, x] + rng.normal(0, 5, 3)
            col = tuple(int(v) for v in np.clip(c, 0, 255))
            cd.line([p0, p1], fill=col, width=w)
            hd.line([p0, p1], fill=150, width=w)
            hd.line([p0, p1], fill=200, width=max(1, w // 2))
            # 毛先の筋（明暗 2 本）
            ox, oy = -math.sin(t) * w * 0.28, math.cos(t) * w * 0.28
            for k, f in ((1, 1.07), (-1, 0.93)):
                q0 = (p0[0] + ox * k, p0[1] + oy * k)
                q1 = (p1[0] + ox * k, p1[1] + oy * k)
                cd.line([q0, q1], fill=tuple(int(v) for v in np.clip(c * f, 0, 255)), width=max(1, w // 6))
                hd.line([q0, q1], fill=120 if k > 0 else 230, width=max(1, w // 6))
    out = np.asarray(color, float)
    h = ra.blur(np.asarray(height, float) / 255, 1.1)
    gy, gx = np.gradient(h * 9)
    relief = np.clip((-gx * -0.6 - gy * -0.7) * 1.0, -1, 1)   # 左上からの光
    lumf = np.clip(out.mean(-1) / 255 * 1.6, 0.15, 1)   # 筆跡の凹凸は明部ほど見える
    out = out * (1 + relief * 0.2 * lumf)[..., None]
    # 半分ほどを溶かした下層に戻し、フェルメールのなめらかさを出す
    return out * 0.68 + src_mid * 0.32


def impasto_dots(base, rng, a, n=120):
    """明部に厚く盛った光の粒。粒の右下に小さな影。"""
    lum = base.mean(-1)
    sel_mask = (a > 0.5) if a is not None and (a > 0.5).any() else np.ones_like(lum, bool)
    thr = np.quantile(lum[sel_mask], 0.97)
    pts = np.argwhere((lum > thr) & sel_mask)
    if not len(pts):
        return base
    pts = pts[rng.choice(len(pts), min(n, len(pts)), replace=False)]
    dots = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(dots)
    for y, x in pts:
        r = rng.uniform(2.5, 4.8)
        d.ellipse([x - r, y - r, x + r, y + r], fill=255)
    m = ra.blur(np.asarray(dots, float) / 255, 0.9)
    sh = np.roll(np.roll(m, 3, 0), 3, 1) * (1 - m)
    base = base * (1 - sh * 0.35)[..., None]
    return ra.mix(base, hexrgb("#fff8e0"), m * 0.9)


def grade(base):
    """光は温かく影は青灰。にじみ（ぼかし）は掛けない — 筆跡を残す。"""
    lum = np.clip(base.mean(-1) / 255, 0, 1)[..., None]
    out = base * (1 - (1 - lum) ** 2 * 0.1) + hexrgb("#44505a") * (1 - lum) ** 2 * 0.1
    out = out * (1 - lum ** 3 * 0.06) + hexrgb("#fff0cc") * lum ** 3 * 0.06
    weave = 1 + 0.03 * (np.sin(np.arange(S)[:, None] * 2.1) * np.sin(np.arange(S)[None, :] * 2.1))
    return out * weave[..., None] * np.array([1.03, 1.0, 0.93])


def dark_ground(rng):
    """『真珠の耳飾りの少女』の地: 緑の釉が暗く沈んだ黒。左上の光がわずかに地を起こす。"""
    X, Y = ra.X, ra.Y
    glow = np.exp(-(((X - 300) ** 2 + (Y - 330) ** 2) / (2 * 300 ** 2)))
    base = ramp(["#0d0f0a", "#1a1c14", "#2a2a1e"], glow * 0.9 + (fbm(rng, (128, 64)) - 0.5) * 0.15)
    return base


def dark_cloth(base, rng):
    """卓に掛けた群青の布。光は左上から、右と奥は闇に沈む。"""
    X, Y = ra.X, ra.Y
    fold = np.sin(X / 40 + np.sin(X / 120) * 2.5 + fbm(rng, (128,)) * 5)
    t = np.where(Y < rv.FRONT, 0.55 + 0.12 * fold, 0.36 + 0.3 * fold - (Y - rv.FRONT) / 500)
    col = ramp(["#05081a", "#101e52", "#2a46a0", "#7890d0", "#d8e0f0"], t)
    fall = np.clip(1.15 - X / 900, 0.25, 1)
    col = col * fall[..., None]
    return ra.mix(base, col, ra.blur((Y >= rv.TABLE).astype(float), 1.0))


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
    scene = painterly(scene, prng, a)
    scene = impasto_dots(scene, prng, a, n=36 if a is not None else 12)
    scene = grade(scene)
    scene = scene * (1 - rn.craquelure(prng) * 0.05)[..., None]
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
