
public class Obstacle {

	private int id;
	private String obstacleName;
	private int damage;
	private int health;
	private int awardMoney;
	private int orjinalHealth;
	
	public Obstacle(int id,String obstacleName, int damage, int health,int awardMoney) {
		this.id = id;
		this.obstacleName=obstacleName;
		this.damage = damage;
		this.health = health;
		this.orjinalHealth=health;
		this.awardMoney= awardMoney;
	}
	
	public int getOrjinalHealth() {
		return orjinalHealth;
	}
	public int getAwardMoney() {
		return awardMoney;
	}
	public void setAwardMoney(int money) {
		this.awardMoney=money;
	}
	public String getObstacleName() {
		return obstacleName;
	}
	public void setObstacleName(String obstacleName) {
		this.obstacleName = obstacleName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		if(health < 0) {
			health=0;
		}
		this.health = health;
	}
	
	

	
}
