package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {

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
		return "redCrystalEffect#" + id;
	}
}
