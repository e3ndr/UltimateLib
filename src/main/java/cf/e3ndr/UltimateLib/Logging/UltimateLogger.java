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
	public static final char SECTION = 0xA7;
	
	private boolean showDebug = false;
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
		return msg.replace(SECTION + "4", "").replace(SECTION + "c", "").replace(SECTION + "6", "").replace(SECTION + "e", "").replace(SECTION + "2", "").replace(SECTION + "a", "").replace(SECTION + "b", "").replace(SECTION + "3", "").replace(SECTION + "1", "").replace(SECTION + "9", "").replace(SECTION + "d", "").replace(SECTION + "5", "").replace(SECTION + "f", "").replace(SECTION + "7", "").replace(SECTION + "8", "").replace(SECTION + "0", "").replace(SECTION + "l", "").replace(SECTION + "n", "").replace(SECTION + "o", "").replace(SECTION + "k", "").replace(SECTION + "m", "").replace(SECTION + "r", "");
	}
	
	/**
	 * Transform color.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	public static String transformColor(String msg) {
		return msg.replace("&4", SECTION + "4").replace("&c", SECTION + "c").replace("&6", SECTION + "6").replace("&e", SECTION + "e").replace("&2", SECTION + "2").replace("&a", SECTION + "a").replace("&b", SECTION + "b").replace("&3", SECTION + "3").replace("&1", SECTION + "1").replace("&9", SECTION + "9").replace("&d", SECTION + "d").replace("&5", SECTION + "5").replace("&f", SECTION + "f").replace("&7", SECTION + "7").replace("&8", SECTION + "8").replace("&0", SECTION + "0").replace("&l", SECTION + "l").replace("&n", SECTION + "n").replace("&o", SECTION + "o").replace("&k", SECTION + "k").replace("&m", SECTION + "m").replace("&r", SECTION + "r");
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
		System.out.println(stripColor(prefix + obj));
	}
	
	/**
	 * Println.
	 *
	 * @param obj the message
	 * @param autoColor whether or not to auto color the message
	 */
	public void println(Object obj, boolean autoColor) {
		if (autoColor) {
			this.println(transformColor(String.valueOf(obj)));
		} else {
			this.println(obj);
		}
	}
	
	/**
	 * Print debug.<br/>
	 * See {@link UltimateLogger#setDebug(boolean)}
	 *
	 * @param obj the message
	 */
	public void printDebug(Object obj) {
		if (this.showDebug) this.println(Thread.currentThread().getStackTrace()[2].getClassName() + " : " + obj);
	}

	/**
	 * Sets the debug state.
	 *
	 * @param the state
	 */
	public void setDebug(boolean show) {
		this.showDebug = show;
	}

	/**
	 * Gets the debug state.
	 * 
	 */
	public boolean getDebug() {
		return this.showDebug;
	}
	
	/**
	 * Clones the logger.
	 *
	 */
	@Override
	public UltimateLogger clone() {
		UltimateLogger logger = this.newInstance(this.prefix);
		
		logger.setDebug(this.getDebug());
		
		return logger;
	}
}
