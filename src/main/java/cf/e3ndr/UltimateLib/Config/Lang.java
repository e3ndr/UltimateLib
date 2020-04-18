/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Lang.
 */
public class Lang {
	private HashMap<String, String> lang;
	
	/**
	 * Instantiates a new language config.
	 */
	public Lang() {
		this.lang = new HashMap<String, String>();
	}
	
	/**
	 * Instantiates a new language config.
	 *
	 * @param lang the Lang
	 */
	public Lang(Map<String, String> lang) {
		this.lang = new HashMap<String, String>(lang);
	}
	
	/**
	 * Sets a localized string.
	 *
	 * @param key the key
	 * @param str the str
	 */
	public void set(String key, String str) {
		this.lang.put(key, str);
	}
	
	/**
	 * Gets the localized string.
	 *
	 * @param str the str
	 * @return the localized string
	 */
	public String getLocalizedString(String str) {
		return lang.get(str);
	}
	
	/**
	 * Gets the lang.
	 *
	 * @return the Lang
	 */
	public Map<String, String> getLang() {
		return this.lang;
	}
}
