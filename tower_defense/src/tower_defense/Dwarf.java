package tower_defense;

public class Dwarf extends Enemy {
	private static final long serialVersionUID = -4887012896252201347L;

	public Dwarf(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "dwarf#" + id;
	}
}
