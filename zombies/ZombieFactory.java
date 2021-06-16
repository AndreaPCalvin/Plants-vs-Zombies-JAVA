package zombies;

import logic.Game;

public class ZombieFactory {
	private static Zombie[] availableZombies = { 
			new Caracubo(), 
			new Deportista(), 
			new ZombieComun() 
	};

	public static Zombie getZombie(String zombieName, int x, int y, Game game) {
		Zombie p = null;
		zombieName.toLowerCase();
		if (zombieName.equals("caracubo") || zombieName.equals("w")) {
			p = new Caracubo(x, y, game);
		} else if (zombieName.equals("deportista") || zombieName.equals("x")) {
			p = new Deportista(x, y, game);
		} else if(zombieName.equals("zombiecomun") || zombieName.equals("z")){
			p = new ZombieComun(x, y, game);
		}
		return p;
	}

	public static String listOfAvilableZombies() {
		String s = "";
		for (Zombie z : availableZombies) {
			s+= z.datos() + System.lineSeparator();
		}
		return s;
	}

	public static Zombie cargarZombie(String zombieName, int lr, int x, int y, int t, Game game) {
		
		Zombie p = null;
		zombieName.toLowerCase();
		if (zombieName.equals("caracubo") || zombieName.equals("w")) {
			p = new Caracubo(x, y, game, lr, t);
		} else if (zombieName.equals("deportista") || zombieName.equals("x")) {
			p = new Deportista(x, y, game, lr, t);
		} else if(zombieName.equals("zombiecomun") || zombieName.equals("z")){
			p = new ZombieComun(x, y, game, lr, t);
		}
		return p;
		
	}
}
