package tower_defense;

public class BlueTower extends Tower {
	private static final long serialVersionUID = -3727403261460737412L;

	public BlueTower(double x, double y) {
		super(x, y);
		projectileDamage = new Damage(5,5,10);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueTower#" + id;
	}
}
