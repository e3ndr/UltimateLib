/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI.GUI;
import cf.e3ndr.UltimateLib.Wrappers.OfflinePlayer.WrappedOfflinePlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WorldLocation;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

/**
 * The Interface ServerUtil.
 */
public interface ServerUtil {
	
	/**
	 * Gets the world.
	 *
	 * @param name the name
	 * @return the world
	 */
	public WrappedWorld getWorld(String name);
	
	/**
	 * @deprecated Never use directly
	 */
	public WorldLocation getLocation(Object nativeLoc);
	
	/**
	 * Gets a player by name.
	 *
	 * @param name the name
	 * @return the player, Null if not found.
	 * @deprecated Use {@link ServerUtil#getOfflinePlayer()}
	 */
	default WrappedPlayer<?> getPlayer(String name) {
		return (WrappedPlayer<?>) this.getOfflinePlayer(name);
	}
	
	/**
	 * Gets a player by uuid.
	 *
	 * @param uuid the uuid
	 * @return the player, Null if not found.
	 * @deprecated Use {@link ServerUtil#getOfflinePlayer()}
	 */
	default WrappedPlayer<?> getPlayer(UUID uuid) {
		return (WrappedPlayer<?>) this.getOfflinePlayer(uuid);
	}
	
	/**
	 * Gets a stack.
	 *
	 * @param material the material
	 * @param ammount the ammount in the stack
	 * @return the stack
	 */
	public Stack getStack(String material, int ammount);
	
	/**
	 * Gets the worlds on the server.
	 * 
	 * @return a list of worlds
	 */
	public ArrayList<WrappedWorld> getWorlds();
	
	/**
	 * Gets the players on the server.
	 *
	 * @return a list of players
	 * @deprecated Use {@link ServerUtil#getOnlinePlayers()}
	 */
	default ArrayList<WrappedPlayer<?>> getPlayers() {
		return this.getOnlinePlayers();
	}
	
	/**
	 * Schedule sync task.
	 *
	 * @param run the runnable
	 * @param startDelay the start delay
	 * @param runFrequency the run frequency
	 * @return the task id
	 */
	public int scheduleSyncTask(Runnable run, int startDelay, int runFrequency);

	/**
	 * Schedule async task.
	 *
	 * @param run the runnable
	 * @return the task id
	 */
	public int scheduleAsyncTask(Runnable run);
	
	/**
	 * Cancel task.
	 *
	 * @param id the id
	 */
	public void cancelTask(int id);

	/**
	 * Is a native plugin present.
	 * 
	 * @param name the name of the plugin
	 * @return true, if present
	 */
	public boolean isNativePluginPresent(String name);
	
	/**
	 * Creates a GUI.
	 * 
	 * @param inv the inventory
	 * @param name the name
	 * @param size the size
	 * @return a new GUI
	 */
	public GUI makeGUI(Stack[] inv, String name, int size);
	
	/**
	 * Sends a command using console.<br/><br/>
	 *
	 * @param command the command
	 * @apiNote Unsupported by Bungee.
	 */
	public void sendConsoleCommand(String command);
	
	/**
	 * Gets an offline player.
	 * 
	 * @apiNote Bungee returns an online player or null
	 * 
	 * @param uuid the uuid
	 * @return the offline player
	 */
	public WrappedOfflinePlayer getOfflinePlayer(UUID uuid);

	/**
	 * Gets an offline player.
	 * 
	 * @apiNote Bungee returns an online player or null
	 * 
	 * @param name the name
	 * @return the offline player
	 */
	public WrappedOfflinePlayer getOfflinePlayer(String name);

	/**
	 * Gets all online players.
	 * 
	 * @apiNote Bungee returns an online player or null
	 * 
	 * @return a list of online players
	 */
	public ArrayList<WrappedPlayer<?>> getOnlinePlayers();

}
