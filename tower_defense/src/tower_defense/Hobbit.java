package tower_defense;

public class Hobbit extends Enemy {
	private static final long serialVersionUID = -3408698338441606044L;
	
	private static double baseHP = 70;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 3;

	protected Hobbit(Node targetNode, int level) {
		super(targetNode,baseMove, level);
		hp = new HP(baseHP + level * levelHP, 0, 0, baseRes + levelRes * level);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "hobbit#" + id;
	}

	@Override
	public double getMagicPower() {
		// TODO hogyan számítsuk a kapott varázserõt?
		return level * 10.0;
	}
}
