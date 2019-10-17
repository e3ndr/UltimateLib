/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.NukkitLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.NukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Location.NukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.NukkitWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;

public class UltimateLibNukkit extends PluginBase implements UltimateLibUtil {
	private NukkitEventWrapper listener = new NukkitEventWrapper();
	
	@Override
	public void onEnable() {
		Server.getInstance().getPluginManager().registerEvents(listener, this);
		new UltimateLib(this, new NukkitLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "NUKKIT", this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		UltimateLib.getInstance().disable();
	}
	
	@Override
	public void registerCommand(UltimateCommand command) {
		SimpleCommandMap map = Server.getInstance().getCommandMap();
		map.register(command.getPlugin().getName(), new NukkitCMD(command.getAliases()[0], command.getAliases(), command));
	}

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		UltimateCommand cmd = new UltimateCommand(plugin, basePerm, names);
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

	@Override
	public ArrayList<WrappedWorld> getWorlds() {
		ArrayList<WrappedWorld> worlds = new ArrayList<WrappedWorld>();
		
		for (Level l : Server.getInstance().getLevels().values()) worlds.add(new NukkitWorld(l));
		
		return worlds;
	}

	@Override
	public ArrayList<WrappedPlayer<?>> getPlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (Player p : Server.getInstance().getOnlinePlayers().values()) ret.add(new NukkitPlayer(p));
		
		return ret;
	}
	
	@Override
	public WrappedPlayer<?> getPlayer(String name) {
		Player p = Server.getInstance().getPlayer(name);
		if (p != null) return new NukkitPlayer(p); 
		return null;
	}

	@Override
	public WrappedPlayer<?> getPlayer(UUID uuid) {
		Optional<Player> p = Server.getInstance().getPlayer(uuid);
		if (p.isPresent()) return new NukkitPlayer(p.get()); 
		return null;
	}

	@Override
	public Stack getStack(String material, int ammount) {
		return new NukkitStack(material, ammount);
	}
}
