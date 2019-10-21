/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Location;

import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public abstract class WrappedLocation {
	public abstract double getX();
	public abstract double getY();
	public abstract double getZ();
	public abstract float getYaw();
	public abstract float getPitch();
	public abstract WrappedWorld getWorld();

}
