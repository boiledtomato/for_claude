#!/usr/bin/env python3
"""レシピから、アプリが読む素材一式（画像 + plate.json）を書き出す。

図版を足す・差し替えるときに触るのはレシピだけで、このスクリプトは変えない。

    python3 tools/make_plate.py tools/recipes/*.json

複数渡すと、まとめて書き出したうえで plates/index.json（切り替え用の一覧）も
作り直す。レシピの座標はすべて「ベクター化した図版画像の画素」で書く。
tools/render_home.py --grid にグリッドを出させて読み取るのが早い。
"""
import argparse
import json
import os
import numpy as np
from PIL import Image, ImageFilter


def feather_edges(im, pad):
    """外周を紙へ溶け込ませる。アプリ側の紙が同色なら継ぎ目は見えない。"""
    w, h = im.size
    mask = Image.new("L", (w, h), 0)
    mask.paste(Image.new("L", (w - pad * 2, h - pad * 2), 255), (pad, pad))
    mask = mask.filter(ImageFilter.GaussianBlur(pad * 0.42))
    out = im.convert("RGBA")
    out.putalpha(mask)
    return out


def _grow(mask_bool, radius):
    m = Image.fromarray((mask_bool.astype(np.uint8) * 255), "L")
    for _ in range(radius):
        m = m.filter(ImageFilter.MaxFilter(3))
    return np.asarray(m) > 0


def cut_by_hue(im, hue, dilate=1, feather=0.7):
    """指定した色みの領域だけ抜く。輪郭の黒線も拾えるよう少し太らせる。

    他の茎に重なる位置へ描くスプライト（開いた花など）に使う。
    """
    a = np.asarray(im.convert("RGB")).astype(np.int16)
    r, g, b = a[..., 0], a[..., 1], a[..., 2]
    if hue == "crimson":
        core = (r > 60) & (r - g > 32) & (r - b > 14)
    elif hue == "yellow":
        core = (r > 120) & (g > 100) & (r - b > 55) & (g - b > 45)
    elif hue == "blue":
        core = (b > 90) & (b - r > 18)
    else:
        raise SystemExit(f"unknown hue: {hue}")
    dark = (r < 120) & (g < 95) & (b < 105)
    mask = (core | (dark & _grow(core, 4))).astype(np.uint8) * 255

    m = Image.fromarray(mask, "L")
    m = m.filter(ImageFilter.MaxFilter(3)).filter(ImageFilter.MinFilter(3))
    for _ in range(dilate):
        m = m.filter(ImageFilter.MaxFilter(3))
    m = m.filter(ImageFilter.GaussianBlur(feather))

    out = im.convert("RGBA")
    out.putalpha(m)
    return out.crop(out.getbbox())


def build(recipe_path, root):
    with open(recipe_path, encoding="utf-8") as f:
        r = json.load(f)

    src = Image.open(os.path.join(root, r["source"])).convert("RGB")
    out_dir = os.path.join(root, r["assetsDir"], r["id"])
    os.makedirs(out_dir, exist_ok=True)

    # --- 本体レイヤー ---
    bq = r["bouquet"]
    box = tuple(bq["box"])
    body = feather_edges(src.crop(box), bq.get("feather", 46))
    body_path = os.path.join(out_dir, bq["image"])
    body.save(body_path, "WEBP", quality=bq.get("quality", 90), method=6)
    bw, bh = body.size
    print(f"  {bq['image']}: {bw}x{bh}  {os.path.getsize(body_path)//1024} KB")

    layer = r["layer"]
    rect = layer["rect"]
    scale = rect[2] / bw

    organs = [
        {
            "id": oid,
            "kind": kind,
            "at": [round(cx / bw, 4), round(cy / bh, 4)],
            "radius": round(rad / bw, 4),
            "label": label,
        }
        for oid, kind, cx, cy, rad, label in r["organs"]
    ]

    manifest = {
        "plate": r["plate"],
        "plants": [
            {
                "id": layer["id"],
                "latin": layer.get("latin", ""),
                "number": layer.get("number", ""),
                "image": bq["image"],
                "rect": rect,
                "pivot": layer["pivot"],
                "bend": layer["bend"],
                "captionAt": layer.get(
                    "captionAt", [r["plate"]["width"] / 2, rect[1] + rect[3] + 46]
                ),
                "organs": organs,
            }
        ],
    }

    # --- 蕾が開いたあとの花 ---
    gem = r.get("gemmaOpen")
    if gem:
        sprite = cut_by_hue(src.crop(tuple(gem["box"])), gem.get("cut", "crimson"))
        sprite_path = os.path.join(out_dir, gem["image"])
        sprite.save(sprite_path, "WEBP", quality=gem.get("quality", 92), method=6)
        print(f"  {gem['image']}: {sprite.width}x{sprite.height}  {os.path.getsize(sprite_path)//1024} KB")

        bud = next(o for o in r["organs"] if o[0] == gem["organ"])
        grow = gem.get("grow", 1.15)
        ow = sprite.width * scale * grow
        oh = sprite.height * scale * grow
        cx = rect[0] + bud[2] * scale
        cy = rect[1] + bud[3] * scale
        manifest["gemma"] = {
            "organ": f"{layer['id']}/{gem['organ']}",
            "openImage": gem["image"],
            "rect": [round(cx - ow / 2, 1), round(cy - oh / 2, 1), round(ow, 1), round(oh, 1)],
        }

    path = os.path.join(out_dir, "plate.json")
    with open(path, "w", encoding="utf-8") as f:
        json.dump(manifest, f, ensure_ascii=False, indent=2)
    print(f"  plate.json: {len(organs)} organs -> {path}")
    return r, out_dir


def write_index(entries, root):
    """切り替え用の一覧。アプリはこれを見て図版を並べる。"""
    if not entries:
        return
    # レシピの order で並べる。コマンドラインの展開順に依存させない。
    entries = sorted(entries, key=lambda e: (e[0].get("order", 999), e[0]["id"]))
    parent = os.path.dirname(entries[0][1])
    index = {
        "plates": [
            {
                "id": r["id"],
                "title": r.get("title", r["id"]),
                "latin": r.get("layer", {}).get("latin", ""),
            }
            for r, _ in entries
        ],
        "default": entries[0][0]["id"],
    }
    path = os.path.join(parent, "index.json")
    with open(path, "w", encoding="utf-8") as f:
        json.dump(index, f, ensure_ascii=False, indent=2)
    print(f"index.json: {len(index['plates'])} plates -> {path}")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("recipes", nargs="+")
    ap.add_argument("--root", default=".", help="レシピ内の相対パスの基準")
    ap.add_argument("--no-index", action="store_true")
    args = ap.parse_args()

    entries = []
    for path in args.recipes:
        print(os.path.basename(path))
        entries.append(build(path, args.root))
    if not args.no_index:
        write_index(entries, args.root)


if __name__ == "__main__":
    main()
