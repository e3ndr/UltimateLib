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
	private static ArrayList<PluginEvent> listeners;
	
	public Events() {
		instance = this;
		listeners = new ArrayList<>();
	}
	
	/**
	 * Register an event.
	 */
	public static void addEventListener(UltimatePlugin plugin, EventListener listener) {
		listeners.add(new PluginEvent(plugin, listener));
	}
	
	public static Events getInstance() {
		return instance;
	}
	
	@Override
	public void onEvent(Event e) { // THE VOID IS ETERNAL
		for (PluginEvent p : listeners) p.getListener().onEvent(e);
	}
	
	
}
