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
	
	public Dwarf(Enemy source) {
		super(source);
	}

	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "dwarf#" + id;
	}

	@Override
	public double getMagicPower() {
		// TODO hogyan sz�m�tsuk a kapott var�zser�t?
		return level * 10.0;
	}
	
	protected Enemy Copy(Enemy source){
		return new Dwarf(source);
	}
}
