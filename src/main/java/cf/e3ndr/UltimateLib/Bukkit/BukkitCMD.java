/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitCommandPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;
import cn.nukkit.command.ConsoleCommandSender;

public class BukkitCMD extends Command {
	private UltimateCommand command;
	
	public BukkitCMD(String name, List<String> aliases, UltimateCommand command) {
		super(name, "", "", aliases);
		this.command = command;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		WrappedConsole executor = null;
		if (sender instanceof ConsoleCommandSender) {
			executor = new BukkitCommandPlayer(sender);
		} else {
			UltimateLib.getInstance().getOfflinePlayer(((Player) sender).getUniqueId());
		}
		return this.command.execute(executor, commandLabel, args);
	}
	
	@Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return this.tabComplete(sender, alias, args, null);
    }
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
		WrappedConsole executor = null;
		if (sender instanceof ConsoleCommandSender) {
			executor = new BukkitCommandPlayer(sender);
		} else {
			UltimateLib.getInstance().getOfflinePlayer(((Player) sender).getUniqueId());
		}
		return this.command.tabComplete(executor, alias, args);
    }
}
