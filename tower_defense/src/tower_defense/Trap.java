package tower_defense;

public abstract class Trap extends GameObject {
	private static final long serialVersionUID = -1174807768236727847L;

	protected static double range;	//csapda range-ja
	protected double rangeMultiplier;	//a range szorzója

	//alap konstrultok
	protected Trap(double x, double y) {
		super(x, y);
		rangeMultiplier = 1;
		range = 10;
	}
	
	//getter setter
	public void modifyRangeMultiplier(double multiplier)
	{
		rangeMultiplier += multiplier;
	}
}
