/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Player;

/**
 * The Enum GameMode.
 */
public enum GameMode {
	SURVIVAL(0),
	CREATIVE(1),
	ADVENTURE(2),
	SPECTATOR(3);
	
	private int i;
	private GameMode(int i) {
		this.i = i;
	}
	
	/**
	 * To string.
	 *
	 * @return the string value
	 */
	@Override
	public String toString() {
		return this.name();
	}
	
	/**
	 * Gets the integer value.
	 *
	 * @return the integer value
	 */
	public int getInt() {
		return i;
	}
	
	/**
	 * From string.<br/>
	 * Valid strings: "SURVIVAL", "CREATIVE", "ADVENTURE", "SPECTATOR", "S", "C",
	 * "A", "SP", "0", "1", "2", and "3".
	 *
	 * @param gamemode the gamemode string
	 * @return the game mode, null if input is not a valid gamemode
	 */
	public static GameMode fromString(String gamemode) {
		switch (gamemode.toUpperCase()) {
			case "SURVIVAL":
				return SURVIVAL;
			case "CREATIVE":
				return CREATIVE;
			case "ADVENTURE":
				return ADVENTURE;
			case "SPECTATOR":
				return SPECTATOR;
			case "S":
				return SURVIVAL;
			case "C":
				return CREATIVE;
			case "A":
				return ADVENTURE;
			case "SP":
				return SPECTATOR;
			case "0":
				return SURVIVAL;
			case "1":
				return CREATIVE;
			case "2":
				return ADVENTURE;
			case "3":
				return SPECTATOR;
			
			default:
				return null;
		}
	}
	
	/**
	 * From integer.<br/>
	 * Valid integers: 0, 1, 2, and 3.
	 *
	 * @param gamemode the integer
	 * @return the game mode, null if input is not a valid gamemode
	 */
	public static GameMode fromInt(int gamemode) {
		switch (gamemode) {
			case 0:
				return SURVIVAL;
			case 1:
				return CREATIVE;
			case 2:
				return ADVENTURE;
			case 3:
				return SPECTATOR;
			
			default:
				return null;
		}
	}
	
}
