package tp1.logic;

public interface GameModel {
	public boolean isFinished();
	public void update();
	public void resetGame();
	public void resetGame(int num);
	public void addAction(Action act);
	public void abandona();
}
