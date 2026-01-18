package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class S_AFTER_ENTER_WORLD extends AionServerPacket {

	private final int code;

	public S_AFTER_ENTER_WORLD(int type, int code) {
		this.code = code;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(this.code);
	}
}
