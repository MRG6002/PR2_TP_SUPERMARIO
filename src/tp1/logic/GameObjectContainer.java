// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;

import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> gameObjects;
	private List<GameObject> delayedObjects;

	public GameObjectContainer() {
		this.gameObjects = new ArrayList<>();
		this.delayedObjects = new ArrayList<>();
	}
	
	public void add(GameObject gameObject) {
		this.gameObjects.addLast(gameObject);
	}
	
	public void delayedAdd(GameObject gameObject) {
		this.delayedObjects.addLast(gameObject);
	}
	
	public boolean isSolid(Position position) {
		for(GameObject gameObject: this.gameObjects) {
			if(gameObject.isInPosition(position)) return gameObject.isSolid();
		}
	return false;
	}
	
	private void removeDead() {
		List<GameObject> auxList = new ArrayList<>();
		
		for(GameObject gameObject: this.gameObjects) {
			if(gameObject.isAlive()) auxList.addLast(gameObject);
		}
		this.gameObjects = auxList;
	}
	
	private void addDelayedObjects() {
		for(GameObject gameObject: this.delayedObjects) this.add(gameObject);
		this.delayedObjects.clear();
	}
	
	public void update() {	
		for(GameObject gameObject: this.gameObjects) {
			if(gameObject.isAlive()) {
				gameObject.update();
				this.doInteraction(gameObject);
			}
		}
		this.removeDead();
		this.addDelayedObjects();
	}
	
	public void doInteraction(GameItem gameItem) {
		if(gameItem.isAlive()) {
			for(GameObject gameObject: this.gameObjects) {
				if(gameObject.isAlive() && gameItem.isAlive()) {
					gameItem.interactWith(gameObject);
					if(gameObject.isAlive() && gameItem.isAlive()) gameObject.interactWith(gameItem);
				}
			}
		}
	}
	
	public String postitionToString(Position position) {		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(GameObject gameObject: this.gameObjects) {
			if(gameObject.isInPosition(position) && gameObject.isAlive()) stringBuilder.append(gameObject.getIcon());
		}
	return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(Messages.LINE.formatted("GAMEOBJECTCONTAINER:"));
		for(GameObject gameObject: this.gameObjects) stringBuilder.append(Messages.LINE.formatted(gameObject.toString()));
	return stringBuilder.toString();
	}
}
