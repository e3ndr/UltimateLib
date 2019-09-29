/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public class BukkitCommandPlayer implements WrappedConsole {
	private CommandSender sender;
	
	public BukkitCommandPlayer(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public boolean teleportPlayer(WrappedLocation wloc) {
		return false;
	}

	@Override
	public void sendMessage(String message) {
		this.sender.sendMessage(message);
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
		return (this.sender instanceof ConsoleCommandSender);
	}

	@Override
	public String getName() {
		return "CONSOLE";
	}

	@Override
	public WrappedPlayer getPlayer() {
		return new BukkitPlayer((Player) sender);
	}
}
