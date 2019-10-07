/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Entity.EntityTypes;
import cf.e3ndr.UltimateLib.Wrappers.Entity.WrappedEntity;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public interface WrappedPlayer<T> extends WrappedConsole, WrappedEntity<T> {
	/**
	 * @deprecated
	 */
	default boolean teleportPlayer(WrappedLocation wloc) {
		return this.teleport(wloc);
	}
	
	public void sendJSON(String json);
	public UUID getUUID();
	public GameMode getMode();
	public void setMode(GameMode gamemode);
	public String getDisplayName();
	
	default boolean isConsole() { return false; }
	default WrappedPlayer<?> getPlayer() { return this; }
	default boolean isBaby() { return false; }
	default EntityTypes getType() { return EntityTypes.PLAYER; }
}
