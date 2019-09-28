/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import net.md_5.bungee.config.Configuration;

public class PluginLoader extends Thread {
	private UltimateLogger logger;
	
	public PluginLoader(UltimateLogger logger) {
		this.logger = logger;
	}
	
	@Override
	public void run() {
		this.logger.println("Loading plugins.");
		for (File f : new File("plugins/").listFiles()) {
			if (f.isDirectory()) continue;
			try {
				URLClassLoader plugin = new URLClassLoader(new URL[] {f.toURI().toURL()}, PluginLoader.class.getClassLoader());
				JarFile jar = new JarFile(f);
				JarEntry entry = jar.getJarEntry("ultimate.yml");
				
				if (entry == null) {
					this.logger.println("There is no ultimate.yml for plugin \"" + f.getName() + ".\"");
					continue;
				}
				
				Configuration ultimate = YMLConfig.getConfig(jar.getInputStream(entry));
				Class<? extends UltimatePlugin> cls = Class.forName(ultimate.getString("main"), true, plugin).asSubclass(UltimatePlugin.class);
				UltimateLib.registerPlugin(ultimate.getString("name"), ultimate.getString("color"), cls.getConstructor().newInstance());
				jar.close();
			} catch (Exception e) {
				e.printStackTrace();
				this.logger.println("Unable to load plugin \"" + f.getName() + ".\"");
			}
		}
	}
}
