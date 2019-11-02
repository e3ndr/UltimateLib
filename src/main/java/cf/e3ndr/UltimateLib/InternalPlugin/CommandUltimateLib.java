/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.InternalPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cf.e3ndr.UltimateLib.ServerHandler;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Logging.ReturningLogger;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.PluginUtil;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class CommandUltimateLib extends PluginUtil implements CommandExec {
	private static final String com = "" + "&5UltimateLib&r\n" + "{}";
	private static final String tab = "    ";
	
	@Override
	public void onCommand(WrappedConsole executor, String alias, String[] args) {
		if (!executor.hasPerm("UltimateLib.admin")) {
			executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &5You don\'t have permission to do this!"));
			return;
		}
		
		(new Thread() {
			@Override
			public void run() {
				StringBuilder sb = new StringBuilder();
				
				if ((args.length > 0) && executor.hasPerm("UltimateLib.admin") && args[0].equalsIgnoreCase("handler")) {
					sb.append(tab);
					sb.append(com.replace("{}", "&cServerHandler"));
					sb.append("\n");
					
					if (args.length > 1 && args[1].equalsIgnoreCase("check")) {
						sb.append(tab);
						sb.append(tab);
						sb.append("&rRefreshed.\n");
						ServerHandler.unsafe().check();
					} if (args.length > 1 && args[1].equalsIgnoreCase("debug")) {
						boolean value = !ServerHandler.unsafe().logger.getDebug();
						ServerHandler.unsafe().logger.setDebug(value);
						sb.append(tab);
						sb.append(tab);
						sb.append("&rToggled debug to &7");
						sb.append(value);
						sb.append("&r.\n");
					}
					
					sb.append(tab);
					sb.append(tab);
					sb.append("&aRegisteredPlayers: &2");
					sb.append(UltimateLib.getInstance().getOnlinePlayers().size());
				} else if ((args.length > 0) && executor.hasPerm("UltimateLib.admin") && (args[0].equalsIgnoreCase("plugins") || args[0].equalsIgnoreCase("plugin"))) {
					if (args.length == 1) {
						for (UltimatePlugin p : UltimateLib.getPlugins()) {
							sb.append(tab);
							if (p.isEnabled()) {
								sb.append(p.getDescription().getColor());
								sb.append("&o");
								sb.append(p.getName());
								
								int reg = p.getCommands().size();
								switch (reg) {
									case 0:
										sb.append("&r doesn\'t have any commands registered.\n");
										break;
									case 1:
										sb.append("&r has 1 command registered.\n");
										break;
									default:
										sb.append("&r has " );
										sb.append(reg);
										sb.append(" commands registered.\n");
										break;
								}
							} else {
								sb.append("&c&o");
								sb.append(p.getName());
								sb.append("&r&4 is disabled.\n");
							}
						}
					} else if (args.length == 2) {
						for (UltimatePlugin p : UltimateLib.getPlugins()) {
							if (p.getName().equalsIgnoreCase(args[1])) {
								sb.append(tab);
								sb.append(p.getDescription().getColor());
								sb.append("&o");
								sb.append(p.getName());
								sb.append("&r\n");
								
								if (p.getCommands().size() > 0) {
									sb.append("\n");
									sb.append(tab);
									sb.append(tab);
									sb.append("&aCommands:&r\n&2");
									
									for (UltimateCommand c : p.getCommands()) {
										sb.append(tab);
										sb.append(tab);
										sb.append(tab);
										sb.append(c.getAliases()[0]);
										sb.append(" (");
										sb.append(Arrays.toString(c.getAliases()).replace("[", "").replace("]", ""));
										sb.append(")\n");
									}
								} else {
									sb.append(tab);
									sb.append("&cNo commands registered.\n");
								}
							}
						}
						
						executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Cannot find plugin \"&c" + args[0] + "&5\""));
					} else if (args.length == 3) {
						for (UltimatePlugin p : UltimateLib.getPlugins()) {
							if (p.getName().equalsIgnoreCase(args[1])) {
								if (p.isEnabled()) {
									if (p.getName().equalsIgnoreCase("UltimateLibPlugin") && args[2].equalsIgnoreCase("disable")) {
										executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " UltimateLibPlugin cannot be disabled as it\'s internal and eternal."));
										return;
									} else if (args[2].equalsIgnoreCase("disable")) {
										UltimateLib.getInstance().callSyncTask(() -> p.close());
										executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &2Sucessfully disabled \"&a" + args[0] + "&5\""));
									} else {
										executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Unknown argument \"&c" + args[1] + "&5\""));
									}
								} else {
									if (args[2].equalsIgnoreCase("enable")) {
										UltimateLib.getInstance().callSyncTask(() -> p.init(new ReturningLogger(executor, "UltimateLib")));
									} else {
										executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Unknown argument \"&c" + args[1] + "&5\""));
									}
								}
							}
						}
						
						executor.sendMessage(UltimateLogger.transformColor(UltimateLib.prefix.replace("{0}", "UltimateLib") + " &4Cannot find plugin \"&c" + args[0] + "&5\""));
						return;
					}
				} else {
					sb.append(com.replace("{}", tab + "&r&aVersion " + UltimateLib.getVersion()));
				}
				
				executor.sendMessage(sb.toString(), true);
			}
		}).start(); // Threaded to prevent hangs, useful here but in your plugin it probably isn't
					// that great of an idea. (Depends on situation)
		
		return;
	}
	
	@Override
	public List<String> onTabComplete(WrappedConsole executor, String alias, String[] args) {
		ArrayList<String> ret = new ArrayList<String>();
		
		if (executor.hasPerm("UltimateLib.admin")) {
			if (args.length == 1) {
				ret.add("plugin");
				ret.add("handler");
			} else if (args.length > 1) {
				if (args[0].equalsIgnoreCase("plugin") || args[0].equalsIgnoreCase("plugins")) { // Separated for
																									// tidiness :)
					if (args.length == 2) {
						for (UltimatePlugin p : UltimateLib.getPlugins()) ret.add(p.getName());
					} else if (args.length == 3) {
						for (UltimatePlugin p : UltimateLib.getPlugins()) {
							if (p.getName().equalsIgnoreCase(args[1])) {
								if (p.isEnabled()) {
									ret.add("disable");
								} else {
									ret.add("enable");
								}
							}
						}
					}
				} else if (args[0].equalsIgnoreCase("handler")) {
					ret.add("check");
					ret.add("debug");
				}
			}
		} else {
			if (args.length == 1) {
				ret.add("info");
			}
		}
		
		return ret;
	}
}
