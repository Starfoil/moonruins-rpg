package gameObjects;

public class Follower {

	public int characterID;
	public String name;
	public int rarityID;
	public int chanceOfObtain;

	public String description;
	public String lore;

	public Stats stats;

	public Follower(int characterID, String name, int rarityID) {
		this.characterID = characterID;
		this.name = name;
		this.rarityID = rarityID;
	}

	public void setDescription(String desc){
		this.description = desc;
	}
	
	public void setLore(String lore){
		this.lore = lore;
	}

	public void setStats(Stats stats){
		this.stats = stats;
	}
	
	public void setObtainChance(int chance){
		this.chanceOfObtain = chance;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Follower other = (Follower) obj;
		if (characterID != other.characterID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID : " + characterID + "]     " + description + "\n"
				+ "Rarity ID : " + rarityID + "\n"
				+ stats + "\n==============================\n"
				+ lore + "\n";
	}
	
	
	
	

}
