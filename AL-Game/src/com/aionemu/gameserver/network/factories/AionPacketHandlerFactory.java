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
package com.aionemu.gameserver.network.factories;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.AionPacketHandler;
import com.aionemu.gameserver.network.aion.clientpackets.*;

/**
 * This factory is responsible for creating {@link AionPacketHandler} object. It
 * also initializes created handler with a set of packet prototypes.<br>
 * Object of this classes uses <tt>Injector</tt> for injecting dependencies into
 * prototype objects.<br>
 * <br>
 * 
 * @author Luno
 */
public class AionPacketHandlerFactory {

	private AionPacketHandler handler;

	public static AionPacketHandlerFactory getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * Creates new instance of <tt>AionPacketHandlerFactory</tt><br>
	 */
	public AionPacketHandlerFactory() {
		handler = new AionPacketHandler();
		////////////////// 2.4/////////////////////
		addPacket(new CM_QUESTIONNAIRE(0x163, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_L2AUTH_LOGIN_CHECK(0x11F, State.CONNECTED));// FOUND 2.4
		addPacket(new CM_CHARACTER_LIST(0x11C, State.AUTHED));// FOUND 2.4
		addPacket(new CM_TELEPORT_SELECT(0x11E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_RESTORE_CHARACTER(0x11B, State.AUTHED));// FOUND 2.4
		addPacket(new CM_START_LOOT(0x118, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CREATE_CHARACTER(0x11D, State.AUTHED));// FOUND 2.4
		addPacket(new CM_DELETE_CHARACTER(0x11A, State.AUTHED));// FOUND 2.4
		addPacket(new CM_SPLIT_ITEM(0x117, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LOOT_ITEM(0x119, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MOVE_ITEM(0x116, State.IN_GAME));// FOUND 2.4
		//addPacket(new CM_LEGION_UPLOAD_EMBLEM(0x114, State.IN_GAME));
		addPacket(new CM_READ_EXPRESS_MAIL(0x150, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PLAYER_SEARCH(0x115, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_LEGION_UPLOAD_INFO(0x113, State.IN_GAME));// 2.6
		addPacket(new CM_BLOCK_ADD(0x14C, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_FRIEND_STATUS(0x148, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BLOCK_DEL(0x14D, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SHOW_BLOCKLIST(0x14A, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHAT_AUTH(0x144, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_CHANGE_CHANNEL(0x11F, State.IN_GAME));// 2.6
		addPacket(new CM_CHECK_NICKNAME(0x143, State.AUTHED));// FOUND 2.4
		addPacket(new CM_REPLACE_ITEM(0x140, State.IN_GAME));// 2.4
		addPacket(new CM_MACRO_CREATE(0x145, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MACRO_DELETE(0x142, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SHOW_BRAND(0x17F, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BLOCK_SET_REASON(0x141, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DISTRIBUTION_SETTINGS(0x178, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MAY_LOGIN_INTO_GAME(0x179, State.AUTHED));// FOUND 2.4
		addPacket(new CM_RECONNECT_AUTH(0x17A, State.AUTHED));// FOUND 2.4
		addPacket(new CM_GROUP_LOOT(0x17B, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MAC_ADDRESS(0x174, State.CONNECTED, State.AUTHED, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_ABYSS_RANKING_PLAYERS(0x177, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_IN_GAME_SHOP_INFO(0x130, State.IN_GAME));// 2.6
		// addPacket(new CM_REPORT_PLAYER(0x132, State.IN_GAME));// 2.6
		addPacket(new CM_INSTANCE_INFO(0x1B3, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_APPEARANCE(0x1AC, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_SHOW_MAP(0x1AF, State.IN_GAME));// 2.4
		addPacket(new CM_SUMMON_MOVE(0x1A8, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SUMMON_EMOTION(0x1A9, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_AUTO_GROUP(0x1AB, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SUMMON_CASTSPELL(0x1A4, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_FUSION_WEAPONS(0x1A5, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SUMMON_ATTACK(0x1A6, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PLAY_MOVIE_END(0x123, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DELETE_QUEST(0x122, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BUY_TRADE_IN_TRADE(0x2DA, State.IN_GAME));// 2.7
		addPacket(new CM_STOP_TRAINING(0x2DE, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_ITEM_REMODEL(0x2D8, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_GODSTONE_SOCKET(0x2D9, State.IN_GAME));// 2.4
		addPacket(new CM_INVITE_TO_GROUP(0x113, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PLAYER_STATUS_INFO(0x112, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_VIEW_PLAYER_DETAILS(0x10E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PING_REQUEST(0x10D, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SHOW_FRIENDLIST(0x104, State.IN_GAME));// 2.4
		addPacket(new CM_CLIENT_COMMAND_ROLL(0x109, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_GROUP_DISTRIBUTION(0x106, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DUEL_REQUEST(0x100, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_FRIEND_ADD(0x105, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_FRIEND_DEL(0x102, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_ABYSS_RANKING_LEGIONS(0x13C, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DELETE_ITEM(0x13E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SUMMON_COMMAND(0x13B, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PRIVATE_STORE(0x13D, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PRIVATE_STORE_NAME(0x13A, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_REGISTERED(0x137, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BUY_BROKER_ITEM(0x134, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_LIST(0x139, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_SEARCH(0x136, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_SETTLE_LIST(0x173, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_SETTLE_ACCOUNT(0x170, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_REGISTER_BROKER_ITEM(0x135, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BROKER_CANCEL_REGISTERED(0x172, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_OPEN_MAIL_WINDOW(0x174, State.IN_GAME));//2.7
		addPacket(new CM_READ_MAIL(0x16C, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SEND_MAIL(0x16E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DELETE_MAIL(0x16B, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_GET_MAIL_ATTACHMENT(0x16A, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CRAFT(0x167, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_CLIENT_COMMAND_LOC(0x17D, State.IN_GAME));// 2.6
		addPacket(new CM_TITLE_SET(0x169, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_TIME_CHECK(0xE0, State.CONNECTED, State.AUTHED, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_LEGION_SEND_EMBLEM(0x83, State.IN_GAME));// 2.6
		addPacket(new CM_PET_EMOTE(0x9E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PET(0x9C, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_GATHER(0xE1, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_OPEN_STATICDOOR(0x9D, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHAT_MESSAGE_PUBLIC(0x99, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHAT_MESSAGE_WHISPER(0x96, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CASTSPELL(0xD3, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_TOGGLE_SKILL_DEACTIVATE(0xD0, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_TARGET_SELECT(0x95, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_ATTACK(0xD2, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_USE_ITEM(0xCF, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EQUIP_ITEM(0xCC, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_REMOVE_ALTERED_STATE(0xD1, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PLAYER_LISTENER(0xCA, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LEGION(0xC7, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_INSTANCE_LEAVE(0xC4, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EMOTION(0xC9, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_PING(0xC6, State.AUTHED, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MOVE_IN_AIR(0xC3, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_QUESTION_RESPONSE(0xC0, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LEGION_SEND_EMBLEM_INFO(0xE2, State.IN_GAME));// 2.4
		addPacket(new CM_MOVE(0xC2, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CLOSE_DIALOG(0xFF, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_DIALOG_SELECT(0xFC, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_BUY_ITEM(0xC1, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SHOW_DIALOG(0xFE, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_SET_NOTE(0xF8, State.IN_GAME));// FOUND 2.4
		// addPacket(new CM_LEGION_TABS(0xAA, State.IN_GAME));// 2.6
		// addPacket(new CM_CHAT_WINDOW(0xAC, State.IN_GAME));// 2.6
		// addPacket(new CM_LEGION_MODIFY_EMBLEM(0xAE, State.IN_GAME));// 2.6
		addPacket(new CM_EXCHANGE_ADD_KINAH(0x130, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EXCHANGE_REQUEST(0xF5, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EXCHANGE_ADD_ITEM(0x132, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EXCHANGE_CANCEL(0x12F, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_WINDSTREAM(0x12C, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EXCHANGE_LOCK(0x131, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_EXCHANGE_OK(0x12E, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MANASTONE(0x128, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_MOTION(0x12D, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_FIND_GROUP(0x127, State.AUTHED, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHARGE_ITEM(0x124, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LEGION_WH_KINAH(0x126, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHARACTER_PASSKEY(0x1A0, State.AUTHED));// FOUND 2.4
		addPacket(new CM_BREAK_WEAPONS(0x1A2, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_VERSION_CHECK(0xF2, State.CONNECTED));// FOUND 2.4
		addPacket(new CM_REVIVE(0xEF, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_QUIT(0xEE, State.AUTHED, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LOGOUT(0xF0, State.AUTHED, State.IN_GAME));
		addPacket(new CM_MAY_QUIT(0xF1, State.AUTHED, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_LEVEL_READY(0xEB, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_UI_SETTINGS(0xE8, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CHARACTER_EDIT(0xED, State.AUTHED));// FOUND 2.4
		addPacket(new CM_ENTER_WORLD(0xEA, State.AUTHED));// FOUND 2.4
		addPacket(new CM_CAPTCHA(0xE4, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_OBJECT_SEARCH(0xE9, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_CUSTOM_SETTINGS(0xE6, State.IN_GAME));// FOUND 2.4

		addPacket(new CM_READY_ENTER_WORLD(0x193, State.IN_GAME));// 2.4
		addPacket(new CM_UNK_114(0x114, State.IN_GAME));// FOUND 2.4
		addPacket(new CM_GM_COMMAND_SEND(0xC8, State.IN_GAME));
		addPacket(new CM_GM_BOOKMARK(0xCB, State.IN_GAME));
		addPacket(new CM_TELEPORT_QUEST(0x1BC, State.IN_GAME));
		addPacket(new CM_UNK_0x187(0x187, State.IN_GAME)); //TODO after Teleport
		addPacket(new CM_RANK_LIST(0x181, State.IN_GAME)); //TODO My Ranklist
		addPacket(new CM_MY_DOCUMENTATION(0x1BE, State.IN_GAME)); //TODO
		addPacket(new CM_GAMEGUARD(0x10A, State.IN_GAME)); //C_NCGUARD
		addPacket(new CM_UPGRADE_ARCADE(0x1B9, State.IN_GAME));
	}

	public AionPacketHandler getPacketHandler() {
		return handler;
	}

	private void addPacket(AionClientPacket prototype) {
		handler.addPacketPrototype(prototype);
	}

	private static class SingletonHolder {

		protected static final AionPacketHandlerFactory instance = new AionPacketHandlerFactory();
	}
}
