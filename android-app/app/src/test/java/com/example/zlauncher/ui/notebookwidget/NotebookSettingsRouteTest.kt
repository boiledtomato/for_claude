package com.example.zlauncher.ui.notebookwidget

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * ウィジェットの「設定してください」をタップしたとき、本当に設定が開くか。
 *
 * **一度これを落とした。** ウィジェット側は `PendingIntent` に action を載せていたのに、
 * 受け取って設定を開く処理がどこにも無く、押すとランチャーが開くだけだった。
 * ビルドもテストも lint も通る ― 文字列が一致しているかは誰も見ていないため。
 *
 * 投げる側と受ける側が**同じ定数**を使っていること、受ける側が
 * **起動時と再利用時の両方**を見ていることを固定する。アプリが死んでいるときに
 * 押すと onCreate に、生きているときは onNewIntent に届くので、片方だけでは
 * 「たまに効かないボタン」になる。
 */
class NotebookSettingsRouteTest {

    private val providers =
        File("src/main/java/com/example/zlauncher/ui/notebookwidget/NotebookWidgetProviders.kt").readText()
    private val mainActivity = File("src/main/java/com/example/zlauncher/MainActivity.kt").readText()
    private val overview =
        File("src/main/java/com/example/zlauncher/ui/console/OverviewPane.kt").readText()
    private val viewModel =
        File("src/main/java/com/example/zlauncher/ui/console/ConsoleViewModel.kt").readText()

    @Test
    fun `the widget's action is routed by name, not retyped`() {
        // 片方だけ書き換えても気付けるよう、受ける側は定数を参照する
        assertTrue(
            "ウィジェットが投げる action の定数が無い",
            providers.contains("const val ACTION_OPEN_NOTEBOOK_SETTINGS"),
        )
        assertTrue(
            "MainActivity が同じ定数で受けていない。文字列を打ち直すと黙ってずれる",
            mainActivity.contains("NotebookWidget.ACTION_OPEN_NOTEBOOK_SETTINGS"),
        )
    }

    @Test
    fun `both the cold start and the warm relaunch are handled`() {
        // ランチャーは singleTask。死んでいれば onCreate、生きていれば onNewIntent
        val routed = Regex("routeNotebookSettings\\(").findAll(mainActivity).count()
        assertTrue(
            "onCreate と onNewIntent の両方から呼ばれていない（定義 1 + 呼び出し 2 = 3）",
            routed >= 3,
        )
        assertTrue(mainActivity.contains("override fun onCreate"))
        assertTrue(mainActivity.contains("override fun onNewIntent"))
    }

    @Test
    fun `the request moves to Overview before raising the dialog`() {
        // ダイアログは Overview の面が持っている。別の面を見たままでは出せないので、
        // 面を移してから立てる。実機では Widgets の面から押される
        val block = viewModel.substringAfter("notebookSettingsRequests.requests.collect")
            .substringBefore("}")
        assertTrue(
            "Overview へ移さずにフラグだけ立てても、ダイアログを持つ面が合成されない",
            block.contains("select(ConsolePane.Overview)"),
        )
    }

    @Test
    fun `the request is consumed, so the dialog does not reopen forever`() {
        // 合図は replay 付き（起動のきっかけそのものなので残す必要がある）。
        // 消さないと画面を作り直すたびに開く
        assertTrue(overview.contains("consumeNotebookSettingsRequest()"))
        assertTrue(viewModel.contains("fun consumeNotebookSettingsRequest()"))
    }
}
