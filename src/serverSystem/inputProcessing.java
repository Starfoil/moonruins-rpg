package serverSystem;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import location.Location;
import Data.DataStorage;
import Player.PLAYER;

public class inputProcessing {
	//BufferedReader in;
	Socket server;
	PrintWriter out;

	public inputProcessing(Socket server) throws IOException{
		this.server = server;
		out = new PrintWriter(server.getOutputStream(), true);
	}

	public void processInput(String input) throws IOException{
		String printString = null;
		String inputArg[] = input.split(" ");
		if (input.equals("dloc")){
			printString = PLAYER.location.toString();
		}
		else if (input.equals("dpc")){
			printString = PLAYER.coins.toString();
		}
		else if (input.equals("dpg")){
			printString = PLAYER.gems.toString();
		}
		else if (input.equals("dpr")){
			printString = PLAYER.inventory.toString();
		}
		else if (input.equals("dpi")){
			printString = PLAYER.equips.displayItems();
		}
		else if (input.equals("dpe")){
			printString = PLAYER.equips.displayEquipped();
		}
		else if (input.equals("dpt")){
			printString = PLAYER.characters.toString();
		}
		else if (input.equals("dpm")){
			printString = PLAYER.misc.toString();
		}
		else if (input.equals("dcon")){
			printString = PLAYER.location.connections.toString();
		}
		else if (input.equals("get")){
			printString = PLAYER.gather();
			if (printString == null){
				printString = "Gathering failed";
			}
		}
		else if (inputArg[0].equals("build")){
			if(inputArg.length == 2){
				Location L = DataStorage.getLocation(inputArg[1]);
				if (L != null){
					printString = PLAYER.buildConnection(L);
				}else{
					System.out.println("Invalid Location");
				}
			}else{
				printString = "Invalid arguments";
			}
		}
		else if (inputArg[0].equals("travel")){
			if(inputArg.length == 2){
				Location L = DataStorage.getLocation(inputArg[1]);
				if (L != null){
					printString = PLAYER.travel(L);
				}
				else{
					printString = "Location does not exist";
				}
			}else{
				printString = "Invalid arguments";
			}		
		}
		else if (inputArg[0].equals("qtravel")){
			if(inputArg.length == 2){
				Location L = DataStorage.getLocation(inputArg[1]);
				if (L != null){
					printString = PLAYER.quickTravel(L);
				}
				else{
					printString = "Location does not exist";
				}
			}else{
				printString = "Invalid arguments";
			}		
		}


		//		else if (inputArg[0].equals("equip")){
		//			if(inputArg[1] != null){
		//				
		//			}
		//		}





		else{
			printString = "Invalid command";
		}
		out.println(printString+"\n@");
	}
}
