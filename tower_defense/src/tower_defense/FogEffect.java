package tower_defense;

public class FogEffect extends Effect {
	private static final long serialVersionUID = 4116855433168657924L;

	public FogEffect() {

	}
	
	// effekt alkalmaz�sa tornyon
	@Override
	public void apply(Tower tower) {
		
	}
	
	@Override
	public void restore(Tower tower) {
		
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "fogEffect#" + id;
	}
}
