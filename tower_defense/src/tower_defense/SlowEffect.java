package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	private double slow;
	
	public SlowEffect(double slow) {
		this.slow = slow;
	}
	
	// effekt alkalmaz�sa ellens�gen
	@Override
	public void apply(Enemy enemy) {
		enemy.modifyMovementMultiplier(slow);
		Console.println(this + ": " + enemy + "movement speed-je cs�kkentve!");
	}
	
	@Override
	public void restore(Enemy enemy) {
		enemy.modifyMovementMultiplier(-slow);
		Console.println(this + "lej�rt: " + enemy + "movement speed-je vissza�ll�tva!");
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
