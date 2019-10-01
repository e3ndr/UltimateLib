/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

import java.util.UUID;

public interface WrappedConsole extends WrappedPlayer {
	public boolean isConsole();
	public WrappedPlayer getPlayer();
	
	// CONSOLE doesn't have any of these, use a WrappedPlayer instead.
	@Override
	default UUID getUUID() {return null;}
	@Override
	default GameMode getMode() {return null;}
	@Override
	default void setMode(GameMode gamemode) {}
	
}
