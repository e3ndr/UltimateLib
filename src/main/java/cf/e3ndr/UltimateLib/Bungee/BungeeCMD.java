package cf.e3ndr.UltimateLib.Bungee;

import cf.e3ndr.UltimateLib.Wrappers.Command.BungeeCommand;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeePlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCMD extends Command {
	private BungeeCommand command;
	
	public BungeeCMD(String name, String permission, String[] aliases, BungeeCommand cmd) {
		super(name, permission, aliases);
		this.command = cmd;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		this.command.execute(new BungeePlayer(sender), this.command.getAliases()[0], args);
	}

}
