package tower_defense;

public class BlueTower extends Tower {
	private static final long serialVersionUID = -3727403261460737412L;

	public BlueTower() {
		Console.println(this + " = new BlueTower()");
	}
	
	@Override
	public String toString() {
		return "blueTower#" + id;
	}
}
