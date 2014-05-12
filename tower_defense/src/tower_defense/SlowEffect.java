package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	private static int duration = 250;
	
	private double slow;
	
	
	public SlowEffect(double slow) {
		super(duration);
		this.slow = slow;
	}
	
	// effekt alkalmaz�sa ellens�gen
	@Override
	public void apply(Enemy enemy) {
		enemy.modifyMovementMultiplier(slow);
	}
	
	@Override //Effect lev�tele
	public void restore(Enemy enemy) {
		enemy.modifyMovementMultiplier(-slow);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
