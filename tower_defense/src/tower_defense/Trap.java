package tower_defense;

public abstract class Trap extends GameObject {
	private static final long serialVersionUID = -1174807768236727847L;

	protected static double range;

	protected Trap(double x, double y) {
		super(x, y);
	}
}
