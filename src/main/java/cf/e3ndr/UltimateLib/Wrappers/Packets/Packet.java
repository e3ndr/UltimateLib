/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Packets;

/**
 * The Class Packet.
 */
public abstract class Packet {
	private boolean cancelled = false;
	
	/**
	 * Gets the packet type.
	 *
	 * @return the type
	 */
	public abstract PacketType getType();

	/**
	 * Checks to see if it is cancelled.
	 *
	 * @return true, if it is cancelled
	 */
	public boolean isCancelled() {
		return this.cancelled;
	}

	/**
	 * Sets the cancellation state.
	 *
	 * @param state the new state
	 */
	public void setCancelled(boolean state) {
		this.cancelled = state;
	}
	
}
