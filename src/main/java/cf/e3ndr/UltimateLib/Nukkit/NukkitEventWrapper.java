/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlock;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerChatEvent;

public class NukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()), e.getBlock().getId(), false)));
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventPlayerChat(e.getMessage(), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()))));
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(UltimateLib.getInstance().getLocation(e.getBlock().getLocation()), (WrappedPlayer<?>) UltimateLib.getInstance().getOfflinePlayer(e.getPlayer().getUniqueId()), e.getBlock().getId(), true)));
	}
}
