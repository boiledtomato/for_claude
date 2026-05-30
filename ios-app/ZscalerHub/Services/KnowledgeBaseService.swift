import Foundation

@Observable
final class KnowledgeBaseService {
    static let shared = KnowledgeBaseService()

    private(set) var topics: [KnowledgeTopic] = []
    private(set) var isLoaded = false

    private init() { load() }

    private func load() {
        guard
            let url = Bundle.main.url(forResource: "knowledge_base", withExtension: "json"),
            let data = try? Data(contentsOf: url),
            let kb = try? JSONDecoder().decode(KnowledgeBase.self, from: data)
        else {
            print("[KnowledgeBase] ロード失敗")
            return
        }
        topics = kb.topics
        isLoaded = true
        print("[KnowledgeBase] \(topics.count)件のトピックをロードしました")
    }

    func topics(for category: KnowledgeTopic.TopicCategory) -> [KnowledgeTopic] {
        topics.filter { $0.category == category }
    }

    func topic(id: String) -> KnowledgeTopic? {
        topics.first { $0.id == id }
    }

    func search(query: String) -> [KnowledgeTopic] {
        guard !query.isEmpty else { return topics }
        let q = query.lowercased()
        return topics.filter {
            $0.title.lowercased().contains(q) ||
            $0.summary.lowercased().contains(q) ||
            $0.content.lowercased().contains(q) ||
            $0.tags.contains { $0.lowercased().contains(q) }
        }
    }

    /// Builds context string to inject into Claude prompt.
    func buildContext(for query: String) -> String {
        let hits = search(query: query)
        let chosen = hits.isEmpty ? Array(topics.prefix(8)) : Array(hits.prefix(6))
        return chosen.map { t in
            "【\(t.title)】（\(t.category.rawValue)）\n\(t.content)"
        }.joined(separator: "\n\n---\n\n")
    }
}
