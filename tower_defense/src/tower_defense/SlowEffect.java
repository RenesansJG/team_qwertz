package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	private double slow;
	
	public SlowEffect(double slow) {
		this.slow = slow;
	}
	
	// effekt alkalmazása ellenségen
	@Override
	public void apply(Enemy enemy) {
		enemy.modifyMovementMultiplier(slow);
		Console.println(this + ": " + enemy + "movement speed-je csökkentve!");
	}
	
	@Override
	public void restore(Enemy enemy) {
		enemy.modifyMovementMultiplier(-slow);
		Console.println(this + "lejárt: " + enemy + "movement speed-je visszaállítva!");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
