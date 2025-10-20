//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Goomba;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int tiempoRestante = 100;
	private int puntos = 0;
	private int numVidas = 3;
	private int nLevel;
	private Mario mario;
	private boolean partidaGanada = false;
	private boolean abandona = false;
	private GameObjectContainer GameObjectContainer;
	
	private void initLevel0() {
		//Lands
		for(int i = 0; i < 15; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		for(int i = 17; i < 30; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		int tamX = 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
						
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				GameObjectContainer.add(new Land(new Position(posIniX + col, posIniY - fila)));
			}
		}
		this.GameObjectContainer.add(new Land(new Position(9,12)));
		this.GameObjectContainer.add(new Land(new Position(12, 12)));
		this.GameObjectContainer.add(new Land(new Position(2, 9)));
		this.GameObjectContainer.add(new Land(new Position(5, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 9)));
		this.GameObjectContainer.add(new Land(new Position(7, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 5)));
		//this.GameObjectContainer.add(new Land(new Position(2, 11)));
		//Mario y puerta
		this.mario = new Mario(this, new Position(0, 12));
		this.GameObjectContainer.add(this.mario);
		this.GameObjectContainer.add(new ExitDoor(new Position(29, 12)));
		//Goombas
		this.GameObjectContainer.add(new Goomba(this, new Position(19, 0)));
	}
	
	private void initLevel1() {
		//Lands
		for(int i = 0; i < 15; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		for(int i = 17; i < 30; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		int tamX = 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
						
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				GameObjectContainer.add(new Land(new Position(posIniX + col, posIniY - fila)));
			}
		}

		this.GameObjectContainer.add(new Land(new Position(9,12)));
		this.GameObjectContainer.add(new Land(new Position(12, 12)));
		this.GameObjectContainer.add(new Land(new Position(2, 9)));
		this.GameObjectContainer.add(new Land(new Position(5, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 9)));
		this.GameObjectContainer.add(new Land(new Position(7, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 5)));
		//Mario y puerta
		this.mario = new Mario(this, new Position(0, 12));
		this.GameObjectContainer.add(this.mario);
		this.GameObjectContainer.add(new ExitDoor(new Position(29, 12)));
		//Goombas
		this.GameObjectContainer.add(new Goomba(this, new Position(6, 12)));
		this.GameObjectContainer.add(new Goomba(this, new Position(8, 12)));
		this.GameObjectContainer.add(new Goomba(this, new Position(11, 12)));
		this.GameObjectContainer.add(new Goomba(this, new Position(14, 12)));
		this.GameObjectContainer.add(new Goomba(this, new Position(10, 10)));
		this.GameObjectContainer.add(new Goomba(this, new Position(6, 4)));
		this.GameObjectContainer.add(new Goomba(this, new Position(19, 0)));
	}
	
	public Game(int nLevel) {
		//Permitira a la larga preguntar si se quieren añadir mas mapas
		this.GameObjectContainer = new GameObjectContainer();
		if(nLevel == 0) {
			initLevel0();
			this.nLevel = 0;
		}
		if(nLevel == 1) {
			initLevel1();
			this.nLevel = 1;
		}
	}
	
	private void inicializarGameObjectContainer(int level) {
		if(level == 0) {
			initLevel0();
			this.nLevel = 0;
		}
		if(level == 1) {
			initLevel1();
			this.nLevel = 1;
		}
	}
	
	public void resetGame(int nLevel) {
		this.tiempoRestante = 100;
		this.GameObjectContainer = new GameObjectContainer();
		this.inicializarGameObjectContainer(nLevel);
	}
	
	public void resetGame() { 
		this.tiempoRestante = 100;
		this.GameObjectContainer = new GameObjectContainer();
		this.inicializarGameObjectContainer(this.nLevel);
	}

	
	public String positionToString(int col, int row) {
		return GameObjectContainer.ContainerEnPos(new Position(col,row));
	}

	public int remainingTime() {
		return this.tiempoRestante;
	}

	public int points() {
		return this.puntos;
	}

	public int numLives() {
		return this.numVidas;
	}
	
	public boolean playerWins() {
		return this.partidaGanada;
	}

	public boolean playerLoses() {
		return this.numVidas == 0 || this.tiempoRestante == 0;
	}
	
	public void abandona() {
		this.abandona = true;
	}

	public boolean isFinished() {
		return this.playerLoses() || this.playerWins() || this.abandona;
	}
	
	public void update() {
		this.restarTiempo();
		this.GameObjectContainer.update();
	}
	
	public boolean doInteractionsFrom(Mario mario) {
		return this.GameObjectContainer.doInteractionsFrom(mario);
	}
	
	public void addAction(Action action) {
		this.GameObjectContainer.addAction(action);
	}
	
	public void marioExited() {
		this.puntos += this.tiempoRestante * 10;
		this.partidaGanada = true;
		this.tiempoRestante = 0;
	}
	
	public void sumar100() {
		this.puntos += 100;
	}
	
	public void perderVida() {
		if(this.numVidas > 0) numVidas--;
	}
	
	public void restarTiempo() {
		if(this.tiempoRestante > 0) this.tiempoRestante--;
	}
	
	public boolean isSolid(Position pos) {
		return this.GameObjectContainer.isSolid(pos);
	}
	
	@Override 
	public String toString() {
		return "Dim_X:" + Game.DIM_X + " Dim_y:" + Game.DIM_Y + " TiempoRestante:" + this.tiempoRestante + 
				" Puntos:" + this.puntos + " numVidas:" + this.numVidas + " nLevel:" + this.nLevel + 
				" PartidaGanada:" + this.partidaGanada + " Abandona:" + this.abandona + 
				this.GameObjectContainer.toString();
	}
}