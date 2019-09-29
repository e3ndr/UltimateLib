/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.PluginLoader;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/**
 * The main class for UltimateLib.
 */
public class UltimateLib {
	
	/** The instance of the current UltimateLib. */
	public static UltimateLib instance;
	public static final String prefix = "&7[&5&l{0}&7]";
	private static ServerType type;
	private UltimateLibUtil util;
	private UltimateLogger logger;
	
	
	public UltimateLib(UltimateLibUtil util, UltimateLogger logger, String utiltype) {
		long start = System.currentTimeMillis();
		
		type = ServerType.fromString(utiltype);
		this.logger = logger;
		this.util = util;
		instance = this;
		
		this.logger.println("Hello world!");
		eventLogger = this.logger.newInstance(prefix.replace("{0}", "UltimateLib &8- &dPluginFramework"));
		(new PluginLoader(eventLogger)).run();
		
		this.logger.println("Done! Took " + (System.currentTimeMillis() - start) + "ms");
	}

	private static UltimateLogger eventLogger;
	private ArrayList<UltimatePlugin> plugins = new ArrayList<UltimatePlugin>();
	public static void registerPlugin(String name, String colorCode, UltimatePlugin plugin) {
		for (UltimatePlugin p : instance.plugins) {
			if (p.getName().equalsIgnoreCase(name)) {
				eventLogger.println(UltimateLogger.transformColor("&5 Plugin \"&c" + name + "&5\" already loaded in the server! Did you forget to delete old jars?"));
				return;
			}
		}
		instance.plugins.add(plugin.init(name, colorCode, eventLogger));
	}
	
	/**
	 * Gets all registered plugins.
	 * 
	 * @return a list of plugins
	 */
	public static List<UltimatePlugin> getPlugins() {
		return instance.plugins;
	}
	
	/**
	 * Makes a command, used internally via UltimatePlugin#registerCommand()
	 * 
	 * @return a command instance, null if unable to create it.
	 */
	public static UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		return instance.util.makeCommand(plugin, basePerm, names);
	}
	
	public void disable() {
		String str = "Disabling " + instance.plugins.size() + " plugins.";
		if (instance.plugins.size() == 1) str = str.replace("plugins", "plugin"); // Just neatness :)
		if (instance.plugins.size() > 10) str += " Whew! That\'s alot!";
		eventLogger.println(str);
		for (UltimatePlugin up : instance.plugins) up.close();
		
		instance = null;
	}

	/**
	 * Gets the logger instance.
	 * 
	 * @return the logger instance
	 */
	public static UltimateLogger getLogger(String prefix) {
		return instance.logger.newInstance(prefix);
	}
	
	/**
	 * Determines whether or not the server is running Bukkit.
	 * 
	 * @return boolean
	 */
	public static boolean isBukkit() {
		return (type == ServerType.BUKKIT);
	}
		
	/**
	 * Determines whether or not the server is running Bungee.
	 * 
	 * @return boolean
	 */
	public static boolean isBungee() {
		return (type == ServerType.BUNGEE);
	}
	
	/**
	 * Determines whether or not the server is running Sponge.
	 * 
	 * @return boolean
	 */
	public static boolean isSponge() {
		return (type == ServerType.SPONGE);
	}
	
	/**
	 * Determines whether or not the server is running Nukkit.
	 * 
	 * @return boolean
	 */
	public static boolean isNukkit() {
		return (type == ServerType.NUKKIT);
	}
		
	/**
	 * Gets the type of the server.
	 * 
	 * @return the server type
	 */
	public static ServerType getServerType() {
		return type;
	}

} enum ServerType {
		BUKKIT(1),
		BUNGEE(2),
		SPONGE(3),
		NUKKIT(4),
		UNKNOWN(-1);
		
		private int i;
		private ServerType(int i) {
			this.i = i;
		}
		
		public static ServerType fromString(String type) {
			switch (type.toUpperCase()) {
				case "BUKKIT": return BUKKIT;
				case "BUNGEE": return BUNGEE;
				case "SPONGE": return SPONGE;
				case "NUKKIT": return NUKKIT;
				
				default: return UNKNOWN;
			}
		}

		public int getInt() {
			return i;
		}
		
		public String toString() {
			return this.name();
		}
	}