/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

public enum ServerType {
	BUKKIT(1),
	BUNGEE(2),
	SPONGE(3),
	NUKKIT(4),
	UNKNOWN(-1);
	
	private int i;
	private ServerType(int i) {
		this.i = i;
	}
	
	public static ServerType fromString(String type) {
		switch (type.toUpperCase()) {
			case "BUKKIT": return BUKKIT;
			case "BUNGEE": return BUNGEE;
			case "SPONGE": return SPONGE;
			case "NUKKIT": return NUKKIT;
			
			default: return UNKNOWN;
		}
	}

	public int getInt() {
		return i;
	}
	
	public String toString() {
		return this.name();
	}
}