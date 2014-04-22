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
		tower.modifyRangeMultiplier(1);
		Console.println(this + ": " + tower + " damage-e növelve");
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		// ranget növeli
		damageTrap.modifyRangeMultiplier(1);
		Console.println(this + ": " + damageTrap + " damage-e növelve");
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		// ranget növeli
		slowTrap.modifyRangeMultiplier(1);
		Console.println(this + ": " + slowTrap + " damage-e növelve");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyRangeMultiplier(-1);
		Console.println(this + ": " + tower + " damage-e visszaálítva");
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyRangeMultiplier(-1);
		Console.println(this + ": " + damageTrap + " damage-e visszaálítva");
	}
	
	@Override
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifyRangeMultiplier(-1);
		Console.println(this + ": " + slowTrap + " damage-e visszaálítva");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
