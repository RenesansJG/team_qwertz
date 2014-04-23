package tower_defense;

import java.io.Serializable;

public abstract class Effect implements ITickable, Serializable {
	private static final long serialVersionUID = -1973848083850121683L;
	protected final int id; // effekt ID
	
	protected int remainingTicks; // hány tick van még míg akítv az effect
	
	// effekt id megszerzése
	protected Effect() {
		id = Game.getNextEffectId();
	}
	
	// tick alkalmazása effekten
	@Override
	public final boolean applyTick() {
		remainingTicks--;
		return remainingTicks<=0;
	}
	
	public void apply(Tower tower) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void apply(DamageTrap damageTrap) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void apply(SlowTrap slowTrap) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void apply(Enemy enemy) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void restore(Tower tower) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void restore(DamageTrap damageTrap) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void restore(SlowTrap slowTrap) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	public void restore(Enemy enemy) {
		// üres, mert felülírandó (kényelmesség)
	}
	
	// kötelezõ toString függvény kiíratáshoz
	@Override
	public abstract String toString();
}
