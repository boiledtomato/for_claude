/**
 * 家計簿アプリ（budget/index.html）用 Google Apps Script バックエンド
 * ------------------------------------------------------------------
 * Google スプレッドシートを保管場所にして、2 台以上のブラウザ間でデータを同期する。
 *
 * 設置手順は ../README.md を参照。要点だけ書くと:
 *   1. 新しいスプレッドシートを作り、拡張機能 → Apps Script でこのファイルを貼る
 *   2. 下の SHARED_TOKEN を自分たちだけの合言葉に書き換える
 *   3. デプロイ → 新しいデプロイ → 種類「ウェブアプリ」
 *        次のユーザーとして実行: 自分
 *        アクセスできるユーザー: 全員
 *   4. 表示された /exec の URL と合言葉をアプリの「設定」タブに入れる
 *
 * ※「アクセスできるユーザー: 全員」なので、URL と合言葉の両方を知っている人は
 *   読み書きできる。URL はリポジトリにも SNS にも貼らないこと。
 */

/** 夫婦で共有する合言葉。必ず書き換えること。 */
var SHARED_TOKEN = 'CHANGE_ME';

/** 空ならこのスクリプトが紐づくスプレッドシートを使う。別のブックを使うなら ID を入れる。 */
var SPREADSHEET_ID = '';

/** 各シートの列定義。順番がそのまま列順になる。 */
var TABLES = {
  entries: {
    sheet: '支出',
    columns: ['id', 'date', 'categoryId', 'categoryName', 'amount', 'memo', 'updatedAt', 'deleted'],
    numeric: ['amount'],
    text: ['id', 'date', 'categoryId', 'categoryName', 'memo', 'updatedAt']
  },
  categories: {
    sheet: 'カテゴリ',
    columns: ['id', 'name', 'color', 'order', 'updatedAt', 'deleted'],
    numeric: ['color', 'order'],
    text: ['id', 'name', 'updatedAt']
  }
};

/* ------------------------------------------------------------------ */
/* エントリポイント                                                      */
/* ------------------------------------------------------------------ */

function doGet() {
  // ブラウザで URL を開いたときの疎通確認用。データは返さない。
  return jsonOut({ ok: true, service: 'kakeibo', message: 'このURLをアプリの設定に貼ってください。' });
}

function doPost(e) {
  try {
    var body = (e && e.postData && e.postData.contents) || '{}';
    var req = JSON.parse(body);

    if (String(req.token || '') !== String(SHARED_TOKEN)) {
      return jsonOut({ ok: false, error: 'auth', message: '合言葉が違います。' });
    }
    if (String(SHARED_TOKEN) === 'CHANGE_ME') {
      return jsonOut({ ok: false, error: 'setup', message: 'SHARED_TOKEN が初期値のままです。Code.gs を編集してください。' });
    }

    switch (req.action) {
      case 'ping':
        return jsonOut({ ok: true, action: 'ping', spreadsheet: book().getName(), time: nowIso() });
      case 'sync':
        return jsonOut(handleSync(req));
      case 'replaceAll':
        return jsonOut(handleReplaceAll(req));
      default:
        return jsonOut({ ok: false, error: 'unknown_action', message: '不明な action: ' + req.action });
    }
  } catch (err) {
    return jsonOut({ ok: false, error: 'exception', message: String(err && err.stack || err) });
  }
}

/* ------------------------------------------------------------------ */
/* アクション                                                           */
/* ------------------------------------------------------------------ */

/**
 * クライアントの未送信分だけを受け取り、行単位で updatedAt の新しい方を採用する
 * （last-write-wins）。マージ後に全件を返し、クライアントはそれで丸ごと置き換える。
 */
function handleSync(req) {
  var lock = LockService.getScriptLock();
  if (!lock.tryLock(25000)) {
    return { ok: false, error: 'busy', message: '他の端末が書き込み中です。少し待って再試行してください。' };
  }
  try {
    var categories = mergeTable('categories', req.categories || []);
    var entries = mergeTable('entries', req.entries || []);

    // 支出行の categoryName を最新のカテゴリ名で塗り直す（シートを人が読むための列）。
    var nameById = {};
    categories.forEach(function (c) { nameById[c.id] = c.name; });
    var renamed = [];
    entries.forEach(function (row) {
      var want = nameById[row.categoryId] || '';
      if (row.categoryName !== want) {
        row.categoryName = want;
        renamed.push(row);
      }
    });
    if (renamed.length) writeTable('entries', entries);

    return { ok: true, action: 'sync', serverTime: nowIso(), categories: categories, entries: entries };
  } finally {
    lock.releaseLock();
  }
}

/** 取り込み（インポート）用。シートの内容を送られてきたデータで完全に置き換える。 */
function handleReplaceAll(req) {
  var lock = LockService.getScriptLock();
  if (!lock.tryLock(25000)) {
    return { ok: false, error: 'busy', message: '他の端末が書き込み中です。少し待って再試行してください。' };
  }
  try {
    var categories = (req.categories || []).map(function (r) { return normalizeRow('categories', r); });
    var nameById = {};
    categories.forEach(function (c) { nameById[c.id] = c.name; });

    var entries = (req.entries || []).map(function (r) {
      var row = normalizeRow('entries', r);
      row.categoryName = nameById[row.categoryId] || '';
      return row;
    });

    writeTable('categories', categories);
    writeTable('entries', entries);
    return { ok: true, action: 'replaceAll', serverTime: nowIso(), categories: categories, entries: entries };
  } finally {
    lock.releaseLock();
  }
}

/* ------------------------------------------------------------------ */
/* シート操作                                                           */
/* ------------------------------------------------------------------ */

function book() {
  return SPREADSHEET_ID
    ? SpreadsheetApp.openById(SPREADSHEET_ID)
    : SpreadsheetApp.getActiveSpreadsheet();
}

function sheetFor(table) {
  var def = TABLES[table];
  var ss = book();
  var sh = ss.getSheetByName(def.sheet);
  if (!sh) {
    sh = ss.insertSheet(def.sheet);
  }
  // ヘッダ行を常に定義どおりに揃える
  var header = sh.getRange(1, 1, 1, def.columns.length).getValues()[0];
  var needsHeader = def.columns.some(function (c, i) { return String(header[i] || '') !== c; });
  if (needsHeader) {
    sh.getRange(1, 1, 1, def.columns.length).setValues([def.columns]).setFontWeight('bold');
    sh.setFrozenRows(1);
  }
  // 日付や ISO 文字列を Date に勝手に変換されると往復で壊れるので、文字列列は書式を「@」に固定する
  def.text.forEach(function (name) {
    var i = def.columns.indexOf(name);
    if (i >= 0) sh.getRange(2, i + 1, Math.max(sh.getMaxRows() - 1, 1), 1).setNumberFormat('@');
  });
  return sh;
}

function readTable(table) {
  var def = TABLES[table];
  var sh = sheetFor(table);
  var last = sh.getLastRow();
  if (last < 2) return [];
  var values = sh.getRange(2, 1, last - 1, def.columns.length).getValues();
  var rows = [];
  for (var i = 0; i < values.length; i++) {
    var raw = {};
    for (var c = 0; c < def.columns.length; c++) raw[def.columns[c]] = values[i][c];
    if (!String(raw.id || '').trim()) continue;   // 空行は無視
    rows.push(normalizeRow(table, raw));
  }
  return rows;
}

function writeTable(table, rows) {
  var def = TABLES[table];
  var sh = sheetFor(table);
  var last = sh.getLastRow();
  if (last > 1) sh.getRange(2, 1, last - 1, def.columns.length).clearContent();
  if (!rows.length) return;

  var values = rows.map(function (row) {
    return def.columns.map(function (c) {
      var v = row[c];
      if (c === 'deleted') return v ? true : false;
      if (def.numeric.indexOf(c) >= 0) return Number(v) || 0;
      return v === null || v === undefined ? '' : String(v);
    });
  });
  sh.getRange(2, 1, values.length, def.columns.length).setValues(values);
}

/** 受信行をシート上の行と突き合わせ、updatedAt の新しい方を残す。 */
function mergeTable(table, incoming) {
  var existing = readTable(table);
  var byId = {};
  existing.forEach(function (row) { byId[row.id] = row; });

  var changed = false;
  incoming.forEach(function (raw) {
    var row = normalizeRow(table, raw);
    if (!row.id) return;
    var cur = byId[row.id];
    if (!cur) {
      byId[row.id] = row;
      existing.push(row);
      changed = true;
    } else if (String(row.updatedAt) > String(cur.updatedAt)) {
      // 同じ参照を書き換えて existing 側にも反映させる
      Object.keys(row).forEach(function (k) { cur[k] = row[k]; });
      changed = true;
    }
  });

  if (changed) {
    existing.sort(function (a, b) {
      if (table === 'entries') return String(a.date) < String(b.date) ? 1 : (String(a.date) > String(b.date) ? -1 : 0);
      return (Number(a.order) || 0) - (Number(b.order) || 0);
    });
    writeTable(table, existing);
  }
  return existing;
}

/* ------------------------------------------------------------------ */
/* 値の正規化                                                           */
/* ------------------------------------------------------------------ */

function normalizeRow(table, raw) {
  var def = TABLES[table];
  var row = {};
  def.columns.forEach(function (c) {
    var v = raw[c];
    if (c === 'deleted') {
      row[c] = (v === true || v === 'true' || v === 'TRUE' || v === 1);
    } else if (def.numeric.indexOf(c) >= 0) {
      row[c] = Number(v) || 0;
    } else if (c === 'date') {
      row[c] = toDateString(v);
    } else {
      row[c] = (v === null || v === undefined) ? '' : String(v);
    }
  });
  if (!row.updatedAt) row.updatedAt = nowIso();
  return row;
}

/** Date に変換されてしまった値も含めて YYYY-MM-DD に揃える。 */
function toDateString(v) {
  if (v instanceof Date) {
    return Utilities.formatDate(v, Session.getScriptTimeZone() || 'Asia/Tokyo', 'yyyy-MM-dd');
  }
  var s = String(v === null || v === undefined ? '' : v).trim();
  var m = s.match(/^(\d{4})[-/](\d{1,2})[-/](\d{1,2})/);
  if (!m) return s;
  return m[1] + '-' + pad2(m[2]) + '-' + pad2(m[3]);
}

function pad2(n) { return ('0' + n).slice(-2); }

function nowIso() { return new Date().toISOString(); }

function jsonOut(obj) {
  return ContentService
    .createTextOutput(JSON.stringify(obj))
    .setMimeType(ContentService.MimeType.JSON);
}
