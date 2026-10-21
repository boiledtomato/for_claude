# Critter Home — 携帯 RPG 風のホームランチャー

インストール済みのアプリを「住みか」に振り分け、住みかごとに 1 体の相棒がいるホームアプリ。
**アプリを開くことがそのまま相棒を育てることになる。** ZLauncher（`android-app/`）とは
コードも署名鍵も Release も共有しない、独立したアプリ。

- ダウンロード: <https://github.com/boiledtomato/for_claude/releases/download/critter-debug-latest/critter-home-debug.apk>
- applicationId: `com.example.critterhome.debug`（ZLauncher・既定のランチャーと共存する）
- 権限: なし（ネットワークも使わない。電池残量は権限不要の sticky broadcast）
- 画面の文言は英語

**動作確認が済むまで、既定のホームアプリには設定しないこと。**

## 画面

| 画面 | 中身 |
|---|---|
| HOME | 時計・日付、トレーナーの Lv と HP（＝電池残量）、最近開いたアプリ 5 つ、手持ち（最大 6 体）、メッセージウィンドウ、メニュー |
| BAG | 全アプリ。検索、住みかで絞り込み。押すと起動、長押しで `USE` / `MOVE HABITAT` / `APP INFO` |
| SUMMARY | 相棒のステータス。下の「わざ」はその住みかのアプリで、押すと起動する |
| BOX | 手持ちに入らなかった相棒と、まだ出会っていない住みか（押すとその住みかで絞った BAG） |
| BADGES | 9 個 |
| CARD | トレーナーカード |

戻るは 1 画面ずつ戻り、HOME では何もしない（ホームアプリは終了しない）。HOME キーはどこからでも
HOME へ戻す（`onNewIntent`）。

## 遊び方の規則（`game/GameRules.kt`、`GameRulesTest` が固定）

| 仕組み | 中身 |
|---|---|
| 出会い | 住みかのアプリを初めて開いたとき。`A wild X appeared in the Arcade!` |
| 経験値 | 1 起動 20。その日最初の起動には連続日数 × 10（7 日ぶんまで）を上乗せ。同じアプリを 1 分以内に開き直すと 2 だけ |
| レベル | Lv. n に要る累計は n³（上限 100）。トレーナーも同じ曲線で、全起動ぶんが入る |
| 進化 | Lv. 16 と 36。名前の頭の音節は変えず、ドット絵は 10 → 12 → 14 マスに大きくなる |
| HP | 最後にその住みかのアプリを開いた日から 7 日で 0（ひんし）。1 回開けば戻る ― 罰ではなく「最近使っていない」の目印 |
| 手持ち / BOX | 出会った順に最初の 6 体が手持ち。残りと未遭遇は BOX |

**数えるのはこのアプリから開いた分だけ。** 通知や他のアプリから開いた分は数えない
（使用状況へのアクセスを求めないため）。起動に成功したときだけ記録する。

## 住みか（`game/Habitat.kt`、`HabitatsTest` が固定）

Arcade / Music Hall / Theater / Gallery / Plaza / Newsroom / Wilds / Workshop / Lab / Meadow の 10 か所。
利用者にカテゴリーを作らせず、自動で振り分ける:

1. 利用者が `MOVE HABITAT` で移したアプリはその住みか（自動と同じ住みかに戻したら上書きごと消す）
2. アプリが宣言した `ApplicationInfo.category` がはっきりしていればそれ（GAME → Arcade など）。
   「生産性」「アクセシビリティ」は範囲が広すぎるので、先に名前の言葉を見る
3. パッケージ名とアプリ名の言葉。**4 文字以下の語は語として一致したときだけ**当てる ―
   "line" が "online" に、"map" が "bitmap" に当たってしまうので
4. どれにも当たらなければ Meadow

## 相棒は生成する

- **実在のゲームの素材は使わない。** キャラクター・名前・タイプ名・ロゴ・音はどれも権利物なので、
  名前は音節表から（`MonsterSpecies`）、姿は種から生成する（`MonsterSprite`）。画像ファイルも
  持たない ― 左半分を中心ほど濃く塗って左右に写し、中心から繋がっていない欠片を捨てて輪郭を
  回すだけで生き物らしい形になる
- **名前も姿も保存しない。** 種は「端末ごとの塩 + 住みかの key」（`GameState.seedOf`）で、毎回
  同じものを導く。塩は最初の起動で 1 度だけ決めるので、同じ Arcade でも端末が違えば別の相棒になる
- 保存しているのは経験値・出会った日・最後に開いた日・起動回数・バッジ・連続日数・最近のアプリ・
  住みかの上書きだけ（DataStore `game`、JSON 1 本。壊れていたら最初からやり直す）

## 構成

DI ライブラリは使わない（`CritterApplication` が `AppsRepository` と `GameStore` を 1 つずつ持つ）。
画面は 1 枚の Activity と `MainViewModel` の小さな戻る履歴（`stack`）。

```
app/src/main/java/com/example/critterhome/
├── game/      Android に触れない規則（Habitat, GameRules, GameState, MonsterSpecies, MonsterSprite）
├── data/      LauncherApps・DataStore・電池
└── ui/        配色（GameTheme）、レトロな部品（RetroKit）、各画面
```

## ビルド

```bash
cd critter-app
./gradlew testDebugUnitTest assembleDebug lintDebug   # compileSdk 36 の Android SDK が要る
```

`.github/workflows/critter-build.yml` が `critter-app/` への push のたびに同じことをして、
Release `critter-debug-latest` の APK を差し替える。**このアプリ専用の Release なので、
どのブランチの push でも更新する**（ZLauncher の `android-debug-latest` は main と `claude/*`
だけ）。`keystore/debug.keystore` はこのアプリ専用の固定デバッグ鍵で、上書き更新を通すために
コミットしてある。release には使わない。
