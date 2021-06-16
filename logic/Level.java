package logic;

public enum Level {
	//enum de niveles y sus estadisticas
	   EASY, HARD, INSANE;
	   
	   public int getNumZombies(){
		   int n;
		   if(this == EASY)
				n=3;
			else if (this == HARD)
				n=5;
			else
				n=10;
	     return n;
	   }
	   
	   public double getFrecuencia(){
		  double n;
			if(this == EASY)
				n=0.1;
			else if (this == HARD)
				n=0.2;
			else
				n=0.3;
	     return n;
	   }
	   public static Level parse(String inputString) { 
		   for (Level level : Level.values()) 
			   if (level .name().equalsIgnoreCase(inputString)) return level; 
		   return null; 
		   } 
	   
	   public static String all(String separator) { 
		   StringBuilder sb = new StringBuilder(); 
		   for (Level level : Level.values()) sb.append(level.name() + separator); 
		   String allLevels = sb.toString();
		   return allLevels.substring(0, allLevels.length()-separator.length());
		   }

}
