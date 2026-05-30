import Foundation
import SwiftData

@Model
final class SearchLogEntry {
    var id: UUID
    var query: String
    var response: String
    var sources: [String]
    var timestamp: Date
    var isBookmarked: Bool

    init(query: String, response: String, sources: [String] = []) {
        self.id = UUID()
        self.query = query
        self.response = response
        self.sources = sources
        self.timestamp = Date()
        self.isBookmarked = false
    }
}
