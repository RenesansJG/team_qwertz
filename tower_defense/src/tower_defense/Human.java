package tower_defense;

public class Human extends Enemy {
	private static final long serialVersionUID = 5166440464581339881L;
	
	private static double baseHP = 100;
	private static double levelHP = 5;
	private static double levelRes = 0.05;
	private static double baseMove = 3;

	protected Human(Node targetNode, int level) {
		super(targetNode,baseMove, level);
		hp = new HP(baseHP + level * levelHP, levelRes * level, levelRes * level, levelRes * level);
	}
	
	public Human(Enemy source) {
		super(source);
	}

	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "human#" + id;
	}

	@Override
	public double getMagicPower() {
		// TODO hogyan számítsuk a kapott varázserõt?
		return level * 10.0;
	}
	
	protected Enemy Copy(Enemy source){
		return new Human(source);
	}
}
