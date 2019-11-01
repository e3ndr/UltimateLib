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
import cf.e3ndr.UltimateLib.Wrappers.Events.Event;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockBreak;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockPlace;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class BukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockPlace(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromBukkit(e.getBlock().getType().name()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventPlayerChat(e.getMessage(), player);
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockBreak(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromBukkit(e.getBlock().getType().name()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
}
