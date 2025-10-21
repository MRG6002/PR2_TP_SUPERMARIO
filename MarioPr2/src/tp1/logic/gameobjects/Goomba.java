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
		this.movAutomatico();
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

