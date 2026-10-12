"""聖なる持物パック v5 — フェルメールの画風。

v3 の持物（render_attributes.py）を差分マットで切り抜き（render_northern.cutout）、
フェルメールの室内に置き直す:

- 左の鉛枠の窓から差す、冷たく柔らかい昼光。光は白い漆喰壁を斜めに流れる
- 壁には釘と釘穴（『牛乳を注ぐ女』）、ときに地図や黒枠の絵（『絵画芸術』）
- 手前のテーブルには襞の寄ったトルコ絨毯、または群青の布（『青衣の女』）
- ハイライトに置く光の粒（ポワンティエ）、カメラ・オブスキュラ的な柔らかいにじみ
- 影は青灰、光は鉛錫黄の温かさ。額は 17 世紀オランダの黒檀の波形縁
- 天気だけは室内ではなく『デルフトの眺望』

    python icon-pack/tools/render_vermeer.py

出力: icon-pack/samples/vermeer/*.png
"""
from __future__ import annotations

import math
import sys
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont

sys.path.insert(0, str(Path(__file__).resolve().parent))
import render_attributes as ra  # noqa: E402
import render_northern as rn  # noqa: E402
from render_attributes import (  # noqa: E402
    C, OUT, S, X, Y, blur, canvas, edge, fbm, hexrgb, lit, M, mix, noise, paint, ramp, shade, SHAPE,
)

ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "vermeer"
TABLE = 806
FRONT = TABLE + 50


def blur3(a, s):
    return np.stack([blur(a[..., c], s) for c in range(3)], -1)


# ── 室内 ────────────────────────────────────────────────────────

def plaster_wall(rng):
    """窓から差す光で明るむ白い漆喰壁。右下へ向かって青灰の陰に沈む。"""
    light = np.clip(1.05 - (X - 150) / 1050, 0, 1) ** 1.3
    light = light * np.clip(1 - np.clip(Y - 80, 0, None) / 1500, 0, 1)
    light = light + 0.28 * np.exp(-(((X - 330 + (Y - 300) * 0.55) ** 2) / (2 * 170 ** 2)))
    light = light * (1 - 0.35 * np.exp(-(Y / 90)))  # 天井際の陰
    lit_col = hexrgb("#f2ead6")
    shadow_col = hexrgb("#8e8a7c")
    base = mix(shadow_col, lit_col, np.clip(light, 0, 1))
    tex = fbm(rng, (96, 48, 24, 12))
    base = base * (0.95 + (tex - 0.5) * 0.12 + (noise(rng, 3) - 0.5) * 0.04)[..., None]
    # 漆喰のしみと釘穴
    stain = np.clip((fbm(rng, (160, 80)) - 0.62) * 3, 0, 1)
    base = base * (1 - stain * 0.06)[..., None]
    holes, hd = canvas()
    for _ in range(9):
        hx, hy = rng.uniform(260, 900), rng.uniform(120, 700)
        hd.ellipse([hx - 3, hy - 3, hx + 3, hy + 3], fill=255)
    hm = M(holes, 0.8)
    base = base * (1 - hm * 0.55)[..., None]
    base = mix(base, hexrgb("#f6f0e0"), np.roll(np.roll(hm, -3, 0), -3, 1) * (1 - hm) * 0.6)
    return base


def leaded_window(base, rng):
    """左端の鉛枠の窓。小さな長方形のガラスが光る。"""
    x0, x1 = 40, 176
    y0, y1 = 70, 600
    win = ((X > x0) & (X < x1) & (Y > y0) & (Y < y1)).astype(float)
    sky = ramp(["#f4f2e6", "#dfe6e2", "#c9d4d0"], (Y - y0) / (y1 - y0))
    pane_var = noise(rng, 22) * 0.12
    glass = sky * (0.92 + pane_var)[..., None]
    lead = ((((X - x0) % 34) < 3) | (((Y - y0) % 30) < 3)).astype(float)
    mull = ((np.abs(X - (x0 + x1) / 2) < 7) | (np.abs(Y - 300) < 8)).astype(float)
    glass = mix(glass, hexrgb("#5a5a52"), blur(lead, 0.6) * 0.55)
    glass = mix(glass, hexrgb("#3a2e22"), blur(mull, 0.8) * 0.9)
    # 色ガラスの紋章（『紳士とワインを飲む女』の窓）
    medal = np.hypot(X - 108, Y - 190) < 30
    glass = np.where(medal[..., None], ramp(["#c03a22", "#2a52a0", "#e6c24a"], (X - 78) / 60), glass)
    base = mix(base, glass, blur(win, 1.2))
    frame = (((np.abs(X - x0) < 10) | (np.abs(X - x1) < 12)) & (Y > y0 - 10) & (Y < y1 + 10)) | \
            (((np.abs(Y - y0) < 10) | (np.abs(Y - y1) < 12)) & (X > x0 - 10) & (X < x1 + 12))
    base = mix(base, hexrgb("#4a3a28"), blur(frame.astype(float), 1.0) * 0.95)
    # 窓辺の光のにじみ
    spill = np.exp(-(((X - x1 - 10) / 60) ** 2)) * (Y > y0) * (Y < y1)
    return mix(base, hexrgb("#fbf6e6"), spill * 0.35)


def nail(base, x, y):
    n_, nd = canvas()
    nd.ellipse([x - 6, y - 6, x + 6, y + 6], fill=255)
    nm = M(n_, 0.8)
    sh = np.roll(np.roll(blur(nm, 3), 6, 0), 9, 1)
    base = base * (1 - sh * (1 - nm) * 0.45)[..., None]
    return mix(base, hexrgb("#2a2622"), nm)


MAP_BOX = (250, 930, 110, 560)


def wall_map(base, rng):
    """壁の地図（『絵画芸術』のネーデルラント十七州図）。上下に黒い軸。"""
    x0, x1, y0, y1 = MAP_BOX
    m_ = ((X > x0) & (X < x1) & (Y > y0) & (Y < y1)).astype(float)
    paper = ramp(["#b9a274", "#d9c79c", "#e8dbb6"], 0.6 + (fbm(rng, (64, 32)) - 0.5) * 0.6 - (X - x0) / 2000)
    coast = np.clip((fbm(rng, (90, 45, 22)) - 0.5) * 8, 0, 1)
    paper = mix(paper, hexrgb("#c7b48a"), coast * 0.5)
    shore = np.clip(1 - np.abs(fbm(rng, (90, 45, 22)) - 0.5) * 60, 0, 1)
    paper = mix(paper, hexrgb("#6a5434"), shore * 0.5)
    grid = (((X - x0) % 85) < 1.5) | (((Y - y0) % 85) < 1.5)
    paper = mix(paper, hexrgb("#8a7450"), grid * 0.25)
    folds = np.abs(((X - x0) % 170) - 85) < 1.2
    paper = paper * (1 - folds * 0.12)[..., None]
    paper = paper * (0.82 + 0.3 * np.clip(1 - (X - x0) / 900, 0, 1))[..., None]
    base = mix(base, paper, blur(m_, 1))
    for yy in (y0, y1):
        rod = ((np.abs(Y - yy) < 9) & (X > x0 - 14) & (X < x1 + 14)).astype(float)
        base = mix(base, ramp(["#141210", "#4a4238", "#141210"], (Y - yy + 9) / 18), blur(rod, 0.8))
    sh = np.roll(np.roll(blur(m_, 10), 12, 0), 16, 1)
    return base * (1 - sh * (1 - m_) * 0.35)[..., None]


def framed_painting(base, x0, y0, x1, y1, rng):
    """壁の黒枠の絵（画中画）。中は暗い風景。"""
    m_ = ((X > x0) & (X < x1) & (Y > y0) & (Y < y1)).astype(float)
    inner = ((X > x0 + 22) & (X < x1 - 22) & (Y > y0 + 22) & (Y < y1 - 22)).astype(float)
    pic = ramp(["#3a3a2a", "#5a5a40", "#7a7a5a"], 0.3 + (fbm(rng, (48, 24)) - 0.5) * 0.8 + (Y - y0) / 1200)
    sky = Y < (y0 + y1) / 2 - 10
    pic = np.where(sky[..., None], ramp(["#8a8a7a", "#b0a888"], (Y - y0) / 200), pic)
    base = mix(base, hexrgb("#18160f"), m_)
    base = mix(base, pic, inner)
    sh = np.roll(np.roll(blur(m_, 10), 12, 0), 16, 1)
    return base * (1 - sh * (1 - m_) * 0.4)[..., None]


def turkish_carpet(base, rng):
    """テーブルに掛けたトルコ絨毯。襞で文様がうねり、手前へ垂れて房になる。"""
    fold = np.sin(X / 46 + np.sin(X / 130) * 2 + fbm(rng, (128,)) * 4)
    disp = fold * 14
    u = X + disp
    v = np.where(Y < FRONT, (Y - TABLE) * 3.0, (Y - FRONT) + 150)
    field = ramp(["#5a1410", "#8e2418", "#a83a22"], 0.5 + (noise(rng, 20) - 0.5) * 0.3)
    border = (np.abs(v - 26) < 14) | (np.abs(v - 300) < 14)
    border_col = ramp(["#c89a3a", "#e6c26a"], 0.5 + 0.5 * np.sin(u / 8))
    ou = (u % 150) - 75
    ov = ((v - 90) % 150) - 75
    medal = (np.abs(ou) + np.abs(ov) < 56) & (v > 44) & (v < 282)
    medal_in = (np.abs(ou) + np.abs(ov) < 28) & medal
    star = (np.abs(ou) < 4) | (np.abs(ov) < 4)
    col = np.where(border[..., None], border_col, field)
    col = np.where(medal[..., None], hexrgb("#1e3a7a"), col)
    col = np.where((medal & star)[..., None], hexrgb("#e2c060"), col)
    col = np.where(medal_in[..., None], hexrgb("#c8b88a"), col)
    knot = 0.93 + 0.1 * ((np.floor(X / 5) + np.floor(Y / 5)) % 2)
    shade_ = np.where(Y < FRONT, 1.08 - (X / 2400) + 0.06 * fold, 0.86 + 0.34 * fold - (Y - FRONT) / 500 - X / 3000)
    col = col * (knot * shade_)[..., None]
    region = (Y >= TABLE).astype(float)
    base = mix(base, col, blur(region, 1.0))
    # 天板の手前の稜線のハイライトと房
    base = mix(base, hexrgb("#e8c890"), np.clip(1 - np.abs(Y - FRONT) / 3, 0, 1) * 0.35)
    return base


def blue_cloth(base, rng):
    """『青衣の女』『牛乳を注ぐ女』の群青の布。深い襞と白い光。"""
    fold = np.sin(X / 38 + np.sin(X / 110) * 2.5 + fbm(rng, (128,)) * 5)
    fold2 = np.sin(X / 17 + fbm(rng, (64,)) * 6) * 0.3
    t = np.where(Y < FRONT, 0.7 + 0.15 * fold - X / 3000, 0.42 + 0.32 * (fold + fold2) - (Y - FRONT) / 600 - X / 3000)
    col = ramp(["#0a1438", "#1a2f78", "#3456b0", "#8aa2d8", "#e6ecf4"], t)
    region = (Y >= TABLE).astype(float)
    base = mix(base, col, blur(region, 1.0))
    return mix(base, hexrgb("#c8d4ec"), np.clip(1 - np.abs(Y - FRONT) / 3, 0, 1) * 0.3)


# ── 仕上げ: 光の粒・にじみ・色調・黒檀の額 ──────────────────────────

def pointille(base, rng, a=None, n=90, thresh=0.9):
    """明部の上に置く光の粒（『牛乳を注ぐ女』のパンの粒）。"""
    lum = base.mean(-1) / 255
    cand = lum > np.quantile(lum if a is None else lum[a > 0.5] if (a > 0.5).any() else lum, thresh)
    if a is not None:
        cand &= a > 0.5
    pts = np.argwhere(cand)
    if not len(pts):
        return base
    sel = pts[rng.choice(len(pts), min(n, len(pts)), replace=False)]
    im, d = canvas()
    for yy, xx in sel:
        r = rng.uniform(2.2, 4.6)
        d.ellipse([xx - r, yy - r, xx + r, yy + r], fill=255)
    dots = M(im, 1.0)
    return mix(base, hexrgb("#fffbe6"), dots * 0.85)


def camera_obscura(base):
    """柔らかなにじみ（ハレーション）と、光は温かく影は青灰に寄せる色調。"""
    soft = blur3(base, 1.1)
    lum = soft.mean(-1) / 255
    bloom = blur3(np.clip(soft - 200, 0, None), 10) * 0.6
    out = soft + bloom
    t = np.clip(lum, 0, 1)[..., None]
    out = out * (1 - (1 - t) ** 2 * 0.1) + hexrgb("#44505a") * (1 - t) ** 2 * 0.1
    out = out * (1 - t ** 3 * 0.08) + hexrgb("#fff0cc") * t ** 3 * 0.08
    sat_mean = out.mean(-1, keepdims=True)
    out = sat_mean + (out - sat_mean) * 0.94
    return out * np.array([1.03, 1.0, 0.93])


def ebony_frame(base, rng):
    """17 世紀オランダの黒檀の額。細かい波形（リップル）の刳り形が光を拾う。"""
    inner = ra.squircle(4.6, 0.06)
    frame = np.clip(SHAPE - inner, 0, 1)
    ang = np.arctan2(Y - C, X - C)
    ripple = 0.5 + 0.25 * np.sin(ang * 220 + np.hypot(X - C, Y - C) * 0.35)
    depth = np.clip((SHAPE - inner) * 3, 0, 1)
    ebony = ramp(["#060606", "#1a1816", "#3a3632", "#6a6460"],
                 0.25 + ripple * 0.3 * depth + lit(SHAPE - inner * 0.7, 4, 50) * 0.45 - 0.2)
    base = mix(base, ebony, frame)
    return base * (1 - blur(np.clip(inner - ra.squircle(4.6, 0.08), 0, 1), 5) * 0.5)[..., None]


def finish(base, rng, a=None):
    base = pointille(base, rng, a) if a is not None else base
    base = camera_obscura(base)
    base = base * (1 - rn.craquelure(rng) * 0.05)[..., None]
    base = ebony_frame(base, rng)
    al = (SHAPE * 255).astype(np.uint8)
    return Image.fromarray(np.dstack([np.clip(base, 0, 255).astype(np.uint8), al]), "RGBA")


# ── 『デルフトの眺望』（天気） ──────────────────────────────────────

def view_of_delft(rng):
    """重い雲の帯の下、雲間の光が奥の町を照らし、手前の建物は陰に沈む。凪いだ川面と砂地。"""
    hz = 600
    sky = ramp(["#7f97ae", "#aabccb", "#dfe3dc"], Y / hz)
    heavy = np.clip((fbm(rng, (160, 80, 40)) - 0.42) * 3, 0, 1) * np.clip(1 - Y / 260, 0, 1)
    sky = mix(sky, hexrgb("#4e5a66"), heavy * 0.8)
    cum = np.clip((fbm(rng, (128, 64, 32, 16)) - 0.42) * 4, 0, 1) * np.exp(-((Y - 380) / 140) ** 2)
    shade_c = np.clip(0.55 + (cum - np.roll(cum, 18, 0)) * 2, 0, 1)
    sky = mix(sky, ramp(["#9aa2a8", "#f8f4e8"], shade_c), cum * 0.95)
    r = np.hypot(X - 600, Y - 820)
    for k, hexc in enumerate(["#c8402a", "#e88a2a", "#eed04a", "#5aa05a", "#3a6ab0", "#6a4a9a"]):
        r0 = 470 - k * 16
        m_ = np.clip((r0 - r) / 2.5, 0, 1) * np.clip((r - (r0 - 16)) / 2.5, 0, 1) * (Y < hz)
        sky = mix(sky, hexrgb(hexc), m_ * 0.32)
    base = sky
    sunlit = np.clip((X - 420) / 160, 0, 1)  # 右奥ほど陽が当たる
    walls, wd = canvas()
    roofs, rd = canvas()
    x = 40
    while x < 990:
        w = rng.uniform(36, 70)
        h = rng.uniform(38, 70)
        wd.rectangle([x, hz - h, x + w, hz + 30], fill=255)
        rd.polygon([(x - 2, hz - h + 1), (x + w / 2, hz - h - rng.uniform(14, 26)), (x + w + 2, hz - h + 1)], fill=255)
        x += w + rng.uniform(-4, 2)
    wd.rectangle([250, hz - 120, 360, hz + 30], fill=255)
    rd.polygon([(246, hz - 120), (305, hz - 176), (364, hz - 120)], fill=255)
    tower, tw = canvas()
    tw.rectangle([694, hz - 200, 744, hz], fill=255)
    tw.rectangle([702, hz - 250, 736, hz - 198], fill=255)
    tw.rectangle([708, hz - 286, 730, hz - 248], fill=255)
    tw.polygon([(708, hz - 284), (719, hz - 318), (730, hz - 284)], fill=255)
    wm, rm, tm = M(walls, 0.8), M(roofs, 0.8), M(tower, 0.8)
    wall_c = mix(ramp(["#3e3a38", "#5a4e46"], noise(rng, 7)), ramp(["#a8805a", "#c89c6a"], noise(rng, 7)), sunlit)
    roof_c = mix(ramp(["#2e3a48", "#46566a"], noise(rng, 9)), ramp(["#b0583a", "#d07a4a"], noise(rng, 9)), sunlit)
    windows = ((X % 14) < 5) & ((Y % 18) < 7) & (Y > hz - 50)
    wall_c = wall_c * (1 - windows * 0.35)[..., None]
    base = mix(base, wall_c, wm)
    base = mix(base, roof_c, rm * (1 - wm * 0.0))
    tower_c = ramp(["#e8cc88", "#c8a060", "#7a6a58"], (X - 694) / 50)
    tower_c = tower_c * (1 - (((Y % 22) < 3) | ((np.abs(X - 719) < 4) & ((Y % 22) > 8))) * 0.25)[..., None]
    base = mix(base, tower_c, tm)
    # 川面: 町の映り込みを縦にぼかし、暗く、さざ波でずらす
    wy = hz + 30
    yi = np.clip(2 * wy - Y.astype(int), 0, S - 1)
    xi = np.clip((X + 3 * np.sin(Y * 0.8)).astype(int), 0, S - 1)
    refl = base[yi, xi]
    refl = np.stack([blur(refl[..., c], 3) for c in range(3)], -1)
    water_c = mix(ramp(["#5a6a68", "#3e4c4c"], (Y - wy) / 260), refl * 0.7, 0.55)
    water_c = water_c * (0.95 + 0.06 * np.sin(Y * 1.3))[..., None]
    base = np.where((Y > wy)[..., None], water_c, base)
    sand = Y > 880 + 18 * np.sin(X / 160)
    base = np.where(sand[..., None], ramp(["#c2a470", "#8a6e48"], (Y - 880) / 140 + (noise(rng, 10) - 0.5) * 0.3), base)
    boat, bd = canvas()
    bd.polygon([(640, wy + 70), (800, wy + 70), (780, wy + 98), (660, wy + 98)], fill=255)
    bd.line([(720, wy + 72), (720, wy - 30)], fill=255, width=5)
    ark, ad = canvas()
    ad.polygon([(150, wy + 120), (290, wy + 120), (274, wy + 152), (166, wy + 152)], fill=255)
    ad.rectangle([178, wy + 92, 262, wy + 122], fill=255)
    ad.polygon([(170, wy + 94), (220, wy + 64), (270, wy + 94)], fill=255)
    base = mix(base, hexrgb("#2a1e16"), np.maximum(M(boat, 0.8), M(ark, 0.8)))
    figs, fd = canvas()
    for fx in (300, 330, 560):
        fd.ellipse([fx - 7, 896, fx + 7, 910], fill=255)
        fd.polygon([(fx - 12, 948), (fx + 12, 948), (fx + 7, 906), (fx - 7, 906)], fill=255)
    base = mix(base, hexrgb("#1e2a3e"), M(figs, 0.8))
    return base


# ── 組み立て ────────────────────────────────────────────────────

SETUP = {
    # fn 名: (テーブル掛け, 壁の飾り, 縮尺, dx, dy)
    "phone_lily": ("carpet", None, 0.84, 30, -54),
    "message_dove": ("carpet", None, 0.9, 40, -80),
    "camera_veil": ("blue", None, 0.94, 40, -60),
    "gallery_luke": ("carpet", "painting", 1.0, 40, -4),
    "mail_paul": ("blue", None, 0.96, 40, 104),
    "browser_orb": ("carpet", None, 1.04, 40, -14),
    "maps_shell": ("blue", "map", 0.8, 50, 92),
    "music_organ": ("carpet", "painting", 1.02, 40, 6),
    "settings_wheel": ("blue", None, 1.02, 40, -20),
    "calendar_paschal": ("carpet", None, 0.94, 40, -50),
    "clock_hourglass": ("blue", None, 0.92, 40, -60),
}


def scene(fn, seed):
    rng = np.random.default_rng(seed + 501)
    name = fn.__name__
    if name == "weather_rainbow":
        return finish(view_of_delft(rng), rng)
    cloth, deco, s, dx, dy = SETUP[name]
    base = plaster_wall(rng)
    base = leaded_window(base, rng)
    if deco == "map":
        base = wall_map(base, rng)
    elif deco == "painting":
        base = framed_painting(base, 640, 110, 900, 330, rng)
    elif name not in ("clock_hourglass", "camera_veil"):
        base = nail(base, 760, 210)
    base = turkish_carpet(base, rng) if cloth == "carpet" else blue_cloth(base, rng)
    col, a = rn.cutout(fn, seed)
    col, a = rn.place(col, a, s, dx, dy)
    if name == "phone_lily":
        base = rn.put(base, col, a, shadow=(36, 14, 20, 0.35))
        base = stoneware_jug(base, rng, 512 + dx)
        a = np.maximum(a, ((np.abs(X - 512 - dx) < 120) & (Y > 570) & (Y < 866)).astype(float))
    else:
        # フェルメールの影は柔らかく、窓と反対（右）へ流れる
        base = rn.put(base, col, a, shadow=(40, 14, 22, 0.38), glaze=False)
    rows = np.where(a.max(1) > 0.5)[0]
    if len(rows) and name not in ("message_dove", "clock_hourglass", "camera_veil"):
        yb = rows[-1]
        xs = np.where(a[max(yb - 6, 0)] > 0.5)[0]
        if len(xs):
            base = rn.contact(base, xs[0], xs[-1], min(yb, FRONT - 8), 0.45)
    if name == "clock_hourglass":
        base = nail(base, 512 + dx, 116 + dy + 52)
    # 窓の光を受ける左側をわずかに明るく（物にも同じ光）
    side = np.clip(1 - (X - 200) / 900, 0, 1) * a
    base = base * (1 + side * 0.08)[..., None]
    return finish(base, rng, a)


def stoneware_jug(base, rng, cx):
    """『牛乳を注ぐ女』の卓上にもあるライン地方の灰釉炻器（ヴェスターヴァルト）。IHS のメダイヨン。"""
    body, bd = canvas()
    bd.ellipse([cx - 112, 650, cx + 112, 846], fill=255)
    bd.rectangle([cx - 50, 586, cx + 50, 690], fill=255)
    bd.ellipse([cx - 64, 572, cx + 64, 606], fill=255)
    bd.polygon([(cx - 72, 838), (cx + 72, 838), (cx + 54, 864), (cx - 54, 864)], fill=255)
    bm = M(body)
    glaze = shade(bm, ["#4a4c50", "#8a8e94", "#c4c8cc", "#eef0f0"], 22, 90, k=2.0)
    deco, dd = canvas()
    for yy in (716, 796):
        dd.line([(cx - 108, yy), (cx + 108, yy)], fill=255, width=9)
    for k in range(10):
        x = cx - 100 + k * 22
        dd.line([(x, 806), (x + 11, 836)], fill=255, width=5)
        dd.line([(x + 22, 806), (x + 11, 836)], fill=255, width=5)
    dd.ellipse([cx - 44, 724, cx + 44, 790], outline=255, width=6)
    f = ImageFont.truetype(ra.SERIF_B, 34)
    dd.text((cx, 757), "IHS", font=f, fill=255, anchor="mm")
    dm = M(deco, 0.8) * bm
    glaze = mix(glaze, hexrgb("#1e3a90") * (0.55 + 0.6 * lit(bm, 22, 90))[..., None], dm * 0.85)
    salt = np.clip((noise(rng, 2) - 0.7) * 5, 0, 1) * bm  # 塩釉の粒
    glaze = mix(glaze, hexrgb("#f0f2f2"), salt * 0.25)
    base = paint(base, bm, glaze, shadow=(40, 14, 22, 0.38), line=(2, "#2a2c30", 0.6))
    hd_im, hd = canvas()
    hd.arc([cx + 70, 600, cx + 170, 740], 270, 90, fill=255, width=20)
    hm = M(hd_im) * (1 - bm)
    base = paint(base, hm, shade(hm, ["#4a4c50", "#a4a8ae", "#e6e8ea"], 4, 40), shadow=None, line=(1.5, "#2a2c30", 0.6))
    lid, ld = canvas()
    ld.ellipse([cx - 60, 556, cx + 60, 600], fill=255)
    lm = M(lid)
    base = paint(base, lm, shade(lm, ["#6a5a3a", "#c8b070", "#f2e2a8"], 4, 40), shadow=None, line=(1.5, "#3a2e1a", 0.7))
    base = rn.window_glint(base, cx - 64, 704, 24, 0.8, bm)
    return rn.contact(base, cx - 72, cx + 72, 864, 0.45)


VERMEER_REF = {
    "phone_lily": "『牛乳を注ぐ女』c.1658 — 白壁と釘穴、卓上の青い装飾の炻器の水差し",
    "message_dove": "『窓辺で手紙を読む女』c.1657 — 鉛枠の窓から届く光と便り",
    "camera_veil": "フェルメールはカメラ・オブスキュラ（カメラの祖先）を使ったとされる。にじむ光の粒はその名残",
    "gallery_luke": "『絵画芸術』c.1666–68 — 画家のアトリエと、壁の黒枠の絵",
    "mail_paul": "『青衣の女』c.1663 — 手紙を読む女と、卓上の群青の布",
    "browser_orb": "『信仰の寓意』c.1670–72 — 部屋全体を映して吊るされたガラス球",
    "maps_shell": "『絵画芸術』『兵士と笑う女』の壁の地図",
    "music_organ": "『音楽の稽古』c.1662–65 — 鍵盤楽器と壁の絵",
    "settings_wheel": "『天文学者』『地理学者』c.1668–69 — 窓辺の学者と器具",
    "calendar_paschal": "『信仰の寓意』— 卓上の聖杯と典礼の品",
    "clock_hourglass": "『天秤を持つ女』c.1662–63 — 背後の「最後の審判」、時と裁きの寓意",
    "weather_rainbow": "『デルフトの眺望』c.1660–61 — 雲の切れ間の光、川面、陽を受ける新教会の塔",
}

WHY_OVERRIDE = {
    "clock_hourglass": "デューラー『書斎の聖ヒエロニムス』で壁に掛かる砂時計は、時が流れ去ることの印（メメント・モリ）。",
    "camera_veil": "布で顔を拭うと像がそのまま写った「真の像（vera icon）」。写真家の守護聖人。",
}


def font(sz):
    return ImageFont.truetype(ra.JP_FONT, sz)


def sheet(rendered):
    cols, cw, chh, pad, top = 2, 800, 280, 40, 150
    rows = math.ceil(len(rendered) / cols)
    W, H = pad * 2 + cols * cw, top + rows * chh + pad
    im = Image.new("RGB", (W, H), (238, 234, 224))
    d = ImageDraw.Draw(im)
    d.text((pad, 30), "聖なる持物アイコンパック　v5 フェルメール", font=font(40), fill=(30, 26, 20))
    d.text((pad, 88), "左の窓の昼光、白い漆喰壁、トルコ絨毯と群青の布、光の粒（ポワンティエ）、黒檀の波形額。",
           font=font(20), fill=(110, 100, 86))
    for i, (app, attr, who, icon, why, ref) in enumerate(rendered):
        x0 = pad + (i % cols) * cw
        y0 = top + (i // cols) * chh
        ic = icon.resize((OUT, OUT), Image.LANCZOS)
        sh = Image.new("RGBA", (OUT, OUT), (0, 0, 0, 0))
        sh.putalpha(ic.split()[3].filter(ImageFilter.GaussianBlur(7)).point(lambda v: v * 0.4))
        im.paste((0, 0, 0), (x0 + 3, y0 + 8), sh)
        im.paste(ic, (x0, y0), ic)
        tx = x0 + OUT + 28
        d.text((tx, y0 + 2), app, font=font(30), fill=(30, 26, 20))
        d.text((tx, y0 + 46), f"{attr} ／ {who}", font=font(19), fill=(150, 40, 30))
        lines = ra.wrap(d, "フェルメール: " + ref, font(16), cw - OUT - 60)[:3]
        for k, line in enumerate(lines):
            d.text((tx, y0 + 82 + k * 24), line, font=font(16), fill=(30, 70, 130))
        for k, line in enumerate(ra.wrap(d, why, font(16), cw - OUT - 60)[:4]):
            d.text((tx, y0 + 82 + len(lines) * 24 + 6 + k * 24), line, font=font(16), fill=(80, 74, 64))
    im.save(DEST / "vermeer_sheet.png", optimize=True)


def homescreen(rendered):
    W, H = 720, 660
    yy = np.mgrid[0:H, 0:W][0]
    im = Image.fromarray(ramp(["#1e2430", "#262a34", "#30302e"], yy / H).astype(np.uint8))
    d = ImageDraw.Draw(im)
    for i, (app, _, _, icon, _, _) in enumerate(rendered):
        x, y = 44 + (i % 4) * 166, 50 + (i // 4) * 200
        ic = icon.resize((132, 132), Image.LANCZOS)
        im.paste(ic, (x, y), ic)
        tw = d.textlength(app, font=font(20))
        d.text((x + (132 - tw) / 2, y + 144), app, font=font(20), fill=(240, 236, 228))
    im.save(DEST / "homescreen.png", optimize=True)


def detail(rendered, picks=(0, 6, 11)):
    W, H = 3 * 500 + 40, 560
    im = Image.new("RGB", (W, H), (238, 234, 224))
    d = ImageDraw.Draw(im)
    for j, i in enumerate(picks):
        app, attr, *_ = rendered[i]
        ic = rendered[i][3].resize((480, 480), Image.LANCZOS)
        im.paste(ic, (20 + j * 500, 20), ic)
        d.text((30 + j * 500, 510), f"{app}（{attr}）拡大", font=font(22), fill=(60, 54, 44))
    im.save(DEST / "detail.png", optimize=True)


def main():
    DEST.mkdir(parents=True, exist_ok=True)
    only = set(sys.argv[1:])
    rendered = []
    for i, (app, attr, who, fn, why) in enumerate(ra.ICONS):
        if only and fn.__name__ not in only:
            continue
        print(f"[RENDER] {fn.__name__}")
        icon = scene(fn, 2000 + i)
        icon.resize((OUT, OUT), Image.LANCZOS).save(DEST / f"{fn.__name__}.png")
        icon.resize((432, 432), Image.LANCZOS).save(DEST / f"{fn.__name__}_432.png")
        rendered.append((app, attr, who, icon, WHY_OVERRIDE.get(fn.__name__, why), VERMEER_REF[fn.__name__]))
    if not only:
        sheet(rendered)
        homescreen(rendered)
        detail(rendered)
    print(f"[DONE] {DEST}")


if __name__ == "__main__":
    main()
