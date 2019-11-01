/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

/**
 * Fired when a block event happens
 */
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

public class EventBlock extends Event {
	private WorldLocation bloc;
	private WrappedPlayer<?> player;
	private ItemType material;
	
	/**
	 * Instantiates a new block event.
	 * @deprecated Use {@link EventBlockBreak} or {@link EventBlockPlace}
	 *
	 * @param bloc the bloc
	 * @param player the player
	 * @param material the material
	 * @param isBreakEvent the is break event
	 */
	public EventBlock(WorldLocation bloc, WrappedPlayer<?> player, ItemType material) {
		this.bloc = bloc;
		this.player = player;
		this.material = material;
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
	 * Gets the location.
	 *
	 * @return the location
	 */
	public WorldLocation getLocation() {
		return this.bloc;
	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	public ItemType getMaterial() {
		return this.material;
	}
}
