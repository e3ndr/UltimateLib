package cf.e3ndr.UltimateLib.Wrappers.Events;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class EventPlayerChat extends Event {
	private String message;
	private WrappedPlayer player;
	
	public EventPlayerChat(String message, WrappedPlayer player) {
		this.message = message;
		this.player = player;
	}
	
	public WrappedPlayer getPlayer() {
		return this.player;
	}
	
	public String getMessage() {
		return this.message;
	}
}
