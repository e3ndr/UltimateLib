/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Config;

import java.util.Map;

/**
 * The Class Lang.
 */
public class Lang {
	
	private Map<String, String> lang;
	
	/**
	 * Instantiates a new lang.
	 *
	 * @param lang the Lang
	 */
	public Lang(Map<String, String> lang) {
		this.lang = lang;
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
