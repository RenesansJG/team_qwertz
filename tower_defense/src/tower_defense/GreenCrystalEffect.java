package tower_defense;

public class GreenCrystalEffect extends Effect {
	private static final long serialVersionUID = -8137816837843882726L;

	public GreenCrystalEffect() {
		Console.println(this + " = new GreenCrystalEffect()");
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		// ranget n�veli
		tower.modifyRangeMultiplier(1);
		Console.println("Tower#" + tower.id + " megkapta greenCrystalEffect#" + id);
	}
	
	// effekt alkalmaz�sa sebz� csapd�n
	@Override
	public void apply(DamageTrap damageTrap) {
		// ranget n�veli
		damageTrap.modifyRangeMultiplier(1);
	}
	
	// effekt alkalmaz�sa lass�t� csapd�n
	@Override
	public void apply(SlowTrap slowTrap) {
		// ranget n�veli
		slowTrap.modifyRangeMultiplier(1);
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyRangeMultiplier(-1);
	}
	
	@Override
	public void restore(DamageTrap damageTrap) {
		damageTrap.modifyRangeMultiplier(-1);
	}
	
	@Override
	public void restore(SlowTrap slowTrap) {
		slowTrap.modifyRangeMultiplier(-1);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "greenCrystalEffect#" + id;
	}
}
