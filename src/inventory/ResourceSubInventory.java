package inventory;

import gameObjects.Resource;

public class ResourceSubInventory{

	public Resource resource;
	public int REGULAR;
	public int REINFORCED;
	public int ENRICHED;
	public int AUGMENTED;
	public int STARFOIL;

	public ResourceSubInventory(Resource item){
		this.resource = item;
		this.REGULAR = 0;
		this.REINFORCED = 0;
		this.ENRICHED = 0;
		this.AUGMENTED = 0;
		this.STARFOIL = 0;
	}
	
	public boolean isEmpty(){
		return (this.REGULAR == 0 && this.REINFORCED == 0 && 
				this.ENRICHED == 0 && this.AUGMENTED == 0 && this.STARFOIL == 0);
	}
	
	
	public void add(int RARITY, int amount){
		if(RARITY == 0){
			this.REGULAR += amount;
		}
		else if(RARITY == 1){
			this.REINFORCED += amount;
		}
		else if(RARITY == 2){
			this.ENRICHED += amount;
		}
		else if(RARITY == 3){
			this.AUGMENTED += amount;
		}
		else if(RARITY == 4){
			this.STARFOIL += amount;
		}
		else{
			//exception
		}
	}
	
	public boolean checkAmount(int RARITY, int quantity){
		if(RARITY == 0){
			return this.REGULAR >= quantity;
		}
		else if(RARITY == 1){
			return this.REINFORCED >= quantity;
		}
		else if(RARITY == 2){
			return this.ENRICHED >= quantity;
		}
		else if(RARITY == 3){
			return this.AUGMENTED >= quantity;
		}
		else if(RARITY == 4){
			return this.STARFOIL >= quantity;
		}
		else{
			return false;
		}
	}
	
	public void remove(int RARITY, int quantity){
		if(checkAmount(RARITY, quantity)){
			if(RARITY == 0){
				this.REGULAR -= quantity;
			}
			else if(RARITY == 1){
				this.REINFORCED -= quantity;
			}
			else if(RARITY == 2){
				this.ENRICHED -= quantity;
			}
			else if(RARITY == 3){
				this.AUGMENTED -= quantity;
			}
			else if(RARITY == 4){
				this.STARFOIL -= quantity;
			}
			else{
				//exception
			}
		}
	}

	public String toString(){
		return resource.name + " : [" + REGULAR + "] [" + REINFORCED + "] [" + ENRICHED
				+ "] [" + AUGMENTED + "] [" + STARFOIL + "]";
	}

	
	

}
