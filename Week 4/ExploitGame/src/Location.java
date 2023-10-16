import java.util.ArrayList;
import java.util.Scanner;

public abstract class Location {

	private int locationId;
	private Player player;
	private String locationName;
	public static Scanner sc = new Scanner(System.in);
	
	public Location(int locationId,Player player,String locationName) {
		this.locationId=locationId;
		this.player=player;
		this.locationName=locationName;
	}

	public abstract boolean onLocation();
	
	public ArrayList<Location> getLocationNames() {
		ArrayList<Location> classes = new ArrayList<Location>();
		classes.add(new SafeHouse(player));
		classes.add(new ToolStore(player));	
		classes.add(new Cave(player));
		classes.add(new Forest(player));
		classes.add(new River(player));
		
		return classes;
		
	}
	
	
	

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	
}
