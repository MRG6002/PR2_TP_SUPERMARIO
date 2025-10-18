package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	public ExitDoor(Game game, Position pos) {
		super(game, pos);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public void update() {}
	
	public String getIcon() {
		return Messages.LAND;
	}
}
