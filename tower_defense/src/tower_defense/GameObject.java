package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements ITickable {
	protected double x;
	protected double y;
	protected final List<Effect> effects;
	
	protected GameObject() {
		effects = new ArrayList<Effect>();
	}
	
	public abstract void action() throws IOException;
	public abstract void affect(Effect effect);
	
	// �sszes effekt l�ptet�se (applyTick minden effecten)
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
	
	// effekt hozz�ad�sa
	public final void addEffect(Effect effect) {
		Console.println(this + ".addEffect(" + effect + ")");
		
		effects.add(effect);
	}
	
	@Override
	public final boolean applyTick() throws IOException {
		Console.println(this + ".applyTick()");
		Console.indent();
		
		effect();
		action();
		
		Console.deIndent();
		return false; // egyel�re nem t�rl�nk, false-sal t�r vissza
	}
	
	public final double getDistance(GameObject object) {
		Console.println(this + ".getDistance(" + object + ")");
		
		return 0; // egyel�re nem sz�molunk t�vols�got, 0-val t�r vissza
	}
	
	public boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return false; // alapb�l egy GameObject nem ellens�ges
	}
	
	@Override
	public abstract String toString();
}
