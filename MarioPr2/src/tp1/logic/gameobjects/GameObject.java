//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject {

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected GameWorld game; 
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.game = game;
		if(pos.esValida()) {
			this.pos = pos;
		}
	}
	
	public boolean isInPosition(Position p) {
		return this.isAlive && this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	public abstract boolean isSolid();
	public abstract void update();
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		Position pos = new Position(dir.getX(), dir.getY()).sumar(this.pos);
		if(pos.esValida()) this.pos = pos;
	}
	
	@Override
	public String toString() {
		return this.pos.toString() + " isAlive:" + this.isAlive;
	}
}
