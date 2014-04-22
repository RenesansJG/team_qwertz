package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;

	public FogEffect() {

	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		tower.modifyRange(-10);
	}
	
	@Override
	public void restore(Tower tower) {
		tower.modifyRange(10);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "fogEffect#" + id;
	}
}
