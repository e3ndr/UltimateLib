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

import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitCommandPlayer;

public class BukkitCMD extends Command {
	private UltimateCommand command;
	
	public BukkitCMD(String name, List<String> aliases, UltimateCommand command) {
		super(name, "", "", aliases);
		this.command = command;
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		return this.command.execute(new BukkitCommandPlayer(sender), commandLabel, args);
	}
	
	@Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return this.tabComplete(sender, alias, args, null);
    }
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
		return this.command.tabComplete(new BukkitCommandPlayer(sender), alias, args);
    }
}
