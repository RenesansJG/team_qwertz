package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	protected final int id;
	
	protected double x;
	protected double y;
	protected final List<Effect> effects;
	
	//Alap konstruktor mindenkire
	protected GameObject(double x, double y) {
		id = Game.getNextObjectId();
		this.x=x;
		this.y=y;
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action(); // tev�kenys�g
	public abstract void affect(Effect effect);          // accept f�ggv�ny visitor patternhez
	
	// �sszes effekt l�ptet�se (applyTick minden effecten)
	public final void effect() {
		Iterator<Effect> effectIt = effects.iterator();
		
		// minden effektre a list�ban
		while (effectIt.hasNext()) {
			Effect effect = effectIt.next();
			
			// alkalmazzuk a ticket, �s megn�zz�k hogy lej�rt-e
			boolean effectIsToBeRemoved = effect.applyTick();
			
			// ha lej�rt, t�r�lj�k
			if (effectIsToBeRemoved) {
				effectIt.remove();
			}
		}
	}
	
	// effekt hozz�ad�sa
	public final void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	// tick alkalmaz�sa
	@Override
	public final boolean applyTick(){
		// effektek l�ptet�se �s az objektum tev�kenys�g�nek megh�v�sa
		effect();                         // l�ptet�s
		boolean isToBeRemoved = action(); // tev�kenys�g, visszat�r, hogy t�rlend�-e az objektum
		
		return isToBeRemoved; // visszat�r�nk, hogy t�rlend�-e
	}
	
	// t�vols�g sz�m�t�sa
	public final double getDistance(GameObject object) {
		return Math.sqrt(Math.pow((x-object.x), 2)+Math.pow((y-object.y), 2)); // egyel�re nem sz�molunk t�vols�got, 0-val t�r vissza
	}
	
	// ellens�ges-e a gameObject
	public boolean isEnemy() {
		return false; // alapb�l egy GameObject nem ellens�ges
	}
	
	// trap-e a gameObject
	public boolean isTrap() {
		return false; // alapb�l egy GameObject nem trap
	}
		
	// tower-e a gameObject
	public boolean isTower() {
		return false; // alapb�l egy GameObject nem tower
	}
	
	@Override
	public abstract String toString();
	
	public final String getPosString() {
		return "(" + x + ", " + y + ")";
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public List<Effect> getEffects() {
		return new ArrayList<Effect>(effects);
	}
}
