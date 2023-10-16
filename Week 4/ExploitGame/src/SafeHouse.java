
public class SafeHouse extends NormalLocation{

	public SafeHouse(Player p) {
		super(1,p,"Güvenli Ev");
	}

	@Override
	public boolean onLocation() {
		this.getPlayer().getCharacter().setHealth(this.getPlayer().getCharacter().getOrijinalHealt());
		System.out.println("Güvenli Evdesiniz, canınız yenilendi");
		System.out.println();
		return true;
	}
}
