#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import os
import xml.etree.ElementTree as ET
from xml.dom import minidom

# -------------------------------------------------
# Arbeitsverzeichnis auf Script-Ordner setzen
# -------------------------------------------------
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
os.chdir(BASE_DIR)

CLIENT_GOODSLIST = "client_npc_goodslist.xml"
CLIENT_ITEMS = "client_items.xml"
CLIENT_TRADEIN = "client_npc_trade_in_list.xml"
OUTPUT_FILE = "goodslist.xml"

# -------------------------------------------------
# Mapping Salestime Tabellenname -> Cron-Zeit
# -------------------------------------------------
SALESTIME_MAP = {
    "Monday_worktime": "0 0 0 ? * MON",
    "Tuesday_worktime": "0 0 0 ? * TUE",
    "Wednesday_worktime": "0 0 0 ? * WED",
    "Thursday_worktime": "0 0 0 ? * THU",
    "Friday_worktime": "0 0 0 ? * FRI",
    "Wednesday_AM_8": "0 0 0,10,12,14,18,22 ? * *",
    "every_10_12_4_8_10_12": "0 0 0,10,12,14,18,22 ? * *",
    "every_10_12_2_6_10_12": "0 0 0,10,12,14,18,22 ? * *",
    "every_morning_8": "0 0 0 ? * *",
    "all_turn": "0 0 0 ? * *",
}

# -------------------------------------------------
# Pretty XML Output
# -------------------------------------------------
def pretty_xml(element):
    rough = ET.tostring(element, "utf-8")
    reparsed = minidom.parseString(rough)
    return reparsed.toprettyxml(indent="    ", encoding="utf-8").decode("utf-8")

# -------------------------------------------------
# File check helper
# -------------------------------------------------
def require_file(path):
    if not os.path.isfile(path):
        print(f"[ERROR] Datei nicht gefunden: {path}")
        sys.exit(1)

# -------------------------------------------------
# Load client_items.xml (case-insensitive)
# -------------------------------------------------
def load_items(path):
    tree = ET.parse(path)
    root = tree.getroot()
    mapping = {}

    for item in root.findall("client_item"):
        item_id = item.findtext("id")
        name = item.findtext("name")
        if item_id and name:
            mapping[name.strip().lower()] = item_id.strip()

    return mapping

# -------------------------------------------------
# Load trade-in lists
# -------------------------------------------------
def load_tradein(path, item_map):
    tree = ET.parse(path)
    root = tree.getroot()
    tradeins = []

    for trade in root.findall("client_npc_trade_in_list"):
        tid = trade.findtext("id")
        if not tid:
            continue

        ids = []
        goods = trade.find("goods_list")
        if goods is not None:
            for data in goods.findall("data"):
                name = data.findtext("item")
                if not name:
                    continue
                item_id = item_map.get(name.strip().lower())
                if item_id:
                    ids.append(item_id)
                else:
                    print(f"[WARN] Trade-In Item '{name}' nicht gefunden (in_list {tid})")

        tradeins.append((tid, ids))

    return tradeins

# -------------------------------------------------
# Main
# -------------------------------------------------
def main():
    require_file(CLIENT_GOODSLIST)
    require_file(CLIENT_ITEMS)
    require_file(CLIENT_TRADEIN)

    item_map = load_items(CLIENT_ITEMS)
    tradeins = load_tradein(CLIENT_TRADEIN, item_map)

    tree = ET.parse(CLIENT_GOODSLIST)
    root = tree.getroot()

    out_root = ET.Element(
        "goodslists",
        {
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xsi:schemaLocation": "goodslists.xsd",
        },
    )

    # -------------------------------
    # Normale Goods-Listen
    # -------------------------------
    for goods in root.findall("client_npc_goodslist"):
        list_id = goods.findtext("id")
        if not list_id:
            continue

        list_el = ET.SubElement(out_root, "list", {"id": list_id})

        st_table = goods.findtext("salestime_table_name")
        if st_table:
            st = SALESTIME_MAP.get(st_table.strip())
            if st:
                ET.SubElement(list_el, "salestime").text = st
            else:
                print(f"[WARN] Unbekannte salestime_table_name '{st_table}' für Liste {list_id}")

        goods_list = goods.find("goods_list")
        if goods_list is None:
            continue

        for data in goods_list.findall("data"):
            name = data.findtext("item")
            if not name:
                continue

            item_id = item_map.get(name.strip().lower())
            if not item_id:
                print(f"[WARN] Item '{name}' in Liste {list_id} nicht gefunden")
                continue

            attrs = {"id": item_id}

            sell = data.findtext("sell_limit")
            buy = data.findtext("buy_limit")

            if sell is not None:
                attrs["sell_limit"] = sell.strip()
            if buy is not None:
                attrs["buy_limit"] = buy.strip()

            ET.SubElement(list_el, "item", attrs)

    # -------------------------------
    # Trade-In Listen → in_list (ROOT-EBENE!)
    # -------------------------------
    for tid, items in tradeins:
        in_el = ET.SubElement(out_root, "in_list", {"id": tid})
        for item_id in items:
            ET.SubElement(in_el, "item", {"id": item_id})

    with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
        f.write(pretty_xml(out_root))

    print("✔ goodslist.xml erfolgreich erstellt")

# -------------------------------------------------
if __name__ == "__main__":
    main()
