package org.uncertweb.aquacrop;

public class AquaCropException extends Exception {

	private static final long serialVersionUID = 1L;

	public AquaCropException() {
		super();
	}
	
	public AquaCropException(String message) {
		super(message);
	}
	
	public AquaCropException(Throwable cause) {
		super(cause);
	}
	
}
