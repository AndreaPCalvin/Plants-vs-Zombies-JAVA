package Comandos;

import logic.Game;

public class PrintModeCommand extends Command{
	private static final String commandText = "printmode";
	private static final String commandinfo = "[P]rintMode: ";
	private static final String helpinfo = "change [P]rintmode [Release|Debug].";
	//private GamePrinter gp;
	private String tipo;
	private static final String args="Incorrect number of arguments for printmode command: "+ helpinfo;
	private static final String wrongprint = "Unknown print mode: ";
	
	public PrintModeCommand() {
		super(commandText, commandinfo, helpinfo);
	}

	@Override
	public boolean execute(Game game) {
		//si llega aqui es que el modod de pintado existe y es correcto
		game.setPrintMode(tipo);
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws ParseException {
		Command com=null;
		if(commandWords[0].toLowerCase().equals(commandText) || 
				commandWords[0].toLowerCase().equals(commandText.substring(0, 1))) {
			if(commandWords.length==2) {
				
				if(commandWords[1].toLowerCase().equals("debug") || commandWords[1].toLowerCase().equals("d")) {
					com=this;
					tipo=commandWords[1].toLowerCase();
				}
				else if(commandWords[1].toLowerCase().equals("release") || commandWords[1].toLowerCase().equals("r")) {
					com=this;
					tipo=commandWords[1].toLowerCase();
				}
				else {
					throw new ParseException(wrongprint+commandWords[1]);
				}
			}
			else {
				throw new ParseException(args);
			}
			
		}
		return com;
	}
	
}
