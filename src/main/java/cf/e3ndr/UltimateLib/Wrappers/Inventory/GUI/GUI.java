/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

/**
 * The Class GUI.
 */
public abstract class GUI {
	protected GUIListener listener;
	protected boolean closed = false;
	
	/**
	 * Make GUI.
	 * 
	 * @apiNote Not available on Bungee.<br/>Nukkit creates a form window thus rightclick is not available.
	 * 
	 * @param inv the items
	 * @param name the name
	 * @param size the size
	 * @return the a new gui
	 */
	public static GUI makeGUI(Stack[] inv, String name, int size) {
		return UltimateLib.getServer().makeGUI(inv, name, size);
	}
	
	protected GUI(Stack[] inv, String name, int size) {}
	
	/**
	 * Sets the listener.
	 *
	 * @param listener the new listener
	 */
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Shows a player the GUI.
	 *
	 * @param player the player to show the GUI to
	 */
	public abstract void open(WrappedPlayer<?> player);
}
