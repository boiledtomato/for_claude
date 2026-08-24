# ZLauncher — Android ホームランチャー MVP

Kotlin + Jetpack Compose のホームランチャー。ホーム画面（アプリグリッド＋お気に入りドック）と、
カード型のコンソール画面（ダークコンソール調・並べ替え可能）の 2 画面構成。

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
│   └── dashboard/                     # ダミーデータとカード配置の永続化
└── ui/
    ├── home/                          # ホーム画面（グリッド＋ドック＋長押しメニュー）
    ├── dashboard/                      # コンソール画面・カード登録表・カード実装
    ├── navigation/                    # NavHost
    └── setup/DefaultLauncher.kt       # ROLE_HOME / ホームアプリ設定への導線
```

## 画面

- **ホーム** — 起動可能アプリを 4 列グリッドに自動整列（名前順 / インストール日時順）。
  下部にお気に入りドック 4 枠。アイコン長押しで「ドックに追加 / アプリ情報」。
- **コンソール** — 12 カラムグリッドにカードを配置。全幅 / 1・2 幅。
  「編集」でドラッグ並べ替え・表示切替・幅変更・既定に戻す。

## 配置モデル（重要）

- グリッドは **常に全アプリ** を自動整列で表示する。ドックはフィルタではなく参照なので、
  同じアプリがグリッドとドックの両方に出るのが正しい挙動。
- 永続化するのは **`packageName` の順序リスト 1 本だけ**。`ComponentName` を保存しないのは、
  アプリ更新でランチャーアクティビティのクラス名が変わると配置が消えるため。
- お気に入りは `FavoritesRepository` でインストール済みアプリの Flow と `combine` して解決する。
  アンインストールされたアプリは自動でドックから落ちるので、削除処理を書く必要がない。

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
| アイコンのラスタライズは IO + LruCache | `AppIconLoader` |
| `LauncherApps` の呼び出しはすべて例外を握って空に落とす | `LauncherAppsDataSource` |

## ビルド

```bash
# Android SDK が必要（compileSdk 36 / build-tools 36.0.0）
echo "sdk.dir=/path/to/Android/sdk" > local.properties
./gradlew assembleDebug     # または: gradle assembleDebug
```

Gradle wrapper の jar はコミットしていないため、初回は Android Studio で開くか
`gradle wrapper` を一度実行する。

依存バージョンは `gradle/libs.versions.toml` の 1 箇所にまとめてある
（AGP 8.11 / Kotlin 2.1.21 / Compose BOM 2025.06.00 の組み合わせで
`assembleDebug` と `lintDebug` の通過を確認済み）。

## 検証時の注意

**実機で「既定のホーム」に指定するのは動作確認が済んでから。** debug ビルドは
applicationId が別なので標準ランチャーと共存するが、既定に指定した状態でクラッシュすると
復帰操作が取りづらい。コンソール画面には常設で「ホームアプリ設定を開く」導線を置いてある。

## MVP に含まれないもの

ウィジェット（`AppWidgetHost`）、フォルダ、ページ分割ワークスペース、自由座標配置、
壁紙ピッカー、通知バッジ、ディープショートカット、Work Profile、アプリ検索。

コンソールの数値は `DashboardDataSource` の固定値（ダミー）。実データを繋ぐときは
このクラスだけ差し替えれば、カード側は変更しなくてよい。
