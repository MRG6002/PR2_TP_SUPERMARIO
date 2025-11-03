// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameModel;

import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
	private static final String HELP = Messages.COMMAND_ACTION_HELP;
	
	private ActionList actionList;

	public ActionCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.actionList = new ActionList();
	}
	
	public ActionCommand(ActionList actionList) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.actionList = new ActionList();
		for(Action action: actionList) this.actionList.addLast(action);
	}

	@Override
	public void execute(GameModel game, GameView view) {
		if(0 < this.actionList.size()) {
			for(Action action: this.actionList) game.addAction(action);
		}
		game.update();
		view.showGame();
	}

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if(this.matchCommandName(commandWords[0]) && 1 < commandWords.length) {
			if(this.actionList.parse(commandWords)) {
				command = new ActionCommand(actionList);
				this.actionList.clear();
			}
			else command = new ActionCommand();
		}
	return command;
	}
}
