
public abstract class NormalLocation extends Location{

	
	public NormalLocation(int locationId,Player p,String locationName) {
		super(locationId,p,locationName);
	}

	@Override
	public abstract boolean onLocation();

}
