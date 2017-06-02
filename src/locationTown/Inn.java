package locationTown;

import gameObjects.Follower;

import java.util.ArrayList;
import java.util.Random;

public class Inn {
	
	public ArrayList<Follower> innCharacters;
	private int recruitChance;
	public int fireRequired;
	
	public Random RNG = new Random();
	
	public Inn(int fireRequired){
		this.fireRequired = fireRequired;
		this.innCharacters = new ArrayList<Follower>();
		recruitChance = 0;
	}
	
	public boolean canRecruit(){
		return !innCharacters.isEmpty() && recruitChance != 0;
	}
	
	public void addCharacterToInn(Follower cha, int chance){
		if(!this.innCharacters.contains(cha)){
			this.recruitChance += chance;
			cha.setObtainChance(chance);
			this.innCharacters.add(cha);
		}
	}
	
	public Follower recruit(){
		if(!this.innCharacters.isEmpty() && canRecruit()){
			int tempPercentage = 0;
			int chance = RNG.nextInt(this.recruitChance) + 1;
			for (Follower C : this.innCharacters){
				tempPercentage += C.chanceOfObtain;
				if(chance < tempPercentage){
					this.innCharacters.remove(C);
					this.recruitChance -= C.chanceOfObtain;
					return C;
				}
			}
		}
		return null;
	}


	@Override
	public String toString() {
		String s = "[Fire : " + this.fireRequired + "] \n\n";
		for (Follower C : innCharacters){
			s += "[" + C.characterID + "] " + C.name + "\n";
		}
		return s;
	}
	
	

}
