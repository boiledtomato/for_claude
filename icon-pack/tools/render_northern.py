"""聖なる持物パック v4 — 北方ルネサンス（初期ネーデルラント絵画）の室内・静物表現。

v3（render_attributes.py）の持物をそのまま描き、黒地と白地の 2 回描画の差から
不透明度を取り出して（差分マット）、新しい舞台に置き直す:

- 左上の窓から入る一方向の光、暗く釉をかけた壁（布や板張りのことも）
- 肖像画の手前に描かれる石の棚（パラペット）。持物はそこに置かれ、影を落とす
- 光沢のある物には窓の形の映り込み（ファン・エイクの真鍮・ガラス表現）
- 樫の額縁と金の細縁、油彩の黄変と亀裂

    python icon-pack/tools/render_northern.py

出力: icon-pack/samples/northern/*.png
"""
from __future__ import annotations

import math
import sys
from pathlib import Path

import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont

sys.path.insert(0, str(Path(__file__).resolve().parent))
import render_attributes as ra  # noqa: E402
from render_attributes import (  # noqa: E402
    C, OUT, S, X, Y, blur, canvas, edge, fbm, hexrgb, lit, M, mix, noise, paint, ramp, shade,
    trumpet_poly, petal_poly, SHAPE,
)

ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "northern"
JP_FONT = ra.JP_FONT
SERIF_B = ra.SERIF_B
LEDGE = 806          # 棚の上面の奥の線
FRONT = LEDGE + 46   # 棚の前面の始まり


# ── v3 の持物を切り抜く（差分マット） ───────────────────────────────

def cutout(fn, seed):
    ra.ROUNDEL = False
    orig_ground, orig_finish = ra.gold_ground, ra.finish
    ra.finish = lambda base, rng: base
    out = []
    try:
        for v in (0.0, 255.0):
            ra.gold_ground = lambda rng, hexc, field_r=372, v=v: np.zeros((S, S, 3)) + v
            out.append(np.clip(fn(np.random.default_rng(seed)), 0, 255))
    finally:
        ra.gold_ground, ra.finish, ra.ROUNDEL = orig_ground, orig_finish, True
    B, W = out
    a = np.clip(1 - (W - B).mean(-1) / 255, 0, 1)
    col = np.where(a[..., None] > 0.004, B / np.maximum(a, 0.004)[..., None], 0)
    return np.clip(col, 0, 255), a


def place(col, a, s=1.0, dx=0, dy=0):
    """切り抜きを中心基準で s 倍、(dx,dy) だけ動かす。"""
    rgba = np.dstack([col, a * 255]).astype(np.uint8)
    im = Image.fromarray(rgba, "RGBA")
    n = int(S * s)
    im = im.resize((n, n), Image.LANCZOS)
    cv = Image.new("RGBA", (S, S), (0, 0, 0, 0))
    cv.paste(im, (int((S - n) / 2 + dx), int((S - n) / 2 + dy)), im)
    arr = np.asarray(cv, float)
    return arr[..., :3], arr[..., 3] / 255


def put(base, col, a, shadow=(30, 16, 16, 0.5), glaze=True):
    if glaze:
        col = col * np.array([1.03, 0.99, 0.9])
        col = 128 + (col - 128) * 1.06
    if shadow:
        dx, dy, sg, k = shadow
        sh = np.roll(np.roll(blur(a, sg), dy, 0), dx, 1)
        base = base * (1 - sh * (1 - a) * k)[..., None]
    return mix(base, col, a)


# ── 舞台: 壁・棚・窓の光 ─────────────────────────────────────────

def wall(rng, hexc, brocade=None, panelled=False):
    light = np.exp(-(((X - 60) ** 2 + (Y - 20) ** 2) / (2 * 520 ** 2)))
    light = light + 0.35 * np.exp(-(((X - 360 + (Y - 300) * 0.5) ** 2) / (2 * 150 ** 2) + ((Y - 330) ** 2) / (2 * 260 ** 2)))
    tex = fbm(rng, (128, 64, 32)) * 0.7 + noise(rng, 6) * 0.3
    streak = 0.5 + 0.5 * np.sin(X / 3.1 + fbm(rng, (64,)) * 20)  # 刷毛目
    base = hexrgb(hexc) * (0.4 + 1.05 * light + (tex - 0.5) * 0.18 + (streak - 0.5) * 0.04)[..., None]
    if brocade:
        motif = _pomegranate()
        base = mix(base, base * 0.6 + hexrgb(brocade) * 0.4 * (0.5 + light)[..., None], motif * 0.55)
    if panelled:
        planks = (np.abs(((X + 30) % 170) - 85) > 82).astype(float)
        base = base * (1 - blur(planks, 1.5) * 0.35)[..., None]
        grain = 0.5 + 0.5 * np.sin(Y / 9 + fbm(rng, (64, 16)) * 14)
        base = base * (0.94 + grain * 0.1)[..., None]
    return base


def _pomegranate():
    """15 世紀フランドルの錦（ザクロ文様）。名誉の布（cloth of honour）用。"""
    im, d = canvas()
    for j in range(-1, 6):
        for i in range(-1, 6):
            cx = i * 200 + (100 if j % 2 else 0)
            cy = j * 200
            d.ellipse([cx - 34, cy - 44, cx + 34, cy + 44], outline=255, width=6)
            d.ellipse([cx - 12, cy - 12, cx + 12, cy + 12], fill=255)
            for s_ in (-1, 1):
                d.arc([cx + s_ * 40 - 60, cy - 10, cx + s_ * 40 + 60, cy + 110], 200 if s_ > 0 else 270, 300 if s_ > 0 else 340, fill=255, width=6)
                d.ellipse([cx + s_ * 70 - 10, cy + 60, cx + s_ * 70 + 10, cy + 80], fill=255)
            d.line([(cx, cy + 44), (cx, cy + 110)], fill=255, width=6)
    return M(im, 1.5)


def ledge(base, rng, carpet=False):
    """肖像画の手前に置かれる石の棚。上面は窓の光を受け、前面は暗い。"""
    top = (Y >= LEDGE) & (Y < FRONT)
    front = Y >= FRONT
    veins = np.clip((np.abs(np.sin(X / 70 + fbm(rng, (128, 32)) * 9)) - 0.96) * 30, 0, 1)
    stone_top = ramp(["#6e6658", "#a49b88", "#c9c0aa"], 0.55 + (Y - LEDGE) / 120 + (fbm(rng, (32, 8)) - 0.5) * 0.3 - X / 3000)
    stone_front = ramp(["#2e2a24", "#5a5346", "#7a7262"], 0.75 - (Y - FRONT) / 260 + (fbm(rng, (32, 8)) - 0.5) * 0.25 - X / 2600)
    stone_front = stone_front * (1 - veins * 0.08)[..., None]
    base = np.where(top[..., None], stone_top, base)
    base = np.where(front[..., None], stone_front, base)
    # 面取りの稜線と、奥の壁際の陰
    base = mix(base, hexrgb("#ddd4bc"), np.clip(1 - np.abs(Y - FRONT) / 2.5, 0, 1) * 0.8)
    base = base * (1 - np.clip(1 - (Y - LEDGE) / 18, 0, 1) * top * 0.35)[..., None]
    if carpet:
        base = _carpet(base, rng)
    return base


def _carpet(base, rng):
    """メムリンクの静物に描かれるアナトリア絨毯。棚の上に敷き、前へ垂らす。"""
    hang = FRONT + 96 + 8 * np.sin(X / 40)
    region = (Y >= LEDGE + 6) & (Y < hang) & (X > 60) & (X < 964)
    field = ramp(["#5a0e0c", "#9a1e16", "#b8301e"], 0.55 + (fbm(rng, (16, 8)) - 0.5) * 0.4)
    # 縁の帯（黄・青）と中央の八角形の文様
    u = X / 1.0
    v = np.where(Y < FRONT, (Y - LEDGE) * 2.6, (Y - FRONT) + 120)
    stripe = (np.abs(v - 12) < 6) | (np.abs(v - 214) < 6)
    oct_c = ((u % 120) - 60, ((v - 60) % 120) - 60)
    octa = (np.abs(oct_c[0]) + np.abs(oct_c[1]) < 44) & (np.maximum(np.abs(oct_c[0]), np.abs(oct_c[1])) < 32) & (v > 24) & (v < 204)
    inner = (np.abs(oct_c[0]) + np.abs(oct_c[1]) < 20) & octa
    col = np.where(stripe[..., None], hexrgb("#d6a838"), field)
    col = np.where(octa[..., None], hexrgb("#1e3a6e"), col)
    col = np.where(inner[..., None], hexrgb("#e2c060"), col)
    knot = 0.92 + 0.12 * ((np.floor(X / 4) + np.floor(Y / 4)) % 2)
    shading = np.where(Y < FRONT, 1.05, 0.62 - (Y - FRONT) / 600)
    col = col * (knot * shading)[..., None]
    fringe = (Y >= hang) & (Y < hang + 22) & ((X % 9) < 4) & (X > 60) & (X < 964)
    m_ = blur(region.astype(float), 1.0)
    base = mix(base, col, m_)
    base = mix(base, hexrgb("#d8ccb0") * 0.7, blur(fringe.astype(float), 0.7))
    return base


def window_glint(base, x, y, w, k=0.9, mask=None):
    """光沢面に映る窓（縦長の 2×2 の窓枠）。"""
    im, d = canvas()
    h = w * 1.4
    d.rounded_rectangle([x - w / 2, y - h / 2, x + w / 2, y + h / 2], max(2, w * 0.15), fill=255)
    bars, bd = canvas()
    bd.line([(x, y - h / 2), (x, y + h / 2)], fill=255, width=max(2, int(w * 0.14)))
    bd.line([(x - w / 2, y), (x + w / 2, y)], fill=255, width=max(2, int(w * 0.14)))
    g = np.clip(M(im, 1.2) - M(bars, 0.8), 0, 1)
    if mask is not None:
        g = g * mask
    return mix(base, hexrgb("#fffdf4"), g * k)


def contact(base, x0, x1, y, k=0.6):
    """物が棚に触れるところの濃い接地影。"""
    sh = np.exp(-(((X - (x0 + x1) / 2) / ((x1 - x0) / 2 + 1)) ** 2 * 2 + ((Y - y) / 10) ** 2))
    return base * (1 - sh * k)[..., None]


def craquelure(rng):
    im, d = canvas()
    for _ in range(700):
        x, y = rng.uniform(0, S, 2)
        a = rng.uniform(0, math.tau)
        pts = [(x, y)]
        for _ in range(rng.integers(2, 5)):
            a += rng.normal(0, 0.9)
            x += 9 * math.cos(a)
            y += 9 * math.sin(a)
            pts.append((x, y))
        d.line(pts, fill=255, width=1)
    return M(im, 0.6)


def finish(base, rng):
    """樫の額と金の細縁、油の黄変、ごく細かな亀裂、周辺の減光。"""
    vign = 1 - 0.35 * (((X - 420) ** 2 + (Y - 420) ** 2) / (2 * 560 ** 2))
    base = base * vign[..., None]
    base = base * (1 - craquelure(rng) * 0.1)[..., None]
    base = base * np.array([1.04, 1.0, 0.86])
    base = base * (0.97 + 0.06 * noise(rng, 3))[..., None]
    # 額縁: スクワークルの内側 34px を樫、その内側に金の細縁
    inner = ra.squircle(4.6, 0.055)
    inner2 = ra.squircle(4.6, 0.066)
    frame = np.clip(SHAPE - inner, 0, 1)
    gilt = np.clip(inner - inner2, 0, 1)
    oak = ramp(["#1e1208", "#3e2614", "#6a4426", "#8a5e36"],
               0.4 + 0.3 * np.sin((X + Y) / 7 + fbm(rng, (32, 8)) * 10) * 0.5 + lit(SHAPE - inner2 * 0.6, 6, 40) * 0.6 - 0.2)
    base = mix(base, oak, frame)
    base = mix(base, ramp(["#6a4a14", "#e2c26a", "#fff0b8"], lit(inner, 2, 30)), gilt)
    base = base * (1 - blur(np.clip(inner2 - ra.squircle(4.6, 0.085), 0, 1), 4) * 0.45)[..., None]  # 額の内側の落ち影
    a = (SHAPE * 255).astype(np.uint8)
    return Image.fromarray(np.dstack([np.clip(base, 0, 255).astype(np.uint8), a]), "RGBA")


# ── 各アイコンの舞台 ─────────────────────────────────────────────

def majolica_jug(base, rng):
    """メムリンク『花瓶の花』（c.1485）の IHS 入りマヨリカの水差し。"""
    body, bd = canvas()
    bd.ellipse([400, 650, 624, 846], fill=255)
    bd.rectangle([462, 586, 562, 690], fill=255)
    bd.ellipse([448, 572, 576, 606], fill=255)
    bd.polygon([(440, 838), (584, 838), (566, 864), (458, 864)], fill=255)
    bm = M(body)
    glaze = shade(bm, ["#7a7466", "#c9c2b0", "#efe9da", "#fbf8ee"], 22, 90, k=2.0)
    glaze = mix(glaze, glaze * 0.75 + hexrgb("#b8a070") * 0.25, np.clip((X - 520) / 160, 0, 1))
    deco, dd = canvas()
    dd.line([(404, 716), (620, 716)], fill=255, width=7)
    dd.line([(404, 790), (620, 790)], fill=255, width=7)
    for k in range(12):
        x = 410 + k * 18
        dd.arc([x - 9, 790, x + 9, 812], 0, 180, fill=255, width=4)
    for x in (470, 556):
        dd.line([(x, 600), (x - 4, 690)], fill=255, width=4)
    f = ImageFont.truetype(SERIF_B, 50)
    dd.text((514, 754), "IHS", font=f, fill=255, anchor="mm")
    dm = M(deco, 0.8) * bm
    glaze = mix(glaze, hexrgb("#1e3a8a") * (0.6 + 0.6 * lit(bm, 22, 90))[..., None], dm * 0.9)
    base = paint(base, bm, glaze, shadow=(34, 16, 16, 0.5), line=(2.5, "#3a3428", 0.7))
    # 取っ手（左右）
    hd_im, hd = canvas()
    for s_ in (-1, 1):
        hd.arc([512 + s_ * 112 - 50, 610, 512 + s_ * 112 + 50, 730], 270 if s_ > 0 else 90, 90 if s_ > 0 else 270, fill=255, width=18)
    hm = M(hd_im) * (1 - bm)
    base = paint(base, hm, shade(hm, ["#7a7466", "#d8d2c0", "#fbf8ee"], 4, 40), shadow=None, line=(2, "#3a3428", 0.7))
    base = window_glint(base, 452, 704, 26, 0.85, bm)
    base = mix(base, hexrgb("#ffffff"), np.exp(-(((X - 470) ** 2) / 50 + ((Y - 596) ** 2) / 30)) * 0.6)
    return contact(base, 440, 584, 864)


def landscape_window(rng):
    """パティニールの「世界風景」: 窓の向こうに青く霞む岩山、川、虹。"""
    base = wall(rng, "#4a4032")
    # アーチ形の窓の開口（石の抱き）
    op, od = canvas()
    od.rectangle([214, 300, 810, 790], fill=255)
    od.pieslice([214, 2, 810, 598], 180, 360, fill=255)
    opening = M(op, 1.2)
    sky = ramp(["#6f8fb8", "#a9c0d6", "#e6ecdc"], (Y - 40) / 600)
    hz = 560
    far = (Y > hz - 70 - 50 * np.abs(np.sin(X / 70)) * (X < 560) - 120 * np.clip(1 - np.abs(X - 330) / 60, 0, 1) ** 1.5 * 1)
    far_col = ramp(["#9db8d0", "#7c9cc0"], (Y - 430) / 200)
    mid = Y > hz + 10 * np.sin(X / 50)
    mid_col = ramp(["#7a9a6a", "#4e6e3e"], (Y - hz) / 140)
    near = Y > 670 + 30 * np.sin(X / 90 + 1)
    near_col = ramp(["#5a5a2a", "#3a3a1a"], (Y - 670) / 120)
    land = sky
    land = np.where(far[..., None], far_col, land)
    land = np.where(mid[..., None], mid_col, land)
    river = (np.abs(X - 600 - 70 * np.sin(Y / 40)) < (Y - hz) * 0.5) & mid & ~near
    land = np.where(river[..., None], ramp(["#c8d8e0", "#8aa8c0"], (Y - hz) / 120), land)
    land = np.where(near[..., None], near_col, land)
    # 尖った岩山（パティニールの特徴）
    jag = noise(rng, 6) * 0.5 + noise(rng, 16) * 0.5
    for px, ph, pw in ((320, 280, 52), (400, 170, 34), (690, 210, 44), (740, 120, 30)):
        prof = pw * ((Y - (hz - ph)) / ph) ** 0.8 * (0.75 + 0.5 * jag)
        crag = (np.abs(X - px) < prof) & (Y > hz - ph) & (Y < hz + 20)
        lit_side = X < px - prof * 0.1
        cc = np.where(lit_side[..., None], ramp(["#d2dce2", "#aebfcc"], (Y - hz + ph) / ph),
                      ramp(["#8aa0b6", "#6a84a0"], (Y - hz + ph) / ph))
        land = np.where(crag[..., None], cc, land)
    # 中景の木立と町
    trees = (np.clip((noise(rng, 5) - 0.62) * 8, 0, 1) * mid * ~near * (Y < hz + 90)).astype(float)
    land = mix(land, hexrgb("#2e4a26"), trees * 0.8)
    town, tdd = canvas()
    for k in range(6):
        tx = 270 + k * 22
        tdd.rectangle([tx, hz + 14, tx + 16, hz + 30], fill=255)
        tdd.polygon([(tx - 2, hz + 15), (tx + 8, hz + 4), (tx + 18, hz + 15)], fill=255)
    tm_ = M(town, 0.6)
    land = mix(land, np.where((Y < hz + 15)[..., None], hexrgb("#a8402a"), hexrgb("#d8ccb0")), tm_ * 0.85)
    # 雲と虹
    c = fbm(rng, (128, 64, 32))
    cl = np.clip((c - 0.56) * 5, 0, 1) * (Y < 420)
    land = mix(land, hexrgb("#f4f2ea"), cl * 0.8)
    r = np.hypot(X - 512, Y - 700)
    bands = ["#c8241e", "#ee7a1e", "#f2cc2a", "#4aa04a", "#2a6ac0", "#5a3a9a"]
    for k, hexc in enumerate(bands):
        r0 = 420 - k * 20
        m_ = np.clip((r0 - r) / 2, 0, 1) * np.clip((r - (r0 - 20)) / 2, 0, 1) * (Y < hz + 10 * np.sin(X / 50))
        land = mix(land, hexrgb(hexc), m_ * 0.42)
    # 小さな箱舟と鳥
    ark, ad = canvas()
    ad.polygon([(560, 640), (646, 640), (636, 660), (570, 660)], fill=255)
    ad.rectangle([578, 620, 628, 642], fill=255)
    ad.polygon([(572, 622), (603, 604), (634, 622)], fill=255)
    land = mix(land, hexrgb("#4a2a14"), M(ark, 0.8))
    birds, bd = canvas()
    for bx, by in ((420, 300), (446, 286), (470, 310)):
        bd.arc([bx - 10, by - 6, bx, by + 6], 200, 340, fill=255, width=3)
        bd.arc([bx, by - 6, bx + 10, by + 6], 200, 340, fill=255, width=3)
    land = mix(land, hexrgb("#2a2a2a"), M(birds, 0.6))
    base = mix(base, land, opening)
    # 窓の抱き（石の厚み）
    reveal = np.clip(edge(opening, 9) * 1.6, 0, 1) * (1 - opening * 0.5)
    base = mix(base, hexrgb("#8a826e"), reveal * 0.7)
    base = base * (1 - edge(opening, 2.5) * 0.5)[..., None]
    return ledge(base, rng)


SCENES = {
    # fn 名: (壁の色, 錦, 板張り, 縮尺, dx, dy, 絨毯)
    "phone_lily": ("#1c1912", None, False, 0.84, 0, -54, True),
    "message_dove": ("#3a0c0c", "#c8a040", False, 0.98, 0, -60, False),
    "camera_veil": ("#1e2a20", None, False, 1.02, 0, -44, False),
    "gallery_luke": ("#2a2218", None, False, 1.04, 0, -4, False),
    "mail_paul": ("#33463a", None, False, 1.0, 0, 110, False),
    "browser_orb": ("#141414", None, False, 1.1, 0, -14, False),
    "maps_shell": ("#16224a", None, False, 1.02, 0, 16, False),
    "music_organ": ("#14301e", "#b89a40", False, 1.06, 0, 6, False),
    "settings_wheel": ("#2a2016", None, False, 1.08, 0, -26, False),
    "calendar_paschal": ("#0c0c10", None, False, 0.96, 0, -56, False),
    "clock_hourglass": ("#3a2616", None, True, 0.96, 0, -50, False),
}


def scene(fn, seed):
    rng = np.random.default_rng(seed + 77)
    name = fn.__name__
    if name == "weather_rainbow":
        return finish(landscape_window(rng), rng)
    hexc, brocade, panelled, s, dx, dy, carpet = SCENES[name]
    base = wall(rng, hexc, brocade, panelled)
    if name == "mail_paul":
        # ホルバイン『ゲオルク・ギーゼ』: 壁の桟に挟んだ手紙
        bat, bd = canvas()
        bd.rectangle([110, 300, 914, 324], fill=255)
        bm = M(bat)
        for lx, ly, w_, h_, ang in ((210, 190, 150, 190, -6), (620, 200, 130, 170, 5), (790, 232, 110, 120, -3)):
            lt = Image.new("L", (S, S), 0)
            ImageDraw.Draw(lt).rectangle([lx, ly, lx + w_, ly + h_], fill=255)
            lm = M(lt.rotate(ang, center=(lx + w_ / 2, ly + h_ / 2), resample=Image.BICUBIC))
            base = paint(base, lm, shade(lm, ["#9a8a68", "#e2d6b4", "#f6ecd2"], 4, 40), shadow=(10, 12, 8, 0.45), line=(1.5, "#5a4a2a", 0.7))
            txt, td = canvas()
            for k in range(4):
                td.line([(lx + 16, ly + 26 + k * 20), (lx + w_ - 24, ly + 26 + k * 20)], fill=255, width=3)
            base = mix(base, hexrgb("#4a3420"), M(txt, 0.8) * lm * 0.55)
        base = paint(base, bm, shade(bm, ["#2a1a0c", "#6a4424", "#9a6a3a"], 3, 30), shadow=(10, 14, 8, 0.5), line=(1.5, "#140a04", 0.8))
    if name == "message_dove":
        # 受胎告知の窓から差す光
        beam = np.clip(1 - np.abs((X - 60) * 0.6 - (Y - 40) * 0.8 + 0) / 180, 0, 1) * np.clip(1 - np.hypot(X - 60, Y - 40) / 1100, 0, 1)
        base = mix(base, hexrgb("#f2d488"), beam * 0.35)
    if name == "calendar_paschal":
        glow = np.exp(-(((X - 512) ** 2 + (Y - 190) ** 2) / (2 * 230 ** 2)))
        base = base * 0.6 + hexrgb("#8a5a20") * glow[..., None] * 0.9
    base = ledge(base, rng, carpet)
    col, a = cutout(fn, seed)
    if name == "phone_lily":
        col, a = place(col, a, s, dx, dy)
        base = put(base, col, a, shadow=(32, 14, 18, 0.45))
        base = majolica_jug(base, rng)
    else:
        col, a = place(col, a, s, dx, dy)
        base = put(base, col, a)
    # 接地影と映り込み
    rows = np.where(a.max(1) > 0.5)[0]
    if len(rows) and name not in ("message_dove", "clock_hourglass", "camera_veil"):
        yb = rows[-1]
        xs = np.where(a[yb - 6] > 0.5)[0]
        if len(xs):
            base = contact(base, xs[0], xs[-1], min(yb, FRONT - 6), 0.5)
    glints = {
        "browser_orb": [(400, 500, 30)],
        "calendar_paschal": [(470, 850, 16)],
        "music_organ": [(364, 470, 12), (404, 500, 12)],
        "clock_hourglass": [(428, 420, 18)],
    }
    for gx, gy, gw in glints.get(name, []):
        base = window_glint(base, gx, gy, gw, 0.75, a)
    if name == "clock_hourglass":
        nail, nd = canvas()
        nd.ellipse([504, 154, 520, 170], fill=255)
        base = paint(base, M(nail), np.zeros((S, S, 3)) + hexrgb("#2a2a2a"), shadow=(6, 6, 3, 0.5), line=None)
    return finish(base, rng)


# ── 対応表（理由は v3 を引き継ぎ、北方の典拠を足す） ─────────────────────

NORTHERN_REF = {
    "phone_lily": "メムリンク『花瓶の花』c.1485 — IHS のマヨリカ水差しと絨毯",
    "message_dove": "ファン・エイク『ゲントの祭壇画』の聖霊の鳩、背後に名誉の布（ザクロ文様の錦）",
    "camera_veil": "メムリンク『聖ヴェロニカ』c.1470–75",
    "gallery_luke": "ロヒール・ファン・デル・ウェイデン『聖母を描く聖ルカ』c.1435–40",
    "mail_paul": "ホルバイン『商人ゲオルク・ギーゼ』1532 — 壁の桟に挟んだ手紙",
    "browser_orb": "初期ネーデルラントの『サルヴァトール・ムンディ』— 水晶に映る窓",
    "maps_shell": "初期ネーデルラントの聖ヤコブ像・巡礼者の帽子の貝（壁は巡礼路の道標の青）",
    "music_organ": "ファン・エイク『ゲントの祭壇画』— オルガンを弾く天使の翼パネル",
    "settings_wheel": "メムリンク『聖カタリナの神秘の結婚』— 足元の車輪",
    "calendar_paschal": "ロベルト・カンピン『メロードの祭壇画』— 室内の燭台と蝋燭",
    "clock_hourglass": "デューラー『書斎の聖ヒエロニムス』1514 — 板張りの壁の砂時計",
    "weather_rainbow": "パティニールの「世界風景」— 窓の向こうに霞む岩山と川",
}


WHY_OVERRIDE = {
    "clock_hourglass": "デューラー『書斎の聖ヒエロニムス』で板張りの壁に掛かる砂時計は、時が流れ去ることの印（メメント・モリ）。",
    "message_dove": "聖霊が降り、使徒たちの言葉が各国の人にそれぞれの言語で通じた日（ペンテコステ）。くわえたオリーブの枝はノアに陸地を知らせた鳩の便り。",
}


def font(sz):
    return ImageFont.truetype(JP_FONT, sz)


def sheet(rendered):
    cols, cw, chh, pad, top = 2, 800, 270, 40, 150
    rows = math.ceil(len(rendered) / cols)
    W, H = pad * 2 + cols * cw, top + rows * chh + pad
    im = Image.new("RGB", (W, H), (240, 236, 226))
    d = ImageDraw.Draw(im)
    d.text((pad, 30), "聖なる持物アイコンパック　v4 北方ルネサンス", font=font(40), fill=(30, 26, 20))
    d.text((pad, 88), "金地をやめ、窓の光が入る室内と石の棚に持物を置く。油彩の釉、窓の映り込み、樫の額縁。",
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
        lines = ra.wrap(d, "参考: " + ref, font(16), cw - OUT - 60)
        for k, line in enumerate(lines[:2]):
            d.text((tx, y0 + 82 + k * 24), line, font=font(16), fill=(40, 90, 120))
        for k, line in enumerate(ra.wrap(d, why, font(16), cw - OUT - 60)[:4]):
            d.text((tx, y0 + 82 + len(lines[:2]) * 24 + 6 + k * 24), line, font=font(16), fill=(80, 74, 64))
    im.save(DEST / "northern_sheet.png", optimize=True)


def homescreen(rendered):
    W, H = 720, 660
    yy = np.mgrid[0:H, 0:W][0]
    im = Image.fromarray(ramp(["#202430", "#2c2830", "#3a302a"], yy / H).astype(np.uint8))
    d = ImageDraw.Draw(im)
    for i, (app, _, _, icon, _, _) in enumerate(rendered):
        x, y = 44 + (i % 4) * 166, 50 + (i // 4) * 200
        ic = icon.resize((132, 132), Image.LANCZOS)
        im.paste(ic, (x, y), ic)
        tw = d.textlength(app, font=font(20))
        d.text((x + (132 - tw) / 2, y + 144), app, font=font(20), fill=(240, 236, 228))
    im.save(DEST / "homescreen.png", optimize=True)


def detail(rendered, picks=(0, 4, 11)):
    W, H = 3 * 500 + 40, 560
    im = Image.new("RGB", (W, H), (240, 236, 226))
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
        why = WHY_OVERRIDE.get(fn.__name__, why)
        rendered.append((app, attr, who, icon, why, NORTHERN_REF[fn.__name__]))
    if not only:
        sheet(rendered)
        homescreen(rendered)
        detail(rendered)
    print(f"[DONE] {DEST}")


if __name__ == "__main__":
    main()
