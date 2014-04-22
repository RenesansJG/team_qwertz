package tower_defense;

public class RedTower extends Tower {
	private static final long serialVersionUID = 3431245497354435731L;


	protected RedTower(double x, double y) {
		super(x, y);
		projectileDamage = new Damage(10,5,5);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redTower#" + id;
	}
}
