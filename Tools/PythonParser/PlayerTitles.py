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

CLIENT_TITLES = "client_titles.xml"
CLIENT_STRINGS = "client_strings_quest.xml"
OUTPUT_FILE = "player_titles.xml"

# -------------------------------------------------
# Client -> Server Attribute Mapping
# -------------------------------------------------
ATTR_MAP = {
    "MaxHP": "MAXHP",
    "maxhp": "MAXHP",
    "maxHp": "MAXHP",
    "maxHP": "MAXHP",
    "HpRegen": "REGEN_HP",
    "Hpregen": "REGEN_HP",
    "maxMp": "MAXMP",
    "maxMP": "MAXMP",
    "MaxMp": "MAXMP",
    "MaxMP": "MAXMP",
    "Mpregen": "REGEN_MP",
    "maxFp": "FLY_TIME",
    "speed": "SPEED",
    "AttackDelay": "ATTACK_SPEED",
    "attackDelay": "ATTACK_SPEED",
    "PhyAttack": "PHYSICAL_ATTACK",
    "phyAttack": "PHYSICAL_ATTACK",
    "PhysicalDefend": "PHYSICAL_DEFENSE",
    "PhysicalAccuracy": "PHYSICAL_ACCURACY",
    "HitAccuracy": "PHYSICAL_ACCURACY",
    "hitAccuracy": "PHYSICAL_ACCURACY",
    "PhysicalCritical": "PHYSICAL_CRITICAL",
    "Critical": "PHYSICAL_CRITICAL",
    "critical": "PHYSICAL_CRITICAL",
    "MagicalCritical": "MAGICAL_CRITICAL",
    "MagicalHitAccuracy": "MAGICAL_ACCURACY",
    "MagicalResist": "MAGICAL_RESIST",
    "MagicalSkillBoost": "BOOST_MAGICAL_SKILL",
    "boostCastingTime": "BOOST_CASTING_TIME",
    "PVPAttackRatio": "PVP_ATTACK_RATIO",
    "PVPDefendRatio": "PVP_DEFEND_RATIO",
    "FlyTime": "FLY_TIME",
    "flyspeed": "FLY_SPEED",
    "Parry": "PARRY",
    "Block": "BLOCK",
    "HealSkillBoost": "HEAL_BOOST",
    "BoostHate": "BOOST_HATE",
    "Dodge": "EVASION",
}

# -------------------------------------------------
# Attribute mit erlaubten Negativwerten
# -------------------------------------------------
NEGATIVE_ALLOWED = {
    "ATTACK_SPEED",
    "PHYSICAL_DEFENSE",
    "MAXHP",
    "MAGICAL_RESIST",
}

# -------------------------------------------------
# Race Mapping
# -------------------------------------------------
RACE_MAP = {
    "0": "ELYOS",
    "1": "ASMODIANS",
    "2": "PC_ALL",
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
# Load client_strings_quest.xml
# -------------------------------------------------
def load_strings(path):
    try:
        tree = ET.parse(path)
    except ET.ParseError as e:
        print(f"[ERROR] XML-Fehler in {path}")
        print(f"        {e}")
        sys.exit(1)

    root = tree.getroot()
    name_to_id = {}
    name_to_body = {}

    for s in root.findall("string"):
        sid = s.findtext("id")
        name = s.findtext("name")
        body = s.findtext("body")

        if name and sid:
            name_to_id[name] = sid
        if name and body:
            name_to_body[name] = body

    return name_to_id, name_to_body

# -------------------------------------------------
# Main
# -------------------------------------------------
def main():
    require_file(CLIENT_TITLES)
    require_file(CLIENT_STRINGS)

    name_to_id, name_to_body = load_strings(CLIENT_STRINGS)

    try:
        tree = ET.parse(CLIENT_TITLES)
    except ET.ParseError as e:
        print(f"[ERROR] XML-Fehler in {CLIENT_TITLES}")
        print(f"        {e}")
        sys.exit(1)

    root = tree.getroot()
    out_root = ET.Element("player_titles")

    for ct in root.findall("client_title"):
        title_id = ct.findtext("id")
        desc_key = ct.findtext("desc")
        race_val = ct.findtext("title_race", "2")

        name_id = name_to_id.get(desc_key)
        desc_text = name_to_body.get(desc_key)

        if not name_id or not desc_text:
            continue

        title_el = ET.SubElement(
            out_root,
            "title",
            {
                "id": title_id,
                "nameId": name_id,
                "desc": desc_text,
                "race": RACE_MAP.get(race_val, "ALL"),
            },
        )

        modifiers_el = ET.SubElement(title_el, "modifiers")
        added = False

        bonus_attrs = ct.find("bonus_attrs")
        if bonus_attrs is not None:
            for data in bonus_attrs.findall("data"):
                bonus = data.findtext("bonus_attr")
                if not bonus:
                    continue

                parts = bonus.split()
                if len(parts) != 2:
                    continue

                client_attr, raw_value = parts
                is_percent = raw_value.endswith("%")
                value = raw_value.replace("%", "")

                server_attr = ATTR_MAP.get(client_attr)
                if not server_attr:
                    continue

                # AttackDelay invertieren
                if client_attr.lower() == "attackdelay":
                    value = str(-int(value))

                if value.startswith("-") and server_attr not in NEGATIVE_ALLOWED:
                    continue

                tag = "rate" if is_percent else "add"

                ET.SubElement(
                    modifiers_el,
                    tag,
                    {
                        "name": server_attr,
                        "value": value,
                        "bonus": "true",
                    },
                )
                added = True

        if not added:
            title_el.remove(modifiers_el)

    with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
        f.write(pretty_xml(out_root))

    print("✔ player_titles.xml erfolgreich erstellt")

# -------------------------------------------------
if __name__ == "__main__":
    main()
