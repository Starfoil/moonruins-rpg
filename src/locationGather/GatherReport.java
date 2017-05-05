package locationGather;

import gameObjects.Resource;

public class GatherReport {
	
	public Resource resource;
	
	public int rarityGathered;
	public int amountGathered;
	public int moneyEarned;
	
	public GatherReport(Resource resource, int rarityGathered, int amountGathered,
			int moneyEarned) {
		this.resource = resource;
		this.rarityGathered = rarityGathered;
		this.amountGathered = amountGathered;
		this.moneyEarned = moneyEarned;
	}

	@Override
	public String toString() {
		return "You have gathered " + amountGathered + " pieces of " + resource 
				+ " of rarity " + rarityGathered + " earning you $" + moneyEarned + ".";
	}
	
	
	
}
