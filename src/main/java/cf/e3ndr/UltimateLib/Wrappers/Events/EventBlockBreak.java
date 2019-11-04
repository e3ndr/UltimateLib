package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

/**
 * Fired when a block break event happens
 */
public class EventBlockBreak extends EventBlock {

	/**
	 * Instantiates a new event block break.
	 *
	 * @param bloc the bloc
	 * @param player the player
	 * @param material the material
	 */
	@SuppressWarnings("deprecation")
	public EventBlockBreak(WorldLocation bloc, WrappedPlayer<?> player, ItemType material) {
		super(bloc, player, material);
	}

	@Override
	public EventType getEventType() {
		return EventType.BLOCKPLACE;
	}
	
}
