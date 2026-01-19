package com.aionemu.gameserver.services.player;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.AccountSielEnergyDAO;
import com.aionemu.gameserver.model.account.Account;
import com.aionemu.gameserver.model.account.AccountSielEnergy;
import com.aionemu.gameserver.model.account.SielEnergyType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_GAMEPASS_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;

import javolution.util.FastMap;

public class SielEnergyService {
	
	Map<Integer, Player> playerTask = new FastMap<Integer, Player>();

	public void onLogin(Player player) {
		Account account = player.getPlayerAccount();

		if (account.getMembership() == 2) {
			PacketSendUtility.sendPacket(player, new SM_GAMEPASS_INFO(3, player));
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_ALARM_PLAYTIME_CLASSIC_ACCOUNT_MEMBERSHIP((int) account.getMembershipExpire().getTime()));
		} else {
			if (player.getAccountSielEnergy() == null) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				cal.add(Calendar.HOUR, 24);
				Timestamp start = new Timestamp(System.currentTimeMillis());
				Timestamp end = new Timestamp(cal.getTimeInMillis());
				AccountSielEnergy sielEnergy = new AccountSielEnergy(SielEnergyType.TRIAL, start, end);
				player.setAccountSielEnergy(sielEnergy);
				PacketSendUtility.sendPacket(player, new SM_GAMEPASS_INFO(1, player));
				PacketSendUtility.sendPacket(player, new SM_GAMEPASS_INFO(1, player));
			}
		}
	}

	public void onRestart() {
	    AccountSielEnergyDAO dao = DAOManager.getDAO(AccountSielEnergyDAO.class);
	    dao.remove();
	}

	public void EndEffect(Player player) {
		AccountSielEnergy sielEnergy = player.getAccountSielEnergy();
		sielEnergy.end(player);
	}

	public static SielEnergyService getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		protected static final SielEnergyService instance = new SielEnergyService();
	}
}

class SielEnergyUpdateTask implements Runnable {
	
	private final int playerId;

	SielEnergyUpdateTask(int playerId) {
		this.playerId = playerId;
	}

	public void run() {
		Player player = World.getInstance().findPlayer(playerId);
		if (player != null)
			try {
				if (player.getAccountSielEnergy() != null && !player.getAccountSielEnergy().isUnderSielEnergy()) {
					SielEnergyService.getInstance().EndEffect(player);
					PacketSendUtility.sendPacket(player, new SM_GAMEPASS_INFO(1, player));
					PacketSendUtility.sendPacket(player, new SM_GAMEPASS_INFO(1, player));
					player.getKnownList().doUpdate();
				}
			} catch (Exception ex) {
		}
	}
}
