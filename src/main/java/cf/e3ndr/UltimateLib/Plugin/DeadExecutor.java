package cf.e3ndr.UltimateLib.Plugin;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

public class DeadExecutor implements CommandExec {
	private UltimatePlugin plugin;
	
	public DeadExecutor(UltimatePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(WrappedConsole executor, String alias, String[] args) {
		executor.sendMessage(UltimateLogger.transformColor("Sorry! The plugin \"&7" + plugin.getName() + "\"&r is disabled. Ask an operator to enable it."));
		return false;
	}

}
