package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject{
	public Land(Position pos) {
		super(null, pos);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public void update() {}
	
	public String getIcon() {
		return Messages.LAND;
	}

}
