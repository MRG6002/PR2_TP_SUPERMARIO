// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.logic;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Mario;

import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld {
	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private GameObjectContainer gameObjectContainer;
	private int time;
	private int points;
	private int lives;
	private int level;
	private Mario mario;
	private boolean exit;
	private boolean victory;

	public Game(int nLevel) {
		if(nLevel == 0) this.initLevel0();
		else if(nLevel == 1) this.initLevel1(); // nLevel == 1
		else this.initLevelMinus1();
		this.points = 0;
		this.lives = 3;
		this.exit = false;
		this.victory = false; 
	}
	
	public String positionToString(int col, int row) {
		Position position = new Position(row, col);
		return this.gameObjectContainer.postitionToString(position);
	}

	public boolean playerWins() {
	return this.victory;
	}

	public boolean playerLoses() {
	return this.time == 0 || this.lives == 0;
	}
	
	// Not mandatory but recommended
	public void exit() {
		this.exit = true;
	}
	
	public boolean isFinished() {
	return this.playerWins() || this.playerLoses() || this.exit == true;
	}
	
	public void reset() {
		reset(this.level);
	}
	
	public boolean reset(int level) {
		boolean levelExists = true;
		if (level == 0) this.initLevel0();
		else if(level == 1) this.initLevel1(); // level == 1
		else if(level == -1) this.initLevelMinus1();
		else levelExists = false;
		return levelExists;
	}
	
	public boolean isSolid(Position position) {
	return this.gameObjectContainer.isSolid(position);
	}
	
	public void marioDead() {
		this.lives--;
		if(0 < this.lives) this.reset();
		else this.mario.dead(); // this.lives == 0 (nunca va a ser menor que 0)
	}
	
	public void update() {
		this.time--;
		this.gameObjectContainer.update();
	}
	
	public int remainingTime() {
	return this.time;
	}

	public int points() {
	return this.points;
	}

	public int numLives() {
	return this.lives;
	}
	
	public void addAction(Action action) {
		this.restrictions(action);
	}
	
	private void restrictions(Action action) {
		if(action != null) {
			if(action == Action.STOP || (this.mario.count(action) < 4 && !this.mario.isOpposite(action))) this.mario.addAction(action);
		}
	}
	
	public void marioExited() {
		this.points += this.time * 10;
		this.time = 0;
		this.victory = true;
	}
	
	public void addPoints() {
		this.points += 100;
	}
	 
	public void doInteractionsFrom(Mario mario) {
		this.gameObjectContainer.doInteractionsFrom(mario);
	}
	
	public void addObject(GameObject obj) {
		this.gameObjectContainer.add(obj);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("GAME: LEVEL ").append(this.level).append(Messages.SPACE).append(this.time).append("s ").append(this.points).append("pts ").append(this.lives).append("lives ");
		if(this.exit) stringBuilder.append("EXITED");
		else stringBuilder.append("NOT EXITED YET");
		if(this.victory) stringBuilder.append(Messages.LINE.formatted(" VICTORY"));
		else stringBuilder.append(Messages.LINE.formatted(" NO VICTORY YET"));
		stringBuilder.append(this.gameObjectContainer.toString());
	return stringBuilder.toString();
	}
	
	private void initLevelMinus1() {
		this.time = 100;
		this.level = -1;
		this.lives = 3;
		gameObjectContainer = new GameObjectContainer();
	}
	
	private void initLevel0() {
		this.time = 100;
		this.level = 0;
		// 1. Lands
		gameObjectContainer = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjectContainer.add(new Land(new Position(13, col), this));
			gameObjectContainer.add(new Land(new Position(14, col), this));		
		}
		gameObjectContainer.add(new Land(new Position(Game.DIM_Y - 3, 9), this));
		gameObjectContainer.add(new Land(new Position(Game.DIM_Y - 3, 12), this));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjectContainer.add(new Land(new Position(Game.DIM_Y - 2, col), this));
			gameObjectContainer.add(new Land(new Position(Game.DIM_Y - 1, col), this));		
		}
		gameObjectContainer.add(new Land(new Position(9, 2), this));
		gameObjectContainer.add(new Land(new Position(9, 5), this));
		gameObjectContainer.add(new Land(new Position(9, 6), this));
		gameObjectContainer.add(new Land(new Position(9, 7), this));
		gameObjectContainer.add(new Land(new Position(5, 6), this));

		int tamX = 8;
		int posIniX = Game.DIM_X - 3 - tamX, posIniY = Game.DIM_Y - 3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col + 1; fila++) {
				gameObjectContainer.add(new Land(new Position(posIniY - fila, posIniX + col), this));
			}
		}
		// 2. ExitDoor
		gameObjectContainer.add(new ExitDoor(new Position(Game.DIM_Y - 3, Game.DIM_X - 1), this));
		// 3. Mario
		this.mario = new Mario(new Position(Game.DIM_Y - 3, 0), this);
		gameObjectContainer.add(this.mario);
		// 4. Goombas
		gameObjectContainer.add(new Goomba(new Position(0, 19), this));
	}
	
	private void initLevel1() {
		this.initLevel0();
		this.level = 1;
		// 4. Goombas adicionales
		gameObjectContainer.add(new Goomba(new Position(4, 6), this));
		gameObjectContainer.add(new Goomba(new Position(12, 6), this));
		gameObjectContainer.add(new Goomba(new Position(12, 8), this));
		gameObjectContainer.add(new Goomba(new Position(10, 10), this));
		gameObjectContainer.add(new Goomba(new Position(12, 11), this));
		gameObjectContainer.add(new Goomba(new Position(12, 14), this));
	}
	
	//Funciones necesarias para generador de objetos
	@Override
	public GameWorld getGameWorld() {
		return this;
	}

	@Override
	public void conectMario(Mario mario) {
		this.mario = mario;
	}
}
