package plantas;

import logic.Game;

public class Nuez extends Plant {	
	
	public Nuez(int posX, int posY, Game game) {
		super(posX, posY, game);
		
		this.damage=0;
		this.resist=10;
		this.vidaMax=10;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=this.frec;
		this.nombre="Nuez";
		this.letra="n";
		coste=50;
	}
		
	public Nuez() {
		super();//es una planta que no existe, solo sirve para lectura
		this.damage=0;
		this.resist=10;
		this.vidaMax=10;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=this.frec;
		this.nombre="Nuez";
		this.letra="n";
		coste=50;
	}

	public Nuez(int x, int y, Game game, int vida, int ciclo) {
		super(x, y, game);
		
		this.damage=0;
		this.resist=vida;
		this.vidaMax=10;
		this.ciclosMax=0;
		this.frec=0;
		this.ciclosRestantes=ciclo;
		this.nombre="Nuez";
		this.letra="n";
		coste=50;
	}

	@Override
	public void update () {
		//esta planta no hace nada
	}
}
