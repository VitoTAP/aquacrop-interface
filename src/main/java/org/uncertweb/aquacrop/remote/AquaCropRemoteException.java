package org.uncertweb.aquacrop.remote;

import org.uncertweb.aquacrop.AquaCropException;

public class AquaCropRemoteException extends AquaCropException {
	
	private static final long serialVersionUID = 3412044012844640236L;

	public AquaCropRemoteException() {
		super();
	}
	
	public AquaCropRemoteException(String message) {
		super(message);
	}
	
	public AquaCropRemoteException(Throwable cause) {
		super(cause);
	}
	
}
