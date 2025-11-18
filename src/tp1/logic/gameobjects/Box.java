package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

import tp1.view.Messages;

public class Box extends GameObject {
	private static final String NAME = "box";
	private static final String SHORTCUT = "b";
	private static final int POINTS = 50;
	
	private boolean full;
	
	Box() {
		super(null, NAME, SHORTCUT, null);
	}
	
	public Box(Position position, GameWorld game) {
		super(position, NAME, SHORTCUT, game);
		this.full = true;
	}
	
	private Box(Position position, GameWorld game, boolean full) {
		super(position, NAME, SHORTCUT, game);
		this.full = full;
	}
	
	@Override
	public boolean isSolid() {
	return true;
	}

	@Override
	public void update() {
	}

	@Override
	public String getIcon() {
		StringBuilder stringBuilder = new StringBuilder();
		
		if(this.full) stringBuilder.append(Messages.BOX);
		else stringBuilder.append(Messages.EMPTY_BOX);
	return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("BOX: ").append(this.position.toString()).append(" SOLID ");
		if(this.full) stringBuilder.append("FULL");
		else stringBuilder.append("EMPTY");
	return stringBuilder.toString();
	}
	
	@Override
	public boolean interactWith(GameItem gameItem) {
		boolean canInteract = this.full && gameItem.isInPosition(this.position) && gameItem.receiveInteraction(this), doInteract = false;;
		if(canInteract) {
			Position position = this.position.go(Action.UP);
			
			if(!this.game.isSolid(position) && position.isValid()) {
				doInteract = true;
				this.game.delayedAdd(new Mushroom(position, this.game));
				this.full = false; 
				this.game.addPoints(POINTS);
			}
		}
	return canInteract && doInteract;
	}
	
	private boolean isFull(String string) {
	return string.equalsIgnoreCase("full") || string.equalsIgnoreCase("f");
	}

	private boolean isEmpty(String string) {
	return string.equalsIgnoreCase("empty") || string.equalsIgnoreCase("e");
	}
	
	@Override
	public GameObject parse(String[] objectWords, GameWorld game) {
		GameObject gameObject = null;
		
		if((objectWords.length == 2 || objectWords.length == 3) && matchObjectName(objectWords[1])) {
			Position position = Position.parseString(objectWords[0]);
			
			if(position != null) {
				if(objectWords.length == 2) gameObject = new Box(position, game);
				else { // objectWords.length == 3
					if(this.isFull(objectWords[2])) gameObject = new Box(position, game);
					else if(this.isEmpty(objectWords[2])) gameObject = new Box(position, game, false);
				}
			}
		}
	return gameObject;
	}
}
