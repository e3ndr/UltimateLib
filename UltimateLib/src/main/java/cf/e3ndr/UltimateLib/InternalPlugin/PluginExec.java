/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.InternalPlugin;

import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class PluginExec implements CommandExec {

	@Override
	public boolean onCommand(WrappedPlayer executor, String alias, String[] args) {
		executor.sendMessage("Hello " + executor.getName() + "!");
		return true;
	}

}
