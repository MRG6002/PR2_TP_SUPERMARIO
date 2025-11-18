// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;

import tp1.view.Messages;

public class Land extends GameObject {
	private static final String NAME = "land";
	private static final String SHORTCUT = "l";
	
	Land() {
		super(null, NAME, SHORTCUT, null);
	}
	
	public Land(Position position, GameWorld game) {
		super(position, NAME, SHORTCUT, game);
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
	return Messages.LAND;
	}
	
	@Override
	public String toString() {
	return "LAND: " + this.position.toString() + " SOLID";
	}
	
	@Override
	GameObject buildWith(Position position, GameWorld game) {
	return new Land(position, game);
	}
}
