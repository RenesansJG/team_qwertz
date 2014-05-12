package tower_defense;

public class RedTower extends Tower {
	private static final long serialVersionUID = 3431245497354435731L;


	protected RedTower(double x, double y) {
		super(x, y);
		projectileDamage = new Damage(40,0,0);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "redTower";
	}
}
