package location;

import gameObjects.Resource;
import gameObjects.ResourcePackage;

import java.util.ArrayList;


public class Connection {
	
	public Location locationA;
	public Location locationB;
	public int travelCost;
	
	public boolean isBuilt;
	
	public ArrayList<ResourcePackage> connectionCost;
	
	public Connection(Location locationA, Location locationB){
		this.locationA = locationA;
		this.locationB = locationB;
		this.isBuilt = false;
		connectionCost = new ArrayList<ResourcePackage>();
		this.travelCost = 0;
	}
	
	public void setTravelCost(int travelCost){
		this.travelCost = travelCost;
	}
	
	public void addConnectionCost(Resource resource, int rarityID, int amount){
		connectionCost.add(new ResourcePackage(resource, rarityID, amount));
	}
	
	public boolean contains(Location loc){
		return (this.locationA.equals(loc) || this.locationB.equals(loc));
	}
	
	
	public boolean build(){
		if(this.isBuilt == false){
			this.isBuilt = true;
			return true;
		}
		return false;
	}
	
	public String displayConnection(){
		String s = "[Connection : " +locationA.name + " -> "+ locationB.name + "]    ";
		if(isBuilt){ s += "BUILT"; }
		else{ s += "NOT BUILT"; }
		return s;
	}
	
	public String displayCost(){
		String s = "";
		if(!isBuilt){
			s = "Connection is not built\nCost of Connection : \n";
			for (ResourcePackage CC: connectionCost){
				s+= CC + "\n";
			}
		}
		return s;
	}
	

	
	@Override
	public String toString() {
		return displayConnection() + "\n" + displayCost();
	}

	
}

