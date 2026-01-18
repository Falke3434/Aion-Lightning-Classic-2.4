package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_GAMEPASS_INFO extends AionServerPacket {

	private final int id;
	@SuppressWarnings("unused")
	private final Player player;

	public SM_GAMEPASS_INFO(int id, Player player) {
		this.id = id;
		this.player = player;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(this.id);
		switch (this.id) {
		case 1:
			writeQ(-System.currentTimeMillis() / 1000);
			writeQ(-System.currentTimeMillis() / 1000);
			break;
		case 2:
			writeQ(0);
			writeQ(0);
			break;
		case 3:
			writeQ(0); // seconds
			writeQ(0);
			writeQ(0);
		}
	}
}
