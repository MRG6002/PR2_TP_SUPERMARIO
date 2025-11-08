// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> objects;
	private List<GameObject> delayedObjects;
	private List<GameObject> lands;
	
	public GameObjectContainer() {
		objects = new ArrayList<>();
		delayedObjects = new ArrayList<>();
		lands = new ArrayList<>();
	}
	
	public void add(GameObject object) {
		if(object.getIcon() == Messages.LAND) lands.add(object);
		else objects.add(object);}
	
	public void addDelayed(GameObject object) {
		delayedObjects.add(object);
	}
	
	private void joinLists() {
		for(GameObject o: delayedObjects) {
			objects.add(o);
		}
		delayedObjects.clear();
	}
	
	public void update() {
		for (GameObject object : objects) {
			object.update();
			this.doInteractionsFrom(object);
		}
		removeDead();
		joinLists();
	}
	
	public void doInteractionsFrom(GameObject object) {
		if(object.isAlive()) {
			for(GameObject o: this.objects) { 
				if(object.isAlive() && o.isAlive()) {
					if(object.interactWith(o)) o.interactWith(object);
				}
			}
		}
	}
	
	private void removeDead() {
		List<GameObject> aux = new ArrayList<>();
		for(GameObject o: this.objects) if(o.isAlive()) aux.add(o);
		this.objects = aux;
	}
	
	public boolean isSolid(Position position) {
		for(GameObject o: this.lands) {
			if(o.isSolid() && o.isInPosition(position)) return true;
		}
	return false;
	}

	public String postitionToString(Position position) {
		StringBuilder stringBuilder = new StringBuilder();
		for(GameObject o: this.objects) {
			if(o.isAlive() && o.isInPosition(position)) stringBuilder.append(o.getIcon());
		}
		for(GameObject o: this.lands) {
			if(o.isAlive() && o.isInPosition(position)) stringBuilder.append(o.getIcon());
		}
		return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Messages.LINE.formatted("GAME_OBJECT_CONTAINER:"));
		for(GameObject o: this.objects) stringBuilder.append(Messages.LINE.formatted(o.toString()));
	return stringBuilder.toString();
	}
}
