#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
from pathlib import Path
import xml.etree.ElementTree as ET

# -----------------------------
# Pfade
# -----------------------------
BASE_DIR = Path(__file__).parent
INPUT_FILE = BASE_DIR / "Data/pcexp_table.xml"
OUTPUT_FILE = BASE_DIR / "player_experience_table.xml"

# -----------------------------
# Hilfsfunktionen
# -----------------------------
def require_file(path):
    if not path.is_file():
        print(f"[ERROR] Datei nicht gefunden: {path}")
        sys.exit(1)

def read_client_exp_table(filename):
    """Liest Client EXP pro Level ein"""
    tree = ET.parse(filename)
    root = tree.getroot()

    exp_per_level = []

    for lvl in root.findall("level"):
        exp = int(lvl.findtext("exp", "0"))
        exp_per_level.append(exp)

    return exp_per_level

def build_player_experience_table(exp_per_level):
    """Erstellt das XML mit nur <exp>Wert</exp> pro Level"""
    root = ET.Element(
        "player_experience_table",
        {
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xsi:noNamespaceSchemaLocation": "player_experience_table.xsd",
        },
    )

    # Level 1 immer auf 0
    ET.SubElement(root, "exp").text = "0"

    # Alle Levels aus Client übernehmen (Level 2+)
    for exp in exp_per_level:
        ET.SubElement(root, "exp").text = str(exp)

    return root

def write_output(root, filename):
    tree = ET.ElementTree(root)

    # Pretty Print (Python 3.9+)
    ET.indent(tree, space="    ", level=0)

    tree.write(
        filename,
        encoding="UTF-8",
        xml_declaration=True,
    )

def main():
    require_file(INPUT_FILE)

    exp_per_level = read_client_exp_table(INPUT_FILE)
    root = build_player_experience_table(exp_per_level)
    write_output(root, OUTPUT_FILE)
    print(f"[OK] {OUTPUT_FILE.name} formatiert erstellt.")

if __name__ == "__main__":
    main()
