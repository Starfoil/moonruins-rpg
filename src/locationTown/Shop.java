package locationTown;

import items.*;

import java.util.ArrayList;

public class Shop {
	
	public ArrayList<Item> shopInventory;
	
	public Shop(){
		this.shopInventory = new ArrayList<Item>();
	}
	
	public boolean canPurchase(){
		return !shopInventory.isEmpty();
	}
	
	public void addItemToShop(Item item){
		if(!shopInventory.contains(item)){
			shopInventory.add(item);
		}
	}
	
	public Item purchaseItem(Item item){
		if(shopInventory.contains(item)){
			shopInventory.remove(item);
			return item;
		}
		return null;
	}

	@Override
	public String toString() {
		String s = "\n";
		for (Item I : shopInventory){
			s += "[" + I.itemID + "] " + I.name + "\n";
		}
		return s;
	}
	
	
	
}
