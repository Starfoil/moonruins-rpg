package gameObjects;

public class ResourceObject {
	
	public int ID;
	public String name;
	public int value;
	public String description;
	
	
	public ResourceObject(int ID, String name){
		this.ID = ID;
		this.name = name;
	}
	
	
	
	public ResourceObject(int ID, String name, int value, String description) {
		this.ID = ID;
		this.name = name;
		this.value = value;
		this.description = description;
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
		ResourceObject other = (ResourceObject) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}


	
	
	
}
