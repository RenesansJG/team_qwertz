package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {

	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed növelés
		tower.modifyAttackspeedMultiplier(1);;
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		// lassítás növelés
		slowTrap.modifySlowMultiplier(1);
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyAttackspeedMultiplier(-1);;
	}
	
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifySlowMultiplier(-1);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
