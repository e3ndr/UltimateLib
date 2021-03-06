/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Misc.WrappedParticle;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public interface WrappedWorld {
	public String getName();
	
	public List<WrappedPlayer<?>> getPlayers();
	
	public void playSound(WorldLocation loc, String sound, float volume, float pitch);
	
	void addParticle(WorldLocation loc, WrappedParticle particle);
	
}
