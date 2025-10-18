package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject {
	private ActionList actions;
	boolean big;
	Position posBig;
	
	public Mario(Game game, Position pos, Action direccion, ActionList actions) {
		super(game, pos, direccion);
		this.actions = actions;
		this.big = true;
		Position aux = new Position(0, -1).sumar(this.pos);
		if(aux.esValida()) {
			this.posBig = aux;
		}
	}
	
	public String getIcon() {
		String aux = "";
		if(this.direccion == Action.STOP)  {
			aux = Messages.MARIO_STOP;
		}
		else if(this.direccion == Action.RIGHT) {
			aux = Messages.MARIO_RIGHT;
		}
		else if(this.direccion == Action.LEFT) {
			aux = Messages.MARIO_LEFT;
		}
		return aux;
	}
	
	public void update() {
		//TODO fill your code
	}
}
