"""器官を 1 つ描く手順。版面（plate.py）とスプライト（build_assets.py）は
同じこの関数を呼ぶ。座標をずらしたいときは shift() で幾何ごと平行移動する。

色が無い以上、面と陰影はハッチング（線の密度）で作るしかない。
銅版画の線描と同じで、

  ・葉は密にハッチングして濃く、花は疎にして明るく残す
  ・輪郭際と稜線に沿って局所的に落とす（半分をべた塗りすると板になる）
  ・手前のものは紙色で伏せて後ろを隠す（透けると線の網になる）
"""
import math
import random

from PIL import ImageDraw, ImageFont

import shapes as S
from pencil import Pencil
from paperlib import clipped, smudge, fill_shape, edge_shade, crease
import layout as Y
from layout import LIGHT, GRAPHITE


def shift(o, dx, dy):
    """点・折れ線・辞書をまとめて平行移動する。"""
    if isinstance(o, dict):
        return {k: shift(v, dx, dy) for k, v in o.items()}
    if isinstance(o, (list, tuple)):
        if len(o) == 2 and all(isinstance(v, (int, float)) for v in o):
            return (o[0] + dx, o[1] + dy)
        return [shift(v, dx, dy) for v in o]
    return o


def points(o, out=None):
    """幾何に含まれる全ての点。外接矩形を採るのに使う。"""
    out = [] if out is None else out
    if isinstance(o, dict):
        for v in o.values():
            points(v, out)
    elif isinstance(o, (list, tuple)):
        if len(o) == 2 and all(isinstance(v, (int, float)) for v in o):
            out.append(o)
        else:
            for v in o:
                points(v, out)
    return out


def _hatcher(img, rng):
    def hatch(poly, curves, tone, width=1.15, span=(0.08, 0.95), lo=0.42,
              weights=None):
        """[lo] は明るい側に残す調子。0 にすると紙のまま白く抜けて平板になる。"""
        clipped(img, poly, lambda dd: Pencil(dd, random.Random(rng.randrange(1 << 30)),
                                             GRAPHITE)
                .hatch_curves(curves, tone=tone, width=width, span=span,
                              gradient=(LIGHT + 180, lo, 1.0), weights=weights))
    return hatch


def cylinder(n, axis_deg, light_deg=LIGHT, floor=0.44, peak=1.30):
    """筒の丸みを表す、線 1 本ごとの濃さ。

    光の向きの一次勾配だけでは筒が平らな板に見える。円筒は「両方の
    シルエット側が暗く、光の当たる一筋だけが明るい」。その一筋の位置は
    面の法線が光と向き合うところ。
    """
    # u=0 の縁の法線は axis-90、u=1 の縁は axis+90。その間を線形に回る。
    rel = ((light_deg - (axis_deg - 90)) % 360.0) / 180.0
    ul = min(max(rel if rel <= 1.0 else (0.0 if rel > 1.5 else 1.0), 0.06), 0.94)
    out = []
    for i in range(n):
        u = (i + 0.5) / n
        e = abs(u - ul) / max(ul, 1.0 - ul)
        out.append(floor + (peak - floor) * e ** 1.35)
    return out


def stem(img, curve, w0, w1, k, rng):
    """茎。太い 1 本の線で引くと黒い棒になる。図版の茎は筒として、
    輪郭 2 本のあいだに縦の調子を入れて描かれている。陰の側の輪郭だけ
    太く濃くすると、それだけで丸みが出る。"""
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, GRAPHITE)

    def half(t):
        return (w0 - (w0 - w1) * t) * 0.5

    left = S.offset(curve, 90, half)
    right = S.offset(curve, -90, half)
    fill_shape(img, left + right[::-1])
    # 内側の縦の調子。左上からの光なので右下側を濃く。
    for f, tone in ((0.62, 0.30), (0.18, 0.18), (-0.34, 0.10)):
        p.stroke(S.offset(curve, -90, lambda t, f=f: half(t) * f),
                 width=0.9, tone=tone * k, jitter=0.8, passes=1, taper=(0.1, 0.3))
    p.stroke(right, width=1.7, tone=1.02 * k, jitter=0.7, passes=1, taper=(0.03, 0.12))
    p.stroke(left, width=1.2, tone=0.56 * k, jitter=0.7, passes=1, taper=(0.03, 0.12))


def leaf(img, lf, k, rng, reach):
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, GRAPHITE)
    hatch = _hatcher(img, rng)
    if "stalk" in lf:
        p.stroke(lf["stalk"], width=2.2, tone=0.6 * k, jitter=0.5)
    if "rachis" in lf:
        p.stroke(lf["rachis"], width=2.4, tone=0.66 * k, jitter=0.5)
    for part in lf["parts"]:
        o = part["outline"]
        smudge(img, o, tone=0.15 * k, blur=10, shift=(8, 11))
        fill_shape(img, o)
        hatch(o, part["flow"], 1.62 * k, width=0.8, span=(0.02, 1.0), lo=0.24)
        # 二度彫り。向きを変えて薄く重ね、線の間を埋める
        hatch(o, part["cross"], 0.66 * k, width=0.8, span=(0.06, 0.96), lo=0.18)
        edge_shade(img, o, GRAPHITE, alpha=int(122 * k),
                   band=max(7.0, reach * 0.10), gradient_deg=LIGHT + 180)
        crease(img, part["midrib"], GRAPHITE, alpha=int(66 * k),
               width=max(3.0, reach * 0.024))
        p.weighted_contour(o, LIGHT, 0.26 * k, 1.12 * k, 1.7)
        p.stroke(part["midrib"], width=1.3, tone=0.58 * k, jitter=0.5)
        for v in part["veins"]:
            p.stroke(v, width=0.8, tone=0.38 * k, jitter=0.5, passes=1)


def flower(img, g, k, rng, size, pedicel=None):
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, GRAPHITE)
    hatch = _hatcher(img, rng)

    if pedicel is not None:
        p.stroke(pedicel, width=2.1, tone=0.52 * k, jitter=0.6)

    shape = g["outline"]
    smudge(img, shape, tone=0.15 * k, blur=9, shift=(7, 9))
    fill_shape(img, shape)

    if g["form"] == "bud":
        base, face, length = g["base"], g["axis"], g["length"]
        hatch(shape, g["ridges"], 1.30 * k, width=0.8, span=(0.04, 0.98), lo=0.34)
        edge_shade(img, shape, GRAPHITE, alpha=int(112 * k),
                   band=size * 0.12, gradient_deg=LIGHT + 180)
        p.weighted_contour(shape, LIGHT, 0.30 * k, 1.08 * k, 1.6)
        for r in g["ridges"]:
            p.stroke(r, width=1.0, tone=0.44 * k, jitter=0.5, passes=1)
        _calyx(img, p, base, face, size, k)
        return

    # 花は葉より疎に彫って明るく残す。色が無くても花が前に出る。
    w = cylinder(len(g["flow"]), g["axis"])
    # 根元から引き切ると線が一点に集まって傘の骨に見える。手前を切る。
    hatch(shape, g["flow"], 0.95 * k, width=0.8, span=(0.24, 1.0), lo=0.62, weights=w)
    hatch(shape, g["cross"], 0.26 * k, width=0.8, span=(0.12, 0.90), lo=0.26)

    # 筒の内側。ここを紙のまま残すと口に白い穴が空いて造花に見える。
    # 覗き込む面はいちばん暗い。図版の釣鐘花はここで立体が決まる。
    throat = g.get("throat")
    if throat:
        fill_shape(img, throat)
        smudge(img, throat, tone=0.52 * k, blur=size * 0.05)
        # 奥ほど暗い。線を向こう側の縁から引いて途中で止めると、
        # 濃さが奥に寄って「覗き込んでいる」深さが出る。
        hatch(throat, g["throat_flow"], 1.80 * k, width=0.9,
              span=(0.0, 0.60), lo=0.86)
        hatch(throat, g["throat_flow"], 1.10 * k, width=0.9,
              span=(0.0, 0.88), lo=0.80)
        hatch(throat, g["throat_flow"][::3], 0.70 * k, width=0.9,
              span=(0.0, 1.0), lo=0.80)
        # 向こう側の縁は中ほどだけ。端まで引くと口が横棒で塞がれて見える。
        n = len(g["far"])
        p.stroke(g["far"][int(n * 0.24):int(n * 0.76)], width=1.0,
                 tone=0.42 * k, jitter=0.7, passes=1, taper=(0.5, 0.5))
        # 手前の縁。ここを強く引くと口が「開いている」ことが一目で分かる。
        p.stroke(g["near"], width=1.9, tone=0.92 * k, jitter=0.7, passes=1)
        for sn in g.get("sinus", []):
            p.stroke(sn, width=1.3, tone=0.66 * k, jitter=0.6, passes=1,
                     taper=(0.05, 0.7))

    edge_shade(img, shape, GRAPHITE, alpha=int(100 * k),
               band=size * 0.13, gradient_deg=LIGHT + 180)
    for r in g["ribs"]:
        crease(img, r, GRAPHITE, alpha=int(44 * k), width=max(2.5, size * 0.035))
        p.stroke(r, width=1.0, tone=0.40 * k, jitter=0.6, passes=1)
    p.weighted_contour(shape, LIGHT, 0.28 * k, 1.06 * k, 1.6)

    # 雌しべ。口から少し出て、先が 3 裂する。図版はここまで描く。
    if g.get("open", 0) > 0.8:
        mouth, face = g["mouth"], g["axis"]
        st = S.polar(mouth, face, size * 0.34)
        p.stroke([S.polar(mouth, face, -size * 0.1), st], width=1.6,
                 tone=0.70 * k, jitter=0.5, passes=1)
        for a in (-34, 0, 34):
            p.stroke(S.quad(st, S.polar(st, face + a, size * 0.07),
                            S.polar(st, face + a * 1.5, size * 0.13), 6),
                     width=1.5, tone=0.72 * k, jitter=0.5, passes=1)

    _calyx(img, p, g.get("base", g["mouth"]), g["axis"], size, k, out=True)


def _calyx(img, p, base, face, size, k, out=False):
    """萼。5 枚の細い裂片が筒の付け根から反り返る。無いと花が茎に刺さって見える。"""
    for i in range(5):
        a = face + (i - 2) * 26
        ln = size * (0.40 if out else 0.34)
        tipp = S.polar(base, a + (12 if out else 0) * (1 if i > 2 else -1), ln)
        mid = S.polar(base, a, ln * 0.55)
        p.stroke(S.quad(base, mid, tipp, 10), width=1.6, tone=0.62 * k,
                 jitter=0.6, passes=1, taper=(0.05, 0.55))


def roots(d, rng, off=(0.0, 0.0)):
    """根。標本画は根まで描く。太い主根から細根が枝分かれする。"""
    p = Pencil(d, rng, GRAPHITE)
    for sid, bx, by, n in Y.ROOTS:
        bx, by = bx + off[0], by + off[1]
        for i in range(n):
            a = 60 + (i - (n - 1) / 2) * (150 / max(n - 1, 1)) + rng.uniform(-8, 8)
            ln = rng.uniform(70, 190)
            end = S.polar((bx, by), a, ln)
            mid = S.polar((bx, by), a + rng.uniform(-22, 22), ln * 0.55)
            spine = S.quad((bx, by), mid, end, 10)
            p.stroke(spine, width=lambda t: 3.4 - 2.6 * t, tone=0.72,
                     jitter=1.1, passes=1, taper=(0.05, 0.5))
            for j in range(2):
                on = spine[int((0.45 + 0.3 * j) * 10)]
                sp = S.polar(on, a + rng.choice([-52, 52]), rng.uniform(26, 54))
                p.stroke(S.quad(on, S.polar(on, a, 14), sp, 6),
                         width=1.2, tone=0.46, jitter=0.8, passes=1, taper=(0.1, 0.6))


SERIF_IT = "/usr/share/fonts/truetype/liberation/LiberationSerif-Italic.ttf"
SERIF = "/usr/share/fonts/truetype/liberation/LiberationSerif-Regular.ttf"


def caption(d, rng):
    """活字ではなく銅版の彫り文字に見せたいので、わずかに揺らして二度置く。"""
    try:
        f = ImageFont.truetype(SERIF_IT, 46)
        fs = ImageFont.truetype(SERIF, 26)
    except OSError:
        return
    for text, font, cy, sp in ((Y.CAPTION, f, 1668, 3), (Y.PLATE_NO, fs, 1726, 6)):
        w = d.textlength(text, font=font) + sp * (len(text) - 1)
        x = (Y.W - w) / 2
        for ch in text:
            for dx, dy, a in ((0, 0, 150), (0.7, 0.6, 70)):
                d.text((x + dx + rng.uniform(-0.5, 0.5),
                        cy + dy + rng.uniform(-0.7, 0.7)),
                       ch, font=font, fill=GRAPHITE + (a,))
            x += d.textlength(ch, font=font) + sp
