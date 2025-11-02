// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(0 ,-1), RIGHT(0, 1), DOWN(1, 0), UP(-1, 0), STOP(0, 0);
	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
	return x;
	}

	public int getY() {
	return y;
	}
	
	public boolean isAction(Action action) {
	return this.x == action.getX() && this.y == action.getY();
	}
	
	public static Action parseAction(String commandWord) {
		Action action = null;
		
		if(commandWord.equalsIgnoreCase("l") || commandWord.equalsIgnoreCase("left")) action = Action.LEFT;
		else if(commandWord.equalsIgnoreCase("r") || commandWord.equalsIgnoreCase("right")) action = Action.RIGHT;
		else if(commandWord.equalsIgnoreCase("d") || commandWord.equalsIgnoreCase("down")) action = Action.DOWN;
		else if(commandWord.equalsIgnoreCase("u") || commandWord.equalsIgnoreCase("up")) action = Action.UP;
		else if(commandWord.equalsIgnoreCase("s") || commandWord.equalsIgnoreCase("stop")) action = Action.STOP;
	return action;
	}
	
	public static Action opposite(Action action) {
		Action aux = null;
		
		if(action.isAction(Action.LEFT)) aux = Action.RIGHT;
		else if(action.isAction(Action.RIGHT)) aux = Action.LEFT;
		else if(action.isAction(Action.UP)) aux = Action.DOWN;
		else if(action.isAction(Action.DOWN)) aux = Action.UP;
	return aux;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		if(this.x == -1 && this.y == 0) stringBuilder.append("LEFT");
		else if(this.x == 1 && this.y == 0) stringBuilder.append("RIGHT");
		else if(this.x == 0 && this.y == 1) stringBuilder.append("DOWN");
		else if(this.x == 0 && this.y == -1) stringBuilder.append("UP");
		else stringBuilder.append("STOP");
	return stringBuilder.toString();
	}
}
