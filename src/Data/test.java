package Data;

import gameObjects.Crate;
import items.*;

import java.io.IOException;

import location.*;
import locationTown.Workshop;
import Player.PLAYER;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DataLoader.loadResources("DATA\\resources.csv");
		//System.out.println(DataStorage.RES);
		
		DataLoader.loadGatherLocations("DATA\\locations.txt");	
		//System.out.println(DataStorage.LOC);
		
		DataLoader.loadItems("DATA\\items.csv");
		//System.out.println(DataStorage.ITM);
		
		DataLoader.loadCharacters("DATA\\characters.txt");
		//System.out.println(DataStorage.CHR);
		
		DataLoader.loadQuests("DATA\\quests.txt");
		
		DataLoader.loadTowns("DATA\\towns.txt");
		
		DataLoader.loadConnections("DATA\\connections.txt");
		//System.out.println(DataStorage.CON);
		
		DataLoader.loadCrates("DATA\\crates.txt");
		
		//DataLoader.loadBlueprints("DATA\\blueprints.txt");
		
		DataLoader.loadSaveData("DATA\\playerdata.txt");
		
		
		DataSaver.saveConnections("DATA\\Nconnections.txt");
		
		DataSaver.savePlayerData("DATA\\Nplayerdata.txt");
		
		//DataSaver.saveTowns("DATA\\towns.txt");
		
		
		//System.out.println(DataStorage.getItem(222).name);
		
//		for(int i = 0 ; i < 10; i++){
//			System.out.println(PLAYER.gather());
//			PLAYER.gather();
//		}

//		System.out.println(PLAYER.location.connections);
//		Location l = PLAYER.location.getConnections().get(0);
//		PLAYER.buildConnection(l);
//		PLAYER.travel(l);
//		for (Item I : DataManager.ITM){
//			System.out.println(I);
//		}
//		for (Items.Character C : DataManager.CHR){
//			System.out.println(C);
//		}
		//System.out.println(DataManager.LOC.get(10));
		//System.out.println(PLAYER.display());
		//System.out.println(Workshop.CRAFTABLES);
		
//		Crate C = DataManager.CRA.get(0);
//		System.out.println(C);
//		
//		for (int i = 0; i < 100; i++){
//			System.out.println(C.roll().name);
//		}
		//System.out.println(DataStorage.CON);
		//System.out.println(PLAYER.location);
		//Location x = DataStorage.LOC.get(9);
		//System.out.println(x);
		//System.out.println(PLAYER.location.hasConnection(x));
		//System.out.println(PLAYER.display());
		System.out.println("Success");
	}

}
