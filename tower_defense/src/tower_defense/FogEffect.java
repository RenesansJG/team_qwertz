package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;
	
	private static int duration = 250;
	
	public FogEffect() {
		super(duration);
	}
	
	// effekt alkalmazása tornyon
	@Override
	public void apply(Tower tower) {
		tower.modifyRange(-10);
	}
	
	@Override	//Effect levétele
	public void restore(Tower tower) {
		tower.modifyRange(10);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "fogEffect";
	}
}
