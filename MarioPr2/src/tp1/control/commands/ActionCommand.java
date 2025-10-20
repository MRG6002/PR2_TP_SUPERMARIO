//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand{

    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private String[] actions;
    
	public ActionCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public Command parse(String[] commandWords) {
		ActionCommand command = null;
		if(this.matchCommandName(commandWords[0]) && commandWords.length > 1) {
			command = new ActionCommand();
			command.actions = commandWords;
		}
		return command;
	}

	@Override
	public void execute(GameModel game, GameView view) {
		int der = 0, izq = 0, arr = 0, aba = 0, sto = 0;
		for(int i = 1; i < this.actions.length; i++) {
			if((this.actions[i].equalsIgnoreCase("right") || this.actions[i].equalsIgnoreCase("r")) && izq == 0 && der < 4) {
				der++;
				game.addAction(Action.RIGHT);
			}
			else if((this.actions[i].equalsIgnoreCase("left") || this.actions[i].equalsIgnoreCase("l")) && der == 0 && izq < 4) {
				izq++;
				game.addAction(Action.LEFT);
			}
			else if((this.actions[i].equalsIgnoreCase("up") || this.actions[i].equalsIgnoreCase("u")) && aba == 0 && arr < 4) {
				arr++;
				game.addAction(Action.UP);
			}
			else if((this.actions[i].equalsIgnoreCase("down") || this.actions[i].equalsIgnoreCase("d")) && arr == 0 && aba < 4) {
				aba++;
				game.addAction(Action.DOWN);
			}
			else if((this.actions[i].equalsIgnoreCase("stop") || this.actions[i].equalsIgnoreCase("s")) && sto < 4) {
				sto++;
				game.addAction(Action.STOP);
			}
		}
		game.update();
		view.showGame();
	}
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		for(String aux: this.actions) {
			string.append(aux + " ");
		}
		return this.helpText() + " Actions: " + string;
	}
}
