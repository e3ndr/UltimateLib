/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cn.nukkit.IPlayer;

public class NukkitOfflinePlayer implements WrappedOfflinePlayer {
	private IPlayer nukkit;
	
	public NukkitOfflinePlayer(IPlayer p) {
		this.nukkit = p;
	}
	
	@Override
	public boolean isOnline() {
		return this.nukkit.isOnline();
	}

	@Override
	public boolean hasPlayedBefore() {
		return this.nukkit.hasPlayedBefore();
	}

	@Override
	public UUID getUUID() {
		return this.nukkit.getUniqueId();
	}

	@Override
	public NukkitPlayer getPlayer() {
		return new NukkitPlayer(this.nukkit.getPlayer());
	}

}
