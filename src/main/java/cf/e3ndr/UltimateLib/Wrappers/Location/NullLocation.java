/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Location;

import cf.e3ndr.UltimateLib.Wrappers.World.NullWorld;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

public class NullLocation implements WrappedLocation {

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public double getZ() {
		return 0;
	}

	@Override
	public float getYaw() {
		return 0;
	}

	@Override
	public float getPitch() {
		return 0;
	}

	@Override
	public WrappedWorld getWorld() {
		return new NullWorld();
	}
}
