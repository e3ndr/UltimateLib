/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib;

import java.util.ArrayList;

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
	public ArrayList<WrappedPlayer> getPlayers();
	
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
	
}
