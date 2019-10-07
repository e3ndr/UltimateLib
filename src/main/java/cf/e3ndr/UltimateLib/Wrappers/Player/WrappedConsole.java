/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

public interface WrappedConsole {
	public void sendMessage(String message);
	public boolean hasPerm(String permission);
	public String getName();
	public boolean isConsole();
	public WrappedPlayer<?> getPlayer();
	
}
