/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Bukkit.UltimateLibBukkit;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.BukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class BukkitGUI extends GUI implements Listener {
	private Inventory inv;
	
	public BukkitGUI(Stack[] inv, String name, int size) {
		super(inv, name, size);
		this.inv = Bukkit.createInventory(null, size, name);
		
		for (Stack s : inv) this.inv.addItem(((BukkitStack) s).getNative());
		
		Bukkit.getPluginManager().registerEvents(this, UltimateLibBukkit.instance);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (this.closed) {
			e.getHandlers().unregister(this);
			return;
		} else if ((e.getClickedInventory() != null) && e.getClickedInventory().equals(this.inv)) {
			if (this.listener != null) {
				Stack s = null;
				if (e.getCurrentItem() != null) s = new BukkitStack(e.getCurrentItem());
				e.setCancelled(this.listener.clickEvent(this, s, (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getWhoClicked().getUniqueId()), e.isRightClick()));
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().equals(this.inv)) {
			if (this.listener != null) this.listener.closeEvent(this, (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()));
			e.getHandlers().unregister(this);
		}
	}
	
	@Override
	public void open(WrappedPlayer<?> player) {
		((BukkitPlayer) player).getBukkitPlayer().openInventory(this.inv);
	}
	
}
