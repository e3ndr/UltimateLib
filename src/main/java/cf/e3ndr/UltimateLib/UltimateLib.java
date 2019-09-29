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

/*    __  ______  _                 __       __    _ __  
 *   / / / / / /_(_)___ ___  ____ _/ /____  / /   (_) /_ 
 *  / / / / / __/ / __ `__ \/ __ `/ __/ _ \/ /   / / __ \
 * / /_/ / / /_/ / / / / / / /_/ / /_/  __/ /___/ / /_/ /
 * \____/_/\__/_/_/ /_/ /_/\__,_/\__/\___/_____/_/_.___/
 */
public class UltimateLib {
	public static final String ultimatelib = "\n" +
			"&5   __  ______  _                 __       &d__    _ __  \n" + 
			"&5  / / / / / /_(_)___ ___  ____ _/ /____  &d/ /   (_) /_ \n" + 
			"&5 / / / / / __/ / __ `__ \\/ __ `/ __/ _ \\&d/ /   / / __ \\\n" + 
			"&5/ /_/ / / /_/ / / / / / / /_/ / /_/  __&d/ /___/ / /_/ /\n" + 
			"&5\\____/_/\\__/_/_/ /_/ /_/\\__,_/\\__/\\___&d/_____/_/_.___/";
	
	/** The instance of the current UltimateLib. */
	public static UltimateLib instance;
	public static final String prefix = "&7[&5&l{0}&7]";
	public static String version;
	private static ServerType type;
	private UltimateLibUtil util;
	private UltimateLogger logger;
	
	
	public UltimateLib(UltimateLibUtil util, UltimateLogger logger, String utiltype, String version) {
		long start = System.currentTimeMillis();
		
		type = ServerType.fromString(utiltype);
		this.logger = logger;
		this.util = util;
		UltimateLib.version = version;
		instance = this;
		
		this.logger.println(UltimateLogger.transformColor(ultimatelib + "&5 version " + version));
		eventLogger = this.logger.newInstance(prefix.replace("{0}", "UltimateLib &8- &dPluginFramework"));
		(new PluginLoader(eventLogger)).run();
		
		this.logger.println("Done! Took " + (System.currentTimeMillis() - start) + "ms");
	}

	private static UltimateLogger eventLogger;
	private ArrayList<UltimatePlugin> plugins = new ArrayList<UltimatePlugin>();
	public static void registerPlugin(String name, String colorCode, String version, UltimatePlugin plugin) {
		for (UltimatePlugin p : instance.plugins) {
			if (p.getName().equalsIgnoreCase(name)) {
				eventLogger.println(UltimateLogger.transformColor("&5 Plugin \"&c" + name + "&5\" already loaded in the server! Did you forget to delete old jars?"));
				return;
			}
		}
		instance.plugins.add(plugin.init(name, colorCode, version, eventLogger));
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
		if (instance.plugins.size() > 9) str += " Whew! That\'s alot!";
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

}