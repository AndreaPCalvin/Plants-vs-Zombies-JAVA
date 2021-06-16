package logic;

import java.util.Random;

import pintar.GamePrinter;
import pintar.ReleasePrinter;

public class PlantsVsZombies {
    private static final String num = ": the seed must be a number";
    private static final String niv = ": level must be one of: EASY, HARD, INSANE";

	// main de la practica
	// se presupone que siempre que se introduce el tipo de pintado, hay semilla
	public static void main(String[] args) {
		Level level;
		long seed;
		Controller controller;
		Game game;
		GamePrinter gp;
		final String errorb = "Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]";
		
		try {
		// comprueba que el numero de argumentos sea correcto
		if ((args.length < 1) || (args.length > 2))
			throw new MainArgsException();
		else {
		
			level=Level.parse(args[0]);
			if(level==null) {
				throw new WrongLevelException();
			}

			// semilla: si no esta genera una
			if (args.length == 2) {
				seed = Long.parseLong(args[1]);
			} else
				seed = new Random().nextInt(1000);
			// ya estan el nivel y la semilla fijados
			// se hace un new game y se selecciona el modod release por defecto
			gp = new ReleasePrinter();
			game = new Game(level, seed, gp);

			
			controller = new Controller(game);
			controller.run();
			
		}
		} /*catch (ArrayIndexOutOfBoundsException e) {// #args=0
			System.out.println(errorb);
		} */catch (NumberFormatException e) {// seed=a
			System.out.println(errorb + num);
		} catch (MainArgsException e) {
			System.out.println(errorb);
		}
		catch (WrongLevelException e) {
			System.out.println(errorb + niv);
		}

	}
}
