package tower_defense;

public class GreenTower extends Tower {
	private static final long serialVersionUID = 9017818506188373134L;

	public GreenTower(double x, double y) {
		super(x, y);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "greenTower#" + id;
	}
}
