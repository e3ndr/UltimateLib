package cf.e3ndr.UltimateLib.Wrappers.Entity;

import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public interface WrappedEntity<T> {
	public WrappedLocation getLocation();
	public boolean teleport(WrappedLocation wloc);
	public String getName();
	public long getID();
	public boolean isBaby();
	public EntityTypes getType();
	public T getNative();
	
}
