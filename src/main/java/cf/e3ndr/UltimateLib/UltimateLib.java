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
import cf.e3ndr.UltimateLib.Plugin.PluginDescription;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/*    __  ______  _                 __       __    _ __       //  __  ____ 
 *   / / / / / /_(_)___ ___  ____ _/ /____  / /   (_) /_     //  / / / / / 
 *  / / / / / __/ / __ `__ \/ __ `/ __/ _ \/ /   / / __ \   //  / / / / /  
 * / /_/ / / /_/ / / / / / / /_/ / /_/  __/ /___/ / /_/ /  //  / /_/ / /___
 * \____/_/\__/_/_/ /_/ /_/\__,_/\__/\___/_____/_/_.___/  //   \____/_____/
 */
public class UltimateLib {
	/** The ASCII art banner */
	public static final String ultimatelib = "\n&5   __  ______  _                 __       &d__    _ __  \n&5  / / / / / /_(_)___ ___  ____ _/ /____  &d/ /   (_) /_ \n&5 / / / / / __/ / __ `__ \\/ __ `/ __/ _ \\&d/ /   / / __ \\\n&5/ /_/ / / /_/ / / / / / / /_/ / /_/  __&d/ /___/ / /_/ /\n&5\\____/_/\\__/_/_/ /_/ /_/\\__,_/\\__/\\___&d/_____/_/_.___/";
	public static final String prefix = "&7[&5{0}&7]";
	private static String version;
	private static UltimateLib instance;
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
	public static void registerPlugin(PluginDescription yml, UltimatePlugin plugin) {
		for (UltimatePlugin p : instance.plugins) {
			if (p.getName().equalsIgnoreCase(yml.getName())) {
				eventLogger.println(UltimateLogger.transformColor("&5 Plugin \"&c" + yml.getName() + "&5\" already loaded in the server! Did you forget to delete old jars?"));
				return;
			}
		}
		instance.plugins.add(plugin.init(yml, eventLogger));
	}
	
	/**
	 * Gets the instance of UltimateLib.
	 * 
	 * @return the instance
	 */
	public static UltimateLib getInstance() {
		return instance;
	}
	
	/**
	 * Gets the version of UltimateLib.
	 * 
	 * @return the version
	 */
	public static String getVersion() {
		return version;
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
	 * Gets the server instance.
	 * 
	 * @return the server instance
	 */
	public static ServerUtil getServer() {
		return (ServerUtil) instance.util;
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