package com.aionemu.gameserver.network.aion.clientpackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;

public class CM_UNK_0xF4 extends AionClientPacket {

	private int code;
	private String message;

	private static Logger log = LoggerFactory.getLogger(CM_UNK_0xF4.class);

	public CM_UNK_0xF4(int opcode, AionConnection.State state, AionConnection.State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		this.code = readC();
		this.message = readS(getRemainingBytes());
	}

	@Override
	protected void runImpl() {
		log.info("[CM_UNK_0xF4] : code : " + this.code + " Message -> " + this.message);
	}
}