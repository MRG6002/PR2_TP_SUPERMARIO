// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Goomba extends MovingObject {
	
	public Goomba(Position position, GameWorld game) {
		super(position, game, Action.LEFT);
	}

	@Override
	public String getIcon() {
	return Messages.GOOMBA;
	}
	
	@Override
	public String toString() {
	return "GOOMBA: " + super.toString() + " NOT SOLID";
	}
	
	public boolean receiveInteraction(Mario mario) {
		super.dead();
		this.game.addPoints();
	return true;
	}
}
