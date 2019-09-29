/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Logging;

import cn.nukkit.Server;

public class NukkitLogger extends UltimateLogger {
	
	public NukkitLogger(String prefix) {
		super(prefix);
	}
	
	public UltimateLogger newInstance(String prefix) {
		return new NukkitLogger(prefix);
	}
	
	
	@Override
	public void println(Object obj) {
		Server.getInstance().getConsoleSender().sendMessage(this.prefix + String.valueOf(obj));
	}
	
}
