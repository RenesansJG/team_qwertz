package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;
	
	private static int duration = 250;
	
	public BlueCrystalEffect() {
		super(duration);
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed n�vel�s
		tower.modifyAttackspeedMultiplier(1);
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		// lass�t�s n�vel�s
		slowTrap.modifySlowMultiplier(1);
	}
	
	// effekt vissza�ll (tower)
	@Override
	public void restore(Tower tower) {
		// attack speed vissza�ll.
		tower.modifyAttackspeedMultiplier(-1);
	}
	
	// effekt vissza�ll. (slowTrap)
	public void restore(SlowTrap slowTrap) {
		// lass�t�s vissza�ll�t�s
		slowTrap.modifySlowMultiplier(-1);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "blueCrystalEffect";
	}
}
