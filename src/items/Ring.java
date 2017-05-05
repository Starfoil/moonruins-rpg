package items;

public class Ring extends Item{

	public Ring(int itemID, String name, int rarityID, int cost) {
		super(itemID, name, rarityID, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RingID " + itemID + " : " + name + "\n"
				+ "Rarity ID : " + rarityID + "   Cost : " + cost + "\n"
				+ stats + "\n";
	}
	
	



}
