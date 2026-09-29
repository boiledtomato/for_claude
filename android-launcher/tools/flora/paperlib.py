"""紙とクリップ付きハッチング。"""
import numpy as np
from PIL import Image, ImageDraw, ImageChops, ImageFilter
from pencil import Pencil


def paper(size, base=(246, 242, 233), tooth=7.0, seed=3):
    """わずかにざらついた温かい白。鉛筆は紙の目が見えないと嘘になる。"""
    rng = np.random.default_rng(seed)
    w, h = size
    n = rng.normal(0, 1, (h // 2 + 1, w // 2 + 1)).astype(np.float32)
    n = np.asarray(Image.fromarray(((n * 40) + 128).clip(0, 255).astype(np.uint8))
                   .resize((w, h), Image.BILINEAR), dtype=np.float32)
    n = (n - 128) / 40.0
    fine = rng.normal(0, 1, (h, w)).astype(np.float32)
    field = n * 0.65 + fine * 0.35
    arr = np.zeros((h, w, 3), dtype=np.float32)
    for i, c in enumerate(base):
        arr[..., i] = c + field * tooth
    return Image.fromarray(arr.clip(0, 255).astype(np.uint8), "RGB").convert("RGBA")


def clipped(img, polygon, fn, feather=0.6):
    """[polygon] の内側だけに fn(draw, pencil) を描く。"""
    layer = Image.new("RGBA", img.size, (0, 0, 0, 0))
    d = ImageDraw.Draw(layer, "RGBA")
    fn(d)
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([tuple(p) for p in polygon], fill=255)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    layer.putalpha(ImageChops.multiply(layer.getchannel("A"), mask))
    img.alpha_composite(layer)


def bbox(polygon, pad=6):
    xs = [p[0] for p in polygon]; ys = [p[1] for p in polygon]
    return (min(xs) - pad, min(ys) - pad, max(xs) + pad, max(ys) + pad)


def smudge(img, polygon, tone=0.10, blur=5.0, shift=(0, 0), color=(70, 68, 72)):
    """こすった黒鉛の面。輪郭だけだと硬いので、影側に薄く敷く。"""
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([(p[0] + shift[0], p[1] + shift[1]) for p in polygon], fill=255)
    mask = mask.filter(ImageFilter.GaussianBlur(blur))
    mask = mask.point(lambda v: int(v * tone))
    layer = Image.new("RGBA", img.size, color + (255,))
    layer.putalpha(mask)
    img.alpha_composite(layer)


def fill_shape(img, polygon, color=(249, 246, 239), alpha=244, feather=0.7):
    """紙の色で形を伏せる。

    これが無いと前後の草が透けて重なり、絵が「線の網」になる。
    完全な不透明にはせず、紙の目をわずかに透かす。
    """
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([tuple(p) for p in polygon], fill=255)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    mask = mask.point(lambda v: v * alpha // 255)
    layer = Image.new("RGBA", img.size, color + (255,))
    layer.putalpha(mask)
    img.alpha_composite(layer)


def wash(img, polygon, color, alpha=42, gradient_deg=None, feather=1.4):
    """淡い彩色。鉛筆の上に薄く置くと、図版の手彩色に近づく。

    べた塗りにすると鉛筆が沈むので、光の向きへ薄くなる階調をつける。
    """
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([tuple(p) for p in polygon], fill=255)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    arr = np.asarray(mask).astype(np.float32) / 255.0

    if gradient_deg is not None:
        h, w = arr.shape
        ys, xs = np.nonzero(arr > 0.02)
        if len(xs) == 0:
            return
        cx, cy = xs.mean(), ys.mean()
        reach = max(1.0, float(np.hypot(xs - cx, ys - cy).max()))
        gx, gy = np.cos(np.radians(gradient_deg)), np.sin(np.radians(gradient_deg))
        gxg, gyg = np.meshgrid(np.arange(w, dtype=np.float32),
                               np.arange(h, dtype=np.float32))
        t = ((gxg - cx) * gx + (gyg - cy) * gy) / reach
        arr = arr * np.clip(t * 0.5 + 0.5, 0.0, 1.0) ** 0.8 * 0.75 + arr * 0.25

    layer = Image.new("RGBA", img.size, tuple(color) + (255,))
    layer.putalpha(Image.fromarray((arr * alpha).clip(0, 255).astype(np.uint8), "L"))
    img.alpha_composite(layer)


def edge_shade(img, polygon, color=(70, 68, 74), alpha=90, band=9.0,
               gradient_deg=None, bias=0.35):
    """輪郭の内側にだけ落とす影。

    形の半分をべた塗りすると平らな板になる。鉛筆は縁と稜が濃く、面の中ほどが
    明るい。ここでは「塗り - 収縮させた塗り」で内側の帯を作って暗くする。
    """
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([tuple(p) for p in polygon], fill=255)
    inner = mask.filter(ImageFilter.GaussianBlur(band))
    inner = inner.point(lambda v: 255 if v > 210 else 0)
    band_mask = ImageChops.subtract(mask, inner)
    band_mask = band_mask.filter(ImageFilter.GaussianBlur(band * 0.5))

    arr = np.asarray(band_mask).astype(np.float32) / 255.0
    if gradient_deg is not None:
        ys, xs = np.nonzero(np.asarray(mask) > 0)
        if len(xs) == 0:
            return
        cx, cy = xs.mean(), ys.mean()
        reach = max(1.0, float(np.hypot(xs - cx, ys - cy).max()))
        gx, gy = np.cos(np.radians(gradient_deg)), np.sin(np.radians(gradient_deg))
        h, w = arr.shape
        gxg, gyg = np.meshgrid(np.arange(w, dtype=np.float32),
                               np.arange(h, dtype=np.float32))
        t = ((gxg - cx) * gx + (gyg - cy) * gy) / reach
        arr = arr * (bias + (1.0 - bias) * np.clip(t * 0.5 + 0.5, 0.0, 1.0))

    layer = Image.new("RGBA", img.size, tuple(color) + (255,))
    layer.putalpha(Image.fromarray((arr * alpha).clip(0, 255).astype(np.uint8), "L"))
    img.alpha_composite(layer)


def crease(img, curve, color=(70, 68, 74), alpha=70, width=7.0):
    """稜線に沿った柔らかい影。裂片の境目を立たせる。"""
    layer = Image.new("L", img.size, 0)
    ImageDraw.Draw(layer).line([tuple(p) for p in curve], fill=255,
                               width=int(width), joint="curve")
    layer = layer.filter(ImageFilter.GaussianBlur(width * 0.55))
    col = Image.new("RGBA", img.size, tuple(color) + (255,))
    col.putalpha(layer.point(lambda v: v * alpha // 255))
    img.alpha_composite(col)
