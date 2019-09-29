/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;

public class NukkitCommandPlayer implements WrappedPlayer {
	private CommandSender sender;
	
	public NukkitCommandPlayer(CommandSender sender) {
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
	
	public Player getNukkitPlayer() {
		return (Player) this.sender;
	}

	@Override
	public String getName() {
		return "CONSOLE";
	}
}
