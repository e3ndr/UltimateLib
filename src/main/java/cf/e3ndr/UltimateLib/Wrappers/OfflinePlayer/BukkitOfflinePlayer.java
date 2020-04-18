/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

import org.bukkit.OfflinePlayer;

public class BukkitOfflinePlayer implements WrappedOfflinePlayer {
	private OfflinePlayer bukkit;
	
	public BukkitOfflinePlayer(OfflinePlayer player) {
		this.bukkit = player;
	}
	
	@Override
	public boolean isOnline() {
		return this.bukkit.isOnline();
	}
	
	@Override
	public boolean hasPlayedBefore() {
		return this.bukkit.hasPlayedBefore();
	}
	
	@Override
	public UUID getUUID() {
		return this.bukkit.getUniqueId();
	}
	
}
