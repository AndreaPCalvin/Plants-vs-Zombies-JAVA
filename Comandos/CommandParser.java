package Comandos;

public class CommandParser {

	private static final String wrongcom = "Unknown command. Use ’help’ to see the available commands";
	private static Command[] availableCommands = { 
			new AddCommand(), 
			new HelpCommand(), 
			new ResetCommand(),
			new ExitCommand(), 
			new ListCommand(), 
			new NoneCommand(),
			new PrintModeCommand(),
			new SaveCommand(),
			new LoadCommand()
	};

	public Command parseCommand(String[] commandWords) throws ParseException {
		// recorre la lista de commands e invoca a sus parse
		boolean found = false;
		int i = 0;
		Command comando = null;
			
		while (!found && i < availableCommands.length) {
			comando = availableCommands[i].parse(commandWords);
			if (comando != null) {
				found = true;
			} else {
				i++;
			}
		}
		if(comando==null) {
			throw new ParseException(wrongcom);
		}
		
		return comando;
	}

	public static String commandHelp() {
		String help = "";
		for(int i=0; i < availableCommands.length; i++) {

			help+= availableCommands[i].helpText() + System.lineSeparator();
		}
		return help;
	}
}