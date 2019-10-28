/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

/**
 * The Class BungeeOfflinePlayer.
 * @apiNote Doesn't work on Bungee.
 */
public class BungeeOfflinePlayer implements WrappedOfflinePlayer {
	
	/**
	 * Checks if is online.
	 *
	 * @return true, if is online
	 */
	@Override
	public boolean isOnline() {
		return false;
	}

	/**
	 * Checks for played before.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean hasPlayedBefore() {
		return true;
	}

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	@Override
	public UUID getUUID() {
		return null;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	@Override
	public WrappedPlayer<?> getPlayer() {
		return null;
	}

}
