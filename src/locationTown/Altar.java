package locationTown;


public class Altar {
	
	private final int EMERALD_RATE = 3;
	private final int SAPPHIRE_RATE = 10;
	private final int RUBY_RATE = 25;
	private final int STAR_RATE = 100;
	
	private final int EMERALD_PRICE = 50;
	private final int SAPPHIRE_PRICE = 1000;
	private final int RUBY_PRICE = 25000;
	
	public int emeralds;
	public int sapphires;
	public int rubies;
	
	public Altar(int emeralds, int sapphires, int rubies){
		this.emeralds = emeralds;
		this.sapphires = sapphires;
		this.rubies = rubies;
	}
	
	
	public int convert(int conversionID, int conversionAmount){
		if (conversionID == 0){
			return conversionAmount / EMERALD_RATE;
		}
		else if (conversionID == 1){
			return conversionAmount / SAPPHIRE_RATE;
		}
		else if (conversionID == 2){
			return conversionAmount / RUBY_RATE;
		}
		else if (conversionID == 3){
			return conversionAmount / STAR_RATE;
		}
		return 0;
	}
	
	public boolean canPurchaseGem(int gemID, int amount){
		if (gemID == 0 && emeralds >= amount){
			return true;
		}
		else if (gemID == 1 && sapphires >= amount){
			return true;
		}
		else if (gemID == 2 && rubies >= amount){
			return true;
		}
		return false;
	}
	
	public int purchaseGem(int gemID, int amount){
		if (canPurchaseGem(gemID, amount)){
			if (gemID == 0){
				emeralds -= amount;
				return amount * EMERALD_PRICE;
			}
			else if (gemID == 1){
				sapphires -= amount;
				return amount * SAPPHIRE_PRICE;
			}
			else if (gemID == 2){
				rubies -= amount;
				return amount * RUBY_PRICE;
			}
		}
		return 0;
	}
	
	public String toString() {
		return "[Emeralds : " + emeralds + "] \n[Sapphires : " + sapphires + "] \n[Rubies : " + rubies + "]";
	}
}
