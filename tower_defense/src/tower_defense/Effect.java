package tower_defense;

import java.io.Serializable;

public abstract class Effect implements ITickable, Serializable {
	private static final long serialVersionUID = -1973848083850121683L;
	protected final int id;
	
	protected int remainingTicks;
	
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
		
	}
	
	public void apply(DamageTrap damageTrap) {
		
	}
	
	public void apply(SlowTrap slowTrap) {
		
	}
	
	public void apply(Enemy enemy) {
		
	}
	
	public void restore(Tower tower) {
		
	}
	
	public void restore(DamageTrap damageTrap) {
		
	}
	
	public void restore(SlowTrap slowTrap) {
		
	}
	
	public void restore(Enemy enemy) {
		
	}
	
	// k�telez� toString f�ggv�ny ki�rat�shoz
	@Override
	public abstract String toString();
}
