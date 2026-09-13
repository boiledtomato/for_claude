# Herbarium — ボタニカル・ホーム画面

19 世紀の手彩色植物図版をそのままホーム画面にする Android ランチャー。

- 図版のブーケが、根元を固定したまま風に撓むように**常時微動**する
- **花や葉をタップ**すると、そこに割り当てたアプリが起動する
- 右上の**蕾をタップすると花が開き**、その花から標本帳（アプリ一覧）が咲き広がる

絵は手続き的に描いたものではなく、実際の図版スキャンを素材として使っている。
使っているのは Anne Pratt 系の *Pl. 134 Bell Flowers*（ホタルブクロ類）。

## 構成

```
android-launcher/
├── app/src/main/
│   ├── assets/plate/            # 版面の素材（自動生成。手で編集しない）
│   │   ├── plate.json           #   配置・当たり判定の定義
│   │   ├── bouquet.webp         #   ブーケ本体
│   │   └── gemma_open.webp      #   蕾が開いたあとの花
│   └── java/com/botanical/launcher/
│       ├── MainActivity.kt
│       ├── garden/              # 版面のモデルと読み込み
│       │   ├── Plate.kt         #   plate.json のパース
│       │   ├── PlateLoader.kt   #   assets → Bitmap（画面幅に合わせて間引く）
│       │   └── Palette.kt
│       ├── data/                # アプリ一覧と割り当ての永続化
│       └── ui/
│           ├── PlateCanvas.kt   # 撓み・開花の描画
│           ├── HomeScreen.kt    # 状態とタップ処理
│           ├── AppDrawer.kt     # 標本帳（アプリ一覧）
│           └── SceneTransform.kt
└── tools/                       # 素材づくり（Python / Pillow + numpy）
    ├── vectorise.py             # 線をベクター化して任意解像度で描き直す
    ├── align_crops.py           # 部分拡大を全体図へ貼り戻して高精細化
    ├── make_plate.py            # レシピ → assets/plate/ 一式
    ├── render_home.py           # 端末に入れる前の確認用レンダラ
    ├── prep_plate.py            # 紙の背景を抜く（別方式が要るとき用）
    ├── recipes/pl134.json       # 図版ごとの設定はここだけ
    └── sources/pl134/           # 元スキャン（パブリックドメイン）
```

## ビルドと導入

```bash
cd android-launcher
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

インストール後、**設定 → アプリ → デフォルトのアプリ → ホームアプリ** で
`Herbarium` を選ぶ。アプリ内でも紙の余白を長押しすると設定画面へ行ける。

`local.properties` に `sdk.dir` が要る（`.gitignore` 済み）。

## 操作

| 操作 | 動き |
|---|---|
| 花・葉をタップ | 割り当てたアプリを起動。未割り当てならその場で割り当てへ |
| 花・葉を長押し | その部位にアプリを割り当てる／解除する |
| 蕾をタップ | 花が開き、アプリ一覧が咲き広がる |
| 一覧でアプリを長押し | アプリ情報を開く |
| 紙の余白を長押し | 版面の設定（学名の表示、当たり判定の可視化、全解除） |
| 戻る | 一覧を閉じる |

割り当ては `SharedPreferences` に「部位 ID → package/class」で保存する。
初回起動時は電話・カメラ・ブラウザなどを目立つ花から順に自動で置く。

## 素材づくり

### なぜ切り抜かずに「紙ごと」使うのか

図版の紙とアプリの紙を**同じ色**にしてあるので、紙ごと撓ませても紙は動いて見えない。
花の周りの紙を無理に抜くと、淡い青や白の花弁まで削れてしまう。外周だけぼかして
紙に溶け込ませるほうが、結果として綺麗で確実だった。

例外は**蕾が開いたあとの花**で、これは他の茎に重なる位置へ描くので色で精密に抜く
（`make_plate.py` の `cut_by_hue`）。開花は「蕾を消して花を描く」のではなく、
蕾の上に開いた花を重ねて覆い隠している。図版から蕾だけを消すと背後の茎まで欠けるため。

### ベクター化で「化け」を消す

ラスターのまま拡大すると輪郭がぼけ、ギザギザが出る。かといって画像全体を
そのままベクター化すると、色の領域がまとめられて**細い黒線が隣の色に呑まれて
消える**。版画は線が主役なので、それでは別物になる。

`tools/vectorise.py` は線と色を分けて扱う。

| | やること |
|---|---|
| 線 | 周囲より暗い成分だけを抜き出して二値化し、ベクター化する。何倍に拡大しても輪郭が崩れない |
| 淡彩 | 線を取り除いてから滑らかに拡大する。もともと低周波なので化けない |

最後に拡大した淡彩の上へベクターの線を重ねる。線の濃さは元の濃淡を掛けて残す
（一律に塗ると版画が塗り絵になる）。

ベクター化はぼけとギザギザを消すが、**元の画像に無い細部は増やせない**。
効くのは「低い解像度の原画を大きく使いたい」場面で、Pl.134 では 380x599 の
全体図を 4 倍で使えるようになった。

### 手順

```bash
# 1. 全体図と部分拡大を位置合わせし、ベクター化して 4 倍で描き直す
#    （倍率と位置は正規化相互相関の総当たりで自動決定）
python3 tools/align_crops.py \
    tools/sources/pl134/plate_full.png tools/work/plate_vec.png \
    --crops tools/sources/pl134/detail_{top,mid,bottom}.png \
    --scale 1.8 3.6 --vectorise --out-scale 4.0 --pad 38

# 2. 座標を読むためのグリッドを出す
python3 tools/render_home.py app/src/main/assets/plate --grid --out tools/work/grid.png

# 3. レシピを書いて素材を生成
python3 tools/make_plate.py tools/recipes/pl134.json

# 4. 端末に入れる前に確認
python3 tools/render_home.py app/src/main/assets/plate --hits
python3 tools/render_home.py app/src/main/assets/plate --bloom 1
```

`Pl.134` では 380x599 の全体図に 2.72 倍の部分拡大 3 枚が一致し（相関 0.96〜0.97）、
ベクター化と合わせて 1520x2396 まで破綻なく引き上げられた。

### 図版を差し替える

`tools/recipes/` に JSON を足して `make_plate.py` に渡すだけ。Kotlin は触らない。

| キー | 意味 |
|---|---|
| `source` | 合成済みの図版画像 |
| `bouquet.box` | そこから切り出す範囲（刷られた説明文は落とす） |
| `bouquet.feather` | 外周を紙へ溶け込ませる幅 |
| `plate` | 版面の大きさ・紙の色。`paper` は必ず**元スキャンの紙の色**に合わせる |
| `layer.rect` | 版面上でのブーケの配置 |
| `layer.pivot` | 撓みの支点。茎が束になる位置（画像内の割合） |
| `layer.bend.amplitude` | 撓みの最大横移動量（版面単位） |
| `organs` | `[id, 種別, 中心x, 中心y, 半径, 表示名]`。座標はブーケ画像の画素 |
| `gemmaOpen` | 蕾が開いたあとの花。`organ` に対応する蕾の id を書く |

`layer.bend.speed` は**整数**にする。整数でないと位相が 1 周するたびに動きが飛ぶ。

## 実装メモ

- **撓みは `Canvas.drawBitmapMesh`**。株を丸ごと回転させると板を振っているように
  見えるので、3x12 の格子を根元からの距離の 1.7 乗でずらしている。
- **アプリ一覧は蕾の座標を原点に拡大して現れる**（`TransformOrigin`）。咲いた花から
  一覧が広がるように見せるため。
- **スキャンは画面幅に合わせて間引いて読む**（`PlateLoader`）。等倍で持つとランチャー
  としては重すぎる。
- **当たり判定は静止位置で行う**。撓みの振れ幅は版面幅の 1.5% ほどで、判定円の半径に
  比べて十分小さい。
- パッケージ一覧は `QUERY_ALL_PACKAGES` ではなく `<queries>` で取っている。

## 素材の権利について

`tools/sources/` に入れてあるのは、19 世紀に出版された図版でいずれも
**著作権の保護期間が満了**している。

ストック写真サイトで売られている同じ図版のスキャンは、透かし入りのものも
ライセンス品も**そのままでは使えない**。同じ版画は Wikimedia Commons や
Biodiversity Heritage Library から自由に入手できる。

素材を足すときは、元の版画が 1900 年以前の出版であることと、スキャンの
配布条件を確認すること。

## 素材づくりに必要な Python パッケージ

```bash
pip install pillow numpy scipy vtracer cairosvg scikit-image
```

`vtracer` がベクター化、`cairosvg` が SVG の描き出しを担当する。
