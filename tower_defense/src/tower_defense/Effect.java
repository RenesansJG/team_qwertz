package tower_defense;

public abstract class Effect implements ITickable {
	private static int cnt = 0;
	protected final int id;
	
	private int remainingTicks;
	
	protected Effect() {
		id = cnt++;
	}
	
	@Override
	public final boolean applyTick() {
		Console.println(this + ".applyTick()");
		
		return false;
	}
	
	public void apply(Tower tower) {}
	public void apply(DamageTrap damageTrap) {}
	public void apply(SlowTrap slowTrap) {}
	public void apply(Enemy enemy) {}
	
	@Override
	public abstract String toString();
}
