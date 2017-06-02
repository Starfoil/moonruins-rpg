package locationTown;

import items.*;
import gameObjects.Follower;
import gameObjects.Quest;
import gameObjects.Reward;
import location.Location;

public class TownLocation extends Location{
	
	public Inn inn;
	public Shop shop;
	public Tavern tavern;
	public Altar altar;
	public Workshop workshop;
	
	public TownLocation(String name, Inn inn, Shop shop, Tavern tavern, Altar altar) {
		super(name);
		this.inn = inn;
		this.shop = shop;
		this.tavern = tavern;
		this.altar = altar;
	}
	
	public String displayShop(){
		if(shop.canPurchase()){
			return shop.toString();
		}
		return "Shop is empty";
	}
	
	public String displayQuests(){
		if(tavern.hasQuest()){
			return tavern.toString();
		}
		return "Tavern is empty";
	}
	
	public String displayAltar(){
		return altar.toString();
	}
	
	public Item purchase(Item item){
		return shop.purchaseItem(item);
	}
	
	public Follower campfire(){
		return inn.recruit();
	}
	
	public Reward completeQuest(Quest quest){
		return tavern.completeQuest(quest);
	}
	
	public int covertResource(int conversionID, int conversionAmount){
		return altar.convert(conversionID, conversionAmount);
	}
	
	public int purchaseGem(int gemID, int amount){
		return altar.purchaseGem(gemID, amount);
	}
	
	public Blueprint craftItem(Item item){
		return workshop.getBlueprint(item);
	}

	public String display() {
		return this.name + "\n=========================\nINN  "
				+ inn + "=========================\nSHOP \n"
				+ shop + "=========================\nTAVERN \n"
				+ tavern + "=========================\nALTAR \n"
				+ altar + "\n=========================\n";
	}
	
	

}
