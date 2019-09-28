package cf.e3ndr.UltimateLib.Example;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;

public class Plugin extends UltimatePlugin {
	@Override
	protected void pluginEnable(UltimateLib lib) {
		this.getLogger().println("Hello I\'m the Example plugin wrapped under UltimateLib!");
		this.registerCommand("", new String[] {"test", "testplugin"}).setExecutor(new CommandTest());
	}
	
	@Override
	protected void pluginDisable(UltimateLib lib) {
		this.getLogger().println("Goodbye!");
	}
}
