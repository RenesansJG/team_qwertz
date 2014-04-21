package tower_defense;

public class Dwarf extends Enemy {
	private static final long serialVersionUID = -4887012896252201347L;

	private static double baseHP = 125;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 3;

	protected Dwarf(Node targetNode, int level) {
		super(targetNode, baseMove, level);
		hp = new HP(baseHP + level * levelHP, baseRes + levelRes * level, 0, 0);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "dwarf#" + id;
	}
}
