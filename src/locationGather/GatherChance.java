package locationGather;

import gameObjects.Resource;


public class GatherChance {
	
	


	public Resource resource;
	
	public int chance;
	
	public int regularChance;
	public int reinforcedChance;
	public int enrichedChance;
	public int augmentedChance;
	
	public int regularAmount;
	public int reinforcedAmount;
	public int enrichedAmount;
	public int augmentedAmount;
	
	public int regularMAmount;
	public int reinforcedMAmount;
	public int enrichedMAmount;
	public int augmentedMAmount;
	
	public GatherChance(Resource resource, int chance,
			
			int regularChance,
			int reinforcedChance, 
			int enrichedChance, 
			int augmentedChance,
			
			int regularAmount, 
			int reinforcedAmount, 
			int enrichedAmount,
			int augmentedAmount,
			
			int regularMAmount,
			int reinforcedMAmount,
			int enrichedMAmount,
			int augmentedMAmount
			) {
		
		this.resource = resource;
		this.chance = chance;
		
		if (regularChance + reinforcedChance + enrichedChance + augmentedChance == 100){
			this.regularChance = regularChance;
			this.reinforcedChance = reinforcedChance;
			this.enrichedChance = enrichedChance;
			this.augmentedChance = augmentedChance;
		}
		else{
			System.out.println("ERROR");
		}
		this.regularAmount = regularAmount;
		this.reinforcedAmount = reinforcedAmount;
		this.enrichedAmount = enrichedAmount;
		this.augmentedAmount = augmentedAmount;
		
		this.regularMAmount = regularMAmount;
		this.reinforcedMAmount = reinforcedMAmount;
		this.enrichedMAmount = enrichedMAmount;
		this.augmentedMAmount = augmentedMAmount;
		

	}


	@Override
	public String toString() {
		return "GatherChance [resource=" + resource + ", chance=" + chance
				+ ", regularChance=" + regularChance + ", reinforcedChance="
				+ reinforcedChance + ", enrichedChance=" + enrichedChance
				+ ", augmentedChance=" + augmentedChance + ", regularAmount="
				+ regularAmount + ", reinforcedAmount=" + reinforcedAmount
				+ ", enrichedAmount=" + enrichedAmount + ", augmentedAmount="
				+ augmentedAmount + ", regularMoney=";
	}
	
	
	
}
