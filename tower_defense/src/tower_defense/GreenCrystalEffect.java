package tower_defense;

public class GreenCrystalEffect extends Effect {
	private static final long serialVersionUID = -8137816837843882726L;

	public GreenCrystalEffect() {
		Console.println(this + " = new GreenCrystalEffect()");
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// ranget növeli
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		// ranget növeli
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		// ranget növeli
	}
	
	@Override
	public void restore(Tower tower) {

	}
	
	@Override
	public void restore(DamageTrap damageTrap) {

	}
	
	@Override
	public void restore(SlowTrap slowTrap) {

	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
