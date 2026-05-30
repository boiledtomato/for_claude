import SwiftUI

struct SettingsView: View {
    @State private var apiKey = UserDefaults.standard.string(forKey: "anthropic_api_key") ?? ""
    @State private var showKey = false
    @State private var savedToast = false

    var body: some View {
        NavigationStack {
            Form {
                apiKeySection
                modelSection
                appInfoSection
                aboutSection
            }
            .navigationTitle("設定")
            .overlay(alignment: .bottom) {
                if savedToast {
                    toastView
                        .transition(.move(edge: .bottom).combined(with: .opacity))
                        .padding(.bottom, 20)
                }
            }
            .animation(.spring(), value: savedToast)
        }
    }

    // MARK: - Sections

    private var apiKeySection: some View {
        Section {
            HStack {
                if showKey {
                    TextField("sk-ant-api03-...", text: $apiKey)
                        .autocorrectionDisabled()
                        .textInputAutocapitalization(.never)
                        .font(.system(.footnote, design: .monospaced))
                } else {
                    SecureField("sk-ant-api03-...", text: $apiKey)
                }
                Spacer()
                Button {
                    showKey.toggle()
                } label: {
                    Image(systemName: showKey ? "eye.slash" : "eye")
                        .foregroundStyle(.secondary)
                }
            }

            Button("保存する") {
                UserDefaults.standard.set(apiKey, forKey: "anthropic_api_key")
                AnthropicService.shared.apiKey = apiKey
                showSaved()
            }
            .disabled(apiKey.trimmingCharacters(in: .whitespaces).isEmpty)
        } header: {
            Label("Anthropic API キー", systemImage: "key.fill")
        } footer: {
            VStack(alignment: .leading, spacing: 4) {
                Text("Anthropic Console（console.anthropic.com）でAPIキーを取得してください。")
                Text("キーはデバイスのUserDefaultsに保存されます。本番環境ではKeychain管理を推奨します。")
            }
        }
    }

    private var modelSection: some View {
        Section("AIモデル") {
            LabeledContent("使用モデル", value: "claude-opus-4-8")
            LabeledContent("最大トークン", value: "4,096")
            LabeledContent("言語", value: "日本語")
        }
    }

    private var appInfoSection: some View {
        Section("アプリ情報") {
            ResultInfoRow(key: "バージョン", value: "1.0.0")
            ResultInfoRow(key: "対応製品", value: "ZIA / ZPA / ZDX")
            ResultInfoRow(key: "最小iOS", value: "iOS 17.0")
            ResultInfoRow(key: "データ保存", value: "SwiftData（ローカル）")
        }
    }

    private var aboutSection: some View {
        Section("このアプリについて") {
            Text("""
            Zscaler Hub は、Zscaler公式ドキュメントを統合したナレッジアプリです。\
            AI検索機能によってZscaler製品（ZIA・ZPA・ZDX）に関する質問に日本語で回答し、\
            設定シミュレーターによって実際の環境を構築することなく机上での動作検証を行えます。\
            すべての検索・シミュレーション結果はログとして記録され、後から参照できます。
            """)
            .font(.subheadline)
            .foregroundStyle(.secondary)
        }
    }

    // MARK: - Toast

    private var toastView: some View {
        Label("APIキーを保存しました", systemImage: "checkmark.circle.fill")
            .font(.subheadline.bold())
            .padding(.horizontal, 16)
            .padding(.vertical, 10)
            .background(.regularMaterial)
            .clipShape(Capsule())
    }

    private func showSaved() {
        savedToast = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            savedToast = false
        }
    }
}
