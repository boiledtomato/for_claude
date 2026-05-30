import SwiftUI

struct DocDetailView: View {
    let topic: KnowledgeTopic
    private let kb = KnowledgeBaseService.shared

    private var related: [KnowledgeTopic] {
        topic.relatedTopicIds.compactMap { kb.topic(id: $0) }
    }

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 20) {
                headerSection
                if !topic.tags.isEmpty { tagsSection }
                Divider()
                contentSection
                if let ex = topic.configExample, !ex.isEmpty { configExampleSection(ex) }
                if !related.isEmpty { relatedSection }
            }
            .padding()
        }
        .navigationTitle(topic.title)
        .navigationBarTitleDisplayMode(.large)
    }

    private var headerSection: some View {
        HStack(spacing: 12) {
            Image(systemName: topic.category.icon)
                .font(.title)
                .foregroundStyle(colorForCategory(topic.category))
                .frame(width: 48, height: 48)
                .background(colorForCategory(topic.category).opacity(0.15))
                .clipShape(RoundedRectangle(cornerRadius: 10))
            VStack(alignment: .leading, spacing: 4) {
                Text(topic.category.rawValue)
                    .font(.caption).foregroundStyle(.secondary)
                Text(topic.subcategory)
                    .font(.subheadline).foregroundStyle(.secondary)
            }
            Spacer()
        }
    }

    private var tagsSection: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 6) {
                ForEach(topic.tags, id: \.self) { tag in
                    Text(tag)
                        .font(.caption)
                        .padding(.horizontal, 8).padding(.vertical, 4)
                        .background(Color.cyan.opacity(0.15))
                        .foregroundStyle(.cyan)
                        .clipShape(Capsule())
                }
            }
        }
    }

    private var contentSection: some View {
        Text(topic.content)
            .font(.body)
            .lineSpacing(5)
            .textSelection(.enabled)
    }

    private func configExampleSection(_ example: String) -> some View {
        VStack(alignment: .leading, spacing: 8) {
            Label("設定例 / コマンド例", systemImage: "terminal.fill")
                .font(.headline).foregroundStyle(.cyan)
            ScrollView(.horizontal, showsIndicators: false) {
                Text(example)
                    .font(.system(.footnote, design: .monospaced))
                    .padding()
                    .background(Color(.systemGray6))
                    .clipShape(RoundedRectangle(cornerRadius: 8))
            }
        }
    }

    private var relatedSection: some View {
        VStack(alignment: .leading, spacing: 10) {
            Label("関連トピック", systemImage: "link")
                .font(.headline)
            ForEach(related) { r in
                NavigationLink(destination: DocDetailView(topic: r)) {
                    HStack {
                        Image(systemName: r.category.icon)
                            .foregroundStyle(colorForCategory(r.category))
                        Text(r.title)
                            .foregroundStyle(.primary)
                        Spacer()
                        Image(systemName: "chevron.right").foregroundStyle(.secondary)
                    }
                    .padding(10)
                    .background(Color(.secondarySystemBackground))
                    .clipShape(RoundedRectangle(cornerRadius: 10))
                }
            }
        }
    }
}
