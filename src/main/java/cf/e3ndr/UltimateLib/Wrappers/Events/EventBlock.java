/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

public class EventBlock extends Event {
	private WorldLocation bloc;
	private WrappedPlayer<?> player;
	private boolean breakEvent;
	private String material;
	
	public EventBlock(WorldLocation bloc, WrappedPlayer<?> player, String material, boolean isBreakEvent) {
		this.bloc = bloc;
		this.player = player;
		this.breakEvent = isBreakEvent;
		this.material = material;
	}
	
	public EventBlock(WorldLocation location, WrappedPlayer<?> player, int id, boolean isBreakEvent) {
		this(location, player, String.valueOf(id), isBreakEvent);
	}

	public WrappedPlayer<?> getPlayer() {
		return this.player;
	}
	
	public WorldLocation getLocation() {
		return this.bloc;
	}
	
	public boolean isBreakEvent() {
		return this.breakEvent;
	}

	public String getMaterial() {
		return this.material;
	}
}
