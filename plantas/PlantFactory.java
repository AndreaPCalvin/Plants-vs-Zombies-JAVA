package plantas;

import logic.Game;

public class PlantFactory {

	private static Plant[] availablePlants = { 
			new Sunflower(), 
			new Peashooter(), 
			new Petacereza(), 
			new Nuez() 
	};

	public static Plant getPlant(String plantName, int x, int y, Game game) {
		//Comprobar que el usuario nos ha introducido una planta válida
		Plant p = null;
		if (plantName.equals("peashooter") || plantName.equals("p")) {
			p = new Peashooter(x, y, game);
		} else if (plantName.equals("sunflower") || plantName.equals("s")) {
			p = new Sunflower(x, y, game);
		} else if (plantName.equals("nuez") || plantName.equals("n")) {
			p = new Nuez(x, y, game);
		} else if (plantName.equals("petacereza") || plantName.equals("c")){
			p = new Petacereza(x, y, game);
		}
		//Si no coincide con ningun planta devuelve el valor inicial de p: null.
		return p;
	}
	
	public static boolean existeTipoPlanta(String n) {
		boolean existe=false;
		int i=0;
		while(!existe && i<availablePlants.length) {
			if(availablePlants[i].existe(n)) {
				existe=true;
			}
			else {
				i++;
			}
		}
		return existe;
	}
	
	public static String listOfAvilablePlants() {
		String s = "";
		for (Plant p : availablePlants) {
			s+= p.datos() + System.lineSeparator();
		}
		
		return s;
	}

	public static Plant cargarPlanta(String plantName, int vida, int x, int y, int ciclo, Game game) {
		Plant p = null;
		if (plantName.equals("peashooter") || plantName.equals("p")) {
			p = new Peashooter(x, y, game, vida, ciclo);
		} else if (plantName.equals("sunflower") || plantName.equals("s")) {
			p = new Sunflower(x, y, game, vida, ciclo);
		} else if (plantName.equals("nuez") || plantName.equals("n")) {
			p = new Nuez(x, y, game, vida, ciclo);
		} else if (plantName.equals("petacereza") || plantName.equals("c")){
			p = new Petacereza(x, y, game, vida, ciclo);
		}
		//Si no coincide con ningun planta devuelve el valor inicial de p: null.
		return p;
	}

}
