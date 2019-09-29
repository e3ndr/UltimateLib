/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

import java.util.List;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class UltimateCommand {
	protected UltimatePlugin plugin;
	protected CommandExec exec;
	protected String[] aliases;
	protected String basePerm = "";
	
	public UltimateCommand(UltimatePlugin plugin, String basePerm, String... names) {
		this.plugin = plugin;
		this.aliases = names;
		this.basePerm = basePerm;
	}
	
	public final UltimateCommand setExecutor(CommandExec exec) {
		this.exec = exec;
		return this;
	}
	
	public final boolean execute(WrappedConsole executor, String alias, String[] args) {
		// TODO arguments & custom help page
		return this.exec.onCommand(executor, alias, args);
	}
	
	public final List<String> tabComplete(WrappedConsole executor, String alias, String[] args) {
		return this.exec.onTabComplete(executor, alias, args);
	}
	
	public final UltimatePlugin getPlugin() {
		return this.plugin;
	}
	
	public final String getBasePerm() {
		return this.basePerm;
	}
	
	public final String[] getAliases() {
		return this.aliases;
	}
	
}
