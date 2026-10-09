"""図版の色。参照した Anne Pratt の多色石版（Pl.134）から実測した値。

手で選ばず測ったのは、記憶で置くと必ず彩度が上がりすぎるから。実物の
青はくすんだスレートブルーで、緑は黄みの強いサップグリーン、輪郭線は
黒ではなく暗い茶緑。印刷された「色数の少ない石版」の渋さがここにある。

技法はハッチングではない。
  ・細く確かな輪郭線を一本
  ・内側は面で色を置く（淡彩）
  ・濃淡は同系色の濃い側で、面として重ねる
  ・脈は地より濃い同系色の線で放射させる
  ・いちばん明るいところは紙の白を残す
銅版画の線描で濃淡を作ると、この図版の軽さは出ない。
"""

PAPER = (240, 235, 226)
PAPER_WARM = (246, 241, 232)

# 輪郭線。純黒だと硬い。実測は暗い茶緑で、わずかに緑に寄っている。
INK = (35, 29, 26)
INK_SOFT = (54, 69, 49)

# 釣鐘花（青）。明→暗
BLUE_HI = (198, 216, 220)
BLUE = (149, 177, 182)
BLUE_MID = (120, 152, 162)
BLUE_DEEP = (89, 123, 136)
BLUE_INK = (58, 86, 102)

# 紅の花。図版では青と並べて使われている
CRIMSON_HI = (178, 96, 98)
CRIMSON = (125, 19, 39)
CRIMSON_DEEP = (106, 7, 22)

# 葯と柱頭。この黄が一点あるだけで図版に見える
YELLOW = (202, 181, 51)
YELLOW_DEEP = (177, 146, 27)

# 葉と茎。黄みの強いサップグリーン
GREEN_HI = (166, 194, 120)
GREEN = (104, 141, 58)
GREEN_DEEP = (48, 75, 26)
GREEN_SHADE = (33, 54, 16)
STEM = (109, 137, 72)
STEM_DEEP = (58, 85, 34)


def mix(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def shade(c, t=0.35):
    """同系色の濃い側。黒と混ぜると濁るので、暗い方の実測色へ寄せる。"""
    return mix(c, (26, 30, 24), t)


def tint(c, t=0.35):
    return mix(c, PAPER_WARM, t)


# 花の色の組。図版は一枚のなかで数種を並べて見せる
FLOWER_SETS = {
    "blue":    dict(hi=BLUE_HI, mid=BLUE, deep=BLUE_DEEP, ink=BLUE_INK),
    "crimson": dict(hi=CRIMSON_HI, mid=CRIMSON, deep=CRIMSON_DEEP, ink=CRIMSON_DEEP),
}
