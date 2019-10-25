/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.config.Configuration;

public class PluginDescription {
	private Configuration config;
	
	public PluginDescription(Configuration config) {
		this.config = config;
	}
	
	/**
	 * Gets the color code for this plugin.
	 *
	 * @return the color code, '&r' if not present
	 */
	public String getColor() {
		return this.config.getString("color", "&r");
	}
	
	/**
	 * Gets the list of disallowed platforms.
	 *
	 * @return the list
	 */
	public List<String> getDisallowedPlatforms() {
		return config.getStringList("disallowed-platforms");
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return this.config.getString("name");
	}
	
	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return this.config.getString("version");
	}

	/**
	 * Returns whether or not the plugin disallows reload.
	 *
	 * @return true if so
	 */
	public boolean disallowReload() {
		return this.config.getBoolean("disallow-reload", false);
	}

	/**
	 * Gets all required dependencies.
	 *
	 * @return the list, empty if none
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getDependencies() {
		return (ArrayList<String>) this.config.getList("dependencies", new ArrayList<String>());
	}
	
}
