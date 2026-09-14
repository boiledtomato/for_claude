"""色を抜いた植物図版の版面。配置は layout.py、器官の描き方は render.py。
ここは紙・茎・根・図版名だけを持つ。

線の階層（版面幅 1100px での目安）:

    茎の根元   12px → 先端 3.4px  もっとも太い。2 回重ねて芯を作る
    葉の輪郭   1.5px              細く。太いと塗り絵の線になる
    主脈       1.3px
    側脈       0.8px
    ハッチング 0.8px を 90 本     調子はここが作る。本数を惜しむと平板になる
"""
import random

from PIL import ImageDraw

import shapes as S
import layout as Y
import render as R
from pencil import Pencil
from paperlib import paper

W, H, LIGHT, GRAPHITE = Y.W, Y.H, Y.LIGHT, Y.GRAPHITE
stem_curve = Y.stem_curve
STEMS, ROOTS, CAPTION = Y.STEMS, Y.ROOTS, Y.CAPTION

def draw(img, seed=17):
    d = ImageDraw.Draw(img, "RGBA")
    rng = random.Random(seed)
    hits = []

    for stem in Y.build(seed):
        R.stem(img, S.cubic(*stem["curve"], 46), stem["w0"], stem["w1"],
               stem["k"], random.Random(rng.randrange(1 << 30)))

        for o in stem["organs"]:
            sub = random.Random(rng.randrange(1 << 30))
            if o["kind"] == "leaf":
                R.leaf(img, o["geo"], o["k"], sub, o["reach"])
            else:
                R.flower(img, o["geo"], o["k"], sub, o["size"], o["pedicel"])
            if o["hit"]:
                hits.append((o["id"], o["hit"]["at"], o["hit"]["r"]))

    R.roots(d, rng)
    R.caption(d, rng)
    return hits


if __name__ == "__main__":
    import time
    t0 = time.time()
    img = paper((W, H), base=(246, 243, 236), tooth=6.0)
    hits = draw(img)
    out = img.convert("RGB")
    out.save("plate_full.png")
    out.resize((W // 2, H // 2)).save("plate_view.png")
    print(f"{time.time()-t0:.1f}s  tappable={len(hits)}")
