/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.NukkitLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.NukkitCommand;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Location.NukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.NukkitWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;
import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;

public class UltimateLibNukkit extends PluginBase implements UltimateLibUtil {
	
	@Override
	public void onEnable() {
		new UltimateLib(this, new NukkitLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "NUKKIT", this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		UltimateLib.instance.disable();
	}
	
	@Override
	public void registerCommand(UltimateCommand command) {
		SimpleCommandMap map = Server.getInstance().getCommandMap();
		map.register(command.getPlugin().getName(), new NukkitCMD(command.getAliases()[0], command.getAliases(), command));
	}

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		NukkitCommand cmd = new NukkitCommand(plugin, basePerm, names);
		this.registerCommand(cmd);
		return cmd;
	}

	@Override
	public WrappedLocation getLocation(WrappedWorld world, float x, float y, float z, float pitch, float yaw) {
		return new NukkitLocation(new Location(x, y, z, pitch, yaw, Server.getInstance().getLevelByName(world.getName())));
	}

	@Override
	public WrappedWorld getWorld(String name) {
		return new NukkitWorld(Server.getInstance().getLevelByName(name));
	}

	@Override
	public int scheduleSyncTask(Runnable run, int startDelay, int runFrequency) {
		return Server.getInstance().getScheduler().scheduleDelayedRepeatingTask(this, run, startDelay, runFrequency, false).getTaskId();
	}

	@Override
	public int scheduleAsyncTask(Runnable run) {
		return Server.getInstance().getScheduler().scheduleDelayedRepeatingTask(this, run, 0, 0, true).getTaskId();
	}

	@Override
	public void cancelTask(int id) {
		Server.getInstance().getScheduler().cancelTask(id);
	}

}
