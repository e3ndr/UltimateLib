/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Events.Event;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventListener;
import cf.e3ndr.UltimateLib.Wrappers.Events.PluginEvent;

public class Events extends EventListener {
	private static Events instance;
	private ArrayList<PluginEvent> listeners = new ArrayList<PluginEvent>();
	
	public Events() {
		instance = this;
	}
	
	/**
	 * Register an event.
	 */
	public static void addEventListener(UltimatePlugin plugin, EventListener listener) {
		instance.listeners.add(new PluginEvent(plugin, listener));
	}
	
	public static Events getInstance() {
		return instance;
	}
	
	@Override
	public void onEvent(Event e) { // THE VOID IS ETERNAL
		for (PluginEvent p : listeners) p.getListener().onEvent(e);
		
	}
	
	
}
