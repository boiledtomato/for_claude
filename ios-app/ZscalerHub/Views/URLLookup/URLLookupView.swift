import SwiftUI

struct URLLookupView: View {
    @State private var vm = URLLookupViewModel()

    var body: some View {
        NavigationStack {
            Form {
                inputSection
                if vm.isLooking    { loadingSection }
                if let e = vm.errorMessage { errorSection(e) }
                if let r = vm.result       { resultSection(r) }
                disclaimerSection
            }
            .navigationTitle("URL Lookup")
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button("リセット") { vm.reset() }
                }
            }
        }
    }

    // MARK: - Sections

    private var inputSection: some View {
        Section {
            LabeledContent("FQDN") {
                TextField("例: www.google.com", text: $vm.fqdn)
                    .multilineTextAlignment(.trailing)
                    .keyboardType(.URL)
                    .autocorrectionDisabled()
                    .textInputAutocapitalization(.never)
                    .onSubmit { vm.runLookup() }
            }
            Button {
                vm.runLookup()
            } label: {
                Label("検索", systemImage: "magnifyingglass")
                    .frame(maxWidth: .infinity)
                    .font(.headline)
            }
            .disabled(vm.fqdn.trimmingCharacters(in: .whitespaces).isEmpty)
        } header: { Text("FQDN入力") }
          footer: { Text("プロトコル（https://）は省略可。サブドメインも入力できます。") }
    }

    private var loadingSection: some View {
        Section {
            HStack {
                ProgressView()
                Text("検索中...").foregroundStyle(.secondary)
            }
        }
    }

    private func errorSection(_ msg: String) -> some View {
        Section {
            Label(msg, systemImage: "exclamationmark.triangle.fill")
                .foregroundStyle(.red)
        }
    }

    @ViewBuilder
    private func resultSection(_ r: LookupResult) -> some View {
        Section {
            HStack {
                Text(r.fqdn)
                    .font(.system(.body, design: .monospaced))
                    .foregroundStyle(.cyan)
                Spacer()
                matchBadge(r.matchType)
            }
        } header: { Text("検索対象") }

        Section("URLカテゴリ（URL Filtering）") {
            infoRow("カテゴリ",    r.record.urlCategory,      .primary)
            infoRow("サービス", "Zscaler URL Filtering",       .secondary)
        }

        Section("Cloud Application Control") {
            infoRow("カテゴリ",    r.record.cloudAppCategory, .primary)
            infoRow("アプリ名",    r.record.cloudApp,         .secondary)
        }

        Section("リスク評価") {
            infoRow("リスクレベル",   r.record.riskLevel.rawValue,          r.record.riskLevel.color)
            infoRow("推奨アクション", r.record.recommendedAction.rawValue,   r.record.recommendedAction.color)
        }

        // Quick samples
        Section {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(URLLookupData.samples, id: \.self) { s in
                        Button(s) { vm.applySample(s) }
                            .buttonStyle(.bordered)
                            .controlSize(.small)
                            .font(.system(.caption, design: .monospaced))
                    }
                }
                .padding(.vertical, 4)
            }
        } header: { Text("クイック検索") }
    }

    private var disclaimerSection: some View {
        Section {
            Text("⚠️ このURL Lookupはシミュレーターです。実際のZscalerポータルの機能とは異なる場合があります。本番環境では Zscaler Admin Portal → URL Lookup をご利用ください。")
                .font(.caption)
                .foregroundStyle(.secondary)
        }
    }

    // MARK: - Helpers

    private func infoRow(_ label: String, _ value: String, _ color: Color) -> some View {
        LabeledContent(label) {
            Text(value)
                .foregroundStyle(color)
                .multilineTextAlignment(.trailing)
        }
    }

    private func matchBadge(_ type: URLLookupRecord.MatchType) -> some View {
        let (label, color): (String, Color) = switch type {
        case .exact:    ("DB一致",       .green)
        case .parent:   ("親ドメイン",   .cyan)
        case .estimate: ("推定",         .yellow)
        case .unknown:  ("不明",         .gray)
        }
        return Text(label)
            .font(.caption2).fontWeight(.bold)
            .padding(.horizontal, 8).padding(.vertical, 3)
            .background(color.opacity(0.15))
            .foregroundStyle(color)
            .clipShape(Capsule())
    }
}

#Preview {
    URLLookupView()
}
