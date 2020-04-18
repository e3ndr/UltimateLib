/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Plugin.PluginLoader;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI.GUI;
import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.WrappedOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

/*    __  ______  _                 __       __    _ __       //  __  ____ 
 *   / / / / / /_(_)___ ___  ____ _/ /____  / /   (_) /_     //  / / / / / 
 *  / / / / / __/ / __ `__ \/ __ `/ __/ _ \/ /   / / __ \   //  / / / / /  
 * / /_/ / / /_/ / / / / / / /_/ / /_/  __/ /___/ / /_/ /  //  / /_/ / /___
 * \____/_/\__/_/_/ /_/ /_/\__,_/\__/\___/_____/_/_.___/  //   \____/_____/
 */
public class UltimateLib implements ServerUtil {
	/** The ASCII art banner */
	public static final String ultimatelib = "\n&5   __  ______  _                 __       &d__    _ __  \n&5  / / / / / /_(_)___ ___  ____ _/ /____  &d/ /   (_) /_ \n&5 / / / / / __/ / __ `__ \\/ __ `/ __/ _ \\&d/ /   / / __ \\\n&5/ /_/ / / /_/ / / / / / / /_/ / /_/  __&d/ /___/ / /_/ /\n&5\\____/_/\\__/_/_/ /_/ /_/\\__,_/\\__/\\___&d/_____/_/_.___/";
	public static final String prefix = "&7[&5{0}&7]";
	private static double version;
	private static UltimateLib instance;
	private static ServerType type;
	private UltimateLibUtil util;
	private UltimateLogger logger;
	private ServerHandler handler;
	
	public UltimateLib(UltimateLibUtil util, UltimateLogger logger, String utiltype, String version) {
		long start = System.currentTimeMillis();
		
		type = ServerType.fromString(utiltype);
		this.logger = logger;
		this.util = util;
		UltimateLib.version = Double.valueOf(version);
		instance = this;
		
		this.logger.println(UltimateLogger.transformColor(ultimatelib + "&5 version " + version + "\n"));
		eventLogger = this.logger.newInstance(prefix.replace("{0}", "UltimateLib &8- &dPluginFramework"));
		new Events();
		this.handler = new ServerHandler(util, this.logger);
		(new PluginLoader(eventLogger, UltimateLib.version)).run();
		this.util.scheduleSyncTask(new SyncTasks(), 0, 10);
		this.logger.setDebug(true);
		
		this.logger.println("Done! Took " + (System.currentTimeMillis() - start) + "ms");
	}
	
	private static UltimateLogger eventLogger;
	private ArrayList<UltimatePlugin> plugins = new ArrayList<UltimatePlugin>();
	
	public static void registerPlugin(UltimatePlugin plugin) {
		if (getPlugin(plugin.getName()) != null) {
			eventLogger.println(UltimateLogger.transformColor("&5 Plugin \"&c" + plugin.getName() + "&5\" already loaded in the server! Did you forget to delete old jars?"));
		} else {
			instance.plugins.add(plugin);
		}
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
		return String.valueOf(version).replace(".0", "");
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
	 * Gets a registered plugin.
	 * 
	 * @return a plugin, null if not found
	 */
	public static UltimatePlugin getPlugin(String name) {
		for (UltimatePlugin p : getPlugins()) {
			if (p.getName().equals(name)) return p;
		}
		
		return null;
	}
	
	private static ArrayList<UltimateCommand> commands = new ArrayList<>();
	/**
	 * Makes a command, used internally via UltimatePlugin#registerCommand()
	 * 
	 * @return a command instance, null if unable to create it.
	 */
	public static UltimateCommand makeCommand(UltimatePlugin plugin, String[] names) {
		for (UltimateCommand c : commands) {
			if (c.getPlugin().getName().equalsIgnoreCase(plugin.getName()) && Arrays.equals(c.getAliases(), names)) {
				return c;
			}
		}
		
		UltimateCommand cmd = instance.util.makeCommand(plugin, names);
		commands.add(cmd);
		return cmd;
	}
	
	public void disable() {
		StringBuilder sb = new StringBuilder();
		sb.append("Disabling ");
		sb.append(this.plugins.size());
		sb.append(" plugin");
		if (instance.plugins.size() != 1) sb.append("s");
		sb.append(".");
		if (instance.plugins.size() > 9) sb.append(" Whew! That\'s alot!");
		eventLogger.println(sb.toString());
		for (UltimatePlugin up : instance.plugins) {
			if ((up != null) && up.isEnabled()) up.unload();
		}
		this.plugins = new ArrayList<>();
		
		this.util.unregisterCommands();
		ServerHandler.unsafe().end();
		instance = null;
	}
	
	/**
	 * Gets the server instance.
	 * 
	 * @deprecated Plugins should never access the ServerAPI
	 * 
	 * @return the server instance
	 */
	public static ServerUtil getServer() {
		return instance.util;
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
	
	public void callSyncTask(Runnable run) {
		SyncTasks.runnables.add(run);
	}
	
	/* Util Start */
	@SuppressWarnings("deprecation")
	@Override
	public WorldLocation getLocation(Object nativeLoc) {
		return this.util.getLocation(nativeLoc);
	}
	
	@Override
	public WrappedWorld getWorld(String name) {
		return this.util.getWorld(name);
	}
	
	@Override
	public Stack getStack(ItemType material, int ammount) {
		return this.util.getStack(material, ammount);
	}
	
	@Override
	public ArrayList<WrappedWorld> getWorlds() {
		return this.util.getWorlds();
	}
	
	@Override
	public int scheduleSyncTask(Runnable run, int startDelay, int runFrequency) {
		return this.util.scheduleSyncTask(run, startDelay, runFrequency);
	}
	
	@Override
	public int scheduleAsyncTask(Runnable run) {
		return this.util.scheduleAsyncTask(run);
	}
	
	@Override
	public void cancelTask(int id) {
		this.util.cancelTask(id);
	}
	
	@Override
	public boolean isNativePluginPresent(String name) {
		return this.util.isNativePluginPresent(name);
	}
	
	@Override
	public GUI makeGUI(Stack[] inv, String name, int size) {
		return this.util.makeGUI(inv, name, size);
	}
	
	@Override
	public void sendConsoleCommand(String command) {
		this.util.sendConsoleCommand(command);
	}
	
	@Override
	public WrappedOfflinePlayer getOfflinePlayer(UUID uuid) {
		return this.handler.getOfflinePlayer(uuid);
	}
	
	@Override
	public WrappedOfflinePlayer getOfflinePlayer(String name) {
		return this.handler.getOfflinePlayer(name);
	}
	
	@Override
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers() {
		return this.handler.getOnlinePlayers();
	}
	
}

class SyncTasks implements Runnable {
	public static ArrayList<Runnable> runnables = new ArrayList<>();
	
	@Override
	public void run() {
		Iterator<Runnable> it = runnables.iterator();
		
		while (it.hasNext()) {
			it.next().run();
			it.remove();
		}
	}
	
}