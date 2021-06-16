package pintar;

import logic.Game;

public class DebugPrinter extends BoardPrinter{
	
	public DebugPrinter() {
		super();
	}
	
	@Override
	public String printGame(Game game) {
		int cellSize=20;
		StringBuilder str = new StringBuilder();
		this.dimY=game.getNumElem();
		this.dimX=1;
		encodeGame(game);
		str.append(game.datosD());//monedas, ciclos y zombies restantes. Semilla y nivel
		str.append(boardPrint(cellSize));
		return str.toString();
	}
	

	@Override
	public void encodeGame(Game game) {

		tablero = new String[dimX][dimY];
		String[] casillas= new String[game.getNumElem()];
		casillas = game.casillaDebug();
		for(int i=0;i<dimY; i++){
				tablero[0][i]=casillas[i];
		}
	}	
}