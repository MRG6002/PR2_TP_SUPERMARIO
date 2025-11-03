// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.control.commands;

import tp1.logic.GameModel;

import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
	private static final String NAME = "reset";
	private static final String SHORTCUT = "r";
	private static final String DETAILS = "[r]eset [numLevel]";
	private static final String HELP = "reset the game to initial configuration if not numLevel else load the numLevel map";
	
	private int level;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.level = -1;
	}
	
	public ResetCommand(int level) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.level = level;
	}

	@Override
	public void execute(GameModel game, GameView view) {
		if(this.level == -1) {
			game.reset();
			view.showGame();
		}
		else if(this.level == 0 || this.level == 1)  {
			game.reset(level);
			view.showGame();
		}
		else view.showError(Messages.INVALID_LEVEL_NUMBER);
	}

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 1) command = new ResetCommand();
			if(commandWords.length == 2) command = new ResetCommand(Integer.parseInt(commandWords[1]));
		}
	return command;
	}
}
