/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

import cn.nukkit.item.Item;

public class NukkitStack extends Stack {
	private Item item;
	
	public NukkitStack(Item item) {
		super(ItemType.getItemFromNukkit(item.getId(), item.getDamage()), item.count);
		this.item = item;
	}
	
	public NukkitStack(ItemType material, int ammount) {
		super(material, ammount);
		this.item = new Item(material.getHolder().nukkitID, material.getHolder().nukkitData, ammount);
	}
	
	@Override
	public int getAmount() {
		return this.item.getCount();
	}
	
	@Override
	public Item getNative() {
		return this.item;
	}
	
	@Override
	public String getName() {
		return this.item.getCustomName();
	}
	
	@Override
	public void setName(String name) {
		this.item.setCustomName(name);
	}
	
	@Override
	public boolean hasNBT(String nbt) {
		return false;
	}
	
	@Override
	public void setNBTString(String nbt, String value) {
	}
	
	@Override
	public void setNBTInt(String nbt, int value) {
	}
	
	@Override
	public void setNBTByte(String nbt, byte value) {
	}
	
	@Override
	public String getNBTString(String nbt) {
		return null;
	}
	
	@Override
	public int getNBTInt(String nbt) {
		return 0;
	}
	
	@Override
	public byte getNBTByte(String nbt) {
		return 0;
	}
	
	@Override
	public void removeNBT(String nbt) {
	}
	
	@Override
	public String[] getLore() {
		return this.item.getLore();
	}
	
	@Override
	public void setLore(String[] lore) {
		this.item.setLore(lore);
	}
	
}
