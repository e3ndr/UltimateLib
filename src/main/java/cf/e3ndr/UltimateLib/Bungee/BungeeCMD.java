/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeeCommandPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;
import cn.nukkit.command.ConsoleCommandSender;
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
		WrappedConsole executor = null;
		if (sender instanceof ConsoleCommandSender) {
			executor = new BungeeCommandPlayer(sender);
		} else {
			UltimateLib.getInstance().getOfflinePlayer(((ProxiedPlayer) sender).getUniqueId());
		}
		this.command.execute(executor, this.command.getAliases()[0], args);
	}

}
