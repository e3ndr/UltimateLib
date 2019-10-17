/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.Location.NullLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;

public class BungeePlayer implements WrappedPlayer<ProxiedPlayer> {
	private CommandSender sender;
	
	public BungeePlayer(CommandSender sender) {
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
	
	@Override
	public boolean isConsole() { return true; }

	@Override
	public String getName() {
		return this.sender.getName();
	}

	@Override
	public WrappedPlayer<?> getPlayer() {
		return (WrappedPlayer<?>) this;
	}

	@Override
	public void sendJSON(String json) {
		this.sender.sendMessage(ComponentSerializer.parse(json));
	}
	
	@Override
	public WrappedLocation getLocation() {
		return new NullLocation();
	}

	@Override
	public UUID getUUID() {
		if (this.sender instanceof ProxiedPlayer) return ((ProxiedPlayer) this.sender).getUniqueId();
		return null;
	}

	@Override
	public GameMode getMode() { return null; }

	@Override
	public void setMode(GameMode gamemode) {}

	@Override
	public boolean teleport(WrappedLocation wloc) { return false; }

	@Override
	public long getID() {
		return -2;
	}

	@Override
	public ProxiedPlayer getNative() {
		if (this.sender instanceof ProxiedPlayer) return (ProxiedPlayer) this.sender;
		return null;
	}

	@Override
	public String getDisplayName() {
		if (this.sender instanceof ProxiedPlayer) return ((ProxiedPlayer) this.sender).getDisplayName();
		return this.getName();
	}

	@SuppressWarnings("deprecation")
	@Override
	public PlayerInventory getInventory() {
		return new PlayerInventory(this);
	}

	@Override
	public void setInventory(Inventory inv) {}

}
