"""色を抜いた植物図版。

線の階層（版面幅 1100px での目安）:

    茎の根元   13px → 先端 3px   もっとも太い。3 回重ねて芯を作る
    葉の輪郭   1.5px             細く。太いと塗り絵の線になる
    主脈       1.3px
    側脈       0.8px
    ハッチング 0.85px を 34 本    調子はここが作る。本数を惜しむと平板になる


参照した図版は紙が埋まるほど描き込まれている。色が無いぶん、面と陰影は
ハッチング（線の密度）で作るしかない。銅版画・石版画の線描と同じ考え方。

  ・葉は密にハッチングして濃く、花は疎にして明るく残す
  ・輪郭際と稜線に沿って局所的に落とす（半分をべた塗りすると板になる）
  ・手前のものは紙色で伏せて後ろを隠す（透けると線の網になる）
"""
import math
import random
from PIL import ImageDraw
import shapes as S
import leaves as L
from pencil import Pencil
from paperlib import paper, clipped, bbox, smudge, fill_shape, edge_shade, crease

W, H = 1100, 1800
LIGHT = -128                    # 光は左上から
GRAPHITE = (66, 63, 68)

# id, 根元x, 根元y, 角度, 茎長, 反り, 奥行, 花序, 花数, 葉数, 葉長, 葉の型
STEMS = [
    ("a", 195, 1640, -78, 1060, -0.44, 0.34, "spike",  7,  7, 290, "ovate"),
    ("c", 545, 1730, -91, 1540, -0.20, 0.90, "umbel",  6,  5, 430, "pinnate"),
    ("d", 860, 1690, -99, 1280,  0.38, 0.68, "spike",  7,  8, 310, "lance"),
    ("b", 370, 1700, -86, 1360,  0.26, 0.56, "bell",   5,  8, 330, "lance"),
    ("e", 985, 1630, -104, 980,  0.46, 0.30, "bell",   4,  6, 280, "ovate"),
    ("f", 215, 1748, -66,  520, -0.68, 0.18, "none",   0,  3, 255, "lobed"),
    ("g", 775, 1752, -114, 560,  0.64, 0.22, "none",   0,  3, 235, "lobed"),
    ("h", 455, 1752, -84,  470, -0.30, 0.14, "umbel",  3,  3, 220, "pinnate"),
]


def stem_curve(base, angle, length, bow):
    tip = S.polar(base, angle, length)
    c1 = S.polar(base, angle + bow * 20, length * 0.36)
    c2 = S.polar(base, angle + bow * 42, length * 0.72)
    return base, c1, c2, tip


def draw(img, seed=17):
    d = ImageDraw.Draw(img, "RGBA")
    rng = random.Random(seed)
    organs = []

    def sub():
        return random.Random(rng.randrange(1 << 30))

    def hatch(poly, curves, tone, width=1.15, span=(0.08, 0.95), lo=0.42):
        """[lo] は明るい側に残す調子。0 にすると紙のまま白く抜けて平板になる。"""
        clipped(img, poly, lambda dd: Pencil(dd, sub(), GRAPHITE)
                .hatch_curves(curves, tone=tone, width=width, span=span,
                              gradient=(LIGHT + 180, lo, 1.0)))

    for sid, bx, by, angle, length, bow, depth, kind, nflo, nleaf, leaflen, leafkind in STEMS:
        p = Pencil(d, rng, GRAPHITE)
        k = 0.52 + 0.48 * depth                 # 奥ほど淡い
        p0, c1, c2, tip = stem_curve((bx, by), angle, length, bow)
        curve = S.cubic(p0, c1, c2, tip, 46)

        # 茎は 2 回重ねて芯を作る。ぶれを大きくすると線が二重に割れて見える。
        for wmul, tmul, jit in [(1.0, 0.98, 0.9), (0.52, 0.58, 1.5)]:
            p.stroke(curve,
                     width=lambda t, m=wmul: (12.0 - 8.6 * t) * m * (0.5 + 0.5 * depth),
                     tone=tmul * k, jitter=jit, passes=1, taper=(0.03, 0.12))

        # 葉。下ほど大きく、交互につく。型は株ごとに変える。
        for i in range(nleaf):
            t = 0.05 + 0.72 * (i / max(nleaf - 1, 1)) + rng.uniform(-0.02, 0.02)
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if i % 2 == 0 else 1
            ll = leaflen * (1.0 - 0.50 * (i / max(nleaf - 1, 1))) * rng.uniform(0.9, 1.1)
            ll *= 0.62 + 0.38 * depth
            # 向きを大きく振る。揃えると壁紙に見える。
            lf = L.make(leafkind, at, ax + side * (52 + rng.uniform(-26, 30)),
                        ll, ll * 0.31, bend=side * rng.uniform(0.15, 0.55))
            # 葉ごとに濃さを振る。揃えると平板になる。
            lk = k * rng.uniform(0.72, 1.18)
            if "stalk" in lf:
                p.stroke(lf["stalk"], width=2.2, tone=0.6 * k, jitter=0.5)
            if "rachis" in lf:
                p.stroke(lf["rachis"], width=2.4, tone=0.66 * k, jitter=0.5)
            for part in lf["parts"]:
                o = part["outline"]
                smudge(img, o, tone=0.24 * lk, blur=10, shift=(8, 11))
                fill_shape(img, o)
                hatch(o, part["flow"], 1.45 * lk, width=0.8, span=(0.02, 1.0))
                # 二度彫り。向きを変えて薄く重ね、線の間を埋める
                hatch(o, part["cross"], 0.62 * lk, width=0.8, span=(0.06, 0.96), lo=0.30)
                edge_shade(img, o, GRAPHITE, alpha=int(104 * lk),
                           band=max(7.0, ll * 0.10), gradient_deg=LIGHT + 180)
                crease(img, part["midrib"], GRAPHITE, alpha=int(66 * lk),
                       width=max(3.0, ll * 0.024))
                p.weighted_contour(o, LIGHT, 0.30 * lk, 0.88 * lk, 1.5)
                p.stroke(part["midrib"], width=1.3, tone=0.58 * lk, jitter=0.5)
                for v in part["veins"]:
                    p.stroke(v, width=0.8, tone=0.38 * lk, jitter=0.5, passes=1)
            if i < 3 and depth > 0.3:
                organs.append((f"{sid}.leaf{i}", lf["anchor"], lf["reach"] * 0.30))

        if kind == "none":
            continue

        # 花序
        for j in range(nflo):
            u = j / max(nflo - 1, 1)
            t = 0.70 + 0.30 * u
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if j % 2 == 0 else 1
            size = leaflen * 0.34 * (1.0 - 0.30 * u) * rng.uniform(0.9, 1.1)
            ped = size * 0.5
            hang = S.polar(at, ax + side * 40, ped)
            p.stroke(S.quad(at, S.polar(at, ax + side * 40, ped * 0.5), hang, 8),
                     width=2.6 * (0.7 + 0.3 * depth), tone=0.66 * k, jitter=0.6)

            if kind == "bell":
                fl = S.bell(hang, ax + (90 - ax) * 0.72, size * 1.25, size)
                shape, flow, cross = fl["outline"], fl["flow"], fl["cross"]
            elif kind == "spike":
                fl = S.star(S.polar(hang, ax + side * 40, size * 0.4), ax - 90, size * 0.82)
                shape, flow, cross = fl["outline"], fl["ribs"], None
            else:                                   # umbel（散形花序）
                centre = S.polar(hang, ax, size * 0.34)
                rays = 9
                for a in range(rays):
                    ang = ax - 90 + (a - (rays - 1) / 2) * 20
                    end = S.polar(centre, ang, size * 0.70)
                    p.stroke(S.quad(centre, S.polar(centre, ang, size * 0.36), end, 6),
                             width=1.5, tone=0.55 * k, jitter=0.4, passes=1)
                    head = S.star(end, ang, size * 0.16, lobes=5)
                    fill_shape(img, head["outline"])
                    p.weighted_contour(head["outline"], LIGHT, 0.3 * k, 0.8 * k, 1.4)
                organs.append((f"{sid}.flos{j}", centre, size * 0.7))
                continue
            smudge(img, shape, tone=0.14 * k, blur=9, shift=(7, 9))
            fill_shape(img, shape)
            # 花は疎に。葉より明るく残すことで、色なしでも花が浮く。
            hatch(shape, flow, 1.55 * k, width=0.8, lo=0.34)
            if cross:
                hatch(shape, cross, 0.58 * k, width=0.8, lo=0.28)
            edge_shade(img, shape, GRAPHITE, alpha=int(108 * k),
                       band=size * 0.14, gradient_deg=LIGHT + 180)
            p.weighted_contour(shape, LIGHT, 0.30 * k, 0.88 * k, 1.5)
            if kind == "umbel":
                for a in range(0, 360, 24):
                    centre = S.polar(hang, ax, size * 0.3)
                    p.stroke(S.quad(centre, S.polar(centre, a, size * 0.3),
                                    S.polar(centre, a, size * 0.6), 6),
                             width=1.2, tone=0.5 * k, jitter=0.5, passes=1)
            organs.append((f"{sid}.flos{j}", hang, size * 0.7))

    return organs


if __name__ == "__main__":
    import time
    t0 = time.time()
    img = paper((W, H), base=(246, 243, 236), tooth=6.0)
    organs = draw(img)
    out = img.convert("RGB")
    out.save("plate_full.png")
    out.resize((W // 2, H // 2)).save("plate_view.png")
    print(f"{time.time()-t0:.1f}s  organs={len(organs)}")
