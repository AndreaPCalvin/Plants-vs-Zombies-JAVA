package Comandos;

public class ParseException extends Exception {

	public ParseException() {
		super(); 
	}
	
	public ParseException(String string) {
		super(string);
	}
	
	public 	ParseException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ParseException(Throwable cause){ super(cause); }
	
	public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}

}
