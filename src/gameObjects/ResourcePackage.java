package gameObjects;

public class ResourcePackage {
	
	public ResourceObject resource;
	public int amount;
	
	public ResourcePackage(ResourceObject resource, int amount) {
		this.resource = resource;
		this.amount = amount;
	}
	
	public void add(int addAmount){
		this.amount += addAmount;
	}
	
	public int remove(int removeAmount){
		if (this.amount - removeAmount < 0){
			this.amount = 0;
			return this.amount;
		}
		else{
			this.amount -= removeAmount;
			return removeAmount;
		}
	}
	
	public boolean check(int checkAmount){
		if (this.amount > checkAmount){
			return true;
		}
		else return false;
	}

	@Override
	public String toString() {
		return resource.name + " - " + amount;
	}
	
	

}
