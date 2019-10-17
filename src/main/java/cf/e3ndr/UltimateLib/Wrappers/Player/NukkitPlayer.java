/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.ArrayList;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.NukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Location.NukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.TextPacket;

public class NukkitPlayer implements WrappedPlayer<Player> {
	private Player nukkit;
	
	public NukkitPlayer(Player player) {
		this.nukkit = player;
	}
	
	@Override
	public void sendMessage(String message) {
		this.nukkit.sendMessage(message);
	}
	
	@Override
	public WrappedLocation getLocation() {
		return new NukkitLocation(this.nukkit.getLocation());
	}

	@Override
	public boolean hasPerm(String permission) {
		return this.nukkit.hasPermission(permission);
	}
	
	public Player getNukkitPlayer() {
		return this.nukkit;
	}

	@Override
	public String getName() {
		return this.nukkit.getName();
	}

	@Override
	public UUID getUUID() {
		return this.nukkit.getUniqueId();
	}

	@Override
	public GameMode getMode() {
		return GameMode.fromInt(this.nukkit.getGamemode());
	}

	@Override
	public void setMode(GameMode gamemode) {
		this.nukkit.setGamemode(gamemode.getInt());
	}

	@Override
	public void sendJSON(String json) {
		TextPacket pk = new TextPacket();
		pk.type = TextPacket.TYPE_JSON;
		pk.message = json; // Theoretically
		nukkit.dataPacket(pk);
	}

	@Override
	public boolean teleport(WrappedLocation wloc) {
		return this.nukkit.teleport(((NukkitLocation) wloc).getNukkit());
	}

	@Override
	public long getID() {
		return this.nukkit.getId();
	}

	@Override
	public Player getNative() {
		return this.nukkit;
	}

	@Override
	public String getDisplayName() {
		return this.nukkit.getDisplayName();
	}

	@Override
	public PlayerInventory getInventory() {
		ArrayList<Stack> inv = new ArrayList<>(this.nukkit.getInventory().getSize());
		
		for (int slot : this.nukkit.getInventory().slots.keySet()) { // Incase they're not in order, probably safer to use the map's integer as the location anyways.
			Item i = this.nukkit.getInventory().slots.get(slot);
			
			if (i != null) inv.set(slot, new NukkitStack(i));
		}
		
		return new PlayerInventory(inv, this.nukkit.getInventory().getSize(), this);
	}

	@Override
	public void setInventory(Inventory inv) {
		for (int i = 0; i != inv.getSize(); i++) {
			Stack s = inv.getSlot(i);
			
			if (s instanceof NukkitStack) {
				this.nukkit.getInventory().setItem(i, (Item) inv.getSlot(i).getNative());
			} else {
				this.nukkit.getInventory().setItem(i, new Item(Integer.valueOf(inv.getSlot(i).getMaterial()), 0, inv.getSlot(i).getAmmount()));
			}
			
		}
	}
	
}
