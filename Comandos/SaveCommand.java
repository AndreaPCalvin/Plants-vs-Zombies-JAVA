package Comandos;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import logic.Game;
import pintar.MyStringUtils;

public class SaveCommand extends Command{
	
	private String nombrefichero;
	private static final String commandText ="save";
	private static final String commandTextMsg = "save filename";
	private static final String helpTextMsg = "save the game";
	private static final String args = "Incorrect number of arguments for ";
	private static final String incorrectname = " is not a valid filename because it contains invalid characters";
	private static final String exito1 = "Game successfully saved in file ";
	private static final String exito2 = ". Use the load command to reload it";

	public SaveCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws ExecuteException{
		
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombrefichero)));		
			bw.write(game.getCabecera());
			bw.newLine();
			bw.write(System.lineSeparator());
			game.save(bw); 
			bw.close();
			System.out.println(exito1+ nombrefichero +exito2);
		}
		catch(IOException ex) {
			throw new ExecuteException();
		}
		
		return false;
	}


	@Override
	public Command parse(String[] commandWords) throws ParseException {
		Command com=null;
		// comprobar parametros y args, que vengan bien
		if (commandWords[0].toLowerCase().equals(commandName.toLowerCase())
				|| commandWords[0].toLowerCase().equals(commandName.toLowerCase().substring(0, 1))) {

			if (commandWords.length != 2) {
				throw new ParseException(args + commandName);
			}
			// si el fichero es valido: con mystringutils.isvalidfilename(file);
			// si falla, lanza excepcion
			else {
				if (MyStringUtils.isValidFilename(commandWords[1])) {
					nombrefichero = commandWords[1] + ".dat";
					com=this;
				}
				else {
					throw new ParseException(commandWords[1] + incorrectname);
				}
			}
		}
		
		return com;
	}
}
