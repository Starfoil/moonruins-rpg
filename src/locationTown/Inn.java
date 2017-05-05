package locationTown;

import gameObjects.Character;

import java.util.ArrayList;
import java.util.Random;

public class Inn {
	
	public ArrayList<Character> innCharacters;
	private int recruitChance;
	public int fireRequired;
	
	public Random RNG = new Random();
	
	public Inn(int fireRequired){
		this.fireRequired = fireRequired;
		this.innCharacters = new ArrayList<Character>();
		recruitChance = 0;
	}
	
	public boolean canRecruit(){
		return !innCharacters.isEmpty() && recruitChance != 0;
	}
	
	public void addCharacterToInn(Character cha, int chance){
		if(!this.innCharacters.contains(cha)){
			this.recruitChance += chance;
			cha.setObtainChance(chance);
			this.innCharacters.add(cha);
		}
	}
	
	public Character recruit(){
		if(!this.innCharacters.isEmpty() && canRecruit()){
			int tempPercentage = 0;
			int chance = RNG.nextInt(this.recruitChance) + 1;
			for (Character C : this.innCharacters){
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
		for (Character C : innCharacters){
			s += "[" + C.characterID + "] " + C.name + "\n";
		}
		return s;
	}
	
	

}
