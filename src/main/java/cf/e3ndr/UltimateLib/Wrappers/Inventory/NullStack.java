/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

public class NullStack extends Stack {

	public NullStack(String material, int ammount) {
		super(material, ammount);
	}

	@Override
	public int getAmount() {
		return 0;
	}

	@Override
	public String getMaterial() {
		return null;
	}

	@Override
	public Object getNative() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {}

	@Override
	public boolean hasNBT(String nbt) {
		return false;
	}

	@Override
	public void setNBTString(String nbt, String value) {}

	@Override
	public void setNBTInt(String nbt, int value) {}

	@Override
	public void setNBTByte(String nbt, byte value) {}

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
	public void removeNBT(String nbt) {}

	@Override
	public String[] getLore() {
		return null;
	}

	@Override
	public void setLore(String[] lore) {}

}
