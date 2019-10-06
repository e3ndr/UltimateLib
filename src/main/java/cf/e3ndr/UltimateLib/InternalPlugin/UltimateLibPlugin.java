/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.InternalPlugin;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;

public class UltimateLibPlugin extends UltimatePlugin {
	
	@Override
	protected void pluginEnable(UltimateLib lib) {
		this.registerCommand("UltimateLib.admin", new String[] {"ultimatelib", "ul"}).setExecutor(new CommandUltimateLib()).addHelp("ul", "plugin", "UltimateLib.admin").addHelp("ul", "info", "");
	}
	
	@Override
	protected void pluginDisable(UltimateLib lib) {}
	
}
