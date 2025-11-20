// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Mario extends MovingObject {
	private static final String NAME = "mario";
	private static final String SHORTCUT = "m";
	
	private boolean big;
	private boolean collidedUp;
	private ActionList actionList;
	
	Mario() {
		super(null, NAME, SHORTCUT, null, null);
	}

	public Mario(Position position, GameWorld game) {
		super(position, NAME, SHORTCUT, game, Action.RIGHT);
		this.big = true;
		this.collidedUp = false;
		this.actionList = new ActionList();
	}
	
	private Mario(Position position, GameWorld game, Action direction, boolean big) {
		super(position, NAME, SHORTCUT, game, direction);
		this.big = big;
		this.collidedUp = false;
		this.actionList = new ActionList();
	}
	
	@Override
	public boolean isInPosition(Position position) {
	return super.isInPosition(position) || (this.big && (this.position.go(Action.UP).equals(position)));
	}
	
	@Override
	public void update() {
		Position position = this.position.go(Action.STOP);

		this.playerMovement();
		if(this.position.equals(position)) { // Si Mario no se ha movido tras ejecutar las acciones, se aplica su movimiento automático
			super.update();
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
		if(this.isFalling()) stringBuilder.append("FALLING");
		else stringBuilder.append("NOT FALLING");	
	return stringBuilder.toString();
	}
	
	private void playerMovement() {
		for(Action action: this.actionList) {
			if(action == Action.DOWN) {
				if(this.game.isSolid(this.position.go(Action.DOWN))) super.stop();
				else {
					while(super.freeFalling() && super.isAlive()) this.game.doInteractionsFrom(this);
					if(!super.isAlive()) this.game.marioDead();
				}
			}
			else if(action == Action.UP) {
				super.up(big);
				this.game.doInteractionsFrom(this);
				this.collidedUp = false;
			}
			else if (action == Action.STOP){
				super.stop();
			}
			else { // action == Action.LEFT || action == Action.RIGHT
				super.doAction(action);
				this.game.doInteractionsFrom(this);
			}
		}
		this.actionList.clear();
	}
	
	@Override
	protected void collidedUp() {
		this.collidedUp = true;
	}
	
	public int count(Action action) {
		int n = 0;
		
		for(Action aux: this.actionList) {
			if(aux == action) n++;
		}
	return n;
	}
	
	public boolean isOpposite(Action action) {
		for(Action aux: this.actionList) {
			if(aux == Action.opposite(action)) return true;
		}
	return false;
	}

	public void addAction(Action action) {
		this.actionList.addLast(action);
	}

	@Override
	public boolean receiveInteraction(ExitDoor exitDoor) {
	return true;
	}

	@Override
	public boolean receiveInteraction(Goomba goomba) {
		if(this.big) {
			if(!super.isFalling()) this.big = false;
		}
		else {
			if(!super.isFalling()) {
				super.dead();
				this.game.marioDead();
			}
		}
	return true;
	}
	
	@Override
	public boolean receiveInteraction(Mushroom mushroom) {
		if(!this.big) this.big = true;
	return true;
	}
	
	@Override
	public boolean receiveInteraction(Box box) {
	return this.collidedUp;
	}
	
	private boolean isBig(String string) {
	return string.equalsIgnoreCase("big") || string.equalsIgnoreCase("b");
	}

	private boolean isSmall(String string) {
	return string.equalsIgnoreCase("small") || string.equalsIgnoreCase("s");
	}
	
	@Override
	public GameObject parse(String[] objectWords, GameWorld game) {
		GameObject gameObject = null;

		if(2 <= objectWords.length && objectWords.length <= 4 && matchObjectName(objectWords[1])) {
			Position position = Position.parseString(objectWords[0]);
			
			if(position != null) {
				if(objectWords.length == 2) gameObject = new Mario(position, game);
				else {
					Action direction = Action.parseAction(objectWords[2]);
					
					if(direction == Action.LEFT || direction == Action.RIGHT || direction == Action.STOP) {
						if(objectWords.length == 3) gameObject = new Mario(position, game, direction, true);
						else { // objectWords.length == 4
							if(this.isBig(objectWords[3])) gameObject = new Mario(position, game, direction, true);
							else if(this.isSmall(objectWords[3])) gameObject = new Mario(position, game, direction, false);
						}
					}
				}
			}
		}
	return gameObject;
	}
	
	@Override
	public void linkWith() {
		this.game.linkWith(this);
	}
}
