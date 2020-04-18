/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Util.IComparable;

/**
 * The Interface WrappedConsole.
 */
public interface WrappedConsole extends IComparable {
	
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
	default String getName() {
		return "CONSOLE";
	}
	
	/**
	 * Checks if is console.
	 *
	 * @return true, if is console
	 */
	default boolean isConsole() {
		return true;
	}
	
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
	
	@Override
	default boolean compareEqual(Object o) {
		if (o instanceof WrappedConsole) {
			return this.getName().equals(((WrappedConsole) o).getName());
		}
		
		return false;
	}
	
	@Override
	default String asString() {
		return "[CONSOLE:NULL]";
	}
}
