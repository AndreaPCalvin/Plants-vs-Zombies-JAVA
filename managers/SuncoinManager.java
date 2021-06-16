package managers;

import java.io.BufferedWriter;
import java.io.IOException;

public class SuncoinManager {
	//controla los suncoins que tiene el jugador. 
	//Las añade con los soles y las gasta al comprar plantas
   private int coins;
   
   
   public SuncoinManager(int coins){
	   this.coins=coins;
   }
   
   //metodos
  	
	public void setCoins(int new_coins){
		coins=new_coins;
	}
	//comprueba si tiene dinero para comprar plantas y en caso afrimativo, las gasta
	public boolean gastar(int precio){
		boolean check=true;
		if(coins>=precio){
			coins-=precio;
		}
		else{
			check=false;
		}
		return check;
	}
	//acumula los suncoins generados por los soles
	public int acumular(int soles){
		coins+=soles;
		return coins;
	}
	
	@Override
	public String toString() {
		return "Suncoins: " + coins;
	}

	public boolean maybeBuy(int coste) {
		boolean check=false;
		if(coins >= coste) {
			coins -= coste;
			check=true;
		}
		return check;
	}

	public void save(BufferedWriter bw) throws IOException{	
		bw.write("sunCoins: "+coins);
	}
}