/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bungee;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Events.Event;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeEventWrapper implements Listener {
	
	@EventHandler
	public void onChat(ChatEvent e) {
		Event event = new EventPlayerChat(e.getMessage(), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(((ProxiedPlayer) e.getSender()).getUniqueId()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
}
