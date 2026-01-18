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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author pixfid
 * @modified Magenik , Kev
 */
public class SM_ACCOUNT_ACCESS_PROPERTIES extends AionServerPacket {

	public SM_ACCOUNT_ACCESS_PROPERTIES() {
	}

	private boolean isGM;

	public SM_ACCOUNT_ACCESS_PROPERTIES(boolean isGM) {
		this.isGM = isGM;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeH(this.isGM ? 3 : 0); // 3 = GM-Panel(Shift+F1)
		writeH(0);
		writeD(1536);
		writeD(0);
		writeD(0);
		writeD(0x00);
		writeC(0);
		writeD(0);
		writeD(0);
	}
}