package tower_defense;

import java.io.Serializable;

public abstract class Effect implements ITickable, Serializable {
	private static final long serialVersionUID = -1973848083850121683L;
	protected final int id; // effekt ID
	
	protected int remainingTicks; // h�ny tick van m�g m�g ak�tv az effect
	
	// effekt id megszerz�se
	protected Effect() {
		id = Game.getNextEffectId();
	}
	
	// tick alkalmaz�sa effekten
	@Override
	public final boolean applyTick() {
		remainingTicks--;
		return remainingTicks<=0;
	}
	
	public void apply(Tower tower) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void apply(DamageTrap damageTrap) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void apply(SlowTrap slowTrap) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void apply(Enemy enemy) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void restore(Tower tower) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void restore(DamageTrap damageTrap) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void restore(SlowTrap slowTrap) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	public void restore(Enemy enemy) {
		// �res, mert fel�l�rand� (k�nyelmess�g)
	}
	
	// k�telez� toString f�ggv�ny ki�rat�shoz
	@Override
	public abstract String toString();
}
