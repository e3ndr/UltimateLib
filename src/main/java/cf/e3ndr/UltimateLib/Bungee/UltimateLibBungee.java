/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BungeeLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.NullWorld;
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
		this.getProxy().getPluginManager().registerCommand(this, new BungeeCMD(command.getAliases()[0], command.getBasePerm(), command.getAliases(), command));
	}

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		UltimateCommand cmd = new UltimateCommand(plugin, basePerm, names);
		this.registerCommand(cmd);
		return cmd;
	}

	@Override
	public WrappedLocation getLocation(WrappedWorld world, float x, float y, float z, float pitch, float yaw) {
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
	public ArrayList<WrappedPlayer<?>> getPlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) ret.add(new BungeePlayer(p));
		
		return ret;
	}
	
}
