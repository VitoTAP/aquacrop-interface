package org.uncertweb.aquacrop.remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Richard Jones
 *
 */
public class AquaCropServer {

	private static final Logger logger = LoggerFactory.getLogger(AquaCropServer.class);

	private int port;
	private String basePath;
	private String prefixCommand;
	private String basePathOverride;
	private boolean keepFiles;
	private ServerSocket serverSocket;

	public AquaCropServer(int port, String basePath, boolean keepFiles) {
		this(port, basePath, null, null, keepFiles);
	}

	public AquaCropServer(int port, String basePath, String prefixCommand, String basePathOverride, boolean keepFiles) {
		this.port = port;
		this.basePath = basePath;
		this.prefixCommand = prefixCommand;
		this.basePathOverride = basePathOverride;
		this.keepFiles = keepFiles;
	}

	public void start() throws IOException {
		// start socket		
		serverSocket =  new ServerSocket(port);
		logger.info("Listening on port " + port + "...");

		// request loop
		ExecutorService pool = Executors.newFixedThreadPool(4);
		while (true && !serverSocket.isClosed()) {
			try {
				Socket clientSocket = serverSocket.accept();
				logger.info("Client " + clientSocket.getRemoteSocketAddress() + " connected.");
				pool.execute(new AquaCropServerThread(clientSocket, basePath, prefixCommand, basePathOverride, keepFiles));
			}
			catch (IOException e) {
				if (!serverSocket.isClosed()) {
					logger.error("Could not accept client connection: " + e.getMessage());
				}
			}
		}
	}

	public void stop() throws IOException {
		if (serverSocket != null) {
			logger.info("Stopped listening on port " + port + ".");
			serverSocket.close();
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// create command line options
		Options options = new Options();

		// create options
		Option portOpt = OptionBuilder.withArgName("port")
				.isRequired()
				.hasArg()
				.withDescription("the port for the server to listen on")
				.create("port");
		Option pathOpt = OptionBuilder.withArgName("path")
				.isRequired()
				.hasArg()
				.withDescription("the path to where AquaCrop and ACsaV31plus directories can be found")
				.create("path");
		Option pathOverrideOpt = OptionBuilder.withArgName("override")
				.hasArg()
				.withDescription("the path where the executable should look for the files")
				.create("override");
		Option prefixOpt = OptionBuilder.withArgName("prefix")
				.hasArg()
				.withDescription("the prefix command for running the executable")
				.create("prefix");
		Option keepFilesOpt = new Option("keep", "keep generated files after run");

		// add them
		options.addOption(portOpt);
		options.addOption(pathOpt);
		options.addOption(pathOverrideOpt);
		options.addOption(prefixOpt);
		options.addOption(keepFilesOpt);

		// create the parser
		CommandLineParser parser = new GnuParser();
		CommandLine line = null;
		try {
			line = parser.parse(options, args);
		}
		catch (ParseException exp) {
			System.err.println("Couldn't start AquaCrop server. " + exp.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("aquacrop-interface", options, true);
			System.exit(0);
		}

		// get args
		int port = Integer.parseInt(line.getOptionValue("port"));
		String path = line.getOptionValue("path");
		String override = line.getOptionValue("override");
		String prefix = line.getOptionValue("prefix");
		boolean keepFiles = line.hasOption("keep");

		logger.info("Starting server with base path '" + path + "' and " + (prefix != null ? "prefix command '" + prefix + "'." : "no prefix command."));
		logger.info((keepFiles ? "Keeping" : "Removing") + " project files after AquaCrop runs.");
		if (override != null) {
			logger.info("Using " + override + " as path override.");
		}

		// create and start		
		final AquaCropServer server = new AquaCropServer(port, path, prefix, override, keepFiles);

		// hook to shutdown gracefully
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					server.stop();
				}
				catch (IOException e) {
					logger.warn("Couldn't shutdown server gracefully, sockets may still be open.");
				}
			}
		});

		try {
			// start
			server.start();
		}
		catch (IOException e) {
			System.err.println("Could not listen on port " + port + ", is it already in use?");
		}
	}

}
