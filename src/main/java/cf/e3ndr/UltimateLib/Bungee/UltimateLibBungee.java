/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import cf.e3ndr.UltimateLib.ServerHandler;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BungeeLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.NullStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI.GUI;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.NullWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class UltimateLibBungee extends Plugin implements UltimateLibUtil {
	private BungeeEventWrapper listener = new BungeeEventWrapper();
	@Override
    public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, listener);
		new UltimateLib(this, new BungeeLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "BUNGEE", this.getDescription().getVersion());
    }
	
	@Override
    public void onDisable() {
		UltimateLib.getInstance().disable();
    }

	@Override
	public void registerCommand(UltimateCommand command) {
		this.getProxy().getPluginManager().registerCommand(this, new BungeeCMD(command.getAliases()[0], command.getAliases(), command));
	}

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String[] names) {
		UltimateCommand cmd = new UltimateCommand(plugin, names);
		this.registerCommand(cmd);
		return cmd;
	}

	@Override
	public WorldLocation getLocation(Object nativeLoc) {
		return new NullLocation();
	}

	@Override
	public WrappedWorld getWorld(String name) {
		return new NullWorld();
	}

	@Override
	public int scheduleSyncTask(Runnable run, int startDelay, int runFrequency) {
		return ProxyServer.getInstance().getScheduler().schedule(this, run, startDelay, runFrequency, TimeUnit.MILLISECONDS).getId();
	}

	@Override
	public int scheduleAsyncTask(Runnable run) {
		return ProxyServer.getInstance().getScheduler().runAsync(this, run).getId();
	}

	@Override
	public void cancelTask(int id) {
		ProxyServer.getInstance().getScheduler().cancel(id);
	}

	@Override
	public ArrayList<WrappedWorld> getWorlds() {return new ArrayList<WrappedWorld>();}

	@Override
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) ret.add(new BungeePlayer(p));
		
		return ret;
	}

	@Override
	public WrappedPlayer<?> getOfflinePlayer(String name) {
		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(name);
		if (p != null) return new BungeePlayer(p); 
		return null;
	}

	@Override
	public WrappedPlayer<?> getOfflinePlayer(UUID uuid) {
		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(uuid);
		if (p != null) return new BungeePlayer(p); 
		return null;
	}

	@Override
	public Stack getStack(String material, int ammount) {
		return new NullStack(material, ammount);
	}

	@Override
	public boolean isNativePluginPresent(String name) {
		return (ProxyServer.getInstance().getPluginManager().getPlugin(name) != null);
	}

	@Override
	public GUI makeGUI(Stack[] inv, String name, int size) {
		return null;
	}

	@Override
	public void sendConsoleCommand(String command) {}

	@Override
	public void setHandler(ServerHandler handler) {
		// TODO
	}

}
