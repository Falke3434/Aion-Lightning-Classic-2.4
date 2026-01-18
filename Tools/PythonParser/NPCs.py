#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import os
import xml.etree.ElementTree as ET
from xml.dom import minidom
from pathlib import Path

# -------------------------------------------------
# Arbeitsverzeichnis auf Script-Ordner setzen
# -------------------------------------------------
BASE_DIR = Path(__file__).parent
os.chdir(BASE_DIR)

CLIENT_NPCS = BASE_DIR / "Data/client_npcs.xml"
CLIENT_ITEMS = BASE_DIR / "Data/client_items.xml"
CLIENT_STRINGS = BASE_DIR / "Data/client_strings_npc.xml"
OLD_TEMPLATE = BASE_DIR / "Data/npc_templates_old.xml"  # Optional für Übernahme bestimmter Werte
OUTPUT = BASE_DIR / "npc_templates.xml"

# -------------------------------------------------
# Hilfsfunktionen
# -------------------------------------------------
def require_file(path):
    if not path.is_file():
        print(f"[ERROR] Datei nicht gefunden: {path}")
        sys.exit(1)

def t(elem, tag):
    """Textinhalt eines Untertags"""
    child = elem.find(tag)
    return child.text.strip() if child is not None and child.text else None

def parse_int(val, default=0):
    try:
        return int(float(val))
    except:
        return default

def parse_float(val, default=0.0):
    try:
        return float(val)
    except:
        return default

def pretty_xml(element):
    rough = ET.tostring(element, "utf-8")
    reparsed = minidom.parseString(rough)
    return reparsed.toprettyxml(indent="    ", encoding="utf-8").decode("utf-8")

# -------------------------------------------------
# Load client items
# -------------------------------------------------
require_file(CLIENT_ITEMS)
items_tree = ET.parse(CLIENT_ITEMS)
items_root = items_tree.getroot()
item_name_to_id = {t(it,"name").lower(): t(it,"id") for it in items_root.findall("client_item") if t(it,"name") and t(it,"id")}

# -------------------------------------------------
# Load client strings
# -------------------------------------------------
require_file(CLIENT_STRINGS)
strings_tree = ET.parse(CLIENT_STRINGS)
strings_root = strings_tree.getroot()
desc_to_nameid = {t(s,"name"): t(s,"id") for s in strings_root.findall("string") if t(s,"name") and t(s,"id")}

# -------------------------------------------------
# Optional alte NPC Templates laden (für Übernahme bestimmter Werte)
# -------------------------------------------------
old_values = {}
if OLD_TEMPLATE.is_file():
    old_tree = ET.parse(OLD_TEMPLATE)
    old_root = old_tree.getroot()
    for npc in old_root.findall("npc_template"):
        npc_id = npc.get("npc_id")
        if npc_id:
            old_values[npc_id] = {
                "level": npc.get("level"),
                "maxHp": npc.find("stats").get("maxHp") if npc.find("stats") is not None else None,
                "maxXp": npc.find("stats").get("maxXp") if npc.find("stats") is not None else None,
            }

# -------------------------------------------------
# Attribute Mapping und Prozentwert-Handling
# -------------------------------------------------
ATTR_MAP = {
    "AttackDelay": "adelay",
    "attackDelay": "adelay",
    "main_hand_attack": "main_hand_attack",
    "main_hand_accuracy": "main_hand_accuracy",
    "pdef": "pdef",
    "mresist": "mresist",
    "power": "power",
    "evasion": "evasion",
    "accuracy": "accuracy",
}

NEGATIVE_ALLOWED = {"adelay"}  # AttackDelay darf negativ sein

# -------------------------------------------------
# Load client NPCs
# -------------------------------------------------
require_file(CLIENT_NPCS)
client_tree = ET.parse(CLIENT_NPCS)
client_root = client_tree.getroot()

root_out = ET.Element("npc_templates")

for c in client_root.findall("npc_client"):
    npc_id = t(c, "id")
    if not npc_id:
        continue

    # ---------------- NPC Template Attribute ----------------
    race_val = (t(c, "race_type") or "NONE").upper()

    # ---------------- LOGISCHE HEIGHT UMRECHNUNG ----------------
    scale_val = parse_float(t(c,"scale"), 100.0)
    height_val = scale_val / 100  # Client-Scale 200 -> height 2.0

    npc_attrs = {
        "npc_id": npc_id,
        "level": old_values.get(npc_id, {}).get("level", t(c,"level") or "1"),
        "name": t(c, "name") or "None",
        "name_id": desc_to_nameid.get(t(c,"desc") or "", "0"),
        "npc_type": "NON_ATTACKABLE",
        "height": str(height_val),
        "rank": t(c,"rank") or "DISCIPLINED",
        "rating": t(c,"rating") or "JUNK",
        "race": race_val,
        "tribe": (t(c,"tribe") or "GENERAL").upper(),
        "type": t(c,"type") or "GENERAL",
        "abyss_type": t(c,"abyss_type") or "",
        "ai": (t(c,"ai_name") or "dummy").lower(),
        "srange": str(parse_int(t(c,"sensory_range"),0)),
        "arange": str(parse_int(t(c,"attack_range"),0)),
        "adelay": str(parse_int(t(c,"attack_delay"),2000)),
        "arate": str(parse_int(t(c,"attack_rate"),0)),
        "hpgauge": str(parse_int(t(c,"hpgauge_level"),1)),
    }
    npc_el = ET.SubElement(root_out, "npc_template", npc_attrs)

    # ---------------- Stats ----------------
    stats_attrs = {
        "maxHp": old_values.get(npc_id, {}).get("maxHp", str(parse_int(t(c,"maxHp"),0))),
        "maxMp": str(parse_int(t(c,"maxMp"),0)),
        "main_hand_attack": str(parse_int(t(c,"main_hand_attack"),0)),
        "main_hand_accuracy": str(parse_int(t(c,"main_hand_accuracy"),0)),
        "pdef": str(parse_int(t(c,"pdef"),0)),
        "mresist": str(parse_int(t(c,"mresist"),0)),
        "power": str(parse_int(t(c,"power"),0)),
        "evasion": str(parse_int(t(c,"evasion"),0)),
        "accuracy": str(parse_int(t(c,"accuracy"),0)),
    }
    stats_el = ET.SubElement(npc_el, "stats", stats_attrs)

    # ---------------- Geschwindigkeiten (walk/run/run_fight) ----------------
    walk = parse_float(t(c,"move_speed_normal_walk"))
    run = parse_float(t(c,"move_speed_normal_run"))
    run_fight = parse_float(t(c,"move_speed_combat_run"))
    if walk > 0 or run > 0 or run_fight > 0:
        ET.SubElement(stats_el, "speeds", {
            "walk_speed": str(walk),
            "run_speed": str(run),
            "run_speed_fight": str(run_fight)
        })

    # ---------------- Equipment ----------------
    ve = c.find("visible_equipments")
    warned_items = set()
    if ve is not None:
        eq_el = ET.SubElement(npc_el, "equipment")
        for part in ve:
            if part.text and part.text.strip():
                item_name = part.text.strip().lower()
                item_id = item_name_to_id.get(item_name)
                if item_id:
                    ET.SubElement(eq_el, "item").text = item_id
                else:
                    if item_name not in warned_items:
                        print(f"[WARN] Item '{part.text.strip()}' nicht in client_items.xml gefunden")
                        warned_items.add(item_name)

    # ---------------- Bound Radius ----------------
    br = c.find("bound_radius")
    if br is not None:
        br_el = ET.SubElement(npc_el, "bound_radius")
        for axis in ["front","side","upper"]:
            val = t(br, axis)
            if val:
                br_el.set(axis, str(parse_float(val)))

# ---------------- Pretty XML ----------------
xml_out = pretty_xml(root_out)

# ---------------- Speichern ----------------
with open(OUTPUT, "w", encoding="utf-8") as f:
    f.write(xml_out)

print(f"[OK] {OUTPUT.name} wurde korrekt erstellt")
