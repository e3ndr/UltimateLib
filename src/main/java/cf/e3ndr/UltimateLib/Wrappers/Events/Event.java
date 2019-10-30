/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

/**
 * The Class Event.
 */
public class Event {
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
	
	/**
	 * This marks the event as canceled.<br/>
	 * Plugins should never call this method directly as it's used internally.
	 *
	 */
	public void cancel() {
		this.cancelled = true;
	}
}
