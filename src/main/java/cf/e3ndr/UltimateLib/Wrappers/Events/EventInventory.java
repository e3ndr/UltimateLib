package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class EventInventory extends Event {
	private Inventory inventory;
	private boolean rightClick;
	private WrappedPlayer<?> player;
	
	public EventInventory(Inventory inv, WrappedPlayer<?> player, boolean rightClick) {
		this.inventory = inv;
		this.player = player;
		this.rightClick = rightClick;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public WrappedPlayer<?> getPlayer() {
		return this.player;
	}
	
	public void setInventory(Inventory inv) {
		this.inventory.setInventory(inv.getRaw());
	}

	public boolean isRightClick() {
		return this.rightClick;
	}
}
