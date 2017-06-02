package Data;

import gameObjects.Follower;
import gameObjects.Crate;
import gameObjects.Quest;
import gameObjects.ResourceObject;
import items.Item;

import java.util.ArrayList;

import Player.PLAYER;
import location.Connection;
import location.Location;
import locationTown.TownLocation;

public class DataStorage {

	public static ArrayList<Connection> CON = new ArrayList<Connection>();
	public static ArrayList<ResourceObject> RES = new ArrayList<ResourceObject>();
	public static ArrayList<Location> LOC = new ArrayList<Location>();
	public static ArrayList<Follower> CHR = new ArrayList<Follower>();
	public static ArrayList<Item> ITM = new ArrayList<Item>();
	public static ArrayList<Quest> QST = new ArrayList<Quest>();
	public static ArrayList<Crate> CRA = new ArrayList<Crate>();
	public static ArrayList<TownLocation> TWN = new ArrayList<TownLocation>();
	
	public static void cleanSearch(){
		for (Location L : LOC){
			L.clean();
		}
	}
	
	public static void unlockAll(){
		for (Item I : ITM){
			PLAYER.addItem(I);
		}
		for (Follower F : CHR){
			PLAYER.addCharacter(F);
		}
	}
	
	public static Connection getConnection(String startPoint, String endPoint){
		for (Connection C : CON){
			if(C.locationA.name.equals(startPoint) && C.locationB.name.equals(endPoint) ||
					C.locationA.name.equals(endPoint) && C.locationB.name.equals(startPoint)){
				return C;
			}
		}
		return null;
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
	
	public static Item getItem(String itemName){
		for (Item I : ITM){
			if(I.name.equals(itemName)){
				return I;
			}
		}
		return null;
	}

	public static Follower getCharacter(int charID){
		for (Follower C : CHR){
			if(C.characterID == charID){
				return C;
			}
		}
		return null;
	}
	
	public static Follower getCharacter(String name){
		for (Follower C : CHR){
			if(C.name.equals(name)){
				return C;
			}
		}
		return null;
	}

	public static ResourceObject getResource(String name){
		for (ResourceObject R : RES){
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
