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
	protected String basePerm = "";
	protected ArrayList<HelpArgument> helpArguments = new ArrayList<HelpArgument>();
	
	public UltimateCommand(UltimatePlugin plugin, String basePerm, String... names) {
		this.plugin = plugin;
		this.aliases = names;
		this.basePerm = basePerm;
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
		if ((this.helpArguments.size()) > 0 && (args.length > 0) && args[0].equalsIgnoreCase("help") && executor.hasPerm(basePerm) && this.plugin.isEnabled()) {
			this.help(executor, alias);
		} else {
			this.exec.onCommand(executor, alias, args);
		}
		
		return true;
	}
	
	public final void help(WrappedConsole executor, String alias) {
		final String commandBody = "{p}&r\n{c}".replace("{p}", this.plugin.getDescription().getColor() + this.plugin.getName());
		final String arg = "{color}/{alias} {arg}";
		String content = "";
		
		for (HelpArgument ha : this.helpArguments) {
			String color = executor.hasPerm(ha.getPermission()) ? "&c" : "&a";
			String label = ha.getAlias() == null ? alias : ha.getAlias();
			content += "    " + arg.replace("{alias}", label).replace("{color}", color).replace("{arg}", ha.getArgument()) + "\n";
		}
		
		executor.sendMessage(UltimateLogger.transformColor(commandBody.replace("{c}", content)));
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
