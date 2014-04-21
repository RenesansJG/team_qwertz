package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;

	public FogEffect() {

	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		
	}
	
	@Override
	public void restore(Tower tower) {
		
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "fogEffect#" + id;
	}
}
