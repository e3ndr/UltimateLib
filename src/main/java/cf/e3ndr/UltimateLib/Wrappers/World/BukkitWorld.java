/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import cf.e3ndr.UltimateLib.Wrappers.Misc.WrappedParticle;
import cf.e3ndr.UltimateLib.Wrappers.Player.BukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class BukkitWorld implements WrappedWorld {
	private World world;
	
	public BukkitWorld(World world) {
		this.world = world;
	}
	
	@Override
	public String getName() {
		return this.world.getName();
	}
	
	@Override
	public List<WrappedPlayer<?>> getPlayers() {
		ArrayList<WrappedPlayer<?>> ret = new ArrayList<WrappedPlayer<?>>();
		
		for (Player p : this.world.getPlayers()) ret.add(new BukkitPlayer(p));
		
		return ret;
	}
	
	@Override
	public void playSound(WorldLocation loc, String sound, float volume, float pitch) {
		this.world.playSound(new Location(this.world, loc.getY(), loc.getZ(), loc.getX(), loc.getPitch(), loc.getYaw()), sound, volume, pitch);
	}
	
	@Override
	public void addParticle(WorldLocation loc, WrappedParticle particle) {
		this.world.spawnParticle(getParticle(particle), new Location(this.world, loc.getY(), loc.getZ(), loc.getX(), loc.getPitch(), loc.getYaw()), 1);
	}
	
	public static Particle getParticle(WrappedParticle particle) {
		return Particle.valueOf(particle.name());
	}
}
