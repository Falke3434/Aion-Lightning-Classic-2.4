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
package com.aionemu.gameserver.network.aion.serverpackets;

import java.util.Calendar;

import com.aionemu.gameserver.configs.main.GSConfig;
import com.aionemu.gameserver.configs.main.MembershipConfig;
import com.aionemu.gameserver.configs.network.IPConfig;
import com.aionemu.gameserver.configs.network.NetworkConfig;
import com.aionemu.gameserver.network.NetworkController;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.ChatService;

/**
 * @author -Nemesiss- CC fix
 * @modified by Novo, cura
 */
public class SM_VERSION_CHECK extends AionServerPacket {

	private int version;
	private int characterLimitCount;

	/**
	 * Related to the character creation mode
	 */
	private final int characterFactionsMode;
	private final int characterCreateMode;

	/**
	 * @param chatService
	 */
	public SM_VERSION_CHECK(int version) {
		this.version = version;
		if (MembershipConfig.CHARACTER_ADDITIONAL_ENABLE != 10
				&& MembershipConfig.CHARACTER_ADDITIONAL_COUNT > GSConfig.CHARACTER_LIMIT_COUNT) {
			characterLimitCount = MembershipConfig.CHARACTER_ADDITIONAL_COUNT;
		} else {
			characterLimitCount = GSConfig.CHARACTER_LIMIT_COUNT;
		}

		characterLimitCount *= NetworkController.getInstance().getServerCount();

		if (GSConfig.CHARACTER_FACTIONS_MODE < 0 || GSConfig.CHARACTER_FACTIONS_MODE > 2)
			characterFactionsMode = 0;
		else
			characterFactionsMode = GSConfig.CHARACTER_FACTIONS_MODE;

		if (GSConfig.CHARACTER_CREATE_MODE < 0 || GSConfig.CHARACTER_CREATE_MODE > 3)
			characterCreateMode = 0;
		else
			characterCreateMode = GSConfig.CHARACTER_CREATE_MODE * 0x04;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		// Aion classic = 192
		if (version < 192) {
			// Send wrong client version
			writeC(0x02);
			return;
		}
		writeC(0x00);
		writeC(NetworkConfig.GAMESERVER_ID);
		writeD(220922);
		writeD(220922);
		writeD(0);
		writeD(220922);
		writeD((int) (Calendar.getInstance().getTimeInMillis() / 1000));
		writeC(0);
		writeC(GSConfig.SERVER_COUNTRY_CODE);// country code;
		writeC(0);
		int serverMode = (characterLimitCount * 16) | characterFactionsMode;
		writeC(128);
		writeD((int) (Calendar.getInstance().getTimeInMillis() / 1000));

		writeH(1);// its loop size
		// for... chat servers?
		{
			writeC(0x00);// spacer
			writeD(921345);// unk
			writeH(511);// unk
			writeC(0);// unk
			writeC(0);// unk
			// if the correct ip is not sent it will not work
			writeB(IPConfig.getDefaultAddress());
			writeH(ChatService.getPort());
		}
	}
}
