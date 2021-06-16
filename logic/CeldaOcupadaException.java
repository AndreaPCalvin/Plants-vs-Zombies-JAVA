package logic;

public class CeldaOcupadaException extends Exception {

	public CeldaOcupadaException() {
		super(); 
	}
	
	public CeldaOcupadaException(String string) {
		super(string);
	}
	
	public 	CeldaOcupadaException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CeldaOcupadaException(Throwable cause){ super(cause); }
	
	public CeldaOcupadaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
