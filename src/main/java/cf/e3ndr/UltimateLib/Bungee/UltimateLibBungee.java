package cf.e3ndr.UltimateLib.Bungee;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.BungeeLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.BungeeCommand;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class UltimateLibBungee extends Plugin implements UltimateLibUtil {
	@Override
    public void onEnable() {
		new UltimateLib(this, new BungeeLogger(UltimateLib.prefix.replace("{0}", "UltimateLib")), "BUNGEE");
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
}
