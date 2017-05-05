package locationTown;

import items.*;

import java.util.ArrayList;

public class Workshop {
	
	public static ArrayList<Blueprint> CRAFTABLES = new ArrayList<Blueprint>();
	
	public Workshop(){
		
	}
	
	public Blueprint getBlueprint(Item item){
		for (Blueprint B : CRAFTABLES){
			if (B.item == item){
				return B;
			}
		}
		return null;
	}
	
}
