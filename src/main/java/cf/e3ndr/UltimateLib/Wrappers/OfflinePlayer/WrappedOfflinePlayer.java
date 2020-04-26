/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer;

import java.util.UUID;

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
	 * Gets the player's name.
	 * 
	 * @apiNote Doesn't work on Bungee.
	 * 
	 * @return the name
	 */
	public String getName();
	
	default String asString() {
		return "[OFFLINE:" + this.getUUID() + "]";
	}
}
