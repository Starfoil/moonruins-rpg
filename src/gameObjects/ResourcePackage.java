package gameObjects;

public class ResourcePackage {
	
	public Resource resource;
	public int rarityID;
	public int amount;
	
	public ResourcePackage(Resource resource, int rarityID, int amount) {
		this.resource = resource;
		this.rarityID = rarityID;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "[ Resource : " + resource.name + "]  [RarityID : "
				+ rarityID + "]  [Amount : " + amount + "]";
	}
	
	

}
