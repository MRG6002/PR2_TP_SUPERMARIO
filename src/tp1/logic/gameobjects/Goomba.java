// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Goomba extends MovingObject {
	private static final String NAME = "goomba";
	private static final String SHORTCUT = "g";
	private static final int POINTS = 100;

	
	Goomba() {
		super(null, NAME, SHORTCUT, null, null);
	}
	
	public Goomba(Position position, GameWorld game) {
		super(position, NAME, SHORTCUT, game, Action.LEFT);
	}
	
	private Goomba(Position position, GameWorld game, Action direction) {
		super(position, NAME, SHORTCUT, game, direction);
	}

	@Override
	public String getIcon() {
	return Messages.GOOMBA;
	}
	
	@Override
	public String toString() {
	return "GOOMBA: " + super.toString();
	}
	
	@Override
	public boolean interactWith(GameItem gameItem) {
		boolean canInteract = gameItem.isInPosition(this.position) && gameItem.receiveInteraction(this);
		if(canInteract) {
			super.dead();
			this.game.addPoints(POINTS);
		}
	return canInteract;
	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {
		super.dead();
		this.game.addPoints(POINTS);
	return true;
	}
	
	@Override
	public GameObject parse(String[] objectWords, GameWorld game) {
		GameObject gameObject = null;
		
		if((objectWords.length == 2 || objectWords.length == 3) && matchObjectName(objectWords[1])) {
			Position position = Position.parseString(objectWords[0]);
			
			if(position != null) {
				if(objectWords.length == 2) gameObject = new Goomba(position, game);
				else { // objectWords.length == 3
					Action direction = Action.parseAction(objectWords[2]);
					
					if(direction == Action.LEFT || direction == Action.RIGHT) gameObject = new Goomba(position, game, direction);
				}
			}
		}
	return gameObject;
	}
}
