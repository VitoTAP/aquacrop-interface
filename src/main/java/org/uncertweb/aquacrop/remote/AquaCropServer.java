package org.uncertweb.aquacrop.remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Richard Jones
 *
 */
public class AquaCropServer {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(AquaCropServer.class);
		if (args.length > 1) {			
			// get arguments
			int port = Integer.parseInt(args[0]);
			String basePath = args[1];
			String prefixCommand = null;
			if (args.length > 2) {
				prefixCommand = args[2];
			}
			String basePathOverride = null;
			if (args.length > 3) {
				basePathOverride = args[3];
			}
			logger.info("Starting server with base path '" + basePath + "' and " + (prefixCommand != null ? "prefix command '" + prefixCommand + "'." : "no prefix command."));
			if (basePathOverride != null) {
				logger.info("Using " + basePathOverride + " as path override.");
			}
			
			// all ok, run server
			ServerSocket serverSocket = null;

			try {
				serverSocket = new ServerSocket(port);
				logger.info("Listening on port " + port + "...");

				while (true) {
					try {
						Socket socket = serverSocket.accept();
						logger.info("Client " + socket.getRemoteSocketAddress() + " connected.");
						new AquaCropServerThread(socket, basePath, prefixCommand, basePathOverride).run();
					}
					catch (IOException e) {
						logger.error("Could not accept client connection: " + e.getMessage());
					}
				}
			}
			catch (IOException e) {
				logger.error("Could not listen on port " + port + ".");
				System.err.println("Could not listen on port " + port + ".");
			}
			finally {
				if (serverSocket != null) {
					try {
						serverSocket.close();
					}
					catch (IOException e) {
						logger.error("Could not close server socket on port " + port + ".");
					}
				}		
			}
		}
		else {
			AquaCropServer.printUsage();
		}
	}
	
	private static void printUsage() {
		System.out.println("Usage: aquacrop-runner PORT BASE [PREFIX]");
		System.out.println(" PORT the port you wish the server to listen on");
		System.out.println(" BASE the path to where AquaCrop and ACsaV31plus directories can be found");
		System.out.println(" PREFIX the prefix command for running the executables");
	}

}
