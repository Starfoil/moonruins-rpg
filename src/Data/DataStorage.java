package Data;

import gameObjects.Character;
import gameObjects.Crate;
import gameObjects.Quest;
import gameObjects.Resource;
import items.Item;

import java.util.ArrayList;

import location.Connection;
import location.Location;
import locationTown.TownLocation;

public class DataStorage {

	public static ArrayList<Connection> CON = new ArrayList<Connection>();
	public static ArrayList<Resource> RES = new ArrayList<Resource>();
	public static ArrayList<Location> LOC = new ArrayList<Location>();
	public static ArrayList<Character> CHR = new ArrayList<Character>();
	public static ArrayList<Item> ITM = new ArrayList<Item>();
	public static ArrayList<Quest> QST = new ArrayList<Quest>();
	public static ArrayList<Crate> CRA = new ArrayList<Crate>();
	public static ArrayList<TownLocation> TWN = new ArrayList<TownLocation>();
	
	public static void cleanSearch(){
		for (Location L : LOC){
			L.clean();
		}
	}
	
	public static Quest getQuest(int questID){
		for (Quest Q : QST){
			if(Q.questID == questID){
				return Q;
			}
		}
		return null;
	}
	
	public static Item getItem(int itemID){
		for (Item I : ITM){
			if(I.itemID == itemID){
				return I;
			}
		}
		return null;
	}

	public static Character getCharacter(int charID){
		for (Character C : CHR){
			if(C.characterID == charID){
				return C;
			}
		}
		return null;
	}

	public static Resource getResource(String name){
		for (Resource R : RES){
			if(R.name.equals(name)){
				return R;
			}
		}
		return null;
	}

	public static Location getLocation(String name){
		for (Location L : LOC){
			if(L.name.equals(name)){
				return L;
			}
		}
		return null;
	}
}
