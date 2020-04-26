package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Nukkit.NukkitInventory;
import cf.e3ndr.UltimateLib.Nukkit.UltimateLibNukkit;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.NukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.inventory.InventoryCloseEvent;

public class NukkitGUI extends GUI implements Listener {
	private NukkitInventory inv;
	
	public NukkitGUI(Stack[] inv, String name, int size) {
		super(inv, name, size);
		this.inv = new NukkitInventory(inv, name, size);

		for (int i = 0; i != inv.length; i++) {
			if (inv[i] != null) {
				this.inv.setItem(i, ((NukkitStack) inv[i]).getNative());
			}
		}
		
		Server.getInstance().getPluginManager().registerEvents(this, UltimateLibNukkit.instance);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (this.closed) {
			InventoryClickEvent.getHandlers().unregister(this);
			return;
		} else if ((e.getInventory() != null) && e.getInventory().equals(this.inv)) {
			if (this.listener != null) {
				Stack s = null;
				if (e.getSourceItem() != null) s = new NukkitStack(e.getSourceItem());
				e.setCancelled(this.listener.clickEvent(this, s, (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()), false));
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().equals(this.inv)) {
			if (this.listener != null) this.listener.closeEvent(this, (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()));
			InventoryCloseEvent.getHandlers().unregister(this);
		}
	}
	
	@Override
	public void open(WrappedPlayer<?> player) {
		Player nukkit = (Player) player.getNative();
		nukkit.addWindow(this.inv);
	}
	
}
