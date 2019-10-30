package cf.e3ndr.UltimateLib.Wrappers.Inventory;

public class ItemHolder {
	public final String bukkitName;
	public final int bukkitID;
	public final String nukkitName;
	public final int nukkitID;
	public final int nukkitData;
	
	/**
	 * Gets a new ItemHolder.<br/>
	 * Useful for custom items/modded minecraft.
	 *
	 * @param bukkitName the bukkit name
	 * @param bukkitID the bukkit ID
	 * @param nukkitName the nukkit name
	 * @param nukkitID the nukkit ID
	 * @param nukkitData the nukkit data
	 * @return a new ItemHolder
	 */
	public ItemHolder(String bukkitName, int bukkitID, String nukkitName, int nukkitID, int nukkitData) {
		this.bukkitName = "minecraft:" + bukkitName;
		this.bukkitID = bukkitID;
		this.nukkitName = "minecraft:" + nukkitName;
		this.nukkitID = nukkitID;
		this.nukkitData = nukkitData;
	}
	
}