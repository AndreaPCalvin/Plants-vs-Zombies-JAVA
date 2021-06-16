package zombies;

import logic.Game;

public class ZombieComun extends Zombie {

	public ZombieComun(int posX, int posY, Game game) {
		super(posX, posY, game);
		this.vidaMax=5;
		this.ciclosMax=1;
		this.damage=1;
		this.resist=5;
		this.frec=1;
		this.nombre="ZombieComun";
		this.letra="z";
		this.ciclosRestantes=this.frec;
	}
		
	public ZombieComun() {
		super();//es una planta que no existe, solo sirve para lectura
	
		this.damage=1;
		this.resist=5;
		this.vidaMax=5;
		this.ciclosMax=1;
		this.frec=1;
		this.ciclosRestantes=this.frec;
		this.nombre="ZombieComun";
		this.letra="z";
	}

	public ZombieComun(int x, int y, Game game, int lr, int t) {
		super(x, y, game);
		this.vidaMax=5;
		this.ciclosMax=1;
		this.damage=1;
		this.resist=lr;
		this.frec=1;
		this.nombre="ZombieComun";
		this.letra="z";
		this.ciclosRestantes=t;
	}
}
