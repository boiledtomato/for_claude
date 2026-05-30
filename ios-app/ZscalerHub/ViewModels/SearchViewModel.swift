import Foundation
import SwiftData

@Observable
final class SearchViewModel {
    var query = ""
    var response = ""
    var isLoading = false
    var errorMessage: String? = nil
    var recentQueries: [String] = []

    private let anthropic = AnthropicService.shared
    private let kb = KnowledgeBaseService.shared

    var canSearch: Bool { !query.trimmingCharacters(in: .whitespaces).isEmpty }

    @MainActor
    func search(modelContext: ModelContext) async {
        let trimmed = query.trimmingCharacters(in: .whitespaces)
        guard !trimmed.isEmpty else { return }

        isLoading = true
        errorMessage = nil
        response = ""

        let context = kb.buildContext(for: trimmed)
        let sourceTitles = kb.search(query: trimmed).prefix(3).map(\.title)

        do {
            let result = try await anthropic.search(query: trimmed, knowledgeContext: context)
            response = result

            let entry = SearchLogEntry(
                query: trimmed,
                response: result,
                sources: Array(sourceTitles)
            )
            modelContext.insert(entry)
            try? modelContext.save()

            if !recentQueries.contains(trimmed) {
                recentQueries.insert(trimmed, at: 0)
                recentQueries = Array(recentQueries.prefix(10))
            }
        } catch {
            errorMessage = error.localizedDescription
        }

        isLoading = false
    }

    func clear() {
        query = ""
        response = ""
        errorMessage = nil
    }
}
