package inventory;

import gameObjects.Resource;

import java.util.ArrayList;

public class ResourceInventory {

	public ArrayList<ResourceSubInventory> resources;

	public ResourceInventory() {
		this.resources = new ArrayList<ResourceSubInventory>();
	}

	public void addInventory(Resource item){
		this.resources.add(new ResourceSubInventory(item));
	}
	
	public boolean hasResource(Resource item){
		for (ResourceSubInventory RI : resources){
			if (RI.resource.equals(item)){
				return true;
			}
		}
		return false;
	}

	public ResourceSubInventory getInventory(Resource item){
		for (ResourceSubInventory RI : this.resources){
			if (RI.resource.equals(item)){
				return RI;
			}
		}
		return null;
	}

	public void addItems(Resource item, int rarityID, int amount){
		ResourceSubInventory RI = getInventory(item);
		RI.add(rarityID, amount);
	}

	public void removeItems(Resource item, int rarityID, int amount){
		ResourceSubInventory RI = getInventory(item);
		RI.remove(rarityID, amount);
	}

	public boolean hasItems(Resource item, int rarityID, int amount){
		ResourceSubInventory RI = getInventory(item);
		return RI.checkAmount(rarityID, amount);
	}

	@Override
	public String toString() {
		String S = "";
		for (ResourceSubInventory RI : this.resources){
			if (!RI.isEmpty()) {S += RI.toString() + "\r\n";}
		}
		return S;
	}
	
	public void sort(){
		
	}


}
