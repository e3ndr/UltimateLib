package cf.e3ndr.UltimateLib.Wrappers.Packets;

import java.util.ArrayList;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public abstract class Packeteer implements PacketListener {
	private ArrayList<PacketListener> listeners = new ArrayList<PacketListener>();

	public abstract boolean sendPacket(Packet packets, WrappedPlayer<?>... players);
	
	public final void addListener(PacketListener listener) {
		this.listeners .add(listener);
	}
	
	
	/* Listeners */
	@Override
	public void handle(Packet packet, boolean toClient) {
		for (PacketListener listener : this.listeners) listener.handle(packet, toClient);
	}
	
	@Override
	public void handle(PacketNamedEntitySpawn packet, boolean toClient) {
		this.handle((Packet) packet, toClient);
		for (PacketListener listener : this.listeners) listener.handle(packet, toClient);
	}
	
}
