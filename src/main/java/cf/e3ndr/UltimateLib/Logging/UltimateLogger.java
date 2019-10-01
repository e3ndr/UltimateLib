/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Logging;

/**
 * The Class UltimateLogger.
 */
public class UltimateLogger {
	
	protected String prefix;
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param prefix the prefix
	 */
	public UltimateLogger(String prefix) {
		this.prefix = transformColor(prefix + "&r ");
	}
	
	/**
	 * Strip color.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	public static String stripColor(String msg) {
		return msg
				.replace("§4", "")
				.replace("§c", "")
				.replace("§6", "")
				.replace("§e", "")
				.replace("§2", "")
				.replace("§a", "")
				.replace("§b", "")
				.replace("§3", "")
				.replace("§1", "")
				.replace("§9", "")
				.replace("§d", "")
				.replace("§5", "")
				.replace("§f", "")
				.replace("§7", "")
				.replace("§8", "")
				.replace("§0", "")
				.replace("§l", "")
				.replace("§n", "")
				.replace("§o", "")
				.replace("§k", "")
				.replace("§m", "")
				.replace("§r", "");
	}
	
	/**
	 * Transform color.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	public static String transformColor(String msg) {
		return msg
				.replace("&4", "§4")
				.replace("&c", "§c")
				.replace("&6", "§6")
				.replace("&e", "§e")
				.replace("&2", "§2")
				.replace("&a", "§a")	
				.replace("&b", "§b")
				.replace("&3", "§3")
				.replace("&1", "§1")
				.replace("&9", "§9")
				.replace("&d", "§d")
				.replace("&5", "§5")
				.replace("&f", "§f")
				.replace("&7", "§7")
				.replace("&8", "§8")
				.replace("&0", "§0")
				.replace("&l", "§l")
				.replace("&n", "§n")
				.replace("&o", "§o")
				.replace("&k", "§k")
				.replace("&m", "§m")
				.replace("&r", "§r");
	}
	
	/**
	 * New instance.
	 *
	 * @param prefix the prefix
	 * @return a new ultimate logger
	 */
	public UltimateLogger newInstance(String prefix) {
		return new UltimateLogger(prefix);
	}
	
	/**
	 * Println.
	 *
	 * @param obj the message
	 */
	public void println(Object obj) {
		System.out.println(prefix + obj);
	}

}
