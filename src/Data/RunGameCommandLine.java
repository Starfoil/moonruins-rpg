package Data;

import items.Item;

import java.io.IOException;
import java.util.Scanner;

import location.Location;
import locationTown.TownLocation;
import gameObjects.Character;
import Player.PLAYER;

public class RunGameCommandLine {

	public static void main(String[] args) throws IOException {

		Scanner x = new Scanner(System.in);
		DataLoader.LOAD();
		System.out.println("Sucessfully loaded data");
		System.out.println("Enter a command ...");
		String input = x.nextLine();
		while(!input.equals("quit")){
			
			if (input.equals("dloc")){
				System.out.println(PLAYER.location.toString());
			}
			else if (input.equals("dinn")){
				if(PLAYER.location instanceof TownLocation){
					System.out.println(((TownLocation) PLAYER.location).inn.fireRequired + 
							" fire needed to start a campfire at this Inn.");
				}
				else{
					System.out.println("Not in Town!");
				}
			}
			else if (input.equals("dshop")){
				if(PLAYER.location instanceof TownLocation){
					System.out.println(((TownLocation) PLAYER.location).displayShop() + "\n"); 
				}
				else{
					System.out.println("Not in Town!");
				}
			}
			else if (input.equals("dtown")){
				if(PLAYER.location instanceof TownLocation){
					System.out.println(((TownLocation) PLAYER.location).display());
				}
				else{
					System.out.println("Not in Town!");
				}
			}
			else if (input.equals("dpall")){
				System.out.println(PLAYER.display());
			}
			else if (input.equals("dpr")){
				System.out.println(PLAYER.inventory);
			}
			else if (input.equals("dpi")){
				System.out.println(PLAYER.equips);
			}
			else if (input.equals("dpt")){
				System.out.println(PLAYER.characters);
			}
			else if (input.equals("dpc")){
				System.out.println(PLAYER.coins +"\n" + PLAYER.gems);
			}
			else if (input.equals("dpm")){
				System.out.println(PLAYER.misc);
			}
			else if (input.equals("dcon")){
				System.out.println(PLAYER.location.connections);
			}
			else if (input.equals("get")){
				String gather = PLAYER.gather();
				if (gather != null){
					System.out.println(gather);
				}
				else{
					System.out.println("Gathering failed");
				}
			}
			else if (input.equals("build")){
				System.out.println("Enter a location to build to");
				System.out.println(PLAYER.location.getListedConnections(0));
				String inp = x.nextLine();
				Location L = DataStorage.getLocation(inp);
				if (L != null){
					System.out.println(PLAYER.buildConnection(L));
				}
				else{
					System.out.println("Invalid Location");
				}
			}
			else if (input.equals("travel")){
				System.out.println("You are current in " + PLAYER.location);
				System.out.println("Enter a location you wish to travel to");
				System.out.println(PLAYER.location.getListedConnections(1));
				String inp = x.nextLine();
				Location L = DataStorage.getLocation(inp);
				if (L != null){
					System.out.println(PLAYER.travel(L));
				}
				else{
					System.out.println("Location does not exist");
				}
			}
			else if (input.equals("qtravel")){
				System.out.println("You are current in " + PLAYER.location);
				System.out.println("Enter a location you wish to quick travel to");
				System.out.println(PLAYER.location.getFullConnections());
				String inp = x.nextLine();
				Location L = DataStorage.getLocation(inp);
				if (L != null){
					System.out.println(PLAYER.quickTravel(L));
				}
				else{
					System.out.println("Location does not exist");
				}
			}
			else if (input.equals("campfire")){
				System.out.println(PLAYER.campfire());
			}
			else if (input.equals("roll")){
				System.out.println(PLAYER.rollCrate(DataStorage.CRA.get(0)));
			}
			else if (input.equals("craft")){
				//
			}
			else if (input.equals("ei")){
				System.out.println("Enter the item ID you wish to equip : ");
				System.out.println(PLAYER.equips.displayItems());
				while (!x.hasNextInt()){ 
					System.out.println("Invalid Input. Try again :");
					x.next();
				}
				int inp = x.nextInt();
				Item I = DataStorage.getItem(inp);
				System.out.println("Enter the slot in which you wish to equip the item : ");
				System.out.println(PLAYER.equips.displayEquipped());
				while (!x.hasNextInt()){ 
					System.out.println("Invalid Input. Try again :");
					x.next();
				}
				int slot = x.nextInt();
				if (I != null && slot >= 0 && slot < 6){
					System.out.println(PLAYER.equipItem(I, slot));
				}
				else{
					System.out.println("Item or slot input does not exist");
				}
			}
			else if (input.equals("et")){
				System.out.println("Enter the Traveler ID you wish to invite : ");
				System.out.println(PLAYER.characters.displayCharacters());
				while (!x.hasNextInt()){ 
					System.out.println("Invalid Input. Try again :");
					x.next();
				}
				int inp = x.nextInt();
				Character C = DataStorage.getCharacter(inp);
				System.out.println("Enter the slot in which you wish to invite the traveler to : ");
				System.out.println(PLAYER.characters.displayParty());
				while (!x.hasNextInt()){ 
					System.out.println("Invalid Input. Try again :");
					x.next();
				}
				int slot = x.nextInt();
				if (C != null && slot > 0 && slot <= 3){
					System.out.println(PLAYER.equipCharacter(C, slot));
				}
				else{
					System.out.println("Character or slot input does not exist");
				}
			}
			else if (input.equals("buy")){
				if (PLAYER.location instanceof TownLocation){
					if(((TownLocation)PLAYER.location).shop.canPurchase()){
						System.out.println("Enter the item ID you wish to buy");
						System.out.println(((TownLocation)PLAYER.location).shop);
						while (!x.hasNextInt()){ 
							System.out.println("Invalid Input. Try again :");
							x.next();
						}
						int inp = x.nextInt();
						Item I = DataStorage.getItem(inp);
						if (I != null){
							System.out.println(PLAYER.purchaseItem(I));
						}
						else{
							System.out.println("Item does not exist");
						}
					}
					System.out.println("Shop is empty");			
				}
				else{
					System.out.println("Not in town!");
				}
			}
			else if (input.equals("addkeys")){
				System.out.println("Enter the amount of keys you wish to add :");
				while (!x.hasNextInt()){ 
					System.out.println("Invalid Input. Try again :");
					x.next();
				}
				int inp = x.nextInt();
				PLAYER.addKeys(inp);
			}
			else if (input.equals("NEWGAME")){
				PLAYER.newGame();
				System.out.println("Started new game. Previous data has been erased");
			}
			else{
				//System.out.println("Invalid command");
			}
			//System.out.println("Enter a command ...");
			input = x.nextLine();
			//DataSaver.SAVE();
		}
		x.close();
		DataSaver.SAVE();
		System.out.println("Succesfully saved data");
	}
}
