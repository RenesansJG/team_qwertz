package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	public SlowEffect() {

	}
	
	// effekt alkalmaz�sa ellens�gen
	@Override
	public void apply(Enemy enemy) {

	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
