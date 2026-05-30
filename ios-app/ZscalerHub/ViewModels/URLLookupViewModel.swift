import Foundation

@Observable
final class URLLookupViewModel {
    var fqdn: String = ""
    var result: LookupResult? = nil
    var errorMessage: String? = nil
    var isLooking = false

    func runLookup() {
        let trimmed = fqdn.trimmingCharacters(in: .whitespaces)
        guard !trimmed.isEmpty else { return }

        guard URLLookupData.isValidFQDN(trimmed) else {
            errorMessage = "有効なFQDN形式で入力してください\n例: www.example.com"
            result = nil
            return
        }

        errorMessage = nil
        isLooking = true
        result = nil

        DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) { [weak self] in
            guard let self else { return }
            self.result = URLLookupData.lookup(fqdn: trimmed)
            self.isLooking = false
        }
    }

    func applySample(_ domain: String) {
        fqdn = domain
        runLookup()
    }

    func reset() {
        fqdn = ""
        result = nil
        errorMessage = nil
    }
}
