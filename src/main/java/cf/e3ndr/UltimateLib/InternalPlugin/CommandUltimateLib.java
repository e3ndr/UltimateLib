/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.InternalPlugin;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class CommandUltimateLib implements CommandExec {
	private static final String com = ""
			+ "&d&m------&r&5UltimateLib&d&m------\n"
			+ "{}"
			+ "\n&r"
			+ "&d&m-----------------------";
	private static final String tab = "    ";
	
	@Override
	public boolean onCommand(WrappedPlayer executor, String alias, String[] args) {
		(new Thread() {
			@Override
			public void run() {
				if (args.length == 0) {
					String s = "";
					
					for (UltimatePlugin p : UltimateLib.getPlugins()) {
						int reg = p.getCommands().size();
						
						switch (reg) {
							case 0: s += p.getColor() + p.getName() + "&r doesn\'t have any commands registered.\n"; break;
							case 1: s += p.getColor() + p.getName() + "&r has " + reg + " command registered.\n"; break;
							default: s += p.getColor() + p.getName() + "&r has " + reg + " commands registered.\n"; break;
						}
					}
					// TODO pages
					executor.sendMessage(UltimateLogger.transformColor(com.replace("{}", s)));
				} else if (args.length == 1) {
					for (UltimatePlugin p : UltimateLib.getPlugins()) {
						if (p.getName().equalsIgnoreCase(args[0])) {
							String s = p.getColor() + "&o" + p.getName() + "&r\n";
							
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
					executor.sendMessage(UltimateLogger.transformColor(com.replace("{}", "&5Cannot find plugin \"&c" + args[0] + "&5\"")));
				}
			}
		}).start(); // Threaded to prevent hangs, useful here but in your plugin it probably isn't that great of an idea. (Depends on situation)
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(WrappedPlayer executor, String alias, String[] args) {
		ArrayList<String> ret = new ArrayList<String>();
		
		if (args.length == 1) for (UltimatePlugin p : UltimateLib.getPlugins()) ret.add(p.getName());
		
		return ret;
	}
}
