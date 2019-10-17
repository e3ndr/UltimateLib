/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

import cf.e3ndr.UltimateLib.UltimateLib;

public abstract class Stack {
	public static Stack getStack(String material, String itemName, int ammount) {
		return UltimateLib.getServer().getStack(material, ammount);
	}
	
	protected Stack(String material, int ammount) {}
	
	public abstract int getAmmount();
	
	public abstract String getMaterial();
	
	public abstract Object getNative();
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract boolean hasNBT(String nbt);

	public abstract void setNBTString(String nbt, String value);

	public abstract void setNBTInt(String nbt, int value);

	public abstract void setNBTByte(String nbt, byte value);

	public abstract String getNBTString(String nbt);

	public abstract int getNBTInt(String nbt);

	public abstract byte getNBTByte(String nbt);
	
	public abstract void removeNBT(String nbt);
	
	public abstract String[] getLore();
	
	public abstract void setLore(String[] lore);
	
}