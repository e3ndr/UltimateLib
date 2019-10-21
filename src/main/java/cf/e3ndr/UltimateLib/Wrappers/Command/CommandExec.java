/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedConsole;

/**
 * The Interface CommandExec.
 */
public interface CommandExec {
	
	/**
	 * On command.
	 *
	 * @param executor the executor
	 * @param alias the alias used
	 * @param args the command arguments
	 * @return true, if successful
	 */
	public void onCommand(WrappedConsole executor, String alias, String[] args);
	
	/**
	 * On tab complete.
	 *
	 * @param executor the executor
	 * @param alias the alias used
	 * @param args the command arguments
	 * @return a list of arguments
	 */
	default List<String> onTabComplete(WrappedConsole executor, String alias, String[] args) {
		return new ArrayList<>();
	}
}
