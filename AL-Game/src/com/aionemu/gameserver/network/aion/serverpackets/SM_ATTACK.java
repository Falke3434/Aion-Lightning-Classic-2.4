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

import java.util.List;

import com.aionemu.gameserver.controllers.attack.AttackResult;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author -Nemesiss-, Sweetkr
 */
public class SM_ATTACK extends AionServerPacket {

	private int attackno;
	private int time;
	private int type;
	private List<AttackResult> attackList;
	private Creature attacker;
	private Creature target;

	public SM_ATTACK(Creature attacker, Creature target, int attackno, int time, int type,
			List<AttackResult> attackList) {
		this.attacker = attacker;
		this.target = target;
		this.attackno = attackno;// empty
		this.time = time;// empty
		this.type = type;// empty
		this.attackList = attackList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(attacker.getObjectId());
		writeC(attackno); // unknown
		writeH(time); // unknown
		writeC(type); // 0, 1, 2
		writeC(1); // unk 2.7
		writeD(target.getObjectId());

		int attackerMaxHp = attacker.getLifeStats().getMaxHp();
		int attackerCurrHp = attacker.getLifeStats().getCurrentHp();
		int targetMaxHp = target.getLifeStats().getMaxHp();
		int targetCurrHp = target.getLifeStats().getCurrentHp();

		writeC(100 * targetCurrHp / targetMaxHp); // target %hp
		writeC(100 * attackerCurrHp / attackerMaxHp); // attacker %hp

		// TODO refactor attack controller
		switch (attackList.get(0).getAttackStatus().getId()) // Counter skills
		{
		case 196: // case CRITICAL_BLOCK
		case 4: // case BLOCK
		case 5:
		case 213:
			writeD(32);
			break;
		case 194: // case CRITICAL_PARRY
		case 2: // case PARRY
		case 3:
		case 211:
			writeD(64);
			break;
		case 192: // case CRITICAL_DODGE
		case 0: // case DODGE
		case 1:
		case 209:
			writeD(128);
			break;
		case 198: // case PHYSICAL_CRITICAL_RESIST
		case 6: // case RESIST
		case 7:
		case 215:
			writeD(256);
			break;
		default:
			writeD(0);
			break;

		// setting counter skill from packet to have the best synchronization of time
		// with client
		}
		if (target instanceof Player) {
			if (attackList.get(0).getAttackStatus().isCounterSkill()) {
				((Player) target).setLastCounterSkill(attackList.get(0).getAttackStatus());
			}
		}
		writeC(attackList.size());
		for (AttackResult attack : attackList) {
			writeD(attack.getDamage());
			writeC(attack.getAttackStatus().getId());

			byte shieldType = (byte) attack.getShieldType();
			writeC(shieldType);

			/**
			 * shield Type: 1: reflector 2: normal shield 8: protect effect (ex. skillId:
			 * 417 Bodyguard) TODO find out 4
			 */
			switch (shieldType) {
			case 0:
			case 2:
				break;
			case 8:
			case 10:
				writeD(attack.getProtectorId()); // protectorId
				writeD(attack.getProtectedDamage()); // protected damage
				writeD(attack.getProtectedSkillId()); // skillId
				break;
			default:
				writeD(attack.getProtectorId()); // protectorId
				writeD(attack.getProtectedDamage()); // protected damage
				writeD(attack.getProtectedSkillId()); // skillId
				writeD(attack.getReflectedDamage()); // reflect damage
				writeD(attack.getReflectedSkillId()); // skill id
				break;
			}
		}
		writeC(0);
		writeB("00000A00");
	}
}
