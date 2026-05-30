import SwiftUI
import SwiftData

struct SimulatorView: View {
    @Environment(\.modelContext) private var modelContext
    @State private var vm = SimulatorViewModel()

    private var canRun: Bool {
        vm.selectedProduct == "ZIA"
            ? !vm.ziaInput.url.trimmingCharacters(in: .whitespaces).isEmpty
            : !vm.zpaInput.applicationFQDN.trimmingCharacters(in: .whitespaces).isEmpty
    }

    var body: some View {
        NavigationStack {
            Form {
                productSection
                if vm.selectedProduct == "ZIA" { ziaSection } else { zpaSection }
                scenarioNameSection
                runSection
            }
            .navigationTitle("設定シミュレーター")
            .sheet(isPresented: $vm.showingResult) { resultSheet }
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button("リセット") { vm.reset() }
                }
            }
        }
    }

    // MARK: - Sections

    private var productSection: some View {
        Section("製品選択") {
            Picker("製品", selection: $vm.selectedProduct) {
                Text("ZIA（インターネットアクセス）").tag("ZIA")
                Text("ZPA（プライベートアクセス）").tag("ZPA")
            }
            .pickerStyle(.segmented)
            .onChange(of: vm.selectedProduct) { _, _ in
                vm.ziaResult = nil; vm.zpaResult = nil
            }
        }
    }

    private var ziaSection: some View {
        Group {
            Section {
                LabeledContent("アクセスURL") {
                    TextField("https://example.com", text: $vm.ziaInput.url)
                        .multilineTextAlignment(.trailing)
                        .keyboardType(.URL)
                        .autocorrectionDisabled()
                        .textInputAutocapitalization(.never)
                }
                Picker("ユーザーグループ", selection: $vm.ziaInput.userGroup) {
                    ForEach(UserGroup.allCases, id: \.self) { Text($0.rawValue).tag($0) }
                }
                Picker("ロケーション", selection: $vm.ziaInput.location) {
                    ForEach(LocationType.allCases, id: \.self) { Text($0.rawValue).tag($0) }
                }
                DatePicker("アクセス時刻", selection: $vm.ziaInput.time,
                           displayedComponents: .hourAndMinute)
            } header: { Text("アクセス情報") }

            Section {
                NavigationLink {
                    PolicyEditorView(policies: $vm.ziaInput.policies)
                } label: {
                    HStack {
                        Text("URLポリシーを編集")
                        Spacer()
                        Text("\(vm.ziaInput.policies.filter(\.enabled).count)件有効")
                            .foregroundStyle(.secondary)
                    }
                }
            } header: { Text("ポリシー設定") }
              footer: { Text("ルール番号順（小さい番号が高優先）に評価されます。") }
        }
    }

    private var zpaSection: some View {
        Section {
            LabeledContent("ユーザーメール") {
                TextField("user@company.com", text: $vm.zpaInput.userEmail)
                    .multilineTextAlignment(.trailing)
                    .keyboardType(.emailAddress)
                    .autocorrectionDisabled()
                    .textInputAutocapitalization(.never)
            }
            LabeledContent("接続先 FQDN") {
                TextField("app.internal.company.com", text: $vm.zpaInput.applicationFQDN)
                    .multilineTextAlignment(.trailing)
                    .autocorrectionDisabled()
                    .textInputAutocapitalization(.never)
            }
            Picker("ユーザーグループ", selection: $vm.zpaInput.userGroup) {
                ForEach(UserGroup.allCases, id: \.self) { Text($0.rawValue).tag($0) }
            }
            Picker("デバイスポスチャー", selection: $vm.zpaInput.devicePosture) {
                ForEach(DevicePosture.allCases, id: \.self) { Text($0.rawValue).tag($0) }
            }
            LabeledContent("IdPプロバイダー") {
                TextField("Azure AD", text: $vm.zpaInput.idpProvider)
                    .multilineTextAlignment(.trailing)
            }
        } header: { Text("アクセス情報") }
    }

    private var scenarioNameSection: some View {
        Section("シナリオ名") {
            TextField("シナリオ名を入力", text: $vm.scenarioTitle)
        }
    }

    private var runSection: some View {
        Section {
            Button {
                if vm.selectedProduct == "ZIA" {
                    vm.runZIA(modelContext: modelContext)
                } else {
                    vm.runZPA(modelContext: modelContext)
                }
            } label: {
                Label("シミュレーション実行", systemImage: "play.fill")
                    .frame(maxWidth: .infinity)
                    .font(.headline)
            }
            .disabled(!canRun)
        }
    }

    @ViewBuilder
    private var resultSheet: some View {
        if vm.selectedProduct == "ZIA", let r = vm.ziaResult {
            ZIAResultView(result: r, input: vm.ziaInput)
        } else if let r = vm.zpaResult {
            ZPAResultView(result: r, input: vm.zpaInput)
        }
    }
}

// MARK: - Policy Editor

struct PolicyEditorView: View {
    @Binding var policies: [URLPolicy]

    var body: some View {
        List {
            ForEach(policies.indices, id: \.self) { i in
                PolicyRow(policy: $policies[i])
            }
        }
        .navigationTitle("URLポリシー")
        .navigationBarTitleDisplayMode(.inline)
    }
}

struct PolicyRow: View {
    @Binding var policy: URLPolicy

    var actionColor: Color {
        switch policy.action {
        case .allow: return .green
        case .block: return .red
        case .caution: return .orange
        case .isolate: return .purple
        case .decrypt: return .blue
        }
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack {
                Text("#\(policy.order)")
                    .font(.caption).foregroundStyle(.secondary).frame(width: 28)
                Text(policy.name).font(.headline)
                Spacer()
                Toggle("", isOn: $policy.enabled).labelsHidden()
            }
            ForEach(policy.conditions.indices, id: \.self) { i in
                Text(policy.conditions[i].description)
                    .font(.caption).foregroundStyle(.secondary)
            }
            HStack(spacing: 4) {
                Image(systemName: policy.action.icon)
                Text(policy.action.rawValue)
            }
            .font(.caption.bold())
            .padding(.horizontal, 8).padding(.vertical, 3)
            .background(actionColor.opacity(0.15))
            .foregroundStyle(actionColor)
            .clipShape(Capsule())
        }
        .padding(.vertical, 4)
        .opacity(policy.enabled ? 1 : 0.4)
    }
}

// MARK: - ZIA Result

struct ZIAResultView: View {
    let result: SimulationResult
    let input: ZIAURLSimulationInput
    @Environment(\.dismiss) private var dismiss

    private var color: Color {
        switch result.finalAction {
        case .allow: return .green
        case .block: return .red
        case .caution: return .orange
        case .isolate: return .purple
        case .decrypt: return .blue
        }
    }

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 20) {
                    verdictCard
                    infoCard
                    stepsCard
                }
                .padding()
            }
            .navigationTitle("ZIA シミュレーション結果")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("閉じる") { dismiss() }
                }
            }
        }
    }

    private var verdictCard: some View {
        VStack(spacing: 10) {
            Image(systemName: result.finalAction.icon)
                .font(.system(size: 64))
                .foregroundStyle(color)
            Text(result.finalAction.rawValue)
                .font(.largeTitle.bold())
                .foregroundStyle(color)
            Text(result.matchedRuleName)
                .font(.subheadline)
                .foregroundStyle(.secondary)
        }
        .frame(maxWidth: .infinity)
        .padding(24)
        .background(color.opacity(0.1))
        .clipShape(RoundedRectangle(cornerRadius: 16))
    }

    private var infoCard: some View {
        VStack(alignment: .leading, spacing: 10) {
            ResultSectionHeader(title: "詳細情報", icon: "info.circle")
            ForEach(result.additionalInfo, id: \.key) { item in
                ResultInfoRow(key: item.key, value: item.value)
            }
            ResultInfoRow(key: "リスクスコア", value: riskStars(result.riskScore))
        }
        .padding()
        .background(Color(.secondarySystemBackground))
        .clipShape(RoundedRectangle(cornerRadius: 14))
    }

    private var stepsCard: some View {
        VStack(alignment: .leading, spacing: 10) {
            ResultSectionHeader(title: "ポリシー評価ステップ", icon: "list.number")
            ForEach(result.evaluationSteps) { step in
                EvalStepRow(step: step)
            }
        }
        .padding()
        .background(Color(.secondarySystemBackground))
        .clipShape(RoundedRectangle(cornerRadius: 14))
    }

    private func riskStars(_ score: Int) -> String {
        String(repeating: "★", count: score) + String(repeating: "☆", count: 5 - score)
    }
}

// MARK: - ZPA Result

struct ZPAResultView: View {
    let result: ZPASimulationResult
    let input: ZPASimulationInput
    @Environment(\.dismiss) private var dismiss

    private var color: Color { result.finalAction == .allow ? .green : .red }
    private var icon: String { result.finalAction == .allow ? "checkmark.circle.fill" : "xmark.circle.fill" }

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 20) {
                    VStack(spacing: 10) {
                        Image(systemName: icon).font(.system(size: 64)).foregroundStyle(color)
                        Text(result.finalAction.rawValue).font(.largeTitle.bold()).foregroundStyle(color)
                        Text(result.matchedRuleName).font(.subheadline).foregroundStyle(.secondary)
                    }
                    .frame(maxWidth: .infinity).padding(24)
                    .background(color.opacity(0.1))
                    .clipShape(RoundedRectangle(cornerRadius: 16))

                    if result.finalAction == .allow {
                        VStack(alignment: .leading, spacing: 10) {
                            ResultSectionHeader(title: "接続情報", icon: "network")
                            if let seg = result.appSegmentMatched {
                                ResultInfoRow(key: "アプリセグメント", value: seg)
                            }
                            if let grp = result.connectorGroup {
                                ResultInfoRow(key: "コネクターグループ", value: grp)
                            }
                            ResultInfoRow(key: "暗号化", value: "TLS 1.3（相互認証）")
                            ResultInfoRow(key: "認証", value: "IdP: \(input.idpProvider)")
                            ResultInfoRow(key: "デバイス", value: input.devicePosture.rawValue)
                        }
                        .padding()
                        .background(Color(.secondarySystemBackground))
                        .clipShape(RoundedRectangle(cornerRadius: 14))
                    }

                    VStack(alignment: .leading, spacing: 10) {
                        ResultSectionHeader(title: "ポリシー評価ステップ", icon: "list.number")
                        ForEach(result.evaluationSteps) { step in EvalStepRow(step: step) }
                    }
                    .padding()
                    .background(Color(.secondarySystemBackground))
                    .clipShape(RoundedRectangle(cornerRadius: 14))
                }
                .padding()
            }
            .navigationTitle("ZPA シミュレーション結果")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("閉じる") { dismiss() }
                }
            }
        }
    }
}

// MARK: - Shared components

struct ResultSectionHeader: View {
    let title: String; let icon: String
    var body: some View {
        Label(title, systemImage: icon).font(.headline)
    }
}

struct ResultInfoRow: View {
    let key: String; let value: String
    var body: some View {
        HStack {
            Text(key).foregroundStyle(.secondary)
            Spacer()
            Text(value).fontWeight(.medium)
        }
    }
}

struct EvalStepRow: View {
    let step: EvaluationStep

    var body: some View {
        HStack(alignment: .top, spacing: 10) {
            ZStack {
                Circle()
                    .fill(step.conditionMet ? Color.green.opacity(0.2) : Color.secondary.opacity(0.1))
                    .frame(width: 28, height: 28)
                Text("\(step.stepNumber)")
                    .font(.caption.bold())
                    .foregroundStyle(step.conditionMet ? .green : .secondary)
            }
            VStack(alignment: .leading, spacing: 4) {
                HStack {
                    Text(step.ruleName).font(.subheadline.bold())
                    Spacer()
                    if step.conditionMet {
                        Image(systemName: "checkmark.circle.fill").foregroundStyle(.green)
                    }
                }
                Text(step.reason).font(.caption).foregroundStyle(.secondary)
                if let action = step.resolvedAction, step.conditionMet {
                    Text("→ \(action)").font(.caption.bold())
                }
            }
        }
        .padding(8)
        .background(step.conditionMet ? Color.green.opacity(0.05) : Color.clear)
        .clipShape(RoundedRectangle(cornerRadius: 8))
    }
}
