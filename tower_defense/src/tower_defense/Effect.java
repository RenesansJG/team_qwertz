package tower_defense;

import java.io.Serializable;

public abstract class Effect implements ITickable, Serializable {
	private static final long serialVersionUID = -1973848083850121683L;
	protected final int id;
	
	//private int remainingTicks;
	
	protected Effect() {
		id = Game.getNextEffectId();
	}
	
	// tick alkalmazása effekten
	@Override
	public final boolean applyTick() {
		return true; // az effekteket egyelõre mindig töröljük
	}
	
	public void apply(Tower tower) {

	}
	
	public void apply(DamageTrap damageTrap) {
		
	}
	
	public void apply(SlowTrap slowTrap) {
		
	}
	
	public void apply(Enemy enemy) {
		
	}
	
	// kötelezõ toString függvény kiíratáshoz
	@Override
	public abstract String toString();
}
