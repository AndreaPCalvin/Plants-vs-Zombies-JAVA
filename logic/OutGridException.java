package logic;

public class OutGridException extends Exception {

	public OutGridException() {
		super(); 
	}
	
	public OutGridException(String string) {
		super(string);
	}
	
	public 	OutGridException(String message, Throwable cause){
		super(message, cause);
	}
	
	public OutGridException(Throwable cause){ super(cause); }
	
	public OutGridException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}

}
