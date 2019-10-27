/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;
import java.util.UUID;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Location.WrappedLocation;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cf.e3ndr.UltimateLib.Wrappers.World.WrappedWorld;

/**
 * The Interface ServerUtil.
 */
public interface ServerUtil {
	
	/**
	 * Gets the location.
	 *
	 * @param world the world
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param pitch the pitch
	 * @param yaw the yaw
	 * @return the location
	 */
	public WrappedLocation getLocation(WrappedWorld world, float x, float y, float z, float pitch, float yaw);

	/**
	 * Gets the world.
	 *
	 * @param name the name
	 * @return the world
	 */
	public WrappedWorld getWorld(String name);

	/**
	 * Gets a player by name.
	 *
	 * @param name the name
	 * @return the player, Null if not found.
	 */
	public WrappedPlayer<?> getPlayer(String name);
	
	/**
	 * Gets a player by uuid.
	 *
	 * @param uuid the uuid
	 * @return the player, Null if not found.
	 */
	public WrappedPlayer<?> getPlayer(UUID uuid);
	
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
	 */
	public ArrayList<WrappedPlayer<?>> getPlayers();

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
	
}
