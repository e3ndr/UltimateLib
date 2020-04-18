package cf.e3ndr.UltimateLib.Wrappers.Events;

import java.util.ArrayList;
import java.util.List;

import cf.e3ndr.UltimateLib.Wrappers.Inventory.Stack;

public class EventEntityDeath extends Event {
	private String type;
	private List<Stack> drops = new ArrayList<>();
	
	public EventEntityDeath(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public List<Stack> getDrops() {
		return this.drops;
	}

	public void setDrops(List<Stack> drops) {
		this.drops = drops;
	}
	
	@Override
	public boolean isCancelled() {
		return false;
	}
	
	@Override
	public EventType getEventType() {
		return EventType.ENTITYDEATH;
	}
	
}
