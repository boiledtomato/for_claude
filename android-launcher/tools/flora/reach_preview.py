"""蔦が伸びて先の蕾が咲くまでを、アプリと同じ手順で確かめる。

Reach.kt / FloraCanvas.drawReach と同じ式をここに写してある。端末に入れる
前にここで見ておかないと、「線が伸びただけ」になっていることに気づけない。
"""
import argparse
import json
import math
import random

from PIL import Image, ImageDraw

import shapes as S
import render as R
import palette as C
from paperlib import paper

A = "assets"


def fnv(seed, i):
    h = 2166136261 ^ (seed & 0xFFFFFFFF)
    for v in (i & 0xFF, (i >> 8) & 0xFF, (seed >> 16) & 0xFF):
        h = ((h ^ v) * 16777619) & 0xFFFFFFFF
    return h


def rand01(seed, i):
    return ((fnv(seed, i) >> 8) & 0xFFFF) / 65535.0


def tendril(start, heading, length, curl, seed, steps=42):
    pts = [start]
    h = heading
    step = length / steps
    for i in range(steps):
        t = (i + 1) / steps
        h += curl * (0.5 + 1.8 * t * t) + (rand01(seed, i) - 0.5) * 5
        pts.append(S.polar(pts[-1], h, step))
    return pts


def along(pts, t):
    n = len(pts) - 1
    f = min(max(t, 0.0), 0.9999) * n
    i = int(f)
    u = f - i
    p = (pts[i][0] + (pts[i + 1][0] - pts[i][0]) * u,
         pts[i][1] + (pts[i + 1][1] - pts[i][1]) * u)
    j, k = min(i + 1, n), max(i - 1, 0)
    return p, math.degrees(math.atan2(pts[j][1] - pts[k][1], pts[j][0] - pts[k][0]))


def waver(pts, seed, length):
    n = len(pts) - 1
    amp = length * 0.085
    waves = 1.6 + rand01(seed, 97) * 1.3
    phase = rand01(seed, 53) * 6.283
    out = []
    for i, p in enumerate(pts):
        t = i / n
        j, k = min(i + 1, n), max(i - 1, 0)
        ang = math.degrees(math.atan2(pts[j][1] - pts[k][1], pts[j][0] - pts[k][0]))
        env = min(t * 3.0, 1.0) * (1 - t * 0.35)
        out.append(S.polar(p, ang + 90, math.sin(t * waves * 2 * math.pi + phase) * amp * env))
    return out


def smooth(t):
    return t * t * (3 - 2 * t)


class Reach:
    def __init__(self, origin, heading, length, seed, apps, curl=1.1):
        self.origin, self.seed, self.apps = origin, seed, apps
        self.forks = max(len(apps), 1)
        self.mf = 0.55 if self.forks > 1 else 1.0
        self.main = waver(tendril(origin, heading, length * self.mf, curl, seed),
                          seed, length)
        self.branches = []
        if self.forks > 1:
            fork, ang = along(self.main, 0.999)
            for i in range(self.forks):
                br = tendril(fork, ang + (i - (self.forks - 1) / 2) * 46,
                             length * 0.62, curl * 0.6, seed + 7 * i, 26)
                self.branches.append(waver(br, seed + 7 * i, length * 0.55))
        self.tips = ([along(self.main, 0.999)] if self.forks <= 1
                     else [along(b, 0.999) for b in self.branches])
        self.leaves = self._leaves()

    def _on(self, vine, path, n, scale):
        out = []
        for i in range(n):
            t = 0.16 + 0.76 * (i / max(n - 1, 1))
            r = rand01(self.seed + vine * 31, i)
            out.append(dict(vine=vine, t=t, side=-1 if i % 2 == 0 else 1,
                            sprite=(i + vine + 1) % 3,
                            scale=scale * (0.62 - 0.14 * t) * (0.85 + 0.3 * r)))
        return out

    def _leaves(self):
        if self.forks <= 1:
            return self._on(-1, self.main, 6, 1.0)
        out = self._on(-1, self.main, 5, 1.0)
        for i, p in enumerate(self.branches):
            out += self._on(i, p, 4, 0.82)
        return out

    def main_grow(self, g):
        return min(g / self.mf, 1.0)

    def branch_grow(self, g):
        return min(max((g - 0.55) / 0.45, 0.0), 1.0)

    def grow_of(self, vine, g):
        return self.main_grow(g) if vine < 0 else self.branch_grow(g)


def place(dst, sprite, off, anchor, rot_deg, scale):
    """焼いた素材を、素材内の原点を軸に回して置く。"""
    w = max(1, int(sprite.width * scale))
    h = max(1, int(sprite.height * scale))
    im = sprite.resize((w, h), Image.LANCZOS)
    px, py = -off[0] * scale, -off[1] * scale        # 素材内の原点
    im = im.rotate(-rot_deg, resample=Image.BICUBIC, center=(px, py), expand=False)
    dst.alpha_composite(im, (int(round(anchor[0] - px)), int(round(anchor[1] - py))))


def draw_reach(img, rc, grow, bloom, cache, vine, plate_w=1496.0):
    d = ImageDraw.Draw(img, "RGBA")
    mg, bg = rc.main_grow(grow), rc.branch_grow(grow)

    def path_of(pts, prog):
        if prog >= 0.999:
            return pts
        n = max(1, int((len(pts) - 1) * prog))
        return pts[:n + 1]

    w = plate_w * 0.0064
    if mg > 0.01:
        R.stem(img, path_of(rc.main, mg), w, w * 0.5, 0.95, random.Random(3))
    for i, br in enumerate(rc.branches):
        if bg <= 0.01:
            break
        R.stem(img, path_of(br, bg), w * 0.78, w * 0.42, 0.9, random.Random(4 + i))

    for lv in rc.leaves:
        path = rc.main if lv["vine"] < 0 else rc.branches[lv["vine"]]
        prog = mg if lv["vine"] < 0 else bg
        op = min(max((prog - lv["t"]) / 0.26, 0.0), 1.0)
        if op <= 0.01:
            continue
        sp = vine["leaves"][lv["sprite"]]
        at, ang = along(path, lv["t"])
        place(img, cache[sp["image"]], sp["off"], at,
              ang + 90 + lv["side"] * 62, lv["scale"] * smooth(op))

    for i, (tip, ang) in enumerate(rc.tips):
        prog = rc.grow_of(-1 if not rc.branches else i, grow)
        if prog < 0.55:
            continue
        swell = min(max((prog - 0.55) / 0.45, 0.0), 1.0)
        idx = min(len(vine["bloom"]) - 1,
                  max(0, int(round(bloom * (len(vine["bloom"]) - 1)))))
        fr = vine["bloom"][idx]
        size = 112.0
        centre = S.polar(tip, ang, size * 0.40 * (0.55 + 0.45 * swell))
        place(img, cache[fr["image"]], fr["off"], centre, ang + 90,
              (size / vine["size"]) * (0.62 + 0.38 * smooth(swell)))


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default="reach.gif")
    ap.add_argument("--apps", type=int, default=1)
    ap.add_argument("--fps", type=int, default=14)
    ap.add_argument("--scale", type=float, default=0.46)
    args = ap.parse_args()

    m = json.load(open(f"{A}/manifest.json", encoding="utf-8"))
    vine = m["vine"]
    cache = {s["image"]: Image.open(f"{A}/{s['image']}").convert("RGBA")
             for s in vine["leaves"] + vine["bloom"]}

    pw = m["plate"]["width"]
    W, H = int(pw * 0.86), int(pw * 0.86)
    rc = Reach((pw * 0.17, H * 0.84), -46.0, pw * 0.42, 4242, ["a"] * args.apps)

    # 伸び 720ms → 間 220ms → 開花 620ms
    grow_n, hold_n, bloom_n = 16, 4, 14
    frames = []
    for i in range(grow_n + hold_n + bloom_n + 4):
        if i < grow_n:
            g, b = (i + 1) / grow_n, 0.0
        elif i < grow_n + hold_n:
            g, b = 1.0, 0.0
        else:
            g = 1.0
            b = min(1.0, (i - grow_n - hold_n + 1) / bloom_n)
        img = paper((W, H), base=(240, 235, 226), tooth=5.0)
        draw_reach(img, rc, g, b, cache, vine, pw)
        frames.append(img.convert("RGB").resize(
            (int(W * args.scale), int(H * args.scale)), Image.LANCZOS))
    frames += [frames[-1]] * 6
    pal = [f.convert("P", palette=Image.ADAPTIVE, colors=128) for f in frames]
    pal[0].save(args.out, save_all=True, append_images=pal[1:],
                duration=int(1000 / args.fps), loop=0, optimize=True)
    print(f"-> {args.out}  {len(frames)} frames")


if __name__ == "__main__":
    main()
