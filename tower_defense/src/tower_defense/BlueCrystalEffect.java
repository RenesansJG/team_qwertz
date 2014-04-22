package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed n�vel�s
		tower.modifyAttackspeedMultiplier(1);;
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		// lass�t�s n�vel�s
		slowTrap.modifySlowMultiplier(1);
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyAttackspeedMultiplier(-1);;
	}
	
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifySlowMultiplier(-1);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
