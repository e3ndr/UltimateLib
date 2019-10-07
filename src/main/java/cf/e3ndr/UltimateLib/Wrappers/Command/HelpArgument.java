/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

public class HelpArgument {
	private String alias;
	private String argument;
	private String permission;
	
	public HelpArgument(String alias, String argument, String permission) {
		this.alias = alias;
		this.argument = argument;
		this.permission = permission;
	}
	
	public String getAlias() {
		return alias;
	}

	public String getArgument() {
		return argument;
	}
	
	public String getPermission() {
		return permission;
	}
}
