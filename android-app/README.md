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
│   ├── designsystem/                  # カラートークン・タイポ・StatusColors
│   │   └── component/                 # DashboardCardScaffold / StatusIndicator / MiniCharts
│   └── ui/DragReorder.kt              # LazyVerticalGrid の長押しドラッグ並べ替え
├── domain/model/                      # AppEntry / CardLayout / CardStatus / CardIds
├── data/
│   ├── apps/                          # LauncherApps ラッパ・一覧・並び順・アイコン・お気に入り
│   ├── prefs/                         # LauncherState（version 付き）と DataStore
│   ├── widgets/                       # AppWidgetHost の管理とウィジェット配置の永続化
│   └── dashboard/                     # ダミーデータとカード配置の永続化
└── ui/
    ├── home/                          # ホーム画面（グリッド＋ドック＋長押しメニュー）
    ├── dashboard/                      # コンソール画面・カード登録表・カード実装
    ├── navigation/                    # NavHost
    └── setup/DefaultLauncher.kt       # ROLE_HOME / ホームアプリ設定への導線
```

## 画面

- **ホーム** — 起動可能アプリを 4 列グリッドに自動整列（名前順 / インストール日時順）。
  上部にウィジェット（全幅・縦一列）とアプリ検索、下部にお気に入りドック 4 枠。
  アイコン長押しで「ドックに追加 / アプリ情報」。
- **コンソール** — 左のレールと右のペインの 2 段構成。
  - レール上部: **よく使う 2 アプリのピン**（タップで起動 / 長押しで差し替え）
  - レール: 「概要」＋**ユーザーが作るカテゴリー**（＋ で追加、色と名前を編集）
  - 概要ペイン: 端末の**実測値**カードを 12 カラムグリッドに配置。全幅 / 1・2 幅。
    「編集」でドラッグ並べ替え・表示切替・幅変更・既定に戻す
  - カテゴリーペイン: そのカテゴリーに入れ子にしたアプリを一覧（長押しで外す）
- **ウィジェットピッカー** — インストール済みプロバイダの一覧。選ぶと ID 払い出し →
  バインド同意 → 設定画面 → 保存まで進む。

## 配置モデル（重要）

- グリッドは **常に全アプリ** を自動整列で表示する。ドックはフィルタではなく参照なので、
  同じアプリがグリッドとドックの両方に出るのが正しい挙動。
- 永続化するのは **`packageName` の順序リスト 1 本だけ**。`ComponentName` を保存しないのは、
  アプリ更新でランチャーアクティビティのクラス名が変わると配置が消えるため。
- お気に入りは `FavoritesRepository` でインストール済みアプリの Flow と `combine` して解決する。
  アンインストールされたアプリは自動でドックから落ちるので、削除処理を書く必要がない。

## コンソールのカードは実測値

ダミーではなく端末から実際に取れる値を 2 秒ごとに読み直す（`DeviceMetricsRepository`）。
コンソールを開いている間だけポーリングし、Flow の購読が切れれば止まる。

| カード | 内容 | タップ |
|---|---|---|
| バッテリー | 残量・充電状態・温度 | 電池使用量の設定 |
| ストレージ | 使用量 / 空き | ストレージ設定 |
| メモリ | 使用量 / 全体 | — |
| ネットワーク | 回線種別・VPN・推定下り速度 | ネットワーク設定 |
| 連続稼働 | 最終起動からの経過 | — |
| アプリ | 起動可能なアプリ数（仕事用を含む） | アプリ設定 |
| 最近追加したアプリ | 直近 3 件、タップで起動 | — |

追加の権限が要るもの（通信量、利用時間、Wi-Fi の SSID）は入れていない。使うのは
`ACCESS_NETWORK_STATE`（通常権限）だけで、実行時の許可ダイアログは出ない。
リングやバーは値が変わるとアニメーションし、上部には更新中を示す明滅インジケーターを出す。

## カテゴリー（コンソール左レール）

ユーザーが自分で作る。1 カテゴリー = 名前 + 色 + 所属アプリ（`packageName` の配列）。
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

コンソールの数値は `DashboardDataSource` の固定値（ダミー）。実データを繋ぐときは
このクラスだけ差し替えれば、カード側は変更しなくてよい。
