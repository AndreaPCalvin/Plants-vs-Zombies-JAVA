package Comandos;
import logic.Game;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;

public Command(String commandText, String commandTextMsg, String helpTextMsg){
	commandName = commandText;
	helpText = commandTextMsg;
	helpInfo = helpTextMsg;
}
	//ejecuta la accion de cada comando
	public abstract boolean execute(Game game) throws ExecuteException;
	//compruba que el comando existe y es correcto
	public abstract Command parse(String[] commandWords) throws ParseException;

	public String helpText() {
		return helpText + ": " + helpInfo;
	}
}
