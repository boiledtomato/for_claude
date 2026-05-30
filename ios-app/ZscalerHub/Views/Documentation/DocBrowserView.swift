import SwiftUI

struct DocBrowserView: View {
    private let kb = KnowledgeBaseService.shared
    @State private var selectedCategory: KnowledgeTopic.TopicCategory? = nil
    @State private var searchText = ""

    private var displayed: [KnowledgeTopic] {
        var base = kb.topics
        if let cat = selectedCategory { base = base.filter { $0.category == cat } }
        if !searchText.isEmpty { base = base.filter {
            $0.title.localizedCaseInsensitiveContains(searchText) ||
            $0.summary.localizedCaseInsensitiveContains(searchText) ||
            $0.tags.contains { $0.localizedCaseInsensitiveContains(searchText) }
        }}
        return base
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                categoryBar
                List {
                    ForEach(displayed) { topic in
                        NavigationLink(destination: DocDetailView(topic: topic)) {
                            TopicRow(topic: topic)
                        }
                    }
                }
                .listStyle(.plain)
                .overlay {
                    if displayed.isEmpty {
                        ContentUnavailableView.search(text: searchText)
                    }
                }
            }
            .navigationTitle("ドキュメント")
            .searchable(text: $searchText, prompt: "ドキュメントを検索...")
        }
    }

    private var categoryBar: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 8) {
                CategoryChip(title: "すべて (\(kb.topics.count))",
                             icon: "square.grid.2x2.fill",
                             isSelected: selectedCategory == nil) {
                    selectedCategory = nil
                }
                ForEach(KnowledgeTopic.TopicCategory.allCases, id: \.self) { cat in
                    let count = kb.topics(for: cat).count
                    CategoryChip(title: "\(cat.rawValue) (\(count))",
                                 icon: cat.icon,
                                 isSelected: selectedCategory == cat) {
                        selectedCategory = selectedCategory == cat ? nil : cat
                    }
                }
            }
            .padding(.horizontal)
            .padding(.vertical, 8)
        }
    }
}

// MARK: - Subviews

struct TopicRow: View {
    let topic: KnowledgeTopic

    var categoryColor: Color { colorForCategory(topic.category) }

    var body: some View {
        HStack(alignment: .top, spacing: 12) {
            Image(systemName: topic.category.icon)
                .font(.title2)
                .foregroundStyle(categoryColor)
                .frame(width: 36, height: 36)
                .background(categoryColor.opacity(0.15))
                .clipShape(RoundedRectangle(cornerRadius: 8))

            VStack(alignment: .leading, spacing: 4) {
                Text(topic.title)
                    .font(.headline)
                    .lineLimit(1)
                Text(topic.summary)
                    .font(.caption)
                    .foregroundStyle(.secondary)
                    .lineLimit(2)
                if !topic.tags.isEmpty {
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 4) {
                            ForEach(topic.tags.prefix(4), id: \.self) { tag in
                                Text(tag)
                                    .font(.caption2)
                                    .padding(.horizontal, 6).padding(.vertical, 2)
                                    .background(Color.cyan.opacity(0.15))
                                    .foregroundStyle(.cyan)
                                    .clipShape(Capsule())
                            }
                        }
                    }
                }
            }
        }
        .padding(.vertical, 4)
    }
}

struct CategoryChip: View {
    let title: String
    let icon: String
    let isSelected: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Label(title, systemImage: icon)
                .font(.subheadline)
                .padding(.horizontal, 12).padding(.vertical, 6)
                .background(isSelected ? Color.cyan : Color(.secondarySystemBackground))
                .foregroundStyle(isSelected ? Color.black : Color.primary)
                .clipShape(Capsule())
        }
    }
}

func colorForCategory(_ cat: KnowledgeTopic.TopicCategory) -> Color {
    switch cat {
    case .zia:          return .blue
    case .zpa:          return .cyan
    case .zdx:          return .green
    case .architecture: return .orange
    case .bestPractice: return .purple
    }
}
