"""アプリが使う素材一式を書き出す。

    organs/<id>.webp   葉・花を 1 つずつ焼いたもの（透過つき）
    bloom/<id>/NN.webp 蕾が開く連続コマ
    manifest.json      茎の形、器官の付け根と揺れ、タップ判定

なぜ器官ごとに焼くのか:
    ハッチングは 1 枚の葉に 100 本以上引くので、毎フレーム描き直すには重すぎる。
    一度焼いてしまえば、あとは付け根を軸に回すだけで個別に動かせる。密度を
    落とさずに「葉が 1 枚ずつ羽ばたく」動きが作れる。

茎と、伸びていく蔦だけは形が毎フレーム変わるので、アプリ側で線を直に引く。
"""
import json
import math
import os
import random
import shutil
from PIL import Image, ImageDraw, ImageFilter
import numpy as np

import shapes as S
import leaves as L
from pencil import Pencil
from paperlib import clipped, bbox, smudge, fill_shape, edge_shade, crease
import plate as P

OUT = "assets"
PAD = 14
BLOOM_FRAMES = 12
PAPER = (246, 243, 236)


def organ_canvas(polys, pad=PAD):
    """器官を囲う透明カンバスと、版面座標への原点を返す。"""
    xs = [p[0] for poly in polys for p in poly]
    ys = [p[1] for poly in polys for p in poly]
    x0, y0 = min(xs) - pad, min(ys) - pad
    x1, y1 = max(xs) + pad, max(ys) + pad
    w, h = int(math.ceil(x1 - x0)), int(math.ceil(y1 - y0))
    img = Image.new("RGBA", (w, h), (0, 0, 0, 0))
    return img, (x0, y0)


def shift(poly, off):
    return [(p[0] - off[0], p[1] - off[1]) for p in poly]


def apply_mask(img, polys, off, feather=0.7):
    """形の外を透明にする。器官の紙地はここで不透明に残る。"""
    mask = Image.new("L", img.size, 0)
    md = ImageDraw.Draw(mask)
    for poly in polys:
        md.polygon([tuple(p) for p in shift(poly, off)], fill=255)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    img.putalpha(mask)
    return img


def draw_leaf_sprite(lf, k, rng):
    """葉 1 枚（複葉なら小葉すべて）を 1 枚の画像に焼く。"""
    polys = [part["outline"] for part in lf["parts"]]
    img, off = organ_canvas(polys)
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, P.GRAPHITE)

    def sub():
        return random.Random(rng.randrange(1 << 30))

    def hatch(poly, curves, tone, width, span, lo):
        clipped(img, shift(poly, off), lambda dd: Pencil(dd, sub(), P.GRAPHITE)
                .hatch_curves([shift(c, off) for c in curves], tone=tone, width=width,
                              span=span, gradient=(P.LIGHT + 180, lo, 1.0)))

    if "stalk" in lf:
        p.stroke(shift(lf["stalk"], off), width=2.2, tone=0.6 * k, jitter=0.5)
    if "rachis" in lf:
        p.stroke(shift(lf["rachis"], off), width=2.4, tone=0.66 * k, jitter=0.5)

    for part in lf["parts"]:
        o = part["outline"]
        fill_shape(img, shift(o, off), color=PAPER)
        hatch(o, part["flow"], 1.45 * k, 0.8, (0.02, 1.0), 0.42)
        hatch(o, part["cross"], 0.62 * k, 0.8, (0.06, 0.96), 0.30)
        edge_shade(img, shift(o, off), P.GRAPHITE, alpha=int(104 * k),
                   band=max(7.0, lf["reach"] * 0.10), gradient_deg=P.LIGHT + 180)
        crease(img, shift(part["midrib"], off), P.GRAPHITE, alpha=int(66 * k),
               width=max(3.0, lf["reach"] * 0.024))
        p.weighted_contour(shift(o, off), P.LIGHT, 0.30 * k, 0.88 * k, 1.5)
        p.stroke(shift(part["midrib"], off), width=1.3, tone=0.58 * k, jitter=0.5)
        for v in part["veins"]:
            p.stroke(shift(v, off), width=0.8, tone=0.38 * k, jitter=0.5, passes=1)

    return apply_mask(img, polys, off), off


def draw_flower_sprite(shape, flow, cross, k, rng, reach):
    img, off = organ_canvas([shape])
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, P.GRAPHITE)

    def sub():
        return random.Random(rng.randrange(1 << 30))

    def hatch(curves, tone, lo):
        clipped(img, shift(shape, off), lambda dd: Pencil(dd, sub(), P.GRAPHITE)
                .hatch_curves([shift(c, off) for c in curves], tone=tone, width=0.8,
                              span=(0.06, 0.96), gradient=(P.LIGHT + 180, lo, 1.0)))

    fill_shape(img, shift(shape, off), color=PAPER)
    hatch(flow, 1.55 * k, 0.34)
    if cross:
        hatch(cross, 0.58 * k, 0.28)
    edge_shade(img, shift(shape, off), P.GRAPHITE, alpha=int(108 * k),
               band=max(6.0, reach * 0.14), gradient_deg=P.LIGHT + 180)
    p.weighted_contour(shift(shape, off), P.LIGHT, 0.30 * k, 0.88 * k, 1.5)
    return apply_mask(img, [shape], off), off


def draw_parts_sprite(parts, k, rng, reach):
    """{outline, flow, cross, midrib, veins} の集まりを 1 枚に焼く汎用版。"""
    polys = [p["outline"] for p in parts]
    img, off = organ_canvas(polys)
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, P.GRAPHITE)

    def sub():
        return random.Random(rng.randrange(1 << 30))

    for part in parts:
        o = part["outline"]
        fill_shape(img, shift(o, off), color=PAPER)
        for curves, tone, lo in ((part.get("flow"), 1.45 * k, 0.42),
                                 (part.get("cross"), 0.60 * k, 0.30)):
            if not curves:
                continue
            clipped(img, shift(o, off), lambda dd, c=curves, t=tone, l=lo:
                    Pencil(dd, sub(), P.GRAPHITE)
                    .hatch_curves([shift(x, off) for x in c], tone=t, width=0.8,
                                  span=(0.04, 0.98), gradient=(P.LIGHT + 180, l, 1.0)))
        edge_shade(img, shift(o, off), P.GRAPHITE, alpha=int(104 * k),
                   band=max(6.0, reach * 0.10), gradient_deg=P.LIGHT + 180)
        if part.get("midrib"):
            crease(img, shift(part["midrib"], off), P.GRAPHITE, alpha=int(60 * k),
                   width=max(3.0, reach * 0.024))
        p.weighted_contour(shift(o, off), P.LIGHT, 0.30 * k, 0.88 * k, 1.5)
        if part.get("midrib"):
            p.stroke(shift(part["midrib"], off), width=1.3, tone=0.58 * k, jitter=0.5)
        for v in part.get("veins", []):
            p.stroke(shift(v, off), width=0.8, tone=0.38 * k, jitter=0.5, passes=1)
    return apply_mask(img, polys, off), off


def bloom_parts(centre, axis, size, openness, petals=6):
    """蕾から花への連続変化。0 で閉じた蕾、1 で満開。

    花弁は常に同じ枚数を描き、開き具合で角度と大きさを変えるだけ。閉じている
    ときは深く重なるので、外側の 1 枚の輪郭だけが見えて蕾の形になる。
    各花弁は紙地で塗ってから描くので、重なった内側の線は隠れる。
    """
    o = openness * openness * (3 - 2 * openness)
    parts = []
    # 萼。閉じているときほど深く包む。
    sep = S.lerp(1.0, 0.45, o)
    for i in range(3):
        a = axis + S.lerp(-54, 54, i / 2)
        parts.append(L._blade(centre, a, size * 0.58 * sep, size * 0.20 * sep,
                              (a - axis) / 90, 0, 0.40))
    hub = size * 0.30 * o
    for i in range(petals):
        spread = S.lerp(6.5, 360.0 / petals, o)
        ang = axis + (i - (petals - 1) / 2) * spread
        ln = size * S.lerp(1.02, 1.30, o)
        wd = size * S.lerp(0.17, 0.46, o)
        root = S.polar(centre, ang, hub)
        parts.append(L._blade(root, ang, ln, wd, 0.0, 0, 0.38))
    return parts


def draw_umbel_sprite(centre, axis, size, k, rng):
    """散形花序。放射する柄の先に小花が集まる。

    円盤ひとつで代用すると睡蓮の葉に見える。柄と小花を描き分ける必要がある。
    """
    rays = 11
    florets, stalks = [], []
    for a in range(rays):
        ang = axis - 90 + (a - (rays - 1) / 2) * 17
        end = S.polar(centre, ang, size * (0.72 + 0.16 * math.sin(a * 1.7)))
        # 柄を 1 点に集めると線が重なって黒い点になる。少し手前から出す。
        root = S.polar(centre, ang, size * 0.14)
        stalks.append(S.quad(root, S.polar(centre, ang, size * 0.42), end, 7))
        head = S.star(end, ang, size * 0.17, lobes=5)
        florets.append({"outline": head["outline"], "flow": head["ribs"],
                        "cross": None, "midrib": None, "veins": []})

    polys = [f["outline"] for f in florets]
    img, off = organ_canvas(polys + stalks, pad=PAD)
    d = ImageDraw.Draw(img, "RGBA")
    p = Pencil(d, rng, P.GRAPHITE)
    for st in stalks:
        p.stroke(shift(st, off), width=1.4, tone=0.46 * k, jitter=0.4, passes=1)

    def sub():
        return random.Random(rng.randrange(1 << 30))

    for f in florets:
        o = f["outline"]
        fill_shape(img, shift(o, off), color=PAPER)
        clipped(img, shift(o, off), lambda dd, c=f["flow"]:
                Pencil(dd, sub(), P.GRAPHITE)
                .hatch_curves([shift(x, off) for x in c], tone=1.2 * k, width=0.8,
                              span=(0.05, 0.97), gradient=(P.LIGHT + 180, 0.35, 1.0)))
        p.weighted_contour(shift(o, off), P.LIGHT, 0.28 * k, 0.80 * k, 1.3)

    # 柄は細いので、形の外を落とすマスクには含めず、線だけ残す
    mask = Image.new("L", img.size, 0)
    md = ImageDraw.Draw(mask)
    for poly in polys:
        md.polygon([tuple(q) for q in shift(poly, off)], fill=255)
    for st in stalks:
        md.line([tuple(q) for q in shift(st, off)], fill=255, width=4)
    mask = mask.filter(ImageFilter.GaussianBlur(0.7))
    img.putalpha(mask)
    return img, off


def cubic_pts(p0, p1, p2, p3, n=40):
    return [S.cubic_at(p0, p1, p2, p3, i / n) for i in range(n + 1)]


def main():
    rng = random.Random(17)
    if os.path.isdir(OUT):
        shutil.rmtree(OUT)
    os.makedirs(f"{OUT}/organs", exist_ok=True)
    os.makedirs(f"{OUT}/bloom", exist_ok=True)

    manifest = {
        "plate": {"width": P.W, "height": P.H, "paper": "#F6F3EC"},
        "stems": [], "organs": [], "gemma": None,
    }
    z = 0

    for sid, bx, by, angle, length, bow, depth, kind, nflo, nleaf, leaflen, leafkind in P.STEMS:
        p0, c1, c2, tip = P.stem_curve((bx, by), angle, length, bow)
        k = 0.52 + 0.48 * depth
        manifest["stems"].append({
            "id": sid,
            "p": [list(p0), list(c1), list(c2), list(tip)],
            "w0": 12.0 * (0.5 + 0.5 * depth), "w1": 3.4 * (0.5 + 0.5 * depth),
            "tone": 0.98 * k, "depth": depth,
            "sway": {"amp": length * 0.055, "speed": 2,
                     "phase": round(rng.uniform(0, 6.28), 3)},
        })

        for i in range(nleaf):
            t = 0.05 + 0.72 * (i / max(nleaf - 1, 1)) + rng.uniform(-0.02, 0.02)
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if i % 2 == 0 else 1
            ll = leaflen * (1.0 - 0.50 * (i / max(nleaf - 1, 1))) * rng.uniform(0.9, 1.1)
            ll *= 0.62 + 0.38 * depth
            off_angle = side * (52 + rng.uniform(-26, 30))
            lf = L.make(leafkind, at, ax + off_angle, ll, ll * 0.31,
                        bend=side * rng.uniform(0.15, 0.55))
            lk = k * rng.uniform(0.72, 1.18)
            img, off = draw_leaf_sprite(lf, lk, random.Random(rng.randrange(1 << 30)))
            name = f"{sid}.leaf{i}"
            img.save(f"{OUT}/organs/{name}.webp", "WEBP", quality=92, method=6)
            z += 1
            manifest["organs"].append({
                "id": name, "stem": sid, "t": round(t, 4),
                "image": f"organs/{name}.webp",
                "off": [round(off[0], 1), round(off[1], 1)],
                "pivot": [round(at[0], 1), round(at[1], 1)],
                "restAngle": round(ax, 2),
                "sway": {"amp": round(6.0 + 6.0 * rng.random(), 2),
                         "speed": rng.choice([8, 9, 11, 13]),
                         "phase": round(rng.uniform(0, 6.28), 3)},
                "hit": ({"at": [round(lf["anchor"][0], 1), round(lf["anchor"][1], 1)],
                         "r": round(lf["reach"] * 0.30, 1)}
                        if i < 3 and depth > 0.3 else None),
                "z": z,
            })

        if kind == "none":
            continue

        for j in range(nflo):
            u = j / max(nflo - 1, 1)
            t = 0.70 + 0.30 * u
            at = S.cubic_at(p0, c1, c2, tip, t)
            ax = S.cubic_angle(p0, c1, c2, tip, t)
            side = -1 if j % 2 == 0 else 1
            size = leaflen * 0.34 * (1.0 - 0.30 * u) * rng.uniform(0.9, 1.1)
            hang = S.polar(at, ax + side * 40, size * 0.5)

            flow = cross = None
            if kind == "bell":
                fl = S.bell(hang, ax + (90 - ax) * 0.72, size * 1.25, size)
                shape, flow, cross = fl["outline"], fl["flow"], fl["cross"]
            elif kind == "spike":
                fl = S.star(S.polar(hang, ax + side * 40, size * 0.4), ax - 90, size * 0.82)
                shape, flow = fl["outline"], fl["ribs"]
                shape = fl["outline"]
            else:
                shape = None

            if shape is None:
                centre = S.polar(hang, ax, size * 0.34)
                img, off = draw_umbel_sprite(centre, ax, size, k,
                                             random.Random(rng.randrange(1 << 30)))
            else:
                img, off = draw_flower_sprite(shape, flow, cross, k,
                                              random.Random(rng.randrange(1 << 30)), size)
            name = f"{sid}.flos{j}"
            img.save(f"{OUT}/organs/{name}.webp", "WEBP", quality=92, method=6)
            z += 1
            manifest["organs"].append({
                "id": name, "stem": sid, "t": round(t, 4),
                "image": f"organs/{name}.webp",
                "off": [round(off[0], 1), round(off[1], 1)],
                "pivot": [round(at[0], 1), round(at[1], 1)],
                "restAngle": round(ax, 2),
                "sway": {"amp": round(8.0 + 7.0 * rng.random(), 2),
                         "speed": rng.choice([5, 6, 7]),
                         "phase": round(rng.uniform(0, 6.28), 3)},
                "hit": {"at": [round(hang[0], 1), round(hang[1], 1)],
                        "r": round(size * 0.8, 1)},
                "z": z,
            })

    # --- アプリ一覧を開く蕾。手前の株の先端につける ---
    host = manifest["stems"][1]
    hp = [tuple(v) for v in host["p"]]
    at = S.cubic_at(*hp, 1.0)
    ax = S.cubic_angle(*hp, 1.0)
    size = 120.0
    frames = []
    for f in range(BLOOM_FRAMES):
        o = f / (BLOOM_FRAMES - 1)
        parts = bloom_parts(at, ax, size, o)
        img, off = draw_parts_sprite(parts, 0.98, random.Random(991 + f), size * 1.6)
        fn = f"bloom/{f:02d}.webp"
        img.save(f"{OUT}/{fn}", "WEBP", quality=92, method=6)
        frames.append({"image": fn, "off": [round(off[0], 1), round(off[1], 1)]})
    manifest["gemma"] = {
        "id": "gemma", "stem": host["id"], "t": 1.0,
        "pivot": [round(at[0], 1), round(at[1], 1)],
        "restAngle": round(ax, 2),
        "frames": frames,
        "hit": {"at": [round(S.polar(at, ax, size * 0.6)[0], 1),
                       round(S.polar(at, ax, size * 0.6)[1], 1)], "r": round(size * 0.95, 1)},
        "sway": {"amp": 9.0, "speed": 6, "phase": 0.7},
    }

    with open(f"{OUT}/manifest.json", "w", encoding="utf-8") as f:
        json.dump(manifest, f, ensure_ascii=False, indent=1)

    total = sum(os.path.getsize(os.path.join(dp, f))
                for dp, _, fs in os.walk(OUT) for f in fs)
    hits = sum(1 for o in manifest["organs"] if o["hit"])
    print(f"organs={len(manifest['organs'])} (tappable {hits})  "
          f"stems={len(manifest['stems'])}  bloom={BLOOM_FRAMES}  "
          f"total={total//1024} KB")


if __name__ == "__main__":
    import time
    t0 = time.time()
    main()
    print(f"{time.time()-t0:.1f}s")
