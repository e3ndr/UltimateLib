/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import cf.e3ndr.UltimateLib.Wrappers.World.BukkitWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public class BukkitLocation extends WrappedLocation {
	private Location loc;
	
	public BukkitLocation(Location loc) {
		this.loc = loc;
	}
	
	public BukkitLocation(String world, double x, double y, double z, float pitch, float yaw) {
		this.loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}

	@Override
	public double getX() {
		return this.loc.getX();
	}

	@Override
	public double getY() {
		return this.loc.getY();
	}

	@Override
	public double getZ() {
		return this.loc.getZ();
	}

	@Override
	public float getYaw() {
		return this.loc.getYaw();
	}

	@Override
	public float getPitch() {
		return this.loc.getPitch();
	}
	
	public Location getBukkit() {
		return this.loc;
	}

	@Override
	public WrappedWorld getWorld() {
		return new BukkitWorld(this.loc.getWorld());
	}
}
