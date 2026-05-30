import Foundation

enum SimulationEngine {

    // MARK: - URL Category Database

    private static let urlCategories: [String: String] = [
        // Social Media
        "facebook.com": "Social Media", "instagram.com": "Social Media",
        "twitter.com": "Social Media", "x.com": "Social Media",
        "tiktok.com": "Social Media", "linkedin.com": "Social Media",
        "line.me": "Social Media",
        // Streaming
        "youtube.com": "Streaming Media", "netflix.com": "Streaming Media",
        "abematv.com": "Streaming Media", "hulu.com": "Streaming Media",
        "twitch.tv": "Streaming Media",
        // Cloud Storage
        "dropbox.com": "Cloud Storage", "box.com": "Cloud Storage",
        "drive.google.com": "Cloud Storage", "onedrive.live.com": "Cloud Storage",
        // Business Apps
        "salesforce.com": "Business Applications", "servicenow.com": "Business Applications",
        "workday.com": "Business Applications", "concur.com": "Business Applications",
        // Webmail
        "gmail.com": "Webmail", "outlook.com": "Webmail", "yahoo.co.jp": "Webmail",
        // Conferencing
        "zoom.us": "Web Conferencing", "teams.microsoft.com": "Web Conferencing",
        "meet.google.com": "Web Conferencing", "webex.com": "Web Conferencing",
        // Collaboration
        "slack.com": "Collaboration", "notion.so": "Collaboration",
        // Dev tools
        "github.com": "Developer Tools", "gitlab.com": "Developer Tools",
        "stackoverflow.com": "Developer Tools",
        // Search
        "google.com": "Search Engines", "bing.com": "Search Engines",
        // News
        "nikkei.com": "News and Media", "bbc.com": "News and Media",
        // Malicious (test)
        "malware-test.example": "Malware", "phishing.example": "Phishing",
        // Adult / Gambling
        "adult-content.example": "Adult Content", "casino.example": "Gambling",
        // P2P
        "torrent.example": "Peer-to-Peer",
        // Reference
        "wikipedia.org": "Reference",
        // Cloud infra
        "amazonaws.com": "Cloud Infrastructure", "azure.microsoft.com": "Cloud Infrastructure",
    ]

    // Default SSL bypass hosts (would normally bypass inspection)
    private static let defaultSSLBypass = [
        "zoom.us", "teams.microsoft.com", "meet.google.com",
        "amazonaws.com", "azure.microsoft.com",
    ]

    private static let riskByCategory: [String: Int] = [
        "Malware": 5, "Phishing": 5,
        "Adult Content": 4, "Gambling": 4, "Peer-to-Peer": 4,
        "Social Media": 3, "Streaming Media": 3,
        "Cloud Storage": 2, "Webmail": 2,
        "Business Applications": 1, "Web Conferencing": 1, "Collaboration": 1,
    ]

    // MARK: - ZIA URL Simulation

    static func simulateURLAccess(_ input: ZIAURLSimulationInput) -> SimulationResult {
        let host = extractHost(from: input.url)
        let category = resolveCategory(host: host)
        let riskScore = riskByCategory[category] ?? 1
        let sslBypassed = input.sslBypassHosts.contains(host) || defaultSSLBypass.contains(host)

        var steps: [EvaluationStep] = []
        let orderedPolicies = input.policies.filter(\.enabled).sorted { $0.order < $1.order }

        for policy in orderedPolicies {
            let stepNum = steps.count + 1

            // Empty conditions = default rule, matches everything
            if policy.conditions.isEmpty {
                steps.append(EvaluationStep(
                    stepNumber: stepNum, ruleName: policy.name,
                    conditionMet: true, reason: "デフォルトルール（全トラフィックに適用）",
                    resolvedAction: policy.action.rawValue
                ))
                return buildZIAResult(
                    action: policy.action, ruleName: policy.name, order: policy.order,
                    steps: steps, category: category, riskScore: riskScore,
                    sslBypassed: sslBypassed, host: host
                )
            }

            let (matches, reasons) = evaluateConditions(
                policy.conditions,
                userGroup: input.userGroup,
                location: input.location,
                category: category,
                url: input.url,
                time: input.time
            )
            let allMatch = matches.allSatisfy { $0 }
            let reasonText = zip(policy.conditions, zip(matches, reasons))
                .map { (_, pair) in "\(pair.0 ? "✓" : "✗") \(pair.1)" }
                .joined(separator: "  ")

            steps.append(EvaluationStep(
                stepNumber: stepNum, ruleName: policy.name,
                conditionMet: allMatch, reason: reasonText,
                resolvedAction: allMatch ? policy.action.rawValue : nil
            ))

            if allMatch {
                return buildZIAResult(
                    action: policy.action, ruleName: policy.name, order: policy.order,
                    steps: steps, category: category, riskScore: riskScore,
                    sslBypassed: sslBypassed, host: host
                )
            }
        }

        return buildZIAResult(
            action: .allow, ruleName: "システムデフォルト許可", order: 999,
            steps: steps, category: category, riskScore: riskScore,
            sslBypassed: sslBypassed, host: host
        )
    }

    // MARK: - ZPA Simulation

    static func simulateAppAccess(_ input: ZPASimulationInput) -> ZPASimulationResult {
        var steps: [EvaluationStep] = []
        let orderedPolicies = input.accessPolicies.filter(\.enabled).sorted { $0.order < $1.order }

        for policy in orderedPolicies {
            let stepNum = steps.count + 1

            // App segment match check
            let fqdnMatchesSegment = policy.appSegmentPattern == "*" ||
                input.applicationFQDN.contains(policy.appSegmentPattern)

            if policy.conditions.isEmpty {
                steps.append(EvaluationStep(
                    stepNumber: stepNum, ruleName: policy.name,
                    conditionMet: true, reason: "デフォルトルール",
                    resolvedAction: policy.action.rawValue
                ))
                return ZPASimulationResult(
                    finalAction: policy.action, matchedRuleName: policy.name,
                    evaluationSteps: steps,
                    appSegmentMatched: fqdnMatchesSegment ? policy.appSegmentPattern : nil,
                    connectorGroup: policy.action == .allow ? "Primary-Connector-Group" : nil
                )
            }

            let (matches, reasons) = evaluateZPAConditions(
                policy.conditions,
                userGroup: input.userGroup,
                devicePosture: input.devicePosture
            )
            let allMatch = matches.allSatisfy { $0 } && fqdnMatchesSegment
            let reasonText = zip(policy.conditions, zip(matches, reasons))
                .map { (_, pair) in "\(pair.0 ? "✓" : "✗") \(pair.1)" }
                .joined(separator: "  ")
            let segmentNote = fqdnMatchesSegment ? "" : "  ✗ アプリセグメント不一致"

            steps.append(EvaluationStep(
                stepNumber: stepNum, ruleName: policy.name,
                conditionMet: allMatch, reason: reasonText + segmentNote,
                resolvedAction: allMatch ? policy.action.rawValue : nil
            ))

            if allMatch {
                return ZPASimulationResult(
                    finalAction: policy.action, matchedRuleName: policy.name,
                    evaluationSteps: steps,
                    appSegmentMatched: policy.appSegmentPattern,
                    connectorGroup: policy.action == .allow ? "Primary-Connector-Group" : nil
                )
            }
        }

        return ZPASimulationResult(
            finalAction: .deny, matchedRuleName: "デフォルト拒否",
            evaluationSteps: steps, appSegmentMatched: nil, connectorGroup: nil
        )
    }

    // MARK: - Private helpers

    private static func extractHost(from url: String) -> String {
        var s = url.lowercased()
            .replacingOccurrences(of: "https://", with: "")
            .replacingOccurrences(of: "http://", with: "")
        if let i = s.firstIndex(of: "/") { s = String(s[..<i]) }
        if s.hasPrefix("www.") { s = String(s.dropFirst(4)) }
        return s
    }

    private static func resolveCategory(host: String) -> String {
        if let cat = urlCategories[host] { return cat }
        // Heuristic fallback
        if host.contains("bank") || host.contains("finance") { return "Financial Services" }
        if host.contains("news")  { return "News and Media" }
        if host.contains("shop") || host.contains("store") { return "Shopping" }
        if host.contains("game") { return "Gaming" }
        if host.contains("gov")  { return "Government" }
        return "General Browsing"
    }

    private static func evaluateConditions(
        _ conditions: [PolicyCondition],
        userGroup: UserGroup, location: LocationType,
        category: String, url: String, time: Date
    ) -> ([Bool], [String]) {
        conditions.map { cond -> (Bool, String) in
            switch cond {
            case .userGroup(let g):
                return (userGroup == g, cond.description)
            case .category(let c):
                return (category == c, "\(cond.description)（実際: \(category)）")
            case .timeRange(let s, let e):
                let inRange = isTimeInRange(time, start: s, end: e)
                return (inRange, "\(cond.description)（現在: \(timeString(time))）")
            case .weekday:
                let wday = Calendar.current.component(.weekday, from: time)
                let isWday = (2...6).contains(wday)
                return (isWday, "\(cond.description)（現在: \(isWday ? "平日" : "休日")）")
            case .location(let l):
                return (location == l, cond.description)
            case .urlPattern(let p):
                return (url.contains(p), cond.description)
            }
        }.reduce(into: ([Bool](), [String]())) { acc, pair in
            acc.0.append(pair.0); acc.1.append(pair.1)
        }
    }

    private static func evaluateZPAConditions(
        _ conditions: [ZPAPolicyCondition],
        userGroup: UserGroup, devicePosture: DevicePosture
    ) -> ([Bool], [String]) {
        conditions.map { cond -> (Bool, String) in
            switch cond {
            case .userGroup(let g):     return (userGroup == g, cond.description)
            case .idpGroup:             return (true, cond.description + "（確認済み）")
            case .devicePosture(let d): return (devicePosture == d, cond.description)
            case .scheduleTime(let s, let e):
                return (isTimeInRange(Date(), start: s, end: e), cond.description)
            }
        }.reduce(into: ([Bool](), [String]())) { acc, pair in
            acc.0.append(pair.0); acc.1.append(pair.1)
        }
    }

    private static func isTimeInRange(_ date: Date, start: String, end: String) -> Bool {
        let c = Calendar.current.dateComponents([.hour, .minute], from: date)
        let current = (c.hour ?? 0) * 60 + (c.minute ?? 0)
        func parse(_ s: String) -> Int {
            let p = s.split(separator: ":").compactMap { Int($0) }
            return (p.first ?? 0) * 60 + (p.last ?? 0)
        }
        return current >= parse(start) && current <= parse(end)
    }

    private static func timeString(_ date: Date) -> String {
        let c = Calendar.current.dateComponents([.hour, .minute], from: date)
        return String(format: "%02d:%02d", c.hour ?? 0, c.minute ?? 0)
    }

    private static func buildZIAResult(
        action: PolicyAction, ruleName: String, order: Int,
        steps: [EvaluationStep], category: String, riskScore: Int,
        sslBypassed: Bool, host: String
    ) -> SimulationResult {
        let info: [(key: String, value: String)] = [
            ("URLカテゴリ",        category),
            ("リスクスコア",       "\(riskScore) / 5"),
            ("SSLインスペクション", sslBypassed ? "バイパス（除外リスト）" : "実施"),
            ("DLPスキャン",        action == .allow ? "実施（問題なし）" : "非実施"),
            ("クラウドプロキシ",   "Tokyo ZEN 経由"),
            ("ログ記録",           "全トランザクション記録"),
        ]
        return SimulationResult(
            finalAction: action, matchedRuleName: ruleName, matchedRuleOrder: order,
            evaluationSteps: steps, urlCategory: category, riskScore: riskScore,
            sslInspected: !sslBypassed, additionalInfo: info
        )
    }
}
