// Grupo 13: XiangLin - MarioRosellGarcia 

package tp1.logic;

import tp1.logic.gameobjects.Mario;

public interface GameWorld {

	public void addPoints();
	public void doInteractionsFrom(Mario mario);
	public void marioDead();
	public boolean isSolid(Position position);
	public void marioExited();
}
