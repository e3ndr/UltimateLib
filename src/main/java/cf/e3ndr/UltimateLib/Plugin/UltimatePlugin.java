/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.ReturningLogger;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/**
 * The Class UltimatePlugin.
 */
public class UltimatePlugin extends PluginUtil {
	private boolean enabled = false;
	private UltimateLogger logger;
	private ArrayList<UltimateCommand> commands = new ArrayList<UltimateCommand>();
	private boolean loaded = false;
	private PluginDescription yml;
	
	/**
	 * Inits the plugin.
	 *
	 * @param name the name
	 * @param colorCode the color code
	 * @param version 
	 * @param eventLogger the event logger
	 * @return the plugin
	 */
	public final UltimatePlugin init(PluginDescription yml, UltimateLogger eventLogger) {
		if (loaded && this.yml.disallowReload()) throw new IllegalStateException("Plugin \"" + this.getName() + "\" explicitly disallows reloading, yet it has been reloaded.");
		if (enabled) {
			eventLogger.println("Plugin \"" + this.getName() + "\" already enabled.");
			return this;
		}
		this.enabled = true;
		this.yml = yml;
		
		String colorCode = this.yml.getColor();
		if ((colorCode != null) && ((colorCode = colorCode.replace(" ", "")).equals(UltimateLogger.transformColor(colorCode)))) {
			eventLogger.println("Invalid color code \"" + colorCode + "\" for " + this.yml.getName() + ".");
			colorCode = null;
		}
		if (colorCode == null) {
			long code = 0;
			for (char c : this.yml.getName().toCharArray()) code += (int) c;
			colorCode = UltimateLogger.transformColor("&" + String.valueOf(code).subSequence(0, 1));
		}
		this.logger = UltimateLib.getLogger("&7[" + colorCode + this.yml.getName() + "&7]");
		
		String verString = "";
		if (!this.yml.getVersion().equals("")) verString += "&r version " + this.yml.getVersion();
		eventLogger.println(UltimateLogger.transformColor("Enabling &8" + yml.getName() + verString + "."));
		this.pluginEnable(UltimateLib.getInstance());
		
		if (eventLogger instanceof ReturningLogger) eventLogger.println(UltimateLogger.transformColor("Enabled &8" + yml.getName() + verString + "."));
		
		return this;
	}

	/**
	 * Gets the plugin description.
	 * 
	 * @deprecated @see {@link UltimatePlugin#getDescription()}
	 * 
	 * @return the plugin description
	 */
	public PluginDescription getYml() {
		return this.getDescription();
	}

	/**
	 * Gets the plugin description.
	 * 
	 * @return the plugin description
	 */
	public PluginDescription getDescription() {
		return this.yml;
	}
	
	/**
	 * Register command.
	 *
	 * @param basePerm the base perm
	 * @param names the names
	 * @return the ultimate command
	 */
	public final UltimateCommand registerCommand(String basePerm, String... names) {
		if (this.loaded) {
			for (UltimateCommand cmd : this.commands) { // This ensures that we never double register this.commands in the server, as that can be problematic.
				if (Arrays.equals(cmd.getAliases(), names)) return cmd;
			}
		}
		UltimateCommand cmd = UltimateLib.makeCommand(this, basePerm, names);
		this.commands.add(cmd);
		return cmd;
	}
	
	/**
	 * Config present.
	 *
	 * @param config the name of the config
	 * @return true, if present
	 */
	public final boolean configPresent(String config) {
		return new File("plugins/" + this + "/" + config).exists();
	}
	
	/**
	 * Save config.
	 *
	 * @param config the config
	 * @return true, if successful
	 */
	public final boolean saveConfig(String config) {
		return YMLConfig.saveConfig(YMLConfig.getConfig(this.getResourceAsStream(config)), "plugins/" + this.yml.getName() + "/" + config);
	}
	
	/**
	 * Gets a file as a stream.
	 *
	 * @param file the file
	 * @return the file
	 */
	public InputStream getResourceAsStream(String file) {
		return this.getClass().getClassLoader().getResourceAsStream(file);
	}

	/**
	 * Close the plugin.
	 */
	public final void close() {
		this.loaded = true;
		this.enabled = false;
		this.pluginDisable(UltimateLib.getInstance());
		for (UltimateCommand c : this.commands) c.setExecutor(new DeadExecutor(this));
	}
	
	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public final UltimateLogger getLogger() {
		return this.logger;
	}
	
	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public final boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Plugin enable.
	 *
	 * @param lib the lib
	 */
	protected void pluginEnable(UltimateLib lib) {}
	
	/**
	 * Plugin disable.
	 *
	 * @param lib the lib
	 */
	protected void pluginDisable(UltimateLib lib) {}

	/**
	 * Gets the this.commands related to the plugin.
	 *
	 * @return a list of this.commands
	 */
	public final List<UltimateCommand> getCommands() {
		return this.commands;
	}

	/**
	 * Gets the name of the plugin.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.yml.getName();
	}

	/**
	 * Checks if it has been this.loaded.
	 *
	 * @return true, if has been this.loaded
	 */
	public boolean hasBeenloaded() {
		return this.loaded;
	}
	
}
