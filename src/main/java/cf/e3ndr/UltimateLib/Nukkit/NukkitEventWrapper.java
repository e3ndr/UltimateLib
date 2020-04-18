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
import cf.e3ndr.UltimateLib.Wrappers.Events.EventEntityDeath;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.NukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.level.Location;

public class NukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockPlace(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromNukkit(e.getBlock().getId(), e.getBlock().getDamage()));
		
		event.setCancelled(e.isCancelled());
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		String type = e.getEntity().getClass().getSimpleName().toUpperCase().replace("ENTITY", ""); // no i'm not proud of it.
		EventEntityDeath event = new EventEntityDeath(type);
		
		event.setCancelled(e.isCancelled());
		
		Events.getInstance().onEvent(event);
		
		Location loc = e.getEntity().getLocation();
		for (Stack s : event.getDrops()) {
			NukkitStack bs = (NukkitStack) s;
			loc.getLevel().dropItem(loc, bs.getNative());
		}
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventPlayerChat(e.getMessage(), player);
		
		event.setCancelled(e.isCancelled());
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		Event event = new EventBlockBreak(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromNukkit(e.getBlock().getId(), e.getBlock().getDamage()));
		
		event.setCancelled(e.isCancelled());
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
}
