/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

/**
 * The listener interface for receiving event events. The class that is
 * interested in processing a event event implements this interface, and the
 * object created with that class is registered with a component using the
 * {@link Events.addEventListener(UltimatePlugin, EventListener)} method. When
 * the event event occurs, that object's appropriate method is invoked.
 */
public abstract class EventListener {
	public void onEvent(Event e) {}
	
}
