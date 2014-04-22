package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {

	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed növelés
		tower.modifyAttackspeedMultiplier(1);
		Console.println(this + ": " + tower + " attack speed-je növelve");
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		// lassítás növelés
		slowTrap.modifySlowMultiplier(1);
		Console.println(this + ": " + slowTrap + " slow effect-je növelve");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyAttackspeedMultiplier(-1);
		Console.println(this + ": " + tower + " attack speed-je visszaállítva");
	}
	
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifySlowMultiplier(-1);
		Console.println(this + ": " + slowTrap + " slow effect-je visszaállítva");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
