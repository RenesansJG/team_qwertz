package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	private static int duration = 250;
	
	public RedCrystalEffect() {
		super(duration);
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// sebz�s n�vel�s
		tower.modifyprojectileDamageMultiplier(1);
	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {
		// sebz�s n�vel�s
		damageTrap.modifyDamageMultiplier(1);
	}
	
	@Override //Effect lev�telek
	public void restore(Tower tower) {
		tower.modifyprojectileDamageMultiplier(-1);
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyDamageMultiplier(-1);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redCrystalEffect";
	}
}
