package cf.e3ndr.UltimateLib.Wrappers.Inventory.GUI;

import java.util.HashMap;

import cf.e3ndr.UltimateLib.Nukkit.UltimateLibNukkit;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.PlayerInventory;
import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;
import cf.e3ndr.UltimateLib.Wrappers.Player.NukkitPlayer;
import cf.e3ndr.UltimateLib.Wrappers.Player.WrappedPlayer;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;

public class NukkitGUI extends GUI implements Listener {
	private FormWindowSimple form;
	private int id;
	private HashMap<ElementButton, Stack> items = new HashMap<>();
	
	public NukkitGUI(Stack[] inv, String name, int size) {
		super(inv, name, size);
		
		this.form = new FormWindowSimple(name, "");
		for (Stack s : inv) {
			ElementButton button = new ElementButton(s.getName());
			this.form.addButton(button);
			this.items.put(button, s);
		}
		
		Server.getInstance().getPluginManager().registerEvents(this, UltimateLibNukkit.instance);
	}
	
	@EventHandler
	public void onResponse(PlayerFormRespondedEvent e) {
		if (this.closed) {
			PlayerFormRespondedEvent.getHandlers().unregister(this);
		} else if (e.getFormID() == id) {
			FormResponseSimple response = (FormResponseSimple) e.getResponse();
			NukkitPlayer player = new NukkitPlayer(e.getPlayer());
			if (response != null) {
				Stack s = this.items.get(response.getClickedButton());
				if (s == null) return;
				if (!this.listener.clickEvent(this, s, player, false)) {
					PlayerInventory pi = player.getInventory();
					pi.addItem(s);
					pi.update();
				}
			} else {
				this.listener.closeEvent(this, player);
				this.closed = true;
			}
		}
	}
	
	@Override
	public void open(WrappedPlayer<?> player) {
		Player nukkit = (Player) player.getNative();
		
		id = nukkit.showFormWindow(this.form);
	}

}
