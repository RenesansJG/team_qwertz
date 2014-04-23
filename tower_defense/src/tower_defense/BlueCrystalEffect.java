package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;
	
	// konstr.
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
	
	// effekt visszaáll (tower)
	@Override
	public void restore(Tower tower) {
		// attack speed visszaáll.
		tower.modifyAttackspeedMultiplier(-1);
		Console.println(this + ": " + tower + " attack speed-je visszaállítva");
	}
	
	// effekt visszaáll. (slowTrap)
	public void restore(SlowTrap slowTrap) {
		// lassítás visszaállítás
		slowTrap.modifySlowMultiplier(-1);
		Console.println(this + ": " + slowTrap + " slow effect-je visszaállítva");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
