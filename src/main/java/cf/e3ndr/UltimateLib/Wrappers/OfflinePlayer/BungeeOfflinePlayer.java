/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

/**
 * The Class BungeeOfflinePlayer.
 * 
 * @apiNote Doesn't work on Bungee.
 */
public class BungeeOfflinePlayer implements WrappedOfflinePlayer {
	
	@Override
	public boolean isOnline() {
		return false;
	}
	
	@Override
	public boolean hasPlayedBefore() {
		return true;
	}
	
	@Override
	public UUID getUUID() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
	
}
