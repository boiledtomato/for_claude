"""植物の形。鉛筆で「引く線」として持つので、塗りではなく折れ線で返す。"""
import math

def noise(seed, i):
    """位置だけで決まる擬似乱数。0..1。

    乱数生成器を持ち回ると版面と実機で値がずれるので、形を決める揺らぎは
    すべて座標から決める。同じ株は何度描いても同じ形になる。
    """
    h = (int(seed * 7919) ^ (i * 2654435761)) & 0xFFFFFFFF
    h = (h ^ (h >> 15)) * 2246822519 & 0xFFFFFFFF
    h = (h ^ (h >> 13)) * 3266489917 & 0xFFFFFFFF
    return ((h ^ (h >> 16)) & 0xFFFF) / 65535.0


def jig(seed, i, amount):
    """-amount..+amount の揺らぎ。"""
    return (noise(seed, i) - 0.5) * 2.0 * amount


def polar(o, deg, d):
    r = math.radians(deg)
    return (o[0] + math.cos(r) * d, o[1] + math.sin(r) * d)

def quad(p0, c, p1, n=20):
    out = []
    for i in range(n + 1):
        t = i / n
        u = 1 - t
        out.append((u*u*p0[0] + 2*u*t*c[0] + t*t*p1[0],
                    u*u*p0[1] + 2*u*t*c[1] + t*t*p1[1]))
    return out

def cubic(p0, p1, p2, p3, n=28):
    out = []
    for i in range(n + 1):
        t = i / n; u = 1 - t
        a, b, c, d = u**3, 3*u*u*t, 3*u*t*t, t**3
        out.append((a*p0[0]+b*p1[0]+c*p2[0]+d*p3[0],
                    a*p0[1]+b*p1[1]+c*p2[1]+d*p3[1]))
    return out

def cubic_at(p0, p1, p2, p3, t):
    u = 1 - t
    a, b, c, d = u**3, 3*u*u*t, 3*u*t*t, t**3
    return (a*p0[0]+b*p1[0]+c*p2[0]+d*p3[0], a*p0[1]+b*p1[1]+c*p2[1]+d*p3[1])

def cubic_angle(p0, p1, p2, p3, t):
    u = 1 - t
    dx = 3*u*u*(p1[0]-p0[0]) + 6*u*t*(p2[0]-p1[0]) + 3*t*t*(p3[0]-p2[0])
    dy = 3*u*u*(p1[1]-p0[1]) + 6*u*t*(p2[1]-p1[1]) + 3*t*t*(p3[1]-p2[1])
    return math.degrees(math.atan2(dy, dx))

def lerp(a, b, t):
    return a + (b - a) * t


def leaf(attach, d, length, width, bend=0.0, teeth=0):
    """披針形の葉の輪郭（閉じた折れ線）と主脈・側脈。"""
    tip = polar(attach, d + bend * 16, length)
    waist = polar(attach, d + bend * 8, length * 0.36)
    left = polar(waist, d - 90, width)
    right = polar(waist, d + 90, width)
    e1 = quad(attach, left, tip, 22)
    e2 = quad(tip, right, attach, 22)

    if teeth:
        def serrate(edge, outdeg):
            out = []
            for i, p in enumerate(edge):
                t = i / (len(edge) - 1)
                env = math.sin(math.pi * t) ** 0.6      # 先端と付け根では歯を弱める
                amp = width * 0.045 * env
                out.append(polar(p, outdeg, amp) if i % 2 else p)
            return out
        e1 = serrate(e1, d - 90)
        e2 = serrate(e2, d + 90)

    outline = e1 + e2[1:]
    midrib = quad(attach, polar(attach, d + bend * 8, length * 0.5), tip, 16)
    veins = []
    for i in range(1, 4):
        t = i / 4
        on = cubic_at(attach, waist, waist, tip, t)
        span = width * 2 * t * (1 - t) * 0.8
        for side in (-1, 1):
            veins.append(quad(on, polar(on, d + side * 40, span * 0.6),
                              polar(on, d + side * 62, span), 8))
    flow = []
    for i in range(1, 24):
        t = i / 24
        on = quad(attach, polar(attach, d + bend * 8, length * 0.5), tip, 24)[int(t * 24)]
        reach = width * 2 * t * (1 - t) * 1.25 + width * 0.12
        for side in (-1, 1):
            flow.append(quad(on, polar(on, d + side * 46, reach * 0.6),
                             polar(on, d + side * 70, reach), 10))
    return {"outline": outline, "midrib": midrib, "veins": veins, "flow": flow,
            "tip": tip, "axis": d, "length": length, "width": width}


def _spline(ts, vs, t):
    """制御点を Catmull-Rom で滑らかに通す。釣鐘の輪郭は式より制御点の方が早い。"""
    if t <= ts[0]:
        return vs[0]
    if t >= ts[-1]:
        return vs[-1]
    i = max(i for i in range(len(ts) - 1) if ts[i] <= t)
    u = (t - ts[i]) / (ts[i + 1] - ts[i])
    p0 = vs[max(i - 1, 0)]; p1 = vs[i]; p2 = vs[i + 1]; p3 = vs[min(i + 2, len(vs) - 1)]
    return 0.5 * ((2 * p1) + (-p0 + p2) * u +
                  (2 * p0 - 5 * p1 + 4 * p2 - p3) * u * u +
                  (-p0 + 3 * p1 - 3 * p2 + p3) * u * u * u)


# 釣鐘の半幅プロファイル（幅に対する割合）。
# 萼から細く出て → 肩で丸くふくらみ → そこからほぼ平行に下り → 口で少し開く。
# 根元から口へ一直線に広げると円錐（ラッパ）になり、途中で最大にすると卵になる。
# 釣鐘に見える条件は「肩が丸く、胴が平行」であること。手鈴の形と同じ。
BELL_TS = [0.0, 0.08, 0.20, 0.38, 0.60, 0.82, 1.0]
BELL_RS = [0.05, 0.17, 0.33, 0.42, 0.455, 0.465, 0.52]


def bell(base, d, length, width, lobes=5, lip=0.34, tooth=0.42, throat=False,
         visible_lobes=3.0, splay=64.0):
    """ホタルブクロ型の釣鐘花。[base] に花柄がつき、[d] の向きに口が開く。"""
    n = 26
    sd = base[0] * 0.29 + base[1] * 0.19 + d * 0.013
    # 左右で輪郭をずらす。同じプロファイルを両側に使うと完全な鏡像になり、
    # 旋盤で挽いた器に見える。花冠はどこかが張り、どこかが凹んでいる。
    pl, pr = noise(sd, 11) * 6.28, noise(sd, 12) * 6.28
    al, ar = 0.07 + 0.07 * noise(sd, 13), 0.07 + 0.07 * noise(sd, 14)
    # 軸そのものも少し曲がる
    lean = jig(sd, 15, 5.0)
    left, right = [], []
    for i in range(n + 1):
        t = i / n
        r = width * _spline(BELL_TS, BELL_RS, t)
        on = polar(base, d + lean * t, length * t)
        left.append(polar(on, d - 90, r * (1.0 + al * math.sin(t * 3.1 + pl))))
        right.append(polar(on, d + 90, r * (1.0 + ar * math.sin(t * 2.6 + pr))))

    mouth = polar(base, d, length)
    ml, mr = left[-1], right[-1]
    rw = width * BELL_RS[-1]

    m = 52
    lip_ctrl = polar(mouth, d, rw * lip * 2)
    curve = quad(ml, lip_ctrl, mr, m)
    near = []
    for i, q in enumerate(curve):
        t = i / m
        # 横から見ると裂片は 3 枚ほどしか見えない。5 枚刻むと鋸の歯になる。
        #
        # 山が両端（t=0,1）に来てはいけない。両端は輪郭と口が出会う一点で、
        # ここが張り出すと口の奥が端まで同じ幅で残り、花が黒い帯に浸かって
        # 見える。谷を端に合わせ、さらに包絡で端をゼロに落とす。
        f = (t * visible_lobes) % 1.0
        tri = (1.0 - abs(f * 2 - 1.0)) ** 0.75
        tri *= math.sin(math.pi * t) ** 0.45
        # 裂片ごとに張り出しを変える。そろえると歯車の歯になる。
        tri *= 0.72 + 0.56 * noise(sd, 200 + int(t * visible_lobes))
        near.append(polar(q, d + (t - 0.5) * splay, rw * tooth * tri))

    outline = left + near[1:] + right[::-1][1:]

    # 裂片の稜。付け根まで引かない。一点に集めると傘の骨に見える。
    ribs = []
    for i in range(lobes):
        t = (i + 0.5) / lobes
        target = near[min(int(t * m), m)]
        start = polar(polar(base, d, length * 0.30), d + (t - 0.5) * 70, width * 0.16)
        mid = polar(polar(base, d, length * 0.68), d + (t - 0.5) * 54, width * 0.24)
        ribs.append(quad(start, mid, target, 14))

    # 面の流れ線。左右の輪郭を補間すると、釣鐘の丸みをそのままなぞる線束になる。
    # 付け根から放射させると根元に束が寄って本体が白く抜けてしまう。
    flow = []
    steps = 52
    for k in range(1, steps):
        u = k / steps
        line = [(lerp(a[0], b[0], u), lerp(a[1], b[1], u)) for a, b in zip(left, right)]
        # 口もとは裂片の側へ寄せる
        tip = near[min(int(u * m), m)]
        line[-1] = (lerp(line[-1][0], tip[0], 0.65), lerp(line[-1][1], tip[1], 0.65))
        flow.append(line)

    # 二度彫り用に、口の向きを横切る線束も持たせる
    cross = []
    for i in range(1, 13):
        t = i / 13
        on = cubic_at(base, polar(base, d, length * 0.4),
                      polar(base, d, length * 0.7), mouth, t)
        r = width * _spline(BELL_TS, BELL_RS, t)
        cross.append(quad(polar(on, d - 90, r), on, polar(on, d + 90, r), 8))

    out = {"outline": outline, "near": near, "ribs": ribs, "flow": flow,
           "cross": cross, "mouth": mouth, "base": base, "axis": d,
           "length": length, "width": width}
    if throat:
        # 口の奥。手前の縁（near）と向こう側の縁（far）にはさまれた面で、
        # 覗き込んでいるぶんいちばん暗い。ここを紙のまま残すと花に穴が空く。
        # 口は楕円に見える。向こう側の縁も手前と同じ向きに弓なりで、
        # 弓の深さだけが浅い。逆向きに張ると口が縦に広がって帯に見える。
        far = quad(ml, polar(mouth, d, rw * lip * 0.62), mr, m)
        out["far"] = far
        # 口の奥は、向こう側の縁と「歯をつける前の」手前の縁にはさまれた帯。
        # 歯の先まで奥に含めると、裂片の外側まで暗くなって、花が黒い泥に
        # 浸かったように見える。裂片の先はこちらを向いた表面で、明るい。
        out["throat"] = far + curve[::-1][1:]
        out["throat_flow"] = [quad(far[i], polar(mouth, d, rw * lip * 0.2),
                                   near[i], 7)
                              for i in range(1, m, 2)]
        sinus = []
        for i in range(int(visible_lobes) + 1):
            t = i / visible_lobes
            j = min(int(t * m), m)
            sinus.append([near[j], (lerp(near[j][0], far[j][0], 0.55),
                                    lerp(near[j][1], far[j][1], 0.55))])
        out["sinus"] = sinus
    return out


def star(center, d, radius, lobes=5):
    """正面から見た星形の花冠。"""
    outline = []
    n = lobes * 16
    for i in range(n + 1):
        t = i / n
        a = d + 360 * t
        f = (t * lobes) % 1.0
        tri = 1.0 - abs(f * 2 - 1.0)
        r = radius * lerp(0.28, 1.0, tri ** 1.35)
        outline.append(polar(center, a, r))
    ribs = [quad(center, polar(center, d + 360*(i+0.5)/lobes, radius*0.5),
                 polar(center, d + 360*(i+0.5)/lobes, radius*0.9), 10)
            for i in range(lobes)]
    stamens = []
    for i in range(lobes):
        a = d + 360 * (i + 0.5) / lobes + 36
        tipp = polar(center, a, radius * 0.42)
        stamens.append([center, polar(center, a, radius * 0.2), tipp])
    return {"outline": outline, "ribs": ribs, "stamens": stamens,
            "center": center, "radius": radius}


def bud(base, d, length, width, ridges=4):
    """まだ閉じた蕾。稜線を何本か入れると萼のねじれが出る。"""
    sd = base[0] * 0.23 + base[1] * 0.37 + d * 0.019
    # 蕾も左右対称ではない。花弁が巻きついているぶん、片側が膨らむ。
    apex = polar(base, d + jig(sd, 1, 5.0), length)
    wl = polar(base, d, length * (0.44 + jig(sd, 2, 0.08)))
    wr = polar(base, d, length * (0.44 + jig(sd, 3, 0.08)))
    left = polar(wl, d - 90, width * (0.84 + 0.32 * noise(sd, 4)))
    right = polar(wr, d + 90, width * (0.84 + 0.32 * noise(sd, 5)))
    outline = quad(base, left, apex, 20) + quad(apex, right, base, 20)[1:]
    lines = []
    for i in range(ridges):
        t = lerp(-0.62, 0.62, i / (ridges - 1))
        c = polar(polar(base, d, length * 0.45), d + 90, width * t * 1.5)
        lines.append(quad(base, c, apex, 16))
    return {"outline": outline, "ridges": lines, "apex": apex, "base": base,
            "axis": d, "length": length, "width": width}


def sepals(base, d, size, count=3, spread=52):
    out = []
    for i in range(count):
        a = d + lerp(-spread, spread, i / (count - 1)) if count > 1 else d
        out.append(leaf(base, a, size, size * 0.30, bend=(a - d) / 90))
    return out


def offset(pts, rel_deg, dist):
    """折れ線を、進行方向に対して [rel_deg] の向きへ [dist] ずらす。

    茎を「太い 1 本の線」ではなく「輪郭 2 本の筒」として描くのに使う。
    図版の茎は必ず筒として描かれていて、そこに調子が入っている。
    dist は t を受ける関数でもよい（根元が太く先が細い茎のため）。
    """
    n = len(pts)
    out = []
    for i, p in enumerate(pts):
        a = pts[min(i + 1, n - 1)]
        b = pts[max(i - 1, 0)]
        ang = math.degrees(math.atan2(a[1] - b[1], a[0] - b[0]))
        dd = dist(i / max(n - 1, 1)) if callable(dist) else dist
        out.append(polar(p, ang + rel_deg, dd))
    return out


def face(centre, d, radius, lobes=5, cut=0.46, sharp=1.5):
    """正面を向いた釣鐘花。

    参照図版でいちばん目を引くのはこの姿で、星形に 5 裂した裂片・そこへ
    放射する脈・中央の黄色い葯がそろって初めてそう見える。星形の多角形を
    置いただけでは紙を切り抜いた飾りにしかならない。裂片を 1 枚ずつ、
    付け根がくびれて先が尖る形に作り、谷で隣とつなぐ。
    """
    step = 360.0 / lobes
    sd = centre[0] * 0.31 + centre[1] * 0.17 + d * 0.011
    outline, sinus, veins = [], [], []

    # 裂片ごとの揺らぎを先に決める。谷は隣り合う裂片で共有するので、
    # ここで一度だけ作らないと輪郭が裂片の境目で割れる。
    # 崩しは控えめに。植物の非対称は「どこか少し違う」程度で、派手に
    # 振ると虫に食われた花になる。半径で ±6%、角度で裂片の 4% ほど。
    lobe_a = [d + step * i + jig(sd, i, step * 0.045) for i in range(lobes)]
    lobe_r = [radius * (0.94 + 0.12 * noise(sd, 40 + i)) for i in range(lobes)]
    # 最後の谷は一周ぶん（360度）を足して戻す。step を足すと位置がずれて、
    # 裂片の輪郭が閉じずに割れる。
    sinus_a = [(lobe_a[i] + lobe_a[(i + 1) % lobes] +
                (360.0 if i == lobes - 1 else 0.0)) / 2 for i in range(lobes)]
    sinus_r = [radius * cut * (0.93 + 0.14 * noise(sd, 70 + i)) for i in range(lobes)]

    for i in range(lobes):
        a, rr = lobe_a[i], lobe_r[i]
        # 谷（裂片のあいだ）。花冠は筒の途中まで裂けている
        j = (i - 1) % lobes
        pv = polar(centre, sinus_a[j], sinus_r[j])
        pw = polar(centre, sinus_a[i], sinus_r[i])
        tip = polar(centre, a + jig(sd, 100 + i, step * 0.03), rr)
        # 裂片の縁。左右のふくらみ方を変える。同じにすると鏡像になり、
        # 5 枚そろうと工業製品の星形に見える。
        f1 = 0.27 + 0.07 * noise(sd, 130 + i)
        f2 = 0.27 + 0.07 * noise(sd, 160 + i)
        c1 = polar(polar(centre, a - step * f1, rr * (0.84 + 0.06 * noise(sd, 190 + i))),
                   a, rr * 0.04)
        c2 = polar(polar(centre, a + step * f2, rr * (0.84 + 0.06 * noise(sd, 220 + i))),
                   a, rr * 0.04)
        outline += quad(pv, c1, tip, 12)[:-1] + quad(tip, c2, pw, 12)[:-1]
        sinus.append(quad(pw, polar(centre, sinus_a[i], sinus_r[i] * 0.5), centre, 7))
        # 脈。裂片の中心へ 1 本、両脇へ 2 本ずつ
        for f, ln in ((0.0, 0.90), (-0.26, 0.74), (0.26, 0.74),
                      (-0.44, 0.56), (0.44, 0.56)):
            veins.append(quad(polar(centre, a + f * step * 0.5, radius * 0.10),
                              polar(centre, a + f * step * 0.62, radius * ln * 0.55),
                              polar(centre, a + f * step * 0.80, radius * ln), 10))
    throat = [polar(centre, d + i * 6,
                    radius * cut * 0.80 * (0.94 + 0.10 * noise(sd, 250 + i // 6)))
              for i in range(60)]
    return {"outline": outline, "sinus": sinus, "veins": veins, "throat": throat,
            "centre": centre, "axis": d, "radius": radius, "mouth": centre,
            "base": centre, "length": radius, "width": radius}
