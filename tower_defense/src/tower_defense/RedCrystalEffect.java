package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {
		Console.println(this + " = new RedCrystalEffect()");
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
		return "redCrystalEffect#" + id;
	}
}
