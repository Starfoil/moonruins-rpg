package locationGather;

import java.util.Random;

public class MoonRuin {
	
	private final Random RNG = new Random();
	public int encounterChance;
	
	public int sapphireChance;
	public int emeraldChance;
	public int rubyChance;
	public int starChance;
	
	public int sapphireAmount;
	public int emeraldAmount;
	public int rubyAmount;
	public int starAmount;
	
	public MoonRuin(int encounterChance, int sapphireChance, int emeraldChance,
			int rubyChance, int starChance, int sapphireAmount,
			int emeraldAmount, int rubyAmount, int starAmount) {
		this.encounterChance = encounterChance;
		if((sapphireChance + emeraldChance + rubyChance + starChance) == 100){
			this.sapphireChance = sapphireChance;
			this.emeraldChance = emeraldChance;
			this.rubyChance = rubyChance;
			this.starChance = starChance;
		}
		this.sapphireAmount = sapphireAmount;
		this.emeraldAmount = emeraldAmount;
		this.rubyAmount = rubyAmount;
		this.starAmount = starAmount;
	}
	
	public int gatherID(){
		int CHANCE = RNG.nextInt(100) + 1;
		if (CHANCE <= this.sapphireChance){
			return 0;
		}
		else if (CHANCE <= this.sapphireChance + this.emeraldChance){
			return 1;
		}
		else if (CHANCE <= this.sapphireChance + this.emeraldChance + this.rubyChance){
			return 2;
		}
		else if (CHANCE <= this.sapphireChance + this.emeraldChance + this.rubyChance + this.starChance){
			return 3;
		}
		else return -1;
	}
	
	public int gatherAmount(int gemID){
		if (gemID == 0){
			return RNG.nextInt(this.sapphireAmount) + 1;
		}
		else if (gemID == 1){
			return RNG.nextInt(this.emeraldAmount) + 1;
		}
		else if (gemID == 2){
			return RNG.nextInt(this.rubyAmount) + 1;
		}
		else if (gemID == 3){
			return RNG.nextInt(this.starAmount) + 1;
		}
		else return -1;
	}
	
	
	

}
