"""聖なる持物パック v9 — 「機械が描いた」印象を消し、画家の手順に寄せる。

v8（render_pearl.py）で目立った CG／生成画像らしさの原因を一つずつ外す:

- 完全な対称・定規の直線 → 低い周波数の揺らぎで全体を少し歪める（手で描いた形）
- なめらかすぎる階調・樹脂のような艶 → 明暗を 4〜5 段の塊にまとめ（ノタン）、窓の映り込みは描かない
- 画面の外から来た鮮やかな色 → 17 世紀の顔料パレットへ寄せて混ぜる
- どこも同じ密度の仕上げ → 茶色の下塗り（インプリマトゥーラ）が暗部や薄い所で透ける
- 卓の縁の水平線や額の内側の線を、わずかにうねらせる

    python icon-pack/tools/render_handmade.py --texture <参考画像> [fn ...]

出力: icon-pack/samples/handmade/*.png
"""
from __future__ import annotations

import argparse
import sys
from pathlib import Path

import numpy as np
from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))
import render_attributes as ra  # noqa: E402
import render_northern as rn  # noqa: E402
import render_pearl as rp  # noqa: E402
import render_vermeer as rv  # noqa: E402
import render_vermeer_portrait as vp  # noqa: E402
from render_attributes import S, OUT, X, Y, blur, fbm, hexrgb, mix, noise  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
DEST = ROOT / "samples" / "handmade"

# フェルメールが使った顔料（おおよその色）
PIGMENTS = np.array([hexrgb(h) for h in (
    "#f2ecdc",  # 鉛白
    "#e6cf7a",  # 鉛錫黄
    "#b8893c",  # 黄土
    "#a8321e",  # 朱
    "#6e1e22",  # 茜のレーキ
    "#2a4aa0",  # 群青
    "#8a9ad0",  # 群青＋鉛白
    "#6e7a52",  # 緑土
    "#5a3c22",  # 生のアンバー
    "#2a1c12",  # 焼きアンバー
    "#121210",  # 骨炭
)])


def wobble(img, a, rng, amp=6.0):
    """手で描いた形の揺らぎ。大きくゆっくりした歪みと、小さなふるえ。"""
    dx = (fbm(rng, (220, 110)) - 0.5) * amp * 2 + (noise(rng, 24) - 0.5) * amp * 0.5
    dy = (fbm(rng, (220, 110)) - 0.5) * amp * 2 + (noise(rng, 24) - 0.5) * amp * 0.5
    out = rp.sample(img, X + dx, Y + dy)
    return out, (rp.sample(a, X + dx, Y + dy) if a is not None else None)


def notan(img, levels=5, soft=0.06):
    """明暗を数段の塊にまとめる。段の境は少しだけ柔らかく、後のにじみで溶ける。"""
    lum = np.clip(img.mean(-1) / 255, 1e-3, 1)
    t = lum * levels
    k = np.floor(t)
    f = t - k
    step = np.clip((f - (1 - soft * levels)) / (soft * levels), 0, 1)
    q = (k + step + 0.5) / levels
    q = np.clip(q, 0.02, 1)
    out = img * (q / lum)[..., None]
    out = out * 0.55 + img * 0.45     # 完全な段にはしない
    return np.stack([blur(out[..., c], 1.4) for c in range(3)], -1)


def pigment_mix(img, k=0.38):
    """最も近い二つの顔料の混色へ寄せる。画面の外の色（蛍光色・電子的な青）が消える。"""
    flat = img.reshape(-1, 3)
    d = (flat ** 2).sum(1)[:, None] + (PIGMENTS ** 2).sum(1)[None, :] - 2 * flat @ PIGMENTS.T
    d = np.maximum(d, 0)
    idx = np.argsort(d, 1)[:, :2]
    d1 = np.take_along_axis(d, idx[:, :1], 1)
    d2 = np.take_along_axis(d, idx[:, 1:2], 1)
    w = d2 / (d1 + d2 + 1e-6)
    target = PIGMENTS[idx[:, 0]] * w + PIGMENTS[idx[:, 1]] * (1 - w)
    return (flat * (1 - k) + target * k).reshape(img.shape)


def imprimatura(img, rng, a):
    """茶色の下塗りが暗部や薄塗りの所で透ける。斜めに擦り込んだ塗りむら。"""
    lum = np.clip(img.mean(-1) / 255, 0, 1)
    rub = fbm(rng, (90, 45, 22))
    streak = 0.5 + 0.5 * np.sin((X * 0.8 + Y * 0.55) / 9 + rub * 9)
    thin = np.clip((rub * 0.7 + streak * 0.3 - 0.52) * 3.2, 0, 1) * np.clip(1 - lum * 2.2, 0, 1)
    if a is not None:
        thin = thin * (1 - np.clip(a * 1.5, 0, 1) * 0.7)
    ground = hexrgb("#6b4a2a")
    return mix(img, img * 0.45 + ground * 0.55, thin * 0.5)


def render(fn, seed, tex):
    name = fn.__name__
    rng = np.random.default_rng(seed + 77)
    if name == "weather_rainbow":
        scene = rv.view_of_delft(np.random.default_rng(seed + 501))
        a = None
    else:
        scene = rv.scene(fn, seed)
        a = vp._LAST_ALPHA.get("a")
        scene = vp.chiaroscuro(scene, a)
    scene, a = wobble(scene, a, rng)
    scene = notan(scene)
    scene = pigment_mix(scene)
    scene = imprimatura(scene, rng, a)
    scene = rp.dry_edges(scene, a, rng)
    scene = vp.paint_surface(scene, 3)
    scene, field = rp.blended_strokes(scene, rng)
    scene = rp.lead_white(scene, rng, a, field, n=5)
    scene = vp.grade(scene)
    m = scene.mean(-1, keepdims=True)
    scene = m + (scene - m) * 1.05
    scene = rp.surface(scene, rng, tex)
    x0 = vp.CROP_X.get(name, 0)
    crop = scene[:, x0:x0 + vp.CROP_W]
    return vp.hang(rp.gilt_frame(crop, rng), rng, top=80)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--texture")
    ap.add_argument("only", nargs="*")
    args = ap.parse_args()
    vp._patch()
    rn.window_glint = lambda base, *a_, **k_: base   # 窓の映り込み（CG らしい艶）は描かない
    DEST.mkdir(parents=True, exist_ok=True)
    tex = rp.reference_texture(args.texture, np.random.default_rng(5)) if args.texture else None
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
