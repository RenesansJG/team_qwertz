package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	private static int duration = 250;
	
	private double slow;
	
	
	public SlowEffect(double slow) {
		super(duration);
		this.slow = slow;
	}
	
	// effekt alkalmazása ellenségen
	@Override
	public void apply(Enemy enemy) {
		enemy.modifyMovementMultiplier(slow);
	}
	
	@Override //Effect levétele
	public void restore(Enemy enemy) {
		enemy.modifyMovementMultiplier(-slow);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
