import java.util.Random;

public abstract class BattleLocation extends Location{

	private Obstacle obstacle;
	private String award;
	private int maxObstacle;
	
	public BattleLocation(int locationId, Player player, String locationName, Obstacle obstacle, String award, int maxObstacle) {
		super(locationId, player, locationName);
		this.obstacle=obstacle;
		this.award=award;
		this.maxObstacle=maxObstacle;
	}

	@Override
	public boolean onLocation() {
		int obsNumber = this.randomObstacleNumber();
		System.out.println("Şuan buradasınız : "+this.getLocationName());
		System.out.println("Dikkatli olun!! Burada "+this.getMaxObstacle()+" tane "+this.getObstacle().getObstacleName()+" yaşıyor!!");
		System.out.println("<S> Savaş veya <K> Kaç");
		String selected = sc.nextLine();
		selected= selected.toUpperCase();
		if(selected.equals("S") && combat(obsNumber)) {
			//SAVAŞMA İŞLEMİ
			System.out.println(this.getLocationName()+"'da tüm düşmanları yendiniz!!!");
			return true;
		}
		if(this.getPlayer().getCharacter().getHealth() <= 0) {
			System.out.println("Öldünüz :/");
			return false;
		}
		return true;
	}

	public boolean combat(int obsNumber) {
		for(int i=1; i<=maxObstacle;i++) {
			this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
			playerStats();
			obstacleStats(i);
			while(this.getPlayer().getCharacter().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
				System.out.print("<V> Vur veya <K> Kaç : ");
				String selectedCom = sc.nextLine();
				selectedCom=selectedCom.toUpperCase();
				if(selectedCom.equals("V")) {
					System.out.println("Siz vurdunuz !");
					this.getObstacle().setHealth( this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
					afterHit();
					if(this.getObstacle().getHealth() > 0) {
						System.out.println("Canavar size vurdu!");
						int obsDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
						if(obsDamage < 0) {
							obsDamage=0;
						}
						this.getPlayer().getCharacter().setHealth(this.getPlayer().getCharacter().getHealth() - obsDamage);
						afterHit();
					}
				}else {
					return false;
				}
			}
			if(this.getObstacle().getHealth() < this.getPlayer().getCharacter().getHealth()) {
				System.out.println("Düşmanı yendiniz");
				System.out.println(this.getObstacle().getAwardMoney()+" para kazandınız!!!");
				this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney()+this.getObstacle().getAwardMoney());
				System.out.println("Güncel Paranız : "+this.getPlayer().getCharacter().getMoney());
			}else {
				return false;
			}
		}
		 
		
		return true;
	}
	public void afterHit() {
		System.out.println("Canınız : "+this.getPlayer().getCharacter().getHealth());
		System.out.println(this.getObstacle().getObstacleName()+" Canı : "+this.getObstacle().getHealth());
		System.out.println();
	}
	public void playerStats() {
		System.out.println("Oyuncu Değerleri");
		System.out.println("----------------------");
		System.out.println("Sağlık : "+this.getPlayer().getCharacter().getHealth());
		System.out.println("Silah : "+this.getPlayer().getInventory().getWeapon().getName());
		System.out.println("Hasar : "+this.getPlayer().getTotalDamage());
		System.out.println("Zırh : "+this.getPlayer().getInventory().getArmor().getArmorName()+" -> "+this.getPlayer().getInventory().getArmor().getBlock());
		System.out.println("Para : "+this.getPlayer().getCharacter().getMoney());
		System.out.println();
	}
	public void obstacleStats(int i) {
		System.out.println(i+". "+this.getObstacle().getObstacleName()+" Değerleri");
		System.out.println();
		System.out.println("Sağlık : "+this.getObstacle().getHealth());
		System.out.println("Hasar : "+this.getObstacle().getDamage());
		System.out.println("Ödül : "+this.getObstacle().getAwardMoney());
		System.out.println();
	}
	public int getMaxObstacle() {
		return maxObstacle;
	}

	public void setMaxObstacle(int maxObstacle) {
		this.maxObstacle = maxObstacle;
	}

	public int randomObstacleNumber() {
		Random r = new Random();
		return r.nextInt(this.getMaxObstacle())+1;
	}
	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

}
