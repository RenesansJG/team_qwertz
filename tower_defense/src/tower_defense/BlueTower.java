package tower_defense;

public class BlueTower extends Tower {
	private static final long serialVersionUID = -3727403261460737412L;

	public BlueTower(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "blueTower#" + id;
	}
}
