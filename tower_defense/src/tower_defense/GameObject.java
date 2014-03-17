package tower_defense;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	private static int count;
	protected int id;
	
	protected double x;
	protected double y;
	protected final List<Effect> effects;
	
	protected GameObject() {
		id = count++;
		
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action() throws IOException;
	public abstract void affect(Effect effect);
	
	// összes effekt léptetése (applyTick minden effecten)
	public final void effect() {
		Console.println(this + ".effect()");
		Console.indent();
		
		for (Effect effect : effects) {
			boolean effectIsToBeRemoved = effect.applyTick();
			
			if (effectIsToBeRemoved) {
				effects.remove(effect);
			}
		}
		
		Console.deIndent();
	}
	
	// effekt hozzáadása
	public final void addEffect(Effect effect) {
		Console.println(this + ".addEffect(" + effect + ")");
		
		effects.add(effect);
	}
	
	@Override
	public final boolean applyTick() throws IOException {
		Console.println(this + ".applyTick()");
		Console.indent();
		
		effect();
		boolean isToBeRemoved = action();
		
		Console.deIndent();
		return isToBeRemoved;
	}
	
	public final double getDistance(GameObject object) {
		Console.println(this + ".getDistance(" + object + ")");
		
		return 0; // egyelõre nem számolunk távolságot, 0-val tér vissza
	}
	
	public boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return false; // alapból egy GameObject nem ellenséges
	}
	
	@Override
	public abstract String toString();
}
