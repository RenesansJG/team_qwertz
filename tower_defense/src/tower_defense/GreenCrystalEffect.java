package tower_defense;

public class GreenCrystalEffect extends Effect {
	private static final long serialVersionUID = -8137816837843882726L;

	private static int duration = 250;
	
	public GreenCrystalEffect() {
		super(duration);
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// ranget növeli
		tower.modifyRangeMultiplier(1);
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		// ranget növeli
		damageTrap.modifyRangeMultiplier(1);
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		// ranget növeli
		slowTrap.modifyRangeMultiplier(1);
	}
	
	@Override //Effect levétele
	public void restore(Tower tower) {
		tower.modifyRangeMultiplier(-1);
	}
	
	@Override //Effect levétele
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyRangeMultiplier(-1);
	}
	
	@Override //Effect levétele
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifyRangeMultiplier(-1);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
