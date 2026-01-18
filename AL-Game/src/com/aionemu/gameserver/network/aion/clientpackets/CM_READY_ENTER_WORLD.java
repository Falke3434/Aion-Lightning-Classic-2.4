package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;

public class CM_READY_ENTER_WORLD extends AionClientPacket {

	@SuppressWarnings("unused")
	private int code;

	public CM_READY_ENTER_WORLD(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		code = readD();
	}

	@Override
	protected void runImpl() {

	}
}
