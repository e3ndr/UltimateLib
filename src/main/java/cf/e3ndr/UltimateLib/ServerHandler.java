package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.WrappedOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class ServerHandler implements Runnable {
	private static ServerHandler instance;
	private ArrayList<WrappedPlayer<?>> players = new ArrayList<>();
	private UltimateLibUtil util;
	public UltimateLogger logger;
	
	public ServerHandler(UltimateLibUtil util, UltimateLogger logger) {
		if (instance != null) {
			throw new IllegalStateException("UltimateLib Server Handler is already initalized!");
		} else {
			this.util = util;
			instance = this;
			this.logger = logger.clone();
			this.check();
			UltimateLib.getInstance().scheduleSyncTask(this, 100, 1200);
		}
	}
	
	public static ServerHandler unsafe() {
		return instance;
	}
	
	public void check() {
		for (WrappedPlayer<?> player : this.util.getOnlinePlayers()) {
			if (!this.players.contains(player)) {
				this.logger.printDebug("join " + player.getUUID());
				this.players.add(player);
			}
		}
		
		Iterator<WrappedPlayer<?>> it = this.players.iterator();
		while (it.hasNext()) {
			WrappedPlayer<?> player = it.next();
			if (!player.isOnline()) {
				this.logger.printDebug("leave " + player.getUUID());
				it.remove();
			}
		}
		
		this.logger.printDebug("check " + Arrays.toString(this.players.toArray()));
	}
	
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers() {
		this.check();
		return this.players;
	}
	
	public WrappedOfflinePlayer getOfflinePlayer(String name) {
		for (WrappedPlayer<?> player : this.players) {
			if (!player.isOnline()) {
				this.check();
				break;
			} else if (player.getName().equals(name)) {
				return player;
			}
		}
		
		return this.util.getOfflinePlayer(name);
	}
	
	public WrappedOfflinePlayer getOfflinePlayer(UUID uuid) {
		this.logger.printDebug("get " + uuid);
		for (WrappedPlayer<?> player : this.players) {
			if (!player.isOnline()) {
				this.check();
				break;
			} else if (player.getUUID().equals(uuid)) {
				return player;
			}
		}
		
		return this.util.getOfflinePlayer(uuid);
	}

	@Override
	public void run() {
		this.check();
	}
}
