package location;

import java.util.ArrayList;

public class TravelReport {
	
	public ArrayList<Location> routeTaken;
	public int totalCost;
	
	public TravelReport(ArrayList<Location> routeTaken, int totalCost) {
		this.routeTaken = routeTaken;
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Route taken to destination : " + routeTaken + "\nYour total cost is "
				+ totalCost + ".";
	}
	
	
	

}
