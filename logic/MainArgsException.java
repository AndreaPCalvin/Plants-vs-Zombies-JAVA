package logic;

public class MainArgsException extends Exception {
	public MainArgsException() {
		super(); 
	}
	
	public MainArgsException(String string) {
		super(string);
	}
	
	public 	MainArgsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MainArgsException(Throwable cause){ super(cause); }
	
	public MainArgsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
	super(message, cause, enableSuppression, writableStackTrace);
	}
}
