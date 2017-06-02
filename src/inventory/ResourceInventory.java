package inventory;

import gameObjects.ResourceObject;
import gameObjects.ResourcePackage;

import java.util.ArrayList;

public class ResourceInventory {

	public ArrayList<ResourcePackage> resources;

	public ResourceInventory() {
		this.resources = new ArrayList<ResourcePackage>();
	}

	private ResourcePackage getInventory(ResourceObject item){
		for (ResourcePackage R : this.resources){
			if (R.resource.equals(item)){
				return R;
			}
		}
		
		return null;
	}
	
	public void addItems(ResourceObject item, int addAmount){
		ResourcePackage R = getInventory(item);
		if (R != null) R.add(addAmount);
		else{
			this.resources.add(new ResourcePackage(item, addAmount));
		}
	}

	public int removeItems(ResourceObject item, int removeAmount){
		ResourcePackage R = getInventory(item);
		if (R != null) return R.remove(removeAmount);
		else return -1;
	}

	public boolean hasItems(ResourceObject item, int checkAmount){
		ResourcePackage R = getInventory(item);
		if (R != null) return R.check(checkAmount);
		else return false;
	}

	@Override
	public String toString() {
		String S = "";
		for (ResourcePackage R : this.resources){
			S += R.toString() + "\n";
		}
		return S;
	}
}
