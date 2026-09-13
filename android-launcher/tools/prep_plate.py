#!/usr/bin/env python3
"""19 世紀の植物図版スキャンを、ランチャーが使える透過レイヤーに変換する。

やること:
  1. 縁から塗りつぶしていって「紙」の色の連結領域だけを透明にする
     （花の内側の白は連結していないので残る。単純な色しきい値だと白い花が消える）
  2. 余白を切り詰める
  3. 指定幅まで拡大し、軽くシャープをかける
  4. アルファの縁を 1px 削って、紙色のにじみ（ハロ）を落とす

使い方:
    python3 tools/prep_plate.py IN.png OUT.png --width 1400
    python3 tools/prep_plate.py IN.png OUT.png --crop 40,30,380,520 --cut-bottom 60
"""
import argparse
from PIL import Image, ImageDraw, ImageFilter, ImageChops

MAGIC = (255, 0, 255)


def sample_paper(im):
    """四隅の平均を紙の色とみなす。"""
    w, h = im.size
    pts = [(2, 2), (w - 3, 2), (2, h - 3), (w - 3, h - 3),
           (w // 2, 2), (w // 2, h - 3), (2, h // 2), (w - 3, h // 2)]
    px = [im.getpixel(p) for p in pts]
    return tuple(sum(c[i] for c in px) // len(px) for i in range(3))


def remove_paper(im, thresh):
    """縁から flood fill して紙の連結領域を抜く。"""
    work = im.copy()
    w, h = work.size
    seeds = [(0, 0), (w - 1, 0), (0, h - 1), (w - 1, h - 1)]
    seeds += [(x, 0) for x in range(0, w, 12)]
    seeds += [(x, h - 1) for x in range(0, w, 12)]
    seeds += [(0, y) for y in range(0, h, 12)]
    seeds += [(w - 1, y) for y in range(0, h, 12)]
    for s in seeds:
        if work.getpixel(s) == MAGIC:
            continue
        ImageDraw.floodfill(work, s, MAGIC, thresh=thresh)
    # MAGIC のところだけ透明にする
    alpha = Image.new("L", work.size, 255)
    ap = alpha.load()
    wp = work.load()
    for y in range(h):
        for x in range(w):
            if wp[x, y] == MAGIC:
                ap[x, y] = 0
    return alpha


def shrink_alpha(alpha, px=1):
    """アルファを内側へ px 収縮させ、紙色のふちを落とす。"""
    if px <= 0:
        return alpha
    return alpha.filter(ImageFilter.MinFilter(1 + px * 2))


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("src")
    ap.add_argument("dst")
    ap.add_argument("--crop", help="x,y,w,h  背景抜きの前に切り出す")
    ap.add_argument("--cut-bottom", type=int, default=0, help="下から削る px（刷られた説明文を落とす）")
    ap.add_argument("--thresh", type=int, default=26, help="紙とみなす色の許容差")
    ap.add_argument("--width", type=int, default=0, help="出力幅。0 で等倍")
    ap.add_argument("--erode", type=int, default=1)
    ap.add_argument("--sharpen", type=float, default=1.35)
    args = ap.parse_args()

    im = Image.open(args.src).convert("RGB")
    if args.crop:
        x, y, w, h = (int(v) for v in args.crop.split(","))
        im = im.crop((x, y, x + w, y + h))
    if args.cut_bottom:
        im = im.crop((0, 0, im.width, im.height - args.cut_bottom))

    paper = sample_paper(im)
    alpha = shrink_alpha(remove_paper(im, args.thresh), args.erode)

    out = im.convert("RGBA")
    out.putalpha(alpha)

    bbox = out.getbbox()
    if bbox:
        out = out.crop(bbox)

    if args.width and args.width != out.width:
        scale = args.width / out.width
        out = out.resize((args.width, round(out.height * scale)), Image.LANCZOS)
        if args.sharpen > 1.0:
            out = ImageChops.composite(
                out.filter(ImageFilter.UnsharpMask(radius=1.6, percent=int((args.sharpen - 1) * 100), threshold=2)),
                out, out.split()[3],
            )

    out.save(args.dst)
    print(f"paper={paper}  out={out.size}  -> {args.dst}")


if __name__ == "__main__":
    main()
