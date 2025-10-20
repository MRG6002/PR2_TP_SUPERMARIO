//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject{
	private Action direccion;
	private boolean isFalling;
	
	public MovingObject(Game game, Position pos, Action direccion) {
		super(game, pos);
		this.direccion = direccion;
		this.isFalling = false;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void invertirDireccion() {
		if(this.direccion == Action.LEFT) this.direccion = Action.RIGHT;
		else if(this.direccion == Action.RIGHT) this.direccion = Action.LEFT;
	}
	
	protected boolean dirEquals(Action act) {
		return this.direccion == act;
	}
	
	protected void changeDireccion(Action act) {
		this.direccion = act;
	}
	
	protected Action getDireccion() {
		return this.direccion;
	}
	
	protected boolean isFalling() {
		return this.isFalling;
	}
	
	protected void notFalling() {
		this.isFalling = false;
	}
	
	protected void falling() {
		this.isFalling = true;
	}
	
	@Override 
	public String toString() {
		return super.toString() + this.direccion.toString() + " isFalling: " + this.isFalling();
	}
}
