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

import cf.e3ndr.UltimateLib.ServerType;
import cf.e3ndr.UltimateLib.UltimateLib;
import cf.e3ndr.UltimateLib.Config.YMLConfig;
import cf.e3ndr.UltimateLib.Logging.UltimateLogger;
import cf.e3ndr.UltimateLib.Util.TextUtil;
import net.md_5.bungee.config.Configuration;

public class PluginLoader {
	private static boolean canReload = true;
	
	private UltimateLogger logger;
	private double version;
	private static char[] allowedChars = "abcdefghijklmnopqrstuvwxyz0123456789_- ".toCharArray();
	
	public PluginLoader(UltimateLogger logger, double version) {
		this.logger = logger;
		this.version = version;
	}
	
	private static File ultPlugins = new File("plugins/UltimateLib/plugins/");
	private static File plugins = new File("plugins/");
	public void run() {
		this.logger.println("Loading plugins.");
		
		ultPlugins.mkdirs();
		plugins.mkdirs();
		
		for (File f : ultPlugins.listFiles()) loadFile(f);
		for (File f : plugins.listFiles()) loadFile(f);
		
		for (UltimatePlugin p : UltimateLib.getPlugins()) p.init(logger);;
	}

	@SuppressWarnings("resource")
	public void loadFile(File f) {
		if (f.isDirectory()) return;
		try {
			URLClassLoader plugin = new URLClassLoader(new URL[] {f.toURI().toURL()}, this.getClass().getClassLoader());
			JarFile jar = new JarFile(f);
			Configuration cfg = null;
			
			/*Enumeration<JarEntry> en = jar.entries();
			while (en.hasMoreElements()) { // Look for annotation
				JarEntry e = en.nextElement();
				if (!e.isDirectory() && e.getName().endsWith(".class")) {
					String claz = e.getName().substring(0, e.getName().length() - 6).replace("/", ".");
					Class<?> cls = Class.forName(claz);
					Plugin plug = cls.getAnnotation(Plugin.class);
					if (plug != null) {
						cfg = new Configuration();
						cfg.set("color", plug.colorCode());
						cfg.set("name", plug.pluginName());
						cfg.set("main", claz);
						cfg.set("disallow-reload", plug.disallowReload());
						cfg.set("needs-loader", plug.needsClassLoader());
					} else {
						return;
					}
				}
			}*/
			
			if (cfg == null) {
				JarEntry entry = jar.getJarEntry("ultimate.yml");
				if (entry == null) {
					return;
				} else {
					cfg = YMLConfig.getConfig(jar.getInputStream(entry));
				}
			}
			
			PluginDescription yml = new PluginDescription(cfg);
			Class<? extends UltimatePlugin> cls = Class.forName(cfg.getString("main"), true, plugin).asSubclass(UltimatePlugin.class);
			
			if (yml.getName().length() > 24) {
				logger.println("Plugin name cannot be longer than 24 characters (" + yml.getName() + ")");
				return;
			}

			if (TextUtil.containsExcessChars(allowedChars, yml.getName().toLowerCase())) {
				logger.println("Plugin name must be alphanumerical including \'_\', \'-\', and \' \' (" + yml.getName() + ")");
				return;
			}
			
			if (version < cfg.getDouble("ultimate-version", 0)) {
				logger.println("Plugin " + yml.getName() + " cannot run on UltimateLib version " + String.valueOf(this.version).replace(".0", "") + " as it requires version" + String.valueOf(cfg.getDouble("ultimate-version", 0)).replace(".0", "") + " or higher.");
				return;
			}
			
			for (String support : yml.getDisallowedPlatforms()) {
				if (UltimateLib.getServerType() == ServerType.fromString(support)) {
					logger.println("Plugin " + yml.getName() + " doesn\'t support " + support.toLowerCase() + ".");
					return;
				}
			} 
			
			if (cfg.getBoolean("disallow-reload", false)) canReload = false;
			
			boolean needsLoader = cfg.getBoolean("needs-loader", true); // take care
			
			UltimatePlugin p = cls.getConstructor().newInstance();
			UltimateLib.registerPlugin(p.make(yml, logger, jar, plugin, needsLoader));
			
			plugin.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.println("Unable to load plugin \"" + f.getName() + ".\"");
		} catch (Error err) {
			logger.println(UltimateLogger.transformColor("&5Runtime error while enabling plugin \"&c" + f.getName() + "&5\" Plugin can not recover. Error \"&c" + err.toString() + "&5\""));
		}
	}
	
	public static boolean canReload() {
		return canReload;
	}
}
