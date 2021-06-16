package Comandos;

import logic.Game;

public class HelpCommand extends NoParamsCommand {

	private static final String commandText ="help"; 
	private static final String commandTextMsg = "[H]elp";
	private static final String helpTextMsg = "print this help message.";
	
	public HelpCommand() {
		super(commandText,commandTextMsg,helpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	};
}
