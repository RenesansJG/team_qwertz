package tower_defense;

import java.io.Serializable;

public abstract class Effect implements ITickable, Serializable {
	private static final long serialVersionUID = -1973848083850121683L;
	protected final int id;
	
	//private int remainingTicks;
	
	protected Effect() {
		id = Game.getNextEffectId();
	}
	
	// tick alkalmaz�sa effekten
	@Override
	public final boolean applyTick() {
		Console.println(this + ".applyTick()");
		
		return true; // az effekteket egyel�re mindig t�r�lj�k
	}
	
	public void apply(Tower tower) {
		Console.println(this + ".apply(" + tower + ")");
	}
	
	public void apply(DamageTrap damageTrap) {
		Console.println(this + ".apply(" + damageTrap + ")");
	}
	
	public void apply(SlowTrap slowTrap) {
		Console.println(this + ".apply(" + slowTrap + ")");
	}
	
	public void apply(Enemy enemy) {
		Console.println(this + ".apply(" + enemy + ")");
	}
	
	// k�telez� toString f�ggv�ny ki�rat�shoz
	@Override
	public abstract String toString();
}
