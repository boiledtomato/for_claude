#!/usr/bin/env python3
"""アプリの描画を再現して、端末に入れる前に版面を確認する。

紙・撓み・当たり判定・開花を、アプリと同じ式で描く。

    python3 tools/render_home.py app/src/main/assets/plate --hits
    python3 tools/render_home.py app/src/main/assets/plate --bloom 1
    python3 tools/render_home.py app/src/main/assets/plate --grid   # 座標読み取り用
"""
import argparse
import json
import math
import os
from PIL import Image, ImageDraw


def hexc(s):
    s = s.lstrip("#")
    return tuple(int(s[i:i + 2], 16) for i in (0, 2, 4))


def fit(screen, pw, ph):
    s = min(screen[0] / pw, screen[1] / ph)
    return s, (screen[0] - pw * s) / 2, (screen[1] - ph * s) / 2


ROOT_SPEED = 2
ROOT_SHARE = 0.45
GUST_TRAVEL = 2.4
COLS, ROWS = 12, 20


def gust_at(phase):
    """風の強弱。ui/Wind.kt の gust と同じ式。"""
    return min(1.0, max(0.10, 0.52 + 0.32 * math.sin(phase) + 0.16 * math.sin(phase * 3 + 1.7)))


def build_organs(layer, plate_w):
    """ui/Wind.kt の WindField.of と同じ規則で、花ごとの揺れを組む。"""
    organs = []
    for spot in layer["organs"]:
        seed = java_hash(spot["id"])
        rnd = lambda n: abs((seed * 31 + n * 2654435761) % 1000) / 1000.0
        high = min(1.0, max(0.0, 1.0 - spot["at"][1]))
        leaf = spot.get("kind") == "leaf"
        organs.append({
            "x": spot["at"][0], "y": spot["at"][1],
            "sigma": min(0.22, max(0.05, spot["radius"] * 1.5)),
            "amp": plate_w * 0.010 * (0.45 + 0.85 * high) * (0.55 if leaf else 1.0)
                   * (0.55 + 1.0 * rnd(1)),
            "phase": rnd(2) * 2 * math.pi,
            "speed": (16 + int(rnd(3) * 11)) if leaf else (5 + int(rnd(4) * 10)),
            "bob": 0.22 + 0.30 * rnd(5),
        })
    return organs


def java_hash(text):
    """Kotlin の String.hashCode と同じ値を出す（位相を揃えるため）。"""
    h = 0
    for ch in text:
        h = (31 * h + ord(ch)) & 0xFFFFFFFF
    return h - 0x100000000 if h >= 0x80000000 else h


def displace(u, v, layer, organs, phase, gust, bloom=0.0, bloom_at=None):
    """ui/Wind.kt の displace と同じ式。"""
    py = max(layer["pivot"][1], 0.05)
    root = min(1.0, max(0.0, (py - v) / py))
    swing = math.sin(phase * ROOT_SPEED * layer["bend"]["speed"]
                     + layer["bend"]["phase"] - u * GUST_TRAVEL * 0.5)
    dx = layer["bend"]["amplitude"] * ROOT_SHARE * root * root * swing * gust
    dy = 0.0
    for o in organs:
        ddx = (u - o["x"]) / o["sigma"]
        ddy = (v - o["y"]) / (o["sigma"] * 1.25)
        d2 = ddx * ddx + ddy * ddy
        if d2 > 9:
            continue
        w = math.exp(-0.5 * d2)
        t = phase * o["speed"] + o["phase"] - u * GUST_TRAVEL
        a = o["amp"] * w * (0.45 + 0.55 * gust)
        dx += a * math.sin(t)
        dy += a * o["bob"] * math.sin(t * 2 + o["phase"])
    if bloom_at and bloom > 0.001:
        bx, by = u - bloom_at[0], v - bloom_at[1]
        d = math.hypot(bx, by)
        swell = math.exp(-0.5 * (d / 0.09) ** 2) * bloom
        if d > 1e-4:
            dx += bx / d * swell * 0.030
            dy += by / d * swell * 0.030
    return dx, dy


def warp(im, layer, organs, phase, gust, scale, bloom=0.0, bloom_at=None):
    """変位の場でメッシュ変形する。アプリの drawBitmapMesh と同じ格子。"""
    w, h = im.size
    mesh = []
    for r in range(ROWS):
        for c in range(COLS):
            x0, x1 = w * c / COLS, w * (c + 1) / COLS
            y0, y1 = h * r / ROWS, h * (r + 1) / ROWS
            quad = []
            for (px, py) in ((x0, y0), (x0, y1), (x1, y1), (x1, y0)):
                dx, dy = displace(px / w, py / h, layer, organs, phase, gust, bloom, bloom_at)
                quad += [px - dx * scale, py - dy * scale]
            mesh.append(((int(x0), int(y0), int(math.ceil(x1)), int(math.ceil(y1))),
                         tuple(quad)))
    return im.transform(im.size, Image.MESH, mesh, Image.BILINEAR)


def unfurl(p, q, bloom):
    """ui/Wind.kt の unfurl と同じ式。"""
    cx, cy = p - 0.5, q - 0.5
    scale = 0.22 + 0.78 * bloom
    twist = (1 - bloom) * 0.9
    c, s = math.cos(twist), math.sin(twist)
    return 0.5 + (cx * c - cy * s) * scale, 0.5 + (cx * s + cy * c) * scale


def render(assets, screen, phase, bloom, hits, out_path, save=True):
    with open(os.path.join(assets, "plate.json"), encoding="utf-8") as f:
        m = json.load(f)
    plate = m["plate"]
    s, dx, dy = fit(screen, plate["width"], plate["height"])
    paper, deep = hexc(plate["paper"]), hexc(plate.get("paperDeep", plate["paper"]))

    img = Image.new("RGB", screen, paper)
    d = ImageDraw.Draw(img, "RGBA")
    for y in range(screen[1]):
        t = abs(y / screen[1] - 0.5) * 2
        k = 0.5 * (1 - t)
        d.line([(0, y), (screen[0], y)],
               fill=tuple(int(paper[i] * (1 - k) + deep[i] * k) for i in range(3)))

    gemma = m.get("gemma")
    bloom_at = None
    host_id = None
    if gemma:
        for l in m["plants"]:
            for o in l["organs"]:
                if f"{l['id']}/{o['id']}" == gemma["organ"]:
                    bloom_at = (o["at"][0], o["at"][1])
                    host_id = l["id"]

    gust = gust_at(phase)
    for layer in m["plants"]:
        im = Image.open(os.path.join(assets, layer["image"])).convert("RGBA")
        rx, ry, rw, rh = layer["rect"]
        im = im.resize((max(1, int(rw * s)), max(1, int(rh * s))), Image.LANCZOS)
        organs = build_organs(layer, plate["width"])
        is_host = layer["id"] == host_id
        im = warp(im, layer, organs, phase, gust, s,
                  bloom if is_host else 0.0, bloom_at if is_host else None)
        img.paste(im, (int(dx + rx * s), int(dy + ry * s)), im)

        if hits:
            for o in layer["organs"]:
                cx = dx + (rx + o["at"][0] * rw) * s
                cy = dy + (ry + o["at"][1] * rh) * s
                r = o["radius"] * rw * s
                col = (200, 40, 40, 210) if o["kind"] == "bud" else (30, 90, 200, 150)
                d.ellipse([cx - r, cy - r, cx + r, cy + r], outline=col, width=4)

    if gemma and bloom > 0:
        g = Image.open(os.path.join(assets, gemma["openImage"])).convert("RGBA")
        gx, gy, gw, gh = gemma["rect"]
        eased = bloom * bloom * (3 - 2 * bloom)
        w2, h2 = max(2, int(gw * s)), max(2, int(gh * s))
        g = g.resize((w2, h2), Image.LANCZOS)
        # 花弁がほどけるように、スプライト自体を変形する
        n = 8
        mesh = []
        for r in range(n):
            for c in range(n):
                x0, x1 = w2 * c / n, w2 * (c + 1) / n
                y0, y1 = h2 * r / n, h2 * (r + 1) / n
                quad = []
                for (px, py) in ((x0, y0), (x0, y1), (x1, y1), (x1, y0)):
                    uu, vv = unfurl(px / w2, py / h2, eased)
                    quad += [px * 2 - (0.5 + (uu - 0.5)) * w2, py * 2 - (0.5 + (vv - 0.5)) * h2]
                mesh.append(((int(x0), int(y0), int(math.ceil(x1)), int(math.ceil(y1))),
                             tuple(quad)))
        g = g.transform(g.size, Image.MESH, mesh, Image.BILINEAR)
        a = min(1.0, max(0.0, (bloom - 0.04) / 0.30))
        g.putalpha(g.getchannel("A").point(lambda v: int(v * a)))
        # 蕾と同じだけ風に流す
        host = next((l for l in m["plants"] if l["id"] == host_id), None)
        off = (0.0, 0.0)
        if host:
            hx, hy, hw, hh = host["rect"]
            off = displace((gx + gw / 2 - hx) / hw, (gy + gh / 2 - hy) / hh,
                           host, build_organs(host, plate["width"]), phase, gust)
        cx = dx + (gx + gw / 2) * s + off[0] * s
        cy = dy + (gy + gh / 2) * s + off[1] * s
        img.paste(g, (int(cx - w2 / 2), int(cy - h2 / 2)), g)

    out = img.resize((screen[0] // 2, screen[1] // 2), Image.LANCZOS)
    if save:
        out.save(out_path)
        print("->", out_path)
    return out


def grid(assets, out_path, step=100, zoom=0.62):
    """レシピの座標を読み取るための、本体レイヤーのグリッド。"""
    with open(os.path.join(assets, "plate.json"), encoding="utf-8") as f:
        m = json.load(f)
    layer = m["plants"][0]
    im = Image.open(os.path.join(assets, layer["image"])).convert("RGB")
    w, h = im.size
    view = im.resize((int(w * zoom), int(h * zoom)), Image.LANCZOS)
    d = ImageDraw.Draw(view)
    for x in range(0, w, step):
        d.line([(x * zoom, 0), (x * zoom, view.height)], fill=(255, 0, 0))
        d.text((x * zoom + 2, 2), str(x), fill=(190, 0, 0))
    for y in range(0, h, step):
        d.line([(0, y * zoom), (view.width, y * zoom)], fill=(0, 0, 255))
        d.text((2, y * zoom + 2), str(y), fill=(0, 0, 190))
    view.save(out_path)
    print(f"-> {out_path}  (layer image is {w}x{h})")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("assets")
    ap.add_argument("--out", default="tools/work/home.png")
    ap.add_argument("--screen", nargs=2, type=int, default=[1080, 2100])
    ap.add_argument("--phase", type=float, default=1.2, help="風の位相（ラジアン）")
    ap.add_argument("--gif", type=float, default=0.0, help="この秒数ぶんの動きを GIF にする")
    ap.add_argument("--period", type=float, default=36.0, help="風の基準周期（秒）")
    ap.add_argument("--bloom", type=float, default=0.0)
    ap.add_argument("--hits", action="store_true")
    ap.add_argument("--grid", action="store_true")
    args = ap.parse_args()

    os.makedirs(os.path.dirname(args.out) or ".", exist_ok=True)
    if args.grid:
        grid(args.assets, args.out)
    elif args.gif > 0:
        fps = 12
        frames = int(args.gif * fps)
        step = 2 * math.pi * (args.gif / args.period) / frames
        imgs = []
        for i in range(frames):
            b = args.bloom
            if b < 0:                       # 負なら開花を往復させる
                k = i / max(frames - 1, 1)
                b = min(1.0, max(0.0, (k * 2 if k < 0.5 else 2 - k * 2) * 1.6))
            imgs.append(render(args.assets, tuple(args.screen), i * step, b,
                               args.hits, args.out, save=False)
                        .resize((args.screen[0] // 3, args.screen[1] // 3), Image.LANCZOS))
        imgs = [im.convert("P", palette=Image.ADAPTIVE, colors=96) for im in imgs]
        imgs[0].save(args.out, save_all=True, append_images=imgs[1:],
                     duration=int(1000 / fps), loop=0, optimize=True)
        print(f"-> {args.out}  {frames} frames")
    else:
        render(args.assets, tuple(args.screen), args.phase, args.bloom, args.hits, args.out)


if __name__ == "__main__":
    main()
