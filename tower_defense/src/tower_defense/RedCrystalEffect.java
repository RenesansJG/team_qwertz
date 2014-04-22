package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {

	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		// sebzés növelés
		tower.modifyprojectileDamageMultiplier(1);
		Console.println(this + ": " + tower + " damage-e növelve");
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		// sebzés növelés
		damageTrap.modifyDamageMultiplier(1);
		Console.println(this + ": " + damageTrap + " damage-e növelve");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyprojectileDamageMultiplier(-1);
		Console.println(this + "lejárt: " + tower + " damage-e visszaállítva");
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyDamageMultiplier(-1);
		Console.println(this + "lejárt: " + damageTrap + " damage-e visszaállítva");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "redCrystalEffect#" + id;
	}
}
