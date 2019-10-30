/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public interface GUIListener {
	public boolean clickEvent(GUI gui, Stack stack, WrappedPlayer<?> player, boolean isRightClick);
	
	public void closeEvent(GUI gui, WrappedPlayer<?> player);
	
}
