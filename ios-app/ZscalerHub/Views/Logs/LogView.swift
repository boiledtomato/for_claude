import SwiftUI
import SwiftData

struct LogView: View {
    @Environment(\.modelContext) private var modelContext
    @Query(sort: \SearchLogEntry.timestamp, order: .reverse) private var searchLogs: [SearchLogEntry]
    @Query(sort: \SimulationLogEntry.timestamp, order: .reverse) private var simLogs: [SimulationLogEntry]
    @State private var tab = 0
    @State private var showDeleteConfirm = false

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                Picker("", selection: $tab) {
                    Label("検索ログ (\(searchLogs.count))", systemImage: "magnifyingglass").tag(0)
                    Label("シミュレーション (\(simLogs.count))", systemImage: "cpu").tag(1)
                }
                .pickerStyle(.segmented)
                .padding()

                if tab == 0 { searchLogList } else { simLogList }
            }
            .navigationTitle("ログ")
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button(role: .destructive) { showDeleteConfirm = true } label: {
                        Image(systemName: "trash")
                    }
                    .disabled(tab == 0 ? searchLogs.isEmpty : simLogs.isEmpty)
                }
            }
            .confirmationDialog("すべてのログを削除しますか？", isPresented: $showDeleteConfirm,
                                titleVisibility: .visible) {
                Button("削除", role: .destructive) { clearCurrentTab() }
            }
        }
    }

    // MARK: - Lists

    private var searchLogList: some View {
        Group {
            if searchLogs.isEmpty {
                emptyState(icon: "magnifyingglass", text: "検索ログがありません")
            } else {
                List {
                    ForEach(searchLogs) { log in
                        NavigationLink(destination: SearchLogDetail(log: log)) {
                            SearchLogRow(log: log)
                        }
                    }
                    .onDelete { offsets in offsets.forEach { modelContext.delete(searchLogs[$0]) } }
                }
                .listStyle(.plain)
            }
        }
    }

    private var simLogList: some View {
        Group {
            if simLogs.isEmpty {
                emptyState(icon: "cpu", text: "シミュレーションログがありません")
            } else {
                List {
                    ForEach(simLogs) { log in
                        NavigationLink(destination: SimLogDetail(log: log)) {
                            SimLogRow(log: log)
                        }
                    }
                    .onDelete { offsets in offsets.forEach { modelContext.delete(simLogs[$0]) } }
                }
                .listStyle(.plain)
            }
        }
    }

    private func emptyState(icon: String, text: String) -> some View {
        VStack(spacing: 12) {
            Spacer()
            Image(systemName: icon).font(.system(size: 48)).foregroundStyle(.secondary)
            Text(text).foregroundStyle(.secondary)
            Spacer()
        }
    }

    private func clearCurrentTab() {
        if tab == 0 { searchLogs.forEach { modelContext.delete($0) } }
        else { simLogs.forEach { modelContext.delete($0) } }
    }
}

// MARK: - Row Views

struct SearchLogRow: View {
    let log: SearchLogEntry
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack {
                Image(systemName: "magnifyingglass").foregroundStyle(.cyan)
                Text(log.query).font(.headline).lineLimit(1)
                Spacer()
                if log.isBookmarked {
                    Image(systemName: "bookmark.fill").foregroundStyle(.yellow).font(.caption)
                }
            }
            Text(log.response.prefix(120) + "…")
                .font(.caption).foregroundStyle(.secondary).lineLimit(2)
            Text(log.timestamp.formatted(date: .abbreviated, time: .shortened))
                .font(.caption2).foregroundStyle(.tertiary)
        }
        .padding(.vertical, 4)
    }
}

struct SimLogRow: View {
    let log: SimulationLogEntry

    private var actionColor: Color {
        if log.resultAction.contains("許可") { return .green }
        if log.resultAction.contains("拒否") || log.resultAction.contains("ブロック") { return .red }
        return .orange
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack {
                Text(log.product)
                    .font(.caption).padding(.horizontal, 6).padding(.vertical, 2)
                    .background(Color.blue.opacity(0.15)).foregroundStyle(.blue).clipShape(Capsule())
                Text(log.scenarioTitle).font(.headline).lineLimit(1)
                Spacer()
            }
            Text(log.inputDescription).font(.caption).foregroundStyle(.secondary).lineLimit(1)
            HStack {
                Circle().fill(actionColor).frame(width: 8, height: 8)
                Text(log.resultAction).font(.caption.bold()).foregroundStyle(actionColor)
                Spacer()
                Text(log.timestamp.formatted(date: .abbreviated, time: .shortened))
                    .font(.caption2).foregroundStyle(.tertiary)
            }
        }
        .padding(.vertical, 4)
    }
}

// MARK: - Detail Views

struct SearchLogDetail: View {
    @Bindable var log: SearchLogEntry

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                detailCard(label: "検索クエリ", icon: "magnifyingglass", color: .cyan) {
                    Text(log.query).font(.headline)
                }
                detailCard(label: "AI回答", icon: "brain", color: .blue) {
                    Text(log.response).textSelection(.enabled).lineSpacing(4)
                }
                if !log.sources.isEmpty {
                    detailCard(label: "参照トピック", icon: "book", color: .purple) {
                        ForEach(log.sources, id: \.self) { s in
                            Label(s, systemImage: "doc.text").font(.subheadline)
                        }
                    }
                }
                Text(log.timestamp.formatted(date: .complete, time: .complete))
                    .font(.caption).foregroundStyle(.secondary)
            }
            .padding()
        }
        .navigationTitle("検索詳細")
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button {
                    log.isBookmarked.toggle()
                } label: {
                    Image(systemName: log.isBookmarked ? "bookmark.fill" : "bookmark")
                        .foregroundStyle(log.isBookmarked ? .yellow : .secondary)
                }
            }
        }
    }

    private func detailCard<Content: View>(
        label: String, icon: String, color: Color,
        @ViewBuilder content: () -> Content
    ) -> some View {
        VStack(alignment: .leading, spacing: 8) {
            Label(label, systemImage: icon).font(.caption.bold()).foregroundStyle(color)
            content()
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding()
        .background(Color(.secondarySystemBackground))
        .clipShape(RoundedRectangle(cornerRadius: 12))
    }
}

struct SimLogDetail: View {
    let log: SimulationLogEntry

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                VStack(alignment: .leading, spacing: 10) {
                    ResultInfoRow(key: "製品", value: log.product)
                    ResultInfoRow(key: "シナリオ種別", value: log.scenarioType)
                    Divider()
                    ResultInfoRow(key: "入力条件", value: log.inputDescription)
                    Divider()
                    ResultInfoRow(key: "結果", value: log.resultSummary)
                    ResultInfoRow(key: "アクション", value: log.resultAction)
                }
                .padding()
                .background(Color(.secondarySystemBackground))
                .clipShape(RoundedRectangle(cornerRadius: 12))

                Text(log.timestamp.formatted(date: .complete, time: .complete))
                    .font(.caption).foregroundStyle(.secondary)
            }
            .padding()
        }
        .navigationTitle(log.scenarioTitle)
    }
}
