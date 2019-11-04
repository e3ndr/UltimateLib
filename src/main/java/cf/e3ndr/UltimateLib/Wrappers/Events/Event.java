/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

/**
 * The Class Event.
 */
public abstract class Event {
	private boolean cancelled = false;
	
	/**
	 * Checks if is cancelled.<br/>
	 * Events are cancelled by returning false on the event call, this method only
	 * shows you plugin that the event has been cancelled by (possibly) another
	 * plugin.
	 *
	 * @return true, if is cancelled
	 */
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean state) {
		this.cancelled = state;
	}

	/**
	 * This marks the event as canceled.
	 * @deprecated Use {@link Event#setCancelled(boolean)}
	 * 
	 */
	public void cancel() {
		this.setCancelled(true);
	}

	/**
	 * Gets the type of the event.
	 * 
	 * @return the event type
	 */
	public abstract EventType getEventType();
	
}
