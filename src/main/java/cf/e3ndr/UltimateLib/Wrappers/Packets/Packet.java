/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Packets;

public abstract class Packet {
	private boolean cancelled = false;
	
	public abstract PacketType getType();

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean state) {
		this.cancelled = state;
	}
	
} enum PacketType {
	NAMED_ENTITY_SPAWN,
	PLAYER_INFO
}
