package objetos;

import java.io.BufferedWriter;
import java.io.IOException;

import logic.Game;

public abstract class GameObject {
	
	protected int ciclosRestantes;
	protected int damage;
	protected int resist;
	protected int frec;
	protected int posX;
	protected int posY;
	protected String nombre;
	protected String letra;
	protected Game game;
	protected int vidaMax;
	protected int ciclosMax;
	
	public GameObject(int posX, int posY, Game game) {
		this.posX = posX;
		this.posY = posY;
		this.game=game;
	}
	
	@Override
	public String toString() {
		String cadena;
		cadena= this.letra.toUpperCase()+"["+this.resist+"]";
		return cadena;
	}
	public String toStringD() {
		return this.letra.toUpperCase() + "[l:" + this.resist + ",x:" + this.posY + ",y:" 
				+ this.posX + ",t:" + this.ciclosRestantes + "]";
	}

	//restar damage puntos de vida a un objeto	
	private int quitarVida (int damage) {
			this.resist-=damage;
			return this.resist;
		}
			
	
	public boolean recibirAtaque(int dam) {
		boolean dead = false;
		quitarVida(dam);
		if (this.resist <= 0) {// planta o zombie muerta
			dead = true;
		}
		return dead;
	}

	public abstract void update();
	
	//dice si hay un objecto en la posicion indicada
	public boolean buscar(int x, int y) {
		boolean check=false;
		if(this.posX==x && this.posY==y) {
			check=true;
		}
		return check;
	}

	public void save(BufferedWriter bw) throws IOException{
		String ret = letra+":"+resist+":"+posY+":"+posX+":"+ciclosRestantes;
		bw.write(ret);
	}
	
	public boolean comprobarDatos() {
		boolean ok=true;
		
		if(resist<=0 || resist>vidaMax) {//vida entre 1 y x
			ok=false;
		}
		//ciclos
		else if(ciclosRestantes<0 || ciclosRestantes>ciclosMax) {//ciclos entre 0 y x
			ok=false;
		}
		else if(posY>=game.getCol() || posX>= game.getFila() ||posX<0 || posY<0) {//casillas en el tablero
			ok=false;
		}
		
		return ok;
	}
}
