"""アプリが使う素材一式を書き出す。

    organs/<id>.webp   葉・花を 1 つずつ焼いたもの（透過つき）
    bloom/NN.webp      蕾が開く連続コマ
    manifest.json      茎の形、器官の付け根と揺れ、タップ判定

なぜ器官ごとに焼くのか:
    ハッチングは 1 枚の葉に 100 本以上引くので、毎フレーム描き直すには重すぎる。
    一度焼いてしまえば、あとは付け根を軸に回すだけで個別に動かせる。密度を
    落とさずに「葉が 1 枚ずつ羽ばたく」動きが作れる。

茎と、伸びていく蔦だけは形が毎フレーム変わるので、アプリ側で線を直に引く。

配置（layout.py）と描き方（render.py）は版面 plate.py と共有している。
以前はここに同じ計算を書き写していて、版面と実機で草の生え方がずれた。
"""
import json
import math
import os
import random
import shutil

from PIL import Image, ImageDraw, ImageFilter

import shapes as S
import leaves as L
import layout as Y
import palette as C
import render as R
from pencil import Pencil
from paperlib import clipped, fill_shape

OUT = "assets"
PAD = 16
BLOOM_FRAMES = 18
PAPER = C.PAPER


def canvas_for(geo, extra=(), pad=PAD):
    """器官を囲うカンバスと、版面座標からの原点を返す。"""
    pts = R.points(geo) + [p for poly in extra for p in poly]
    x0 = min(p[0] for p in pts) - pad
    y0 = min(p[1] for p in pts) - pad
    x1 = max(p[0] for p in pts) + pad
    y1 = max(p[1] for p in pts) + pad
    w, h = int(math.ceil(x1 - x0)), int(math.ceil(y1 - y0))
    return Image.new("RGBA", (w, h), (0, 0, 0, 0)), (x0, y0)


def mask_to(img, polys, lines=(), feather=0.7, line_w=5):
    """形の外を透明にする。器官の紙地はここで不透明に残る。
    花柄のような細いものは面を持たないので、線として太らせて拾う。"""
    mask = Image.new("L", img.size, 0)
    md = ImageDraw.Draw(mask)
    for poly in polys:
        md.polygon([tuple(p) for p in poly], fill=255)
    for ln in lines:
        md.line([tuple(p) for p in ln], fill=255, width=line_w)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    img.putalpha(mask)
    return img


def bake(organ, rng):
    """器官 1 つをスプライトに焼く。版面と同じ render.* を通す。"""
    geo, k = organ["geo"], organ["k"]
    extra = [organ["pedicel"]] if organ["kind"] == "flower" else []
    img, off = canvas_for(geo, extra)
    g = R.shift(geo, -off[0], -off[1])

    if organ["kind"] == "leaf":
        R.leaf(img, g, k, rng, organ["reach"])
        polys = [part["outline"] for part in g["parts"]]
        lines = [g[key] for key in ("stalk", "rachis") if key in g]
        return mask_to(img, polys, lines, line_w=6), off

    ped = R.shift(organ["pedicel"], -off[0], -off[1])
    R.flower(img, g, k, rng, organ["size"], ped)
    return mask_to(img, [g["outline"]], [ped], line_w=7), off


def bake_roots(rng):
    """根を 1 枚に焼く。土の中なので風では動かない。"""
    xs = [Y.sc(r[1]) for r in Y.ROOTS]
    ys = [Y.sc(r[2]) for r in Y.ROOTS]
    x0, y0 = min(xs) - Y.sc(240), min(ys) - Y.sc(20)
    x1, y1 = max(xs) + Y.sc(240), max(ys) + Y.sc(230)
    img = Image.new("RGBA", (int(x1 - x0), int(y1 - y0)), (0, 0, 0, 0))
    d = ImageDraw.Draw(img, "RGBA")
    R.roots(d, rng, (-x0, -y0))
    return img, (x0, y0)


def bake_vine(rng):
    """蔦の素材。アプリを開くときに伸びる蔓は押した場所から任意の向きへ
    伸びるので形は実行時に決まるが、そこにつく葉と先の花は焼いておける。

    参照図版の下半分にある「ツタバギキョウ（Campanula hederacea）」が
    そのまま手本になる。細い蔓が這い、蔦形の葉が互い違いにつき、先に
    釣鐘がさがっている。線だけを伸ばしても蔦には見えない。葉が要る。
    """
    out = {"leaves": [], "bloom": []}

    # 蔦の葉。大きさ違いを 3 枚焼いて、蔓の上で使い分ける。
    for i, ln in enumerate((Y.sc(108.0), Y.sc(86.0), Y.sc(66.0))):
        lf = L.lobed((0.0, 0.0), -90.0, ln, ln * 0.42, bend=0.0, lobes=5)
        img, off = canvas_for(lf, pad=10)
        g = R.shift(lf, -off[0], -off[1])
        R.leaf(img, g, 0.96, random.Random(rng.randrange(1 << 30)), ln)
        polys = [p["outline"] for p in g["parts"]]
        img = mask_to(img, polys, [g["stalk"]], line_w=5)
        name = f"vine/leaf{i}.webp"
        img.save(f"{OUT}/{name}", "WEBP", quality=92, method=6)
        out["leaves"].append({
            "image": name,
            "off": [round(off[0], 1), round(off[1], 1)],
            "reach": round(ln, 1),
        })

    # 蕾がほどけて咲くまで。中心を軸に置いたまま形だけ変える。
    size = Y.sc(92.0)
    for f in range(Y.VINE_BLOOM_FRAMES):
        o = f / (Y.VINE_BLOOM_FRAMES - 1)
        g = Y.vine_bloom((0.0, 0.0), -90.0, size, o)
        polys = [p["outline"] for p in g["petals"] + g["sepals"]]
        img, off = canvas_for(polys, pad=12)
        g2 = R.shift(g, -off[0], -off[1])
        R.vine_bloom(img, g2, random.Random(4000 + f), icon_hole=1.0)
        img = mask_to(img, [p["outline"] for p in g2["petals"] + g2["sepals"]],
                      feather=0.8)
        name = f"vine/bloom{f:02d}.webp"
        img.save(f"{OUT}/{name}", "WEBP", quality=92, method=6)
        out["bloom"].append({
            "image": name,
            "off": [round(off[0], 1), round(off[1], 1)],
        })
    out["size"] = size
    return out


def main():
    rng = random.Random(17)
    if os.path.isdir(OUT):
        shutil.rmtree(OUT)
    os.makedirs(f"{OUT}/organs", exist_ok=True)
    os.makedirs(f"{OUT}/bloom", exist_ok=True)
    os.makedirs(f"{OUT}/vine", exist_ok=True)

    manifest = {
        "plate": {"width": Y.W, "height": Y.H, "paper": "#F0EBE2",
                  "caption": Y.CAPTION, "plateNo": Y.PLATE_NO},
        "stems": [], "organs": [], "gemma": None, "roots": None, "vine": None,
    }
    z = 0

    for stem in Y.build(17):
        p0, c1, c2, tip = stem["curve"]
        manifest["stems"].append({
            "id": stem["id"],
            "p": [list(p0), list(c1), list(c2), list(tip)],
            "w0": stem["w0"], "w1": stem["w1"],
            "tone": round(stem["k"], 3), "depth": stem["depth"],
            "sway": stem["sway"],
        })

        for o in stem["organs"]:
            img, off = bake(o, random.Random(rng.randrange(1 << 30)))
            name = o["id"]
            img.save(f"{OUT}/organs/{name}.webp", "WEBP", quality=92, method=6)
            z += 1
            manifest["organs"].append({
                "id": name, "stem": stem["id"], "t": round(o["t"], 4),
                "image": f"organs/{name}.webp",
                "off": [round(off[0], 1), round(off[1], 1)],
                "pivot": [round(o["pivot"][0], 1), round(o["pivot"][1], 1)],
                "restAngle": round(o["restAngle"], 2),
                "sway": o["sway"],
                "hit": ({"at": [round(o["hit"]["at"][0], 1),
                                round(o["hit"]["at"][1], 1)],
                         "r": round(o["hit"]["r"], 1)} if o["hit"] else None),
                "z": z,
            })

    # --- アプリ一覧を開く蕾。いちばん高い株の先端につける ---
    host = manifest["stems"][0]
    hp = [tuple(v) for v in host["p"]]
    at = S.cubic_at(*hp, 1.0)
    ax = S.cubic_angle(*hp, 1.0)
    # 蔦の先の蕾と同じ作り方で焼く。アプリ一覧を開く蕾も、咲き方は同じ。
    size = Y.sc(146.0)
    frames = []
    for f in range(BLOOM_FRAMES):
        o = f / (BLOOM_FRAMES - 1)
        g = Y.vine_bloom(at, ax, size, o)
        polys = [p["outline"] for p in g["petals"] + g["sepals"]]
        img, off = canvas_for(polys, pad=14)
        g2 = R.shift(g, -off[0], -off[1])
        R.vine_bloom(img, g2, random.Random(991 + f))
        img = mask_to(img, [p["outline"] for p in g2["petals"] + g2["sepals"]],
                      feather=0.8)
        fn = f"bloom/{f:02d}.webp"
        img.save(f"{OUT}/{fn}", "WEBP", quality=92, method=6)
        frames.append({"image": fn, "off": [round(off[0], 1), round(off[1], 1)]})
    manifest["gemma"] = {
        "id": "gemma", "stem": host["id"], "t": 1.0,
        "pivot": [round(at[0], 1), round(at[1], 1)],
        "restAngle": round(ax, 2),
        "frames": frames,
        "hit": {"at": [round(S.polar(at, ax, size * 0.6)[0], 1),
                       round(S.polar(at, ax, size * 0.6)[1], 1)],
                "r": round(size * 0.95, 1)},
        "sway": {"amp": 9.0, "speed": 6, "phase": 0.7},
    }

    manifest["vine"] = bake_vine(random.Random(5150))

    # 根はほとんど動かないので 1 枚に焼く。標本画は根まで描くのが約束。
    img, off = bake_roots(random.Random(4242))
    img.save(f"{OUT}/roots.webp", "WEBP", quality=92, method=6)
    manifest["roots"] = {"image": "roots.webp",
                         "off": [round(off[0], 1), round(off[1], 1)]}

    # 判定の円が重ならないところまで詰める。重なると「近い方」任せになり、
    # 狙ったのと違う部位が開く。蕾も含めて一度に均す。
    hits = [o["hit"] for o in manifest["organs"] if o["hit"]] + [manifest["gemma"]["hit"]]
    for h in hits:
        h["at"] = tuple(h["at"])
    Y.separate(hits)
    for h in hits:
        h["at"] = [round(h["at"][0], 1), round(h["at"][1], 1)]
        h["r"] = round(h["r"], 1)

    with open(f"{OUT}/manifest.json", "w", encoding="utf-8") as f:
        json.dump(manifest, f, ensure_ascii=False, indent=1)

    total = sum(os.path.getsize(os.path.join(dp, f))
                for dp, _, fs in os.walk(OUT) for f in fs)
    n_hit = sum(1 for o in manifest["organs"] if o["hit"])
    rs = [h["r"] for h in hits]
    print(f"organs={len(manifest['organs'])} (tappable {n_hit}+1, "
          f"半径 {min(rs):.0f}〜{max(rs):.0f}px)  "
          f"stems={len(manifest['stems'])}  bloom={BLOOM_FRAMES}  "
          f"vine={len(manifest['vine']['leaves'])}葉/"
          f"{len(manifest['vine']['bloom'])}コマ  "
          f"total={total//1024} KB")


if __name__ == "__main__":
    import time
    t0 = time.time()
    main()
    print(f"{time.time()-t0:.1f}s")
