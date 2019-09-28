/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.io.InputStream;

import cf.e3ndr.UltimateLib.LibUtil;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/**
 * The Class UltimatePlugin.
 */
public class UltimatePlugin extends LibUtil {
	private boolean enabled = false;
	private String name = "Plugin " + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10));
	private UltimateLogger logger;
	
	/**
	 * Inits the plugin.
	 *
	 * @param name the name
	 * @param colorCode the color code
	 * @param eventLogger the event logger
	 * @return the plugin
	 */
	public final UltimatePlugin init(String name, String colorCode, UltimateLogger eventLogger) {
		this.name = UltimateLogger.stripColor(name);
		this.enabled = true;
		
		if ((colorCode != null) && ((colorCode = colorCode.replace(" ", "")).equals(UltimateLogger.transformColor(colorCode)))) {
			eventLogger.println("Invalid color code \"" + colorCode + "\" for " + name + ".");
			colorCode = null;
		}
		if (colorCode == null) {
			long code = 0;
			for (char c : name.toCharArray()) code += (int) c;
			colorCode = UltimateLogger.transformColor("&" + String.valueOf(code).subSequence(0, 1));
		}
		this.logger = UltimateLib.getLogger("&7[" + colorCode + this.name + "&7]");
		
		eventLogger.println("Enabling " + this.name);
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
		return UltimateLib.makeCommand(this, basePerm, names);
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
	
}
