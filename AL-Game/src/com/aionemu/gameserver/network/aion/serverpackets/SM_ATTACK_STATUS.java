/**
 * This file is part of aion-unique <aion-unique.smfnew.com>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.configs.main.GSConfig;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author alexa026
 * @author ATracer
 * @author kecimis
 */
public class SM_ATTACK_STATUS extends AionServerPacket {

	private Creature attacked;
	private Creature attacker;
	private TYPE type;
	private int skillId;
	private int value;
	private int logId;

	public static enum TYPE {
		NATURAL_HP(3), USED_HP(4), // when skill uses hp as cost parameter
		REGULAR(5), ABSORBED_HP(6), DAMAGE(7), HP(7), PROTECTDMG(8), DELAYDAMAGE(10), FALL_DAMAGE(17), HEAL_MP(
				19), ABSORBED_MP(
						20), MP(21), NATURAL_MP(22), ATTACK(23), FP_RINGS(24), FP(25), NATURAL_FP(26), NATURAL_FP2(27);

		private int value;

		private TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public static enum LOG {
		SPELLATK(1), HEAL(3), MPHEAL(4), SKILLLATKDRAININSTANT(23), SPELLATKDRAININSTANT(24), POISON(25), BLEED(
				26), PROCATKINSTANT(92), DELAYEDSPELLATKINSTANT(
						95), SPELLATKDRAIN(130), FPHEAL(133), REGULARHEAL(170), REGULAR(173), ATTACK(172);

		private int value;

		private LOG(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public SM_ATTACK_STATUS(Creature attacked, Creature attacker, TYPE type, int skillId, int value, LOG log) {
		this.attacked = attacked;
		this.attacker = attacker;
		this.type = type;
		this.skillId = skillId;
		this.value = value;
		this.logId = log.getValue();
	}

	public SM_ATTACK_STATUS(Creature attacked, Creature attacker, TYPE type, int skillId, int value) {
		this(attacked, attacker, type, skillId, value, LOG.REGULAR);
	}

	public SM_ATTACK_STATUS(Creature attacked, Creature attacker, int value) {
		this(attacked, attacker, TYPE.REGULAR, 0, value, LOG.REGULAR);
	}

	/**
	 * {@inheritDoc} ddchcc
	 */

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(this.attacked.getObjectId());
		switch (this.type) {
		case DAMAGE:
		case DELAYDAMAGE:
			writeD(-this.value);
			break;
		default:
			writeD(this.value);
		}
		writeC(this.type.getValue());
		if ((this.type.getValue() == 19) || (this.type.getValue() == 20) || (this.type.getValue() == 21)
				|| (this.type.getValue() == 22)) {
			writeC(this.attacked.getLifeStats().getMpPercentage());
		} else {
			writeC(this.attacked.getLifeStats().getHpPercentage());
		}
		writeH(0);
		writeH(GSConfig.SERVER_COUNTRY_CODE == 0 ? 178 : 173);
	}
}
