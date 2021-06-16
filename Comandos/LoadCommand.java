package Comandos;

import java.io.*;

import logic.FileContentsException;
import logic.Game;
import pintar.MyStringUtils;

public class LoadCommand extends Command{

	private static final String commandText ="load";
	private static final String commandTextMsg = "load filename";
	private static final String helpTextMsg = "load the game";
	private static final String args = "Incorrect number of arguments for ";
	private static final String nolegible = " is not readable";
	private static final String noexiste = " does not exist. File not found. Remember to write .dat";
	private static final String invalidname = " is not a valid name";
	private String nombrefichero;
	private static final String exito = "Game successfully loaded from file ";
	private static final String wrong_cab = "la cabecera del archivo no coincide con la esperada";
	private static final String pintado = "release";
	
	public LoadCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws ExecuteException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File (nombrefichero)));
			
			if(br.readLine().equals(game.getCabecera())) {
				br.readLine();
				game.load(br);
				br.close();
				System.out.println(exito + nombrefichero);
				System.out.println(System.lineSeparator());
				game.setPrintMode(pintado);
			}
			else {
				br.close();
				throw new ExecuteException (wrong_cab);
			}
		} 
		catch (FileContentsException e) {
			throw new ExecuteException (e);
		} 
		catch (IOException e) {
			throw new ExecuteException ();
		}

		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws ParseException {
		Command com=null;
		// comprobar parametros y args, que vengan bien
		if (commandWords[0].toLowerCase().equals(commandName.toLowerCase())
				|| commandWords[0].toLowerCase().equals("lo")) {

			if (commandWords.length != 2) {
				throw new ParseException(args + commandName);
			}
			else {
				
				if (MyStringUtils.isValidFilename(commandWords[1])) {
					if (MyStringUtils.ﬁleExists(commandWords[1])) {
						if (MyStringUtils.isReadable(commandWords[1])) {
							nombrefichero = commandWords[1];
							com = this;
						}
						else {
							throw new ParseException(commandWords[1] + nolegible);
						}
					}
					else {
						throw new ParseException(commandWords[1] + noexiste);
					}
				}
				else {
					throw new ParseException(commandWords[1] + invalidname);
				}
			}
		}		
		return com;			
	}
}
