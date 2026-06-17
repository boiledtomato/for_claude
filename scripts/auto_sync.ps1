# Zscaler Scraper Auto Sync
# このスクリプトをWindowsタスクスケジューラに登録すると自動同期されます

$repoUrl = "https://github.com/boiledtomato/for_claude.git"
$targetDir = "C:\Users\sugin\OneDrive\drive_folder\python\zscaler_scraper"
$logFile = "$targetDir\sync.log"

function Write-Log($message) {
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    "$timestamp $message" | Out-File -Append -FilePath $logFile
    Write-Host "$timestamp $message"
}

# フォルダが存在しない場合はclone
if (-not (Test-Path $targetDir)) {
    Write-Log "[INFO] フォルダが存在しないため clone します..."
    git clone $repoUrl $targetDir
    Write-Log "[OK] clone 完了"
    exit 0
}

# .git フォルダがない場合は初期化してclone
if (-not (Test-Path "$targetDir\.git")) {
    Write-Log "[INFO] Gitリポジトリではないため初期化します..."
    Remove-Item -Recurse -Force $targetDir
    git clone $repoUrl $targetDir
    Write-Log "[OK] clone 完了"
    exit 0
}

# 既存リポジトリの場合はpull
Set-Location $targetDir
Write-Log "[INFO] git pull 実行中..."
$result = git pull origin main 2>&1
Write-Log "[OK] $result"
