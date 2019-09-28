/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.BukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public class BukkitConsolePlayer implements WrappedPlayer {
	private CommandSender sender;
	
	public BukkitConsolePlayer(CommandSender sender) {
		this.sender = sender;
	}
	
	@Override
	public boolean playerPresent() {
		return false;
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
		return new BukkitLocation(Bukkit.getWorlds().get(0).getName(), 0, 0, 0, 0, 0);
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.sender.hasPermission(permission);
	}
	
	@Override
	public boolean isConsole() {
		return (this.sender instanceof ConsoleCommandSender);
	}
	
	public Player getBukkitPlayer() {
		return (Player) this.sender;
	}

	@Override
	public String getName() {
		return "CONSOLE";
	}
}
