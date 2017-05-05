package inventory;

public class GemInventory {
	
	
	public int emerald;
	public int sapphire;
	public int ruby;
	public int star;
	
	public GemInventory() {
		this.emerald = 0;
		this.sapphire = 0;
		this.ruby = 0;
		this.star = 0;
	}
	
	public String toString(){
		return "[Emerald : " + emerald + "] [Sapphire : " + sapphire + "] [Ruby : "+ ruby + "] [Star :" + star + "]";
	}
	
	public void add(int gemID, int amount){
		if(gemID == 0){
			this.emerald += amount;
		}
		else if(gemID == 1){
			this.sapphire += amount;
		}
		else if(gemID == 2){
			this.ruby += amount;
		}
		else if(gemID == 3){
			this.star += amount;
		}
		else{
			//exception
		}
	}
	
	public void remove(int gemID, int amount){
		if(gemID == 0){
			this.emerald -= amount;
		}
		else if(gemID == 1){
			this.sapphire -= amount;
		}
		else if(gemID == 2){
			this.ruby -= amount;
		}
		else if(gemID == 3){
			this.star -= amount;
		}
		else{
			//exception
		}
	}
	
	
}
