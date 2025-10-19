package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject{
	Action direccion;
	boolean isFalling;
	
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
}
