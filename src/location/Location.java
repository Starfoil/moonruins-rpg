package location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public abstract class Location {

	public String name;
	public ArrayList<Connection> connections;
	public String description;

	private int gCost;
	private int hCost;
	private Location parent;

	public Location(String name) {
		this.name = name;
		this.connections = new ArrayList<Connection>();
		this.gCost = 0;
		this.hCost = 0;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}

	public void addConnection(Connection C) {
		if (C.contains(this)) {
			this.connections.add(C);
		}
	}

	public void clean() {
		this.gCost = 0;
		this.hCost = 0;
		this.parent = null;
	}

	private void findPath(Location target) {
		if (hasConnection(target)) {
			ArrayList<Location> list = new ArrayList<Location>();
			Set<Location> set = new HashSet<Location>();
			this.hCost = getTravelCost(target);
			list.add(this);
			while (!list.isEmpty()) {
				int smallestIndex = list.indexOf(getSmallest(list));
				Location current = list.remove(smallestIndex);
				set.add(current);
				if (current.equals(target)) {
					break;
				}
				for (Location O : current.getListedConnections(1)) {
					if (set.contains(O)) {
						continue;
					} else {
						int gScore = current.gCost + current.getTravelCost(O);
						if (!list.contains(O)) {
							list.add(O);
						} else if (list.contains(O) && gScore >= O.gCost) {
							continue;
						}
						O.parent = current;
						O.gCost = gScore;
						O.hCost = O.getTravelCost(target);
					}
				}
			}
		}
	}

	public ArrayList<Location> getListedConnections(int codeID) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (Connection C : this.connections) {
			if (codeID == 0){
				if (!C.isBuilt && C.locationA.equals(this)) {
					locations.add(C.locationB);
				} else if (!C.isBuilt){
					locations.add(C.locationA);
				}
			}
			else if (codeID == 1){
				if (C.isBuilt && C.locationA.equals(this)) {
					locations.add(C.locationB);
				} else if (C.isBuilt) {
					locations.add(C.locationA);
				}
			}
			else{
				if (C.locationA.equals(this)) {
					locations.add(C.locationB);
				} else {
					locations.add(C.locationA);
				}
			}
		}
		return locations;
	}

	public Connection getConnection(Location loc) {
		for (Connection C : this.connections) {
			if (C.contains(loc)) {
				return C;
			}
		}
		return null;
	}
	

	private int getFCost() {
		return gCost + hCost;
	}

	public TravelReport getRoute(Location loc) {
		findPath(loc);
		if (hasConnection(loc)) {
			return new TravelReport(retracePath(loc), getTotalCost(loc));
		}
		return null;
	}

	private Location getSmallest(ArrayList<Location> list) {
		Location smallest = list.get(0);
		for (Location L : list) {
			if (L.getFCost() < smallest.getFCost()) {
				smallest = L;
			}
		}
		return smallest;
	}

	private int getTotalCost(Location targetLocation) {
		Location current = targetLocation;
		int totalCost = 0;
		while (current != this && current != null) {
			totalCost += current.getTravelCost(current.parent);
			current = current.parent;
		}
		return totalCost;
	}

	private int getTravelCost(Location loc) {
		Connection C = getConnection(loc);
		if (C != null) {
			return C.travelCost;
		}
		return 0;
	}
	
	public ArrayList<Location> getFullConnections(){
		Stack<Location> stack = new Stack<Location>();
		Set<Location> set = new HashSet<Location>();
		ArrayList<Location> returnLocations = new ArrayList<Location>();
		stack.push(this);
		while (!stack.isEmpty()) {
			Location target = stack.pop();
			if (!set.contains(target)) {
				set.add(target);
				if(!target.equals(this)){
					returnLocations.add(target);
				}
				for (Location L : target.getListedConnections(1)) {
					if (!set.contains(L)) {
						stack.push(L);
					}
				}
			}
		}
		return returnLocations;
	}

	private boolean hasConnection(Location targetLocation) {
		Stack<Location> stack = new Stack<Location>();
		Set<Location> set = new HashSet<Location>();
		stack.push(this);
		while (!stack.isEmpty()) {
			Location target = stack.pop();
			if (target.equals(targetLocation)) {
				return true;
			}
			if (!set.contains(target)) {
				set.add(target);
				for (Location L : target.getListedConnections(1)) {
					if (!set.contains(L)) {
						stack.push(L);
					}
				}
			}
		}
		return false;
	}

	private ArrayList<Location> retracePath(Location targetLocation) {
		Location current = targetLocation;
		ArrayList<Location> path = new ArrayList<Location>();
		while (current != this && current != null) {
			path.add(current);
			current = current.parent;
		}
		path.add(this);
		Collections.reverse(path);
		return path;
	}

	@Override
	public String toString() {
		return name;
	}

}
