/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import cf.e3ndr.UltimateLib.Plugin.PluginLoader;

public class ReloadListener implements Listener {
	private static final String msg = "&5UltimateLib&r\n    There are plugins preventing server reload, please restart the server instead.";
	
	@EventHandler
	public void onCommand(ServerCommandEvent event) {
		if (event.getCommand().toLowerCase().startsWith("reload") && !PluginLoader.canReload()) {
			event.setCancelled(true);
			event.getSender().sendMessage(msg);
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().toLowerCase().startsWith("/reload") && !PluginLoader.canReload()) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(msg);
		}
	}
	
}
