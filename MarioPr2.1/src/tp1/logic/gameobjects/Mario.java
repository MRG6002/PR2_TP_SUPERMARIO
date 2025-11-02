// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Mario extends MovingObject {
	private boolean big;
	private ActionList actionList;

	public Mario(Position position, GameWorld game) {
		super(position, game, Action.RIGHT);
		this.big = true;
		this.actionList = new ActionList();
	}
	
	@Override
	public boolean isInPosition(Position position) {
	return super.isInPosition(position) || (this.big && (this.position.go(Action.UP).equals(position)));
	}
	
	@Override
	public boolean isSolid() {
	return false;
	}
	
	@Override
	public void update() {
		Position position = this.position.go(Action.STOP); // Guardamos la position actual

		this.playerMovement();
		if(this.position.equals(position) && !super.isInDirection(Action.STOP)) { // Si Mario no se ha movido tras ejecutar las acciones, se aplica su movimiento automático
			super.automaticMovement();
			this.game.doInteractionsFrom(this);
			if(!super.isAlive()) this.game.marioDead();
		}
	}
	
	@Override
	public String getIcon() {
		StringBuilder stringBuilder = new StringBuilder();
		
		if(this.isInDirection(Action.STOP)) stringBuilder.append(Messages.MARIO_STOP);
		else if(this.isInDirection(Action.LEFT)) stringBuilder.append(Messages.MARIO_LEFT);
		else stringBuilder.append(Messages.MARIO_RIGHT); // this.direction == Action.RIGHT (nunca a ser UP o DOWN)
	return stringBuilder.toString(); 
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("MARIO: ").append(super.toString());
		if(this.big) stringBuilder.append("BIG ");
		else stringBuilder.append("NOT BIG ");
		if(this.isFalling()) stringBuilder.append("FALLING ");
		else stringBuilder.append("NOT FALLING ");
		stringBuilder.append("NOT SOLID");		
	return stringBuilder.toString();
	}
	
	private void playerMovement() {
		for(Action action: this.actionList) {
			if(action.isAction(Action.DOWN)) {
				if(this.game.isSolid(this.position.go(Action.DOWN))) super.stop();
				else {
					while(super.freeFalling()) this.game.doInteractionsFrom(this);
					if(!super.isAlive()) this.game.marioDead();
				}
			}
			else if(action.isAction(Action.UP)) {
				super.up(big);
				this.game.doInteractionsFrom(this);
			}
			else if (action.isAction(Action.STOP)){
				super.stop();
			}
			else { // action.isAction(Action.LEFT) || action.isAction(Action.RIGHT)
				super.doAction(action);
				this.game.doInteractionsFrom(this);
			}
		}
		this.actionList.clear();
	}

	public void addAction(Action action) {
		this.actionList.addLast(action);
	}
	
	public boolean interactWith(ExitDoor exitDoor) {
		boolean interaction = false;
		
		if(exitDoor.isInPosition(this.position)) {
			interaction = true;
			this.game.marioExited();
		}
	return interaction;
	}
	
	public boolean interactWith(Goomba goomba) {
		boolean interaction = false;
		
		if(goomba.isInPosition(this.position) || (this.big && goomba.isInPosition(this.position.go(Action.UP)))) {
			interaction = true;
			if(this.big) {
				if(!super.isFalling()) this.big = false;
			}
			else {
				if(!super.isFalling()) this.game.marioDead();
			}
			goomba.receiveInteraction(this);
		}
	return interaction;
	}
}
