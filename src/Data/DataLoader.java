package Data;

import gameObjects.*;
import gameObjects.Follower;
import items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import location.*;
import locationGather.GatherChance;
import locationGather.GatherLocation;
import locationGather.MoonRuin;
import locationTown.Altar;
import locationTown.Inn;
import locationTown.Shop;
import locationTown.Tavern;
import locationTown.TownLocation;
import locationTown.Workshop;
import Player.PLAYER;

public class DataLoader {
	
	public static void LOAD() throws IOException{
		DataLoader.loadResources("DATA\\resources.csv");
		DataLoader.loadGatherLocations("DATA\\locations.txt");	
		DataLoader.loadItems("DATA\\items.csv");
		DataLoader.loadCharacters("DATA\\characters.txt");
		DataLoader.loadQuests("DATA\\quests.txt");
		DataLoader.loadTowns("DATA\\towns.txt");
		DataLoader.loadConnections("DATA\\connections.txt");
		//DataLoader.loadBlueprints("DATA\\blueprints.txt");
		DataLoader.loadCrates("DATA\\crates.txt");
		DataLoader.loadSaveData("DATA\\playerdata.txt");
	}
	
	
	public static void loadCrates(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		String[] tokens;
		while ((line = READER.readLine()) != null) {	
			tokens = line.split(",");
			Crate C = new Crate(tokens[0].trim(), Integer.parseInt(tokens[1].trim()));
			while(!(line = READER.readLine()).equals("END")){
				tokens = line.split(",");
				Item I = DataStorage.getItem(Integer.parseInt(tokens[0].trim()));
				int crateChance = Integer.parseInt(tokens[1].trim());
				C.addItemToCrate(I, crateChance);
			}
			DataStorage.CRA.add(C);
		}
		READER.close();
	}

//	public static void loadBlueprints(String path) throws NumberFormatException, IOException{
//		FileReader FILE = new FileReader(path);
//		BufferedReader READER = new BufferedReader(FILE);
//		String line = "";
//		String[] tokens;
//		while ((line = READER.readLine()) != null) {	
//			Item I = DataStorage.getItem(Integer.parseInt(line));
//			Blueprint B = new Blueprint(I);
//			while(!(line = READER.readLine()).equals("END")){
//				tokens = line.split(","); 
//				Resource RES = DataStorage.getResource(tokens[0].trim());
//				int RESRarity = Integer.parseInt(tokens[1].trim());
//				int RESAmount = Integer.parseInt(tokens[2].trim());
//				ResourcePackage RP = new ResourcePackage(RES, RESRarity, RESAmount);
//				B.addComponenet(RP);
//			}
//			Workshop.CRAFTABLES.add(B);
//		}
//		READER.close();
//	}

	public static void loadQuests(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) {	
			String[] tokens = line.split(",");
			int questID = Integer.parseInt(tokens[0].trim());
			String questName = tokens[1].trim();
			boolean questCompleted = false;
			if(Integer.parseInt(tokens[2].trim()) == 1) {questCompleted = true;}

			line = READER.readLine();
			tokens = line.split(",");
			ResourceObject RES = DataStorage.getResource(tokens[0].trim());
			int RESRarity = Integer.parseInt(tokens[1].trim());
			int RESAmount = Integer.parseInt(tokens[2].trim());	

			line = READER.readLine();
			tokens = line.split(",");
			int rewardFire = Integer.parseInt(tokens[0].trim());
			int rewardKeys = Integer.parseInt(tokens[1].trim());	
			int rewardMoney = Integer.parseInt(tokens[2].trim());

			String DES = "";
			while(!(line = READER.readLine()).equals("END")){
				DES += line + "\n"; 
			}
			Quest Q = new Quest(questID, questName, RES, RESRarity, RESAmount);
			Reward QR = new Reward(Q, rewardFire, rewardKeys, rewardMoney);
			Q.isComplete = questCompleted;
			Q.setReward(QR);
			Q.setDescription(DES);
			DataStorage.QST.add(Q);
		}
		READER.close();
	}

	public static void loadTowns(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) {
			String[] tokens = line.split(",");
			String townName = tokens[0].trim();
			Inn INN = new Inn(Integer.parseInt(tokens[1].trim()));
			Shop SHOP = new Shop();
			Tavern TAVERN = new Tavern();
			Altar ALTAR = new Altar(Integer.parseInt(tokens[2].trim()), 
					Integer.parseInt(tokens[3].trim()), Integer.parseInt(tokens[4].trim()));

			while (!(line = READER.readLine()).equals("ENDINN")){
				tokens = line.split(",");
				Follower C = DataStorage.getCharacter((Integer.parseInt(tokens[0].trim())));
				if (C != null){INN.addCharacterToInn(C, Integer.parseInt(tokens[1].trim()));}
			}
			while (!(line = READER.readLine()).equals("ENDSHOP")){
				tokens = line.split(",");
				Item I = DataStorage.getItem(Integer.parseInt(tokens[0].trim()));
				if (I != null){SHOP.addItemToShop(I);}
			}
			while (!(line = READER.readLine()).equals("END")){
				tokens = line.split(",");
				Quest Q = DataStorage.getQuest(Integer.parseInt(tokens[0].trim()));
				if (Q != null) {TAVERN.addQuest(Q); }
			}
			TownLocation T = new TownLocation(townName, INN, SHOP, TAVERN, ALTAR);
			DataStorage.LOC.add(T);
			DataStorage.TWN.add(T);
		}
		READER.close();
	}

	public static void loadCharacters(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) 
		{
			String[] tokens = line.split(",");
			int ID = Integer.parseInt(tokens[0].trim());
			String name = tokens[1].trim();
			int rarityID = Integer.parseInt(tokens[2].trim());
			Follower C = new Follower(ID, name, rarityID);
			String description = READER.readLine();
			C.setDescription(description);

			line = READER.readLine();
			tokens = line.split(",");
			double hands  = Integer.parseInt(tokens[0].trim());
			double magic = Integer.parseInt(tokens[1].trim());
			int third = Integer.parseInt(tokens[2].trim());
			int fourth = Integer.parseInt(tokens[3].trim());

			Stats S = new Stats(hands, magic);
			C.setStats(S);
			String lore = "";
			while(!(line = READER.readLine()).equals("END")){
				lore += line + "\n";
			}
			C.setLore(lore);
			DataStorage.CHR.add(C);
		}
		READER.close();
	}

	public static void loadItems(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) 
		{
			String[] tokens = line.split(",");
			int ID = Integer.parseInt(tokens[0].trim());
			String name = tokens[1].trim();
			int rarityID = Integer.parseInt(tokens[2].trim());
			int cost = Integer.parseInt(tokens[3].trim());
			Item I;
			if(ID < 100){
				I = new Ring(ID, name, rarityID, cost);
			}
			else if(ID < 200){
				I = new Glove(ID, name, rarityID, cost);
			}
			else if(ID < 300){
				I = new Boot(ID, name, rarityID, cost);
			}
			else if(ID < 400){
				I = new Pendant(ID, name, rarityID, cost);
			}
			else if(ID < 500){
				I = new Talisman(ID, name, rarityID, cost);
			}
			else{
				I = new Ring(ID, name, rarityID, cost);
			}
			double hands = Double.parseDouble(tokens[4].trim());
			double magic = Double.parseDouble(tokens[5].trim());
			int todo1 = Integer.parseInt(tokens[6].trim());
			int todo2 = Integer.parseInt(tokens[7].trim());

			Stats S = new Stats(hands, magic);
			I.setStats(S);
			DataStorage.ITM.add(I);
		}
		READER.close();
	}

	public static void loadResources(String path) throws IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) 
		{
			String[] tokens = line.split(",");
			int ID = Integer.parseInt(tokens[0].trim());
			String name = tokens[1].trim();
			int value = Integer.parseInt(tokens[2].trim());
			String desc = tokens[3].trim();
			ResourceObject R = new ResourceObject(ID, name, value, desc);
			DataStorage.RES.add(R);
		}
		READER.close();
	}	

	public static void loadGatherLocations(String path) throws IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) 
		{
			String LocationName = line;
			GatherLocation L = new GatherLocation(LocationName);
			String desc = "";
			while(!(line = READER.readLine()).equals("ENDDES")){
				desc += line + "\n";
			}
			L.setDescription(desc);
			while(!(line = READER.readLine()).equals("END")){
				String[] tokens = line.split(",");
				if(tokens[0].trim().equals("MR")){
					int encounterChance = Integer.parseInt(tokens[1].trim());
					int sapphireChance = Integer.parseInt(tokens[2].trim());
					int emeraldChance = Integer.parseInt(tokens[3].trim());
					int rubyChance = Integer.parseInt(tokens[4].trim());
					int starChance = Integer.parseInt(tokens[5].trim());
					int sapphireAmount = Integer.parseInt(tokens[6].trim());
					int emeraldAmount = Integer.parseInt(tokens[7].trim());
					int rubyAmount = Integer.parseInt(tokens[8].trim());
					int starAmount = Integer.parseInt(tokens[9].trim());
					MoonRuin MR = new MoonRuin(encounterChance, sapphireChance, emeraldChance,
							rubyChance, starChance, sapphireAmount,
							emeraldAmount, rubyAmount, starAmount);
					L.addMoonRuin(MR);
					tokens = READER.readLine().split(",");
				};
				ResourceObject R = DataStorage.getResource(tokens[0].trim());
				int chance = Integer.parseInt(tokens[1].trim());
				int min = Integer.parseInt(tokens[2].trim());
				int max = Integer.parseInt(tokens[3].trim());
				
				GatherChance GC = new GatherChance(R, chance, min, max);
				L.addGatheringChance(GC);
			}
			DataStorage.LOC.add(L);
		}
		READER.close();
	}
	
	
	public static void loadConnections(String path) throws IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		while ((line = READER.readLine()) != null) {	
			String[] tokens = line.split(",");
			String start = tokens[0].trim();
			String end = tokens[1].trim();
			boolean built;
			if (Integer.parseInt(tokens[2].trim()) == 1){ built = true;}
			else { built = false; }
			Location startLoc = DataStorage.getLocation(start);
			Location endLoc = DataStorage.getLocation(end);
			Connection c = new Connection(startLoc, endLoc);
			c.isBuilt = built;
			c.setTravelCost(Integer.parseInt(tokens[3].trim()));
			while(!(line = READER.readLine()).equals("END")){
				tokens = line.split(",");
				ResourceObject r = DataStorage.getResource(tokens[0].trim());
				int amount = Integer.parseInt(tokens[1].trim());
				c.addConnectionCost(r, amount);
			}
			startLoc.addConnection(c);
			endLoc.addConnection(c);
			DataStorage.CON.add(c);
		}
		READER.close();
	}

	public static void loadSaveData(String path) throws IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		PLAYER.setName(READER.readLine());
		PLAYER.Initialize(DataStorage.getLocation(READER.readLine()));
		String[] tokens = READER.readLine().split(",");
		PLAYER.addCoins(Integer.parseInt(tokens[0].trim()));
		tokens = READER.readLine().split(",");
		PLAYER.addGems(0, Integer.parseInt(tokens[0].trim()));
		PLAYER.addGems(1, Integer.parseInt(tokens[1].trim()));
		PLAYER.addGems(2, Integer.parseInt(tokens[2].trim()));
		PLAYER.addGems(3, Integer.parseInt(tokens[3].trim()));
		tokens = READER.readLine().split(",");
		PLAYER.addFire(Integer.parseInt(tokens[0].trim()));
		PLAYER.addKeys(Integer.parseInt(tokens[1].trim()));
		
		String line = "";
		while(!(line = READER.readLine()).equals("ENDRES")){
			tokens = line.split(",");
			ResourceObject item = DataStorage.getResource(tokens[0].trim());
			int amount = Integer.parseInt(tokens[1].trim());
			PLAYER.addResources(item, amount);
		}

		line = READER.readLine();
		tokens = line.split(",");
		Ring leftRing = (Ring) DataStorage.getItem(Integer.parseInt(tokens[0].trim()));
		Ring rightRing = (Ring) DataStorage.getItem(Integer.parseInt(tokens[1].trim()));
		Glove glove = (Glove) DataStorage.getItem(Integer.parseInt(tokens[2].trim()));
		Boot boot = (Boot) DataStorage.getItem(Integer.parseInt(tokens[3].trim()));
		Pendant pendant = (Pendant) DataStorage.getItem(Integer.parseInt(tokens[4].trim()));
		Talisman talisman = (Talisman) DataStorage.getItem(Integer.parseInt(tokens[5].trim()));

		PLAYER.addItem(leftRing);		
		PLAYER.equipItem(leftRing, 0);
		PLAYER.addItem(rightRing);
		PLAYER.equipItem(rightRing, 1);
		PLAYER.addItem(glove);
		PLAYER.equipItem(glove, 2);
		PLAYER.addItem(boot);
		PLAYER.equipItem(boot, 3);
		PLAYER.addItem(pendant);
		PLAYER.equipItem(pendant, 4);
		PLAYER.addItem(talisman);
		PLAYER.equipItem(talisman, 5);

		while(!(line = READER.readLine()).equals("ENDITM")){
			Item I = DataStorage.getItem(Integer.parseInt(line));
			PLAYER.addItem(I);
		}
		

		line = READER.readLine();
		tokens = line.split(",");
		Follower slot1 = DataStorage.getCharacter(Integer.parseInt(tokens[0].trim()));
		Follower slot2 = DataStorage.getCharacter(Integer.parseInt(tokens[1].trim()));
		Follower slot3 = DataStorage.getCharacter(Integer.parseInt(tokens[2].trim()));
		Follower slot4 = DataStorage.getCharacter(Integer.parseInt(tokens[3].trim()));
		Follower slot5 = DataStorage.getCharacter(Integer.parseInt(tokens[4].trim()));

		
		PLAYER.addCharacter(slot1);
		PLAYER.equipCharacter(slot1, 1);
		PLAYER.addCharacter(slot2);
		PLAYER.equipCharacter(slot2, 2);
		PLAYER.addCharacter(slot3);
		PLAYER.equipCharacter(slot3, 3);
		PLAYER.addCharacter(slot4);
		PLAYER.equipCharacter(slot4, 4);
		PLAYER.addCharacter(slot5);
		PLAYER.equipCharacter(slot5, 5);

		while(!(line = READER.readLine()).equals("END")){
			Follower C = DataStorage.getCharacter(Integer.parseInt(line.trim()));
			PLAYER.addCharacter(C);
		}
		READER.close();
	}

}
