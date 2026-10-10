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


# 線幅・ぼかし半径の倍率。
#
# Pillow の描画にはアンチエイリアスが無く、線はすべて 1px の階段になる。
# 拡大して描いてから縮小すると、縮小のときの平均化がアンチエイリアスの
# 役目を果たす（スーパーサンプリング）。幾何はそのまま掛け算で拡大できる
# が、px で直書きしている線幅とぼかしは一緒に拡大されないので、ここで
# まとめて掛ける。PX=1 なら従来どおり。
PX = 1.0


def P(v):
    return v * PX


def scale(o, k):
    """点・折れ線・辞書をまとめて原点中心に拡大する。"""
    if isinstance(o, dict):
        return {key: scale(v, k) for key, v in o.items()}
    if isinstance(o, (list, tuple)):
        if len(o) == 2 and all(isinstance(v, (int, float)) for v in o):
            return (o[0] * k, o[1] * k)
        return [scale(v, k) for v in o]
    return o


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
    # 明暗の幅を潰さない。
    #
    # 参照図版の葉は 暗 #2E4819 / 中 #618537 / 明 #ABC291 と、ひとつの葉の
    # 中で大きく振れている。濃い側を面の全体に少しずつ乗せると、中間色に
    # 寄って平板になる。光の当たる側には地の色をそのまま残し、濃い側だけを
    # 深く落とす（floor=0）。
    fill_shape(img, poly, color=hi, alpha=246, feather=0.6)
    wash(img, poly, mid, alpha=int(178 * strength), gradient_deg=light + 180,
         floor=0.10)
    wash(img, poly, deep, alpha=int(168 * strength), gradient_deg=light + 180,
         floor=0.0)
    # 縁のきわだけ落とす。半分を塗ると板になる。
    # 尖った形では、縁の帯が先端をまるごと覆ってしまう。花弁のように
    # 先が尖るものは [edge] を下げる。下げないと先端に黒い楔が残る。
    if edge > 0.02:
        # bias は光の側に残す分。既定の 0.35 だと明るい側の縁まで一律に
        # 暗くなり、せっかく残した地の明るさが縁から削られる。
        edge_shade(img, poly, C.shade(deep, 0.30), alpha=int(92 * strength * edge),
                   band=_band(poly), gradient_deg=light + 180, bias=0.10)
    mottle(img, poly, C.shade(deep, 0.20), alpha=int(70 * strength),
           seed=seed, scale=_band(poly, 0.40) + P(8),
           gradient_deg=light + 180, floor=0.12)
    mottle(img, poly, C.tint(hi, 0.70), alpha=int(72 * strength),
           seed=seed + 101, scale=_band(poly, 0.62) + P(12),
           gradient_deg=light, floor=0.20)
    # 面の流れに沿った色の筆致。均一な塗りはベクタ画像に見える。手彩色は
    # 必ず形の流れ（花なら稜、葉なら側脈）に沿って刷毛目が残る。
    if flow:
        r2 = rng or random.Random(seed)
        clipped(img, poly, lambda dd: Pencil(dd, random.Random(r2.randrange(1 << 30)),
                                             C.shade(deep, 0.18))
                .hatch_curves(flow, tone=0.42 * strength,
                              width=max(P(1.6), _band(poly, 0.12)),
                              jitter=0.8, span=(0.05, 0.98), grain=0.14,
                              taper=(0.15, 0.45),
                              gradient=(light + 180, 0.0, 1.0), cross_at=9.0))


def _band(poly, frac=0.13):
    xs = [p[0] for p in poly]
    ys = [p[1] for p in poly]
    return max(P(3.0), min(max(xs) - min(xs), max(ys) - min(ys)) * frac)


def ink(d, rng, pts, width=1.3, tone=0.92, jitter=0.5, color=C.INK, closed=False,
        taper=(0.06, 0.06)):
    """輪郭線。石版の線は細く、迷いがなく、太さが少しだけ揺れる。"""
    p = Pencil(d, rng, color)
    seq = list(pts) + [pts[0]] if closed else pts
    p.stroke(seq, width=P(width), tone=tone, jitter=jitter * PX, passes=1,
             taper=taper, grain=0.14)


def vein(d, rng, curves, color, width=0.9, tone=0.5):
    p = Pencil(d, rng, color)
    for c in curves:
        p.stroke(c, width=P(width), tone=tone, jitter=0.45 * PX, passes=1,
                 taper=(0.1, 0.5), grain=0.2)


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
    ink(d, rng, right, width=0.95, tone=0.78 * k, jitter=0.45)
    ink(d, rng, left, width=0.8, tone=0.54 * k, jitter=0.45)


# ------------------------------------------------------------------------ 葉

def leaf(img, lf, k, rng, reach):
    """葉。

    手彩色は面をほぼ平らに塗る。立体は、方向つきの滑らかな階調ではなく
    「縁の濃い帯」と「主脈に沿った陰と照り」という局所の仕掛けで出す。
    面の全体に階調をかけると、石版ではなく 3D のレンダリングに見える。
    """
    d = ImageDraw.Draw(img, "RGBA")
    if "stalk" in lf:
        ink(d, rng, lf["stalk"], width=1.2, tone=0.7, color=C.STEM_DEEP)
    if "rachis" in lf:
        ink(d, rng, lf["rachis"], width=1.2, tone=0.7, color=C.STEM_DEEP)

    for part in lf["parts"]:
        o = part["outline"]
        mid = part["midrib"]
        band = _band(o, 0.16)
        smudge(img, o, tone=0.10, blur=P(11), shift=(P(7), P(10)), color=C.GREEN_SHADE)

        # 地。階調をかけずに平らに置く。紙の白は混ぜない（彩度が落ちる）。
        fill_shape(img, o, color=C.GREEN_LIGHT, alpha=248, feather=0.6)
        wash(img, o, C.GREEN_HI, alpha=int(150 * k))

        # 主脈で分けた片側を、濃い緑でもう一度塗る。
        #
        # 半透明の重ねだけでは、面の全体を覆わない限り暗い側まで届かない。
        # 参照図版の葉は、濃い緑が「境目の見える 2 度目の塗り」として片側に
        # 置かれている。中が明るいまま、片側だけが深く沈むのはこれのため。
        lit = S.polar((0.0, 0.0), LIGHT, 1.0)
        mr = part["midrib"]
        axis = S.polar((0.0, 0.0),
                       math.degrees(math.atan2(mr[-1][1] - mr[0][1],
                                               mr[-1][0] - mr[0][0])) + 90, 1.0)
        # lit は光が来る向き。法線がそれと逆を向いている側が陰になる。
        away = (axis[0] * lit[0] + axis[1] * lit[1]) < 0
        shadow = part["right"] if away else part["left"]
        sdeg = 90 if away else -90
        fill_shape(img, list(mr) + list(shadow), color=C.GREEN_DEEP,
                   alpha=int(224 * k), feather=max(P(1.6), band * 0.22))
        # 影の側のさらに外寄り、縁に近いところがいちばん深い。参照図版の
        # 葉はここが #2A4817 まで落ちていて、そこまで行かないと「厚みの
        # ある一枚の葉」ではなく、色を塗った切り紙に見える。
        fill_shape(img, S.offset(mr, sdeg, band * 0.85) + list(shadow),
                   color=C.GREEN_SHADE, alpha=int(205 * k),
                   feather=max(P(2.0), band * 0.40))

        # 濃い緑を側脈に沿って筋で入れる。
        #
        # これが調子の主役。滑らかな階調で付けると 3D のレンダリングに
        # 見える。参照図版の葉は、濃い緑が脈に沿った不揃いな筋として
        # 置かれていて、筋の間に明るい地が残っている。
        clipped(img, o, lambda dd: Pencil(dd, random.Random(rng.randrange(1 << 30)),
                                          C.GREEN_DEEP)
                .hatch_curves(part["flow"][::4], tone=0.80 * k,
                              width=max(P(4.0), band * 1.05), jitter=1.6,
                              span=(0.02, 1.0), grain=0.06, taper=(0.08, 0.30),
                              gradient=(LIGHT + 180, 0.22, 1.0), cross_at=9.0))
        clipped(img, o, lambda dd: Pencil(dd, random.Random(rng.randrange(1 << 30)),
                                          C.GREEN_SHADE)
                .hatch_curves(part["flow"][5::9], tone=0.78 * k,
                              width=max(P(3.0), band * 0.70), jitter=1.8,
                              span=(0.08, 0.96), grain=0.08, taper=(0.10, 0.40),
                              gradient=(LIGHT + 180, 0.0, 1.0), cross_at=9.0))
        mottle(img, o, C.GREEN_DEEP, alpha=int(60 * k),
               seed=int(o[0][0]) & 255, scale=band * 2.2 + P(8),
               gradient_deg=LIGHT + 180, floor=0.25)
        mottle(img, o, C.GREEN_LIGHT, alpha=int(96 * k),
               seed=(int(o[0][0]) & 255) + 101, scale=band * 3.4 + P(12),
               gradient_deg=LIGHT, floor=0.22)

        # 縁の帯。細く全周に。広く取ると「帯」ではなく全体の暗転になる。
        edge_shade(img, o, C.GREEN_SHADE, alpha=int(168 * k), band=band * 0.55,
                   gradient_deg=LIGHT + 180, bias=0.34)

        # 主脈。片側に陰、反対側に照り。葉が一枚の板ではなく、
        # 中央で折れた面に見えるのはこの 2 本が効いている。
        w = max(P(2.5), reach * 0.028)
        crease(img, S.offset(mid, -90, w * 0.85), C.GREEN_DEEP,
               alpha=int(104 * k), width=w)
        crease(img, S.offset(mid, 90, w * 0.75), C.GREEN_LIGHT,
               alpha=int(140 * k), width=w * 0.9)

        vein(d, rng, part["veins"], C.GREEN_DEEP, width=0.7, tone=0.44 * k)
        ink(d, rng, mid, width=1.0, tone=0.52 * k, color=C.GREEN_SHADE)
        ink(d, rng, o, width=0.95, tone=0.76 * k, closed=True)


# ------------------------------------------------------------------------ 花

def flower(img, g, k, rng, size, pedicel=None):
    d = ImageDraw.Draw(img, "RGBA")
    col = C.FLOWER_SETS[g.get("hue", "blue")]

    if pedicel is not None:
        pw = max(P(2.0), size * 0.035)
        body = (S.offset(pedicel, 90, pw * 0.5) +
                S.offset(pedicel, -90, pw * 0.5)[::-1])
        lay(img, body, C.tint(C.STEM, 0.3), C.STEM, C.STEM_DEEP, strength=0.8)
        ink(d, rng, pedicel, width=0.85, tone=0.52, color=C.STEM_DEEP)

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
    smudge(img, shape, tone=0.11, blur=P(11), shift=(P(6), P(9)), color=C.shade(col["deep"]))
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
           width=max(P(3.0), g["width"] * 0.17))

    # 口の奥
    throat = g.get("throat")
    if throat:
        # 奥へ行くほど暗い。一色で塗ると、花が黒い帯に浸かって見える。
        # 手前の裂片の内側は光を拾うので、そこだけ明るく残す。
        fill_shape(img, throat, color=C.shade(col["deep"], 0.30), alpha=250)
        wash(img, throat, C.shade(col["deep"], 0.70), alpha=185,
             gradient_deg=axis + 180)
        n = len(g["far"])
        ink(d, rng, g["far"][int(n * 0.26):int(n * 0.74)], width=0.75, tone=0.42,
            color=col["ink"], taper=(0.5, 0.5))

    # 稜。本数を惜しむと樽になる。間を空けて濃さを振ると手で引いた線に見える。
    # 稜は付け根まで引かず、口の手前で止める。
    #
    # 付け根まで引くと一点に集まって傘の骨に見える。口まで引き切ると、
    # 全部の線が口の奥で重なって、そこだけ真っ黒な帯になる。奥は面として
    # 暗いのであって、線で埋めて暗くするところではない。
    vein(d, rng, [c[6:-7] for c in g["flow"][5::4]], col["ink"],
         width=0.65, tone=0.30 * k)
    vein(d, rng, [c[:-4] for c in g["ribs"]], col["ink"], width=0.85, tone=0.44 * k)
    for sn in g.get("sinus", []):
        ink(d, rng, sn, width=0.8, tone=0.48, color=col["ink"], taper=(0.05, 0.7))

    ink(d, rng, shape, width=0.95, tone=0.78 * k, closed=True)
    _calyx(img, d, rng, g["base"], g["axis"], size, k)
    if g.get("open", 0) > 0.8:
        _style(img, d, rng, g["mouth"], g["axis"], size * 0.85)


def _face(img, d, g, k, rng, size, col):
    """正面を向いた花。参照図版でいちばん目を引くのはこの姿で、
    5 裂した裂片・放射する脈・中央の黄色い葯がそろって初めてそう見える。"""
    shape = g["outline"]
    smudge(img, shape, tone=0.09, blur=P(11), shift=(P(6), P(9)), color=C.shade(col["deep"]))
    lay(img, shape, C.tint(col["hi"], 0.50), C.mix(col["hi"], col["mid"], 0.80),
        col["deep"], strength=0.86 * k, seed=int(shape[0][0]) & 255,
        flow=g["veins"][::2], rng=rng)

    # 喉もとは明るく抜く。中心が暗いと花が「穴」に見える。
    fill_shape(img, g["throat"], color=C.tint(col["hi"], 0.55), alpha=210, feather=2.2)

    vein(d, rng, g["veins"], col["ink"], width=0.6, tone=0.38 * k)
    for sn in g["sinus"]:
        ink(d, rng, sn, width=0.75, tone=0.44, color=col["ink"], taper=(0.05, 0.8))
    ink(d, rng, shape, width=0.95, tone=0.78 * k, closed=True)
    _anthers(img, d, rng, g["centre"], g["axis"], size * 0.52)


def _bud(img, d, g, k, rng, size):
    """まだ開かない蕾。稜が 5 本あって、先がねじれている。
    ただの楕円に線を入れただけでは「種」にしか見えない。"""
    shape = g["outline"]
    hi, mid, deep = C.mix(C.GREEN_HI, C.BLUE_HI, 0.45), C.mix(C.GREEN, C.BLUE, 0.4), C.GREEN_DEEP
    smudge(img, shape, tone=0.09, blur=P(9), shift=(P(5), P(8)), color=C.GREEN_SHADE)
    lay(img, shape, C.tint(hi, 0.35), mid, deep, strength=0.9 * k,
        seed=int(shape[0][0]) & 255, flow=g["ridges"], rng=rng)
    vein(d, rng, g["ridges"], C.STEM_DEEP, width=0.7, tone=0.42 * k)
    ink(d, rng, shape, width=0.95, tone=0.76 * k, closed=True)
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
        ink(d, rng, body, width=0.8, tone=0.66 * k, closed=True, color=C.INK_SOFT)


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
        ink(d, rng, poly, width=0.85, tone=0.68, closed=True, color=C.INK_SOFT)

    # 蕾のうちは緑を帯び、開くにつれて青が差す。実物の蕾は緑い。
    hi = C.mix(C.mix(C.GREEN_HI, C.BLUE_HI, 0.35), C.tint(col["hi"], 0.45), o)
    mid = C.mix(C.mix(C.GREEN, C.BLUE, 0.45), col["mid"], o)
    deep = C.mix(C.GREEN_DEEP, col["deep"], o)

    for i, pt in enumerate(g["petals"]):
        poly = pt["outline"]
        smudge(img, poly, tone=0.13, blur=max(P(2.5), g["size"] * 0.05),
               color=C.shade(deep, 0.3))
        lay(img, poly, hi, mid, deep, strength=0.80, seed=71 + i * 13,
            flow=pt["flow"][::6], rng=rng, edge=0.40)
        # 花弁の筋は付け根から先へ走る。葉のような羽状の脈を入れると、
        # 何枚並べても「小さい葉が 5 枚ついている」ようにしか見えない。
        vein(d, rng, pt["cross"][3:-3], col["ink"] if o > 0.5 else C.GREEN_DEEP,
             width=0.6, tone=0.26 * min(1.0, o * 1.6 + 0.2))
        ink(d, rng, poly, width=0.9, tone=0.76, closed=True)

    # 葯。開ききる手前から現れる。黄が差した瞬間に「咲いた」と読める。
    if o > 0.55:
        f = (o - 0.55) / 0.45
        _anthers(img, d, rng, g["centre"], g["axis"],
                 g["size"] * (0.30 + 0.22 * f) * (1.0 - icon_hole * 0.45))
