/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Packets;

import java.util.ArrayList;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public interface Packeteer extends PacketListener {
	public ArrayList<PacketListener> listeners = new ArrayList<PacketListener>();

	public boolean sendPacket(Packet packets, WrappedPlayer<?>... players);
	
	default void addListener(PacketListener listener) {
		listeners.add(listener);
	}
	
	
	/* Listeners */
	@Override
	default void handle(Packet packet, boolean toClient) {
		for (PacketListener listener : listeners) listener.handle(packet, toClient);
	}
	
	@Override
	default void handle(PacketNamedEntitySpawn packet, boolean toClient) {
		this.handle((Packet) packet, toClient);
		for (PacketListener listener : listeners) listener.handle(packet, toClient);
	}
	
}
