/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import org.bukkit.command.CommandSender;

public class BukkitCommandPlayer implements WrappedConsole {
	private CommandSender sender;
	
	public BukkitCommandPlayer(CommandSender sender) {
		this.sender = sender;
	}
	
	@Override
	public void sendMessage(String message) {
		this.sender.sendMessage(message);
	}
	
	@Override
	public boolean hasPerm(String permission) {
		return this.sender.hasPermission(permission);
	}
	
}
