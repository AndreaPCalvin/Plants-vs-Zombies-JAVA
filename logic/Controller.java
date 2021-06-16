package logic;

import java.util.Scanner;

import Comandos.Command;
import Comandos.CommandParser;
import Comandos.ExecuteException;
import Comandos.ParseException;


public class Controller {
	private Scanner in;
	private CommandParser commandparser;
	private Game game;
	
	
	public Controller (Game game) {
		in = new Scanner(System.in);
		commandparser = new CommandParser();
		this.game = game;
		
	}
	
	//metodo principal que ejecuta el juego
	public void run() {
		String[] comando;
		game.draw();
		// no se haya perdido, ganado o salido
		while (!game.isFinished() && !game.isSalir()) {

			System.out.println("Introduce un comando: ");
			comando = in.nextLine().toLowerCase().split(" ");
			try {
				Command com = commandparser.parseCommand(comando);// parsea el comando

				if (com != null) {
				 if (com.execute(game))
					game.draw();
				}
			}
			catch (ParseException e) {
				System.out.println(e);
			} catch (ExecuteException e) {
				System.out.println(e);
			} 
		}
		// mensaje fin de juego
		System.out.println("Game over");
		// pierde
		if (game.perder()) {
			System.out.println("Zombies win");
		}
		// gana: ni sales ni pierdes
		else if (!game.isSalir()) {
			System.out.println("You win");
		}
		else {
			System.out.println("User exit");
		}
	}

}
