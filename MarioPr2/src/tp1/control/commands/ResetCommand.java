//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {

    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private int numLevel = -1;
    
    public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public Command parse(String[] commandWords) {
		ResetCommand command = null;
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 1) command = new ResetCommand();
			else if(commandWords.length == 2) {
				command = new ResetCommand();
				command.numLevel = Integer.parseInt(commandWords[1]);
			}
		}
		return command;
	}
    
	@Override
	public void execute(GameModel game, GameView view) {
		if(this.numLevel == 0 || this.numLevel == 1) {
			game.resetGame(this.numLevel);
			view.showGame();
		}
		else if(this.numLevel == -1) {
			game.resetGame();
			view.showGame();
		}
		else view.showMessage(Messages.ERROR.formatted(Messages.INVALID_LEVEL_NUMBER));
	}

	@Override
	public String toString() {
		return this.helpText() + " numLevel:" + this.numLevel;
	}

}
