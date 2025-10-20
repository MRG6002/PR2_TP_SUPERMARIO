//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.view;

import tp1.logic.GameStatus;

public abstract class GameView implements ViewInterface{

	protected GameStatus game;
	
	public GameView(GameStatus game) {
		this.game = game;
	}
	
}
