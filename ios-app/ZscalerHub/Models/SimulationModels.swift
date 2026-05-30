import Foundation

// MARK: - ZIA Input Models

struct ZIAURLSimulationInput {
    var url: String = ""
    var userGroup: UserGroup = .general
    var location: LocationType = .hq
    var time: Date = Date()
    var policies: [URLPolicy] = URLPolicy.defaults
    var sslBypassHosts: [String] = ["zoom.us", "teams.microsoft.com", "meet.google.com"]
}

enum UserGroup: String, CaseIterable, Codable {
    case itAdmin = "IT管理者"
    case general = "一般ユーザー"
    case executive = "役員"
    case contractor = "外部委託"
    case guest = "ゲスト"
}

enum LocationType: String, CaseIterable, Codable {
    case hq = "本社"
    case branch = "支社"
    case remote = "リモート"
    case mobile = "モバイル"
}

struct URLPolicy: Identifiable {
    let id = UUID()
    var name: String
    var order: Int
    var conditions: [PolicyCondition]
    var action: PolicyAction
    var enabled: Bool = true

    static var defaults: [URLPolicy] {[
        URLPolicy(name: "IT管理者バイパス", order: 1,
                  conditions: [.userGroup(.itAdmin)], action: .allow),
        URLPolicy(name: "マルウェア・フィッシングブロック", order: 2,
                  conditions: [.category("Malware"), .category("Phishing")], action: .block),
        URLPolicy(name: "成人向けコンテンツブロック", order: 3,
                  conditions: [.category("Adult Content")], action: .block),
        URLPolicy(name: "ギャンブルブロック", order: 4,
                  conditions: [.category("Gambling")], action: .block),
        URLPolicy(name: "SNSブロック（業務時間中）", order: 5,
                  conditions: [.category("Social Media"), .timeRange("09:00", "18:00"), .weekday],
                  action: .block),
        URLPolicy(name: "動画ストリーミング 注意", order: 6,
                  conditions: [.category("Streaming Media")], action: .caution),
        URLPolicy(name: "P2P ブロック", order: 7,
                  conditions: [.category("Peer-to-Peer")], action: .block),
        URLPolicy(name: "デフォルト許可", order: 99,
                  conditions: [], action: .allow),
    ]}
}

enum PolicyCondition {
    case userGroup(UserGroup)
    case category(String)
    case timeRange(String, String)
    case weekday
    case location(LocationType)
    case urlPattern(String)

    var description: String {
        switch self {
        case .userGroup(let g):         return "ユーザーグループ = \(g.rawValue)"
        case .category(let c):          return "URLカテゴリ = \(c)"
        case .timeRange(let s, let e):  return "時間帯 \(s)〜\(e)"
        case .weekday:                  return "平日のみ"
        case .location(let l):          return "ロケーション = \(l.rawValue)"
        case .urlPattern(let p):        return "URLパターン: \(p)"
        }
    }
}

enum PolicyAction: String {
    case allow   = "許可"
    case block   = "ブロック"
    case caution = "注意"
    case isolate = "分離"
    case decrypt = "SSLインスペクション"

    var icon: String {
        switch self {
        case .allow:   return "checkmark.circle.fill"
        case .block:   return "xmark.circle.fill"
        case .caution: return "exclamationmark.triangle.fill"
        case .isolate: return "shield.fill"
        case .decrypt: return "lock.open.fill"
        }
    }
}

// MARK: - ZPA Input Models

struct ZPASimulationInput {
    var userEmail: String = ""
    var userGroup: UserGroup = .general
    var applicationFQDN: String = ""
    var idpProvider: String = "Azure AD"
    var devicePosture: DevicePosture = .managed
    var accessPolicies: [ZPAAccessPolicy] = ZPAAccessPolicy.defaults
}

enum DevicePosture: String, CaseIterable, Codable {
    case managed   = "管理デバイス"
    case unmanaged = "非管理デバイス"
    case byod      = "BYOD"
}

struct ZPAAccessPolicy: Identifiable {
    let id = UUID()
    var name: String
    var order: Int
    var appSegmentPattern: String
    var conditions: [ZPAPolicyCondition]
    var action: ZPAAction
    var enabled: Bool = true

    static var defaults: [ZPAAccessPolicy] {[
        ZPAAccessPolicy(name: "IT管理者 全アプリ許可", order: 1,
                        appSegmentPattern: "*",
                        conditions: [.userGroup(.itAdmin)], action: .allow),
        ZPAAccessPolicy(name: "一般ユーザー 業務アプリ（管理デバイスのみ）", order: 2,
                        appSegmentPattern: "business",
                        conditions: [.userGroup(.general), .devicePosture(.managed)], action: .allow),
        ZPAAccessPolicy(name: "外部委託 限定アプリ", order: 3,
                        appSegmentPattern: "contractor",
                        conditions: [.userGroup(.contractor)], action: .allow),
        ZPAAccessPolicy(name: "役員 全アプリ許可", order: 4,
                        appSegmentPattern: "*",
                        conditions: [.userGroup(.executive)], action: .allow),
        ZPAAccessPolicy(name: "デフォルト拒否", order: 99,
                        appSegmentPattern: "*",
                        conditions: [], action: .deny),
    ]}
}

enum ZPAPolicyCondition {
    case userGroup(UserGroup)
    case idpGroup(String)
    case devicePosture(DevicePosture)
    case scheduleTime(String, String)

    var description: String {
        switch self {
        case .userGroup(let g):        return "ユーザーグループ = \(g.rawValue)"
        case .idpGroup(let g):         return "IdPグループ = \(g)"
        case .devicePosture(let d):    return "デバイスポスチャー = \(d.rawValue)"
        case .scheduleTime(let s, let e): return "時間帯 \(s)〜\(e)"
        }
    }
}

enum ZPAAction: String {
    case allow = "アクセス許可"
    case deny  = "アクセス拒否"
}

// MARK: - Simulation Results

struct SimulationResult {
    let finalAction: PolicyAction
    let matchedRuleName: String
    let matchedRuleOrder: Int
    let evaluationSteps: [EvaluationStep]
    let urlCategory: String
    let riskScore: Int
    let sslInspected: Bool
    let additionalInfo: [(key: String, value: String)]

    var summary: String {
        "ルール「\(matchedRuleName)」に一致 → \(finalAction.rawValue)"
    }
}

struct ZPASimulationResult {
    let finalAction: ZPAAction
    let matchedRuleName: String
    let evaluationSteps: [EvaluationStep]
    let appSegmentMatched: String?
    let connectorGroup: String?

    var summary: String {
        "ルール「\(matchedRuleName)」→ \(finalAction.rawValue)"
    }
}

struct EvaluationStep: Identifiable {
    let id = UUID()
    let stepNumber: Int
    let ruleName: String
    let conditionMet: Bool
    let reason: String
    let resolvedAction: String?
}
