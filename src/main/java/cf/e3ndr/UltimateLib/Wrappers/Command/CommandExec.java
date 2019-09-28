/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public interface CommandExec {
	public boolean onCommand(WrappedPlayer executor, String alias, String[] args);
	
	default List<String> onTabComplete(WrappedPlayer executor, String alias, String[] args) {
		return new ArrayList<String>();
	}
}
