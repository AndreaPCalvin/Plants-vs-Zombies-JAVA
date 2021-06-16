package zombies;

import logic.Game;

public class Caracubo extends Zombie{

	public Caracubo(int posX, int posY, Game game) {
		super(posX, posY, game);
		
		this.damage=1;
		this.resist=8;
		this.vidaMax=8;
		this.ciclosMax=3;
		this.frec=3;
		this.nombre="Caracubo";
		this.letra="w";
		this.ciclosRestantes=this.frec;
	}
		
	public Caracubo() {
		super();//es un zombie que no existe, solo sirve para lectura
	
		this.damage=1;
		this.resist=8;
		this.vidaMax=8;
		this.ciclosMax=3;
		this.frec=3;
		this.ciclosRestantes=this.frec;
		this.nombre="Caracubo";
		this.letra="w";
	}

	public Caracubo(int x, int y, Game game, int vida, int ciclo) {
		super(x, y, game);
		this.vidaMax=8;
		this.ciclosMax=3;
		this.damage=1;
		this.resist=vida;
		this.frec=3;
		this.nombre="Caracubo";
		this.letra="w";
		this.ciclosRestantes=ciclo;
	}

}
