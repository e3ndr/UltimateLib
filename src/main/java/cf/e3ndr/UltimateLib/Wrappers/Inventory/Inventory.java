/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cf.e3ndr.UltimateLib.Logging.UltimateLogger;

/**
 * The Class Inventory.
 */
public class Inventory {
	private ArrayList<Stack> inv = new ArrayList<Stack>();
	private int size;
	private String name;
	
	/**
	 * Instantiates a new inventory.
	 * 
	 * @deprecated Never instantiate directly, this is for Bungee support.
	 */
	public Inventory() {
		this(0);
	}
	
	/**
	 * Instantiates a new inventory.
	 * 
	 * @param size the size
	 */
	public Inventory(int size) {
		this(null, size);
	}
	
	/**
	 * Instantiates a new inventory.
	 * 
	 * @param size the size
	 * @param name the name
	 */
	public Inventory(int size, String name) {
		this(null, size, name);
	}
	
	/**
	 * Instantiates a new inventory.
	 *
	 * @param inv the inv
	 * @param size the size
	 */
	public Inventory(List<Stack> inv, int size) {
		this(inv, size, "Inventory");
	}
	
	/**
	 * Instantiates a new inventory.
	 *
	 * @param inv the inv
	 * @param size the size
	 * @param name the name
	 */
	public Inventory(List<Stack> inv, int size, String name) {
		if ((inv == null) || (inv.size() == 0)) inv = new ArrayList<>(size);
		
		this.size = size;
		this.set(inv);
		this.name = UltimateLogger.transformColor("&r&r&r&r" + name);
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the raw inventory.
	 *
	 * @return the inventory
	 */
	public final List<Stack> getRaw() {
		return this.inv;
	};
	
	/**
	 * Sets the inventory.
	 *
	 * @param inv the new inventory
	 */
	public void setInventory(List<Stack> inv) {
		this.set(inv);
	}
	
	private void set(List<Stack> inv) {
		if (inv == null) {
			this.inv = new ArrayList<>(this.size);
		} else {
			this.inv = new ArrayList<>();
			for (int i = 0; i != this.size; i++) {
				if (i > (inv.size() - 1)) {
					this.inv.add(null);
				} else {
					this.inv.add(inv.get(i));
				}
			}
		}
	}
	
	/**
	 * Gets the slot's item.
	 *
	 * @param slot the slot
	 * @return the slot
	 */
	public Stack getSlot(int slot) {
		if ((this.inv.size() - 1) < slot) return null;
		return this.inv.get(slot);
	}
	
	/**
	 * Sets a slot's item.
	 *
	 * @param slot the slot
	 * @param item the item
	 */
	public void setSlot(int slot, Stack item) {
		this.inv.set(slot, item);
	}
	
	/**
	 * Adds an item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean addItem(Stack item) {
		for (int i = 0; i != this.size; i++) {
			if (this.inv.get(i) == null) {
				this.inv.set(i, item);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Removes all items of the type specified.
	 *
	 * @param type the type
	 */
	public void removeAll(ItemType type) {
		Iterator<Stack> it = this.inv.iterator();
		while (it.hasNext()) {
			if (it.next().getMaterial() == type) it.remove();
		}
		
	}
	
	/**
	 * Removes the item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean removeItem(Stack item) {
		Iterator<Stack> it = this.inv.iterator();
		while (it.hasNext()) {
			if (it.next() == item) {
				it.remove();
				return true;
			}
		}
		
		return false;
	}
	
	public String getName() {
		return this.name;
	}
}
