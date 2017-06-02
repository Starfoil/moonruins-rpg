package inventory;

import gameObjects.Follower;

import java.util.ArrayList;

public class CharacterInventory {

	public Follower slot1;
	public Follower slot2;
	public Follower slot3;
	public Follower slot4;
	public Follower slot5;

	public ArrayList<Follower> characterInventory;

	public CharacterInventory(){
		characterInventory = new ArrayList<Follower>();
	}

	public void addCharacter(Follower cha){
		characterInventory.add(cha);
	}

	public void removeCharacter(Follower cha){
		if(characterInventory.contains(cha)){
			characterInventory.remove(cha);
		}
	}

	public void equipCharacter(Follower cha, int slot){
		if(slot == 1 && slot1 == null){
			slot1 = cha;
			removeCharacter(cha);
		}
		else if (slot == 2 && slot2 == null){
			slot2 = cha;
			removeCharacter(cha);
		}
		else if (slot == 3 && slot3 == null){
			slot3 = cha;
			removeCharacter(cha);
		}
		else if (slot == 4 && slot4 == null){
			slot4 = cha;
			removeCharacter(cha);
		}
		else if (slot == 5 && slot5 == null){
			slot5 = cha;
			removeCharacter(cha);
		}
	}

	public void deEquipCharacter(int slot){
		if (slot == 1 && slot1 != null){
			addCharacter(slot1);
			slot1 = null;
		}
		else if (slot == 2 && slot2 != null){
			addCharacter(slot2);
			slot2 = null;
		}
		else if (slot == 3 && slot3 != null){
			addCharacter(slot3);
			slot3 = null;
		}
		else if (slot == 4 && slot4 != null){
			addCharacter(slot4);
			slot4 = null;
		}
		else if (slot == 5 && slot5 != null){
			addCharacter(slot5);
			slot5 = null;
		}
	}
	
	public Follower getFollower(int slot){
		if (slot == 1 && slot1 != null){
			return slot1;
		}
		else if (slot == 2 && slot2 != null){
			return slot2;
		}
		else if (slot == 3 && slot3 != null){
			return slot3;
		}
		else if (slot == 4 && slot4 != null){
			return slot4;
		}
		else if (slot == 5 && slot5 != null){
			return slot5;
		}
		return null;
	}
	
//	public String displayParty(){
//		String s =  "[Slot 1 : ";
//		if(middle != null){s += middle.name;}else{s+= "EMPTY";}
//		s += "]  [Slot 2 : ";
//		if(left != null){s += left.name;}else{s+= "EMPTY";}
//		s += "]  [Slot 3 : ";
//		if(right != null){s += right.name;}else{s+= "EMPTY";}
//		s += "]";
//		return s;
//	}
//	
//	public String displayCharacters(){
//		String s = "";
//		for (Character C : characterInventory){
//			s += "[" + C.characterID +"]  " + C.name + "\r\n";
//		}
//		return s;
//	}

	@Override
	public String toString() {
		return "";
	}









}
