/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Logging;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class BungeeLogger extends UltimateLogger {
	
	public BungeeLogger(String prefix) {
		super(prefix);
	}
	
	public UltimateLogger newInstance(String prefix) {
		return new BungeeLogger(prefix);
	}
	
	
	@Override
	public void println(Object obj) {
		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(this.prefix + String.valueOf(obj)));
	}
		
}