/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Logging;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class ReturningLogger extends UltimateLogger {
	private WrappedConsole executor;
	
	public ReturningLogger(WrappedConsole executor, String name) {
		super(UltimateLib.prefix.replace("{0}", name));
		this.executor = executor;
	}
	
	@Override
	public void println(Object obj) {
		executor.sendMessage(prefix + obj);
	}
}
