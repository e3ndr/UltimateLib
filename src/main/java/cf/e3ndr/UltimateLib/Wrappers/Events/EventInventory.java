/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class EventInventory extends Event {
	private Inventory inventory;
	private Stack item;
	private boolean rightClick;
	private WrappedPlayer<?> player;
	
	public EventInventory(Inventory inv, WrappedPlayer<?> player, Stack item , boolean rightClick) {
		this.inventory = inv;
		this.player = player;
		this.rightClick = rightClick;
		this.item = item;
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

	public Stack getItem() {
		return this.item;
	}
	
	@Override
	public EventType getEventType() {
		return EventType.INVENTORY;
	}
	
}
