/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * The Class YMLConfig.
 */
public class YMLConfig {
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public static Configuration getConfig(File config) {
		try {
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public static Configuration getConfig(File config, InputStream defaults) {
		try {
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config, getConfig(defaults));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public static Configuration getConfig(InputStream config) {
		return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
	}
	
	/**
	 * Save config.
	 */
	public static boolean saveConfig(Configuration config, String location) {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(location));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}