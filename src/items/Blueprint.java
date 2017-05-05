package items;

import gameObjects.ResourcePackage;

import java.util.ArrayList;

public class Blueprint {
	
	public Item item;
	
	public ArrayList<ResourcePackage> components;

	public Blueprint(Item item) {
		this.item = item;
		this.components = new ArrayList<ResourcePackage>();
	}
	
	public void addComponenet(ResourcePackage RP){
		this.components.add(RP);
	}

	@Override
	public String toString() {
		String s = "[ Item : " + item.name + "]\n";
		for (ResourcePackage RP : this.components){
			s += RP.toString() + "\n";
		}
		return s;
	}
	
	
	
}
