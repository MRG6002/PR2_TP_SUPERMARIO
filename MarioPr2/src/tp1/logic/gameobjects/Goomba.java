//Grupo 13: MarioRosellGarcía - XiangLin

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
		Position pos = new Position(this.getDireccion().getX(), this.getDireccion().getY());
		if(this.game.isSolid(this.pos.sumar(new Position(0,1)))) {
			if(this.game.isSolid(this.pos.sumar(pos)) || this.pos.EsBorde(this.dirEquals(Action.RIGHT))) {
				this.invertirDireccion();
			}
			else this.move(this.getDireccion());
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
		return "Goomba " + super.toString() + " vivo:" + this.isAlive() + " Direccion:" + this.getDireccion().toString();
	}
}

