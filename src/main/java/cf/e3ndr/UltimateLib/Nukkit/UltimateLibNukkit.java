package cf.e3ndr.UltimateLib.Nukkit;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.UltimateLibUtil;
import cf.e3ndr.UltimateLib.Logging.NukkitLogger;
import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.NukkitCommand;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
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

}
