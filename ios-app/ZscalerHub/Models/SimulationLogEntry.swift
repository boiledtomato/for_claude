import Foundation
import SwiftData

@Model
final class SimulationLogEntry {
    var id: UUID
    var scenarioTitle: String
    var product: String
    var scenarioType: String
    var inputDescription: String
    var resultSummary: String
    var resultAction: String
    var timestamp: Date
    var configSnapshot: String

    init(
        scenarioTitle: String,
        product: String,
        scenarioType: String,
        inputDescription: String,
        resultSummary: String,
        resultAction: String,
        configSnapshot: String = ""
    ) {
        self.id = UUID()
        self.scenarioTitle = scenarioTitle
        self.product = product
        self.scenarioType = scenarioType
        self.inputDescription = inputDescription
        self.resultSummary = resultSummary
        self.resultAction = resultAction
        self.timestamp = Date()
        self.configSnapshot = configSnapshot
    }
}
