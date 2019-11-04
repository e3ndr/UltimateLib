/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

/**
 * Fired when a chat event happens
 */
public class EventPlayerChat extends Event {
	private String message;
	private WrappedPlayer<?> player;
	
	/**
	 * Instantiates a new event player chat.
	 *
	 * @param message the message
	 * @param player the player
	 */
	public EventPlayerChat(String message, WrappedPlayer<?> player) {
		this.message = message;
		this.player = player;
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public WrappedPlayer<?> getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public EventType getEventType() {
		return EventType.PLAYERCHAT;
	}
}
