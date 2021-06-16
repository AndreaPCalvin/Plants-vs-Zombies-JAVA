package Comandos;

import logic.Game;

public class NoneCommand extends NoParamsCommand {
	private static final String commandText ="none";
	private static final String commandTextMsg = "none";
	private static final String helpTextMsg = "skips cycle";
	
	public NoneCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;		
	}
	
	@Override 
	public Command parse(String[] commandWords) throws ParseException {
		Command com =  null;
		if(commandWords[0].toLowerCase().equals(commandName.toLowerCase()) || 
				commandWords[0].toLowerCase().equals(commandName.toLowerCase().substring(0,1)) ||
				(commandWords[0].equals(""))) {
			
			if (commandWords.length != 1) {
				throw new ParseException(args + commandName);
			}
			else
			com= this;
		}
		return com;
	}

}
