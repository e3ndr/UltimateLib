/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.changeme.nbtapi.NBTItem;

public class BukkitStack extends Stack {
	private NBTItem item;
	
	public BukkitStack(ItemStack item) {
		super(item.getType().name(), item.getAmount());
		this.item = new NBTItem(item);
	}
	
	public BukkitStack(String material, int ammount) {
		super(material, ammount);
		item = new NBTItem(new ItemStack(Material.valueOf(material.toUpperCase()), ammount));
	}

	@Override
	public int getAmmount() {
		return this.getNative().getAmount();
	}

	@Override
	public String getMaterial() {
		return this.getNative().getType().name();
	}

	@Override
	public ItemStack getNative() {
		return item.getItem();
	}

	@Override
	public String getName() {
		return this.getNative().getItemMeta().getDisplayName();
	}

	@Override
	public void setName(String name) {
		ItemMeta im = this.getNative().getItemMeta();
		im.setDisplayName(name);
		this.getNative().setItemMeta(im);
		this.item = new NBTItem(this.getNative());
	}

	@Override
	public boolean hasNBT(String nbt) {
		return this.item.hasKey(nbt);
	}

	@Override
	public void setNBTString(String nbt, String value) {
		this.item.setString(nbt, value);
	}

	@Override
	public void setNBTInt(String nbt, int value) {
		this.item.setInteger(nbt, value);
	}

	@Override
	public void setNBTByte(String nbt, byte value) {
		this.item.setByte(nbt, value);
	}

	@Override
	public String getNBTString(String nbt) {
		return this.item.getString(nbt);
	}

	@Override
	public int getNBTInt(String nbt) {
		return this.item.getInteger(nbt);
	}

	@Override
	public byte getNBTByte(String nbt) {
		return this.item.getByte(nbt);
	}

	@Override
	public void removeNBT(String nbt) {
		this.item.removeKey(nbt);
	}

	@Override
	public String[] getLore() {
		return this.getNative().getItemMeta().getLore().toArray(new String[0]);
	}

	@Override
	public void setLore(String[] lore) {
		ItemMeta im = this.getNative().getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.getNative().setItemMeta(im);
		this.item = new NBTItem(this.getNative());
	}
	
}
