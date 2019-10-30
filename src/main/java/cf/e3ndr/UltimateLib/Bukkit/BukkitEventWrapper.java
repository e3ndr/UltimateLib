/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlock;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class BukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()), e.getBlock().getType().name(), false)));
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventPlayerChat(e.getMessage(), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()))));
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()), e.getBlock().getType().name(), true)));
	}
	
}
