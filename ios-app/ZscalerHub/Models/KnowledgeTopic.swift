import Foundation

struct KnowledgeTopic: Codable, Identifiable, Hashable {
    let id: String
    let title: String
    let category: TopicCategory
    let subcategory: String
    let summary: String
    let content: String
    let tags: [String]
    let relatedTopicIds: [String]
    let configExample: String?

    enum TopicCategory: String, Codable, CaseIterable {
        case zia = "ZIA"
        case zpa = "ZPA"
        case zdx = "ZDX"
        case architecture = "アーキテクチャ"
        case bestPractice = "ベストプラクティス"

        var icon: String {
            switch self {
            case .zia: return "shield.fill"
            case .zpa: return "lock.fill"
            case .zdx: return "waveform.path.ecg"
            case .architecture: return "building.2.fill"
            case .bestPractice: return "star.fill"
            }
        }
    }
}

struct KnowledgeBase: Codable {
    let version: String
    let topics: [KnowledgeTopic]
}
