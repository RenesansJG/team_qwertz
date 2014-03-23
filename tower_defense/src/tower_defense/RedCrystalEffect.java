package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {
		Console.println(this + " = new RedCrystalEffect()");
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		Console.println(this + ".apply(" + tower + ")");
	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {
		Console.println(this + ".apply(" + damageTrap + ")");
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		Console.println(this + ".apply(" + slowTrap + ")");
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redCrystalEffect#" + id;
	}
}
