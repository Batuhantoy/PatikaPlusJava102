
public class Armor {

	private int id;
	private String armorName;
	private int block;
	private int price;
	
	public Armor(int id, String armorName, int block, int price) {
		super();
		this.id = id;
		this.armorName = armorName;
		this.block = block;
		this.price = price;
	}

	public static Armor[] getArmorList() {
		Armor[] armors = new Armor[3];
		armors[0] = new Armor(1,"Hafif",1,15);
		armors[1] = new Armor(2,"Orta",3,25);
		armors[2] = new Armor(3,"Ağır",5,40);
		return armors;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArmorName() {
		return armorName;
	}

	public void setArmorName(String armorName) {
		this.armorName = armorName;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	

}
