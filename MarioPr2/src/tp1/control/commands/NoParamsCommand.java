//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		NoParamsCommand aux = null;
		if(commandWords.length == 1 && this.matchCommandName(commandWords[0])) {
			aux = this;
		}
		return aux;
	}
}
