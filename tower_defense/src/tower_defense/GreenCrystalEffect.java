package tower_defense;

public class GreenCrystalEffect extends Effect {
	private static final long serialVersionUID = -8137816837843882726L;

	public GreenCrystalEffect() {
		Console.println(this + " = new GreenCrystalEffect()");
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {

	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {

	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {

	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
