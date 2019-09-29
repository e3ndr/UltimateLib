/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;

public interface WrappedPlayer {
	public boolean teleportPlayer(WrappedLocation wloc);
	public void sendMessage(String message);
	public WrappedLocation getLocation();
	public boolean hasPerm(String permission);
	public String getName();
	
}
