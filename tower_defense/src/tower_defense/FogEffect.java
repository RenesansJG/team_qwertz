package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;

	public FogEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		tower.modifyRange(-10);
		Console.println(this + ": " + tower + " range-je cs�kkentve!");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyRange(10);
		Console.println(this + "lej�rt: " + tower + " range-je vissza�ll�tva!");
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "fogEffect#" + id;
	}
}
