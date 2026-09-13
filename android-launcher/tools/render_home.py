#!/usr/bin/env python3
"""アプリの描画を再現して、端末に入れる前に版面を確認する。

紙・撓み・当たり判定・開花を、アプリと同じ式で描く。

    python3 tools/render_home.py app/src/main/assets/plate --hits
    python3 tools/render_home.py app/src/main/assets/plate --bloom 1
    python3 tools/render_home.py app/src/main/assets/plate --grid   # 座標読み取り用
"""
import argparse
import json
import os
from PIL import Image, ImageDraw


def hexc(s):
    s = s.lstrip("#")
    return tuple(int(s[i:i + 2], 16) for i in (0, 2, 4))


def fit(screen, pw, ph):
    s = min(screen[0] / pw, screen[1] / ph)
    return s, (screen[0] - pw * s) / 2, (screen[1] - ph * s) / 2


def bend(im, pivot, amplitude_px, swing, rows=48):
    """根元を固定して上ほど横へずらす。アプリの drawBitmapMesh と同じ式。"""
    w, h = im.size
    pad = int(abs(amplitude_px)) + 2
    out = Image.new("RGBA", (w + pad * 2, h), (0, 0, 0, 0))
    py = max(pivot[1], 0.05)
    for r in range(rows):
        y0, y1 = int(h * r / rows), int(h * (r + 1) / rows)
        v = (y0 + y1) / 2 / h
        u = max(0.0, min(1.0, (py - v) / py))
        dx = amplitude_px * (u ** 1.7) * swing
        out.paste(im.crop((0, y0, w, y1)), (pad + int(round(dx)), y0))
    return out, pad


def render(assets, screen, swing, bloom, hits, out_path):
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

    for layer in m["plants"]:
        im = Image.open(os.path.join(assets, layer["image"])).convert("RGBA")
        rx, ry, rw, rh = layer["rect"]
        im = im.resize((max(1, int(rw * s)), max(1, int(rh * s))), Image.LANCZOS)
        bent, pad = bend(im, layer["pivot"], layer["bend"]["amplitude"] * s, swing)
        img.paste(bent, (int(dx + rx * s - pad), int(dy + ry * s)), bent)

        if hits:
            for o in layer["organs"]:
                cx = dx + (rx + o["at"][0] * rw) * s
                cy = dy + (ry + o["at"][1] * rh) * s
                r = o["radius"] * rw * s
                col = (200, 40, 40, 210) if o["kind"] == "bud" else (30, 90, 200, 150)
                d.ellipse([cx - r, cy - r, cx + r, cy + r], outline=col, width=4)

    gem = m.get("gemma")
    if gem and bloom > 0:
        g = Image.open(os.path.join(assets, gem["openImage"])).convert("RGBA")
        gx, gy, gw, gh = gem["rect"]
        k = 0.34 + 0.66 * bloom
        w2, h2 = max(1, int(gw * s * k)), max(1, int(gh * s * k))
        g = g.resize((w2, h2), Image.LANCZOS)
        g.putalpha(g.getchannel("A").point(lambda v: int(v * bloom)))
        cx, cy = dx + (gx + gw / 2) * s, dy + (gy + gh / 2) * s
        img.paste(g, (int(cx - w2 / 2), int(cy - h2 / 2)), g)

    img.resize((screen[0] // 2, screen[1] // 2), Image.LANCZOS).save(out_path)
    print("->", out_path)


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
    ap.add_argument("--swing", type=float, default=0.85, help="-1..1 撓みの位相")
    ap.add_argument("--bloom", type=float, default=0.0)
    ap.add_argument("--hits", action="store_true")
    ap.add_argument("--grid", action="store_true")
    args = ap.parse_args()

    os.makedirs(os.path.dirname(args.out) or ".", exist_ok=True)
    if args.grid:
        grid(args.assets, args.out)
    else:
        render(args.assets, tuple(args.screen), args.swing, args.bloom, args.hits, args.out)


if __name__ == "__main__":
    main()
