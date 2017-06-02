package locationGather;

import gameObjects.ResourceObject;

public class GatherReport {
	
	public ResourceObject resource;
	public int amountGathered;
	public int moneyEarned;
	
	public GatherReport(ResourceObject resource, int amountGathered) {
		this.resource = resource;
		this.amountGathered = amountGathered;
	}

	@Override
	public String toString() {
		return "You have gathered " + amountGathered + " pieces of " + resource.name;
	}
	
	
	
}
