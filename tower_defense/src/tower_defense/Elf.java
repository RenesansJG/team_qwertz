package tower_defense;

public class Elf extends Enemy {
	private static final long serialVersionUID = 8749863853199308399L;
	
	// elf statjai
	private static double baseHP = 70;
	private static double levelHP = 5;
	private static double baseRes = 0.25;
	private static double levelRes = 0.05;
	private static double baseMove = 0.9;
	private static double levelMove = 0.06;

	// elf ctor
	protected Elf(Node targetNode, int level) {
		super(targetNode,baseMove + level * levelMove, level);
		hp = new HP(baseHP + level * levelHP, 0, baseRes + levelRes * level, 0);
	}
	
	// elf copy ctor
	public Elf(Enemy source) {
		super(source);
	}

	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "elf";
	}

	@Override
	public double getMagicPower() {
		// TODO hogyan sz�m�tsuk a kapott var�zser�t?
		return level * 10.0;
	}
	
	// elf m�sol�sa
	protected Enemy Copy(Enemy source){
		return new Elf(source);
	}
}
