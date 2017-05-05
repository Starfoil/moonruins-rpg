package locationGather;

import gameObjects.Stats;

import java.util.ArrayList;
import java.util.Random;

import location.Location;

public class GatherLocation extends Location{

	public ArrayList<GatherChance> gatheringChance;
	private int gatheringPercentage;
	public MoonRuin moonRuin;
	public Random RNG = new Random();

	public GatherLocation(String name){
		super(name);
		gatheringChance = new ArrayList<GatherChance>();
		gatheringPercentage = 0;
	}

	public void addMoonRuin(MoonRuin MR){
		this.moonRuin = MR;
	}

	public void addGatheringChance(GatherChance GC){
		gatheringChance.add(GC);
		gatheringPercentage += GC.chance;
	}

	//Gather System	


	private GatherChance gatherID(){
		int CHANCE = RNG.nextInt(gatheringPercentage) + 1;
		int percentage = 0;
		for (GatherChance GC : gatheringChance){
			percentage += GC.chance;
			if (CHANCE <= percentage){
				return GC;
			}
		}
		return null;
	}

	private int gatherRarity(GatherChance GC, Stats stat){
		int reinforcedBoost = GC.reinforcedChance * stat.RFF / 100;
		int enrichedBoost = GC.enrichedChance * stat.ERF / 100;
		int augmentedBoost = GC.augmentedChance * stat.AMF / 100;
		int CHANCE = RNG.nextInt(gatheringPercentage) + 1 + reinforcedBoost + enrichedBoost + augmentedBoost;
		if (CHANCE <= GC.regularChance){
			return 0;
		}
		else if (CHANCE <= GC.regularChance + GC.reinforcedChance + reinforcedBoost){
			return 1;
		}
		else if (CHANCE <= GC.regularChance + GC.reinforcedChance + GC.enrichedChance + reinforcedBoost + enrichedBoost){
			return 2;
		}
		else if (CHANCE <= GC.regularChance + GC.reinforcedChance + GC.enrichedChance + GC.augmentedChance + 
				reinforcedBoost + enrichedBoost + augmentedBoost){
			return 3;
		}
		else return -1;
	}

	private int gatherAmount(GatherChance GC, int rarityID, Stats stat){
		if (rarityID == 0){
			int amountGathered = RNG.nextInt(GC.regularAmount) + 1 + GC.regularMAmount;
			int amountExtra = amountGathered * stat.WIL / 100;
			return amountGathered + amountExtra;
		}
		else if (rarityID == 1){
			int amountGathered = RNG.nextInt(GC.reinforcedAmount) + 1 + GC.reinforcedMAmount;
			int amountExtra = amountGathered * stat.RFW / 100;
			return amountGathered + amountExtra;
		}
		else if (rarityID == 2){
			int amountGathered = RNG.nextInt(GC.enrichedAmount) + 1 + GC.enrichedMAmount;
			int amountExtra = amountGathered * stat.ERW / 100;
			return amountGathered + amountExtra;
		}
		else if (rarityID == 3){
			int amountGathered = RNG.nextInt(GC.augmentedAmount) + 1 + GC.augmentedMAmount;
			int amountExtra = amountGathered * stat.AMW / 100;
			return amountGathered + amountExtra;
		}
		else return -1;
	}

	private int gatherMoney(GatherChance GC, int rarityID, int amount, int affluenceBoost){
		if (rarityID == 0){
			int moneyEarned = GC.resource.regularMoney * amount;
			int moneyExtra = moneyEarned * affluenceBoost / 100;
			return moneyEarned + moneyExtra;
		}
		else if (rarityID == 1){
			int moneyEarned = GC.resource.reinforcedMoney * amount;
			int moneyExtra = moneyEarned * affluenceBoost / 100;
			return moneyEarned + moneyExtra;
		}
		else if (rarityID == 2){
			int moneyEarned = GC.resource.enrichedMoney * amount;
			int moneyExtra = moneyEarned * affluenceBoost / 100;
			return moneyEarned + moneyExtra;
		}
		else if (rarityID == 3){
			int moneyEarned = GC.resource.augmentedMoney * amount;
			int moneyExtra = moneyEarned * affluenceBoost / 100;
			return moneyEarned + moneyExtra;
		}
		else return -1;
	}

	public GatherReport gather(Stats stat){
		if(!this.gatheringChance.isEmpty()){
			GatherChance ID = gatherID();
			int rarity = gatherRarity(ID, stat);
			int amount = gatherAmount(ID, rarity, stat);
			int money = gatherMoney(ID, rarity, amount, stat.AFF);
			GatherReport GR = new GatherReport(ID.resource, rarity, amount, money);
			return GR;
		}
		return null;
	}


}
