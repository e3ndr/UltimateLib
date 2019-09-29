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
	
	@Override
	default UUID getUUID() {
		return null; // CONSOLE doesn't have a UUID, use a WrappedPlayer instead.
	}
	
	@Override
	default GameMode getMode() {
		return null; // CONSOLE doesn't have a GameMode, use a WrappedPlayer instead.
	}
	
	@Override
	default void setMode(GameMode gamemode) {} // CONSOLE doesn't have a GameMode, use a WrappedPlayer instead.
	
}
