package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// sebz�s n�vel�s
	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {
		// sebz�s n�vel�s
	}
	
	@Override
	public void restore(Tower tower) {
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redCrystalEffect#" + id;
	}
}
