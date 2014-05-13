package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;
	
	private static int duration = 250;
	
	public FogEffect() {
		super(duration);
	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		tower.modifyRange(-50);
	}
	
	@Override	//Effect lev�tele
	public void restore(Tower tower) {
		tower.modifyRange(50);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "fogEffect";
	}
}
