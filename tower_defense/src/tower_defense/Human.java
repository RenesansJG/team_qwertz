package tower_defense;

public class Human extends Enemy {
	private static final long serialVersionUID = 5166440464581339881L;

	public Human(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "human#" + id;
	}
}
