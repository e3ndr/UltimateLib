/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BukkitLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.BukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI.BukkitGUI;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI.GUI;
import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.BukkitOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.BukkitWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public class UltimateLibBukkit extends JavaPlugin implements UltimateLibUtil {
	public static UltimateLibBukkit instance;
	private BukkitEventWrapper listener = new BukkitEventWrapper();
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(listener, this);
		new UltimateLib(this, new BukkitLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "BUKKIT", this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		instance = null;
		Bukkit.getScheduler().cancelTasks(this);
		
		try {
			URLClassLoader url = (URLClassLoader) this.getClassLoader();
			
			url.close();
			
			Field pluginField = url.getClass().getDeclaredField("plugin");
			pluginField.setAccessible(true); // Reflection for bukkit's own classloader
			pluginField.set(url, null);
			
			Field pluginInitField = url.getClass().getDeclaredField("pluginInit");
			pluginInitField.setAccessible(true);
			pluginInitField.set(url, null);
		} catch (Throwable t) {
			UltimateLib.getLogger("ULNative").println("&5Could not release own file, this may cause issues.&e\n" + t.getMessage(), true);
		}
		
		UltimateLib.getInstance().disable();
		
		System.gc(); // :)
	}
	
	@Override
	public void registerCommand(UltimateCommand command) {
		SimpleCommandMap map = this.getCommandMap();
		Command cmd = new BukkitCMD(command.getAliases()[0], new ArrayList<String>(Arrays.asList(command.getAliases())), command);
		
		map.register(command.getPlugin().getName(), cmd);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void unregisterCommands() {
		SimpleCommandMap map = this.getCommandMap();
		try {
			Field knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
			knownCommands.setAccessible(true);
			
			Map<String, Command> cmds = (Map<String, Command>) knownCommands.get(map);
			
			Iterator<Command> it = cmds.values().iterator();
			while (it.hasNext()) {
				Command c = it.next();
				if (c instanceof BukkitCMD) {
					it.remove();
				}
			}
			
			knownCommands.set(map, cmds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SimpleCommandMap getCommandMap() {
		Field map;
		try {
			map = SimplePluginManager.class.getDeclaredField("commandMap");
			map.setAccessible(true);
			return (SimpleCommandMap) map.get(this.getServer().getPluginManager());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String[] names) {
		UltimateCommand cmd = new UltimateCommand(plugin, names);
		this.registerCommand(cmd);
		return cmd;
	}
	
	@Override
	public WorldLocation getLocation(Object nativeLoc) {
		Location loc = (Location) nativeLoc;
		
		return new WorldLocation(loc.getX(), loc.getY(), loc.getZ(), this.getWorld(loc.getWorld().getName()), loc.getPitch(), loc.getYaw());
	}
	
	@Override
	public WrappedWorld getWorld(String name) {
		return new BukkitWorld(Bukkit.getWorld(name));
	}
	
	@Override
	public int scheduleSyncTask(Runnable run, int startDelay, int runFrequency) {
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(this, run, startDelay, runFrequency);
	}
	
	@Override
	public int scheduleAsyncTask(Runnable run) {
		return Bukkit.getScheduler().runTaskAsynchronously(this, run).getTaskId();
	}
	
	@Override
	public void cancelTask(int id) {
		Bukkit.getScheduler().cancelTask(id);
	}
	
	@Override
	public ArrayList<WrappedWorld> getWorlds() {
		ArrayList<WrappedWorld> worlds = new ArrayList<WrappedWorld>();
		
		for (World w : Bukkit.getWorlds()) worlds.add(new BukkitWorld(w));
		
		return worlds;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (Player p : Bukkit.getOnlinePlayers()) ret.add(new BukkitPlayer(p));
		
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BukkitOfflinePlayer getOfflinePlayer(String name) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(name);
		if (p != null) return new BukkitOfflinePlayer(p);
		return null;
	}
	
	@Override
	public BukkitOfflinePlayer getOfflinePlayer(UUID uuid) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
		if (p != null) return new BukkitOfflinePlayer(p);
		return null;
	}
	
	@Override
	public Stack getStack(ItemType material, int ammount) {
		return new BukkitStack(material, ammount);
	}
	
	@Override
	public boolean isNativePluginPresent(String name) {
		return (Bukkit.getPluginManager().getPlugin(name) != null);
	}
	
	@Override
	public GUI makeGUI(Stack[] inv, String name, int size) {
		return new BukkitGUI(inv, name, size);
	}
	
	@Override
	public void sendConsoleCommand(String command) {
		UltimateLib.getInstance().callSyncTask(() -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
	}

	@Override
	public int getAmountOnline() {
		return Bukkit.getOnlinePlayers().size();
	}
	
}
