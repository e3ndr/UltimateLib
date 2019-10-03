/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.InternalPlugin;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Logging.ReturningLogger;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class CommandUltimateLib implements CommandExec {
	private static final String com = ""
			+ "&d&m------&r&5UltimateLib&d&m------\n"
			+ "{}"
			+ "\n&r"
			+ "&d&m---------------------";
	private static final String tab = "    ";
	
	@Override
	public boolean onCommand(WrappedConsole executor, String alias, String[] args) {
		if (!executor.hasPerm("UltimateLib.admin")) {
			executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4 You don\'t have permission to do this!"));
			return true;
		}
		
		(new Thread() {
			@Override
			public void run() {
				if (args.length == 0) {
					String s = "";
					
					for (UltimatePlugin p : UltimateLib.getPlugins()) {
						if (p.isEnabled()) {
							int reg = p.getCommands().size();
							switch (reg) {
								case 0: s += p.getDescription().getColor() + "&o" + p.getName() + "&r doesn\'t have any commands registered.\n"; break;
								case 1: s += p.getDescription().getColor() + "&o" + p.getName() + "&r has " + reg + " command registered.\n"; break;
								default: s += p.getDescription().getColor() + "&o" + p.getName() + "&r has " + reg + " commands registered.\n"; break;
							}
						} else {
							s += "&c&o" + p.getName() + "&r&4 is disabled.\n";
						}
					}
					// TODO multiple pages
					executor.sendMessage(UltimateLogger.transformColor(com.replace("{}", s)));
				} else if (args.length == 1) {
					for (UltimatePlugin p : UltimateLib.getPlugins()) {
						if (p.getName().equalsIgnoreCase(args[0])) {
							String s = p.getDescription().getColor() + "&o" + p.getName() + "&r\n";
							
							if (p.getCommands().size() > 0) {
								s += "\n&aCommands:&r\n&2";
								for (UltimateCommand c : p.getCommands()) {
									s += tab + c.getAliases()[0] + " (";
									for (String n : c.getAliases()) s += n + ", ";
									s = s.substring(0, s.length() - 2) + ")\n";
								}
							} else {
								s += "&cNo commands registered.\n";
							}
							
							executor.sendMessage(UltimateLogger.transformColor(com.replace("{}", s)));
							return;
						}
					}
					
					executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Cannot find plugin \"&c" + args[0] + "&5\""));
				} else if (args.length == 2) {
					for (UltimatePlugin p : UltimateLib.getPlugins()) {
						if (p.getName().equalsIgnoreCase(args[0])) {
							if (p.isEnabled()) {
								if (p.getName().equalsIgnoreCase("UltimateLibPlugin") && args[1].equalsIgnoreCase("disable")) {
									executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " UltimateLibPlugin cannot be disabled as it\'s internal and eternal."));
									return;
								} else if (args[1].equalsIgnoreCase("disable")) {
									p.close();
									executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &2Sucessfully disabled \"&a" + args[0] + "&5\""));
								//} else if (args[1].equalsIgnoreCase("reload")) {
								//	p.reload(); // TODO Not yet
								//	executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &2Sucessfully reloaded \"&a" + args[0] + "&5\""));
								} else {
									executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Unknown argument \"&c" + args[1] + "&5\""));
								}
							} else {
								if (args[1].equalsIgnoreCase("enable")) {
									p.init(new ReturningLogger(executor, "UltimateLib"));
								} else {
									executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Unknown argument \"&c" + args[1] + "&5\""));
								}
							}
							
							return;
						}
					}
					
					executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Cannot find plugin \"&c" + args[0] + "&5\""));
				}
			}
		}).start(); // Threaded to prevent hangs, useful here but in your plugin it probably isn't that great of an idea. (Depends on situation)
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(WrappedConsole executor, String alias, String[] args) {
		ArrayList<String> ret = new ArrayList<String>();
		
		if (args.length == 1) {
			for (UltimatePlugin p : UltimateLib.getPlugins()) ret.add(p.getName());
		} else if (args.length == 2) {
			for (UltimatePlugin p : UltimateLib.getPlugins()) {
				if (p.getName().equalsIgnoreCase(args[0])) {
					if (p.isEnabled()) {
						ret.add("disable");
					} else {
						// ret.add("reload");
						ret.add("enable");
					}
				}
			}
		}
		
		return ret;
	}
}
