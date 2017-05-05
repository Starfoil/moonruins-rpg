package Data;

import gameObjects.*;
import gameObjects.Character;
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
		DataLoader.loadResources("DATA\\resources.txt");
		DataLoader.loadGatherLocations("DATA\\locations.txt");	
		DataLoader.loadItems("DATA\\items.csv");
		DataLoader.loadCharacters("DATA\\characters.txt");
		DataLoader.loadQuests("DATA\\quests.txt");
		DataLoader.loadTowns("DATA\\towns.txt");
		DataLoader.loadConnections("DATA\\connections.txt");
		DataLoader.loadBlueprints("DATA\\blueprints.txt");
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

	public static void loadBlueprints(String path) throws NumberFormatException, IOException{
		FileReader FILE = new FileReader(path);
		BufferedReader READER = new BufferedReader(FILE);
		String line = "";
		String[] tokens;
		while ((line = READER.readLine()) != null) {	
			Item I = DataStorage.getItem(Integer.parseInt(line));
			Blueprint B = new Blueprint(I);
			while(!(line = READER.readLine()).equals("END")){
				tokens = line.split(","); 
				Resource RES = DataStorage.getResource(tokens[0].trim());
				int RESRarity = Integer.parseInt(tokens[1].trim());
				int RESAmount = Integer.parseInt(tokens[2].trim());
				ResourcePackage RP = new ResourcePackage(RES, RESRarity, RESAmount);
				B.addComponenet(RP);
			}
			Workshop.CRAFTABLES.add(B);
		}
		READER.close();
	}

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
			Resource RES = DataStorage.getResource(tokens[0].trim());
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
				Character C = DataStorage.getCharacter((Integer.parseInt(tokens[0].trim())));
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
			Character C = new Character(ID, name, rarityID);
			String description = READER.readLine();
			C.setDescription(description);

			line = READER.readLine();
			tokens = line.split(",");
			int RFF = Integer.parseInt(tokens[0].trim());
			int ERF = Integer.parseInt(tokens[1].trim());
			int AMF = Integer.parseInt(tokens[2].trim());
			int SRF = Integer.parseInt(tokens[3].trim());

			line = READER.readLine();
			tokens = line.split(",");
			int WIL = Integer.parseInt(tokens[0].trim());
			int RFW = Integer.parseInt(tokens[1].trim());
			int ERW = Integer.parseInt(tokens[2].trim());
			int AMW = Integer.parseInt(tokens[3].trim());

			line = READER.readLine();
			tokens = line.split(",");
			int EML = Integer.parseInt(tokens[0].trim());
			int SPL = Integer.parseInt(tokens[1].trim());
			int RBL = Integer.parseInt(tokens[2].trim());
			int SRL = Integer.parseInt(tokens[3].trim());

			line = READER.readLine();
			tokens = line.split(",");
			int MNC = Integer.parseInt(tokens[0].trim());
			int AFF = Integer.parseInt(tokens[1].trim());
			int VEN = Integer.parseInt(tokens[2].trim());
			int HAS = Integer.parseInt(tokens[3].trim());

			Stats S = new Stats(RFF, ERF, AMF, SRF, WIL, RFW, ERW, AMW, EML, SPL, RBL, SRL, MNC, AFF, VEN, HAS);
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
			int RFF = Integer.parseInt(tokens[4].trim());
			int ERF = Integer.parseInt(tokens[5].trim());
			int AMF = Integer.parseInt(tokens[6].trim());
			int SRF = Integer.parseInt(tokens[7].trim());
			int WIL = Integer.parseInt(tokens[8].trim());
			int RFW = Integer.parseInt(tokens[9].trim());
			int ERW = Integer.parseInt(tokens[10].trim());
			int AMW = Integer.parseInt(tokens[11].trim());
			int EML = Integer.parseInt(tokens[12].trim());
			int SPL = Integer.parseInt(tokens[13].trim());
			int RBL = Integer.parseInt(tokens[14].trim());
			int SRL = Integer.parseInt(tokens[15].trim());
			int MNC = Integer.parseInt(tokens[16].trim());
			int AFF = Integer.parseInt(tokens[17].trim());
			int VEN = Integer.parseInt(tokens[18].trim());
			int HAS = Integer.parseInt(tokens[19].trim());

			Stats S = new Stats(RFF, ERF, AMF, SRF, WIL, RFW, ERW, AMW, EML, SPL, RBL, SRL, MNC, AFF, VEN, HAS);
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
			int regularMoney = Integer.parseInt(tokens[2].trim());
			int reinforcedMoney = Integer.parseInt(tokens[3].trim());
			int enrichedMoney = Integer.parseInt(tokens[4].trim());
			int augmentedMoney = Integer.parseInt(tokens[5].trim());
			Resource R = new Resource(Integer.parseInt(tokens[0].trim()), tokens[1].trim(),
					regularMoney, reinforcedMoney, enrichedMoney, augmentedMoney);
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
				Resource R = DataStorage.getResource(tokens[0].trim());
				int chance = Integer.parseInt(tokens[1].trim());

				line = READER.readLine();
				tokens = line.split(",");
				int regularChance = Integer.parseInt(tokens[0].trim());
				int reinforcedChance = Integer.parseInt(tokens[1].trim());
				int enrichedChance = Integer.parseInt(tokens[2].trim());
				int augmentedChance = Integer.parseInt(tokens[3].trim());

				line = READER.readLine();
				tokens = line.split(",");
				int regularAmount = Integer.parseInt(tokens[0].trim());
				int reinforcedAmount = Integer.parseInt(tokens[1].trim());
				int enrichedAmount = Integer.parseInt(tokens[2].trim());
				int augmentedAmount = Integer.parseInt(tokens[3].trim());

				line = READER.readLine();
				tokens = line.split(",");
				int regularMAmount = Integer.parseInt(tokens[0].trim());
				int reinforcedMAmount = Integer.parseInt(tokens[1].trim());
				int enrichedMAmount = Integer.parseInt(tokens[2].trim());
				int augmentedMAmount = Integer.parseInt(tokens[3].trim());



				GatherChance GC = new GatherChance(R, chance, regularChance, reinforcedChance, enrichedChance, augmentedChance,
						regularAmount, reinforcedAmount, enrichedAmount, augmentedAmount,
						regularMAmount, reinforcedMAmount, enrichedMAmount, augmentedMAmount);

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
				Resource r = DataStorage.getResource(tokens[0].trim());
				int rarityID = Integer.parseInt(tokens[1].trim());
				int amount = Integer.parseInt(tokens[2].trim());
				c.addConnectionCost(r, rarityID, amount);
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
		PLAYER.addCoins(0, Integer.parseInt(tokens[2].trim()));
		PLAYER.addCoins(1, Integer.parseInt(tokens[1].trim()));
		PLAYER.addCoins(2, Integer.parseInt(tokens[0].trim()));
		tokens = READER.readLine().split(",");
		PLAYER.addGems(0, Integer.parseInt(tokens[0].trim()));
		PLAYER.addGems(1, Integer.parseInt(tokens[1].trim()));
		PLAYER.addGems(2, Integer.parseInt(tokens[2].trim()));
		PLAYER.addGems(3, Integer.parseInt(tokens[3].trim()));
		tokens = READER.readLine().split(",");
		PLAYER.addFire(Integer.parseInt(tokens[0].trim()));
		PLAYER.addKeys(Integer.parseInt(tokens[1].trim()));
		
		String line = "";
		for (Resource R : DataStorage.RES){
			PLAYER.addResources(R, 0, 0);
		}
		while(!(line = READER.readLine()).equals("ENDRES")){
			tokens = line.split(",");
			Resource item = DataStorage.getResource(tokens[0].trim());
			PLAYER.addResources(item, 0, Integer.parseInt(tokens[1].trim()));
			PLAYER.addResources(item, 1, Integer.parseInt(tokens[2].trim()));
			PLAYER.addResources(item, 2, Integer.parseInt(tokens[3].trim()));
			PLAYER.addResources(item, 3, Integer.parseInt(tokens[4].trim()));
			PLAYER.addResources(item, 0, Integer.parseInt(tokens[5].trim()));
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
		Character middle = DataStorage.getCharacter(Integer.parseInt(tokens[0].trim()));
		Character left = DataStorage.getCharacter(Integer.parseInt(tokens[1].trim()));
		Character right = DataStorage.getCharacter(Integer.parseInt(tokens[2].trim()));


		PLAYER.addCharacter(middle);
		PLAYER.equipCharacter(middle, 0);
		PLAYER.addCharacter(left);
		PLAYER.equipCharacter(left, 1);
		PLAYER.addCharacter(right);
		PLAYER.equipCharacter(right, 2);

		while(!(line = READER.readLine()).equals("END")){
			Character C = DataStorage.getCharacter(Integer.parseInt(line.trim()));
			PLAYER.addCharacter(C);
		}
		READER.close();
	}

}
