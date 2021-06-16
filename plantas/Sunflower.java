package plantas;
import logic.Game;

public class Sunflower extends Plant {
	
	private static int soles;
	
	public Sunflower(int posX, int posY, Game game) {
		super(posX, posY, game);
		this.vidaMax=1;
		this.ciclosMax=1;
		this.resist=1;
		this.damage=0;
		this.frec=1;//genera cada 2 ciclos
		this.ciclosRestantes=this.frec;
		this.nombre="Sunflower";
		this.letra="s";
		coste=20;
		soles = 10;
	}
	
	public Sunflower() {
		super();//es una planta que no existe, solo sirve para lectura
		this.resist=1;
		this.damage=0;
		this.vidaMax=1;
		this.ciclosMax=1;
		this.frec=1;
		this.ciclosRestantes=this.frec;
		this.nombre="Sunflower";
		this.letra="s";
		coste=20;
		soles=0;
	}

	public Sunflower(int x, int y, Game game, int vida, int ciclo) {
		super(x, y, game);
		this.vidaMax=1;
		this.ciclosMax=1;
		this.resist=vida;
		this.damage=0;
		this.frec=1;//genera cada 2 ciclos
		this.ciclosRestantes=ciclo;
		this.nombre="Sunflower";
		this.letra="s";
		coste=20;
		soles = 10;
	}

	@Override
	//genera soles si ciclos restantes es 0. 10 soles cada 2 ciclos
	public void update () {
		if(this.ciclosRestantes == 0) {
			//Generar sol
			this.ciclosRestantes = this.frec;
			game.sumarSoles(soles);
		} else {
			this.ciclosRestantes--;
		}
		
	}
	
}
