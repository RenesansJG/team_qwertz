package tower_defense;

public class Dwarf extends Enemy {
	private static final long serialVersionUID = -4887012896252201347L;
	
	// dwarf alap statjai
	private static double baseHP = 125;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 0.6;

	// dwarf ctor
	protected Dwarf(Node targetNode, int level) {
		super(targetNode, baseMove, level);
		hp = new HP(baseHP + level * levelHP, baseRes + levelRes * level, 0, 0);
	}
	
	// dwarf copy ctor
	public Dwarf(Enemy source) {
		super(source);
	}

	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "dwarf";
	}

	// kapott varázserõ
	@Override
	public double getMagicPower() {
		// TODO hogyan számítsuk a kapott varázserõt?
		return level + 10.0;
	}
	
	// enemy másolás (kettéesés)
	protected Enemy Copy(Enemy source){
		return new Dwarf(source);
	}
}
