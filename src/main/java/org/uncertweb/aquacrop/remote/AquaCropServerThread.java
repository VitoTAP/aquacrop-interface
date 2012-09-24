package org.uncertweb.aquacrop.remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.aquacrop.AquaCropException;
import org.uncertweb.aquacrop.AquaCropInterface;
import org.uncertweb.aquacrop.data.Output;
import org.uncertweb.aquacrop.data.Project;

public class AquaCropServerThread extends Thread {

	private final Logger logger = LoggerFactory.getLogger(AquaCropServerThread.class);
	private Socket socket;
	private String aquaCropBase;
	private String aquaCropPrefix;
	private String aquaCropBaseOverride;

	public AquaCropServerThread(Socket socket, String aquaCropBase, String aquaCropPrefix, String aquaCropBaseOverride) {
		super("AquaCropServerThread");
		this.socket = socket;
		this.aquaCropBase = aquaCropBase;
		this.aquaCropPrefix = aquaCropPrefix;
		this.aquaCropBaseOverride = aquaCropBaseOverride;
	}

	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		try {
			AquaCropInterface runner = new AquaCropInterface(aquaCropBase, aquaCropPrefix, aquaCropBaseOverride);

			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

			// parse object
			try {
				Project project = (Project) ois.readObject();
				logger.info("Received project" + (project.getTitle() != null ?  " '" + project.getTitle() + "' " : "") + ".");

				try {
					Output output = runner.run(project);					
					oos.writeObject(output);
					logger.info("Handled request successfully.");					
				}
				catch (AquaCropException e) {
					logger.error("Caught exception when running AquaCrop.", e);
					oos.writeObject(e);
				}
			}
			catch (ClassNotFoundException e) {
				logger.error("Couldn't find class for parsed object.", e);
				oos.writeObject(new AquaCropRemoteException("Couldn't read client object."));
			}
			catch (RuntimeException e) {
				// safety catch all
				// this is what will happen if any required inputs aren't set (e.g. dates)
				logger.error("Caught runtime exception during AquaCrop run stage.", e);				
				oos.writeObject(new AquaCropRemoteException("Error during AquaCrop run stage. Are all required inputs set?"));
			}
		}
		catch (IOException e) {
			logger.error("Couldn't handle input/output streams: " + e.getMessage());
		}
		finally {
			// close streams
			if (oos != null) {
				try {
					oos.close();
				}
				catch (IOException e) {	}
			}
			if (ois != null) {
				try {
					ois.close();
				}
				catch (IOException e) {	}
			}			
			
			// and socket
			try {
				socket.close();
			}
			catch (IOException e) {	}
		}
	}
}