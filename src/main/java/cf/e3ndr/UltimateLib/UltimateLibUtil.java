/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import cf.e3ndr.UltimateLib.Plugin.UltimatePlugin;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

public interface UltimateLibUtil {
	public void registerCommand(UltimateCommand command);

	public UltimateCommand makeCommand(UltimatePlugin plugin, String basePerm, String[] names);
}
