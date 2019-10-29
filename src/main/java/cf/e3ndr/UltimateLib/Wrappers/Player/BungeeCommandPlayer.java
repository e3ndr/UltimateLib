/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class BungeeCommandPlayer implements WrappedConsole {
	private CommandSender sender;
	
	public BungeeCommandPlayer(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public void sendMessage(String message) {
		this.sender.sendMessage(new TextComponent(message));
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.sender.hasPermission(permission);
	}

}
