/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.BukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Location.BukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

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
	public WrappedLocation getLocation() {
		return new BukkitLocation(this.bukkit.getLocation());
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
		Bukkit.getScheduler().callSyncMethod(Bukkit.getPluginManager().getPlugin("UltimateLib"), new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + bukkit.getName() + " " + json);
				return 0; // We love threading :)
			}}
		);
		
	}

	@Override
	public boolean teleport(WrappedLocation wloc) {
		return this.bukkit.teleport(((BukkitLocation) wloc).getBukkit());
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
		ArrayList<Stack> inv = new ArrayList<>(this.bukkit.getInventory().getSize());
		
		for (ItemStack is : this.bukkit.getInventory().getContents()) {
			if (is != null) inv.add(new BukkitStack(is));
		}
		
		return new PlayerInventory(inv, this.bukkit.getInventory().getSize(), this);
	}

	@Override
	public void setInventory(Inventory inv) {
		for (int i = 0; i != inv.getSize(); i++) {
			Stack s = inv.getSlot(i);
			
			if (s instanceof BukkitStack) {
				this.bukkit.getInventory().setItem(i, (ItemStack) inv.getSlot(i).getNative());
			} else {
				this.bukkit.getInventory().setItem(i, new ItemStack(Material.valueOf(s.getMaterial().toUpperCase()), s.getAmmount()));
			}
		}
	}
	
}
