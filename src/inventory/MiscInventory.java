package inventory;

public class MiscInventory {
	
	public int fire;
	public int keys;
	
	public MiscInventory(){
		this.fire = 0;
		this.keys = 0;
	}
	
	public void addFire(int amount){
		fire += amount;
	}
	
	public void removeFire(int amount){
		fire -= amount;
		if (fire < 0){
			fire = 0;
		}
	}
	
	public boolean checkFire(int amount){
		return fire >= amount;
	}
	
	public void addKeys(int amount){
		keys += amount;
	}
	
	public void removeKeys(int amount){
		keys -= amount;
		if (keys < 0){
			keys = 0;
		}
	}
	
	public boolean checkKeys(int amount){
		return keys >= amount;
	}

	@Override
	public String toString() {
		return "[Fire : " + fire + "]  [Keys : " + keys + "]";
	}
	
	
	
}
