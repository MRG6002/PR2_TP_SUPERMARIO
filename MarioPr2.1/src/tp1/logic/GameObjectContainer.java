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
	
	public GameObjectContainer() {objects = new ArrayList<>();}
	
	public void add(GameObject object) {objects.add(object);}
	
	public void update() {
		for (GameObject object : objects) {
			object.update();
			this.doInteractionsFrom(object);
		}
		removeDead();
	}
	
	//los booleanos de interactWith los usamos si mario ha perdido vida en la interaccion
	//por ello, toda interaccion que no pueda matar a mario, dara false
	public void doInteractionsFrom(GameObject object) {
		if(object.isAlive()) {
			for(GameObject o: this.objects) { 
				if(o.isAlive()) {
					if(o.interactWith(object) || object.interactWith(o)) return;
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
		for(GameObject o: this.objects) {
			if(o.isSolid() && o.isInPosition(position)) return true;
		}
	return false;
	}

	public String postitionToString(Position position) {
		StringBuilder stringBuilder = new StringBuilder();
		for(GameObject o: this.objects) {
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
