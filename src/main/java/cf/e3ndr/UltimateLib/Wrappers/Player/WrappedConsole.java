/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;

/**
 * The Interface WrappedConsole.
 */
public interface WrappedConsole {
	
	/**
	 * Send message.
	 *
	 * @param message the message
	 */
	public void sendMessage(String message);
	
	/**
	 * Checks for perm.
	 *
	 * @param permission the permission
	 * @return true, if successful
	 */
	public boolean hasPerm(String permission);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Checks if is console.
	 *
	 * @return true, if is console
	 */
	public boolean isConsole();
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public WrappedPlayer<?> getPlayer();
	
	/**
	 * Send message.
	 *
	 * @param message the message
	 * @param autoColor whether or not to transform colors automatically
	 */
	default void sendMessage(String message, boolean autoColor) {
		if (autoColor) {
			this.sendMessage(UltimateLogger.transformColor(message));
		} else {
			this.sendMessage(message);
		}
	}
}
