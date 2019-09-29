/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.level.Level;

public class NukkitWorld implements WrappedWorld {
	private Level world;
	
	public NukkitWorld(Level level) {
		this.world = level;
	}

	@Override
	public String getName() {
		return this.world.getName();
	}

	@Override
	public List<WrappedPlayer> getPlayers() {
		ArrayList<WrappedPlayer> ret = new ArrayList<WrappedPlayer>();
		
		for (Long p : this.world.getPlayers().keySet()) ret.add(new NukkitPlayer(this.world.getPlayers().get(p)));
		
		return ret;
	}

}
