package cf.e3ndr.UltimateLib.Example;

import cf.e3ndr.UltimateLib.Wrappers.Command.CommandExec;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class CommandTest implements CommandExec {

	@Override
	public boolean onCommand(WrappedPlayer executor, String alias, String[] args) {
		executor.sendMessage("Hello " + executor.getName() + "!");
		return true;
	}

}
