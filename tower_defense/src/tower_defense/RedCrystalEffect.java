package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// sebz�s n�vel�s
		tower.modifyprojectileDamageMultiplier(1);
		Console.println(this + ": " + tower + " damage-e n�velve");
	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {
		// sebz�s n�vel�s
		damageTrap.modifyDamageMultiplier(1);
		Console.println(this + ": " + damageTrap + " damage-e n�velve");
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyprojectileDamageMultiplier(-1);
		Console.println(this + "lej�rt: " + tower + " damage-e vissza�ll�tva");
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyDamageMultiplier(-1);
		Console.println(this + "lej�rt: " + damageTrap + " damage-e vissza�ll�tva");
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redCrystalEffect#" + id;
	}
}
