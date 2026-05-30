import SwiftUI
import SwiftData

struct SearchView: View {
    @Environment(\.modelContext) private var modelContext
    @State private var vm = SearchViewModel()
    @FocusState private var focused: Bool

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                searchBar
                    .padding()

                Group {
                    if vm.isLoading {
                        loadingView
                    } else if let err = vm.errorMessage {
                        errorView(err)
                    } else if !vm.response.isEmpty {
                        resultView
                    } else {
                        placeholderView
                    }
                }
            }
            .navigationTitle("Zscaler ナレッジ検索")
            .navigationBarTitleDisplayMode(.large)
        }
    }

    // MARK: - Search bar

    private var searchBar: some View {
        HStack(spacing: 10) {
            Image(systemName: "magnifyingglass")
                .foregroundStyle(.secondary)

            TextField("機能・設定・トラブルを検索...", text: $vm.query)
                .focused($focused)
                .submitLabel(.search)
                .autocorrectionDisabled()
                .onSubmit { Task { await vm.search(modelContext: modelContext) } }

            if !vm.query.isEmpty {
                Button { vm.clear() } label: {
                    Image(systemName: "xmark.circle.fill").foregroundStyle(.secondary)
                }
            }
        }
        .padding(12)
        .background(Color(.secondarySystemBackground))
        .clipShape(RoundedRectangle(cornerRadius: 14))
    }

    // MARK: - States

    private var loadingView: some View {
        VStack(spacing: 16) {
            Spacer()
            ProgressView().scaleEffect(1.5)
            Text("AIが調査中...").foregroundStyle(.secondary)
            Spacer()
        }
    }

    private func errorView(_ msg: String) -> some View {
        VStack(spacing: 16) {
            Spacer()
            Image(systemName: "exclamationmark.triangle.fill")
                .font(.system(size: 48))
                .foregroundStyle(.orange)
            Text(msg)
                .multilineTextAlignment(.center)
                .foregroundStyle(.secondary)
                .padding(.horizontal)
            Button("再試行") {
                Task { await vm.search(modelContext: modelContext) }
            }
            .buttonStyle(.borderedProminent)
            Spacer()
        }
    }

    private var resultView: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 14) {
                // Query chip
                Label(vm.query, systemImage: "quote.bubble.fill")
                    .font(.subheadline.bold())
                    .foregroundStyle(.cyan)
                    .padding(10)
                    .background(Color.cyan.opacity(0.1))
                    .clipShape(RoundedRectangle(cornerRadius: 10))

                // AI response (rendered as plain text; Markdown renders natively in iOS 15+)
                Text(vm.response)
                    .textSelection(.enabled)
                    .padding()
                    .background(Color(.secondarySystemBackground))
                    .clipShape(RoundedRectangle(cornerRadius: 14))

                // Actions
                HStack {
                    Button {
                        vm.clear()
                        focused = true
                    } label: {
                        Label("新しい検索", systemImage: "magnifyingglass")
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(.bordered)
                }
                .padding(.bottom)
            }
            .padding()
        }
    }

    private var placeholderView: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                if !vm.recentQueries.isEmpty {
                    recentSection
                }
                suggestionsSection
            }
            .padding()
        }
    }

    private var recentSection: some View {
        VStack(alignment: .leading, spacing: 8) {
            Label("最近の検索", systemImage: "clock")
                .font(.headline).foregroundStyle(.secondary)
            ForEach(vm.recentQueries, id: \.self) { q in
                chipButton(q, icon: "arrow.counterclockwise")
            }
        }
    }

    private var suggestionsSection: some View {
        VStack(alignment: .leading, spacing: 8) {
            Label("おすすめクエリ", systemImage: "lightbulb.fill")
                .font(.headline).foregroundStyle(.secondary)
            ForEach(suggestions, id: \.self) { s in
                chipButton(s, icon: "arrow.up.right")
            }
        }
    }

    private func chipButton(_ text: String, icon: String) -> some View {
        Button {
            vm.query = text
            Task { await vm.search(modelContext: modelContext) }
        } label: {
            HStack {
                Image(systemName: icon).foregroundStyle(.cyan).frame(width: 20)
                Text(text).foregroundStyle(.primary).multilineTextAlignment(.leading)
                Spacer()
            }
            .padding(10)
            .background(Color(.secondarySystemBackground))
            .clipShape(RoundedRectangle(cornerRadius: 10))
        }
    }

    private let suggestions = [
        "SSLインスペクションの設定と証明書配布方法",
        "ZPAアプリコネクターの構築手順",
        "URLフィルタリングポリシーのベストプラクティス",
        "ZDXでデジタルエクスペリエンスを監視する方法",
        "SAML/IdP連携の設定手順",
        "GREトンネルとIPSecの違いと選択基準",
        "DLPポリシーでクレジットカード番号を検出する方法",
        "クラウドファイアウォールのFQDNカテゴリ制御",
        "ZIAとZPAの転送設定（Forwarding Profile）",
        "ゼロトラストアーキテクチャにおけるZscalerの役割",
    ]
}
