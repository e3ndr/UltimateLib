/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cn.nukkit.command.CommandSender;

public class NukkitCommandPlayer implements WrappedConsole {
	private CommandSender sender;
	
	public NukkitCommandPlayer(CommandSender sender) {
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
