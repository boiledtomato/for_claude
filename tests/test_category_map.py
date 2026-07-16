"""
config/category_map.yaml のスキーマ・制約を検証するテスト

実行方法:
    python -m unittest tests.test_category_map -v
"""

from __future__ import annotations

import unittest
from pathlib import Path

import yaml

CATEGORY_MAP_PATH = Path(__file__).resolve().parent.parent / "config" / "category_map.yaml"
MAX_CATEGORIES = 100


def _load_categories() -> dict:
    with CATEGORY_MAP_PATH.open(encoding="utf-8") as f:
        data = yaml.safe_load(f) or {}
    return data.get("categories", {})


class TestCategoryMap(unittest.TestCase):
    def setUp(self) -> None:
        self.categories = _load_categories()

    def test_file_exists(self) -> None:
        self.assertTrue(CATEGORY_MAP_PATH.exists(), f"{CATEGORY_MAP_PATH} が見つかりません")

    def test_has_at_least_one_category(self) -> None:
        self.assertGreater(len(self.categories), 0, "categories が空です")

    def test_bucket_count_within_limit(self) -> None:
        count = len(self.categories)
        self.assertLessEqual(
            count, MAX_CATEGORIES,
            f"カテゴリ(バケツ)数が上限({MAX_CATEGORIES})を超えています: {count}",
        )

    def test_each_category_has_required_keys(self) -> None:
        required_keys = {"display_name", "url_prefixes", "google_doc_id"}
        for name, cfg in self.categories.items():
            with self.subTest(category=name):
                self.assertIsInstance(cfg, dict, f"{name} の値がdictではありません")
                missing = required_keys - cfg.keys()
                self.assertFalse(missing, f"{name} に必須キーが不足しています: {missing}")

    def test_display_name_is_non_empty_string(self) -> None:
        for name, cfg in self.categories.items():
            with self.subTest(category=name):
                display_name = cfg.get("display_name")
                self.assertIsInstance(display_name, str)
                self.assertTrue(display_name.strip(), f"{name} の display_name が空です")

    def test_url_prefixes_is_list_of_strings(self) -> None:
        for name, cfg in self.categories.items():
            with self.subTest(category=name):
                prefixes = cfg.get("url_prefixes")
                self.assertIsInstance(prefixes, list, f"{name} の url_prefixes がlistではありません")
                for prefix in prefixes:
                    self.assertIsInstance(prefix, str)
                    self.assertFalse(prefix.startswith("/"), f"{name}: prefixは先頭に'/'を含めない — {prefix!r}")
                    self.assertFalse(prefix.endswith("/"), f"{name}: prefixは末尾に'/'を含めない — {prefix!r}")

    def test_url_prefixes_are_unique_across_categories(self) -> None:
        """同じprefixが複数カテゴリに重複定義されていないか確認する。

        scraper.resolve_category_urls() は最初にマッチしたカテゴリを採用するため、
        重複定義があってもエラーにはならないが、意図しない分類ミスにつながるため
        テストで検出する。
        """
        seen: dict[str, str] = {}
        duplicates: list[str] = []
        for name, cfg in self.categories.items():
            for prefix in cfg.get("url_prefixes") or []:
                if prefix in seen:
                    duplicates.append(f"{prefix!r} は {seen[prefix]} と {name} の両方に定義されています")
                else:
                    seen[prefix] = name
        self.assertFalse(duplicates, "\n".join(duplicates))

    def test_other_fallback_category_exists(self) -> None:
        self.assertIn("other", self.categories, "フォールバック用の 'other' カテゴリが必要です")
        self.assertEqual(
            self.categories["other"].get("url_prefixes"), [],
            "'other' は url_prefixes を空のままにし、フォールバック専用として扱う",
        )

    def test_category_names_are_valid_identifiers(self) -> None:
        for name in self.categories:
            with self.subTest(category=name):
                self.assertRegex(
                    name, r"^[a-z][a-z0-9_]*$",
                    f"カテゴリ名は小文字英数字とアンダースコアのみ: {name!r}",
                )


if __name__ == "__main__":
    unittest.main()
