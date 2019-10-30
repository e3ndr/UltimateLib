/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import cf.e3ndr.UltimateLib.UltimateLib;

public class PluginUtil {
	/**
	 * Determines whether or not the server is running Bukkit.
	 * 
	 * @return boolean
	 */
	public final boolean isBukkit() {
		return UltimateLib.isBukkit();
	}
	
	/**
	 * Determines whether or not the server is running Bungee.
	 * 
	 * @return boolean
	 */
	public final boolean isBungee() {
		return UltimateLib.isBungee();
	}
	
	/**
	 * Determines whether or not the server is running Sponge.
	 * 
	 * @return boolean
	 */
	public final boolean isSponge() {
		return UltimateLib.isSponge();
	}
	
	/**
	 * Determines whether or not the server is running Nukkit.
	 * 
	 * @return boolean
	 */
	public final boolean isNukkit() {
		return UltimateLib.isNukkit();
	}
}
