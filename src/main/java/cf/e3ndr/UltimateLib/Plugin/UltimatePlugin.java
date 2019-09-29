/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/**
 * The Class UltimatePlugin.
 */
public class UltimatePlugin extends PluginUtil {
	private boolean enabled = false;
	private String name = "Plugin " + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10));
	private UltimateLogger logger;
	private ArrayList<UltimateCommand> commands = new ArrayList<UltimateCommand>();
	private String color = "";
	private String version;
	
	/**
	 * Inits the plugin.
	 *
	 * @param name the name
	 * @param colorCode the color code
	 * @param version 
	 * @param eventLogger the event logger
	 * @return the plugin
	 */
	public final UltimatePlugin init(String name, String colorCode, String version, UltimateLogger eventLogger) {
		this.name = UltimateLogger.stripColor(name);
		this.enabled = true;
		this.version = version;
		
		if ((colorCode != null) && ((colorCode = colorCode.replace(" ", "")).equals(UltimateLogger.transformColor(colorCode)))) {
			eventLogger.println("Invalid color code \"" + colorCode + "\" for " + name + ".");
			colorCode = null;
		}
		if (colorCode == null) {
			long code = 0;
			for (char c : name.toCharArray()) code += (int) c;
			colorCode = UltimateLogger.transformColor("&" + String.valueOf(code).subSequence(0, 1));
		}
		this.color = colorCode;
		this.logger = UltimateLib.getLogger("&7[" + this.color + this.name + "&7]");
		
		eventLogger.println(UltimateLogger.transformColor("Enabling &8" + this.name + "&r version " + this.version + "."));
		this.pluginEnable(UltimateLib.instance);
		
		return this;
	}
	
	/**
	 * Register command.
	 *
	 * @param basePerm the base perm
	 * @param names the names
	 * @return the ultimate command
	 */
	public final UltimateCommand registerCommand(String basePerm, String... names) {
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
		return new File("plugins/" + this.name + "/" + config).exists();
	}
	
	/**
	 * Save config.
	 *
	 * @param config the config
	 * @return true, if successful
	 */
	public final boolean saveConfig(String config) {
		return YMLConfig.saveConfig(YMLConfig.getConfig(this.getResourceAsStream(config)), "plugins/" + this.name + "/" + config);
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
		this.pluginDisable(UltimateLib.instance);
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return this.name;
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
	 * Gets the commands related to the plugin.
	 *
	 * @return a list of commands
	 */
	public final List<UltimateCommand> getCommands() {
		return this.commands;
	}

	/**
	 * Gets the color code for this plugin.
	 *
	 * @return the color code
	 */
	public String getColor() {
		return this.color;
	}
	
}
