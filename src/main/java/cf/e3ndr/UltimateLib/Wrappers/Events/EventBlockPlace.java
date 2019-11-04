package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

/**
 * Fired when a block event happens
 */
public class EventBlockPlace extends EventBlock {

	@SuppressWarnings("deprecation")
	public EventBlockPlace(WorldLocation bloc, WrappedPlayer<?> player, ItemType material) {
		super(bloc, player, material);
	}
	
	@Override
	public EventType getEventType() {
		return EventType.BLOCKPLACE;
	}
	
}
