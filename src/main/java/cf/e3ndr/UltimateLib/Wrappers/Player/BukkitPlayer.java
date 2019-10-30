/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.BukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

public class BukkitPlayer implements WrappedPlayer<Player> {
	private Player bukkit;
	
	public BukkitPlayer(Player player) {
		this.bukkit = player;
	}
	
	@Override
	public void sendMessage(String message) {
		this.bukkit.sendMessage(message);
	}
	
	@Override
	public WorldLocation getLocation() {
		return UltimateLib.getInstance().getLocation(this.bukkit.getLocation());
	}
	
	@Override
	public boolean hasPerm(String permission) {
		return this.bukkit.hasPermission(permission);
	}
	
	public Player getBukkitPlayer() {
		return this.bukkit;
	}
	
	@Override
	public String getName() {
		return this.bukkit.getName();
	}
	
	@Override
	public UUID getUUID() {
		return this.bukkit.getUniqueId();
	}
	
	@Override
	public GameMode getMode() {
		return GameMode.fromString(this.bukkit.getGameMode().toString());
	}
	
	@Override
	public void setMode(GameMode gamemode) {
		this.bukkit.setGameMode(org.bukkit.GameMode.valueOf(gamemode.toString()));
	}
	
	@Override
	public void sendJSON(String json) {
		UltimateLib.getInstance().callSyncTask(() -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + bukkit.getName() + " " + json));
	}
	
	@Override
	public boolean teleport(WorldLocation wloc) {
		return this.bukkit.teleport(new Location(Bukkit.getWorld(wloc.getWorld().getName()), wloc.getY(), wloc.getZ(), wloc.getX(), wloc.getPitch(), wloc.getYaw()));
	}
	
	@Override
	public long getID() {
		return this.bukkit.getEntityId();
	}
	
	@Override
	public Player getNative() {
		return this.bukkit;
	}
	
	@Override
	public String getDisplayName() {
		return this.bukkit.getDisplayName();
	}
	
	@Override
	public PlayerInventory getInventory() {
		ArrayList<Stack> inv = new ArrayList<>();
		
		for (ItemStack is : this.bukkit.getInventory().getContents()) {
			if (is != null) inv.add(new BukkitStack(is));
		}
		
		return new PlayerInventory(inv, this.bukkit.getInventory().getSize(), this);
	}
	
	@Override
	public void setInventory(Inventory inv) {
		for (int i = 0; i != inv.getSize(); i++) {
			Stack s = inv.getSlot(i);
			if (s == null) continue;
			
			this.bukkit.getInventory().setItem(i, (ItemStack) inv.getSlot(i).getNative());
		}
	}
	
	@Override
	public void showInventory(Inventory inv) {
		org.bukkit.inventory.Inventory ninv = Bukkit.getServer().createInventory(null, inv.getSize(), inv.getName());
		for (int i = 0; i != inv.getSize(); i++) {
			Stack s = inv.getSlot(i);
			if (s == null) continue;
			
			if (s instanceof BukkitStack) {
				ninv.setItem(i, (ItemStack) inv.getSlot(i).getNative());
			} else {
				ninv.setItem(i, new ItemStack(Material.valueOf(s.getMaterial().toUpperCase()), s.getAmount()));
			}
		}
		this.bukkit.openInventory(ninv);
	}
	
	@Override
	public void closeInventory() {
		this.bukkit.closeInventory();
	}
	
	@Override
	public boolean isOnline() {
		return this.bukkit.isOnline();
	}
	
	@Override
	public boolean hasPlayedBefore() {
		return this.bukkit.hasPlayedBefore();
	}
	
	@Override
	public String toString() {
		return this.asString();
	}

	@Override
	public boolean equals(Object o) {
		return this.compareEqual(o);
	}
	
}
