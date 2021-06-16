package Comandos;

import logic.CeldaOcupadaException;
import logic.Game;
import logic.OutGridException;
import logic.SuncoinException;
import plantas.PlantFactory;

public class AddCommand extends Command{
	
	private static final String commandText ="add";
	private static final String commandinfo = "[A]dd <plant> <x> <y>";
	private static final String helpinfo = "adds a plant in position x, y.";
	private int x;
	private int y;
	private String nombrePlant;
	private static final String wrongplant= "Unknown plant name: ";
	private static final String args="Incorrect number of arguments for add command: " + commandinfo;
	private static final String numberformat = "Invalid argument for add command, number expected: " + commandinfo;

	public AddCommand() {
		super(commandText, commandinfo, helpinfo);
	}

	@Override
	public boolean execute(Game game) throws ExecuteException {
			//si la planta se puede añadir al juego
		try {	
			game.addPlantToGame(nombrePlant, x, y);
			game.update();
		}
		catch (CeldaOcupadaException e) {
			System.out.println(e);
			throw new ExecuteException();
		} catch (SuncoinException e) {
			System.out.println(e);
			throw new ExecuteException();
		} catch (OutGridException e) {
			System.out.println(e);
			throw new ExecuteException();
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws ParseException {
		Command com =  this;
		commandWords[0] = commandWords[0].toLowerCase();
		if (commandWords[0].toLowerCase().equals("add") || commandWords[0].toLowerCase().equals("a")) {
			if (commandWords.length != 4) {
				com = null;
				throw new ParseException(args);
			}
			else {
				//comprueba que el nombre de la planta sea correcto
				if (!PlantFactory.existeTipoPlanta(commandWords[1])) {
					throw new ParseException(wrongplant+commandWords[1]);
				} else {
					nombrePlant = commandWords[1].toLowerCase();
					try {
					x = Integer.parseInt(commandWords[3]);
					y = Integer.parseInt(commandWords[2]);

					com = this;
					}
					catch(NumberFormatException e){
						throw new ParseException(numberformat);
					}
				}
			}
		} else {
			com = null;
		}
		return com;
	}
}
