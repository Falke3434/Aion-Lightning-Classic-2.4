package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.account.SielEnergyType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_GAMEPASS_OTHER_UPDATED extends AionServerPacket {

	private final Player player;

	private final SielEnergyType type;

	public SM_GAMEPASS_OTHER_UPDATED(Player player, SielEnergyType type) {
		this.player = player;
		this.type = type;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(player.getObjectId());
		writeD(type.getId());
	}
}
