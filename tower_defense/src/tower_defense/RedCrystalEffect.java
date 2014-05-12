package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	private static int duration = 250;
	
	public RedCrystalEffect() {
		super(duration);
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// sebzés növelés
		tower.modifyprojectileDamageMultiplier(1);
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		// sebzés növelés
		damageTrap.modifyDamageMultiplier(1);
	}
	
	@Override //Effect levételek
	public void restore(Tower tower) {
		tower.modifyprojectileDamageMultiplier(-1);
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyDamageMultiplier(-1);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "redCrystalEffect";
	}
}
