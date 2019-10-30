/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitCommandPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;

public class NukkitCMD extends Command {
	private UltimateCommand command;
	
	public NukkitCMD(String name, String[] aliases, UltimateCommand cmd) {
		super(name, "", "", aliases);
		this.command = cmd;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		WrappedConsole executor = new NukkitCommandPlayer(sender);
		if (!(sender instanceof ConsoleCommandSender)) {
			executor = (WrappedConsole) UltimateLib.getInstance().getOfflinePlayer(((Player) sender).getUniqueId());
		}
		
		return this.command.execute(executor, commandLabel, args);
	}
	
}
