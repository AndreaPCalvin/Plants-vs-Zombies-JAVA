package plantas;

import logic.Game;

public class Petacereza extends Plant {
		
	public Petacereza(int posX, int posY, Game game) {
		super(posX, posY, game);
		this.resist=2;
		this.vidaMax=2;
		this.ciclosMax=2;
		this.damage=10;
		this.frec=2;
		this.ciclosRestantes=this.frec;
		this.nombre="Petacereza";
		this.letra="c";
		coste=50;
	}

	public Petacereza() {
		super();//es una planta que no existe, solo sirve para lectura
		this.resist=2;
		this.vidaMax=2;
		this.ciclosMax=2;
		this.damage=10;
		this.frec=2;
		this.ciclosRestantes=this.frec;
		this.nombre="Petacereza";
		this.letra="c";
		coste=50;
	}

	public Petacereza(int x, int y, Game game, int vida, int ciclo) {
		super(x, y, game);
		
		this.damage=10;
		this.vidaMax=2;
		this.ciclosMax=2;
		this.resist=vida;
		this.frec=2;
		this.ciclosRestantes=ciclo;
		this.nombre="Petacereza";
		this.letra="c";
		coste=50;
	}

	@Override
	public void update() {
		if(this.ciclosRestantes == 0) {
			boolean a;
			for(int i=posX-1; i <= posX+1;i++) {//todos los zombies de su alrededor mueren, las plantas no
				for(int j=posY-1; j<= posY+1; j++) {
					a=game.PlantaAttack(this.damage, j, i);
				}
			}
			this.resist = 0;
			game.autokill(posX, posY);//muere sin ser atacada por un zombie
		} else {
			this.ciclosRestantes--;
		}
		
	}

}
