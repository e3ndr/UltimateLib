/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Location;

import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public interface WrappedLocation {
	public double getX();
	public double getY();
	public double getZ();
	public float getYaw();
	public float getPitch();
	public WrappedWorld getWorld();
	
}
