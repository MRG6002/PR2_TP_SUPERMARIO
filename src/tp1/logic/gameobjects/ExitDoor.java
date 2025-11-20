// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class ExitDoor extends GameObject {
	private static final String NAME = "exitdoor";
	private static final String SHORTCUT = "ed";
	
	ExitDoor() {
		super(null, NAME, SHORTCUT, null);
	}

	public ExitDoor(Position position, GameWorld game) {
		super(position, NAME, SHORTCUT, game);
	}
	
	@Override
	public boolean isSolid() {
	return false;
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public String getIcon() {
	return Messages.EXIT_DOOR;
	}
	
	@Override
	public String toString() {
	return "EXITDOOR: " + this.position.toString() + " NOT SOLID";
	}

	@Override
	public boolean interactWith(GameItem gameItem) {
		boolean canInteract = gameItem.isInPosition(this.position) && gameItem.receiveInteraction(this);
		
		if(canInteract) this.game.marioExited();
	return canInteract;
	}
	
	@Override
	GameObject buildWith(Position position, GameWorld game) {
	return new ExitDoor(position, game);
	}
}
