package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {
		Console.println(this + " = new BlueCrystalEffect()");
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		Console.println(this + ".apply(" + tower + ")");
	}
	
	// effekt alkalmazása sebzõ csapdán
	@Override
	public void apply(DamageTrap damageTrap) {
		Console.println(this + ".apply(" + damageTrap + ")");
	}
	
	// effekt alkalmazása lassító csapdán
	@Override
	public void apply(SlowTrap slowTrap) {
		Console.println(this + ".apply(" + slowTrap + ")");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
