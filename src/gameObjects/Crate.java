package gameObjects;

import items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Crate {
	
	public String crateName;
	public int costToRoll;

	public ArrayList<Item> crateItems;
	private int gatheringChance;
	private Random RNG = new Random();
	
	public Crate(String crateName, int costToRoll){
		this.crateName = crateName;
		this.crateItems = new ArrayList<Item>();
		this.gatheringChance = 0;
		this.costToRoll = costToRoll;
	}
	
	public void addItemToCrate(Item item, int chance){
		if(!crateItems.contains(item)){
			item.crateChance = chance;
			gatheringChance += chance;
			crateItems.add(item);
		}
	}
	
	public Item roll(){
		int CHANCE = RNG.nextInt(gatheringChance) + 1;
		int percentage = 0;
		for (Item I : crateItems){
			percentage += I.crateChance;
			if (CHANCE < percentage){
				return I;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String s = "Crate : " + crateName + "\n===============================\n";
		for (Item I : this.crateItems){
			s += I.name + " [" + I.crateChance + "]\n";
		}
		return s;
	}
	
	
}
