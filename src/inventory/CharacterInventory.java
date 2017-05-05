package inventory;

import gameObjects.Character;

import java.util.ArrayList;

public class CharacterInventory {

	public Character left;
	public Character right;
	public Character middle;

	public ArrayList<Character> characterInventory;

	public CharacterInventory(){
		left = null;
		right = null;
		middle = null;
		characterInventory = new ArrayList<Character>();
	}

	public void addCharacter(Character cha){
		characterInventory.add(cha);
	}

	public void removeCharacter(Character cha){
		if(characterInventory.contains(cha)){
			characterInventory.remove(cha);
		}
	}

	public void equipCharacter(Character cha, int slot){
		if(slot == 1 && middle == null){
			middle = cha;
			removeCharacter(cha);
		}
		else if (slot == 2 && left == null){
			left = cha;
			removeCharacter(cha);
		}
		else if (slot == 3 && right == null){
			right = cha;
			removeCharacter(cha);
		}

	}

	public void deEquipCharacter(int slot){
		if (slot == 1 && middle != null){
			addCharacter(middle);
			middle = null;
		}
		else if (slot == 2 && left != null){
			addCharacter(left);
			left = null;
		}
		else if (slot == 3 && right != null){
			addCharacter(right);
			right = null;
		}
	}
	
	public String displayParty(){
		String s =  "[Slot 1 : ";
		if(middle != null){s += middle.name;}else{s+= "EMPTY";}
		s += "]  [Slot 2 : ";
		if(left != null){s += left.name;}else{s+= "EMPTY";}
		s += "]  [Slot 3 : ";
		if(right != null){s += right.name;}else{s+= "EMPTY";}
		s += "]";
		return s;
	}
	
	public String displayCharacters(){
		String s = "";
		for (Character C : characterInventory){
			s += "[" + C.characterID +"]  " + C.name + "\r\n";
		}
		return s;
	}

	@Override
	public String toString() {
		return displayParty() + "\n\n" + displayCharacters();
	}









}
