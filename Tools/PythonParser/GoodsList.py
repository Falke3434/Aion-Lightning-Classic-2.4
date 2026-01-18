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
# Load client_items.xml (name -> id), case-insensitive
# -------------------------------------------------
def load_items(path):
    try:
        tree = ET.parse(path)
    except ET.ParseError as e:
        print(f"[ERROR] XML-Fehler in {path}")
        print(f"        {e}")
        sys.exit(1)

    root = tree.getroot()
    name_to_id = {}

    for item in root.findall("client_item"):
        item_id = item.findtext("id")
        name = item.findtext("name")

        if item_id and name:
            name_to_id[name.strip().lower()] = item_id.strip()  # <-- Kleinbuchstaben

    return name_to_id

# -------------------------------------------------
# Main
# -------------------------------------------------
def main():
    require_file(CLIENT_GOODSLIST)
    require_file(CLIENT_ITEMS)

    item_map = load_items(CLIENT_ITEMS)

    try:
        tree = ET.parse(CLIENT_GOODSLIST)
    except ET.ParseError as e:
        print(f"[ERROR] XML-Fehler in {CLIENT_GOODSLIST}")
        print(f"        {e}")
        sys.exit(1)

    root = tree.getroot()

    out_root = ET.Element(
        "goodslists",
        {
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xsi:schemaLocation": "goodslists.xsd",
        },
    )

    for goods in root.findall("client_npc_goodslist"):
        list_id = goods.findtext("id")
        if not list_id:
            continue

        list_el = ET.SubElement(out_root, "list", {"id": list_id})

        # -------------------------------
        # Optional salestime auf List-Ebene über Tabelle
        # -------------------------------
        salestime_table = goods.findtext("salestime_table_name")
        if salestime_table:
            st_value = SALESTIME_MAP.get(salestime_table.strip())
            if st_value:
                st_el = ET.SubElement(list_el, "salestime")
                st_el.text = st_value
            else:
                print(f"[WARN] Unbekannte salestime_table_name '{salestime_table.strip()}' für Liste {list_id}")

        # -------------------------------
        # Items
        # -------------------------------
        goods_list = goods.find("goods_list")
        if goods_list is None:
            continue

        for data in goods_list.findall("data"):
            item_name = data.findtext("item")
            if not item_name:
                continue
            item_name = item_name.strip()

            item_id = item_map.get(item_name.lower())  # <-- case-insensitive lookup
            if not item_id:
                # Warnung für unbekannte Items
                print(f"[WARN] Item '{item_name}' in Liste {list_id} nicht in client_items.xml gefunden")
                continue

            attrs = {"id": item_id}

            sell_limit = data.findtext("sell_limit")
            buy_limit = data.findtext("buy_limit")

            if sell_limit is not None:
                attrs["sell_limit"] = sell_limit.strip()
            if buy_limit is not None:
                attrs["buy_limit"] = buy_limit.strip()

            ET.SubElement(list_el, "item", attrs)

    with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
        f.write(pretty_xml(out_root))

    print("✔ goodslist.xml erfolgreich erstellt")

# -------------------------------------------------
if __name__ == "__main__":
    main()
