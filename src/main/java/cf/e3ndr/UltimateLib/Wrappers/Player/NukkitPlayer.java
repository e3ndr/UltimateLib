/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Location.NukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cn.nukkit.Player;

public class NukkitPlayer implements WrappedPlayer {
	private Player nukkit;
	
	public NukkitPlayer(Player player) {
		this.nukkit = player;
	}
	
	@Override
	public boolean teleportPlayer(WrappedLocation wloc) {
		return this.nukkit.teleport(((NukkitLocation) wloc).getNukkit());
	}
	
	@Override
	public void sendMessage(String message) {
		this.nukkit.sendMessage(message);
	}
	
	@Override
	public WrappedLocation getLocation() {
		return new NukkitLocation(this.nukkit.getLocation());
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.nukkit.hasPermission(permission);
	}
	
	public Player getNukkitPlayer() {
		return this.nukkit;
	}

	@Override
	public String getName() {
		return this.nukkit.getName();
	}

	@Override
	public UUID getUUID() {
		return this.nukkit.getUniqueId();
	}

	@Override
	public GameMode getMode() {
		return GameMode.fromInt(this.nukkit.getGamemode());
	}

	@Override
	public void setMode(GameMode gamemode) {
		this.nukkit.setGamemode(gamemode.getInt());
	}
}
