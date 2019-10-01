/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

/**
 * The listener interface for receiving event events.
 * The class that is interested in processing a event
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * ServerUtil's <code>addEventListener</code> method. When
 * the event event occurs, that object's appropriate
 * method is invoked.
 */
public interface EventListener {
	default boolean onEvent(EventBlock e) {return false;}
	default boolean onEvent(EventPlayerChat e) {return false;}
	
	
}
