// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Mario;

import tp1.view.Messages;

public class GameObjectContainer {
	private List<Land> landList;
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
	}
	
	private void deleteDead() {
		List<Goomba> aux = new ArrayList<>();
		for(Goomba g: this.goombaList) if(g.isAlive()) aux.add(g);
		this.goombaList = aux;
	}
	
	public boolean isSolid(Position position) {
		for(Land land: landList) {
			if(land.isInPosition(position)) return true;
		}
	return false;
	}
	
	public void update() {
		this.mario.update();
		this.mario.interactWith(this.exitDoor);	
		for(Goomba goomba: goombaList) goomba.update();
		this.doInteractionsFrom(this.mario);
		this.deleteDead();
	}
	
	public void doInteractionsFrom(Mario mario) {
		for(Goomba goomba: this.goombaList) mario.interactWith(goomba);
	}

	public String postitionToString(Position position) {
		for(Land land: this.landList) {
			if(land.isInPosition(position)) return land.getIcon();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Goomba goomba: this.goombaList) {
			if(goomba.isInPosition(position)) stringBuilder.append(goomba.getIcon());
		}
		if(this.exitDoor.isInPosition(position)) stringBuilder.append(this.exitDoor.getIcon());
		if(this.mario.isInPosition(position) && this.mario.isAlive()) stringBuilder.append(this.mario.getIcon());
	return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(Messages.LINE.formatted("GAMEOBJECTCONTAINER:"));
		for(Land land: this.landList) stringBuilder.append(Messages.LINE.formatted(land.toString()));
		stringBuilder.append(Messages.LINE.formatted(this.exitDoor.toString()));
		stringBuilder.append(Messages.LINE.formatted(this.mario.toString()));
		for(Goomba goomba: this.goombaList) stringBuilder.append(Messages.LINE.formatted(goomba.toString()));
	return stringBuilder.toString();
	}
}
