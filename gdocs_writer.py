"""
Google Docs / Drive への書き込み

カテゴリごとに1つのGoogleドキュメントを作成 or 上書き更新する。
サービスアカウント認証を使用し、認証情報は環境変数
(GOOGLE_SERVICE_ACCOUNT_JSON など)経由で渡す。リポジトリには含めない。

NotebookLM側はこのドキュメント群が置かれたGoogleドライブフォルダを
自動同期ソースとして参照する想定のため、本モジュールは
「Driveへの書き込みまで」を担当する。

TODO(次のステップで実装):
    - サービスアカウント認証 (google-auth) の初期化
    - upsert_document(): category_map.yaml の google_doc_id が空なら新規作成し
      作成後にIDを category_map.yaml へ書き戻す。既にIDがあれば本文を上書き更新
    - 対象Googleドライブフォルダへの配置 (Drive API files.update parents)

使い方 (実装後):
    pip install google-api-python-client google-auth google-auth-httplib2
    環境変数:
      GOOGLE_SERVICE_ACCOUNT_JSON  サービスアカウントの認証情報(JSON文字列)
      GDRIVE_FOLDER_ID             ドキュメントを配置するDriveフォルダID
"""

from __future__ import annotations


def get_credentials():
    """環境変数からサービスアカウント認証情報を読み込む。"""
    raise NotImplementedError


def upsert_document(category: str, display_name: str, content: str) -> str:
    """カテゴリ用Googleドキュメントを作成 or 更新し、doc_idを返す。"""
    raise NotImplementedError
