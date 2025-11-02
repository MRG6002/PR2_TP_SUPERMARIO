// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.GameModel;

import tp1.view.GameView;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = "action";
	private static final String SHORTCUT = "a";
	private static final String DETAILS = "[a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+";
	private static final String HELP = "user performs actions";
	
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
