/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Packets;

import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;

public class PacketNamedEntitySpawn extends Packet {
	private WrappedPlayer<?> player;
	
	public PacketNamedEntitySpawn(WrappedPlayer<?> player) {
		this.player = player;
	}
	
	@Override
	public PacketType getType() {
		return PacketType.NAMED_ENTITY_SPAWN;
	}

	public WrappedPlayer<?> getPlayer() {
		return player;
	}

}
