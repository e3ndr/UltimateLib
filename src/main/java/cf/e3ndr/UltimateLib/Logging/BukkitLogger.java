/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Logging;

import org.bukkit.Bukkit;

public class BukkitLogger extends UltimateLogger {
	
	public BukkitLogger(String prefix) {
		super(prefix);
	}
	
	public UltimateLogger newInstance(String prefix) {
		return new BukkitLogger(prefix);
	}
	
	@Override
	public void println(Object obj) {
		Bukkit.getServer().getConsoleSender().sendMessage(this.prefix + String.valueOf(obj));
	}
	
}