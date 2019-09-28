/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Misc.Bukkit;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import cf.e3ndr.UltimateLib.Wrappers.Command.BukkitCommand;

public class BukkitCMD extends Command {
	private BukkitCommand exec;
	
	public BukkitCMD(String name, List<String> aliases, BukkitCommand command) {
		super(name, "", "", aliases);
		this.exec = command;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return ((CommandExecutor) this.exec).onCommand(sender, this, commandLabel, args);
	}
	
	@Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return this.tabComplete(sender, alias, args, null);
    }
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
		return ((TabCompleter) this.exec).onTabComplete(sender, this, alias, args);
    }
}
