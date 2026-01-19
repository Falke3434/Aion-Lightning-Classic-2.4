/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.aionemu.gameserver.network.aion.serverpackets.*;

/**
 * This class is holding opcodes for all server packets. It's used only to have
 * all opcodes in one place
 * 
 * @author Luno, alexa026, ATracer, avol, orz, cura
 */
public class ServerPacketsOpcodes {

	private static Map<Class<? extends AionServerPacket>, Integer> opcodes = new HashMap<Class<? extends AionServerPacket>, Integer>();

	static {
		Set<Integer> idSet = new HashSet<Integer>();

		addPacketOpcode(SM_VERSION_CHECK.class, 0x00, idSet); // 2.4 EU [S_VERSION_CHECK]
		addPacketOpcode(SM_STATS_INFO.class, 0x01, idSet); // [S_STATUS]
		addPacketOpcode(SM_STATUPDATE_HP.class, 0x03, idSet); // 2.4 EU [S_HIT_POINT]
		addPacketOpcode(SM_STATUPDATE_MP.class, 0x04, idSet); // 2.4 EU [S_MANA_POINT]
		addPacketOpcode(SM_ATTACK_STATUS.class, 0x05, idSet); // 2.4 EU [S_HIT_POINT_OTHER]
		addPacketOpcode(SM_STATUPDATE_DP.class, 0x06, idSet); // 2.4 EU [S_DP]
		addPacketOpcode(SM_DP_INFO.class, 0x07, idSet); // 2.4 EU [S_DP_USER]
		addPacketOpcode(SM_STATUPDATE_EXP.class, 0x08, idSet); // 2.4 EU [S_EXP]
		addPacketOpcode(SM_NPC_ASSEMBLER.class, 0x0A, idSet); // 2.4 EU [S_CUTSCENE_NPC_INFO]
		addPacketOpcode(SM_LEGION_UPDATE_NICKNAME.class, 0x0B, idSet); // 2.4 EU [S_CHANGE_GUILD_MEMBER_NICKNAME]
		addPacketOpcode(SM_LEGION_TABS.class, 0x0C, idSet); // 2.4 EU [S_GUILD_HISTORY]
		addPacketOpcode(SM_ENTER_WORLD_CHECK.class, 0x0D, idSet); // 2.4 EU [S_ENTER_WORLD_CHECK]
		addPacketOpcode(SM_NPC_INFO.class, 0x0E, idSet); // 2.4 EU [S_PUT_NPC]
		addPacketOpcode(SM_PLAYER_SPAWN.class, 0x0F, idSet); // 2.4 EU [S_WORLD]
		addPacketOpcode(SM_GATHERABLE_INFO.class, 0x11, idSet); // 2.4 EU [S_PUT_OBJECT]
		addPacketOpcode(SM_TELEPORT_LOC.class, 0x14, idSet); // 2.4 EU [S_REQUEST_TELEPORT]
		addPacketOpcode(SM_PLAYER_MOVE.class, 0x15, idSet); // 2.4 EU [S_BLINK]
		addPacketOpcode(SM_DELETE.class, 0x16, idSet); // 2.4 EU [S_REMOVE_OBJECT]
		addPacketOpcode(SM_LOGIN_QUEUE.class, 0x17, idSet); // 2.4 EU [S_WAIT_LIST]
		addPacketOpcode(SM_MESSAGE.class, 0x18, idSet); // 2.4 EU [S_MESSAGE]
		addPacketOpcode(SM_SYSTEM_MESSAGE.class, 0x19, idSet); // 2.4 EU [S_MESSAGE_CODE]
		addPacketOpcode(SM_INVENTORY_INFO.class, 0x1A, idSet); // 2.4 EU [S_LOAD_INVENTORY]
		addPacketOpcode(SM_INVENTORY_ADD_ITEM.class, 0x1B, idSet); // 2.4 EU [S_ADD_INVENTORY]
		addPacketOpcode(SM_DELETE_ITEM.class, 0x1C, idSet); // 2.4 EU [S_REMOVE_INVENTORY]
		addPacketOpcode(SM_INVENTORY_UPDATE_ITEM.class, 0x1D, idSet); // 2.4 EU [S_CHANGE_ITEM_DESC]
		addPacketOpcode(SM_UI_SETTINGS.class, 0x1E, idSet); // 2.4 EU [S_LOAD_CLIENT_SETTINGS]
		addPacketOpcode(SM_PLAYER_STANCE.class, 0x1F, idSet); // 2.4 EU [S_CHANGE_STANCE]
		addPacketOpcode(SM_PLAYER_INFO.class, 0x20, idSet); // 2.4 EU [S_PUT_USER]
		addPacketOpcode(SM_CASTSPELL.class, 0x21, idSet); // 2.4 EU [S_USE_SKILL]
		addPacketOpcode(SM_GATHER_STATUS.class, 0x22, idSet); // 2.4 EU [S_GATHER_OTHER]
		addPacketOpcode(SM_GATHER_UPDATE.class, 0x23, idSet); // 2.4 EU [S_GATHER]
		addPacketOpcode(SM_UPDATE_PLAYER_APPEARANCE.class, 0x24, idSet); // 2.4 EU [S_WIELD]
		addPacketOpcode(SM_EMOTION.class, 0x25, idSet); // 2.4 EU [S_ACTION]
		addPacketOpcode(SM_GAME_TIME.class, 0x26, idSet); // 2.4 EU [S_TIME]
		addPacketOpcode(SM_TIME_CHECK.class, 0x27, idSet); // 2.4 EU [S_SYNC_TIME]
		addPacketOpcode(SM_LOOKATOBJECT.class, 0x28, idSet); // 2.4 EU [S_NPC_CHANGED_TARGET]
		addPacketOpcode(SM_TARGET_SELECTED.class, 0x29, idSet); // 2.4 EU [S_TARGET_INFO]
		addPacketOpcode(SM_SKILL_CANCEL.class, 0x2A, idSet); // 2.4 EU [S_SKILL_CANCELED]
		addPacketOpcode(SM_CASTSPELL_RESULT.class, 0x2B, idSet); // 2.4 EU [S_SKILL_SUCCEDED]
		addPacketOpcode(SM_SKILL_LIST.class, 0x2C, idSet); // 2.4 EU [S_ADD_SKILL]
		addPacketOpcode(SM_SKILL_REMOVE.class, 0x2D, idSet); // 2.4 EU [S_DELETE_SKILL]
		addPacketOpcode(SM_SKILL_ACTIVATION.class, 0x2E, idSet); // 2.4 EU [S_TOGGLE_SKILL_ON_OFF]
		addPacketOpcode(SM_ABNORMAL_STATE.class, 0x31, idSet); // 2.4 EU [S_ABNORMAL_STATUS]
		addPacketOpcode(SM_ABNORMAL_EFFECT.class, 0x32, idSet); // 2.4 EU [S_ABNORMAL_STATUS_OTHER]
		addPacketOpcode(SM_SKILL_COOLDOWN.class, 0x33, idSet); // 2.4 EU [S_LOAD_SKILL_COOLTIME]
		addPacketOpcode(SM_QUESTION_WINDOW.class, 0x34, idSet); // 2.4 EU [S_ASK]
		addPacketOpcode(SM_ATTACK.class, 0x36, idSet); // 2.4 EU [S_ATTACK]
		addPacketOpcode(SM_MOVE.class, 0x37, idSet); // 2.4 EU [S_MOVE_NEW]
		addPacketOpcode(SM_HEADING_UPDATE.class, 0x39, idSet); // 2.4 EU [S_CHANGE_DIRECTION]
		addPacketOpcode(SM_TRANSFORM.class, 0x3A, idSet); // 2.4 EU [S_POLYMORPH]
		addPacketOpcode(SM_DIALOG_WINDOW.class, 0x3C, idSet); // 2.4 EU [S_NPC_HTML_MESSAGE]
		addPacketOpcode(SM_SELL_ITEM.class, 0x3E, idSet); // 2.4 EU [S_STORE_PURCHASE_INFO]
		addPacketOpcode(SM_VIEW_PLAYER_DETAILS.class, 0x41, idSet); // 2.4 EU [S_ITEM_LIST]
		addPacketOpcode(SM_WEATHER.class, 0x43, idSet); // 2.4 EU [S_WEATHER]
		addPacketOpcode(SM_PLAYER_STATE.class, 0x44, idSet); // 2.4 EU [S_INVISIBLE_LEVEL]
		addPacketOpcode(SM_LEVEL_UPDATE.class, 0x46, idSet); // 2.4 EU
		addPacketOpcode(SM_QUEST_LIST.class, 0x47, idSet); // 2.4 EU [S_LOAD_WORKINGQUEST]
		addPacketOpcode(SM_KEY.class, 0x48, idSet); // 2.4 EU [S_KEY]
		addPacketOpcode(SM_SUMMON_PANEL_REMOVE.class, 0x49, idSet); // 2.4 EU [S_RESET_SKILL_COOLING_TIME]
		addPacketOpcode(SM_EXCHANGE_REQUEST.class, 0x4A, idSet); // 2.4 EU [S_XCHG_START]
		addPacketOpcode(SM_EXCHANGE_ADD_ITEM.class, 0x4B, idSet); // 2.4 EU [S_ADD_XCHG]
		addPacketOpcode(SM_EXCHANGE_ADD_KINAH.class, 0x4D, idSet); // 2.4 EU [S_XCHG_GOLD]
		addPacketOpcode(SM_EXCHANGE_CONFIRMATION.class, 0x4E, idSet); // 2.4 EU [S_XCHG_RESULT]
		addPacketOpcode(SM_EMOTION_LIST.class, 0x4F, idSet); // 2.4 EU [S_ADDREMOVE_SOCIAL]
		addPacketOpcode(SM_TARGET_UPDATE.class, 0x51, idSet); // 2.4 EU [S_USER_CHANGED_TARGET]

		addPacketOpcode(SM_PLASTIC_SURGERY.class, 0x53, idSet); // 2.4 EU [S_EDIT_CHARACTER]
		addPacketOpcode(SM_INFLUENCE_RATIO.class, 0x55, idSet); // 2.4 EU [S_ABYSS_NEXT_PVP_CHANGE_TIME]
		addPacketOpcode(SM_FORTRESS_STATUS.class, 0x56, idSet); // 2.4 EU [S_ABYSS_CHANGE_NEXT_PVP_STATUS]
		addPacketOpcode(SM_CAPTCHA.class, 0x57, idSet); // 2.4 EU [S_CAPTCHA]
		addPacketOpcode(SM_RENAME.class, 0x58, idSet); // 2.4 EU [S_ADDED_SERVICE_CHANGE]
		addPacketOpcode(SM_SHOW_NPC_ON_MAP.class, 0x59, idSet); // 2.4 EU [S_FIND_NPC_POS_RESULT]
		addPacketOpcode(SM_GROUP_INFO.class, 0x5A, idSet); // 2.4 EU [S_PARTY_INFO]
		addPacketOpcode(SM_GROUP_MEMBER_INFO.class, 0x5B, idSet); // 2.4 EU [S_PARTY_MEMBER_INFO]
		addPacketOpcode(SM_ABYSS_ARTIFACT_INFO.class, 0x60, idSet); // 2.4 EU [S_BALAUREA_INFO]
		addPacketOpcode(SM_QUIT_RESPONSE.class, 0x62, idSet); // 2.4 EU [S_ASK_QUIT_RESULT]
		addPacketOpcode(SM_CHAT_WINDOW.class, 0x63, idSet); // 2.4 EU [S_ASK_INFO_RESULT]
		addPacketOpcode(SM_PET.class, 0x65, idSet); // 2.4 EU [S_FUNCTIONAL_PET]
		addPacketOpcode(SM_ITEM_COOLDOWN.class, 0x67, idSet); // 2.4 EU [S_LOAD_ITEM_COOLTIME]
		addPacketOpcode(SM_UPDATE_NOTE.class, 0x68, idSet); // 2.4 EU [S_TODAY_WORDS]
		addPacketOpcode(SM_PLAY_MOVIE.class, 0x69, idSet); // 2.4 EU [S_PLAY_CUTSCENE]
		addPacketOpcode(SM_LEGION_INFO.class, 0x6E, idSet); // 2.4 EU [S_GUILD_INFO]
		addPacketOpcode(SM_LEGION_ADD_MEMBER.class, 0x6F, idSet); // 2.4 EU [S_ADD_GUILD_MEMBER]
		addPacketOpcode(SM_LEGION_LEAVE_MEMBER.class, 0x70, idSet); // 2.4 EU [S_DELETE_GUILD_MEMBER]
		addPacketOpcode(SM_LEGION_UPDATE_MEMBER.class, 0x71, idSet); // 2.4 EU [S_CHANGE_GUILD_MEMBER_INFO]
		addPacketOpcode(SM_LEGION_UPDATE_TITLE.class, 0x72, idSet); // 2.4 EU [S_CHANGE_GUILD_OTHER]
		addPacketOpcode(SM_LEGION_UPDATE_SELF_INTRO.class, 0x77, idSet); // 2.4 EU [S_CHANGE_GUILD_MEMBER_INTRO]
		addPacketOpcode(SM_INSTANCE_SCORE.class, 0x79, idSet); // 2.4 EU [S_INSTANT_DUNGEON_INFO]
		addPacketOpcode(SM_AUTO_GROUP.class, 0x7A, idSet); // 2.4 EU [S_MATCHMAKER_INFO]
		addPacketOpcode(SM_QUEST_COMPLETED_LIST.class, 0x7B, idSet); // 2.4 EU [S_LOAD_FINISHEDQUEST]
		addPacketOpcode(SM_QUEST_ACTION.class, 0x7C, idSet); // 2.4 EU [S_QUEST]
		addPacketOpcode(SM_NEARBY_QUESTS.class, 0x7F, idSet); // 2.4 EU [S_UPDATE_ZONE_QUEST]
		addPacketOpcode(SM_PING_RESPONSE.class, 0x80, idSet); // 2.4 EU [S_PING]
		addPacketOpcode(SM_CUBE_UPDATE.class, 0x82, idSet); // 2.4 EU [S_EVENT]
		addPacketOpcode(SM_FRIEND_LIST.class, 0x84, idSet); // 2.4 EU [S_BUDDY_LIST]
		addPacketOpcode(SM_PRIVATE_STORE.class, 0x86, idSet); // 2.4 EU [S_SHOP_SELL_LIST]
		addPacketOpcode(SM_GROUP_LOOT.class, 0x87, idSet); // 2.4 EU [S_GROUP_ITEM_DIST]
		addPacketOpcode(SM_ABYSS_RANK_UPDATE.class, 0x88, idSet); // 2.4 EU [S_ETC_STATUS]
		addPacketOpcode(SM_MAY_LOGIN_INTO_GAME.class, 0x89, idSet); // 2.4 EU [S_SA_ACCOUNT_ITEM_NOTI]
		addPacketOpcode(SM_ABYSS_RANKING_PLAYERS.class, 0x8A, idSet); // 2.4 EU [S_ABYSS_RANKER_INFOS]
		addPacketOpcode(SM_ABYSS_RANKING_LEGIONS.class, 0x8B, idSet); // 2.4 EU [S_ABYSS_GUILD_INFOS]
		addPacketOpcode(SM_INSTANCE_STAGE_INFO.class, 0x8C, idSet); // 2.4 EU [S_WORLD_SCENE_STATUS]
		addPacketOpcode(SM_INSTANCE_INFO.class, 0x8D, idSet); // 2.4 EU [S_INSTANCE_DUNGEON_COOLTIMES]
		addPacketOpcode(SM_PONG.class, 0x8E, idSet); // 2.4 EU [S_ALIVE]
		addPacketOpcode(SM_KISK_UPDATE.class, 0x90, idSet); // 2.4 EU [S_PLACEABLE_BINDSTONE_INFO]
		addPacketOpcode(SM_PRIVATE_STORE_NAME.class, 0x91, idSet); // 2.4 EU [S_PERSONAL_SHOP]
		addPacketOpcode(SM_BROKER_SERVICE.class, 0x92, idSet); // 2.4 EU [S_VENDOR]
		addPacketOpcode(SM_MOTION.class, 0x94, idSet); // 2.4 EU [S_CUSTOM_ANIM]
		addPacketOpcode(SM_TRADE_IN_LIST.class, 0x97, idSet); // 2.4 EU [S_TRADE_IN]
		// addPacketOpcode(SM_BROKER_REGISTRATION_SERVICE.class, 0x93, idSet);
		// addPacketOpcode(SM_BROKER_SETTLED_LIST.class, 0x95, idSet);
		addPacketOpcode(SM_SUMMON_OWNER_REMOVE.class, 0x9A, idSet); // 2.4 EU [S_REMOVE_PET]
		addPacketOpcode(SM_SUMMON_PANEL.class, 0x99, idSet); // 2.4 EU [S_ADD_PET]
		addPacketOpcode(SM_SUMMON_UPDATE.class, 0x9B, idSet); // 2.4 EU [S_CHANGE_PET_STATUS]
		addPacketOpcode(SM_TRANSFORM_IN_SUMMON.class, 0x9C, idSet); // 2.4 EU [S_CHANGE_MASTER]
		addPacketOpcode(SM_LEGION_MEMBERLIST.class, 0x9D, idSet); // 2.4 EU [S_GUILD_MEMBER_INFO]
		addPacketOpcode(SM_LEGION_EDIT.class, 0x9E, idSet); // 2.4 EU [S_CHANGE_GUILD_INFO]
		addPacketOpcode(SM_TOLL_INFO.class, 0x9F, idSet); // 2.4 EU [S_SHOP_POINT_INFO]
		addPacketOpcode(SM_MAIL_SERVICE.class, 0xA1, idSet); // 2.4 EU [S_MAIL]
		addPacketOpcode(SM_SUMMON_USESKILL.class, 0xA2, idSet); // 2.4 EU [S_ALLOW_PET_USE_SKILL]
		addPacketOpcode(SM_WINDSTREAM.class, 0xA3, idSet); // 2.4 EU [S_WIND_PATH_RESULT]
		addPacketOpcode(SM_WINDSTREAM_ANNOUNCE.class, 0xA4, idSet); // 2.4 EU [S_WIND_STATE_INFO]
		addPacketOpcode(SM_FIND_GROUP.class, 0xA6, idSet); // 2.4 EU [S_PARTY_MATCH]
		addPacketOpcode(SM_REPURCHASE.class, 0xA7, idSet); // 2.4 EU [S_USER_SELL_HISTORY_LIST]
		addPacketOpcode(SM_WAREHOUSE_INFO.class, 0xA8, idSet); // 2.4 EU [S_LOAD_WAREHOUSE]
		addPacketOpcode(SM_WAREHOUSE_ADD_ITEM.class, 0xA9, idSet); // 2.4 EU [S_ADD_WAREHOUSE]
		addPacketOpcode(SM_DELETE_WAREHOUSE_ITEM.class, 0xAA, idSet); // 2.4 EU [S_REMOVE_WAREHOUSE]
		addPacketOpcode(SM_WAREHOUSE_UPDATE_ITEM.class, 0xAB, idSet); // 2.4 EU [S_CHANGE_WAREHOUSE_ITEM_DESC]
		// addPacketOpcode(SM_IN_GAME_SHOP_CATEGORY_LIST.class, 0xAC, idSet); //
		// ingameshop
		// addPacketOpcode(SM_IN_GAME_SHOP_LIST.class, 0xAD, idSet); // ingameshop
		// addPacketOpcode(SM_IN_GAME_SHOP_ITEM.class, 0xAE, idSet); // ingameshop
		addPacketOpcode(SM_TITLE_INFO.class, 0xB0, idSet); // 2.4 EU [S_TITLE]
		addPacketOpcode(SM_CHARACTER_SELECT.class, 0xB1, idSet); // 2.4 EU [S_2ND_PASSWORD]
		addPacketOpcode(SM_CRAFT_ANIMATION.class, 0xB4, idSet); // 2.4 EU [S_COMBINE_OTHER]
		addPacketOpcode(SM_CRAFT_UPDATE.class, 0xB5, idSet); // 2.4 EU [S_COMBINE]
		addPacketOpcode(SM_ASCENSION_MORPH.class, 0xB6, idSet); // 2.4 EU [S_PLAY_MODE]
		addPacketOpcode(SM_ITEM_USAGE_ANIMATION.class, 0xB7, idSet); // 2.4 EU [S_USE_ITEM]
		addPacketOpcode(SM_CUSTOM_SETTINGS.class, 0xB8, idSet); // 2.4 EU [S_CHANGE_FLAG]
		addPacketOpcode(SM_DUEL.class, 0xB9, idSet); // 2.4 EU [S_DUEL]
		addPacketOpcode(SM_PET_EMOTE.class, 0xBB, idSet); // 2.4 EU [S_FUNCTIONAL_PET_MOVE]
		addPacketOpcode(SM_QUESTIONNAIRE.class, 0xBF, idSet); // 2.4 EU [S_POLL_CONTENTS]
		addPacketOpcode(SM_DIE.class, 0xC1, idSet); // 2.4 EU [S_RESURRECT_INFO]
		addPacketOpcode(SM_RESURRECT.class, 0xC2, idSet); // 2.4 EU [S_RESURRECT_BY_OTHER]
		addPacketOpcode(SM_FORCED_MOVE.class, 0xC3, idSet); // 2.4 EU [S_MOVEBACK]
		addPacketOpcode(SM_TELEPORT_MAP.class, 0xC4, idSet); // 2.4 EU [S_ROUTEMAP_INFO]
		addPacketOpcode(SM_USE_OBJECT.class, 0xC5, idSet); // 2.4 EU [S_GAUGE]

		addPacketOpcode(SM_L2AUTH_LOGIN_CHECK.class, 0xC7, idSet); // 2.4 EU [S_L2AUTH_LOGIN_CHECK]
		addPacketOpcode(SM_CHARACTER_LIST.class, 0xC8, idSet); // 2.4 EU [S_CHARACTER_LIST]
		addPacketOpcode(SM_CREATE_CHARACTER.class, 0xC9, idSet); // 2.4 EU [S_CREATE_CHARACTER]
		addPacketOpcode(SM_DELETE_CHARACTER.class, 0xCA, idSet); // 2.4 EU [S_DELETE_CHARACTER]
		addPacketOpcode(SM_RESTORE_CHARACTER.class, 0xCB, idSet); // 2.4 EU [S_RESTORE_CHARACTER]
		addPacketOpcode(SM_TARGET_IMMOBILIZE.class, 0xCC, idSet); // 2.4 EU [S_FORCE_BLINK]
		addPacketOpcode(SM_LOOT_STATUS.class, 0xCD, idSet); // 2.4 EU [S_LOOT]
		addPacketOpcode(SM_LOOT_ITEMLIST.class, 0xCE, idSet); // 2.4 EU [S_LOOT_ITEMLIST]
		addPacketOpcode(SM_RECIPE_LIST.class, 0xCF, idSet); // 2.4 EU [S_RECIPE_LIST]
		addPacketOpcode(SM_MANTRA_EFFECT.class, 0xD0, idSet); // 2.4 EU [S_SKILL_ACTIVATED]
		addPacketOpcode(SM_SIEGE_LOCATION_INFO.class, 0xD1, idSet); // 2.4 EU [S_ABYSS_INFO]
		addPacketOpcode(SM_PLAYER_SEARCH.class, 0xD3, idSet); // 2.4 EU [S_SEARCH_USER_RESULT]
		addPacketOpcode(SM_LEGION_SEND_EMBLEM.class, 0xD5, idSet); // 2.4 EU [S_GUILD_EMBLEM_IMG_BEGIN]
		addPacketOpcode(SM_LEGION_SEND_EMBLEM_DATA.class, 0xD6, idSet); // 2.4 EU [S_GUILD_EMBLEM_IMG_DATA]
		addPacketOpcode(SM_LEGION_UPDATE_EMBLEM.class, 0xD7, idSet); // 2.4 EU [S_GUILD_EMBLEM_UPDATED]
		// addPacketOpcode(SM_ABYSS_ARTIFACT_INFO2.class, 0xD9, idSet);
		addPacketOpcode(SM_SHIELD_EFFECT.class, 0xDA, idSet); // 2.4 EU [S_ABYSS_SHIELD_INFO]
		// addPacketOpcode(SM_ABYSS_ARTIFACT_INFO3.class, 0xDC, idSet);
		addPacketOpcode(SM_FRIEND_RESPONSE.class, 0xDF, idSet); // 2.4 EU [S_BUDDY_RESULT]
		addPacketOpcode(SM_BLOCK_RESPONSE.class, 0xE0, idSet); // 2.4 EU [S_BLOCK_RESULT]
		addPacketOpcode(SM_BLOCK_LIST.class, 0xE1, idSet); // 2.4 EU [S_BLOCK_LIST]
		addPacketOpcode(SM_FRIEND_NOTIFY.class, 0xE2, idSet); // 2.4 EU [S_NOTIFY_BUDDY]
		addPacketOpcode(SM_FRIEND_STATUS.class, 0xE4, idSet); // 2.4 EU [S_CUR_STATUS]
		addPacketOpcode(SM_CHANNEL_INFO.class, 0xE6, idSet); // 2.4 EU [S_CHANGE_CHANNEL]
		addPacketOpcode(SM_CHAT_INIT.class, 0xE7, idSet); // 2.4 EU [S_SIGN_CLIENT]
		addPacketOpcode(SM_MACRO_LIST.class, 0xE8, idSet); // 2.4 EU [S_LOAD_MACRO]
		addPacketOpcode(SM_MACRO_RESULT.class, 0xE9, idSet); // 2.4 EU [S_MACRO_RESULT]
		addPacketOpcode(SM_NICKNAME_CHECK_RESPONSE.class, 0xEA, idSet); // 2.4 EU [S_EXIST_RESULT]
		addPacketOpcode(SM_RIFT_ANNOUNCE.class, 0xED, idSet); // 2.4 EU [S_WORLD_INFO]
		addPacketOpcode(SM_ABYSS_RANK.class, 0xEE, idSet); // 2.4 EU [S_ABYSS_POINT]
		addPacketOpcode(SM_ACCOUNT_ACCESS_PROPERTIES.class, 0xEF, idSet); // [S_BUILDER_LEVEL]
		addPacketOpcode(SM_PETITION_STATUS.class, 0xF0, idSet); // 2.4 EU
		addPacketOpcode(SM_FRIEND_UPDATE.class, 0xF1, idSet); // 2.4 EU [S_BUDDY_DATA]
		addPacketOpcode(SM_LEARN_RECIPE.class, 0xF2, idSet); // 2.4 EU [S_ADD_RECIPE]
		addPacketOpcode(SM_RECIPE_DELETE.class, 0xF3, idSet); // 2.4 EU [S_REMOVE_RECIPE]
		addPacketOpcode(SM_FLY_TIME.class, 0xF5, idSet); // 2.4 EU [S_FLIGHT_POINT]
		addPacketOpcode(SM_ALLIANCE_INFO.class, 0xF6, idSet); // 2.4 EU [S_ALLIANCE_INFO]
		addPacketOpcode(SM_ALLIANCE_MEMBER_INFO.class, 0xF7, idSet); // 2.4 EU [S_ALLIANCE_MEMBER_INFO]
		addPacketOpcode(SM_LEAVE_GROUP_MEMBER.class, 0xF8, idSet); // 2.4 EU [S_GROUP_INFO]
		addPacketOpcode(SM_SHOW_BRAND.class, 0xFA, idSet); // 2.4 EU [S_TACTICS_SIGN]
		addPacketOpcode(SM_ALLIANCE_READY_CHECK.class, 0xFB, idSet); // 2.4 EU [S_GROUP_READY]
		addPacketOpcode(SM_PRICES.class, 0xFD, idSet); // 2.4 EU [S_TAX_INFO]
		addPacketOpcode(SM_TRADELIST.class, 0xFE, idSet); // 2.4 EU [S_STORE_SALE_INFO]
		addPacketOpcode(SM_RECONNECT_KEY.class, 0x100, idSet); // 2.4 EU [S_RECONNECT_KEY]
		addPacketOpcode(SM_GAMEPASS_INFO.class, 0x11A, idSet); // 2.4 EU
		addPacketOpcode(SM_GAMEPASS_OTHER_UPDATED.class, 0x11B, idSet); // 2.4 EU
		addPacketOpcode(SM_CHAT_ACCUSE.class, 0x122, idSet); // 2.4 EU
		addPacketOpcode(SM_SPAM_FILTER_FLAG.class, 0x127, idSet); // 2.4 EU
		addPacketOpcode(SM_CUSTOM_PACKET.class, 99999, idSet); // 2.4 EU fake packet

		addPacketOpcode(SM_SERVER_ENV.class, 0x10F, idSet);
		addPacketOpcode(SM_PACKAGE_INFO_NOTIFY.class, 0x102, idSet); // 2.4 EU [S_BM_PACK_LIST]
		addPacketOpcode(S_REPLY_NP_LOGIN_GAMESVR.class, 0x107, idSet);
		addPacketOpcode(S_REPLY_NP_CONSUME_TOKEN_RESULT.class, 0x108, idSet);
		addPacketOpcode(S_REPLY_NP_AUTH_TOKEN.class, 0x109, idSet);
		addPacketOpcode(S_NPSHOP_GOODS_COUNT.class, 0x10B, idSet);
		addPacketOpcode(S_READY_ENTER_WORLD.class, 0x11E, idSet);
		addPacketOpcode(S_AFTER_ENTER_WORLD.class, 0x193, idSet);

	}

	static int getOpcode(Class<? extends AionServerPacket> packetClass) {
		Integer opcode = opcodes.get(packetClass);
		if (opcode == null)
			throw new IllegalArgumentException("There is no opcode for " + packetClass + " defined.");

		return opcode;
	}

	private static void addPacketOpcode(Class<? extends AionServerPacket> packetClass, int opcode, Set<Integer> idSet) {
		if (opcode < 0)
			return;

		if (idSet.contains(opcode))
			throw new IllegalArgumentException(String.format("There already exists another packet with id 0x%02X", opcode));

		idSet.add(opcode);
		opcodes.put(packetClass, opcode);
	}
}
