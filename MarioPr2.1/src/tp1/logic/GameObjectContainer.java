// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
/*import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Mario;*/

import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> objects;
	private List<GameObject> lands; // para el testeo nos molestan los lands
	public GameObjectContainer() {objects = new ArrayList<>();
	lands = new ArrayList<>();
	}
	
	public void add(GameObject object) {
		if(object.getIcon() == Messages.LAND) {
			lands.add(object);
		}
		else objects.add(object);
	}
	
	public void update() {
		for (GameObject object : objects) {
			object.update();
			this.doInteractionsFrom(object);
		}
		removeDead();
	}
	
	public void doInteractionsFrom(GameObject object) {
		if(object.isAlive()) {
			for(GameObject o: this.objects) {
				if(o.isAlive()) {
					o.interactWith(object);
					object.interactWith(o);
				}
			}
		}
	}
	/*private List<Land> landList;
	private List<Goomba> goombaList;
	private ExitDoor exitDoor;
	private Mario mario;
	
	public GameObjectContainer() {
		landList = new ArrayList<>();
		goombaList = new ArrayList<>();
	}
	
	public void add(Land land) {
		this.landList.addLast(land);
	}
	
	public void add(Goomba goomba) {
		this.goombaList.addLast(goomba);
	}
	
	public void add(ExitDoor exitDoor) {
		this.exitDoor = exitDoor;
	}
	
	public void add(Mario mario) {
		this.mario = mario;
	}*/
	
	private void removeDead() {
		List<GameObject> aux = new ArrayList<>();
		for(GameObject o: this.objects) if(o.isAlive()) aux.add(o);
		this.objects = aux;
	}
	
	public boolean isSolid(Position position) {
		for(GameObject o: lands) {
			if(o.isSolid() && o.isInPosition(position)) return true;
		}
	return false;
	}
	
	/*public void update() {
		this.mario.update();
		this.mario.interactWith(this.exitDoor);	
		for(Goomba goomba: goombaList) goomba.update();
		this.doInteractionsFrom(this.mario);
		this.deleteDead();
	}*/
	
	/*public void doInteractionsFrom(Mario mario) {
		for(Goomba goomba: this.goombaList) mario.interactWith(goomba);
	}*/

	public String postitionToString(Position position) {
		StringBuilder stringBuilder = new StringBuilder();
		for(GameObject o: this.objects) {
			if(o.isAlive() && o.isInPosition(position)) stringBuilder.append(o.getIcon());
		}
		for(GameObject o: this.lands) {
			if(o.isAlive() && o.isInPosition(position)) stringBuilder.append(o.getIcon());
		}
		return stringBuilder.toString();
		/*for(Land land: this.landList) {
			if(land.isInPosition(position)) return land.getIcon();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Goomba goomba: this.goombaList) {
			if(goomba.isInPosition(position)) stringBuilder.append(goomba.getIcon());
		}
		if(this.exitDoor.isInPosition(position)) stringBuilder.append(this.exitDoor.getIcon());
		if(this.mario.isInPosition(position) && this.mario.isAlive()) stringBuilder.append(this.mario.getIcon());*/
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Messages.LINE.formatted("GAME_OBJECT_CONTAINER:"));
		for(GameObject o: this.objects) stringBuilder.append(Messages.LINE.formatted(o.toString()));
	return stringBuilder.toString();
	}
}
