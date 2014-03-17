package tower_defense;

public class SlowEffect extends Effect {
	private static final long serialVersionUID = -1864723360268261902L;

	public SlowEffect() {
		Console.println(this + " = new SlowEffect()");
	}
	
	@Override
	public String toString() {
		return "slowEffect#" + id;
	}
}
