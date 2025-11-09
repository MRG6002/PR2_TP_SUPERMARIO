package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject{
	private boolean isOpen;

	public Box(Position position, GameWorld game) {
		super(position, game, "box", "b");
		this.isOpen = false;
	}
	
	public Box(Position position, GameWorld game, boolean open) {
		super(position, game, "box", "b");
		this.isOpen = open;
	}
	
	Box(){
		super(null, null, "box", "b");
		this.isOpen = false;
	}
	
	@Override
	public boolean isSolid() {return true;}
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean interaction = item.isInPosition(this.position.go(Action.DOWN));
		if(interaction) {item.receiveInteraction(this);}
		return interaction;
	}
	
	@Override 
	public boolean receiveInteraction(Mario mario) {
		this.isOpen = true;
		return false;
	}
	
	@Override 
	public Box parse(String objWords[], GameWorld game) {
		Box box = null;	
		if(objWords.length >= 2 && matchObjectName(objWords[1])) {
			Position pos = Position.stringToPosition(objWords[0]);
			if(pos != null) {
				if(objWords.length == 2) box = new Box(pos, game);
				else if(objWords.length == 3) {
					boolean isOpen = false, error = false;
					if (objWords[3].equalsIgnoreCase("full") || objWords[3].equalsIgnoreCase("f")) isOpen = false;
					else if(objWords[3].equalsIgnoreCase("empty") || objWords[3].equalsIgnoreCase("e")) isOpen = true;
					else error = true;
					if(!error) box = new Box(pos, game, isOpen);
				}
			}
		}
	return box;
	}
	
	@Override
	public String getIcon() {
		String string = "";
		if(this.isOpen) string = Messages.EMPTY_BOX;
		else string = Messages.BOX;
		return string;
	}

	@Override
	public String toString() {
	return "BOX: " + this.position.toString() + " SOLID";
	}

}
