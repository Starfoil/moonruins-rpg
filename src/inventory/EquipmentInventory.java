package inventory;

import items.*;

import java.util.ArrayList;

public class EquipmentInventory {

	public Ring leftRing;
	public Ring rightRing;
	public Glove gloves;
	public Boot boots;
	public Pendant pendant;
	public Talisman talisman;
	public ArrayList<Item> itemInventory;
	
	public EquipmentInventory(){
		leftRing = null;
		rightRing = null;
		gloves = null;
		boots = null;
		pendant = null;
		talisman = null;
		itemInventory = new ArrayList<Item>();
	}

	public void addItem(Item item){
		itemInventory.add(item);
	}

	public void removeItem(Item item){
		if(itemInventory.contains(item)){
			itemInventory.remove(item);
		}
	}

	public void equip(Item item, int slot){
		if (slot == 0 && leftRing == null && (item instanceof Ring)){
			leftRing = (Ring) item;
			removeItem(item);
		}
		else if (slot == 1 && rightRing == null && (item instanceof Ring)){
			rightRing = (Ring) item;
			removeItem(item);
		}
		else if (slot == 2 && gloves == null && (item instanceof Glove)){
			gloves = (Glove) item;
			removeItem(item);
		}
		else if (slot == 3 && boots == null && (item instanceof Boot)){
			boots = (Boot) item;
			removeItem(item);
		}
		else if (slot == 4 && pendant == null && (item instanceof Pendant)){
			pendant = (Pendant) item;
			removeItem(item);
		}
		else if (slot == 5 && talisman == null && (item instanceof Talisman)){
			talisman = (Talisman) item;
			removeItem(item);
		}
	}

	public void deEquip(Item item){
		if(leftRing.equals(item)){
			leftRing = null;
			addItem(item);
		}
		else if(rightRing.equals(item)){
			rightRing = null;
			addItem(item);
		}
		else if(gloves.equals(item)){
			gloves = null;
			addItem(item);
		}
		else if(boots.equals(item)){
			boots = null;
			addItem(item);
		}
		else if(pendant.equals(item)){
			pendant = null;
			addItem(item);
		}
		else if(talisman.equals(item)){
			talisman = null;
			addItem(item);
		}
	}
	
	public void deEquip(int slot){
		if (slot == 0 && leftRing != null){
			addItem(leftRing);
			leftRing = null;
		}
		else if (slot == 1 && rightRing != null){
			addItem(rightRing);
			rightRing = null;
		}
		else if (slot == 2 && gloves != null){
			addItem(gloves);
			gloves = null;
		}
		else if (slot == 3 && boots != null){
			addItem(boots);
			boots = null;
		}
		else if (slot == 4 && pendant != null){
			addItem(pendant);
			pendant = null;
		}
		else if (slot == 5 && talisman != null){
			addItem(talisman);
			talisman = null;
		}
	}

	public void deEquipAll(){
		deEquip(leftRing);
		deEquip(rightRing);
		deEquip(gloves);
		deEquip(boots);
		deEquip(pendant);
		deEquip(talisman);
	}

	
	public String displayItems(){
		String s = "";
		if (this.itemInventory.isEmpty()){
			return "Inventory is empty\n";
		}
		for( Item I : this.itemInventory){
			s += "[" + I.itemID +"]  " + I.name + "\r\n";
		}
		return s;
	}
	
	public String displayEquipped(){
		String s = "[Left Ring : ";
		if(leftRing != null){s += leftRing.name;}else{s+= "EMPTY";}
		s += "]  [Right Ring : ";
		if(rightRing != null){s += rightRing.name;}else{s+= "EMPTY";}
		s += "]  [Gloves : ";
		if(gloves != null){s += gloves.name;}else{s+= "EMPTY";}
		s += "]\n[Boots : ";
		if(boots != null){s += boots.name;}else{s+= "EMPTY";}
		s += "]  [Pendant : ";
		if(pendant != null){s += pendant.name;}else{s+= "EMPTY";}
		s += "]  [Talisman : ";
		if(talisman != null){s += talisman.name;}else{s+= "EMPTY";}
 		s += "]";
 		return s;
	}
	
	@Override
	public String toString() {
		return displayEquipped() + "\r\n" + displayItems();
	}



}
