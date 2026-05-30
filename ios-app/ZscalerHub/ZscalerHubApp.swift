import SwiftUI
import SwiftData

@main
struct ZscalerHubApp: App {
    var body: some Scene {
        WindowGroup {
            MainTabView()
                .preferredColorScheme(.dark)
        }
        .modelContainer(for: [SearchLogEntry.self, SimulationLogEntry.self])
    }
}
