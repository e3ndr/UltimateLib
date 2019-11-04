package cf.e3ndr.UltimateLib.Wrappers.Entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;

public class BukkitEntity<T extends LivingEntity> implements WrappedEntity<T> {
	private T entity;
	
	public BukkitEntity(T entity) {
		this.entity = entity;
	}
	
	@Override
	public WorldLocation getLocation() {
		return UltimateLib.getInstance().getLocation(this.entity.getLocation());
	}

	@Override
	public boolean teleport(WorldLocation wloc) {
		return this.entity.teleport(new Location(Bukkit.getWorld(wloc.getWorld().getName()), wloc.getY(), wloc.getZ(), wloc.getX(), wloc.getPitch(), wloc.getYaw()));
	}

	@Override
	public String getName() {
		return this.entity.getName();
	}
	
	@Override
	public String getStringType() {
		return this.entity.getType().name();
	}
	
	@Override
	public long getID() {
		return this.entity.getEntityId();
	}

	@Override
	public boolean isBaby() {
		return false;
	}

	@Override
	public EntityTypes getType() {
		return EntityTypes.UNKNOWN;
	}
	
	@Override
	public T getNative() {
		return this.entity;
	}
	
}
