package logic;

public class WrongLevelException extends Exception {
	public WrongLevelException() {
		super(); 
	}
	
	public WrongLevelException(String string) {
		super(string);
	}
	
	public 	WrongLevelException(String message, Throwable cause){
		super(message, cause);
	}
	
	public WrongLevelException(Throwable cause){ super(cause); }
	
	public WrongLevelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}
}
