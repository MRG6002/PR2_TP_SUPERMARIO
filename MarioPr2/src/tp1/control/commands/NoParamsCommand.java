package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		NoParamsCommand aux = null;
		if(commandWords.length == 1) {
			if(new HelpCommand().matchCommandName(commandWords[0])) {
				aux = new HelpCommand();
			}
			else if (new ExitCommand().matchCommandName(commandWords[0])) {
				aux = new ExitCommand();
			}
		}
		return aux;
	}
}
