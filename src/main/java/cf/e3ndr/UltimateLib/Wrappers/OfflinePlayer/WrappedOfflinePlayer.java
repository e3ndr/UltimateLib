/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public interface WrappedOfflinePlayer {

	/**
	 * Checks whether or not the player is online.
	 * 
	 * @apiNote Doesn't work on Bungee.
	 * 
	 * @return true, if online
	 */
	public boolean isOnline();
	
	/**
	 * Checks to see if the player has played before.
	 * 
	 * @apiNote Assumes true on Bungee.
	 * 
	 * @return true, if player has played before
	 */
	public boolean hasPlayedBefore();
	
	/**
	 * Gets the player's uuid.
	 * 
	 * @apiNote Doesn't work on Bungee.
	 * 
	 * @return the uuid
	 */
	public UUID getUUID();
	
	/**
	 * Get's the online player object.
	 * 
	 * @apiNote Doesn't work on Bungee.
	 * 
	 * @return the player;
	 */
	public WrappedPlayer<?> getPlayer();
}
