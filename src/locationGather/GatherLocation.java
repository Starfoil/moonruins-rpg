package locationGather;

import gameObjects.Stats;

import java.util.ArrayList;
import java.util.Random;

import Player.PLAYER;
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
		gatheringPercentage += GC.resourceChance;
	}

	//Gather System	

	// Get which resource we're gathering from this location
	private GatherChance gatherID(){
		int CHANCE = RNG.nextInt(gatheringPercentage) + 1;
		int percentage = 0;
		for (GatherChance GC : gatheringChance){
			percentage += GC.resourceChance;
			if (CHANCE <= percentage){
				return GC;
			}
		}
		return null;
	}

	// Get the amount of resource we're gathering
	private int gatherAmount(GatherChance GC, double boostAmount){
		int amountGathered = RNG.nextInt(GC.resourceMaxAmount - GC.resourceMinAmount) 
				+ GC.resourceMinAmount;
		return (int) (amountGathered * boostAmount);
	}

//	private int gatherMoney(GatherChance GC, int amount, int boostAmount){
//		int moneyEarned = GC.resource.value * amount;
//		return (int) (moneyEarned * boostAmount);
//	}

	public GatherReport gather(){
		if(!this.gatheringChance.isEmpty()){
			GatherChance ID = gatherID();
			int amount = gatherAmount(ID, PLAYER.stat.multiplier);
			GatherReport GR = new GatherReport(ID.resource, amount);
			return GR;
		}
		return null;
	}


}
