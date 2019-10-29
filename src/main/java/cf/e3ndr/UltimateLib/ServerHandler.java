package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.WrappedOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class ServerHandler {
	private static boolean initialized;
	private HashMap<UUID, WrappedPlayer<?>> players;
	
	public ServerHandler() {
		if (initialized) {
			throw new IllegalStateException("UltimateLib Server Handler is already initalized!");
		} else {
			initialized = true;
		}
	}

	public void join(WrappedPlayer<?> player) {
		this.players.put(player.getUUID(), player);
	}

	public void leave(UUID uuid) {
		this.players.remove(uuid);
	}
	
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers() {
		return new ArrayList<>(this.players.values());
	}

	public WrappedOfflinePlayer getOfflinePlayer(String name) {
		for (WrappedPlayer<?> player : this.players.values()) {
			if (player.getName().equals(name)) return player;
		}
		
		return null;
	}

	public WrappedOfflinePlayer getOfflinePlayer(UUID uuid) {
		return this.players.get(uuid);
	}
}
