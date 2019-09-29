package cf.e3ndr.UltimateLib.Wrappers.Location;

import cn.nukkit.level.Location;

public class NukkitLocation implements WrappedLocation {
private Location loc;
	
	public NukkitLocation(Location loc) {
		this.loc = loc;
	}
	
	public NukkitLocation(String world, double x, double y, double z, float pitch, float yaw) {
		this.loc = new Location(x, y, z, yaw, pitch);
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
		return (float) this.loc.getYaw();
	}

	@Override
	public float getPitch() {
		return (float) this.loc.getPitch();
	}
	
	public Location getNukkit() {
		return this.loc;
	}
}
