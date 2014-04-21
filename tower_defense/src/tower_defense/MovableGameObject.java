package tower_defense;

public abstract class MovableGameObject extends GameObject {
	private static final long serialVersionUID = 5551615397261090410L;
	protected double targetX;
	protected double targetY;
	protected double movementSpeed;
	
	protected MovableGameObject(double x, double y, double movementSpeed) {
		super(x, y);
		this.movementSpeed=movementSpeed;
	}
}
