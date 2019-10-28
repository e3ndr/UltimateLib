/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Command;

/**
 * The Class HelpArgument.
 */
public class HelpArgument {
	private String alias;
	private String argument;
	private String permission;
	private String description;
	
	/**
	 * Instantiates a new help argument.
	 *
	 * @param alias the alias
	 * @param argument the argument
	 * @param permission the permission
	 */
	public HelpArgument(String alias, String argument, String permission) {
		this(alias, argument, permission, null);
	}
	
	/**
	 * Instantiates a new help argument.
	 *
	 * @param alias the alias
	 * @param argument the argument
	 * @param permission the permission
	 * @param description the description
	 */
	public HelpArgument(String alias, String argument, String permission, String description) {
		this.alias = alias;
		this.argument = argument;
		this.permission = permission;
		this.description = description;
	}
	
	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Gets the argument.
	 *
	 * @return the argument
	 */
	public String getArgument() {
		return this.argument;
	}
	
	/**
	 * Gets the permission.
	 *
	 * @return the permission
	 */
	public String getPermission() {
		return this.permission;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
}
