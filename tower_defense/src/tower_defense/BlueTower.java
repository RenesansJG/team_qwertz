package tower_defense;

public class BlueTower extends Tower {
	private static final long serialVersionUID = -3727403261460737412L;
	
	// konstr.
	public BlueTower(double x, double y) {
		super(x, y);
		// projectile sebzésének beáll.
		projectileDamage = new Damage(0,0,40);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueTower";
	}
}
