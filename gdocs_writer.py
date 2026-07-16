"""
Google Docs / Drive への書き込み

カテゴリごとに1つのGoogleドキュメントを作成 or 上書き更新する。
サービスアカウント認証を使用し、認証情報は環境変数
GOOGLE_SERVICE_ACCOUNT_JSON (JSON文字列そのもの)経由で渡す。
リポジトリには含めない。

NotebookLM側はこのドキュメント群が置かれたGoogleドライブフォルダを
自動同期ソースとして参照する想定のため、本モジュールは
「Driveへの書き込みまで」を担当する。

使い方:
    pip install google-api-python-client google-auth google-auth-httplib2
    環境変数:
      GOOGLE_SERVICE_ACCOUNT_JSON  サービスアカウントの認証情報(JSON文字列)
      GDRIVE_FOLDER_ID             ドキュメントを配置するDriveフォルダID（任意）
"""

from __future__ import annotations

import json
import os

from google.oauth2 import service_account
from googleapiclient.discovery import build
from googleapiclient.errors import HttpError

SCOPES = [
    "https://www.googleapis.com/auth/documents",
    "https://www.googleapis.com/auth/drive",
]

SERVICE_ACCOUNT_ENV = "GOOGLE_SERVICE_ACCOUNT_JSON"
DRIVE_FOLDER_ENV = "GDRIVE_FOLDER_ID"

# NotebookLMドキュメントのタイトルでカテゴリを判別しやすくするための接頭辞
DOC_TITLE_PREFIX = "[Zscaler Help]"

_credentials = None
_docs_service = None
_drive_service = None


def get_credentials():
    """環境変数からサービスアカウント認証情報を読み込む。"""
    global _credentials
    if _credentials is not None:
        return _credentials

    raw = os.environ.get(SERVICE_ACCOUNT_ENV)
    if not raw:
        raise RuntimeError(
            f"環境変数 {SERVICE_ACCOUNT_ENV} が設定されていません"
            "（サービスアカウントのJSON認証情報を渡してください）"
        )
    info = json.loads(raw)
    _credentials = service_account.Credentials.from_service_account_info(info, scopes=SCOPES)
    return _credentials


def _get_docs_service():
    global _docs_service
    if _docs_service is None:
        _docs_service = build("docs", "v1", credentials=get_credentials(), cache_discovery=False)
    return _docs_service


def _get_drive_service():
    global _drive_service
    if _drive_service is None:
        _drive_service = build("drive", "v3", credentials=get_credentials(), cache_discovery=False)
    return _drive_service


def _doc_title(display_name: str) -> str:
    return f"{DOC_TITLE_PREFIX} {display_name}"


def _replace_content(doc_id: str, content: str) -> None:
    """既存ドキュメントの本文を content で丸ごと置き換える。"""
    docs = _get_docs_service()
    existing = docs.documents().get(documentId=doc_id).execute()
    end_index = existing["body"]["content"][-1]["endIndex"]

    requests = []
    if end_index > 2:
        # インデックス1は文書先頭の暗黙の改行なので残し、それ以降を全削除
        requests.append({
            "deleteContentRange": {"range": {"startIndex": 1, "endIndex": end_index - 1}}
        })
    if content:
        requests.append({"insertText": {"location": {"index": 1}, "text": content}})

    if requests:
        docs.documents().batchUpdate(documentId=doc_id, body={"requests": requests}).execute()


def _move_to_folder(doc_id: str) -> None:
    """GDRIVE_FOLDER_ID が指定されていれば、そのフォルダ配下に移動する。"""
    folder_id = os.environ.get(DRIVE_FOLDER_ENV)
    if not folder_id:
        return
    drive = _get_drive_service()
    file = drive.files().get(fileId=doc_id, fields="parents").execute()
    previous_parents = ",".join(file.get("parents", []))
    drive.files().update(
        fileId=doc_id,
        addParents=folder_id,
        removeParents=previous_parents,
        fields="id, parents",
    ).execute()


def _create_document(display_name: str, content: str) -> str:
    docs = _get_docs_service()
    doc = docs.documents().create(body={"title": _doc_title(display_name)}).execute()
    doc_id = doc["documentId"]
    _replace_content(doc_id, content)
    _move_to_folder(doc_id)
    return doc_id


def upsert_document(
    category: str,
    display_name: str,
    content: str,
    doc_id: str | None = None,
) -> str:
    """カテゴリ用Googleドキュメントを作成 or 更新し、doc_idを返す。

    doc_id が指定されていればその文書の本文を置き換える。ドキュメントが
    見つからない場合（削除済み等）は新規作成にフォールバックする。
    doc_id が未指定なら新規作成し、GDRIVE_FOLDER_ID があれば対象フォルダへ配置する。
    """
    if doc_id:
        try:
            _replace_content(doc_id, content)
            return doc_id
        except HttpError as e:
            if e.resp.status == 404:
                print(f"  [WARN] {category}: doc_id={doc_id} が見つからないため新規作成します")
            else:
                raise

    return _create_document(display_name, content)
