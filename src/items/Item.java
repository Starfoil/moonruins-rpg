package items;

import gameObjects.Stats;

public abstract class Item {
	
	public int itemID;
	public String name;
	public int rarityID;
	public int cost;
	public int crateChance;
	
	public Stats stats;
	
	public Item(int itemID, String name, int rarityID, int cost){
		this.itemID = itemID;
		this.name = name;
		this.rarityID = rarityID;
		this.cost = cost;
		this.crateChance = -1;
	}
	
	public void setStats(Stats stats){
		this.stats = stats;
	}
	
	public String displayStats(){
		return this.stats.toString();
	}
	
	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", name=" + name + ", rarityID="
				+ rarityID + ", cost=" + cost + ", stats=" + stats + "]";
	}
	
	
	
}
