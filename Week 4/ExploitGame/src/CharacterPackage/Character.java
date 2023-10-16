package CharacterPackage;

import java.util.ArrayList;

public abstract class Character {

	private int id;
	private String charName;
	private int damage;
	private int health;
	private int orijinalHealt;
	private int money;
	
	public Character(int id,String charName,int damage,int health,int money){
		this.id=id;
		this.charName=charName;
		this.damage=damage;
		this.health=health;
		this.orijinalHealt=health;
		this.money=money;
	}
	
	public static ArrayList<Character> getSubClasses() {
		ArrayList<Character> classes = new ArrayList<Character>();
		classes.add(new Archer());
		classes.add(new Knight());
		classes.add(new Samuray());
		
		return classes;
	}
	
	public int getOrijinalHealt() {
		return orijinalHealt;
	}

	public void setOrijinalHealt(int orijinalHealt) {
		this.orijinalHealt = orijinalHealt;
	}

	public int getId() {
		return id;
	}
	public String getcharName() {
		return charName;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		if(health<0) {
			health=0;
		}
		this.health = health;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	

}
