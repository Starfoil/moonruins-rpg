package gameObjects;

public class Reward {
	
	public Quest quest;
	
	public int fire;
	public int keys;
	public int money;
	
	public Reward(Quest quest, int fire, int keys, int money){
		this.quest = quest;
		this.fire = fire;
		this.money = money;
		this.keys = keys;
	}

}
