"""器官を 1 つ描く手順。版面（plate.py）とスプライト（build_assets.py）は
同じこの関数を呼ぶ。座標をずらしたいときは shift() で幾何ごと平行移動する。

技法は多色石版の淡彩であって、銅版画の線描ではない。参照した図版を見ると、

  ・細く確かな輪郭線が一本あるだけ。線は輪郭と脈にしか使わない
  ・内側は面で色を置く。濃淡も面で、同系色の濃い側を重ねて作る
  ・脈は地より濃い同系色で、花は裂片の先へ放射、葉は主脈から縁へ
  ・いちばん明るいところは紙の白をそのまま残す
  ・葯の黄が一点入る。この黄がないと、青と緑だけで図版に見えない

以前はハッチングで濃淡を作っていた。密度は出るが、線が増えるほど図版の
「軽さ」から離れていく。石版は色を塗って線を一本引いた絵である。
"""
import math
import random

from PIL import ImageDraw, ImageFont

import shapes as S
import palette as C
from pencil import Pencil
from paperlib import clipped, smudge, fill_shape, edge_shade, crease, wash, mottle
import layout as Y
from layout import LIGHT


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


# ---------------------------------------------------------------- 淡彩の置き方

def lay(img, poly, hi, mid, deep, light=LIGHT, strength=1.0, seed=7,
        flow=None, rng=None, edge=1.0):
    """面に色を置く。地を敷き、濃い側を光と逆から重ね、最後にむらを置く。

    単色をべた塗りしただけでは色紙を切り貼りしたように見える。濃い側を
    方向つきで重ねて面に向きを与え、さらにむらで刷りの不均一さを出す。
    地をわずかに透かすのは、紙の目を殺さないため。
    """
    # 重ねすぎない。実測した「中」の色より暗くなったら塗りすぎで、
    # 図版の明るさが消える。濃い側は面の 3 割ほどに効けば足りる。
    fill_shape(img, poly, color=hi, alpha=246, feather=0.6)
    wash(img, poly, mid, alpha=int(150 * strength), gradient_deg=light + 180)
    wash(img, poly, deep, alpha=int(84 * strength), gradient_deg=light + 180)
    # 縁のきわだけ落とす。半分を塗ると板になる。
    # 尖った形では、縁の帯が先端をまるごと覆ってしまう。花弁のように
    # 先が尖るものは [edge] を下げる。下げないと先端に黒い楔が残る。
    if edge > 0.02:
        edge_shade(img, poly, C.shade(deep, 0.30), alpha=int(84 * strength * edge),
                   band=_band(poly), gradient_deg=light + 180)
    mottle(img, poly, C.shade(deep, 0.20), alpha=int(70 * strength),
           seed=seed, scale=_band(poly, 0.40) + 8)
    mottle(img, poly, C.tint(hi, 0.70), alpha=int(62 * strength),
           seed=seed + 101, scale=_band(poly, 0.62) + 12)
    # 面の流れに沿った色の筆致。均一な塗りはベクタ画像に見える。手彩色は
    # 必ず形の流れ（花なら稜、葉なら側脈）に沿って刷毛目が残る。
    if flow:
        r2 = rng or random.Random(seed)
        clipped(img, poly, lambda dd: Pencil(dd, random.Random(r2.randrange(1 << 30)),
                                             C.shade(deep, 0.18))
                .hatch_curves(flow, tone=0.30 * strength, width=max(1.6, _band(poly, 0.12)),
                              jitter=0.8, span=(0.05, 0.98),
                              gradient=(light + 180, 0.0, 1.0), cross_at=9.0))


def _band(poly, frac=0.13):
    xs = [p[0] for p in poly]
    ys = [p[1] for p in poly]
    return max(3.0, min(max(xs) - min(xs), max(ys) - min(ys)) * frac)


def ink(d, rng, pts, width=1.3, tone=0.92, jitter=0.5, color=C.INK, closed=False,
        taper=(0.06, 0.06)):
    """輪郭線。石版の線は細く、迷いがなく、太さが少しだけ揺れる。"""
    p = Pencil(d, rng, color)
    seq = list(pts) + [pts[0]] if closed else pts
    p.stroke(seq, width=width, tone=tone, jitter=jitter, passes=1,
             taper=taper, grain=0.14)


def vein(d, rng, curves, color, width=0.9, tone=0.5):
    p = Pencil(d, rng, color)
    for c in curves:
        p.stroke(c, width=width, tone=tone, jitter=0.45, passes=1, taper=(0.1, 0.5),
                 grain=0.2)


# ------------------------------------------------------------------------ 茎

def stem(img, curve, w0, w1, k, rng):
    """茎。輪郭 2 本のあいだを緑で埋めた筒。太い線 1 本で引くと棒になる。"""
    d = ImageDraw.Draw(img, "RGBA")

    def half(t):
        return (w0 - (w0 - w1) * t) * 0.5

    left = S.offset(curve, 90, half)
    right = S.offset(curve, -90, half)
    body = left + right[::-1]
    lay(img, body, C.tint(C.STEM, 0.30), C.STEM, C.STEM_DEEP, strength=0.9)
    ink(d, rng, right, width=1.25, tone=0.86 * k, jitter=0.45)
    ink(d, rng, left, width=1.0, tone=0.62 * k, jitter=0.45)


# ------------------------------------------------------------------------ 葉

def leaf(img, lf, k, rng, reach):
    d = ImageDraw.Draw(img, "RGBA")
    if "stalk" in lf:
        ink(d, rng, lf["stalk"], width=1.8, tone=0.7, color=C.STEM_DEEP)
    if "rachis" in lf:
        ink(d, rng, lf["rachis"], width=1.8, tone=0.7, color=C.STEM_DEEP)

    for part in lf["parts"]:
        o = part["outline"]
        smudge(img, o, tone=0.10, blur=11, shift=(7, 10), color=C.GREEN_SHADE)
        lay(img, o, C.tint(C.GREEN_HI, 0.30), C.GREEN, C.GREEN_DEEP,
            strength=k, seed=int(o[0][0]) & 255, flow=part["flow"][::3], rng=rng)
        # 主脈の両脇をわずかに明るく。葉の折れが出る。
        crease(img, part["midrib"], C.tint(C.GREEN_HI, 0.55),
               alpha=int(96 * k), width=max(3.0, reach * 0.030))
        vein(d, rng, part["veins"], C.GREEN_DEEP, width=0.95, tone=0.46 * k)
        ink(d, rng, part["midrib"], width=1.5, tone=0.62 * k, color=C.GREEN_SHADE)
        ink(d, rng, o, width=1.35, tone=0.9 * k, closed=True)


# ------------------------------------------------------------------------ 花

def flower(img, g, k, rng, size, pedicel=None):
    d = ImageDraw.Draw(img, "RGBA")
    col = C.FLOWER_SETS[g.get("hue", "blue")]

    if pedicel is not None:
        pw = max(2.0, size * 0.035)
        body = (S.offset(pedicel, 90, pw * 0.5) +
                S.offset(pedicel, -90, pw * 0.5)[::-1])
        lay(img, body, C.tint(C.STEM, 0.3), C.STEM, C.STEM_DEEP, strength=0.8)
        ink(d, rng, pedicel, width=1.1, tone=0.6, color=C.STEM_DEEP)

    if g["form"] == "bud":
        _bud(img, d, g, k, rng, size)
        return
    if g["form"] == "face":
        _face(img, d, g, k, rng, size, col)
        return
    _bell(img, d, g, k, rng, size, col)


def _bell(img, d, g, k, rng, size, col):
    """横から見た釣鐘。

    筒が主役で、裂片は口の縁の張り出しにすぎない。立体は 3 つで決まる。
      ・筒の丸み（両脇が暗く、光の当たる一筋だけ明るい）
      ・口の奥の暗さ（紙のまま残すと花に穴が空いて造花に見える）
      ・付け根から口へ放射する稜。この線が釣鐘を釣鐘にしている
    """
    shape = g["outline"]
    seed = int(shape[0][0]) & 255
    smudge(img, shape, tone=0.11, blur=11, shift=(6, 9), color=C.shade(col["deep"]))
    # 花は葉より淡く置く。花まで濃く塗ると図版の明るさが消え、
    # 口の奥との差もなくなって、下半分が黒い塊になる。
    lay(img, shape, C.tint(col["hi"], 0.44), col["mid"],
        C.mix(col["deep"], col["mid"], 0.45),
        strength=0.62 * k, seed=seed, flow=g["flow"][::6], rng=rng)

    axis = g["axis"]
    # 筒の丸み。光の当たる一筋を明るく抜く。方向つきの階調だけでは板になる。
    lit = S.offset(S.cubic(g["base"], S.polar(g["base"], axis, g["length"] * 0.4),
                           S.polar(g["base"], axis, g["length"] * 0.7),
                           g["mouth"], 18), -90, g["width"] * 0.16)
    crease(img, lit, C.tint(col["hi"], 0.72), alpha=int(118 * k),
           width=max(3.0, g["width"] * 0.17))

    # 口の奥
    throat = g.get("throat")
    if throat:
        # 奥へ行くほど暗い。一色で塗ると、花が黒い帯に浸かって見える。
        # 手前の裂片の内側は光を拾うので、そこだけ明るく残す。
        fill_shape(img, throat, color=C.shade(col["deep"], 0.30), alpha=250)
        wash(img, throat, C.shade(col["deep"], 0.70), alpha=185,
             gradient_deg=axis + 180)
        n = len(g["far"])
        ink(d, rng, g["far"][int(n * 0.26):int(n * 0.74)], width=1.0, tone=0.46,
            color=col["ink"], taper=(0.5, 0.5))

    # 稜。本数を惜しむと樽になる。間を空けて濃さを振ると手で引いた線に見える。
    # 稜は付け根まで引かず、口の手前で止める。
    #
    # 付け根まで引くと一点に集まって傘の骨に見える。口まで引き切ると、
    # 全部の線が口の奥で重なって、そこだけ真っ黒な帯になる。奥は面として
    # 暗いのであって、線で埋めて暗くするところではない。
    vein(d, rng, [c[6:-7] for c in g["flow"][5::4]], col["ink"],
         width=0.95, tone=0.34 * k)
    vein(d, rng, [c[:-4] for c in g["ribs"]], col["ink"], width=1.25, tone=0.52 * k)
    for sn in g.get("sinus", []):
        ink(d, rng, sn, width=1.1, tone=0.55, color=col["ink"], taper=(0.05, 0.7))

    ink(d, rng, shape, width=1.35, tone=0.9 * k, closed=True)
    _calyx(img, d, rng, g["base"], g["axis"], size, k)
    if g.get("open", 0) > 0.8:
        _style(img, d, rng, g["mouth"], g["axis"], size * 0.85)


def _face(img, d, g, k, rng, size, col):
    """正面を向いた花。参照図版でいちばん目を引くのはこの姿で、
    5 裂した裂片・放射する脈・中央の黄色い葯がそろって初めてそう見える。"""
    shape = g["outline"]
    smudge(img, shape, tone=0.09, blur=11, shift=(6, 9), color=C.shade(col["deep"]))
    lay(img, shape, C.tint(col["hi"], 0.50), C.mix(col["hi"], col["mid"], 0.80),
        col["deep"], strength=0.86 * k, seed=int(shape[0][0]) & 255,
        flow=g["veins"][::2], rng=rng)

    # 喉もとは明るく抜く。中心が暗いと花が「穴」に見える。
    fill_shape(img, g["throat"], color=C.tint(col["hi"], 0.55), alpha=210, feather=2.2)

    vein(d, rng, g["veins"], col["ink"], width=0.85, tone=0.44 * k)
    for sn in g["sinus"]:
        ink(d, rng, sn, width=1.0, tone=0.5, color=col["ink"], taper=(0.05, 0.8))
    ink(d, rng, shape, width=1.35, tone=0.9 * k, closed=True)
    _anthers(img, d, rng, g["centre"], g["axis"], size * 0.52)


def _bud(img, d, g, k, rng, size):
    """まだ開かない蕾。稜が 5 本あって、先がねじれている。
    ただの楕円に線を入れただけでは「種」にしか見えない。"""
    shape = g["outline"]
    hi, mid, deep = C.mix(C.GREEN_HI, C.BLUE_HI, 0.45), C.mix(C.GREEN, C.BLUE, 0.4), C.GREEN_DEEP
    smudge(img, shape, tone=0.09, blur=9, shift=(5, 8), color=C.GREEN_SHADE)
    lay(img, shape, C.tint(hi, 0.35), mid, deep, strength=0.9 * k,
        seed=int(shape[0][0]) & 255, flow=g["ridges"], rng=rng)
    vein(d, rng, g["ridges"], C.STEM_DEEP, width=1.0, tone=0.48 * k)
    ink(d, rng, shape, width=1.3, tone=0.88 * k, closed=True)
    _calyx(img, d, rng, g["base"], g["axis"], size, k, hug=True)


def _calyx(img, d, rng, base, face, size, k, hug=False):
    """萼。5 枚の細い裂片。無いと花が茎に刺さって見える。
    蕾のうちは筒を抱き、咲くと反り返る。"""
    spread = 20.0 if hug else 30.0
    ln = size * (0.40 if hug else 0.30)
    for i in range(5):
        a = face + (i - 2) * spread
        tipp = S.polar(base, a + (0 if hug else (14 if i > 2 else -14)), ln)
        mid = S.polar(base, a, ln * 0.5)
        blade = S.quad(base, mid, tipp, 12)
        body = (S.offset(blade, 90, lambda t: ln * 0.085 * (1 - t) + 0.6) +
                S.offset(blade, -90, lambda t: ln * 0.085 * (1 - t) + 0.6)[::-1])
        lay(img, body, C.GREEN_HI, C.GREEN, C.GREEN_DEEP, strength=0.85 * k)
        ink(d, rng, body, width=1.0, tone=0.76 * k, closed=True, color=C.INK_SOFT)


def _style(img, d, rng, mouth, face, size):
    """花柱と柱頭。口から出て先が 3 裂する。"""
    st = S.polar(mouth, face, size * 0.34)
    stalk = [S.polar(mouth, face, -size * 0.08), st]
    ink(d, rng, stalk, width=2.0, tone=0.8, color=C.tint(C.YELLOW_DEEP, 0.25))
    for a in (-36, 0, 36):
        arm = S.quad(st, S.polar(st, face + a, size * 0.07),
                     S.polar(st, face + a * 1.5, size * 0.14), 7)
        ink(d, rng, arm, width=2.4, tone=0.85, color=C.YELLOW)
        ink(d, rng, arm, width=1.0, tone=0.5, color=C.YELLOW_DEEP)


def _anthers(img, d, rng, centre, face, size):
    """葯。中心から 5 本出て、先に黄色い粒がつく。図版の焦点はここ。"""
    for i in range(5):
        a = face + 72 * i + 18
        tipp = S.polar(centre, a, size * 0.62)
        arm = S.quad(centre, S.polar(centre, a, size * 0.3), tipp, 7)
        ink(d, rng, arm, width=2.6, tone=0.9, color=C.YELLOW)
        ink(d, rng, arm, width=1.1, tone=0.55, color=C.YELLOW_DEEP)
        head = S.bud(tipp, a, size * 0.26, size * 0.085)["outline"]
        fill_shape(img, head, color=C.YELLOW, alpha=255, feather=0.5)
        ink(d, rng, head, width=0.9, tone=0.7, color=C.YELLOW_DEEP, closed=True)
    # 柱頭
    for a in (face, face + 120, face + 240):
        arm = S.quad(centre, S.polar(centre, a, size * 0.16),
                     S.polar(centre, a, size * 0.30), 6)
        ink(d, rng, arm, width=2.2, tone=0.85, color=C.YELLOW_DEEP)


# ------------------------------------------------------------------- 根と図版名

def roots(d, rng, off=(0.0, 0.0)):
    """根。標本画は根まで描く。太い主根から細根が枝分かれする。"""
    p = Pencil(d, rng, C.mix(C.INK_SOFT, C.STEM_DEEP, 0.4))
    for sid, bx0, by0, n in Y.ROOTS:
        bx, by = Y.sc(bx0) + off[0], Y.sc(by0) + off[1]
        for i in range(n):
            a = 60 + (i - (n - 1) / 2) * (150 / max(n - 1, 1)) + rng.uniform(-8, 8)
            ln = Y.sc(rng.uniform(70, 190))
            end = S.polar((bx, by), a, ln)
            mid = S.polar((bx, by), a + rng.uniform(-22, 22), ln * 0.55)
            spine = S.quad((bx, by), mid, end, 10)
            p.stroke(spine, width=lambda t: Y.sc(3.4 - 2.6 * t), tone=0.72,
                     jitter=1.1, passes=1, taper=(0.05, 0.5))
            for j in range(2):
                on = spine[int((0.45 + 0.3 * j) * 10)]
                sp = S.polar(on, a + rng.choice([-52, 52]), Y.sc(rng.uniform(26, 54)))
                p.stroke(S.quad(on, S.polar(on, a, Y.sc(14)), sp, 6),
                         width=Y.sc(1.2), tone=0.46, jitter=0.8, passes=1,
                         taper=(0.1, 0.6))


SERIF_IT = "/usr/share/fonts/truetype/liberation/LiberationSerif-Italic.ttf"
SERIF = "/usr/share/fonts/truetype/liberation/LiberationSerif-Regular.ttf"


def caption(d, rng):
    """活字ではなく石版の文字に見せたいので、わずかに揺らして二度置く。"""
    try:
        f = ImageFont.truetype(SERIF_IT, int(Y.H * 0.0256))
        fs = ImageFont.truetype(SERIF, int(Y.H * 0.0144))
    except OSError:
        return
    for text, font, cy, sp in ((Y.CAPTION, f, Y.H * 0.927, 3),
                               (Y.PLATE_NO, fs, Y.H * 0.959, 6)):
        w = d.textlength(text, font=font) + sp * (len(text) - 1)
        x = (Y.W - w) / 2
        for ch in text:
            for dx, dy, a in ((0, 0, 170), (0.7, 0.6, 80)):
                d.text((x + dx + rng.uniform(-0.5, 0.5),
                        cy + dy + rng.uniform(-0.7, 0.7)),
                       ch, font=font, fill=C.INK + (a,))
            x += d.textlength(ch, font=font) + sp


def vine_bloom(img, g, rng, icon_hole=0.0):
    """蔦の先の蕾が咲くまでの 1 コマ。

    花弁を 1 枚ずつ、紙地ではなく花の色で塗ってから輪郭を引く。こうすると
    重なった内側の線が隠れ、閉じているあいだは外側の 1 枚の輪郭だけが残って
    蕾の形になる。輪郭を透かしたまま重ねると、ただの線の束にしかならない。
    """
    d = ImageDraw.Draw(img, "RGBA")
    o = g["open"]
    col = C.FLOWER_SETS["blue"]

    # 萼は花弁の後ろ。開くほど外へ逃げるので先に描いておく。
    for i, sp in enumerate(g["sepals"]):
        poly = sp["outline"]
        lay(img, poly, C.tint(C.GREEN_HI, 0.25), C.GREEN, C.GREEN_DEEP,
            strength=0.85, seed=31 + i * 7)
        ink(d, rng, poly, width=1.1, tone=0.78, closed=True, color=C.INK_SOFT)

    # 蕾のうちは緑を帯び、開くにつれて青が差す。実物の蕾は緑い。
    hi = C.mix(C.mix(C.GREEN_HI, C.BLUE_HI, 0.35), C.tint(col["hi"], 0.45), o)
    mid = C.mix(C.mix(C.GREEN, C.BLUE, 0.45), col["mid"], o)
    deep = C.mix(C.GREEN_DEEP, col["deep"], o)

    for i, pt in enumerate(g["petals"]):
        poly = pt["outline"]
        smudge(img, poly, tone=0.13, blur=max(2.5, g["size"] * 0.05),
               color=C.shade(deep, 0.3))
        lay(img, poly, hi, mid, deep, strength=0.80, seed=71 + i * 13,
            flow=pt["flow"][::6], rng=rng, edge=0.40)
        # 花弁の筋は付け根から先へ走る。葉のような羽状の脈を入れると、
        # 何枚並べても「小さい葉が 5 枚ついている」ようにしか見えない。
        vein(d, rng, pt["cross"][3:-3], col["ink"] if o > 0.5 else C.GREEN_DEEP,
             width=0.85, tone=0.30 * min(1.0, o * 1.6 + 0.2))
        ink(d, rng, poly, width=1.25, tone=0.88, closed=True)

    # 葯。開ききる手前から現れる。黄が差した瞬間に「咲いた」と読める。
    if o > 0.55:
        f = (o - 0.55) / 0.45
        _anthers(img, d, rng, g["centre"], g["axis"],
                 g["size"] * (0.30 + 0.22 * f) * (1.0 - icon_hole * 0.45))
