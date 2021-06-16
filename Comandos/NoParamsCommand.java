package Comandos;
import logic.Game;

public abstract class NoParamsCommand extends Command {
//constructor
	protected static final String args = "Incorrect number of arguments for ";
	
	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public abstract boolean execute (Game game);
	
	@Override
	public Command parse(String[] commandWords) throws ParseException {
		Command com =  null;
		if (commandWords[0].toLowerCase().equals(commandName.toLowerCase()) ||
				commandWords[0].toLowerCase().equals(commandName.toLowerCase().substring(0, 1))){
			
			if (commandWords.length != 1) {
				throw new ParseException(args + commandName);
			} 
			else {
				com= this;
			}
		}
		
		return com;
	}
}
