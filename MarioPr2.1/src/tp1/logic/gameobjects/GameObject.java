// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem{
	protected Position position; // If you can, make it private
	protected GameWorld game; 
	private boolean isAlive;
	
	public GameObject(Position position, GameWorld game) {
		this.position = position;
		this.game = game;
		this.isAlive = true;
	}
	
	public boolean isInPosition(Position position) {
	return this.position.equals(position);
	}
 	
	public boolean isAlive() {
	return this.isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	// Not mandatory but recommended
	protected void move(Action direction) {
		this.position = this.position.go(direction);
	}
	
	public boolean isSolid() {return false;}
	public abstract void update();
	public abstract String getIcon();
	public abstract String toString();
	
	
	public  boolean receiveInteraction(Land obj) {return false;}
	public  boolean receiveInteraction(ExitDoor obj) {return false;}
	public  boolean receiveInteraction(Mario obj) {return false;}
	public  boolean receiveInteraction(Goomba obj) {return false;}
}
