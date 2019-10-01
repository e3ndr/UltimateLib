/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;

public class PluginEvent {
	private UltimatePlugin plugin;
	private EventListener listener;
	
	public PluginEvent(UltimatePlugin plugin, EventListener listener) {
		this.plugin = plugin;
		this.listener = listener;
	}

	public EventListener getListener() {
		return listener;
	}

	public UltimatePlugin getPlugin() {
		return plugin;
	}
}
