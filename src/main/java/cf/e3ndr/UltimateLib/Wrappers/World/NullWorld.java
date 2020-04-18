/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.World;

import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Misc.WrappedParticle;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class NullWorld implements WrappedWorld {
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public List<WrappedPlayer<?>> getPlayers() {
		return null;
	}
	
	@Override
	public void playSound(WorldLocation loc, String sound, float volume, float pitch) {
	}
	
	@Override
	public void addParticle(WorldLocation loc, WrappedParticle particle) {
	}
	
}
