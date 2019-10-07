/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class EventBlock extends Event {
	private WrappedLocation bloc;
	private WrappedPlayer<?> player;
	private boolean breakEvent;
	
	public EventBlock(WrappedLocation bloc, WrappedPlayer<?> player, boolean isBreakEvent) {
		this.bloc = bloc;
		this.player = player;
		this.breakEvent = isBreakEvent;
	}
	
	public WrappedPlayer<?> getPlayer() {
		return this.player;
	}
	
	public WrappedLocation getLocation() {
		return this.bloc;
	}

	public boolean isBreakEvent() {
		return breakEvent;
	}
}
