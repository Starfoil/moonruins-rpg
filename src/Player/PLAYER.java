package Player;
import gameObjects.*;
import inventory.CharacterInventory;
import inventory.CurrencyInventory;
import inventory.EquipmentInventory;
import inventory.GemInventory;
import inventory.MiscInventory;
import inventory.ResourceInventory;
import items.*;

import java.io.IOException;
import java.text.DecimalFormat;
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
	public static Stats stat;

	public static void setName(String name){
		PLAYERNAME = name;
	}

	//	public static void newGame(){
	//		inventory = new ResourceInventory();
	//		for (Resource R : DataStorage.RES){addResources(R, 0);}
	//		coins = new CurrencyInventory();
	//		gems = new GemInventory();
	//		equips = new EquipmentInventory();
	//		characters = new CharacterInventory();
	//		misc = new MiscInventory();
	//		location = DataStorage.LOC.get(0);
	//		try {
	//			DataStorage.LOC = new ArrayList<Location>();
	//			DataStorage.TWN = new ArrayList<TownLocation>();
	//			DataStorage.CON = new ArrayList<Connection>();
	//			DataLoader.loadGatherLocations("DATA\\locations.txt");
	//			DataLoader.loadTowns("DATA\\NEWtowns.txt");
	//			DataLoader.loadConnections("DATA\\NEWconnections.txt");
	//		} catch (NumberFormatException e) {
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	public static void Initialize(Location loc){
		inventory = new ResourceInventory();
		coins = new CurrencyInventory();
		gems = new GemInventory();
		location = loc;
		equips = new EquipmentInventory();
		characters = new CharacterInventory();
		misc = new MiscInventory();
		stat = new Stats(1, 0);
		processPlayerStats();
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
		inventory.addItems(GC.resource, GC.amountGathered);
	}

	public static void processPlayerStats(){
		double hands = 1;
		if(equips.leftRing != null){ hands += equips.leftRing.stats.multiplier;}
		if(equips.rightRing != null){ hands += equips.rightRing.stats.multiplier;}
		if(equips.gloves != null){ hands += equips.gloves.stats.multiplier;}

		double magic = 0;
		if(equips.leftRing != null){ magic += equips.leftRing.stats.magicFind;}
		if(equips.rightRing != null){ magic += equips.rightRing.stats.magicFind;}
		if(equips.pendant != null){ hands += equips.pendant.stats.multiplier;}
		stat.updateStat(hands, magic);
	}

	public static String travel(Location loc){
		Connection C = location.getConnection(loc);
		if (C != null && C.isBuilt){
			if(coins.totalMoney > C.travelCost){
				location = loc;
				coins.remove(C.travelCost);
				return "Traveled to " + loc.name;
			}
			return "Not enough money";
		}
		return "No valid selection";
	}
	
	public static void addCoins(int amount){
		coins.add(amount);
	}

	public static void addGems(int gemID, int amount){
		if(gems != null){
			gems.add(gemID, amount);
		}
	}

	public static void addResources(ResourceObject item, int amount){
		inventory.addItems(item, amount);
	}

	public static String buildConnection(Connection c){
		if(hasConnectionCost(c)){
			for (ResourcePackage R : c.connectionCost){
				inventory.removeItems(R.resource, R.amount);
			}
			c.build();
			return "Connection has been built";
		}else if (c == null){
			return "No valid selection";
		}else{
			return "You do not have the required materials";
		}		
	}

	private static boolean hasConnectionCost(Connection c){
		for (ResourcePackage R : c.connectionCost){
			if(!inventory.hasItems(R.resource, R.amount)) return false;
		}
		return true;
	}

	public static String gather(){
		if(location instanceof GatherLocation){
			GatherReport GR = ((GatherLocation)location).gather();
			process(GR);
			return GR.toString();
		}
		return "Not in a gather location";
	}


	public static void addItem(Item item){
		if(item != null){
			equips.addItem(item);
		}
	}

	public static void addCharacter(Follower cha){
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

	public static String equipCharacter(Follower cha, int slotID){
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
					coins.remove(item.cost);
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
				Follower characterObtained = ((TownLocation)location).campfire();
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

	//	public static void craftItem(Item item){
	//		if(location instanceof TownLocation){
	//			Blueprint BI = ((TownLocation) location).workshop.getBlueprint(item);
	//			if (BI != null ){
	//				boolean hasResources = true;
	//				for (ResourcePackage RP : BI.components){
	//					if(!inventory.hasItems(RP.resource, RP.rarityID, RP.amount)){
	//						hasResources = false;
	//					}
	//				}
	//				if (hasResources){
	//					for (ResourcePackage RP : BI.components){
	//						inventory.removeItems(RP.resource, RP.rarityID, RP.amount);
	//					}
	//					equips.addItem(item);
	//				}	
	//			}
	//		}
	//
	//	}

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
					coins.remove(TR.totalCost);
					return TR.toString();
				}
				return "Not enough money to commence quick travel.";
			}
			return "Quick travel was not succesful.";
		}
	}

}
