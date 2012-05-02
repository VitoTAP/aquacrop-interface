package org.uncertweb.aquacrop.remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.uncertweb.aquacrop.AquaCropException;
import org.uncertweb.aquacrop.data.Output;
import org.uncertweb.aquacrop.data.Project;

/**
 * serialize project java object
 * send to host:port
 * deserialize returned output java object 
 * 
 * @author Richard Jones
 *
 */
public class AquaCropClient {

	private String host;
	private int port;

	public AquaCropClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Output send(Project project) throws IOException, AquaCropException, AquaCropRemoteException {
		Socket socket = new Socket(host, port);

		// get streams
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		// send project
		oos.writeObject(project);

		try {
			// get response (output or exception)
			Object response = ois.readObject();

			if (response instanceof Output) {
				return (Output) response;
			}
			else if (response instanceof AquaCropException) {
				throw (AquaCropException) response;
			}
			else {
				throw (AquaCropRemoteException) response;
			}
		}
		catch (ClassNotFoundException e) {
			throw new AquaCropRemoteException("Couldn't read response from server.");
		}
	}

}
