/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Entity;

import cf.e3ndr.UltimateLib.Wrappers.Util.IComparable;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

/**
 * The Interface WrappedEntity.
 *
 * @param <T> the generic type
 */
public interface WrappedEntity<T> extends IComparable {
	
	/**
	 * Gets the location of the entity.
	 *
	 * @return the location
	 */
	public WorldLocation getLocation();
	
	/**
	 * Teleports the entity.
	 *
	 * @param wloc the location
	 * @return true, if successful
	 */
	public boolean teleport(WorldLocation wloc);
	
	/**
	 * Gets the name of the entity.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Gets the id of the entity.
	 *
	 * @return the id
	 */
	public long getID();
	
	/**
	 * Checks to see if it is a baby.
	 *
	 * @return true, if it is a baby
	 */
	public boolean isBaby();
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EntityTypes getType();
	
	/**
	 * Gets the native object.
	 *
	 * @return the native object
	 */
	public T getNative();
	
	@Override
	default String asString() {
		return "[" + this.getName() + ":" + this.getID() + "]";
	}
	
	@Override
	default boolean compareEqual(Object o) {
		if (o instanceof WrappedEntity<?>) {
			return this.getID() == ((WrappedEntity<?>) o).getID();
		}
		
		return false;
	}
}
