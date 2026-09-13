"""鉛筆の線質。Compose へ移植する前に、ここで見た目を決める。

鉛筆らしさは 3 つで出る:
  1. 筆圧 — 線の両端が細く薄く、中ほどが太く濃い
  2. 手のぶれ — 低周波のゆらぎ。白色ノイズだと「震え」になって汚い
  3. 紙の目 — 濃度が細かく途切れる
さらに、一本を少しずらして 2〜3 回引くと「探り描き」になり手描きに見える。
"""
import math
import random


class Pencil:
    def __init__(self, draw, rng=None, tone=(58, 56, 60)):
        self.d = draw
        self.rng = rng or random.Random(7)
        self.tone = tone

    # --- 補間 ---
    @staticmethod
    def _resample(pts, step):
        """折れ線を等間隔に打ち直す。筆圧を距離で扱えるようにするため。"""
        if len(pts) < 2:
            return pts
        out = [pts[0]]
        carry = 0.0
        for (x0, y0), (x1, y1) in zip(pts, pts[1:]):
            seg = math.hypot(x1 - x0, y1 - y0)
            if seg < 1e-6:
                continue
            t = carry
            while t < seg:
                k = t / seg
                out.append((x0 + (x1 - x0) * k, y0 + (y1 - y0) * k))
                t += step
            carry = t - seg
        out.append(pts[-1])
        return out

    def _wobble(self, n, amp, octaves=2):
        """低周波のゆらぎ。正弦を数本足すだけで手のぶれらしくなる。"""
        waves = []
        for o in range(octaves):
            waves.append((
                self.rng.uniform(0.6, 1.9) * (o + 1),      # 周波数
                self.rng.uniform(0, math.tau),             # 位相
                amp / (o + 1),                             # 振幅
            ))
        return [sum(a * math.sin(f * math.tau * i / max(n - 1, 1) + p)
                    for f, p, a in waves) for i in range(n)]

    def stroke(self, pts, width=1.7, tone=0.72, jitter=0.9, passes=2,
               taper=(0.35, 0.35), step=2.6, grain=0.34, seed=None):
        """一本の鉛筆線。[pts] は折れ線。"""
        if seed is not None:
            self.rng.seed(seed)
        base = self._resample(pts, step)
        n = len(base)
        if n < 2:
            return
        for p_i in range(passes):
            # 2 本目以降は薄く、少しずらす
            k = 1.0 if p_i == 0 else self.rng.uniform(0.38, 0.62)
            off = self._wobble(n, jitter * (1.0 if p_i == 0 else 1.7))
            offp = self._wobble(n, jitter * 0.7)
            pathw = []
            for i, (x, y) in enumerate(base):
                # 法線方向へずらす
                j = min(i + 1, n - 1)
                dx, dy = base[j][0] - x, base[j][1] - y
                ln = math.hypot(dx, dy) or 1.0
                nx, ny = -dy / ln, dx / ln
                pathw.append((x + nx * off[i] + offp[i] * 0.25,
                              y + ny * off[i] + offp[i] * 0.25))
            for i in range(n - 1):
                t = i / (n - 1)
                pr = self._pressure(t, taper)
                if self.rng.random() < grain * (1.0 - pr) * 0.9:
                    continue                                  # 紙の目で途切れる
                a = min(255, int(255 * tone * pr * k * self.rng.uniform(0.62, 1.0)))
                if a <= 3:
                    continue
                wv = width(t) if callable(width) else width
                w = max(1, round(wv * (0.55 + 0.45 * pr)))
                self.d.line([pathw[i], pathw[i + 1]], fill=self.tone + (a,), width=w)

    @staticmethod
    def _pressure(t, taper):
        """両端を細く。中ほどは少しうねらせる。"""
        a, b = taper
        head = min(1.0, t / a) if a > 0 else 1.0
        tail = min(1.0, (1 - t) / b) if b > 0 else 1.0
        env = (head * tail) ** 0.55
        return max(0.05, env * (0.85 + 0.15 * math.sin(t * 9.0)))

    def contour(self, pts, **kw):
        """輪郭線。主線は 1 本、探り線を 1 本添える。"""
        kw.setdefault("width", 2.0)
        kw.setdefault("tone", 0.8)
        kw.setdefault("jitter", 0.7)
        kw.setdefault("passes", 2)
        self.stroke(pts, **kw)

    def hatch(self, mask_box, angle, spacing, tone=0.45, width=1.3,
              jitter=0.8, length_jitter=0.22, gradient=None, cross_at=0.62,
              cross_delta=52):
        """[mask_box] を覆う平行線。呼び出し側で clip する前提。

        [gradient] = (方向deg, 明側の倍率, 暗側の倍率)。これが無いと、ただの
        均一な網目になって「面の向き」が出ない。濃い側は自動で交差させる。
        """
        x0, y0, x1, y1 = mask_box
        cx, cy = (x0 + x1) / 2, (y0 + y1) / 2
        diag = math.hypot(x1 - x0, y1 - y0) / 2 + spacing
        rad = math.radians(angle)
        ux, uy = math.cos(rad), math.sin(rad)
        vx, vy = -uy, ux

        if gradient:
            gdeg, lo, hi = gradient
            grad = math.radians(gdeg)
            gx, gy = math.cos(grad), math.sin(grad)
        k = int(diag / spacing) + 1
        for i in range(-k, k + 1):
            off = i * spacing + self.rng.uniform(-spacing * 0.18, spacing * 0.18)
            ex = diag * self.rng.uniform(1 - length_jitter, 1.0)
            mx, my = cx + vx * off, cy + vy * off
            a = (mx - ux * ex, my - uy * ex)
            b = (mx + ux * ex, my + uy * ex)

            f = 1.0
            if gradient:
                # 形の中心からの、光の向きに沿った位置を 0..1 に
                t = ((mx - cx) * gx + (my - cy) * gy) / (diag + 1e-6)
                f = lo + (hi - lo) * max(0.0, min(1.0, t * 0.5 + 0.5))
            if f <= 0.04:
                continue
            self.stroke([a, b],
                        width=width, tone=tone * f * self.rng.uniform(0.75, 1.08),
                        jitter=jitter, passes=1, taper=(0.2, 0.2), grain=0.4)
            if f >= cross_at:
                rad2 = math.radians(angle + cross_delta)
                u2x, u2y = math.cos(rad2), math.sin(rad2)
                e2 = diag * self.rng.uniform(0.55, 0.85)
                self.stroke([(mx - u2x * e2, my - u2y * e2), (mx + u2x * e2, my + u2y * e2)],
                            width=width * 0.9,
                            tone=tone * f * 0.62 * self.rng.uniform(0.7, 1.05),
                            jitter=jitter, passes=1, taper=(0.2, 0.2), grain=0.46)

    def hatch_curves(self, curves, tone=0.45, width=1.25, jitter=0.7,
                     gradient=None, span=(0.05, 1.0), cross_at=0.58):
        """曲線の束に沿って引くハッチング。

        平行線だと曲面が平らに見える。面の流れ（釣鐘なら稜、葉なら側脈）に
        沿わせると、そこではじめて立体として読める。
        """
        if gradient:
            gdeg, lo, hi = gradient
            grad = math.radians(gdeg)
            gx, gy = math.cos(grad), math.sin(grad)
            xs = [p[0] for c in curves for p in c]
            ys = [p[1] for c in curves for p in c]
            cx, cy = (min(xs) + max(xs)) / 2, (min(ys) + max(ys)) / 2
            reach = max(1e-6, max(math.hypot(p[0] - cx, p[1] - cy)
                                  for c in curves for p in c))
        for c in curves:
            if len(c) < 2:
                continue
            a = self.rng.uniform(span[0], span[0] + 0.18)
            b = self.rng.uniform(span[1] - 0.22, span[1])
            i0 = int(a * (len(c) - 1))
            i1 = max(i0 + 2, int(b * (len(c) - 1)))
            seg = c[i0:i1 + 1]
            f = 1.0
            if gradient:
                mid = seg[len(seg) // 2]
                t = ((mid[0] - cx) * gx + (mid[1] - cy) * gy) / reach
                f = lo + (hi - lo) * max(0.0, min(1.0, t * 0.5 + 0.5))
            if f <= 0.04:
                continue
            self.stroke(seg, width=width, tone=tone * f * self.rng.uniform(0.72, 1.1),
                        jitter=jitter, passes=1, taper=(0.25, 0.3), grain=0.4)
            if f >= cross_at:
                self.stroke(seg, width=width * 0.85,
                            tone=tone * f * 0.5 * self.rng.uniform(0.7, 1.05),
                            jitter=jitter * 2.4, passes=1, taper=(0.3, 0.3), grain=0.5)

    def weighted_contour(self, pts, light_deg, tone_lo=0.45, tone_hi=0.95, width=2.1):
        """輪郭の線の強さを、光の向きで変える。均一な線は「塗り絵」に見える。"""
        n = len(pts)
        if n < 3:
            return
        cx = sum(p[0] for p in pts) / n
        cy = sum(p[1] for p in pts) / n
        rad = math.radians(light_deg)
        gx, gy = math.cos(rad), math.sin(rad)
        span = max(1e-6, max(math.hypot(p[0] - cx, p[1] - cy) for p in pts))
        seg = []
        cur_f = None
        for p in pts + [pts[0]]:
            t = ((p[0] - cx) * gx + (p[1] - cy) * gy) / span
            f = max(0.0, min(1.0, t * 0.5 + 0.5))
            band = round(f * 4) / 4.0
            if cur_f is None:
                cur_f = band
            if band != cur_f and len(seg) > 1:
                self.stroke(seg, width=width * (0.78 + 0.34 * cur_f),
                            tone=tone_lo + (tone_hi - tone_lo) * cur_f,
                            jitter=0.65, passes=2, taper=(0.18, 0.18))
                seg = [seg[-1]]
                cur_f = band
            seg.append(p)
        if len(seg) > 1:
            self.stroke(seg, width=width * (0.78 + 0.34 * (cur_f or 0.5)),
                        tone=tone_lo + (tone_hi - tone_lo) * (cur_f or 0.5),
                        jitter=0.65, passes=2, taper=(0.18, 0.18))
