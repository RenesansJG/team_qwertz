package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// attackspeed n�vel�s
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		// lass�t�s n�vel�s
	}
	
	@Override
	public void restore(Tower tower) {
		
	}
	
	public void restore(SlowTrap slowTrap) {
		
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
