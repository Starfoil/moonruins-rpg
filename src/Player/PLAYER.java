package Player;
import gameObjects.*;
import gameObjects.Character;
import inventory.CharacterInventory;
import inventory.CurrencyInventory;
import inventory.EquipmentInventory;
import inventory.GemInventory;
import inventory.MiscInventory;
import inventory.ResourceInventory;
import items.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Data.DataLoader;
import Data.DataStorage;
import location.*;
import locationGather.GatherLocation;
import locationGather.GatherReport;
import locationTown.TownLocation;


public class PLAYER {

	private static final Random RNG = new Random();

	public static String PLAYERNAME;
	public static ResourceInventory inventory;
	public static Location location;
	public static CurrencyInventory coins;
	public static GemInventory gems;
	public static MiscInventory misc;
	public static EquipmentInventory equips;
	public static CharacterInventory characters;

	public static void setName(String name){
		PLAYERNAME = name;
	}

	public static void newGame(){
		inventory = new ResourceInventory();
		for (Resource R : DataStorage.RES){addResources(R, 0, 0);}
		coins = new CurrencyInventory();
		gems = new GemInventory();
		equips = new EquipmentInventory();
		characters = new CharacterInventory();
		misc = new MiscInventory();
		location = DataStorage.LOC.get(0);
		try {
			DataStorage.LOC = new ArrayList<Location>();
			DataStorage.TWN = new ArrayList<TownLocation>();
			DataStorage.CON = new ArrayList<Connection>();
			DataLoader.loadGatherLocations("DATA\\locations.txt");
			DataLoader.loadTowns("DATA\\NEWtowns.txt");
			DataLoader.loadConnections("DATA\\NEWconnections.txt");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Initialize(Location loc){
		inventory = new ResourceInventory();
		coins = new CurrencyInventory();
		gems = new GemInventory();
		location = loc;
		equips = new EquipmentInventory();
		characters = new CharacterInventory();
		misc = new MiscInventory();
	}

	public static String display(){
		return "=============================================================\nPLAYER NAME : " + PLAYERNAME + "\n"
				+ "=============================================================\n"
				+ location.toString() + "\n=============================\n"
				+ coins.toString() + "\n=============================\n"
				+ gems.toString() + "\n=============================\n"
				+ inventory.toString() + "=============================\n"
				+ equips.toString() + "=============================\n"
				+ characters.toString() +"\n";
	}

	private static void process(GatherReport GC){
		inventory.addItems(GC.resource, GC.rarityGathered, GC.amountGathered);
		coins.gainMoney(GC.moneyEarned);
	}

	private static Stats processPlayerStats(){
		int totalRFF = 0;
		if(equips.leftRing != null){ totalRFF += equips.leftRing.stats.RFF;}
		if(equips.rightRing != null){ totalRFF += equips.rightRing.stats.RFF;}
		if(characters.middle != null){ totalRFF += characters.middle.stats.RFF;}
		if(characters.left != null){ totalRFF += characters.left.stats.RFF;}
		if(characters.right != null){ totalRFF += characters.right.stats.RFF;}

		int totalERF = 0;
		if(equips.leftRing != null){ totalERF += equips.leftRing.stats.ERF;}
		if(equips.rightRing != null){ totalERF += equips.rightRing.stats.ERF;}
		if(characters.middle != null){ totalERF += characters.middle.stats.ERF;}
		if(characters.left != null){ totalERF += characters.left.stats.ERF;}
		if(characters.right != null){ totalERF += characters.right.stats.ERF;}

		int totalAMF = 0;
		if(equips.leftRing != null){ totalAMF += equips.leftRing.stats.AMF;}
		if(equips.rightRing != null){ totalAMF += equips.rightRing.stats.AMF;}
		if(characters.middle != null){ totalAMF += characters.middle.stats.AMF;}
		if(characters.left != null){ totalAMF += characters.left.stats.AMF;}
		if(characters.right != null){ totalAMF += characters.right.stats.AMF;}

		int totalSRF = 0;
		if(equips.leftRing != null){ totalSRF += equips.leftRing.stats.SRF;}
		if(equips.rightRing != null){ totalSRF += equips.rightRing.stats.SRF;}
		if(characters.middle != null){ totalSRF += characters.middle.stats.SRF;}
		if(characters.left != null){ totalSRF += characters.left.stats.SRF;}
		if(characters.right != null){ totalSRF += characters.right.stats.SRF;}

		int totalWIL = 0;
		if(equips.gloves != null){ totalWIL += equips.gloves.stats.WIL;}
		if(characters.middle != null){ totalWIL += characters.middle.stats.WIL;}
		if(characters.left != null){ totalWIL += characters.left.stats.WIL;}
		if(characters.right != null){ totalWIL += characters.right.stats.WIL;}

		int totalRFW = 0;
		if(equips.gloves != null){ totalRFW += equips.gloves.stats.RFW;}
		if(characters.middle != null){ totalRFW += characters.middle.stats.RFW;}
		if(characters.left != null){ totalRFW += characters.left.stats.RFW;}
		if(characters.right != null){ totalRFW += characters.right.stats.RFW;}

		int totalERW = 0;
		if(equips.gloves != null){ totalERW += equips.gloves.stats.ERW;}
		if(characters.middle != null){ totalERW += characters.middle.stats.ERW;}
		if(characters.left != null){ totalERW += characters.left.stats.ERW;}
		if(characters.right != null){ totalERW += characters.right.stats.ERW;}

		int totalAMW = 0;
		if(equips.gloves != null){ totalAMW += equips.gloves.stats.AMW;}
		if(characters.middle != null){ totalAMW += characters.middle.stats.AMW;}
		if(characters.left != null){ totalAMW += characters.left.stats.AMW;}
		if(characters.right != null){ totalAMW += characters.right.stats.AMW;}

		int totalEML = 0;
		if(equips.pendant != null){ totalEML += equips.pendant.stats.EML;}
		if(characters.middle != null){ totalEML += characters.middle.stats.EML;}
		if(characters.left != null){ totalEML += characters.left.stats.EML;}
		if(characters.right != null){ totalEML += characters.right.stats.EML;}

		int totalSPL = 0;
		if(equips.pendant != null){ totalSPL += equips.pendant.stats.SPL;}
		if(characters.middle != null){ totalSPL += characters.middle.stats.SPL;}
		if(characters.left != null){ totalSPL += characters.left.stats.SPL;}
		if(characters.right != null){ totalSPL += characters.right.stats.SPL;}

		int totalRBL = 0;
		if(equips.pendant != null){ totalRBL += equips.pendant.stats.RBL;}
		if(characters.middle != null){ totalRBL += characters.middle.stats.RBL;}
		if(characters.left != null){ totalRBL += characters.left.stats.RBL;}
		if(characters.right != null){ totalRBL += characters.right.stats.RBL;}

		int totalSRL = 0;
		if(equips.pendant != null){ totalSRL += equips.pendant.stats.SRL;}
		if(characters.middle != null){ totalSRL += characters.middle.stats.SRL;}
		if(characters.left != null){ totalSRL += characters.left.stats.SRL;}
		if(characters.right != null){ totalSRL += characters.right.stats.SRL;}

		int totalMNC = 0;
		if(equips.talisman != null){ totalMNC += equips.talisman.stats.MNC;}
		if(characters.middle != null){ totalMNC += characters.middle.stats.MNC;}
		if(characters.left != null){ totalMNC += characters.left.stats.MNC;}
		if(characters.right != null){ totalMNC += characters.right.stats.MNC;}

		int totalAFF = 0;
		if(equips.talisman != null){ totalAFF += equips.talisman.stats.AFF;}
		if(characters.middle != null){ totalAFF += characters.middle.stats.AFF;}
		if(characters.left != null){ totalAFF += characters.left.stats.AFF;}
		if(characters.right != null){ totalAFF += characters.right.stats.AFF;}

		int totalVNT = 0;
		if(equips.talisman != null){ totalVNT += equips.talisman.stats.VNT;}
		if(characters.middle != null){ totalVNT += characters.middle.stats.VNT;}
		if(characters.left != null){ totalVNT += characters.left.stats.VNT;}
		if(characters.right != null){ totalVNT += characters.right.stats.VNT;}

		int totalHAS = 0;
		if(equips.boots != null){ totalHAS += equips.boots.stats.HAS;}
		if(characters.middle != null){ totalHAS += characters.middle.stats.HAS;}
		if(characters.left != null){ totalHAS += characters.left.stats.HAS;}
		if(characters.right != null){ totalHAS += characters.right.stats.HAS;}

		Stats totalStats = new Stats(totalRFF, totalERF, totalAMF, totalSRF, totalWIL, totalRFW, totalERW, totalAMW,
				totalEML, totalSPL, totalRBL, totalSRL, totalMNC, totalAFF, totalVNT, totalHAS);
		return totalStats;
	}

	public static String travel(Location loc){
		Connection C = location.getConnection(loc);
		if (C != null && C.isBuilt){
			if(coins.totalMoney > C.travelCost){
				location = loc;
				coins.loseMoney(C.travelCost);
				return "Traveled to " + loc.name;
			}
			return "Not enough money to travel to location";
		}
		return "No valid built connection to specified location";
	}

	public static void addCoins(int coinID, int amount){
		if(coins != null){
			coins.add(coinID, amount);
		}
	}

	public static void addGems(int gemID, int amount){
		if(gems != null){
			gems.add(gemID, amount);
		}
	}

	public static void addResources(Resource item, int rarityID, int amount){
		if(inventory.hasResource(item)){
			inventory.addItems(item, rarityID, amount);
		}
		else{
			inventory.addInventory(item);
			inventory.addItems(item, rarityID, amount);
		}
	}

	public static String buildConnection(Location loc){
		if(location.getListedConnections(0).contains(loc)){
			if(location.buildConnection(loc)){
				return "Connection has been built";
			}
			return "You do not have the required materials";
		}
		return "Connection to specified location was not found";
	}

	public static String gather(){
		if(location instanceof GatherLocation){
			int MRChance = RNG.nextInt(100) + 1;
			if (((GatherLocation)location).moonRuin != null && MRChance <= ((GatherLocation)location).moonRuin.encounterChance){
				int gemIDGathered = ((GatherLocation)location).moonRuin.gatherID();
				addGems(gemIDGathered, ((GatherLocation)location).moonRuin.gatherAmount(gemIDGathered));
				return "MOONRUINS!";
			}
			else{
				GatherReport GR = ((GatherLocation)location).gather(processPlayerStats());
				process(GR);
				return GR.toString();
			}
		}
		return "Not in a gather location";
	}


	public static void addItem(Item item){
		if(item != null){
			equips.addItem(item);
		}
	}

	public static void addCharacter(Character cha){
		if(!characters.characterInventory.contains(cha) && cha != null){
			characters.addCharacter(cha);
		}
	}

	public static String equipItem(Item item, int slotID){
		if (equips.itemInventory.contains(item) && item != null){
			equips.deEquip(slotID);
			equips.equip(item, slotID);
			return "Sucessfully equipped " + item.name;
		}
		return "You do not own this item you are wishing to equip";
	}

	public static String equipCharacter(Character cha, int slotID){
		if (characters.characterInventory.contains(cha) && cha != null){
			characters.deEquipCharacter(slotID);
			characters.equipCharacter(cha, slotID);
			return "Sucessfully invited " + cha.name; 
		}
		return "You do not own this traveler";
	}

	public static String purchaseItem(Item item){
		if(location instanceof TownLocation){
			if(((TownLocation) location).shop.shopInventory.contains(item)){
				if(coins.totalMoney >= item.cost){
					coins.loseMoney(item.cost);
					Item itemPurchased = ((TownLocation) location).purchase(item);
					equips.addItem(itemPurchased);
					return "Sucessfully purchased " + itemPurchased.name;
				}
				return "You don't have enough money";
			}
			return "The shop does not own this item";
		}
		return "Not in town";
	}

	public static String campfire(){
		if(location instanceof TownLocation){
			if(misc.checkFire(((TownLocation)location).inn.fireRequired)){
				misc.removeFire(((TownLocation)location).inn.fireRequired);
				Character characterObtained = ((TownLocation)location).campfire();
				if (characterObtained != null){
					characters.addCharacter(characterObtained);
					return "Obtained and added " + characterObtained.name + " to inventory.";
				}
				return "Inn has no more characters";
			}
			return "Not enough fire";
		}
		return "Not in town";
	}

	public static void craftItem(Item item){
		if(location instanceof TownLocation){
			Blueprint BI = ((TownLocation) location).workshop.getBlueprint(item);
			if (BI != null ){
				boolean hasResources = true;
				for (ResourcePackage RP : BI.components){
					if(!inventory.hasItems(RP.resource, RP.rarityID, RP.amount)){
						hasResources = false;
					}
				}
				if (hasResources){
					for (ResourcePackage RP : BI.components){
						inventory.removeItems(RP.resource, RP.rarityID, RP.amount);
					}
					equips.addItem(item);
				}	
			}
		}

	}

	public static String rollCrate(Crate C){
		if (misc.checkKeys(C.costToRoll)){
			Item crateReward = C.roll();
			addItem(crateReward);
			misc.removeKeys(C.costToRoll);
			return "Congratulations!! You recieved a " + crateReward.name;
		}
		return "Not enough keys.";
	}
	
	public static void processReward(Reward reward){
		misc.addKeys(reward.keys);
		misc.addFire(reward.fire);
	}

	public static void addKeys(int amount){
		misc.addKeys(amount);
	}

	public static void addFire(int amount){
		misc.addFire(amount);
	}

	public static String quickTravel(Location loc){
		if(loc.equals(DataStorage.LOC.get(0))){
			location = loc;
			return "Returned to " + PLAYER.location + " for free.";
		}
		else{
			TravelReport TR = location.getRoute(loc);
			if (TR != null){
				if (coins.totalMoney >= TR.totalCost){
					location = loc;
					coins.loseMoney(TR.totalCost);
					return TR.toString();
				}
				return "Not enough money to commence quick travel.";
			}
			return "Quick travel was not succesful.";
		}
	}

}
