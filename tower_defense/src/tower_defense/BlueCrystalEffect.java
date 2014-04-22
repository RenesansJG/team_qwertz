package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed n�vel�s
		tower.modifyAttackspeedMultiplier(1);
		Console.println(this + ": " + tower + " attack speed-je n�velve");
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		// lass�t�s n�vel�s
		slowTrap.modifySlowMultiplier(1);
		Console.println(this + ": " + slowTrap + " slow effect-je n�velve");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyAttackspeedMultiplier(-1);
		Console.println(this + ": " + tower + " attack speed-je vissza�ll�tva");
	}
	
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifySlowMultiplier(-1);
		Console.println(this + ": " + slowTrap + " slow effect-je vissza�ll�tva");
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
