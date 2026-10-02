#!/usr/bin/env python3
"""カテゴリー配色の検証。

15 色は categorical palette の基準（通常視 ΔE 15 / CVD ΔE 8）を満たせない ― 色相で
15 色に割ると CVD では赤緑が必ず潰れる。だからこそ「目視で決めない」ことに意味がある:
配色を触ったら必ずこれを走らせ、実測値が下がっていないかを見る。

    python3 tools/palette_check.py            # 現在の Color.kt を読んで検証する
    python3 tools/palette_check.py --optimize light   # 明るい面向けの候補を探す

判定に使うもの:

- ΔE2000（通常視）
- ΔE2000（protan / deutan を Viénot 1999 で近似したうえで）
- 面に対する WCAG コントラスト比（図形なので 3:1 を下限にする）
"""
from __future__ import annotations

import argparse
import math
import random
import re
from pathlib import Path

# ---- 色空間 ---------------------------------------------------------------


def srgb_to_linear(c: float) -> float:
    return c / 12.92 if c <= 0.04045 else ((c + 0.055) / 1.055) ** 2.4


def linear_to_srgb(c: float) -> float:
    c = max(0.0, min(1.0, c))
    return c * 12.92 if c <= 0.0031308 else 1.055 * (c ** (1 / 2.4)) - 0.055


def hex_to_rgb(value: str) -> tuple[float, float, float]:
    value = value.lstrip("#")
    if len(value) == 8:  # 0xAARRGGBB 形式
        value = value[2:]
    return tuple(int(value[i:i + 2], 16) / 255 for i in (0, 2, 4))


def rgb_to_hex(rgb) -> str:
    return "#%02X%02X%02X" % tuple(round(max(0.0, min(1.0, c)) * 255) for c in rgb)


def rgb_to_xyz(rgb):
    r, g, b = (srgb_to_linear(c) for c in rgb)
    return (
        0.4124564 * r + 0.3575761 * g + 0.1804375 * b,
        0.2126729 * r + 0.7151522 * g + 0.0721750 * b,
        0.0193339 * r + 0.1191920 * g + 0.9503041 * b,
    )


def xyz_to_rgb(xyz):
    x, y, z = xyz
    r = 3.2404542 * x - 1.5371385 * y - 0.4985314 * z
    g = -0.9692660 * x + 1.8760108 * y + 0.0415560 * z
    b = 0.0556434 * x - 0.2040259 * y + 1.0572252 * z
    return tuple(linear_to_srgb(c) for c in (r, g, b))


WHITE = (0.95047, 1.0, 1.08883)


def xyz_to_lab(xyz):
    def f(t):
        return t ** (1 / 3) if t > 216 / 24389 else (841 / 108) * t + 4 / 29

    fx, fy, fz = (f(c / w) for c, w in zip(xyz, WHITE))
    return (116 * fy - 16, 500 * (fx - fy), 200 * (fy - fz))


def lab_to_xyz(lab):
    l, a, b = lab
    fy = (l + 16) / 116
    fx = fy + a / 500
    fz = fy - b / 200

    def inv(t):
        return t ** 3 if t ** 3 > 216 / 24389 else (108 / 841) * (t - 4 / 29)

    return tuple(inv(c) * w for c, w in zip((fx, fy, fz), WHITE))


def rgb_to_lab(rgb):
    return xyz_to_lab(rgb_to_xyz(rgb))


def lch_to_rgb(l, c, h_deg):
    h = math.radians(h_deg)
    return xyz_to_rgb(lab_to_xyz((l, c * math.cos(h), c * math.sin(h))))


def lab_to_lch(lab):
    l, a, b = lab
    return (l, math.hypot(a, b), math.degrees(math.atan2(b, a)) % 360)


def in_gamut(rgb) -> bool:
    return all(-0.001 <= c <= 1.001 for c in rgb)


# ---- ΔE2000 ---------------------------------------------------------------


def delta_e(lab1, lab2) -> float:
    l1, a1, b1 = lab1
    l2, a2, b2 = lab2
    avg_l = (l1 + l2) / 2
    c1, c2 = math.hypot(a1, b1), math.hypot(a2, b2)
    avg_c = (c1 + c2) / 2
    g = 0.5 * (1 - math.sqrt(avg_c ** 7 / (avg_c ** 7 + 25 ** 7))) if avg_c else 0
    a1p, a2p = a1 * (1 + g), a2 * (1 + g)
    c1p, c2p = math.hypot(a1p, b1), math.hypot(a2p, b2)
    avg_cp = (c1p + c2p) / 2
    h1p = math.degrees(math.atan2(b1, a1p)) % 360
    h2p = math.degrees(math.atan2(b2, a2p)) % 360
    dlp = l2 - l1
    dcp = c2p - c1p
    if c1p * c2p == 0:
        dhp = 0.0
    elif abs(h2p - h1p) <= 180:
        dhp = h2p - h1p
    elif h2p - h1p > 180:
        dhp = h2p - h1p - 360
    else:
        dhp = h2p - h1p + 360
    dhp = 2 * math.sqrt(c1p * c2p) * math.sin(math.radians(dhp) / 2)
    if c1p * c2p == 0:
        avg_hp = h1p + h2p
    elif abs(h1p - h2p) <= 180:
        avg_hp = (h1p + h2p) / 2
    elif h1p + h2p < 360:
        avg_hp = (h1p + h2p + 360) / 2
    else:
        avg_hp = (h1p + h2p - 360) / 2
    t = (
        1
        - 0.17 * math.cos(math.radians(avg_hp - 30))
        + 0.24 * math.cos(math.radians(2 * avg_hp))
        + 0.32 * math.cos(math.radians(3 * avg_hp + 6))
        - 0.20 * math.cos(math.radians(4 * avg_hp - 63))
    )
    sl = 1 + (0.015 * (avg_l - 50) ** 2) / math.sqrt(20 + (avg_l - 50) ** 2)
    sc = 1 + 0.045 * avg_cp
    sh = 1 + 0.015 * avg_cp * t
    rt = (
        -2
        * math.sqrt(avg_cp ** 7 / (avg_cp ** 7 + 25 ** 7))
        * math.sin(math.radians(60 * math.exp(-(((avg_hp - 275) / 25) ** 2))))
    )
    return math.sqrt(
        (dlp / sl) ** 2 + (dcp / sc) ** 2 + (dhp / sh) ** 2 + rt * (dcp / sc) * (dhp / sh)
    )


# ---- 色覚特性の近似（Viénot 1999） ----------------------------------------

_LMS = (
    (0.31399022, 0.63951294, 0.04649755),
    (0.15537241, 0.75789446, 0.08670142),
    (0.01775239, 0.10944209, 0.87256922),
)
_LMS_INV = (
    (5.47221206, -4.6419601, 0.16963708),
    (-1.1252419, 2.29317094, -0.1678952),
    (0.02980165, -0.19318073, 1.16364789),
)
_SIM = {
    "protan": ((0.0, 1.05118294, -0.05116099), (0.0, 1.0, 0.0), (0.0, 0.0, 1.0)),
    "deutan": ((1.0, 0.0, 0.0), (0.9513092, 0.0, 0.04264592), (0.0, 0.0, 1.0)),
}


def _mul(m, v):
    return tuple(sum(m[i][j] * v[j] for j in range(3)) for i in range(3))


def simulate(rgb, kind: str):
    lin = tuple(srgb_to_linear(c) for c in rgb)
    lms = _mul(_LMS, lin)
    out = _mul(_SIM[kind], lms)
    back = _mul(_LMS_INV, out)
    return tuple(linear_to_srgb(c) for c in back)


def luminance(rgb) -> float:
    r, g, b = (srgb_to_linear(c) for c in rgb)
    return 0.2126 * r + 0.7152 * g + 0.0722 * b


def contrast(rgb, other) -> float:
    a, b = luminance(rgb), luminance(other)
    hi, lo = max(a, b), min(a, b)
    return (hi + 0.05) / (lo + 0.05)


# ---- 検証 ------------------------------------------------------------------


def report(name: str, colors: list[str], surface: str) -> dict:
    rgbs = [hex_to_rgb(c) for c in colors]
    surface_rgb = hex_to_rgb(surface)
    labs = [rgb_to_lab(c) for c in rgbs]
    pairs = [(i, j) for i in range(len(rgbs)) for j in range(i + 1, len(rgbs))]

    normal = min(delta_e(labs[i], labs[j]) for i, j in pairs)
    cvd = min(
        min(
            delta_e(rgb_to_lab(simulate(rgbs[i], k)), rgb_to_lab(simulate(rgbs[j], k)))
            for i, j in pairs
        )
        for k in _SIM
    )
    worst_contrast = min(contrast(c, surface_rgb) for c in rgbs)
    worst_pair = min(pairs, key=lambda p: delta_e(labs[p[0]], labs[p[1]]))

    print(f"[{name}] surface {surface}")
    print(f"  normal  min ΔE2000 = {normal:5.1f}   (floor 15)  worst pair {colors[worst_pair[0]]}/{colors[worst_pair[1]]}")
    print(f"  CVD     min ΔE2000 = {cvd:5.1f}   (floor 8)")
    print(f"  contrast vs surface = {worst_contrast:5.2f}:1 (floor 3.0)")
    return {"normal": normal, "cvd": cvd, "contrast": worst_contrast}


# ---- 明るい面向けの候補探し ------------------------------------------------


def score(rgbs, surface_rgb, min_contrast):
    labs = [rgb_to_lab(c) for c in rgbs]
    pairs = [(i, j) for i in range(len(rgbs)) for j in range(i + 1, len(rgbs))]
    normal = min(delta_e(labs[i], labs[j]) for i, j in pairs)
    cvd = min(
        min(
            delta_e(rgb_to_lab(simulate(rgbs[i], k)), rgb_to_lab(simulate(rgbs[j], k)))
            for i, j in pairs
        )
        for k in _SIM
    )
    worst = min(contrast(c, surface_rgb) for c in rgbs)
    penalty = 0.0 if worst >= min_contrast else (min_contrast - worst) * 40
    # CVD 側が常に効くので重めに見る
    return normal + 2.2 * cvd - penalty, normal, cvd, worst


def optimize(reference: list[str], surface: str, l_range, hue_drift, seed=7, rounds=24000):
    """元の色相を保ったまま、明るい面で分離する L*/C* を探す。

    色相を大きく動かさないのは、暗い配色と明るい配色で「同じスロットは同じ色」に
    見せるため。スロットの対応が崩れると、テーマを切り替えたときに別のカテゴリーに
    見える。
    """
    rng = random.Random(seed)
    surface_rgb = hex_to_rgb(surface)
    base = [lab_to_lch(rgb_to_lab(hex_to_rgb(c))) for c in reference]

    def sample(i):
        _, _, h0 = base[i]
        for _ in range(120):
            h = (h0 + rng.uniform(-hue_drift, hue_drift)) % 360
            l = rng.uniform(*l_range)
            c = rng.uniform(20, 120)
            rgb = lch_to_rgb(l, c, h)
            if in_gamut(rgb):
                return tuple(min(1.0, max(0.0, v)) for v in rgb)
        return hex_to_rgb(reference[i])

    current = [sample(i) for i in range(len(reference))]
    best_score = score(current, surface_rgb, 3.0)[0]

    for _ in range(rounds):
        i = rng.randrange(len(current))
        trial = list(current)
        trial[i] = sample(i)
        s = score(trial, surface_rgb, 3.0)[0]
        if s > best_score:
            current, best_score = trial, s
    return [rgb_to_hex(c) for c in current], score(current, surface_rgb, 3.0)


# ---- Color.kt の読み取り ---------------------------------------------------


def read_scheme(path: Path, name: str) -> dict[str, str]:
    """`val <name> = ZColorScheme( ... )` の中の単色トークンを読む。"""
    text = path.read_text()
    block = re.search(rf"val {name} = ZColorScheme\((.*?)\n\)", text, re.S)
    if not block:
        raise SystemExit(f"{name} が {path} に見つからない")
    return {
        m.group(1): "#" + m.group(2)
        for m in re.finditer(r"(\w+) = Color\(0xFF([0-9A-Fa-f]{6})\)", block.group(1))
    }


def report_text(name: str, scheme: dict[str, str]) -> None:
    """文字と面の組み合わせ。読めない組が 1 つでもあれば配色として失敗している。"""
    print(f"[{name}] 文字コントラスト")
    for fg in ("textPrimary", "textSecondary", "textDim", "accentSoft"):
        for bg in ("background", "surface", "surfaceHigh"):
            ratio = contrast(hex_to_rgb(scheme[fg]), hex_to_rgb(scheme[bg]))
            floor = 4.5 if fg == "textPrimary" else 3.0
            mark = "ok " if ratio >= floor else "NG "
            print(f"  {mark}{fg:<14} on {bg:<12} {ratio:5.2f}:1 (floor {floor})")


def read_palette(path: Path, name: str) -> list[str]:
    text = path.read_text()
    block = re.search(rf"val {name} = listOf\((.*?)\n\)", text, re.S)
    if not block:
        raise SystemExit(f"{name} が {path} に見つからない")
    return ["#" + m.group(1) for m in re.finditer(r"0xFF([0-9A-Fa-f]{6})", block.group(1))]


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--color-file", default="app/src/main/java/com/example/zlauncher/core/designsystem/Color.kt")
    parser.add_argument("--optimize", choices=["light", "light-chart"], help="候補を探して出力する")
    args = parser.parse_args()

    path = Path(args.color_file)
    dark_identity = read_palette(path, "DarkCategoryColors")
    dark_chart = read_palette(path, "DarkCategoryChartColors")

    if args.optimize == "light":
        colors, (_, normal, cvd, worst) = optimize(dark_identity, "#FFFFFF", (38, 60), 18)
        print("識別色（明るい面）:", colors)
        print(f"  normal {normal:.1f} / CVD {cvd:.1f} / contrast {worst:.2f}")
        return
    if args.optimize == "light-chart":
        colors, (_, normal, cvd, worst) = optimize(dark_chart, "#F4F7FB", (45, 68), 18)
        print("グラフ色（明るい面）:", colors)
        print(f"  normal {normal:.1f} / CVD {cvd:.1f} / contrast {worst:.2f}")
        return

    for scheme_name in ("ZDarkColors", "ZLightColors"):
        report_text(scheme_name, read_scheme(path, scheme_name))
    report("識別色 / 暗い面", dark_identity, "#121826")
    report("グラフ色 / 暗い面", dark_chart, "#121826")
    for name, surface in (("LightCategoryColors", "#FFFFFF"), ("LightCategoryChartColors", "#F4F7FB")):
        try:
            report(f"{name} / 明るい面", read_palette(path, name), surface)
        except SystemExit:
            print(f"[{name}] まだ無い")


if __name__ == "__main__":
    main()
