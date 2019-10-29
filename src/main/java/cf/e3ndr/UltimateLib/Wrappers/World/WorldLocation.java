/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

public class WorldLocation {
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;
	private WrappedWorld world;
	
	public WorldLocation(double x, double y, double z, WrappedWorld world) {
		this(x, y, z, world, 0f, 0f);
	}
	
	public WorldLocation(double x, double y, double z, WrappedWorld world, float pitch, float yaw) {
		this.x = x;
		this.y = y;
		this.y = y;
		this.world = world;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public float getYaw() {
		return this.yaw;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public WrappedWorld getWorld() {
		return this.world;
	}

}
