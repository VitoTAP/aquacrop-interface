package org.uncertweb.aquacrop.remote;

import org.uncertweb.aquacrop.AquaCropException;

public class AquaCropRemoteException extends AquaCropException {
	
	private static final long serialVersionUID = 5160651881014190220L;

	public AquaCropRemoteException() {
		super();
	}
	
	public AquaCropRemoteException(String message) {
		super(message);
	}
	
	public AquaCropRemoteException(Throwable cause) {
		super(cause);
	}
	
	public AquaCropRemoteException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
