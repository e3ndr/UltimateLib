/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Nukkit;

import cf.e3ndr.UltimateLib.Events;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventBlock;
import cf.e3ndr.UltimateLib.Wrappers.Events.EventPlayerChat;
import cf.e3ndr.UltimateLib.Wrappers.Location.NukkitLocation;
import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerChatEvent;

public class NukkitEventWrapper implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(new NukkitLocation(e.getBlock().getLocation()), new NukkitPlayer(e.getPlayer()), false)));
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventPlayerChat(e.getMessage(), new NukkitPlayer(e.getPlayer()))));
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		e.setCancelled(Events.getInstance().onEvent(new EventBlock(new NukkitLocation(e.getBlock().getLocation()), new NukkitPlayer(e.getPlayer()), true)));
	}
}
