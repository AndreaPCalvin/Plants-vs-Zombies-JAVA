package Comandos;

import logic.*;

public class ExitCommand extends NoParamsCommand{
	
	private static final String commandText ="exit"; //Cambiar todos a static final
	private static final String commandTextMsg = "[E]xit";//meter los mensajes que quieras
	private static final String helpTextMsg = "terminate the program.";
	
	public ExitCommand() {
		super(commandText,commandTextMsg,helpTextMsg);
	}
	
	@Override
	public boolean execute(Game game) {
		game.setSalir(true);
		return false;
	}
	
	
	
}
