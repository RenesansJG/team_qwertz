package tower_defense;

public class Hobbit extends Enemy {
	private static final long serialVersionUID = -3408698338441606044L;

	public Hobbit(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "hobbit#" + id;
	}
}
