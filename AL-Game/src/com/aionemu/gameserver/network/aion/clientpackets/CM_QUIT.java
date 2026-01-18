/**
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

package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUIT_RESPONSE;
import com.aionemu.gameserver.network.loginserver.LoginServer;
import com.aionemu.gameserver.services.player.PlayerLeaveWorldService;

/**
 * In this packets aion client is asking if may quit.
 * 
 * @author -Nemesiss-
 */
public class CM_QUIT extends AionClientPacket {

	/**
	 * Constructs new instance of <tt>CM_QUIT </tt> packet
	 *
	 * @param opcode
	 */
	public CM_QUIT(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {

	}

	@Override
	protected void runImpl() {
		
	}

}
