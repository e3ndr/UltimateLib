/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BukkitLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.BukkitCommand;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Location.BukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.BukkitWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public class UltimateLibBukkit extends JavaPlugin implements UltimateLibUtil {
	
	@Override
	public void onEnable() {
		new UltimateLib(this, new BukkitLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "BUKKIT", this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		UltimateLib.instance.disable();
	}

	@Override
	public void registerCommand(UltimateCommand command) {
		CommandMap map = this.getCommandMap();
		Command cmd = new BukkitCMD(command.getAliases()[0], new ArrayList<String>(Arrays.asList(command.getAliases())), (BukkitCommand) command);
		
		map.register(command.getPlugin().getName(), cmd);
	}
	
	public CommandMap getCommandMap() {
         Field map;
         try {
             map = SimplePluginManager.class.getDeclaredField("commandMap");
             map.setAccessible(true);
             return (CommandMap) map.get(this.getServer().getPluginManager());
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		BukkitCommand cmd = new BukkitCommand(plugin, basePerm, names);
		this.registerCommand(cmd);
		return cmd;
	}

	@Override
	public WrappedLocation getLocation(WrappedWorld world, float x, float y, float z, float pitch, float yaw) {
		return new BukkitLocation(new Location(Bukkit.getWorld(world.getName()), x, y, z, yaw, pitch));
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
}
