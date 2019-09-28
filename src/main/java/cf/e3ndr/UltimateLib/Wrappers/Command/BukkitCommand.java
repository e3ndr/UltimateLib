/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitConsolePlayer;

public class BukkitCommand extends UltimateCommand implements CommandExecutor, TabCompleter {
	
	public BukkitCommand(UltimatePlugin plugin, String basePerm, String[] names) {
		super(plugin, basePerm, names);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return this.tabComplete(new BukkitConsolePlayer(sender), alias, args);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return this.execute(new BukkitConsolePlayer(sender), label, args);
	}
	
}
