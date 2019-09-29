package cf.e3ndr.UltimateLib;

import java.util.ArrayList;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlock;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventListener;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Events.PluginEvent;

public class Events implements EventListener {
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
	public boolean onEvent(EventBlock e) {
		for (PluginEvent p : listeners) if (p.getPlugin().isEnabled() && !p.getListener().onEvent(e)) e.cancel();
		
		return e.isCancelled();
	}
	// it goes on and on and on
	@Override
	public boolean onEvent(EventPlayerChat e) {
		for (PluginEvent p : listeners) if (p.getPlugin().isEnabled() && !p.getListener().onEvent(e)) e.cancel();
		
		return e.isCancelled();
	}
	// and on and on
	
}
