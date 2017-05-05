package Data;

import inventory.*;
import items.*;
import Player.PLAYER;
import gameObjects.Character;
import gameObjects.Quest;
import gameObjects.ResourcePackage;
import location.*;
import locationTown.TownLocation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class DataSaver {

	public static void SAVE() throws IOException{
		DataSaver.saveConnections("DATA\\connections.txt");
		DataSaver.savePlayerData("DATA\\playerdata.txt");
		DataSaver.saveTowns("DATA\\towns.txt");
	}

	public static void saveConnections(String path) throws IOException{
		File F = new File(path);
		FileWriter FILE = new FileWriter(F.getAbsolutePath());
		BufferedWriter WRITER = new BufferedWriter(FILE);
		if (!F.exists()) {
			F.createNewFile();
		}

		String s;
		for (Connection C : DataStorage.CON){
			s = C.locationA.name + ", " + C.locationB.name + ", ";
			if (C.isBuilt){ s+= "1";} else { s+= "0";}
			s += ", " + C.travelCost + "\n";
			for (ResourcePackage CC : C.connectionCost){
				s += CC.resource.name + ", " + CC.rarityID + ", " + CC.amount + "\n";
			}
			s+= "END\n";
			WRITER.write(s);
		}

		WRITER.close();
	}

	public static void saveQuests(String path){

	}

	public static void saveTowns(String path) throws IOException{
		File F = new File(path);
		FileWriter FILE = new FileWriter(F.getAbsolutePath());
		BufferedWriter WRITER = new BufferedWriter(FILE);
		if (!F.exists()) {
			F.createNewFile();
		}
		String s;
		for (TownLocation TL : DataStorage.TWN){
			s = TL.name + ", " + TL.inn.fireRequired + ", " + TL.altar.emeralds + ", " + 
					TL.altar.sapphires + ", " + TL.altar.rubies;
			WRITER.write(s);
			WRITER.newLine();
			for (Character C : TL.inn.innCharacters){
				s = C.characterID + ", " + C.chanceOfObtain;
				WRITER.write(s);
				WRITER.newLine();
			}
			WRITER.write("ENDINN");
			WRITER.newLine();
			for (Item I : TL.shop.shopInventory){
				s = I.itemID + "";
				WRITER.write(s);
				WRITER.newLine();
			}
			WRITER.write("ENDSHOP");
			WRITER.newLine();
			for (Quest Q : TL.tavern.tavernQuests){
				s = Q.questID + "";
				WRITER.write(s);
				WRITER.newLine();
			}
			WRITER.write("END");
			WRITER.newLine();
		}
		WRITER.close();
	}

	public static void savePlayerData(String path) throws IOException{
		File F = new File(path);
		FileWriter FILE = new FileWriter(F.getAbsolutePath());
		BufferedWriter WRITER = new BufferedWriter(FILE);
		if (!F.exists()) {
			F.createNewFile();
		}
		String s;
		WRITER.write(PLAYER.PLAYERNAME);
		WRITER.newLine();
		WRITER.write(PLAYER.location.name);
		WRITER.newLine();
		s = PLAYER.coins.gold  + ", " + PLAYER.coins.silver + ", " + PLAYER.coins.copper;
		WRITER.write(s);
		WRITER.newLine();
		s = PLAYER.gems.emerald + ", " + PLAYER.gems.sapphire  + ", " + PLAYER.gems.ruby  + ", " + PLAYER.gems.star;
		WRITER.write(s);
		WRITER.newLine();
		s = PLAYER.misc.fire  + ", " + PLAYER.misc.keys;
		WRITER.write(s);
		WRITER.newLine();
		for (ResourceSubInventory RI : PLAYER.inventory.resources){
			s = RI.resource.name + ", " + RI.REGULAR + ", " + 
					RI.REINFORCED + ", " + RI.ENRICHED + ", " + RI.AUGMENTED + ", " + RI.STARFOIL;
			WRITER.write(s);
			WRITER.newLine();
		}
		WRITER.write("ENDRES");
		WRITER.newLine();
		s = "";
		if(PLAYER.equips.leftRing != null){ s += PLAYER.equips.leftRing.itemID + ", "; } else { s+= "0, ";}
		if(PLAYER.equips.rightRing != null){ s += PLAYER.equips.rightRing.itemID + ", "; } else { s+= "0, ";}
		if(PLAYER.equips.gloves != null){ s += PLAYER.equips.gloves.itemID + ", "; } else { s+= "0, ";}
		if(PLAYER.equips.boots != null){ s += PLAYER.equips.boots.itemID + ", "; } else { s+= "0, ";}
		if(PLAYER.equips.pendant != null){ s += PLAYER.equips.pendant.itemID + ", "; } else { s+= "0, ";}
		if(PLAYER.equips.talisman != null){ s += PLAYER.equips.talisman.itemID; } else { s+= "0";}
		WRITER.write(s);
		WRITER.newLine();
		for (Item I : PLAYER.equips.itemInventory){
			s = "" + I.itemID;
			WRITER.write(s);
			WRITER.newLine();
		}
		WRITER.write("ENDITM");
		WRITER.newLine();
		s = "";
		if(PLAYER.characters.middle != null){ s += PLAYER.characters.middle.characterID + ", "; } else { s+= "0, ";}
		if(PLAYER.characters.left != null){ s += PLAYER.characters.left.characterID + ", "; } else { s+= "0, ";}
		if(PLAYER.characters.right != null){ s += PLAYER.characters.right.characterID; } else { s+= "0";}
		WRITER.write(s);
		WRITER.newLine();
		for (Character C : PLAYER.characters.characterInventory){
			s = "" + C.characterID;
			WRITER.write(s);
			WRITER.newLine();
		}
		WRITER.write("END");
		WRITER.close();
	}

}
