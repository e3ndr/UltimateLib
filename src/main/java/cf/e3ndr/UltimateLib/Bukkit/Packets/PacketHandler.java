package cf.e3ndr.UltimateLib.Bukkit.Packets;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class PacketHandler extends ChannelDuplexHandler {
	private Player player;
	
	public PacketHandler(Player p) {
		this.player = p;
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext c, Object packet) throws Exception {
		if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInResourcePackStatus")) {
			
		} else {
			super.channelRead(c, packet);
		}
	}
}