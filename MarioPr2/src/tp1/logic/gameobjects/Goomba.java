package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
	
	public Goomba(Game game, Position pos) {
		super(game, pos, Action.LEFT);
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public void update() { //actualizaciones para Goombas
		Position pos = new Position(this.direccion.getX(), this.direccion.getY());
		if(this.game.isSolid(this.pos.sumar(new Position(0,1)))) {
			if(this.game.isSolid(this.pos.sumar(pos)) || this.pos.EsBorde(this.direccion == Action.RIGHT)) {
				if(this.direccion.getX() == 1) this.direccion = Action.LEFT;
				else this.direccion = Action.RIGHT;
			}
			else this.move(this.direccion);
		}
		else {
			if(this.pos.estaAbajo()) {
				this.dead();
			}
			else this.move(Action.DOWN);
		}
	}
	
	public boolean recieveInteraction(Mario mario) {
		this.dead();
		return true;
	}
	
	@Override
	public String toString() {
		return "Goomba " + this.pos.toString() + " vivo:" + this.isAlive() + " Direccion:" + this.direccion.toString();
	}
}

