package tower_defense;

public class Elf extends Enemy {
	private static final long serialVersionUID = 8749863853199308399L;
	
	private static double baseHP = 70;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 3;
	private static double levelMove = 0.2;

	protected Elf(Node targetNode, int level) {
		super(targetNode,baseMove + level * levelMove, level);
		hp = new HP(baseHP + level * levelHP, 0, baseRes + levelRes * level, 0);
	}
	
	public Elf(Enemy source) {
		super(source);
	}

	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "elf#" + id;
	}

	@Override
	public double getMagicPower() {
		// TODO hogyan számítsuk a kapott varázserõt?
		return level * 10.0;
	}
	
	protected Enemy Copy(Enemy source){
		return new Elf(source);
	}
}
