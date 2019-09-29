/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import net.md_5.bungee.config.Configuration;

public class PluginLoader {
	private UltimateLogger logger;
	
	public PluginLoader(UltimateLogger logger) {
		this.logger = logger;
	}
	
	private static File ultPlugins = new File("UltimateLib/plugins/");
	private static File plugins = new File("plugins/");
	public void run() {
		this.logger.println("Loading plugins.");
		
		ultPlugins.mkdirs();
		plugins.mkdirs();
		
		for (File f : plugins.listFiles()) loadFile(f);
		for (File f : ultPlugins.listFiles()) loadFile(f);
	}

	public void loadFile(File f) {
		if (f.isDirectory()) return;
		try {
			URLClassLoader plugin = new URLClassLoader(new URL[] {f.toURI().toURL()}, PluginLoader.class.getClassLoader());
			JarFile jar = new JarFile(f);
			JarEntry entry = jar.getJarEntry("ultimate.yml");
			
			if (entry == null) {
				jar.close();
				plugin.close();
				return;
			}
			
			Configuration ultimate = YMLConfig.getConfig(jar.getInputStream(entry));
			Class<? extends UltimatePlugin> cls = Class.forName(ultimate.getString("main"), true, plugin).asSubclass(UltimatePlugin.class);
			
			try {
				UltimateLib.registerPlugin(ultimate.getString("name"), ultimate.getString("color"), cls.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				logger.println("Unable to load plugin \"" + f.getName() + ".\"");
			}
			
			// This could be multithreaded in the future, however it sometimes throws errors when I make new threads (Bukkit, ?).
			
			jar.close();
			plugin.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.println("Unable to load plugin \"" + f.getName() + ".\"");
		}
	}
}
