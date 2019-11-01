/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;


import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Events.Event;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockBreak;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockPlace;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerChatEvent;

public class NukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockPlace(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromNukkit(e.getBlock().getId(), e.getBlock().getDamage()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventPlayerChat(e.getMessage(), player);
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockBreak(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromNukkit(e.getBlock().getId(), e.getBlock().getDamage()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
}
