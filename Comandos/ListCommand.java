package Comandos;

import logic.Game;
import plantas.PlantFactory;

public class ListCommand extends NoParamsCommand {
	
	private static final String commandText ="list"; //nombre de la clase
	private static final String commandTextMsg = "[L]ist";//imprimir nombre
	private static final String helpTextMsg = "print the list of available plants.";//descripcion comportamiento
	
	public ListCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	@Override
	public boolean execute(Game game) {
		System.out.println(PlantFactory.listOfAvilablePlants());
		return false;
	}
	
}
