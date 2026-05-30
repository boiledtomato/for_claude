import SwiftUI

struct MainTabView: View {
    var body: some View {
        TabView {
            SearchView()
                .tabItem { Label("検索", systemImage: "magnifyingglass") }

            DocBrowserView()
                .tabItem { Label("ドキュメント", systemImage: "book.fill") }

            SimulatorView()
                .tabItem { Label("シミュレーター", systemImage: "cpu") }

            LogView()
                .tabItem { Label("ログ", systemImage: "list.clipboard.fill") }

            SettingsView()
                .tabItem { Label("設定", systemImage: "gearshape.fill") }
        }
        .tint(.cyan)
    }
}
