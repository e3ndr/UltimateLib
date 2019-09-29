/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.BukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public class BukkitPlayer implements WrappedPlayer {
	private Player bukkit;
	
	public BukkitPlayer(Player player) {
		this.bukkit = player;
	}
	
	@Override
	public boolean teleportPlayer(WrappedLocation wloc) {
		return this.bukkit.teleport(((BukkitLocation) wloc).getBukkit());
	}
	
	@Override
	public void sendMessage(String message) {
		this.bukkit.sendMessage(message);
	}
	
	@Override
	public WrappedLocation getLocation() {
		return new BukkitLocation(this.bukkit.getLocation());
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.bukkit.hasPermission(permission);
	}
	
	public Player getBukkitPlayer() {
		return this.bukkit;
	}

	@Override
	public String getName() {
		return this.bukkit.getName();
	}

	@Override
	public UUID getUUID() {
		return this.bukkit.getUniqueId();
	}

	@Override
	public GameMode getMode() {
		return GameMode.fromString(this.bukkit.getGameMode().toString());
	}

	@Override
	public void setMode(GameMode gamemode) {
		this.bukkit.setGameMode(org.bukkit.GameMode.valueOf(gamemode.toString()));
	}
	
}
