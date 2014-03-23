package tower_defense;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	private static int count;
	protected final int id;
	
	//protected double x;
	//protected double y;
	protected final List<Effect> effects;
	
	protected GameObject() {
		id = count++;
		
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action() throws IOException; // tev�kenys�g
	public abstract void affect(Effect effect);          // accept f�ggv�ny visitor patternhez
	
	// �sszes effekt l�ptet�se (applyTick minden effecten)
	public final void effect() {
		Console.println(this + ".effect()");
		Console.indent();
		
		// minden effektre a list�ban
		for (Effect effect : effects) {
			// alkalmazzuk a ticket, �s megn�zz�k hogy lej�rt-e
			boolean effectIsToBeRemoved = effect.applyTick();
			
			// ha lej�rt, t�r�lj�k
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
	
	// tick alkalmaz�sa
	@Override
	public final boolean applyTick() throws IOException {
		Console.println(this + ".applyTick()");
		Console.indent();
		
		// effektek l�ptet�se �s az objektum tev�kenys�g�nek megh�v�sa
		effect();                         // l�ptet�s
		boolean isToBeRemoved = action(); // tev�kenys�g, visszat�r, hogy t�rlend�-e az objektum
		
		Console.deIndent();
		return isToBeRemoved; // visszat�r�nk, hogy t�rlend�-e
	}
	
	// t�vols�g sz�m�t�sa
	public final double getDistance(GameObject object) {
		Console.println(this + ".getDistance(" + object + ")");
		
		return 0; // egyel�re nem sz�molunk t�vols�got, 0-val t�r vissza
	}
	
	// ellens�ges-e a gameObject
	public boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return false; // alapb�l egy GameObject nem ellens�ges
	}
	
	@Override
	public abstract String toString();
}
