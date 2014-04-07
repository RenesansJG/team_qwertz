package tower_defense;

public class GreenCrystalEffect extends Effect {
	private static final long serialVersionUID = -8137816837843882726L;

	public GreenCrystalEffect() {
		Console.println(this + " = new GreenCrystalEffect()");
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {

	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {

	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {

	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
