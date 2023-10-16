
public class ToolStore extends NormalLocation{

	public ToolStore(Player p) {
		super(2,p, "Mağaza");
	}

	@Override
	public boolean onLocation() {
		System.out.println("Mağazaya hoşgeldiniz");
		System.out.println("    1 - Silahlar");
		System.out.println("    2 - Zırhlar");
		System.out.println("    3 - Çıkış yap");
		System.out.println("Seçiminiz : ");
		int selected = sc.nextInt();
		while(selected < 1 || selected > 3){
			System.out.println("Geçersiz değer, tekrar giriniz!!");
			selected = sc.nextInt();
		}
		switch(selected) {
			case 1:
				printWeapon();
				buyWeapon();
				break;
			case 2:
				printArmor();
				buyArmor();
				break;
			case 3:
				return true;				
		}
		return true;
	}
	public void printWeapon(){
		System.out.println("------SİLAHLAR------");
		System.out.println();
		for(Weapon weapon : Weapon.getWeaponList()) {
			System.out.println("("+weapon.getId()+")"+weapon.getName()+"   <Hasar : "+weapon.getDamage()+" - Fiyat : "+weapon.getPrice()+">");
		}
		System.out.println("0 - Çıkış");		
		System.out.println();
		
	}
	public void buyWeapon() {
		System.out.println("Bir silah seçiniz : ");
		int selectedWp = sc.nextInt();
		while(selectedWp < 0 || selectedWp > Weapon.getWeaponList().length){
			System.out.println("Geçersiz değer, tekrar giriniz!!");
			selectedWp = sc.nextInt();
		}
		if(selectedWp!=0) {
			Weapon selectedWeapon = Weapon.getWeaponList()[selectedWp-1];
			if(selectedWeapon.getPrice() > (this.getPlayer().getCharacter().getMoney())) {
				System.out.println("Yeterli paranız bulunmamaktadır :(");
			}else {
				//Satın alımın gerçekleştiği blok
				
				this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() - selectedWeapon.getPrice());			
				this.getPlayer().getInventory().setWeapon(selectedWeapon);
				System.out.println(selectedWeapon.getName()+ " silahını satın aldınız!");
				System.out.println("Kalan paranız : "+this.getPlayer().getCharacter().getMoney());
			}
		}
		
		
	}
	
	public void printArmor(){
		System.out.println("------ARMORLAR------");
		System.out.println();
		for(Armor armor : Armor.getArmorList()) {
			System.out.println("("+armor.getId()+")"+armor.getArmorName()+"   <Zırh : "+armor.getBlock()+" - Fiyat : "+armor.getPrice()+">");
		}
		System.out.println("0 - Çıkış");
		System.out.println();
	}
	
	public void buyArmor() {
		
		System.out.println("Bir zırh seçiniz : ");
		int selectedArmorId = sc.nextInt();
		while(selectedArmorId < 0 || selectedArmorId > Armor.getArmorList().length){
			System.out.println("Geçersiz değer, tekrar giriniz!!");
			selectedArmorId = sc.nextInt();
		}
		
		if(selectedArmorId!=0) {
			Armor selectedArmor = Armor.getArmorList()[selectedArmorId-1];
			if(selectedArmor.getPrice() > (this.getPlayer().getCharacter().getMoney())) {
				System.out.println("Yeterli paranız bulunmamaktadır :(");
			}else {
				//Satın alımın gerçekleştiği blok
				
				this.getPlayer().getCharacter().setMoney(this.getPlayer().getCharacter().getMoney() - selectedArmor.getPrice());			
				this.getPlayer().getInventory().setArmor(selectedArmor);
				System.out.println(selectedArmor.getArmorName()+ " zırhını satın aldınız!");
				System.out.println("Kalan paranız : "+this.getPlayer().getCharacter().getMoney());
			}
		}
		
		
	}

}
