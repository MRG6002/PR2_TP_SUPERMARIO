//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import tp1.logic.gameobjects.Mario;

public interface GameWorld {
	public boolean isSolid(Position pos);
	public void marioExited();
	public void doInteractionsFrom(Mario mario);
	public void perderVida();
	public int numLives();
	public void resetGame();
	public void sumar100();
}
