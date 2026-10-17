"""聖なる持物パック v8 — 『真珠の耳飾りの少女』の画面そのものの質感と、金の額。

v7（render_vermeer_portrait.py）の持物・暗い地・卓の布を使い、画面の作り方を変える:

1. 闇との境の輪郭を、乾いた筆のようにかすれさせる（ターバンの縁）
2. 輪郭を保って色面をまとめた後、形に沿う向きへ色を流す（LIC）。
   筆の線は描かず、溶け合った筆の流れと、流れに沿う細い刷毛目だけが残る
3. 参考にする複製写真から明暗の細かな凹凸（亀裂・絵具の粒立ち）だけを抜き出し、
   パッチを継いで画面に転写する。写真自体はリポジトリに入れない（--texture で渡す）
4. 少数の厚い鉛白のハイライト、黄ばんだワニス、金の幅広額

    python icon-pack/tools/render_pearl.py --texture <参考画像> [fn ...]

--texture を省くと亀裂は手続き生成（v7 と同じ）になる。
出力: icon-pack/samples/pearl/*.png
"""
from __future__ import annotations

import argparse
import math
import sys
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw

sys.path.insert(0, str(Path(__file__).resolve().parent))
import render_attributes as ra  # noqa: E402
import render_vermeer as rv  # noqa: E402
import render_vermeer_portrait as vp  # noqa: E402
from render_attributes import S, OUT, X, Y, blur, fbm, hexrgb, mix, noise, ramp  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "pearl"


# ── 標本化と流れ場 ───────────────────────────────────────────────

def sample(img, xs, ys):
    """双線形補間で img を (xs, ys) から拾う。"""
    h, w = img.shape[:2]
    xs = np.clip(xs, 0, w - 1.001)
    ys = np.clip(ys, 0, h - 1.001)
    x0, y0 = np.floor(xs).astype(int), np.floor(ys).astype(int)
    fx, fy = xs - x0, ys - y0
    if img.ndim == 3:
        fx, fy = fx[..., None], fy[..., None]
    a, b = img[y0, x0], img[y0, x0 + 1]
    c, d = img[y0 + 1, x0], img[y0 + 1, x0 + 1]
    return (a * (1 - fx) + b * fx) * (1 - fy) + (c * (1 - fx) + d * fx) * fy


def stroke_field(img, rng):
    """構造テンソルから「形に沿う向き」を求める。平らな所は大きくうねる斜めの流れ。"""
    lum = blur(img.mean(-1), 2.0)
    gy, gx = np.gradient(lum)
    j11, j22, j12 = blur(gx * gx, 7), blur(gy * gy, 7), blur(gx * gy, 7)
    theta = 0.5 * np.arctan2(2 * j12, j11 - j22) + math.pi / 2
    coh = np.sqrt((j11 - j22) ** 2 + 4 * j12 ** 2) / (j11 + j22 + 1e-3)
    free = 1.0 + (fbm(rng, (200, 100)) - 0.5) * 1.8
    w = np.clip(coh * np.clip(np.sqrt(j11 + j22) / 2.5, 0, 1) * 1.5, 0, 1)
    vx = np.cos(theta) * w + np.cos(free) * (1 - w)
    vy = np.sin(theta) * w + np.sin(free) * (1 - w)
    n = np.hypot(vx, vy) + 1e-6
    return vx / n, vy / n


def lic(fields, vx, vy, length=11, step=1.0):
    """流線に沿って両方向へ積分した平均（線積分畳み込み）。fields は配列のリスト。"""
    acc = [f * 1.0 for f in fields]
    wsum = np.ones((S, S))
    for sign in (1, -1):
        px, py = X.copy(), Y.copy()
        dx, dy = vx * sign, vy * sign
        for k in range(1, length + 1):
            px = px + dx * step
            py = py + dy * step
            nx, ny = sample(vx, px, py), sample(vy, px, py)
            flip = np.where(nx * dx + ny * dy < 0, -1.0, 1.0)
            dx, dy = nx * flip, ny * flip
            w = 1 - k / (length + 1)
            for i, f in enumerate(fields):
                acc[i] = acc[i] + sample(f, px, py) * (w if f.ndim == 2 else w)
            wsum = wsum + w
    return [a / (wsum[..., None] if a.ndim == 3 else wsum) for a in acc]


# ── 画面の作り ─────────────────────────────────────────────────

def dry_edges(base, a, rng):
    """持物と闇の境を、乾いた筆の縁のようにかすれさせる。"""
    if a is None:
        return base
    band = np.clip(1 - np.abs(blur(a, 2.5) - 0.5) * 2.3, 0, 1)
    dx = (noise(rng, 3) - 0.5) * 16 + (noise(rng, 9) - 0.5) * 10
    dy = (noise(rng, 3) - 0.5) * 16 + (noise(rng, 9) - 0.5) * 10
    return sample(base, X + dx * band, Y + dy * band)


def blended_strokes(base, rng):
    """溶け合った筆の流れ: 形に沿って色を流し、流れに沿う刷毛目の濃淡を残す。"""
    vx, vy = stroke_field(base, rng)
    grain = blur(rng.random((S, S)), 0.7)
    flowed, streak = lic([base, grain], vx, vy, length=11)
    streak = (streak - streak.mean()) / (streak.std() + 1e-6)
    lum = np.clip(flowed.mean(-1) / 255, 0, 1)
    out = flowed * 0.7 + base * 0.3
    return out * (1 + streak * (0.02 + 0.05 * lum))[..., None], (vx, vy)


def lead_white(base, rng, a, field, n=10):
    """最も明るい所に、流れの向きへ短く盛った鉛白（ターバンの上の白い点のような）。"""
    if a is None or not (a > 0.5).any():
        return base
    vx, vy = field
    lum = base.mean(-1)
    thr = np.quantile(lum[a > 0.5], 0.99)
    pts = np.argwhere((lum >= thr) & (a > 0.5))
    if not len(pts):
        return base
    pts = pts[rng.choice(len(pts), min(n, len(pts)), replace=False)]
    dab = Image.new("L", (S, S), 0)
    d = ImageDraw.Draw(dab)
    for y, x in pts:
        L = rng.uniform(6, 14)
        ex, ey = vx[y, x] * L, vy[y, x] * L
        d.line([(x - ex, y - ey), (x + ex, y + ey)], fill=255, width=int(rng.uniform(5, 9)))
    m = blur(np.asarray(dab, float) / 255, 1.4)
    gy, gx = np.gradient(blur(m, 1.5) * 12)
    relief = np.clip(gx * 0.6 + gy * 0.7, -1, 1)
    base = mix(base, hexrgb("#fbf6e8"), m * 0.8)
    return base * (1 + relief * 0.25)[..., None]


def reference_texture(path, rng, scale=0.72, patch=150):
    """参考の複製写真から明暗の細かな凹凸（亀裂・絵具の粒）だけを取り出し、パッチを継いで S×S にする。

    regions は写真内の絵の部分（ターバン・顔・上着・黄色の布）の矩形。
    写真の画素は細部の濃淡としてしか使わない（色・形は持ち込まない）。"""
    im = Image.open(path).convert("RGB")
    w, h = im.size
    im = im.resize((int(w * scale), int(h * scale)), Image.LANCZOS)
    L = np.asarray(im.convert("L"), float)
    hp = L - np.asarray(Image.fromarray(L.astype(np.uint8)).filter(
        __import__("PIL.ImageFilter", fromlist=["GaussianBlur"]).GaussianBlur(4)), float)
    regions = [(640, 850, 1010, 1010), (566, 1593, 1062, 1841), (1062, 1062, 1239, 1770), (900, 1010, 1060, 1400)]
    regions = [tuple(int(v * scale) for v in r) for r in regions]
    out = np.zeros((S, S))
    wsum = np.zeros((S, S)) + 1e-6
    win1 = np.sin(np.linspace(0, math.pi, patch)) ** 2
    win = win1[:, None] * win1[None, :]
    stride = patch // 2
    for ty in range(-stride, S, stride):
        for tx in range(-stride, S, stride):
            x0, y0, x1, y1 = regions[rng.integers(len(regions))]
            if x1 - x0 <= patch or y1 - y0 <= patch:
                continue
            sx, sy = rng.integers(x0, x1 - patch), rng.integers(y0, y1 - patch)
            p = hp[sy:sy + patch, sx:sx + patch]
            p = (p - p.mean()) / (p.std() + 1e-6)
            ya, xa = max(ty, 0), max(tx, 0)
            yb, xb = min(ty + patch, S), min(tx + patch, S)
            out[ya:yb, xa:xb] += (p * win)[ya - ty:yb - ty, xa - tx:xb - tx]
            wsum[ya:yb, xa:xb] += win[ya - ty:yb - ty, xa - tx:xb - tx]
    tex = out / wsum
    return np.clip(tex / (tex.std() + 1e-6), -2.6, 2.6)


def surface(base, rng, tex):
    """亀裂と絵具の粒立ち（参考写真から、または手続き生成）、布目、ワニス。"""
    lum = np.clip(base.mean(-1) / 255, 0, 1)
    if tex is not None:
        base = base * (1 + tex * (0.035 + 0.11 * lum))[..., None]
    else:
        cr = vp.crack_network(rng, 22)
        base = base * (1 - cr * (0.2 + 0.25 * lum))[..., None]
    weave = vp.canvas_weave(rng)
    base = base * (1 + (weave - 0.5) * (0.03 + 0.04 * lum))[..., None]
    varn = 0.95 + 0.07 * fbm(rng, (128, 64))
    base = base * varn[..., None] * np.array([1.04, 1.0, 0.87])
    sheen = np.exp(-(((X + Y - 520) / 170) ** 2)) * np.clip(1 - Y / 900, 0, 1)
    return base + sheen[..., None] * 10


def saturate_vermeer(base):
    """群青と鉛錫黄をはっきりと（参考の青い布・黄色い布の彩度）。暗部は緑がかった黒へ。"""
    m = base.mean(-1, keepdims=True)
    out = m + (base - m) * 1.18
    lum = np.clip(m / 255, 0, 1)
    return out * (1 - (1 - lum) ** 3 * 0.25) + hexrgb("#0c0f0a") * (1 - lum) ** 3 * 0.25


# ── 金の額 ─────────────────────────────────────────────────────

def gilt_frame(img, rng, fw=84):
    """幅広の金の額。外の玉縁、溝、まだらに古びた幅の広い面（縁に平行な細い筋）、段、内側の金の細縁。
    四隅は留め継ぎ（斜めの継ぎ目）。光は左上から。"""
    h, w = img.shape[:2]
    H, W = h + 2 * fw, w + 2 * fw
    yy, xx = np.mgrid[0:H, 0:W].astype(float)
    d = np.minimum.reduce([xx, yy, W - 1 - xx, H - 1 - yy])
    t = d / fw
    side_l = (xx <= yy) & (xx <= H - 1 - yy) & (xx <= W - 1 - xx)
    side_t = (yy < xx) & (yy <= W - 1 - xx) & ~side_l
    side_r = (W - 1 - xx < yy) & (W - 1 - xx < H - 1 - yy) & ~side_l & ~side_t
    facing = np.where(side_l | side_t, 1.0, np.where(side_r, -0.35, -0.6))
    along = np.where(side_l | side_r, yy, xx)
    rs = np.random.default_rng(int(rng.integers(1 << 30)))
    n_small = np.asarray(Image.fromarray(rs.random((H // 6 + 2, W // 6 + 2)).astype(np.float32), "F")
                         .resize((W + 12, H + 12), Image.BICUBIC))[:H, :W]
    n_big = np.asarray(Image.fromarray(rs.random((H // 40 + 2, W // 40 + 2)).astype(np.float32), "F")
                       .resize((W + 80, H + 80), Image.BICUBIC))[:H, :W]
    mottle = np.clip((n_big * 0.7 + n_small * 0.3 - 0.45) * 2.2, 0, 1)
    striae = 0.5 + 0.5 * np.sin(d * 1.7 + n_small * 6 + np.sin(along / 40) * 2)
    prof = np.where(t < 0.09, 0.8 - t * 3,                                  # 外の玉縁
           np.where(t < 0.16, 0.25,                                          # 溝
           np.where(t < 0.74, 0.55 + 0.12 * np.cos((t - 0.16) / 0.58 * math.pi),  # 幅の広い面
           np.where(t < 0.8, 0.3,                                            # 段
           np.where(t < 0.92, 0.75 - (t - 0.8) * 2.5, 0.85)))))              # 内側の金の細縁
    tone = prof * (0.5 + 0.55 * facing) + (striae - 0.5) * 0.2 * ((t > 0.16) & (t < 0.74))
    gold = ramp(["#2a1806", "#6a4612", "#a77a2c", "#d8b25e", "#f6e2a0"], np.clip(tone, 0, 1))
    patina = ramp(["#3a220c", "#6e4418", "#8a5a22"], n_small)
    gold = mix(gold, patina, mottle * 0.75 * ((t > 0.16) & (t < 0.74)))
    gold = gold * (1 - np.clip(1 - np.abs(t - 0.16) / 0.03, 0, 1) * 0.6 - np.clip(1 - np.abs(t - 0.8) / 0.03, 0, 1) * 0.6)[..., None]
    mitre = np.clip(1 - np.abs(np.minimum(xx, W - 1 - xx) - np.minimum(yy, H - 1 - yy)) / 1.2, 0, 1) * (t < 1)
    gold = gold * (1 - mitre * 0.35)[..., None]
    out = gold.copy()
    inner = img.copy()
    iy, ix = np.mgrid[0:h, 0:w].astype(float)
    sh = np.exp(-ix / 12) * 0.55 + np.exp(-iy / 14) * 0.6
    inner = inner * (1 - np.clip(sh, 0, 0.7))[..., None]
    out[fw:fw + h, fw:fw + w] = inner
    return out


# ── 組み立て ────────────────────────────────────────────────────

def render(fn, seed, tex):
    name = fn.__name__
    rng = np.random.default_rng(seed + 31)
    if name == "weather_rainbow":
        scene = rv.view_of_delft(np.random.default_rng(seed + 501))
        a = None
    else:
        scene = rv.scene(fn, seed)
        a = vp._LAST_ALPHA.get("a")
        scene = vp.chiaroscuro(scene, a)
    scene = dry_edges(scene, a, rng)
    scene = vp.paint_surface(scene, 3)
    scene, field = blended_strokes(scene, rng)
    scene = lead_white(scene, rng, a, field)
    scene = vp.grade(scene)
    scene = saturate_vermeer(scene)
    scene = surface(scene, rng, tex)
    x0 = vp.CROP_X.get(name, 0)
    crop = scene[:, x0:x0 + vp.CROP_W]
    return vp.hang(gilt_frame(crop, rng), rng, top=80)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--texture", help="亀裂・絵具の粒立ちを写し取る参考画像（『真珠の耳飾りの少女』の写真）")
    ap.add_argument("only", nargs="*")
    args = ap.parse_args()
    vp._patch()
    DEST.mkdir(parents=True, exist_ok=True)
    tex = reference_texture(args.texture, np.random.default_rng(5)) if args.texture else None
    rendered = []
    for i, (app, attr, who, fn, why) in enumerate(ra.ICONS):
        if args.only and fn.__name__ not in args.only:
            continue
        print(f"[RENDER] {fn.__name__}")
        icon = render(fn, 2000 + i, tex)
        icon.resize((OUT, OUT), Image.LANCZOS).save(DEST / f"{fn.__name__}.png")
        icon.resize((432, 432), Image.LANCZOS).save(DEST / f"{fn.__name__}_432.png")
        icon.resize((720, 720), Image.LANCZOS).save(DEST / f"{fn.__name__}_720.png")
        rendered.append((app, icon))
    if not args.only:
        vp.DEST = DEST
        vp.homescreen(rendered, DEST / "homescreen_dark.png")
        vp.homescreen(rendered, DEST / "homescreen_wall.png", wall=True)
        vp.detail(rendered)
    print(f"[DONE] {DEST}")


if __name__ == "__main__":
    main()
