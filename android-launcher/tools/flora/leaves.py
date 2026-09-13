"""葉の型。図版らしさは「種ごとに葉の形が違う」ことでほぼ決まる。

すべて {"parts": [{outline, midrib, veins, flow}], ...} の形で返し、
描画側は部分の数を気にせず同じ手順で扱える。複葉は部分が小葉の数だけ増える。
"""
import math
from shapes import polar, quad, lerp


def _blade(attach, d, length, width, bend=0.0, teeth=0, waist=0.36, tip_sharp=1.0):
    """1 枚の葉身。waist を上げると卵形、下げると披針形。"""
    tip = polar(attach, d + bend * 16, length)
    mid = polar(attach, d + bend * 8, length * waist)
    left = polar(mid, d - 90, width)
    right = polar(mid, d + 90, width)
    e1 = quad(attach, left, tip, 20)
    e2 = quad(tip, right, attach, 20)

    if teeth:
        def serrate(edge, outdeg):
            out = []
            for i, p in enumerate(edge):
                t = i / (len(edge) - 1)
                amp = width * 0.085 * (math.sin(math.pi * t) ** 0.55)
                out.append(polar(p, outdeg, amp) if i % 2 else p)
            return out
        e1 = serrate(e1, d - 90)
        e2 = serrate(e2, d + 90)

    outline = e1 + e2[1:]
    midrib = quad(attach, polar(attach, d + bend * 8, length * 0.5), tip, 14)
    veins, flow = [], []
    for i in range(1, 5):
        t = i / 5
        on = midrib[int(t * 14)]
        span = width * 2 * t * (1 - t) * 0.82
        for sd in (-1, 1):
            veins.append(quad(on, polar(on, d + sd * 40, span * 0.6),
                              polar(on, d + sd * 62, span), 7))
    # ハッチングの向きは側脈に沿わせる（主脈から縁へ）。
    #
    # 葉の長手方向に走らせると木目や段ボールに見える。銅版画の葉は必ず
    # 葉脈の流れに沿って彫られていて、それが葉の面の向きを伝えている。
    STEPS = 46
    for i in range(1, STEPS):
        t = i / STEPS
        on = midrib[min(int(t * 14), 14)]
        # 二次ベジエの実際の振れ幅。これを超えると葉からはみ出す。
        span = width * 2 * t * (1 - t) * 0.94
        for sd in (-1, 1):
            flow.append(quad(on, polar(on, d + sd * 38, span * 0.55),
                             polar(on, d + sd * 62, span), 7))
    # 二度目の彫り。長手方向に薄く重ねると、線と線の間が埋まって面になる。
    # 版画の調子は「向きの違うハッチングを重ねる」ことで作られている。
    cross = []
    n = min(len(e1), len(e2[::-1]))
    le, ri = e1[:n], e2[::-1][:n]
    for kk in range(1, 16):
        u = kk / 16
        cross.append([(lerp(a[0], b[0], u), lerp(a[1], b[1], u)) for a, b in zip(le, ri)])

    return {"outline": outline, "midrib": midrib, "veins": veins,
            "flow": flow, "cross": cross}


def lance(attach, d, length, width, bend=0.0, teeth=1):
    return {"parts": [_blade(attach, d, length, width, bend, teeth, 0.34)],
            "anchor": polar(attach, d, length * 0.45), "reach": length}


def ovate(attach, d, length, width, bend=0.0, teeth=1):
    return {"parts": [_blade(attach, d, length, width * 1.5, bend, teeth, 0.44)],
            "anchor": polar(attach, d, length * 0.45), "reach": length}


def pinnate(attach, d, length, width, bend=0.0, pairs=3, teeth=1):
    """羽状複葉。軸に小葉が対でつく。図版でいちばん「らしく」見える型。"""
    rachis_tip = polar(attach, d + bend * 12, length)
    rachis = quad(attach, polar(attach, d + bend * 6, length * 0.5), rachis_tip, 16)
    parts = []
    for i in range(pairs):
        t = 0.22 + 0.62 * (i / max(pairs - 1, 1))
        on = rachis[int(t * 16)]
        ll = length * (0.42 - 0.07 * i)
        for sd in (-1, 1):
            parts.append(_blade(on, d + sd * 58, ll, ll * 0.34, sd * 0.3, teeth, 0.36))
    parts.append(_blade(rachis_tip, d, length * 0.34, length * 0.12, bend, teeth, 0.36))
    return {"parts": parts, "rachis": rachis,
            "anchor": polar(attach, d, length * 0.5), "reach": length}


def lobed(attach, d, length, width, bend=0.0, lobes=5, teeth=1):
    """掌状葉。中心から数枚の裂片が開く。蔦や紅葉の葉。"""
    hub = polar(attach, d, length * 0.22)
    parts = []
    span = 96.0
    for i in range(lobes):
        a = d + lerp(-span, span, i / (lobes - 1))
        ll = length * (0.95 - 0.30 * abs(i - (lobes - 1) / 2) / max((lobes - 1) / 2, 1))
        parts.append(_blade(hub, a, ll, ll * 0.40, 0.0, teeth, 0.42))
    return {"parts": parts, "stalk": quad(attach, polar(attach, d, length * 0.12), hub, 6),
            "anchor": polar(attach, d, length * 0.6), "reach": length}


KINDS = {"lance": lance, "ovate": ovate, "pinnate": pinnate, "lobed": lobed}


def make(kind, attach, d, length, width, bend=0.0, teeth=1):
    return KINDS[kind](attach, d, length, width, bend, teeth=teeth)
