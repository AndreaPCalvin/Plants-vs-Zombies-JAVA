package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import managers.*;
import objetos.GameObject;
import objetos.GameObjectList;
import pintar.DebugPrinter;
import pintar.GamePrinter;
import pintar.ReleasePrinter;
import plantas.*;
import zombies.*;

public class Game {
	private final String cabecera="Plants vs Zombies v3.0";
	
	public static final String wrongPreﬁxMsg = "unknown game attribute: " ;
	public static final String lineTooLongMsg = "too many words on line commencing: "; 
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	
	private static final String fallo = "Failed to add ";
	private static final String nocoins = ": not enough suncoins to buy it";
	private static final String ocupada = " is already occupied";
	private static final String outside = " is an invalid position";
	
	private static final String errordatos="can not load file: ";
	private static final String wrongciclos = "impossible number of game cycles";
	private static final String wrongsuncoins = "suncoins can not be a negative number";
	private static final String wronglevel = "the provided level does not exit";
	private static final String wrongremainingZM = "remainng zombies can not be less than zero";
	private static final String  wrongnumatrib = "wrong number of atributes for an object";

	private static final String notnum = "there is a char where a number should be";
	private static final String repeatedpos = "there are two objects in the same position";
	private static final String wrongdatos = "an objects atributte may not have sense";
	
	private final int columna = 8;
	private final int fila = 4;
	private final int MAX = columna * fila;
	private final int soles_iniciales = 50;
	
	private Level level;
	private Random seed;
	private long semilla;
	
	private GameObjectList listaplantas;
	private GameObjectList listazombies;
	
	private SuncoinManager m_suncoin;
	private ZombieManager m_zombie;
	private GamePrinter g_print;
	
	private int ciclos;
	private int deadZM;
	private boolean salir;
	
	public Game(Level level2, long sem, GamePrinter pintado) {
		this.semilla=sem;//la semilla
		this.seed = new Random(sem);//obj random dependiente de la semilla
		this.level = level2;
		setSalir(false);
		deadZM = 0;
		ciclos=0;
		listaplantas = new GameObjectList(MAX);
		listazombies=new GameObjectList(MAX);
		m_suncoin=new SuncoinManager(soles_iniciales);
		m_zombie=new ZombieManager(level, seed);
		this.g_print=pintado;//interfaz
	}
	
	public int getNumElem() {//numero de objetos en las listas
		return listaplantas.getCont()+listazombies.getCont();
	}
	
	public int getFila() {
		return this.fila;
	}
	public int getCol() {
		return this.columna;
	}
	public void setPrintMode(String tipo) {//cambia el modo de pintado
		if(tipo.equals("debug") || tipo.equals("d")) {
			this.g_print=new DebugPrinter();
		}
		else {
			this.g_print=new ReleasePrinter();
		}
    }
	
	public void draw () {
		System.out.println(g_print.printGame(this));
	}
	
	public String getCabecera() {
		return this.cabecera;
	}

	public void update() {
		// generador de zombies
		int zm_col = m_zombie.maybeNewZombie(fila);//fila del zombie, -1 si no se genera nuevo zombie
		if (zm_col != -1 && listaplantas.posLibre(zm_col, columna - 1) && listazombies.posLibre(zm_col, columna - 1)) {
			// si se genera zombie, y hay sitio para el
			//mirar tipo zombie: caracubo, comun o deportista
			String name = m_zombie.tipoZM();
			Zombie miZombie= ZombieFactory.getZombie(name, zm_col, columna-1, this);
			listazombies.insertar(miZombie);
			m_zombie.removeZombie();
		}

		// fase 1: update de plantas
		listaplantas.listaUpdate();
		// fase 2: los zombies avanzan y luego atacan
		listazombies.listaUpdate();

		ciclos++;
	}

	//vuelve a la situacion inicial de juego
	public void reset() {
		this.ciclos = 0;
		// crear listas
		listaplantas=new GameObjectList(MAX);
		listazombies=new GameObjectList(MAX);
		// crear manager
		this.m_suncoin = new SuncoinManager(soles_iniciales);// empiezas con 50 soles
		this.m_zombie = new ZombieManager(this.level, this.seed);
	}

	public void addPlantToGame(String name, int x, int y) throws CeldaOcupadaException, SuncoinException, OutGridException {
		
		if (x < fila && y < columna) {//la planta esta en las dim del tablero
			if (listaplantas.posLibre(x, y) && listazombies.posLibre(x, y)) {//hay hueco
				Plant miplanta = PlantFactory.getPlant(name, x, y, this);

				int monedas = miplanta.getCoste();
				// comprobar si hay monedas
				if (m_suncoin.maybeBuy(monedas)) {
					listaplantas.insertar(miplanta);
				} else {
					throw new SuncoinException(fallo + name + nocoins);
				}

			} else {
				//celda ocupada
				throw new CeldaOcupadaException(fallo + name + ": position (" + y + "," + x + ")" + ocupada);
			}
		}
		else {
			//celda fuera del tablero
			throw new OutGridException(fallo + name + ": (" + y + "," + x + ")" + outside);
		}
	}
	
	public boolean isFinished() {
		boolean terminar;
		// todos los zombies muertos o has perdido
		if (this.deadZM >= level.getNumZombies() || perder()) {
			terminar = true;
		} else {
			terminar = false;
		}
		return terminar;
	}
	// comprueba si has perdido, mira si el zombie ha llegado al fin del tablero
		public boolean perder() {
			boolean perder = false;
			int i = 0;
			while (i < fila && !listazombies.isEmpty() && !perder) {
				perder = (!listazombies.posLibre(i, 0));
				i++;
			}
			return perder;
		}
	
	//acumula los soles que generan las suncoins
	public void sumarSoles(int soles) {
		m_suncoin.acumular(soles);
	}

	//la planta hace daño
	public boolean PlantaAttack(int damage, int posX, int posY) {
		boolean encontrado = false;
		if (!listazombies.posLibre(posY, posX)) {//hay 1 zombie
			encontrado=true;
			if (listazombies.recibirAtaque(posY, posX, damage)) {
				this.deadZM++;//el zm ha muerto
			}
		}
		return encontrado;
	}
	//comprueba que haya hueco libre en la pos anterior
	public boolean casillaAnterior(int x, int y) {
		boolean libre = false;
		if (listazombies.posLibre(x, y) && listaplantas.posLibre(x, y)) {
			libre = true;
		}
		return libre;
	}

	public void ZMattack(int x, int y, int damage) {
		listaplantas.recibirAtaque(x, y, damage);
	}
	
//pintar tablero
	public String casillaRelease(int x, int y) {
		String mystring=" ";	
		if (!listazombies.posLibre(x, y)) {
			mystring=listazombies.toString(x,y);
		}
		else if(!listaplantas.posLibre(x, y)) {
			mystring=listaplantas.toString(x, y);
		}
		return mystring;
	}

	public String datosR() {
		String cadena="Number of cycles: " + ciclos +"\n" + m_suncoin +"\n" + m_zombie;
		return cadena;
	}
	
	public Object datosD() {
		String cadena="Number of cycles: " + ciclos + "\n" + m_suncoin + "\n" + m_zombie
				+ "\nLevel: " + this.level + "\nSeed: " + this.semilla;
		return cadena;
	}

	public String[] casillaDebug() {//devuelve un array de strings con las plantas y zombies
		int s1=listaplantas.getCont();
		int s2=listazombies.getCont();
		String[] str=new String[s1+s2];
			
		String[] aux1 = listaplantas.toStringD();
		String[] aux2 = listazombies.toStringD();
		
		for(int i=0;i<s1;i++) {
			str[i]=aux1[i];
		}
		int x=0;
		for(int j=s1;j<s1+s2;j++) {
			str[j]=aux2[x];
			x++;
		}
		
		return str;
	}
	
	public void autokill(int posX, int posY) {//la planta se mata a si misma: petacereza
		listaplantas.eliminar(posX, posY);
	}

	public boolean isSalir() {
		return salir;
	}

	public void setSalir(boolean salir) {
		this.salir = salir;
	}

	public void save(BufferedWriter bw) throws IOException{
		try{
			//revisar excepciones File
			bw.write("cycle: "+ciclos);
			bw.write(System.lineSeparator());
			m_suncoin.save(bw);
			bw.write(System.lineSeparator());
			bw.write("level: "+this.level.name());
			bw.write(System.lineSeparator());
			m_zombie.save(bw);
			bw.write(System.lineSeparator());
			bw.write("plantList: ");
			listaplantas.save(bw);//tienen separator
			bw.write("zombieList: ");
			listazombies.save(bw);
			//le pasas el buffer a la lista y escribe ella los elementos
		}
		catch(IOException e) {
			throw new IOException("Error al intentar escribir en el archivo.");
		}
	}

	
	private GameObject loadZombie(String[] atributos) throws FileContentsException {
		Zombie mizm=null;
		try {
		if(atributos.length==5) {
			String symbol = atributos[0];//el nombre se comprueba al intentar cargar la planta
			int lr = Integer.parseInt(atributos[1]);//vida
			int x = Integer.parseInt(atributos[2]);//fila
			int y = Integer.parseInt(atributos[3]);//col
			int t = Integer.parseInt(atributos[4]);//ciclos
		
			mizm = ZombieFactory.cargarZombie(symbol, lr, y, x, t, this);
			if(mizm==null) {
				throw new FileContentsException("no se pudieron cargar los zombies");
			}
			else {
				if(!mizm.comprobarDatos()) {
					throw new FileContentsException(errordatos+wrongdatos);
				}
			}
		}
		else {
			throw new FileContentsException(errordatos+ wrongnumatrib);
		}}
		catch(NumberFormatException e) {
			throw new FileContentsException(errordatos+ notnum);
		}
		return mizm;
	}

	private GameObject loadPlant(String[] atributos) throws FileContentsException {
		Plant miplanta=null;
		try {
		//symbol:lr:x:y:t
		if(atributos.length==5) {
			String symbol = atributos[0];//el nombre se comprueba al intentar cargar la planta
			int lr = Integer.parseInt(atributos[1]);//vida
			int x = Integer.parseInt(atributos[2]);//fila
			int y = Integer.parseInt(atributos[3]);//col			
			int t = Integer.parseInt(atributos[4]);//ciclos

			//comprueba que el tipo de planta existe
			miplanta = PlantFactory.cargarPlanta(symbol, lr, y, x, t, this);
			if(miplanta==null) {
				throw new FileContentsException("no se pudieron cargar las plantas");
			}
			else {
				if(!miplanta.comprobarDatos()) {
					//los datos son erroneos
					
					throw new FileContentsException(errordatos+ wrongdatos);
				}
			}
		}
		else {
			throw new FileContentsException(errordatos+ wrongnumatrib);
		}
		
		}
		catch(NumberFormatException e) {
			throw new FileContentsException(errordatos+ notnum);
		}
		return miplanta;
	}
	
	
	public String[] loadLine(BufferedReader inStream, String preﬁx, boolean isList) throws IOException, FileContentsException{ 
		String line = inStream.readLine().trim(); // absence of the preﬁx is invalid 
		if ( !line.startsWith(preﬁx + ":") ) 
			throw new FileContentsException(wrongPreﬁxMsg + preﬁx); 
	// cut the preﬁx and the following colon oﬀ the line 
	// then trim it to get the attribute contents 
		String contentString = line.substring(preﬁx.length()+1).trim(); 
		String[] words; // the attribute contents are not empty 
		
		if (!contentString.equals("")) {
				if (!isList ) { // split non−list attribute contents into words 
					// using 1−or−more−white−spaces as separator 
					words = contentString.split("\\s+"); // a non−list attribute with contents of more than one word is invalid 
					if (words.length != 1) 
						throw new FileContentsException(lineTooLongMsg + preﬁx); } 
					else // split list attribute contents into words // using comma+0−or−more−white−spaces as separator 
						words = contentString.split(",\\s*"); // the attribute contents are empty 
				} 
		else { // a non−list attribute with empty contents is invalid 
			if (!isList ) throw new FileContentsException(lineTooShortMsg + preﬁx); 
			// a list attibute with empty contents is valid; // use a zero−length array to store its words 
			words = new String[0]; 
		} 
		return words; 
	}
	
	public void load(BufferedReader br) throws IOException, FileContentsException {
		try {
			int cicloaux = 0;
			int sunconaux = 0;
			Level levelaux = null;
			int remainingzombiesaux = 0;
			GameObjectList listaplantaaux = null;
			GameObjectList listazombieaux = null;
			int[] posiciones=new int[MAX*2];
			int poscont=0;
			//disponible para plantas y zombies
			
			for (int i = 0; i < 6; i++) {
				
				if (i == 0) {
					String[] aux=loadLine(br, "cycle" ,false);
					cicloaux = Integer.parseInt(aux[0]);
					if(cicloaux<0) {
						//suponemos que se puede cargar una situacion inicial 
						throw new FileContentsException(errordatos+wrongciclos);
					}
				}
				else if (i == 1) {
					String[] aux=loadLine(br, "sunCoins" ,false);
					sunconaux = Integer.parseInt(aux[0]);
					if(sunconaux<0) {
						throw new FileContentsException(errordatos+wrongsuncoins);
					}
				}
				else if (i == 2) {
					String[] aux=loadLine(br, "level" ,false);
					levelaux=Level.parse(aux[0]);
					if(levelaux==null) {//no existe
						throw new FileContentsException(errordatos+wronglevel);
					}
					
				} else if (i == 3) {
					String[] aux=loadLine(br, "remZombies" ,false);
					remainingzombiesaux = Integer.parseInt(aux[0]);
					if(remainingzombiesaux<0) {
						throw new FileContentsException(errordatos+wrongremainingZM);
					}
				} else {
					
					if (i == 4) {
						listaplantaaux = new GameObjectList(MAX);
						String[] aux=loadLine(br, "plantList" , true);
						if(aux.length > 0) {
						
							for (String s : aux) {
								String[] datos=s.split(":");
								int x = Integer.parseInt(datos[2]);//fila
								int y = Integer.parseInt(datos[3]);//col
								
								posiciones[poscont]=x;
								poscont++;
								posiciones[poscont]=y;
								poscont++;
								
								if (loadposLibre(posiciones, poscont, x, y)) {
								listaplantaaux.insertar(loadPlant(datos));
								}
								else {
									throw new FileContentsException( errordatos+repeatedpos);
								}
							}
						}
					} else {
						listazombieaux = new GameObjectList(MAX);
						String[] aux=loadLine(br, "zombieList" , true);
						if(aux.length > 0) {
							for (String s : aux) {
								String[] datos=s.split(":");
								int x = Integer.parseInt(datos[2]);//fila
								int y = Integer.parseInt(datos[3]);//col
								
								posiciones[poscont]=x;
								poscont++;
								posiciones[poscont]=y;
								poscont++;
								
								if (loadposLibre(posiciones, poscont, x, y)) {
								listazombieaux.insertar(loadZombie(s.split(":")));
								}
								else {
									throw new FileContentsException( errordatos+repeatedpos);
								}
							}
						}
					}

				}
			}
			//No hay error. Volcamos las listas de verdad
			listaplantas = listaplantaaux;
			listazombies = listazombieaux;
			ciclos = cicloaux;
			m_suncoin.setCoins(sunconaux);
			level=levelaux;
			m_zombie.setRemZombies(remainingzombiesaux );
			
			
			
		} catch (IOException e) {
			throw new IOException("No se puede leer el fichero");
		}
		catch(FileContentsException e) {
			throw new FileContentsException(e);
		}
	}

	private boolean loadposLibre(int[] posiciones, int poscont, int x, int y) {
		boolean libre=true;
		
		for(int i=0;i<poscont-2;i=i+2) {
			if(posiciones[i]==x && posiciones[i+1]==y) {
				libre=false;
			}
		}
		
		return libre;
	}

		
}
