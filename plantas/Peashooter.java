package plantas;

import logic.Game;

public class Peashooter extends Plant {
	
	public Peashooter(int posX, int posY, Game game) {
		super(posX, posY, game);
		this.damage=1;
		this.resist=3;
		this.vidaMax=3;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=this.frec;
		this.nombre="Peashooter";
		this.letra="p";
		coste=50;
	}
		
	public Peashooter() {
		super();//es una planta que no existe, solo sirve para lectura
		this.damage=1;
		this.resist=3;
		this.vidaMax=3;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=this.frec;
		this.nombre="Peashooter";
		this.letra="p";
		coste=50;
	}

	public Peashooter(int x, int y, Game game, int vida, int ciclo) {
		super(x, y, game);
		this.damage=1;
		this.resist=vida;
		this.vidaMax=3;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=ciclo;
		this.nombre="Peashooter";
		this.letra="p";
		coste=50;
	}

	@Override
	public void update () {
		//atacar
		boolean encontrado = false;
		int i = posY;
		while (i < game.getCol() && !encontrado) {//recorre el tablero en horizotal
			encontrado=game.PlantaAttack(this.damage, i, posX);

			i++;
		}
		
	}

}
