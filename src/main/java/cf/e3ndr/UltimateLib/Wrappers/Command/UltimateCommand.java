/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class UltimateCommand {
	protected UltimatePlugin plugin;
	protected CommandExec exec;
	protected String[] aliases;
	protected ArrayList<HelpArgument> helpArguments = new ArrayList<HelpArgument>();
	
	public UltimateCommand(UltimatePlugin plugin, String... names) {
		this.plugin = plugin;
		this.aliases = names;
	}
	
	public final UltimateCommand setExecutor(CommandExec exec) {
		this.exec = exec;
		return this;
	}
	
	public final UltimateCommand addHelp(String alias, String argument, String permission) {
		return this.addHelp(new HelpArgument(alias, argument, permission));
	}
	
	public final UltimateCommand addHelp(HelpArgument helpArgument) {
		this.helpArguments.add(helpArgument);
		return this;
	}
	
	public final boolean execute(WrappedConsole executor, String alias, String[] args) {
		if ((this.helpArguments.size()) > 0 && (args.length > 0) && args[0].equalsIgnoreCase("help") && this.plugin.isEnabled()) {
			this.help(executor, alias);
		} else {
			this.exec.onCommand(executor, alias, args);
		}
		
		return true;
	}
	
	public final void help(WrappedConsole executor, String alias) {
		StringBuilder sb = new StringBuilder(this.plugin.getDescription().getColor());
		sb.append(this.plugin.getName());
		sb.append("&r\n");
		
		for (HelpArgument ha : this.helpArguments) {
			sb.append("    ");
			sb.append(executor.hasPerm(ha.getPermission()) ? "&c" : "&a");
			sb.append((ha.getAlias() == null) ? alias : ha.getAlias());
			sb.append(" ");
			sb.append(ha.getArgument());
		}
		
		executor.sendMessage(UltimateLogger.transformColor(sb.toString()));
	}

	public final List<String> tabComplete(WrappedConsole executor, String alias, String[] args) {
		return this.exec.onTabComplete(executor, alias, args);
	}
	
	public final UltimatePlugin getPlugin() {
		return this.plugin;
	}
	
	public final String[] getAliases() {
		return this.aliases;
	}
	
}
