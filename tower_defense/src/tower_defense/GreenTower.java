package tower_defense;

public class GreenTower extends Tower {
	private static final long serialVersionUID = 9017818506188373134L;

	public GreenTower(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "greenTower#" + id;
	}
}
