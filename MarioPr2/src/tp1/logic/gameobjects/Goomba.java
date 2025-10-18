package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
	
	public Goomba(Game game, Position pos, Action direccion) {
		super(game, pos, direccion);
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public void update(List <Land> listLand) { //actualizaciones para Goombas
		Position pos = new Position(this.direccion.getX(), this.direccion.getY());
		if(this.GoombaColisiona(listLand, new Position(0,1))) {
			if(this.GoombaColisiona(listLand, pos) || this.pos.EsBorde(this.direccion == Action.RIGHT)) {
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
	
	public boolean GoombaColisiona(List <Land> listLand, Position pos) {
		for(Land land: listLand) {
			if(land.isInPosition(this.pos.sumar(pos))) {
				return true;
			}
		}
		return false;
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

