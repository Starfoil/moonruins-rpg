package inventory;

public class CurrencyInventory {
	
	public int copper;
	public int silver;
	public int gold;
	public int totalMoney;

	public CurrencyInventory(){
		this.copper = 0;
		this.silver = 0;
		this.gold = 0;
		this.totalMoney = 0;
	}
	
	public void gainMoney(int money){
		int gold = money / 10000;
		int silver = (money % 10000) / 100;
		int copper = money % 100;
		add(0, copper);
		add(1, silver);
		add(2, gold);
	}
	
	public void loseMoney(int money){
		int gold = money / 10000;
		int silver = (money % 10000) / 100;
		int copper = money % 100;
		remove(0, copper);
		remove(1, silver);
		remove(2, gold);
		System.out.println("Removed [" + gold + " gold] [" + silver + " silver] [" + copper + "]");
	}

	private void reEvaluate(){
		int goldMoney = gold * 10000;
		int silverMoney = silver * 100;
		int copperMoney = copper;
		this.totalMoney = goldMoney + silverMoney + copperMoney;
	}
	
	public void add(int coinID, int amount){
		if(coinID == 0){
			this.copper += amount;
			if (this.copper >= 100){
				int conversionCoins = this.copper / 100;
				this.copper -= conversionCoins * 100;
				add(1, conversionCoins);
			}
		}
		else if(coinID == 1){
			this.silver += amount;
			if (this.silver >= 100){
				int conversionCoins = this.silver / 100;
				this.silver -= conversionCoins * 100;
				add(2, conversionCoins);
			}
		}
		else if(coinID == 2){
			this.gold += amount;
		}
		reEvaluate();
	}


	public void remove(int coinID, int amount){
		if(checkAmount(coinID, amount)){
			if(coinID == 0){
				this.copper -= amount;
			}
			else if(coinID == 1){
				this.silver -= amount;
			}
			else if(coinID == 2){
				this.gold -= amount;
			}
		}
		reEvaluate();
	}

	private boolean checkAmount(int coinID, int amount){
		if(coinID == 0){
			return this.copper >= amount;
		}
		else if(coinID == 1){
			return this.silver >= amount;
		}
		else if(coinID == 2){
			return this.gold >= amount;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[Gold : " + gold + "] [Silver : " + silver
				+ "] [Copper : " + copper + "]";
	}

}
