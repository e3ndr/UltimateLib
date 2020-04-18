/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Wrappers.Packets;

/**
 * <marquee><3 u kp</marquee>
 */
public class PacketOfKetchup extends Packet {
	private Object obj;
	
	public PacketOfKetchup(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public PacketType getType() {
		return PacketType.UNKNOWN;
	}
	
	public Object getObject() {
		return this.obj;
	}
	
}
