package zombies;

import logic.Game;

public class Deportista extends Zombie {
	
	public Deportista(int posX, int posY, Game game) {
		super(posX, posY, game);
		
		this.damage=1;
		this.resist=2;
		this.vidaMax=2;
		this.ciclosMax=0;
		this.frec=0;
		this.nombre="Deportista";
		this.letra="x";
		this.ciclosRestantes=this.frec;
	}
		
	public Deportista() {
		super();//es una planta que no existe, solo sirve para lectura
		this.vidaMax=2;
		this.ciclosMax=0;
		this.damage=1;
		this.resist=2;
		this.frec=0;
		this.ciclosRestantes=this.frec;
		this.nombre="Deportista";
		this.letra="x";
	}

	public Deportista(int x, int y, Game game, int lr, int t) {
		super(x, y, game);
		this.vidaMax=2;
		this.ciclosMax=0;
		this.damage=1;
		this.resist=lr;
		this.frec=0;
		this.nombre="Deportista";
		this.letra="x";
		this.ciclosRestantes=t;
	}
}
