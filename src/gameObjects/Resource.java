package gameObjects;

public class Resource {
	
	public int ID;
	public String name;
	
	public int regularMoney;
	public int reinforcedMoney;
	public int enrichedMoney;
	public int augmentedMoney;
	
	
	public Resource(int ID, String name){
		this.ID = ID;
		this.name = name;
	}
	
	
	
	public Resource(int iD, String name, int regularMoney, int reinforcedMoney,
			int enrichedMoney, int augmentedMoney) {
		ID = iD;
		this.name = name;
		this.regularMoney = regularMoney;
		this.reinforcedMoney = reinforcedMoney;
		this.enrichedMoney = enrichedMoney;
		this.augmentedMoney = augmentedMoney;
	}



	public String toString(){
		return this.name + "[" + this.ID + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
