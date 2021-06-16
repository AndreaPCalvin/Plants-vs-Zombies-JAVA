package pintar;

import logic.Game;

public abstract class BoardPrinter implements GamePrinter {
public int dimX;
public int dimY;
public String[][] tablero;
final String space = " ";

public BoardPrinter(){
	dimX=0;
	dimY=0;
	tablero=null;
}
	
	public abstract void encodeGame(Game game);
	//metodo de la interfaz
	public abstract String printGame(Game game);
	
	public String boardPrint(int cellSize) {
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
				
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		//
		StringBuilder str = new StringBuilder();
		str.append(lineDelimiter);
		
		for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(tablero[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
}
