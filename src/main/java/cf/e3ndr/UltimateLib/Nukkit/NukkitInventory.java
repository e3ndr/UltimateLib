package cf.e3ndr.UltimateLib.Nukkit;

import java.util.HashMap;
import java.util.Map;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cn.nukkit.inventory.CustomInventory;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;

public class NukkitInventory extends CustomInventory {

	public NukkitInventory(Inventory inv) {
		super(null, InventoryType.CHEST, getItemMap(inv), inv.getSize(), inv.getName());
	}
	
	public NukkitInventory(Stack[] inv, String name, int size) {
		super(null, InventoryType.CHEST, getItemMap(inv), size, name);
	}
	
	public static Map<Integer, Item> getItemMap(Stack[] inv) {
		HashMap<Integer, Item> ret = new HashMap<>();
		
		for (Stack s : inv) ret.put(ret.size(), (Item) s.getNative());
		
		return ret;
	}

	public static Item[] getItems(Inventory inv) {
		Item[] items = new Item[inv.getSize()];
		
		for (int i = 0; i != items.length; i++) {
			items[i] = (Item) inv.getSlot(i).getNative();
		}
		
		return items;
	}
	
	public static Map<Integer, Item> getItemMap(Inventory inv) {
		HashMap<Integer, Item> ret = new HashMap<>();
		
		for (Item i : getItems(inv)) ret.put(ret.size(), i);
		
		return ret;
	}

}
