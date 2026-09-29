"""図版の配置。plate.py（版面の下描き）と build_assets.py（スプライト焼き）は
どちらもここが返した配置だけを見る。

以前は同じ配置計算を両方に書き写していて、片方だけ直すと版面と実機で
草の生え方がずれた。乱数も別々に回っていたので、同じ種を渡しても
結果が一致しなかった。配置はここにしか無い。
"""
import math
import random

import shapes as S
import leaves as L

W, H = 1100, 1800
LIGHT = -128                    # 光は左上から
GRAPHITE = (66, 63, 68)

# 参照した19世紀の図版は「標本画」である。画面を葉で埋めるのではなく、
# 一株を根から花まで完全に描き切り、たっぷりの余白の中央に据える。
# 株を減らすことは触りやすさにも効く（対象が減って一つずつ大きくなる）。
#
# id, 根元x, 根元y, 角度, 茎長, 反り, 奥行, 花数, 葉数, 葉長, 葉の型
STEMS = [
    ("main", 548, 1520, -89, 1150, -0.16, 1.00, 4, 6, 350, "ovate"),
    ("side", 398, 1536, -99,  742, -0.40, 0.58, 3, 5, 286, "ovate"),
    ("base", 716, 1528, -80,  520,  0.46, 0.34, 2, 4, 248, "lance"),
]

# 根。標本画は根まで描く。（株id, 根元x, 根元y, 本数）
ROOTS = [("main", 548, 1520, 9), ("side", 398, 1536, 6), ("base", 716, 1528, 5)]

CAPTION = "Campanula latifolia"
PLATE_NO = "PL. I"

# 花は下から順に「蕾 → 半開 → 開花」と熟す。総状花序はこの三態が
# 一本の茎に同居しているのが特徴で、そこが図版らしさの要になる。
FORMS = ["bell", "half", "bud"]


def stem_curve(base, angle, length, bow):
    tip = S.polar(base, angle, length)
    c1 = S.polar(base, angle + bow * 20, length * 0.36)
    c2 = S.polar(base, angle + bow * 42, length * 0.72)
    return base, c1, c2, tip


def build(seed=17):
    """株のリストを返す。各株は {"spec", "curve", "k", "sway", "organs"}。"""
    rng = random.Random(seed)
    stems = []

    for sid, bx, by, angle, length, bow, depth, nflo, nleaf, leaflen, leafkind in STEMS:
        p0, c1, c2, tip = stem_curve((bx, by), angle, length, bow)
        k = 0.52 + 0.48 * depth
        organs = []

        # 葉。下ほど大きく、交互につく。
        for i in range(nleaf):
            u = i / max(nleaf - 1, 1)
            t = 0.06 + 0.62 * u + rng.uniform(-0.02, 0.02)
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if i % 2 == 0 else 1
            ll = leaflen * (1.0 - 0.46 * u) * rng.uniform(0.92, 1.08)
            ll *= 0.64 + 0.36 * depth
            # 同じ形をきれいに並べると壁紙になる。図版の葉は一枚ごとに
            # 幅も反りも違い、真横を向いたものと手前へ倒れて短く見える
            # ものが混ざっている。その乱れが「観察して描いた」に見せる。
            fore = rng.uniform(0.72, 1.0) if rng.random() < 0.35 else 1.0
            lf = L.make(leafkind, at, ax + side * (54 + rng.uniform(-26, 30)),
                        ll * fore, ll * rng.uniform(0.25, 0.40),
                        bend=side * rng.uniform(0.10, 0.70))
            organs.append({
                "kind": "leaf", "id": f"{sid}.leaf{i}", "stem": sid, "t": t,
                "geo": lf, "k": k * rng.uniform(0.74, 1.16), "reach": ll,
                "pivot": at, "restAngle": ax,
                # 最下の葉 1 枚だけをタップ対象にする。数を絞って一つを大きく。
                "hit": ({"at": lf["anchor"], "r": ll * 0.38} if i == 0 else None),
                "sway": {"amp": round(5.0 + 6.0 * rng.random(), 2),
                         "speed": rng.choice([8, 9, 11, 13]),
                         "phase": round(rng.uniform(0, 6.28), 3)},
            })

        # 花序。茎の上 3 割に、先へ行くほど若い花をつける。
        for j in range(nflo):
            u = j / max(nflo - 1, 1)
            t = 0.56 + 0.30 * u
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if j % 2 == 0 else 1
            form = FORMS[min(int(u * len(FORMS) * 0.999), len(FORMS) - 1)]
            size = leaflen * 0.46 * (1.0 - 0.30 * u) * rng.uniform(0.95, 1.05)
            # 花柄は長めに。花どうしが離れて、指で押し分けられる。
            ped = size * (0.62 + 0.22 * rng.random())
            hang = S.polar(at, ax + side * 56, ped)
            pedicel = S.quad(at, S.polar(at, ax + side * 50, ped * 0.55), hang, 10)
            # 釣鐘は重さで下を向く。茎の向きに引きずられすぎると造花になる。
            face = ax + (90 - ax) * 0.78 + rng.uniform(-7, 7)
            organs.append({
                "kind": "flower", "form": form, "id": f"{sid}.flos{j}",
                "stem": sid, "t": t, "k": k, "size": size,
                "geo": flower_geo(form, hang, face, size),
                "pedicel": pedicel, "hang": hang, "face": face,
                "pivot": at, "restAngle": ax,
                "hit": {"at": S.polar(hang, face, size * 0.62), "r": size * 0.92},
                "sway": {"amp": round(7.0 + 7.0 * rng.random(), 2),
                         "speed": rng.choice([5, 6, 7]),
                         "phase": round(rng.uniform(0, 6.28), 3)},
            })

        stems.append({
            "id": sid, "curve": (p0, c1, c2, tip), "k": k, "depth": depth,
            "w0": 12.0 * (0.5 + 0.5 * depth), "w1": 3.4 * (0.5 + 0.5 * depth),
            "length": length, "organs": organs,
            "sway": {"amp": length * 0.055, "speed": 2,
                     "phase": round(rng.uniform(0, 6.28), 3)},
        })

    return stems


def flower_geo(form, base, face, size):
    """開花の度合いに応じた花の形。三態で別々の関数を呼ぶのではなく、
    同じ釣鐘の口の開き方だけを変える。途中の姿が自然につながる。"""
    if form == "bud":
        g = S.bud(base, face, size * 1.05, size * 0.30, ridges=4)
        g["form"] = "bud"
        return g
    open_ = 1.0 if form == "bell" else 0.42
    g = S.bell(base, face, size * 1.12, size * (0.80 + 0.26 * open_),
               lip=0.26 + 0.10 * open_, tooth=0.14 + 0.18 * open_,
               throat=True, visible_lobes=3.0, splay=20 + 18 * open_)
    g["form"] = form
    g["open"] = open_
    return g


def separate(targets, passes=4, floor=58.0):
    """タップ判定の円が重ならないところまで半径を詰める。

    重なったままだと、どちらを押したのか判定が「近い方」任せになり、
    指で狙ったものと違う部位が開く。中心間距離の半分までなら、判定は
    必ず一意になる。器官を減らして大きくした意味はここで出る。

    [targets] は [{"at": (x, y), "r": 半径}] を書き換える。
    """
    for _ in range(passes):
        moved = False
        for i in range(len(targets)):
            for j in range(i + 1, len(targets)):
                a, b = targets[i], targets[j]
                d = math.hypot(a["at"][0] - b["at"][0], a["at"][1] - b["at"][1])
                over = a["r"] + b["r"] - d
                if over <= 0.5:
                    continue
                f = d / (a["r"] + b["r"])
                a["r"] = max(floor, a["r"] * f)
                b["r"] = max(floor, b["r"] * f)
                moved = True
        if not moved:
            break
    return targets
