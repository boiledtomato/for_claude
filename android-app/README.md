# ZLauncher — Android ホームランチャー MVP

Kotlin + Jetpack Compose のホームランチャー。ホーム画面（ウィジェット＋アプリ検索＋アプリグリッド
＋お気に入りドック）と、カード型のコンソール画面（ダークコンソール調・並べ替え可能）、
ウィジェットピッカーの 3 画面構成。

UI モックアップ: [Android Launcher Console](https://claude.ai/code/artifact/ef5bddd6-040a-497f-8bcc-f7a89fe8567f)

## 構成

| | |
|---|---|
| 言語 / UI | Kotlin 2.1 + Jetpack Compose (Material3) |
| minSdk / targetSdk | 28 / 36 |
| DI | Hilt |
| 永続化 | DataStore(Preferences) + kotlinx.serialization |
| ナビゲーション | Navigation Compose（`home` / `dashboard` の 2 ルート） |

```
app/src/main/java/com/example/zlauncher/
├── MainActivity.kt                    # HOME インテントフィルタを持つ唯一の Activity
├── ZLauncherApplication.kt            # Hilt エントリ、debug 時 StrictMode
├── core/
│   ├── designsystem/                  # カラートークン・タイポ・StatusColors・ZMotion
│   │   └── component/                 # DashboardCardScaffold / StatusIndicator / MiniCharts
│   └── ui/
│       ├── DragReorder.kt             # 長押しドラッグ並べ替え（離した後の着地アニメ付き）
│       └── Interaction.kt             # springyClick — 押し込みスケールの共通 Modifier
├── domain/model/                      # AppEntry / CardLayout / CardStatus / CardIds
├── data/
│   ├── apps/                          # LauncherApps ラッパ・一覧・並び順・アイコン・お気に入り
│   ├── prefs/                         # LauncherState（version 付き）と DataStore
│   ├── device/                        # DeviceMetricsRepository（1 秒ごとの実測値サンプリング）
│   ├── insights/                      # Web Insights（UID 別通信量・カテゴリー集計・CSV 出力）
│   ├── widgets/                       # AppWidgetHost の管理とウィジェット配置の永続化
│   └── dashboard/                     # カード配置の永続化
└── ui/
    ├── home/                          # ホーム画面（グリッド＋ドック＋長押しメニュー）
    ├── console/                       # コンソール画面・レール・カード登録表・カード実装
    ├── navigation/                    # NavHost
    └── setup/DefaultLauncher.kt       # ROLE_HOME / ホームアプリ設定への導線
```

## 画面

- **ホーム** — 起動可能アプリを 4 列グリッドに自動整列（名前順 / インストール日時順）。
  上部にウィジェット（全幅・縦一列）とアプリ検索、下部にお気に入りドック 4 枠。
  アイコン長押しで「ドックに追加 / アプリ情報」。
- **コンソール** — 左のレールと右のペインの 2 段構成。
  - レール上部: **よく使う 2 アプリのピン**（タップで起動 / 長押しで差し替え）
  - レール: 「Overview」＋**ユーザーが作るカテゴリー**（＋ で追加、色と名前を編集）
  - 概要ペイン: 端末の**実測値**カードを 12 カラムグリッドに配置。全幅 / 1・2 幅。
    **カードは常時ドラッグ＆ドロップで並べ替えられる**（編集モードに入る必要はない）。
    「Layout」は非表示化・幅変更・既定に戻すといった編集用の操作を出すだけ
  - カテゴリーペイン: そのカテゴリーに入れ子にしたアプリを一覧（長押しで外す）。
    「Insights」でそのカテゴリーに絞った通信ログへ飛ぶ
  - Insights ペイン: **Web Insights**（下記）
- **ウィジェットピッカー** — インストール済みプロバイダの一覧。選ぶと ID 払い出し →
  バインド同意 → 設定画面 → 保存まで進む。

## 表記と操作感

- **UI の文字列はすべて英語**（Overview / Layout / Done / Pinned / Search apps …）。
  Zscaler Experience Center の見た目に合わせるため、日付も `EEE, MMM d` の英語表記にしている。
  このドキュメントと README だけが日本語。
- **モーションは `ZMotion` に集約**してある（`core/designsystem/Motion.kt`）。タッチ反応・
  再レイアウト・画面遷移・数値の補間でそれぞれ別のスペックを使い、値をここ 1 箇所で変えれば
  アプリ全体の手触りが揃って変わる。

| 用途 | スペック |
|---|---|
| 押し込み・持ち上げ | `spring(dampingRatio = 0.78f, StiffnessMediumLow)` |
| 並べ替え時の他要素の追従 | `spring(dampingRatio = 0.85f, StiffnessLow)` |
| 画面 / ペイン遷移 | `tween(260ms)` のフェード＋スライド |
| メーターやグラフの値 | `tween(550ms)` |

- タップできる要素は `Modifier.springyClick()` / `springyCombinedClick()` を通す。押下中は
  0.94 倍に縮み、離すとバネで戻る。アプリ内に素の `clickable` は残していない
  （`Interaction.kt` の実装だけが例外）。
- カードを掴むと 1.04 倍に浮いて僅かに傾き、周囲のカードは `animateItem` で流れるように詰める。
  指を離した位置から元の位置へバネで着地するので、パッと戻る瞬間が無い。

## 配置モデル（重要）

- グリッドは **常に全アプリ** を自動整列で表示する。ドックはフィルタではなく参照なので、
  同じアプリがグリッドとドックの両方に出るのが正しい挙動。
- 永続化するのは **`packageName` の順序リスト 1 本だけ**。`ComponentName` を保存しないのは、
  アプリ更新でランチャーアクティビティのクラス名が変わると配置が消えるため。
- お気に入りは `FavoritesRepository` でインストール済みアプリの Flow と `combine` して解決する。
  アンインストールされたアプリは自動でドックから落ちるので、削除処理を書く必要がない。

## コンソールのカードは実測値

ダミーではなく端末から実際に取れる値を 1 秒ごとに読み直す（`DeviceMetricsRepository`）。
採取コルーチンは `stateIn` で 1 本に絞ってある（購読ごとに走らせるとトラフィックの差分が
二重に出る）。コンソールを閉じれば止まる。

カード名は UI 表記（英語）で示す。

| カード | 内容 | 既定 | タップ |
|---|---|---|---|
| Health check | 5 項目を掃引アニメーションで順に点検、9 秒ごとに再実行 | 表示 | 即時再点検 |
| Traffic | 下り / 上りの実測レート＋直近 40 秒のスパークライン | 表示 | — |
| Battery | 残量・充電状態・温度 | 表示 | 電池使用量の設定 |
| Storage | 使用量 / 空き | 表示 | ストレージ設定 |
| Memory | 使用量 / 全体 | 表示 | — |
| Network | 回線種別・VPN・推定下り速度 | 表示 | ネットワーク設定 |
| Battery trend | セッション中の残量スパークライン | 表示 | — |
| Clock | 秒まで動く時計・日付・稼働時間 | 表示 | — |
| Data since boot | 受信 / 送信の累計、モバイルと Wi-Fi の内訳 | 表示 | — |
| Recently added | 直近 3 件、タップで起動 | 表示 | — |
| Uptime | 最終起動からの経過 | 非表示 | — |
| Apps | 起動可能なアプリ数（仕事用を含む） | 非表示 | アプリ設定 |
| Sound & light | メディア / 着信 / 画面の明るさ | 非表示 | — |
| Device | モデル・Android バージョン・解像度・CPU コア数 | 非表示 | — |

非表示のカードは「Layout」の Hidden cards から出せる。

概要ペインのカードが使うのは `ACCESS_NETWORK_STATE`（通常権限）だけで、実行時の許可
ダイアログは出ない。アプリ別の通信量は Web Insights 側に分けてあり、そちらだけが
「使用状況へのアクセス」を要る（許可しなくても概要ペインは全部動く）。トラフィックは
`TrafficStats` の端末全体の累計を 1 秒差分でレートにしている（権限不要、`UNSUPPORTED` を返す
機種があるので必ず判定する）。

リングやバーは値が変わるとアニメーションし、上部には更新中を示す明滅インジケーターを出す。

## Web Insights（カテゴリー別の通信ログ）

レールの「Insights」。**自分で作ったカテゴリーを軸に、いつ・どのアプリが・どれだけ
流したかを一覧・検索する。**

### 取れるもの / 取れないもの

Zscaler の Web Insights と違い、端末の中からは **URL もホスト名も取れない**。
それをやるには VPN サービスを立てて全パケットを覗くしかなく、ランチャーの役割を
大きく外れるうえ、常時 VPN を占有して他の VPN と共存できなくなる。採らない。

代わりに `NetworkStatsManager` から取れる **UID 単位・時間バケット単位の受信/送信
バイト数**を使う。ログ 1 行 = 時刻 × アプリ × 回線種別（Wi-Fi / Mobile）× バイト数。

| | |
|---|---|
| 期間 | 1H（5 分刻み）/ 24H（1 時間刻み）/ **7D（6 時間刻み）** |
| 軸 | ユーザー定義カテゴリー。どこにも属さないものは `Uncategorized` にまとめる |
| 表示 | **Chart / Apps / Log** の 3 段。同じデータを粗い順に見る |
| 検索 | アプリ名 / パッケージ名 / カテゴリー名の部分一致 |
| 絞り込み | 時系列の棒をタップでその時間帯だけ、カテゴリー行をタップでそのカテゴリーだけ |
| ドリルダウン | カテゴリー / アプリをタップすると、そのものの時系列・上下内訳・回線内訳を重ねて出す |
| 出力 | 表示中（＝絞り込み後）のログを CSV にして共有シートへ。**出せるものが無いときはボタンを出さない** |

### 3 つの表示

| | 見るもの |
|---|---|
| **Chart** | カテゴリー別の積み上げ時系列、選択した時間帯の内訳、Wi-Fi / モバイルと 上り / 下りの内訳、全カテゴリーの一覧 |
| **Apps** | アプリ別の順位。行ごとに小さな時系列と全体比 |
| **Log** | 生の行（時刻 × アプリ × 回線 × バイト数）。表としての最終形 |

### グラフの決まり

- **系列色は `ZColors.CategoryChartColors`**。カテゴリーが持つ色番号に従い、多い順では
  決めない。絞り込みで系列が減っても残った系列の色は変わらない
- 識別色（`CategoryColors`、レールの点に使う明るいほう）を面の塗りには使わない。
  明るい 6 色を面で並べると隣同士が見分けられない（緑と水色、青と紫が特に近い）。
  塗り用は色相を 8 度以内しかずらしていないので「点と棒が同じ色」には見えるまま、
  暗い面の上で分離する。**数値で確認済み**（サーフェス `#121826`・全ペア）:
  通常視の最小 ΔE 18.2 / CVD の最小 ΔE 10.1 / コントラスト 3:1 以上。
  色を足す・変えるときは目視で決めず、必ず同じ検証をやり直すこと
- **7 色目を作らない。** 上位 5 カテゴリーだけ色を付け、残りは無彩色の `Other` 1 本に
  まとめる（`WebInsightsReport.chartSeries()`）。色を回すと「色が違う＝別のカテゴリー」
  という読み方そのものが壊れる
- 積み上げの段の間に 2dp の隙間を空ける。上端だけ角を丸め、下端は土台に接地させる
- 凡例は 2 系列以上なら必ず出し、名前と実数を並べる。色だけで区別させない
- **数字とラベルは必ずテキスト色**。系列色を文字に使うと色の意味が二重になる
- 軸の目盛りは両端と中央だけ。全部の棒に時刻を振ると読めない

7 日を 6 時間刻みにしているのは体裁ではなく上限。UID 別の統計はプラットフォームが
2 時間バケットで持ち、保持期間も数週間しかない。それより細かく刻んでも精度は上がらず、
照会回数（1 バケット × 回線 2 種）だけが増える。

### 使用状況へのアクセスが要る

`PACKAGE_USAGE_STATS` は signature|appop なので**実行時ダイアログでは出せない**。
マニフェストで宣言し、設定 →「使用状況へのアクセス」でユーザーに許可してもらう。
未許可のときは Insights ペインに許可導線だけを出し、他の画面は通常どおり動く。
設定から戻ったタイミング（`repeatOnLifecycle(RESUMED)`）で読み直すので、
戻ってきたら勝手に表示が切り替わる。

### 実装上の注意

- `querySummary` は指定区間を 1 つに畳んで返すだけなので、**時系列が欲しければ区間を
  刻んで呼ぶ**しかない。7D で 28 区間 × 2 回線 = 56 回。IO で回して結果を保持する
- **subscriberId には必ず null を渡す。** API 29 以降、一般アプリが実際の値を渡すと
  SecurityException になる
- 同じ UID が set / tag / metered 違いで複数バケットに分かれて返るので、UID でまとめる
- 時間軸は実データではなく期間から作る（`InsightRange.bucketStarts`）。無通信の時間帯が
  軸から抜けると、離れた時刻の棒が隣り合って連続しているように見えてしまう
- 作ったカテゴリーは、その窓で通信が無くても 0 の行として必ず出す。消えると壊れて見える
- 共有 UID（`sharedUserId`）で 1 UID に複数パッケージがぶら下がる場合は、
  **ユーザーがカテゴリーに入れたパッケージを代表にする**（分類した側の意図に沿う）
- CSV は cacheDir に書き、FileProvider の一時 read 権限だけを渡す。書く前に古いものを消す
- 行内のミニ時系列は 16 本を上限にまとめる。64dp に 28 本を入れると 1 本 1dp 強で線に潰れる
- 権限が無い＝データが 0 件のときに Export を押せてはいけない。以前は押せてしまい、
  本当の理由（未許可）ではなく「絞り込みに該当なし」と表示していた

## カテゴリー（コンソール左レール）

ユーザーが自分で作る。1 カテゴリー = 名前 + 色 + 所属アプリ（`packageName` の配列）。
追加ダイアログには **Zscaler の predefined URL カテゴリー名（英語表記そのまま）のプリセット**
（`PRESET_CATEGORIES`、24 種）を並べてあり、まとめて選んで作れる。ブロック運用が前提の
カテゴリー（Adult Material 等）はランチャーの用途に合わないので入れていない。プリセットで作ったものも普通のカテゴリーなので、
名前・色・中身は自由に変えられる。自分で名前を決めて作る導線も同じダイアログにある。
所属アプリはインストール済みアプリの Flow と combine して解決するので、アンインストール
されたアプリは自動的に消える。ピン留めの 2 枠も同じ仕組み。

## ウィジェット

`AppWidgetHost` を `WidgetHostController` に閉じ込め、配置は座標ではなく**順序リスト**で持つ
（全幅・縦一列）。自由座標にするとグリッド列数の変化で配置が破綻するため MVP では採らない。

追加フローの要点（`WidgetPickerScreen`）:

1. `allocateAppWidgetId()` で ID を払い出す
2. `bindAppWidgetIdIfAllowed()` を試す — **`BIND_APPWIDGET` 権限は署名 / 特権アプリ専用**で
   一般アプリには付与されないため、既定ホームでなければ大抵 false が返る
3. false なら `ACTION_APPWIDGET_BIND` でユーザーの同意を取る
4. `configure` を持つプロバイダは `ACTION_APPWIDGET_CONFIGURE` を起動する
   （拒否されることがあるので、失敗しても未設定のまま追加する）
5. 保存する

**どこで中断されても払い出した ID を返す**のが肝（`cancel()`）。放置すると ID がリークする。
`startListening` / `stopListening` は Activity の onStart / onStop に合わせる。掃除
（`pruneMissing`）は listening 開始後にだけ走らせる — 開始前だと有効なウィジェットまで
「提供元が無い」と誤判定しかねない。

## 仕事用プロファイル（Work Profile）

`LauncherApps` + `UserManager.userProfiles` で個人用と仕事用の両方を 1 つのグリッドに出す
（プロファイルのタブ分けはしない）。アイコンは `getUserBadgedIcon` でバッジ付きにする。

制限:

- 仕事用プロファイルのアプリが実際に見えるのは**既定のホームアプリのときだけ**。
  取得できなければ現在のユーザーだけに落ちる。
- **ドックは個人用アプリのみ**。お気に入りを `packageName` だけで保存しており、
  同一パッケージが両プロファイルに存在しうるため。仕事用アプリの長押しメニューには
  「ドックに追加」を出さない。
- ウィジェットも現在のユーザーのプロバイダのみ。

## アプリ検索

ホーム上部の検索バーでラベル / パッケージ名の部分一致で絞り込む。アプリ数が増えると
配置より検索の方が早く目的に着くうえ、下記の並び順の制限も緩和できる。

## アイコン

- ランチャー自身のアイコンは、アプリの見た目に合わせた枠＋実測値の線のベクター
  （`ic_launcher_foreground.xml`）
- コンソールの設定で **アイコンの見た目** を「テーマ調」に切り替えると、モノクロレイヤーを
  持つアプリのアイコンをアクセント色に寄せて描く（Android 13 以降・対応アプリのみ）。
  非対応アプリは通常のアイコンのまま出す。キャッシュキーに設定を含めているので切り替えは即反映

## 並び順の制限（Android の制約）

Android には**アプリ名の読み仮名を取得する API が無い**。そのため「名前順」は
英字 → かな → 漢字（コードポイント順）になり、**完全な五十音順にはできない**。
AOSP の Launcher3 も同じ挙動。回避策として「インストール日時順」を用意している
（`AppSortOrder`、コンソール側から切替予定）。

## ランチャー特有の実装ポイント

| 対処 | 場所 |
|---|---|
| `stateNotNeeded="true"` で復元時例外による起動不能を防ぐ | `AndroidManifest.xml` |
| `launchMode="singleTask"`（`singleInstance` は不可） | `AndroidManifest.xml` |
| HOME キー再押下は `onNewIntent` で拾う（拾わないと HOME が効かない） | `MainActivity` |
| ホームでは戻る操作を無効化 | `HomeScreen` の `BackHandler` |
| `<queries>` のみでアプリ可視性を確保（`QUERY_ALL_PACKAGES` は使わない） | `AndroidManifest.xml` |
| debug は `applicationIdSuffix ".debug"`。既定ホームを壊さず共存できる | `app/build.gradle.kts` |
| DataStore の `corruptionHandler` と JSON デコード失敗の既定値フォールバック | `LauncherPreferencesRepository` |
| 未知のカード ID はスキップ、`reconcile` で増減に追従 | `DashboardLayoutRepository` / `CardCatalog` |
| アイコンのラスタライズは IO + LruCache（キーはコンポーネント＋ユーザー） | `AppIconLoader` |
| アプリタイルはセル幅いっぱいに広げ、ラベルは常に 2 行ぶんを確保する（可変長ラベルで配置が崩れるため） | `AppTile` |
| ウィジェット ID は中断時も必ず `deleteAppWidgetId` で返す | `WidgetPickerScreen` |
| `querySummary` の subscriberId は必ず null（API 29+ で SecurityException になる） | `NetworkStatsSource` |
| 通信ログの時間軸は実データではなく期間から作る | `InsightRange.bucketStarts` |
| `LauncherApps` の呼び出しはすべて例外を握って空に落とす | `LauncherAppsDataSource` |

## インストール（ビルド不要）

`.github/workflows/android-build.yml` が `android-app/` への push ごとにデバッグ APK を
ビルドし、Release **`android-debug-latest`** の資材を差し替える。タグは据え置きなので
**ダウンロード URL は常に同じ**：

- ページ: https://github.com/boiledtomato/for_claude/releases/tag/android-debug-latest
- 直リンク: https://github.com/boiledtomato/for_claude/releases/download/android-debug-latest/zlauncher-debug.apk

リポジトリが public なので、Android 端末のブラウザでそのまま開いてインストールできる
（GitHub へのログイン不要）。PC から入れる場合は `adb install -r zlauncher-debug.apk`。

## ビルド

```bash
# Android SDK が必要（compileSdk 36 / build-tools 36.0.0）
echo "sdk.dir=/path/to/Android/sdk" > local.properties
./gradlew assembleDebug
```

Gradle wrapper をコミットしてあるので、Gradle 本体のインストールは不要
（Ubuntu の `apt install gradle` は 4.x 系と古く、AGP 8.11 では使えない）。

依存バージョンは `gradle/libs.versions.toml` の 1 箇所にまとめてある
（AGP 8.11 / Kotlin 2.1.21 / Compose BOM 2025.06.00 の組み合わせで
`assembleDebug` と `lintDebug` の通過を確認済み）。

## デバッグ署名鍵をコミットしている理由

`keystore/debug.keystore` はリポジトリに含めてある。デバッグ鍵はビルド環境ごとに
自動生成されるため、そのままだと CI と手元とで署名が変わり、上書き更新が
「アプリが既にインストールされています」で失敗する。固定鍵にすることで、どこで
ビルドした APK でも上書きインストールできる。

パスワードは Android 標準のデバッグ鍵と同じ既知の値（`android` /
`androiddebugkey`）。**release ビルドには絶対に使わないこと**。公開したい場合は
別途リリース鍵を作り、GitHub Secrets 経由で渡す。

## 検証時の注意

**実機で「既定のホーム」に指定するのは動作確認が済んでから。** debug ビルドは
applicationId が別なので標準ランチャーと共存するが、既定に指定した状態でクラッシュすると
復帰操作が取りづらい。コンソール画面には常設で「ホームアプリ設定を開く」導線を置いてある。

## まだ入っていないもの

フォルダ、ページ分割ワークスペース、アイコンの自由座標配置、ウィジェットのリサイズ、
壁紙ピッカー、通知バッジ、ディープショートカット、プロファイル別タブ。

Web Insights の URL / ホスト名別の内訳（VPN を立てないと取れないので採らない）、
7 日を超える期間（プラットフォーム側が UID 別統計をそこまで保持しない）。
