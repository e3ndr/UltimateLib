package cf.e3ndr.UltimateLib.Wrappers.Packets;

public interface PacketListener {
	default void handle(Packet packet, boolean toClient) {}
	default void handle(PacketNamedEntitySpawn packet, boolean toClient) {}
	
}
