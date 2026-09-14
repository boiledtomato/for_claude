"""焼いた素材を組み直して動かす。アプリ側の描画と同じ手順を踏む。

  1. 風で茎を撓ませる（制御点を高さに応じてずらす）
  2. 撓んだ茎の上の付け根と接線を求める
  3. 器官の画像を、付け根を軸に (接線の変化 + 羽ばたき) だけ回して置く

茎そのものは形が毎フレーム変わるので、線を直に引く。
"""
import argparse
import json
import math
import os
from PIL import Image, ImageDraw
import numpy as np
import random
import render as R
from pencil import Pencil

A = "assets"
GRAPHITE = (66, 63, 68)


def load():
    with open(f"{A}/manifest.json", encoding="utf-8") as f:
        m = json.load(f)
    cache = {}
    for o in m["organs"]:
        cache[o["id"]] = Image.open(f"{A}/{o['image']}").convert("RGBA")
    for i, fr in enumerate(m["gemma"]["frames"]):
        cache[f"bloom{i}"] = Image.open(f"{A}/{fr['image']}").convert("RGBA")
    if m.get("roots"):
        cache["roots"] = Image.open(f"{A}/{m['roots']['image']}").convert("RGBA")
    return m, cache


def paper(size, seed=5):
    rng = np.random.default_rng(seed)
    n = rng.normal(0, 1, (size[1], size[0])).astype(np.float32) * 3.0
    arr = np.zeros((size[1], size[0], 3), dtype=np.float32)
    for i, c in enumerate((246, 243, 236)):
        arr[..., i] = c + n
    return Image.fromarray(arr.clip(0, 255).astype(np.uint8), "RGB")


def gust(phase, x, width):
    travel = x / max(width, 1) * 2.6
    return 0.58 + 0.30 * math.sin(phase - travel) + 0.12 * math.sin(phase * 3 - travel)


def bend_stem(stem, phase, width):
    """制御点を高さに応じてずらす。根元は動かない。"""
    p = [list(v) for v in stem["p"]]
    g = gust(phase, p[0][0], width)
    s = math.sin(phase * stem["sway"]["speed"] + stem["sway"]["phase"]
                 - p[0][0] / max(width, 1) * 2.6)
    amp = stem["sway"]["amp"] * g * s
    for i, v in enumerate((0.35, 0.75, 1.0)):
        p[i + 1][0] += amp * (v ** 1.7)
        p[i + 1][1] += abs(amp) * (v ** 1.7) * 0.12     # 撓んだぶん少し縮む
    return [tuple(v) for v in p]


def cubic_at(p, t):
    u = 1 - t
    a, b, c, d = u**3, 3*u*u*t, 3*u*t*t, t**3
    return (a*p[0][0] + b*p[1][0] + c*p[2][0] + d*p[3][0],
            a*p[0][1] + b*p[1][1] + c*p[2][1] + d*p[3][1])


def cubic_angle(p, t):
    u = 1 - t
    dx = 3*u*u*(p[1][0]-p[0][0]) + 6*u*t*(p[2][0]-p[1][0]) + 3*t*t*(p[3][0]-p[2][0])
    dy = 3*u*u*(p[1][1]-p[0][1]) + 6*u*t*(p[2][1]-p[1][1]) + 3*t*t*(p[3][1]-p[2][1])
    return math.degrees(math.atan2(dy, dx))


def place(dst, sprite, off, pivot, new_pivot, angle_deg):
    """画像を付け根中心に回して置く。"""
    px, py = pivot[0] - off[0], pivot[1] - off[1]      # 画像内の付け根
    rot = sprite.rotate(-angle_deg, resample=Image.BICUBIC, center=(px, py),
                        expand=False)
    dst.alpha_composite(rot, (int(round(new_pivot[0] - px)),
                              int(round(new_pivot[1] - py))))


def frame(m, cache, phase, bloom=0.0, boil=0):
    W, H = m["plate"]["width"], m["plate"]["height"]
    img = paper((W, H)).convert("RGBA")
    if "roots" in cache:
        ro = m["roots"]["off"]
        img.alpha_composite(cache["roots"], (int(ro[0]), int(ro[1])))
    bent = {s["id"]: bend_stem(s, phase, W) for s in m["stems"]}

    # 茎（毎フレーム形が変わるので直に引く）
    for s in m["stems"]:
        p = bent[s["id"]]
        pts = [cubic_at(p, i / 40) for i in range(41)]
        R.stem(img, pts, s["w0"], s["w1"], s["tone"],
               random.Random(boil * 7919 + 3))

    # 器官（焼いた画像を付け根で回して置く）
    for o in sorted(m["organs"], key=lambda x: x["z"]):
        p = bent[o["stem"]]
        at = cubic_at(p, o["t"])
        ang = cubic_angle(p, o["t"])
        sw = o["sway"]
        g = gust(phase, at[0], W)
        flutter = sw["amp"] * math.sin(phase * sw["speed"] + sw["phase"]) * (0.4 + 0.6 * g)
        place(img, cache[o["id"]], o["off"], o["pivot"], at,
              (ang - o["restAngle"]) + flutter)

    # 蕾（開く途中は連続コマを差し替える）
    gm = m["gemma"]
    p = bent[gm["stem"]]
    at = cubic_at(p, gm["t"])
    ang = cubic_angle(p, gm["t"])
    idx = min(len(gm["frames"]) - 1, max(0, int(round(bloom * (len(gm["frames"]) - 1)))))
    sw = gm["sway"]
    flutter = sw["amp"] * math.sin(phase * sw["speed"] + sw["phase"])
    place(img, cache[f"bloom{idx}"], gm["frames"][idx]["off"], gm["pivot"], at,
          (ang - gm["restAngle"]) + flutter)
    R.caption(ImageDraw.Draw(img, "RGBA"), random.Random(9))
    return img.convert("RGB")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default="motion.gif")
    ap.add_argument("--seconds", type=float, default=6.0)
    ap.add_argument("--period", type=float, default=9.0)
    ap.add_argument("--fps", type=int, default=10)
    ap.add_argument("--bloom", action="store_true")
    ap.add_argument("--still", action="store_true")
    ap.add_argument("--scale", type=float, default=0.30)
    args = ap.parse_args()

    m, cache = load()
    W, H = m["plate"]["width"], m["plate"]["height"]
    out = (int(W * args.scale), int(H * args.scale))

    if args.still:
        frame(m, cache, 0.4).resize(out, Image.LANCZOS).save(args.out)
        print("->", args.out)
        return

    n = int(args.seconds * args.fps)
    imgs = []
    for i in range(n):
        ph = i / n * (args.seconds / args.period) * 2 * math.pi
        b = 0.0
        if args.bloom:
            k = i / max(n - 1, 1)
            b = min(1.0, max(0.0, k * 2.4 - 0.3 if k < 0.6 else (2.4 - k * 2.4)))
        imgs.append(frame(m, cache, ph, b, boil=i).resize(out, Image.LANCZOS))
    imgs = [im.convert("P", palette=Image.ADAPTIVE, colors=96) for im in imgs]
    imgs[0].save(args.out, save_all=True, append_images=imgs[1:],
                 duration=int(1000 / args.fps), loop=0, optimize=True)
    print(f"-> {args.out}  {n} frames")


if __name__ == "__main__":
    main()
