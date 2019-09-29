package cf.e3ndr.UltimateLib.Wrappers.Player;

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
		return nukkit.getName();
	}
}
