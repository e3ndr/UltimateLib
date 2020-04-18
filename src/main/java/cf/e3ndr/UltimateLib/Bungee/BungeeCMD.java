/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeeCommandPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCMD extends Command {
	private UltimateCommand command;
	
	public BungeeCMD(String name, String[] aliases, UltimateCommand cmd) {
		super(name, "", aliases);
		this.command = cmd;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		WrappedConsole executor = new BungeeCommandPlayer(sender);
		if (sender instanceof ProxiedPlayer) {
			executor = (WrappedConsole) UltimateLib.getInstance().getOfflinePlayer(((Player) sender).getUniqueId());
		}
		
		this.command.execute(executor, this.command.getAliases()[0], args);
	}
	
}
