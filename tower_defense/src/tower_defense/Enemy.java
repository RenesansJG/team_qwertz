package tower_defense;

import java.io.IOException;

public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected final HP hp;
	
	protected Enemy() {
		hp = new HP();
	}
	
	// ha lesz takeDamage method és/vagy visitor pattern a damagere is akkor nem kell!
	public final void takeDamage(Damage damage) {
		Console.println(this + ".takeDamage(" + damage + ")");
		Console.indent();
		
		hp.takeDamage(damage);
		
		Console.deIndent();
	}
	
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		// TODO Auto-generated method stub
		
		
		Console.deIndent();
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		Console.indent();
		
		effect.apply(this);
		
		Console.deIndent();
	}
	
	@Override
	public final boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return true; // az enemy ellenséges
	}
	
	
}
