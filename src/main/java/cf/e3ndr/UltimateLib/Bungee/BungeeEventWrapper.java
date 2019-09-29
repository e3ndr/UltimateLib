package cf.e3ndr.UltimateLib.Bungee;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Player.BungeePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeEventWrapper implements Listener {
	
	@EventHandler
	public void onChat(ChatEvent e){
		e.setCancelled(Events.getInstance().onEvent(new EventPlayerChat(e.getMessage(), new BungeePlayer((ProxiedPlayer) e.getSender()))));
	}
	
}
