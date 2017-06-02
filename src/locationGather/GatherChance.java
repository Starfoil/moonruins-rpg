package locationGather;

import gameObjects.ResourceObject;


public class GatherChance {
	
	


	public ResourceObject resource;
	
	public int resourceChance;
	public int resourceMinAmount;
	public int resourceMaxAmount;
	
	public GatherChance(ResourceObject resource, int chance, int minAmount, int maxAmount){
		
		this.resource = resource;
		this.resourceChance = chance;
		this.resourceMinAmount = minAmount;
		this.resourceMaxAmount = maxAmount;
	}


	@Override
	public String toString() {
		return "GatherChance [resource=" + resource + ", chance=" + resourceChance;
	}
	
	
	
}
