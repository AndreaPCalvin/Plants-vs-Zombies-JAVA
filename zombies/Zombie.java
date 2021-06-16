package zombies;

import logic.Game;
import objetos.GameObject;

public abstract class Zombie extends GameObject{
	public Zombie(int posX, int posY, Game game) {
		super(posX, posY, game);
	}
	public Zombie() {
		super(-1,-1,null);
	}
	
	public String datos() {
		return this.nombre + " : " + "Speed : " + this.frec + " Harm : " + this.damage + " Life : " + this.resist;
	}
	
	public void update() {
		//tiene dos partes. avanzar y atacar
		if (game.casillaAnterior(posX, posY-1)) {//objeto en pos anterior
			if (ciclosRestantes == 0) {
				this.posY --;
				this.ciclosRestantes=this.frec;
			} 
			else {
				this.ciclosRestantes--;
			}
		}
	 //ataque zombie
	 game.ZMattack(this.posX, this.posY-1, this.damage);
	}
}
