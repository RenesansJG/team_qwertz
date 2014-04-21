package tower_defense;

public class Hobbit extends Enemy {
	private static final long serialVersionUID = -3408698338441606044L;
	
	private static double baseHP = 70;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 3;
	private static double levelMove = 0;

	protected Hobbit(Node targetNode, int level) {
		super(targetNode,baseMove + level * levelMove, level);
		hp = new HP(baseHP + level * levelHP, 0, 0, baseRes + levelRes * level);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "hobbit#" + id;
	}
}
