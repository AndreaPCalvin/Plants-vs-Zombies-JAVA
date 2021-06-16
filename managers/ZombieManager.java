package managers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import logic.Level;

public class ZombieManager {
	// atributos
	private final Random seed;
	private final Level nivel;
	private int zombies;// cuantos zombies quedan por salir

	// constructor
	public ZombieManager(Level nivel, Random seed) {
		this.nivel = nivel;
		this.seed = seed;
		zombies = nivel.getNumZombies();

	}
	
	public void setRemZombies(int zm) {
		this.zombies=zm;
	}
	
	// decide si se genera un nuevo zombie o no. depende del nivel
	public int maybeNewZombie(int fila) {
		int y;
		double maybe;
		maybe = seed.nextDouble();
		if (maybe > nivel.getFrecuencia() || zombies == 0) {
			y = -1;
		} else {
			y = seed.nextInt(fila - 1);
		}
		return y;
	}

	public void removeZombie() {
		zombies--;
	}

	@Override
	public String toString() {
		return "Remaining zombies: " + zombies;

	}

	public String tipoZM() {
		String tipo = "";
		int num = seed.nextInt(3);// hay 3 tipos de zombies
		if (num == 0)
			tipo = "zombiecomun";
		else if (num == 1)
			tipo = "deportista";
		else
			tipo = "caracubo";

		return tipo;
	}

	public void save(BufferedWriter bw) throws IOException{
		bw.write("remZombies: "+zombies);
	}
}

