package Comandos;

import logic.*;

public class ResetCommand extends NoParamsCommand {

	private static final String commandText ="reset"; //Cambiar todos a static final
	private static final String commandTextMsg = "[R]eset";//meter los mensajes que quieras
	private static final String helpTextMsg = "resets game.";
	
	public ResetCommand() {
		super(commandText,commandTextMsg,helpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	};
}
