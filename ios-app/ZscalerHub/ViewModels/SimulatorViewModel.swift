import Foundation
import SwiftData

@Observable
final class SimulatorViewModel {
    var selectedProduct = "ZIA"
    var scenarioTitle = "新規シミュレーション"

    // ZIA
    var ziaInput = ZIAURLSimulationInput()
    var ziaResult: SimulationResult? = nil

    // ZPA
    var zpaInput = ZPASimulationInput()
    var zpaResult: ZPASimulationResult? = nil

    var showingResult = false

    func runZIA(modelContext: ModelContext) {
        ziaResult = SimulationEngine.simulateURLAccess(ziaInput)
        showingResult = true

        let entry = SimulationLogEntry(
            scenarioTitle: scenarioTitle,
            product: "ZIA",
            scenarioType: "URLアクセス検証",
            inputDescription: "URL: \(ziaInput.url)  ユーザー: \(ziaInput.userGroup.rawValue)  場所: \(ziaInput.location.rawValue)",
            resultSummary: ziaResult?.summary ?? "",
            resultAction: ziaResult?.finalAction.rawValue ?? ""
        )
        modelContext.insert(entry)
        try? modelContext.save()
    }

    func runZPA(modelContext: ModelContext) {
        zpaResult = SimulationEngine.simulateAppAccess(zpaInput)
        showingResult = true

        let entry = SimulationLogEntry(
            scenarioTitle: scenarioTitle,
            product: "ZPA",
            scenarioType: "アプリアクセス検証",
            inputDescription: "アプリ: \(zpaInput.applicationFQDN)  ユーザー: \(zpaInput.userGroup.rawValue)  デバイス: \(zpaInput.devicePosture.rawValue)",
            resultSummary: zpaResult?.summary ?? "",
            resultAction: zpaResult?.finalAction.rawValue ?? ""
        )
        modelContext.insert(entry)
        try? modelContext.save()
    }

    func reset() {
        ziaInput = ZIAURLSimulationInput()
        zpaInput = ZPASimulationInput()
        ziaResult = nil
        zpaResult = nil
        scenarioTitle = "新規シミュレーション"
    }
}
