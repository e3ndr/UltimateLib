/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public interface WrappedPlayer extends WrappedConsole {
	public boolean teleportPlayer(WrappedLocation wloc);
	public WrappedLocation getLocation();
	public void sendJSON(String json);
	public UUID getUUID();
	public GameMode getMode();
	public void setMode(GameMode gamemode);
	
	default boolean isConsole() { return false; }
	default WrappedPlayer getPlayer() { return this; }
}
