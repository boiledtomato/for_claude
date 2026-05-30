import Foundation

enum AnthropicError: LocalizedError {
    case noAPIKey
    case networkError(Error)
    case invalidResponse
    case rateLimited
    case apiError(String)

    var errorDescription: String? {
        switch self {
        case .noAPIKey:
            return "APIキーが設定されていません。「設定」タブで入力してください。"
        case .networkError(let e):
            return "ネットワークエラー: \(e.localizedDescription)"
        case .invalidResponse:
            return "APIレスポンスが不正です。しばらく後で再試行してください。"
        case .rateLimited:
            return "レート制限に達しました。しばらく待ってから再試行してください。"
        case .apiError(let msg):
            return "APIエラー: \(msg)"
        }
    }
}

@Observable
final class AnthropicService {
    static let shared = AnthropicService()

    private let endpoint = URL(string: "https://api.anthropic.com/v1/messages")!
    private let model = "claude-opus-4-8"
    private let apiVersion = "2023-06-01"

    private init() {}

    var apiKey: String {
        get { UserDefaults.standard.string(forKey: "anthropic_api_key") ?? "" }
        set { UserDefaults.standard.set(newValue, forKey: "anthropic_api_key") }
    }

    var hasAPIKey: Bool { !apiKey.trimmingCharacters(in: .whitespaces).isEmpty }

    func search(query: String, knowledgeContext: String) async throws -> String {
        guard hasAPIKey else { throw AnthropicError.noAPIKey }

        let system = """
        あなたはZscalerセキュリティプラットフォームの専門エキスパートです。\
        ZIA（Zscaler Internet Access）、ZPA（Zscaler Private Access）、\
        ZDX（Zscaler Digital Experience）について深い知識を持っています。

        以下のナレッジベースを参照して質問に回答してください：

        \(knowledgeContext)

        【回答ガイドライン】
        - 日本語で、専門的かつ分かりやすく回答する
        - 見出し（##）と箇条書きで構造化する
        - 具体的な設定手順・数値・コマンドを含める
        - 関連する他の機能や注意点も補足する
        - 実際の運用で役立つベストプラクティスを含める
        """

        let body: [String: Any] = [
            "model": model,
            "max_tokens": 4096,
            "system": system,
            "messages": [["role": "user", "content": query]]
        ]

        var request = URLRequest(url: endpoint)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.setValue(apiKey.trimmingCharacters(in: .whitespaces),
                         forHTTPHeaderField: "x-api-key")
        request.setValue(apiVersion, forHTTPHeaderField: "anthropic-version")
        request.httpBody = try JSONSerialization.data(withJSONObject: body)

        let (data, response): (Data, URLResponse)
        do {
            (data, response) = try await URLSession.shared.data(for: request)
        } catch {
            throw AnthropicError.networkError(error)
        }

        guard let http = response as? HTTPURLResponse else {
            throw AnthropicError.invalidResponse
        }

        switch http.statusCode {
        case 200:
            break
        case 429:
            throw AnthropicError.rateLimited
        default:
            let detail = (try? JSONDecoder().decode(APIErrorEnvelope.self, from: data))
                            .map { $0.error.message } ?? "HTTP \(http.statusCode)"
            throw AnthropicError.apiError(detail)
        }

        let decoded = try JSONDecoder().decode(MessagesResponse.self, from: data)
        return decoded.content.first?.text ?? ""
    }
}

// MARK: - Response types

private struct MessagesResponse: Decodable {
    let content: [ContentBlock]
    struct ContentBlock: Decodable {
        let type: String
        let text: String
    }
}

private struct APIErrorEnvelope: Decodable {
    let error: APIErrorDetail
    struct APIErrorDetail: Decodable {
        let message: String
        let type: String
    }
}
