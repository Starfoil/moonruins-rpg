package gameObjects;


public class Quest {
	
	public int questID;
	public String name;
	
	public ResourceObject resourceRequired;
	public int rarityRequired;
	public int amountRequired;
	
	private Reward questReward;
	public boolean isComplete;
	
	public String questDescription;

	public Quest(int questID, String name, ResourceObject resourceRequired,
			int rarityRequired, int amountRequired) {
		this.questID = questID;
		this.name = name;
		this.resourceRequired = resourceRequired;
		this.rarityRequired = rarityRequired;
		this.amountRequired = amountRequired;
		this.questReward = new Reward(this, 0, 0, 0);
		this.isComplete = false;
	}
	
	public void setReward(Reward reward){
		this.questReward = reward;
	}
	
	public void setDescription(String questDescription){
		this.questDescription = questDescription;
	}
	
	public Reward completeQuest(){
		this.isComplete = true;
		return questReward;
	}

	@Override
	public String toString() {
		return "Quest [questID=" + questID + ", name=" + name
				+ ", resourceRequired=" + resourceRequired
				+ ", rarityRequired=" + rarityRequired + ", amountRequired="
				+ amountRequired + ", questReward=" + questReward
				+ ", isComplete=" + isComplete + ", questDescription="
				+ questDescription + "]";
	}
	
}
