/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Bukkit;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Plugin.PluginLoader;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockBreak;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlockPlace;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventEntityDeath;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.BukkitStack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.ItemType;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class BukkitEventWrapper implements Listener {
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
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		EventBlockPlace event = new EventBlockPlace(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromBukkit(e.getBlock().getType().name()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		String type = e.getEntityType().name().replace("_", "");
		EventEntityDeath event = new EventEntityDeath(type);
		
		Events.getInstance().onEvent(event);
		
		Location loc = e.getEntity().getLocation();
		for (Stack s : event.getDrops()) {
			BukkitStack bs = (BukkitStack) s;
			loc.getWorld().dropItem(loc, bs.getNative());
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		EventPlayerChat event = new EventPlayerChat(e.getMessage(), player);
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		WrappedPlayer<?> player = (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId());
		EventBlockBreak event = new EventBlockBreak(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), player, ItemType.getItemFromBukkit(e.getBlock().getType().name()));
		
		Events.getInstance().onEvent(event);
		
		e.setCancelled(event.isCancelled());
	}
	
}
