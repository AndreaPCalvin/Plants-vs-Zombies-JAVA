package pintar;

import logic.Game;

public class ReleasePrinter extends BoardPrinter{
//pintado pract1
	
	public ReleasePrinter() {
		super();
	}
	
	public String printGame(Game game) {
		int cellSize=8;
		StringBuilder str = new StringBuilder();
		this.dimX= game.getFila();
		this.dimY= game.getCol();
		encodeGame(game);
		str.append(game.datosR());//monedas, ciclos y zombies restantes
		str.append(boardPrint(cellSize));
		return str.toString();
}

	public void encodeGame(Game game) {
		tablero = new String[dimX][dimY];
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {

				tablero[i][j] =  game.casillaRelease(i,j);
							
			}
		}
	}
}
