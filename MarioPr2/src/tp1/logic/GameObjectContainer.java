//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.view.Messages;


public class GameObjectContainer {
	
	//lands
	private List<Land> listLand;
	public void listaLand() {
		this.listLand = new ArrayList<>();
	}
	public void add(Land land) {
		this.listLand.add(land);
	}

	
	//exit door
	private ExitDoor door;
	public void add(ExitDoor door) {
		this.door = door;
	}
	
	//goombas
	private List<Goomba> listGoomba;
	public void listaGoomba() {
		this.listGoomba = new ArrayList<>();
	}
	public void add(Goomba goomba) {
		this.listGoomba.add(goomba);
	}

	private int numGoombasAqui(Position pos) {
		int cont = 0; 
		for(Goomba goomba: this.listGoomba) {
			if(goomba.isInPosition(pos)) {
				cont++;
				if(cont == 2)return cont;
			}
		}
		return cont;
	}
	
	//mario
	private Mario mario;
	public void add(Mario mario) {
		this.mario = mario;
	}
	
	//constructor general
	public GameObjectContainer() {
		this.listaLand();
		this.listaGoomba();
	}
	
	//funciones
	public String ContainerEnPos(Position pos) {
		String aux = Messages.EMPTY;
		if(this.mario.numVidas() > 0 && this.mario.isInPosition(pos)) {
			aux += mario.getIcon();
		}
		if(this.door.isInPosition(pos)) {
			aux += Messages.EXIT_DOOR;
		}
		int numGoombas = this.numGoombasAqui(pos);
		if (numGoombas > 0) {
			aux = Messages.GOOMBA;
			if(numGoombas == 2) {
				aux += Messages.GOOMBA;
			}
		}
		else if(this.isSolid(pos)) {
			aux = Messages.LAND;
		}
		return aux;
	}
	
	public void update() {
		this.mario.update();
		if(this.mario.interactWith(door)) {
			this.mario.marioExited();
		}
		for(Goomba goomba: this.listGoomba) {
			goomba.update();
		}
		this.doInteractionsFrom(this.mario);
		this.listGoomba.removeIf(goomba -> !goomba.isAlive());
	}
	
	public void addAction(Action action) {
		this.mario.addAction(action);
	}
	
	public void doInteractionsFrom(Mario mario) {
		for(int i = 0; i < this.listGoomba.size(); i++) {
			Goomba goomba = this.listGoomba.get(i);
			mario.interactWith(goomba);
		}
	}
	
	//cuando cambie gameObjectContainer recorreremos la unica lista de objects
	//y preguntaremos isSolid(), al cual solo responden true los Lands.
	public boolean isSolid(Position pos) {
		for(Land land: this.listLand) {
			if(land.isInPosition(pos)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append(this.mario.toString());
		string.append(this.door.toString());
		for(Land land: this.listLand) {
			string.append(land.toString());
		}
		for(Goomba goomba: this.listGoomba) {
			string.append(goomba.toString());
		}
		return string.toString();
	}
}
