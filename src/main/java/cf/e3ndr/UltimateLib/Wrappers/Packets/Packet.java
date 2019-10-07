package cf.e3ndr.UltimateLib.Wrappers.Packets;

public abstract class Packet {
	public abstract PacketType getType();
	
} enum PacketType {
	NAMED_ENTITY_SPAWN,
	PLAYER_INFO
}
