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
	

//	private void reevaluate(){
//		int goldMoney = gold * 10000;
//		int silverMoney = silver * 100;
//		int copperMoney = copper;
//		this.totalMoney = goldMoney + silverMoney + copperMoney;
//	}
	
	private void redistribute(){
		gold = totalMoney / 10000;
		silver = (totalMoney % 10000) / 100;
		copper = totalMoney % 100;
	}
	
	public void add(int amount){
		totalMoney += amount;
		redistribute();
	}


	public void remove(int amount){
		totalMoney -= amount;
		redistribute();
	}

	public boolean checkAmount(int amount){
		return totalMoney > amount;
	}
	
	@Override
	public String toString() {
		return "[Gold : " + gold + "] [Silver : " + silver
				+ "] [Copper : " + copper + "]";
	}

}
