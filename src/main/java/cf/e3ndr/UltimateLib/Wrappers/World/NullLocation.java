/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

public class NullLocation extends WorldLocation {
	
	public NullLocation() {
		super(0, 0, 0, null, 0f, 0f);
	}
	
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
