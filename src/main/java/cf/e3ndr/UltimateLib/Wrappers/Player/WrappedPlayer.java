/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Wrappers.Entity.EntityTypes;
import cf.e3ndr.UltimateLib.Wrappers.Entity.WrappedEntity;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Inventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.WrappedOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

/**
 * The Interface WrappedPlayer.
 *
 * @param <T> the generic type
 */
public interface WrappedPlayer<T> extends WrappedOfflinePlayer, WrappedConsole, WrappedEntity<T> {
	
	/**
	 * Teleport player.
	 *
	 * @param wloc the wloc
	 * @return true, if successful
	 * @deprecated Use {@link WrappedPlayer#teleport(WorldLocation)}
	 */
	default boolean teleportPlayer(WorldLocation wloc) {
		return this.teleport(wloc);
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Send a JSON message.
	 *
	 * @param json the json
	 */
	public void sendJSON(String json);
	
	/**
	 * Gets the gamemode of the player.
	 *
	 * @return the mode
	 */
	public GameMode getMode();
	
	/**
	 * Sets the gamemode.
	 *
	 * @param gamemode the new mode
	 */
	public void setMode(GameMode gamemode);
	
	/**
	 * Gets the player's display name.
	 *
	 * @return the display name
	 */
	public String getDisplayName();
	
	/**
	 * Gets the player's inventory.
	 *
	 * @return the inventory
	 */
	public PlayerInventory getInventory();
	
	/**
	 * Sets the player's inventory.
	 *
	 * @param inv the new inventory
	 */
	public void setInventory(Inventory inv);
	
	/**
	 * Shows a player an inventory.
	 * 
	 * @apiNote This only works on Bukkit.
	 * 
	 * @param inv the inventory
	 */
	public void showInventory(Inventory inv);
	
	/**
	 * Closes the player's inventory
	 * 
	 * @apiNote This only works on Bukkit.
	 * 
	 */
	public void closeInventory();
	
	@Override
	default boolean isConsole() {
		return false;
	}
	
	@Override
	default boolean isBaby() {
		return false;
	}
	
	@Override
	default EntityTypes getType() {
		return EntityTypes.PLAYER;
	}
	
	@Override
	default String asString() {
		return "[" + this.getName() + ":" + this.getUUID() + "]";
	}
	
	@Override
	default boolean compareEqual(Object o) {
		if (o instanceof WrappedPlayer<?>) {
			return this.getUUID().equals(((WrappedPlayer<?>) o).getUUID());
		}
		
		return false;
	}
}
