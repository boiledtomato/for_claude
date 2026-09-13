#!/usr/bin/env python3
"""低解像度のフル図版に、高解像度の部分クロップを位置合わせして貼り込む。

古書の図版は「全体像は小さく、部分の拡大写真は大きい」形で出回ることが多い。
両方あるなら、部分を全体へ貼り戻すだけで実質的な解像度が数倍になる。

倍率と位置は正規化相互相関の総当たりで求める。

--vectorise を付けると、貼り込む前に各画像をベクター化して描き直す
（tools/vectorise.py を参照）。クロップに覆われていない領域はフル図版を
そのまま引き伸ばすことになるので、そこがぼける。ベクター化するとその
ぼけとギザギザが消え、--out-scale で好きな解像度に出せる。

    python3 tools/align_crops.py full.png out.png --crops a.png b.png \
        --scale 1.6 4.0 --vectorise --out-scale 4.0
"""
import argparse
import numpy as np
from PIL import Image, ImageFilter

import vectorise


def gray(im):
    return np.asarray(im.convert("L"), dtype=np.float32)


def ncc_at(P, Q, x, y):
    W = P[y:y + Q.shape[0], x:x + Q.shape[1]]
    if W.shape != Q.shape:
        return -2.0
    Wn, Qn = W - W.mean(), Q - Q.mean()
    den = (np.sqrt((Wn * Wn).sum()) + 1e-6) * (np.sqrt((Qn * Qn).sum()) + 1e-6)
    return float((Wn * Qn).sum() / den)


def search(plate, crop, scales, div, x0=None, y0=None, span=None):
    """div で間引いた解像度で総当たり。span を渡すと (x0,y0) 周辺だけ見る。"""
    P = gray(plate.resize((plate.width // div, plate.height // div), Image.LANCZOS))
    best = (-2.0, 0.0, 0, 0)
    for s in scales:
        w = max(8, int(round(crop.width / s / div)))
        h = max(8, int(round(crop.height / s / div)))
        Q = gray(crop.resize((w, h), Image.LANCZOS))
        if Q.shape[0] > P.shape[0] or Q.shape[1] > P.shape[1]:
            continue
        if span is None:
            xs = range(P.shape[1] - w + 1)
            ys = range(P.shape[0] - h + 1)
        else:
            xs = range(max(0, x0 // div - span), min(P.shape[1] - w, x0 // div + span) + 1)
            ys = range(max(0, y0 // div - span), min(P.shape[0] - h, y0 // div + span) + 1)
        for y in ys:
            for x in xs:
                sc = ncc_at(P, Q, x, y)
                if sc > best[0]:
                    best = (sc, float(s), x * div, y * div)
    return best


def feather(size, pad):
    m = Image.new("L", size, 0)
    m.paste(255, (pad, pad, size[0] - pad, size[1] - pad))
    return m.filter(ImageFilter.GaussianBlur(pad * 0.55))


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("full")
    ap.add_argument("dst")
    ap.add_argument("--crops", nargs="+", required=True)
    ap.add_argument("--scale", nargs=2, type=float, default=[1.6, 4.0])
    ap.add_argument("--step", type=float, default=0.1)
    ap.add_argument("--pad", type=int, default=26, help="貼り込みの縁をぼかす幅")
    ap.add_argument("--sharpen", type=int, default=55)
    ap.add_argument("--vectorise", action="store_true",
                    help="貼り込む前に各画像をベクター化して描き直す")
    ap.add_argument("--out-scale", type=float, default=0.0,
                    help="出力倍率（フル図版に対して）。0 でクロップの倍率に合わせる")
    ap.add_argument("--ink-thresh", type=int, default=26)
    args = ap.parse_args()

    plate = vectorise.load_rgb(args.full)
    print(f"full plate: {plate.size}")

    coarse = np.arange(args.scale[0], args.scale[1] + 1e-9, args.step)
    fits = []
    for path in args.crops:
        crop = Image.open(path).convert("RGB")
        sc, s, x, y = search(plate, crop, coarse, div=4)
        fine = np.arange(s - 0.14, s + 0.14 + 1e-9, 0.02)
        sc, s, x, y = search(plate, crop, fine, div=1, x0=x, y0=y, span=7)
        print(f"  {path}: scale={s:.3f} at ({x},{y}) ncc={sc:.3f}")
        fits.append((path, s, x, y))

    factor = args.out_scale or max(f[1] for f in fits)

    if args.vectorise:
        base = vectorise.restore(plate, factor, thresh=args.ink_thresh + 4,
                                 keep=0.74, binary_thresh=0.55, work_prefix="tools/work/_vec_plate")
    else:
        base = plate.resize((round(plate.width * factor), round(plate.height * factor)),
                            Image.LANCZOS)
        base = base.filter(ImageFilter.UnsharpMask(radius=2.0, percent=args.sharpen, threshold=3))

    for i, (path, s, x, y) in enumerate(fits):
        crop = Image.open(path).convert("RGB")
        if args.vectorise:
            # クロップも同じ縮尺（factor / s 倍）でベクターから描き直す
            c = vectorise.restore(crop, factor / s, thresh=args.ink_thresh,
                                  keep=0.78, work_prefix=f"tools/work/_vec_crop{i}")
        else:
            c = crop.resize((round(crop.width / s * factor), round(crop.height / s * factor)),
                            Image.LANCZOS)
        base.paste(c, (round(x * factor), round(y * factor)), feather(c.size, args.pad))

    base.save(args.dst)
    print(f"-> {args.dst}  {base.size}  (x{factor:.2f})")


if __name__ == "__main__":
    main()
