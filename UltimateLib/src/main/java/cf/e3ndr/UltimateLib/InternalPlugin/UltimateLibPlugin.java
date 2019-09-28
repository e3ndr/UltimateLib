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
		this.getLogger().println("Hello I\'m the Test plugin wrapped under UltimateLib!");
		this.registerCommand("", new String[] {"ultimatelib", "ul"}).setExecutor(new PluginExec());
		
	}
	
	@Override
	protected void pluginDisable(UltimateLib lib) {
		this.getLogger().println("Goodbye!");
		
	}
	
}