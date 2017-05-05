package locationTown;

import gameObjects.Quest;
import gameObjects.Reward;

import java.util.ArrayList;


public class Tavern {
	
	public ArrayList<Quest> tavernQuests;
	
	public Tavern(){
		this.tavernQuests = new ArrayList<Quest>();
	}
	
	public void addQuest(Quest quest){
		if(!tavernQuests.contains(quest)){
			tavernQuests.add(quest);
		}
	}
	
	public Reward completeQuest(Quest quest){
		if(tavernQuests.contains(quest) && !quest.isComplete){
			tavernQuests.remove(quest);
			return quest.completeQuest();
		}
		return null;
	}
	
	public boolean hasQuest(){
		return !tavernQuests.isEmpty();
	}

	@Override
	public String toString() {
		String s = "\n";
		for (Quest Q : tavernQuests){
			s += "[" + Q.questID + "] " + Q.name + "\n";
		}
		return s;
	}
	
	

}
