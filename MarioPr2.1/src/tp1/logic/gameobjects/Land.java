// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Land extends GameObject {
	
	
	public Land(Position position, GameWorld game) {
		super(position, game, "land", "l");
	}
	
	Land() {
		super(null, null, "land", "l");
	}

	@Override
	public boolean isSolid() {return true; }
	
	@Override
	public void update() {
	}
	
	@Override
	public String getIcon() {
	return Messages.LAND;
	}
	
	@Override
	public String toString() {
	return "LAND: " + this.position.toString() + " SOLID";
	}
	
	public  boolean interactWith(GameItem item) {return false;}
	
	/*public Land parse(String objWords[], GameWorld game) {
		Land land = null;
		if(objWords.length == 2 && matchObjectName(objWords[1])) {
			Position pos = Position.stringToPosition(objWords[0]);
			land = new Land(pos, game);
		}
	return land;
	}*/
	
	@Override
	public GameObject newCopy(Position pos, GameWorld game){
		return new Land(pos, game);
	}
	
	
}
