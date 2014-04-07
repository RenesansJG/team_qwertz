package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	public SlowEffect() {

	}
	
	// effekt alkalmazása ellenségen
	@Override
	public void apply(Enemy enemy) {

	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
