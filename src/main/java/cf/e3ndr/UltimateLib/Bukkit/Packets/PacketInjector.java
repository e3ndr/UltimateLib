package cf.e3ndr.UltimateLib.Bukkit.Packets;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import io.netty.channel.Channel;

public class PacketInjector {

	  private Field EntityPlayer_playerConnection;
	  private Class<?> PlayerConnection;
	  private Field PlayerConnection_networkManager;

	  private Class<?> NetworkManager;
	  private Field channel;

	  public PacketInjector() {
		  try {
			  EntityPlayer_playerConnection = Reflection.getField(Reflection.getClass("{nms}.EntityPlayer"), "playerConnection");
			  
			  PlayerConnection = Reflection.getClass("{nms}.PlayerConnection");
			  PlayerConnection_networkManager = Reflection.getField(PlayerConnection, "networkManager");
			  
			  NetworkManager = Reflection.getClass("{nms}.NetworkManager");
			  
			  for (Field field : Reflection.getClass("{nms}.NetworkManager").getDeclaredFields()) {
				  if (field.getType().getSimpleName().equalsIgnoreCase("Channel") ) {
					  channel = Reflection.getField(NetworkManager, "channel");
				  }
			  }
			  
		  } catch (Throwable t) {
			  t.printStackTrace();
		  }
	  }

	  public void addPlayer(Player p) {
		  try {
			  Channel ch = getChannel(getNetworkManager(Reflection.getNmsPlayer(p)));
			  if(ch.pipeline().get("PacketInjector") == null) {
				  PacketHandler h = new PacketHandler(p);
				  ch.pipeline().addBefore("packet_handler", "PacketInjector", h);
			  }
		  } catch (Throwable t) {
			  t.printStackTrace();
		  }
	  }

	  public void removePlayer(Player p) {
		  try {
			  Channel ch = getChannel(getNetworkManager(Reflection.getNmsPlayer(p)));
			  if(ch.pipeline().get("PacketInjector") != null) {
				  ch.pipeline().remove("PacketInjector");
			  }
		  } catch (Throwable t) {
			  t.printStackTrace();
		  }
	  }

	  private Object getNetworkManager(Object ep) {
		  return Reflection.getFieldValue(PlayerConnection_networkManager, Reflection.getFieldValue(EntityPlayer_playerConnection, ep));
	  }

	  private Channel getChannel(Object networkManager) {
		  return (Channel) Reflection.getFieldValue(channel, networkManager);
	  }
	  
}