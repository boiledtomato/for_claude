#!/usr/bin/env python3
"""版画スキャンをベクター化して、任意の解像度で描き直す。

ラスターのまま拡大すると輪郭がぼけ、ギザギザが出る（いわゆる化け）。
かといって画像全体をそのままベクター化すると、色の領域がまとめられて
**細い黒線が隣の色に呑まれて消える**。版画は線が主役なので、それでは別物になる。

そこで線と色を分けて扱う:

  線   周囲より暗い成分（トップハット）だけを抜き出して二値化し、ベクター化する。
       ベクターなので何倍に拡大しても輪郭が崩れない。
  淡彩 もともと低周波なので、滑らかに拡大しても化けない。

重ね方に 2 通りある。

  overlay  元の絵をそのまま拡大し、上からベクター線で暗くする（既定）。
           元の線の濃さがそのまま残るので輪郭が痩せない。ぼけた線の上に
           芯が通った状態になる。
  replace  線を取り除いてから拡大し、ベクター線で引き直す。拡大率が大きい
           ときは有効だが、線の濃さが平均化されるぶん輪郭は弱くなる。

    python3 tools/vectorise.py in.png out.png --scale 4.0

注意: ベクター化はぼけとギザギザを消すが、元の画像に無い細部は増やせない。
効くのは「低い解像度の原画を大きく使いたい」場面。
"""
import argparse
import os
import numpy as np
from PIL import Image, ImageFilter
from scipy.ndimage import gaussian_filter
import cairosvg
import vtracer


def ink_mask(im, radius=2.4, thresh=26):
    """周囲より暗い画素＝線。淡彩のゆるやかな濃淡には反応しない。"""
    g = np.asarray(im.convert("L"), dtype=np.float32)
    bg = np.asarray(im.convert("L").filter(ImageFilter.GaussianBlur(radius)),
                    dtype=np.float32)
    dark = np.clip(bg - g, 0, None)
    return np.clip((dark - thresh) / max(1.0, 60.0 - thresh), 0, 1)


def inpaint_ink(im, a, radius=4.0):
    """線の画素を周りの淡彩で埋める（正規化畳み込み）。

    埋めずに拡大すると、ぼけた線がベクター線の周りに滲んで二重に見える。
    """
    arr = np.asarray(im.convert("RGB"), dtype=np.float32)
    valid = 1.0 - a
    num = np.stack([gaussian_filter(arr[..., c] * valid, radius) for c in range(3)], -1)
    den = gaussian_filter(valid, radius)
    filled = num / np.maximum(den, 1e-3)[..., None]
    v = valid[..., None]
    return Image.fromarray((arr * v + filled * (1 - v)).clip(0, 255).astype(np.uint8), "RGB")


def vector_ink(a, size, work_prefix, binary_thresh=0.52, speckle=2):
    """線を二値化 → ベクター化 → 目的の解像度で描き直す。"""
    os.makedirs(os.path.dirname(work_prefix) or ".", exist_ok=True)
    svg, png = f"{work_prefix}.svg", f"{work_prefix}.png"
    binary = (a > binary_thresh).astype(np.uint8) * 255
    Image.fromarray(255 - binary, "L").convert("RGB").save(f"{work_prefix}_bin.png")
    vtracer.convert_image_to_svg_py(
        f"{work_prefix}_bin.png", svg, colormode="binary",
        filter_speckle=speckle, corner_threshold=56, length_threshold=2.2,
        splice_threshold=42, mode="spline", path_precision=3)
    # 背景を白で敷く。透明のままだと黒と解釈されて画面全体が線になる。
    cairosvg.svg2png(url=svg, write_to=png, output_width=size[0],
                     output_height=size[1], background_color="white")
    return Image.open(png).convert("L")


def ink_colour(im, a):
    arr = np.asarray(im.convert("RGB"), dtype=np.float32)
    strong = a > 0.6
    if strong.sum() < 50:
        return (60, 52, 48)
    return tuple(int(v) for v in arr[strong].mean(axis=0))


def restore(im, scale=4.0, thresh=26, radius=2.4, speckle=2, keep=0.55,
            binary_thresh=0.52, mode="overlay", work_prefix="tools/work/_vec"):
    """[im] を [scale] 倍で描き直した RGB 画像を返す。mode は overlay / replace。"""
    size = (round(im.width * scale), round(im.height * scale))
    a = ink_mask(im, radius=radius, thresh=thresh)
    vec = vector_ink(a, size, work_prefix, binary_thresh, speckle)
    cov = 1.0 - np.asarray(vec, dtype=np.float32) / 255.0
    ink = np.array(ink_colour(im, a), dtype=np.float32)

    if mode == "overlay":
        base = np.asarray(im.resize(size, Image.LANCZOS), dtype=np.float32)
        cov = np.clip(cov * keep, 0, 1)[..., None]
        # 暗くする方向にだけ効かせる。明るい面を塗り潰さない。
        out = base * (1 - cov) + np.minimum(base, ink) * cov
    else:
        base = np.asarray(inpaint_ink(im, a).resize(size, Image.LANCZOS), dtype=np.float32)
        # 元の線の濃淡を保つ。すべて同じ濃さにすると版画が塗り絵になる。
        strength = np.asarray(
            Image.fromarray((a * 255).astype(np.uint8), "L").resize(size, Image.LANCZOS),
            dtype=np.float32) / 255.0
        cov = np.clip(cov * np.clip(strength * 1.35 + 0.18, 0, 1) * keep, 0, 1)[..., None]
        out = base * (1 - cov) + ink * cov

    return Image.fromarray(out.clip(0, 255).astype(np.uint8), "RGB")


def load_rgb(path):
    """透明パディング付きの画像は、中身だけ取り出す。"""
    im = Image.open(path)
    if im.mode == "RGBA":
        box = im.getchannel("A").getbbox()
        if box:
            im = im.crop(box)
    return im.convert("RGB")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("src")
    ap.add_argument("dst")
    ap.add_argument("--scale", type=float, default=4.0)
    ap.add_argument("--thresh", type=int, default=26, help="線とみなす暗さ。上げると線が減る")
    ap.add_argument("--keep", type=float, default=0.55, help="ベクター線の効き")
    ap.add_argument("--mode", choices=["overlay", "replace"], default="overlay")
    ap.add_argument("--binary-thresh", type=float, default=0.52)
    ap.add_argument("--speckle", type=int, default=2)
    args = ap.parse_args()

    im = load_rgb(args.src)
    out = restore(im, args.scale, thresh=args.thresh, keep=args.keep,
                  binary_thresh=args.binary_thresh, speckle=args.speckle,
                  mode=args.mode, work_prefix=os.path.splitext(args.dst)[0] + "_work")
    out.save(args.dst)
    print(f"{args.src} {im.size} -> {args.dst} {out.size}  (x{args.scale})")


if __name__ == "__main__":
    main()
