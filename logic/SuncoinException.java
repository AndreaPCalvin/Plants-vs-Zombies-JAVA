package logic;

public class SuncoinException extends Exception {

	public SuncoinException() {
		super(); 
	}
	
	public SuncoinException(String string) {
		super(string);
	}
	
	public 	SuncoinException(String message, Throwable cause){
		super(message, cause);
	}
	
	public SuncoinException(Throwable cause){ super(cause); }
	
	public SuncoinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}

}
