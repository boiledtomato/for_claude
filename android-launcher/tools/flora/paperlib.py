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


def wash(img, polygon, color, alpha=42, gradient_deg=None, feather=1.4, floor=0.25):
    """淡い彩色。面に色を置く。

    [floor] は、光の当たる側にも残る量。ここが 0 でないと、重ねるたびに
    明るい側まで一律に沈んで、明暗の幅が中間色に潰れる。地の色を残したい
    ときは 0 にする。
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
        gx, gy = np.cos(np.radians(gradient_deg)), np.sin(np.radians(gradient_deg))
        # 伸びは「光の向きに測った差し渡し」で正規化する。
        #
        # 最大半径で割ると、細長い葉のように向きの偏った形では、光の向きの
        # 差し渡しがそれよりずっと短いので、階調が真ん中の半分しか使われない。
        # どこもかしこも中間色になり、明暗の幅が半分に潰れる。
        reach = max(1.0, float(np.abs((xs - cx) * gx + (ys - cy) * gy).max()))
        gxg, gyg = np.meshgrid(np.arange(w, dtype=np.float32),
                               np.arange(h, dtype=np.float32))
        t = ((gxg - cx) * gx + (gyg - cy) * gy) / reach
        arr = (arr * np.clip(t * 0.5 + 0.5, 0.0, 1.0) ** 0.8 * (1.0 - floor)
               + arr * floor)

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
        gx, gy = np.cos(np.radians(gradient_deg)), np.sin(np.radians(gradient_deg))
        reach = max(1.0, float(np.abs((xs - cx) * gx + (ys - cy) * gy).max()))
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


_MOTTLE = {}


def _mottle_field(size, seed, scale):
    """低い周波数のむら。石版の刷りむらと、手彩色の筆むらの代わり。"""
    key = (size, seed, scale)
    if key in _MOTTLE:
        return _MOTTLE[key]
    w, h = size
    rng = np.random.default_rng(seed)
    sw, sh = max(2, int(w / scale)), max(2, int(h / scale))
    n = rng.normal(0, 1, (sh, sw)).astype(np.float32)
    n = (n - n.min()) / max(float(n.max() - n.min()), 1e-6)
    f = np.asarray(Image.fromarray((n * 255).astype(np.uint8)).resize((w, h), Image.BICUBIC),
                   dtype=np.float32) / 255.0
    _MOTTLE[key] = f
    return f


def mottle(img, polygon, color, alpha=40, seed=7, scale=46.0, feather=1.2,
           gradient_deg=None, floor=0.35):
    """面にむらを置く。均一な塗りは印刷物ではなくベクタ画像に見える。

    参照した石版は、同じ花弁のなかでも濃いところと薄いところがある。
    紙の目が透けるのも含めて、その不均一さが「刷ったもの」の手触りになる。

    [gradient_deg] を渡すと、その向きにだけむらを効かせる。暗い色のむらを
    面の全体に乗せると、せっかく残した明るい側まで一律に沈んで、明暗の幅が
    中間色に潰れる。濃いむらは影の側、淡いむらは光の側に置く。
    """
    mask = Image.new("L", img.size, 0)
    ImageDraw.Draw(mask).polygon([tuple(p) for p in polygon], fill=255)
    if feather:
        mask = mask.filter(ImageFilter.GaussianBlur(feather))
    a = np.asarray(mask).astype(np.float32) / 255.0
    f = _mottle_field(img.size, seed, scale)
    a = a * np.clip(f * 1.5 - 0.25, 0.0, 1.0)
    if gradient_deg is not None:
        ys, xs = np.nonzero(a > 0.02)
        if len(xs) == 0:
            return
        cx, cy = xs.mean(), ys.mean()
        gx, gy = np.cos(np.radians(gradient_deg)), np.sin(np.radians(gradient_deg))
        reach = max(1.0, float(np.abs((xs - cx) * gx + (ys - cy) * gy).max()))
        h, w = a.shape
        gxg, gyg = np.meshgrid(np.arange(w, dtype=np.float32),
                               np.arange(h, dtype=np.float32))
        t = ((gxg - cx) * gx + (gyg - cy) * gy) / reach
        a = a * (floor + (1.0 - floor) * np.clip(t * 0.5 + 0.5, 0.0, 1.0))
    layer = Image.new("RGBA", img.size, tuple(color) + (255,))
    layer.putalpha(Image.fromarray((a * alpha).clip(0, 255).astype(np.uint8), "L"))
    img.alpha_composite(layer)
