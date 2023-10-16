import java.util.*;
import CharacterPackage.Character;

public class Player {

	public Inventory inventory;
	private String userName;
	private Character character; 
	
	Scanner sc = new Scanner(System.in);
	
	public Player(String userName) {
		this.userName=userName;
		this.inventory=new Inventory();
	}

	public void selectChar() {
		
		System.out.println("KARAKTERLER : ");
		for(Character c : Character.getSubClasses()) {
			System.out.println("("+c.getId()+")"+c.getcharName()+" :");
			System.out.println("             "+"Damage : "+c.getDamage());
			System.out.println("             "+"Health : "+c.getHealth());
			System.out.println("             "+"Money : "+c.getMoney());
			System.out.println();
			
		}
		System.out.print("Karakter seçiniz : ");
		int selected=sc.nextInt();
		setCharacter(Character.getSubClasses().get(selected-1));
		System.out.println(character.getcharName()+" karakterini seçtiniz");
		System.out.println();
	}	

	public void printInfo() {
		System.out.println("Silahınız : "+this.inventory.getWeapon().getName()+
				", Zırh : "+this.getInventory().getArmor().getArmorName()+"->"+this.getInventory().getArmor().getBlock()+
				", Hasar : "+(this.character.getDamage()+this.inventory.getWeapon().getDamage())+
				", Sağlık : "+this.character.getHealth()+", Para : "+this.character.getMoney());
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory ı) {
		this.inventory=ı;
	}
	public int getTotalDamage() {
		return (this.character.getDamage()+this.inventory.getWeapon().getDamage());
	}
	public String getUserName() {
		return userName;
	}
	public String getCharName() {
		return character.getcharName();
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character c) {
		this.character=c;
	}
}
