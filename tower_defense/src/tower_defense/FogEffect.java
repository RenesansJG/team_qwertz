package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;

	public FogEffect() {

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
		return "fogEffect#" + id;
	}
}
