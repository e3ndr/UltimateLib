/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory;

import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

/**
 * The Class PlayerInventory.
 */
public class PlayerInventory extends Inventory {
	private WrappedPlayer<?> player;
	
	/**
	 * Instantiates a new player  inventory.
	 * @deprecated Never instantiate directly, this is for Bungee support.
	 */
	public PlayerInventory(WrappedPlayer<?> player) {
		this(null, 0, player);
	}
	
	/**
	 * Instantiates a new player inventory.
	 *
	 * @param inv the inv
	 * @param size the size
	 * @param player the player
	 */
	public PlayerInventory(List<Stack> inv, int size, WrappedPlayer<?> player) {
		super(inv, size);
		this.player = player;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public WrappedPlayer<?> getPlayer() {
		return this.player;
	}
	
	/**
	 * Updates the players inventory.<br/>A convience method for {@link WrappedPlayer#setInventory(Inventory)}
	 */
	public void update() {
		this.player.setInventory(this);
	}
	
}
