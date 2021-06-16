package objetos;

import java.io.BufferedWriter;
import java.io.IOException;

public class GameObjectList {

		private GameObject[] lista;
		private int contador;
		
		public GameObjectList(int max) {
			lista=new GameObject[max];
			contador=0;
		}
		
		public int getCont() {
			return this.contador;
		}
		//no hay objetos en la lista
		public boolean isEmpty() {
			return contador == 0;
		}
		//comprueba si hay algo en la casilla indicada
		public boolean posLibre(int x, int y) {
			boolean vacio=false;
			if(buscar(x, y) == -1) {
				vacio=true;
			}
			return vacio;
		}
		//añade un elemento a la lista
		public void insertar (GameObject elem) {
			lista[contador] = elem;
			contador++;
		}
		//desplaza elementos en la lista
		private void desplazar (int index) {
			int i=index;
			while( i < contador-1) {
				lista[i] = lista[i+1];
				i++;
			}
			lista[i]=null;
		}
		//elimina un objeto muerto de la lista
		public void eliminar(int x, int y) {
			int indice = buscar(x, y);
			desplazar(indice);
			contador--;
		}
		//busca la pos en la lista de un objeto segun sus coordenadas
		private int buscar(int x, int y) {
			int res=-1;
			if(contador>=0) {
			for(int i=0; i< contador && res==-1; i++) {
				if(lista[i].buscar(x, y)) {
					res=i;
				}
			}
			}
			return res;
		}
		
		public boolean recibirAtaque(int x, int y, int damage) {
			boolean muerta=false;
			int elem=buscar(x, y);
			
			if (elem !=-1) {//si hay algo
				muerta = lista[elem].recibirAtaque(damage);
				if(muerta) {
					eliminar(x, y);
				}
			}
			return muerta;
		}

		public void listaUpdate() {
			int i = 0;
			while (i < contador && lista[i] != null) {
				lista[i].update();
				i++;
			}
		}

		public String toString(int x, int y) {
			
				String mystring=" ";
				if (buscar(x, y) != -1) {
					mystring = lista[buscar(x, y)].toString();
				}
				return mystring;
		}

		public String[] toStringD() {
			String[] tab= new String[contador];
			int i=0;
			while(i<this.contador) {
				tab[i]=lista[i].toStringD();
				i++;
			}
			return tab;
		}

		public void save(BufferedWriter bw) throws IOException{
			
			for(int i=0;i<contador;i++) {
				lista[i].save(bw);
				if(i!=contador-1) {
					bw.write(", ");
				}
			}
			bw.write(System.lineSeparator());
		}
}
