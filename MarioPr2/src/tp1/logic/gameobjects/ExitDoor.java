package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	public ExitDoor(Position pos) {
		super(null, pos);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void update() {}
	
	public String getIcon() {
		return Messages.LAND;
	}
}
