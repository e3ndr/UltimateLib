/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public abstract class GUI {
	protected GUIListener listener;
	protected boolean closed = false;
	
	public static GUI makeGUI(Stack[] inv, String name, int size) {
		return UltimateLib.getServer().makeGUI(inv, name, size);
	}
	
	protected GUI(Stack[] inv, String name, int size) {}
	
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	
	public abstract void open(WrappedPlayer<?> player);
}
