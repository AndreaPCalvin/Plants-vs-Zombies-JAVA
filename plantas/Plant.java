package plantas;

import logic.Game;
import objetos.GameObject;

public abstract class Plant extends GameObject {
	protected int coste;
	
	public Plant(int posX, int posY, Game game) {
		super(posX, posY, game);
	}
	public Plant() {
		super(-1,-1,null);
	}
	
	//crear outprint de las plantas en tablero
	
	//outprint de los datos de las plantas
	public String datos() {
		return this.nombre + " : " + "Coste : " + coste + " Ataque : " + this.damage;
	}
	
	//comprueba si existe una planta a partir de su nombre
	public boolean existe(String n) {
		boolean exist=false;
		
		if(this.nombre.toLowerCase().equals(n.toLowerCase()) || 
				n.toLowerCase().equals(letra.toLowerCase())) { 
			exist=true;
		}
		return exist;
	}
	
	
	public int getCoste() {
		return coste;
	}
	
	
}
