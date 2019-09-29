/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class BungeePlayer implements WrappedConsole {
	private CommandSender sender;
	
	public BungeePlayer(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public boolean teleportPlayer(WrappedLocation wloc) {
		return false;
	}

	@Override
	public void sendMessage(String message) {
		this.sender.sendMessage(new TextComponent(message));
	}

	@Override
	public WrappedLocation getLocation() {
		return new NullLocation();
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.sender.hasPermission(permission);
	}
	
	@Override
	public boolean isConsole() {
		return true;
	}

	@Override
	public String getName() {
		return sender.getName();
	}

	@Override
	public WrappedPlayer getPlayer() {
		return this;
	}
}
