/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import java.util.concurrent.TimeUnit;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BungeeLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.BungeeCommand;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.NullWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class UltimateLibBungee extends Plugin implements UltimateLibUtil {
	@Override
    public void onEnable() {
		new UltimateLib(this, new BungeeLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "BUNGEE", this.getDescription().getVersion());
    }
	
	@Override
    public void onDisable() {
		UltimateLib.instance.disable();
    }

	@Override
	public void registerCommand(UltimateCommand command) {
		this.getProxy().getPluginManager().registerCommand(this, new BungeeCMD(command.getAliases()[0], command.getBasePerm(), command.getAliases(), (BungeeCommand) command));
	}

	@Override
	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		BungeeCommand cmd = new BungeeCommand(plugin, basePerm, names);
		this.registerCommand(cmd);
		return cmd;
	}

	@Override
	public WrappedLocation getLocation(WrappedWorld world, float x, float y, float z, float pitch, float yaw) {
		return new NullLocation();
	}

	@Override
	public WrappedWorld getWorld(String name) {
		// TODO Auto-generated method stub
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
}
