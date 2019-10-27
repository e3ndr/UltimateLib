/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Wrappers.Command.UltimateCommand;

/**
 * The Class UltimatePlugin.
 * 
 * You might want to start by overriding<br/>{@link UltimatePlugin#pluginEnable(UltimateLib)} and<br/>{@link UltimatePlugin#pluginDisable(UltimateLib)}
 */
public class UltimatePlugin extends PluginUtil {
	private boolean enabled = false;
	private UltimateLogger logger;
	private ArrayList<UltimateCommand> commands;
	private boolean loaded = false;
	private PluginDescription yml;
	private JarFile jar;
	private URLClassLoader loader;
	private boolean needsLoader;
	
	/**
	 * Makes and loads the plugin
	 * 
	 * @param yml the ultimate.yml associated with the plugin
	 * @param eventLogger the event logger
	 * @param jar the jar file associated with the plugin
	 * @param loader the classloader associated with the plugin
	 * @param b 
	 * @return the plugin instance
	 */
	public UltimatePlugin make(PluginDescription yml, UltimateLogger eventLogger, JarFile jar, URLClassLoader loader, boolean needsLoader) {
		this.yml = yml;
		this.jar = jar;
		this.commands = new ArrayList<UltimateCommand>();
		this.loaded = true;
		this.loader = loader;
		this.logger = UltimateLib.getLogger("&7[" + this.yml.getColor() + this.yml.getName() + "&7]");
		this.needsLoader = needsLoader;
		
		this.loadClasses();
		this.pluginLoad(UltimateLib.getInstance());
		
		return this;
	}
	
	/**
	 * Inits the plugin.
	 * 
	 * @param eventLogger the event logger
	 */
	public final void init(UltimateLogger eventLogger) {
		if (loaded && this.yml.disallowReload()) {
			eventLogger.println("Plugin \"" + this.getName() + "\" explicitly disallows reloading.");
		} else if (enabled) {
			eventLogger.println("Plugin \"" + this.getName() + "\" already enabled.");
		} else {
			this.loadClasses();
			this.enabled = true;
			String verString = "";
			
			if (!this.yml.getVersion().equals("")) verString += "&r version " + this.yml.getVersion();
			this.pluginEnable(UltimateLib.getInstance());
			eventLogger.println(UltimateLogger.transformColor("Enabled &8" + yml.getName() + verString + "."));
		}
	}
	
	/**
	 * Loads all jar classes
	 * 
	 * @deprecated Never use this for anything you do, ever, this is for internal jar loading.
	 */
	private final void loadClasses() {
		if (!needsLoader) return;
		Enumeration<JarEntry> en = this.jar.entries();
		while (en.hasMoreElements()) { // Load all jar classes into memory
			JarEntry e = en.nextElement();
			if (!e.isDirectory() && e.getName().endsWith(".class")) {
				try {
					this.loader.loadClass(e.getName().substring(0, e.getName().length() - 6).replace("/", "."));
				} catch (Error | Exception ex) {}
			}
		}
	}
	
	/**
	 * Gets the plugin description.
	 * 
	 * @deprecated Use {@link UltimatePlugin#getDescription()} for clarity.
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
	 * @deprecated use {@link UltimatePlugin#getCommand(String...)}<br/>
	 * It's recommended you implement your own permission checking that way you can customize error messages.
	 * 
	 * @param basePerm the base perm
	 * @param names the names
	 * @return the ultimate command
	 */
	public final UltimateCommand registerCommand(String basePerm, String... names) {
		return this.getCommand(names);
	}
	
	/**
	 * Register command.
	 * 
	 * @param names the names
	 * @return the ultimate command
	 */
	public final UltimateCommand getCommand(String... names) {
		if (this.loaded) {
			for (UltimateCommand cmd : this.commands) { // This ensures that we never double register this.commands in the server, as that can be problematic.
				if (Arrays.equals(cmd.getAliases(), names)) return cmd;
			}
		}
		UltimateCommand cmd = UltimateLib.makeCommand(this, names);
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
		return new File("plugins/" + this.yml.getName() + "/" + config).exists();
	}
	
	/**
	 * Save config.
	 *
	 * @param config the config
	 * @return true, if successful
	 */
	public final boolean saveConfig(String config) {
		new File("plugins/" + this.yml.getName()).mkdirs();
		return YMLConfig.saveConfig(YMLConfig.getConfig(this.getResourceAsStream(config)), "plugins/" + this.yml.getName() + "/" + config);
	}
	
	/**
	 * Gets a file as a stream.
	 *
	 * @param file the file
	 * @return the file
	 */
	public InputStream getResourceAsStream(String file) {
		try {
			JarEntry je = this.jar.getJarEntry(file);
			if (je != null) {
				return this.jar.getInputStream(je);
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Close the plugin.
	 */
	public final void close() {
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
	 * Plugin load.<br/>
	 * This is called when your plugin is loaded into memory, use this to setup any APIs that you need.<br/>
	 * Dependencies/Core APIs are recommended to load here, you should treat this method as if no other plugin exists.
	 * 
	 * @param lib the lib
	 */
	protected void pluginLoad(UltimateLib lib) {}
	
	/**
	 * Plugin enable.<br/>
	 * Called after <b>all</b> plugins have loaded.
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
