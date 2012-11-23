package org.uncertweb.aquacrop;

import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.data.sample.SampleData;
import org.uncertweb.aquacrop.remote.AquaCropClient;
import org.uncertweb.aquacrop.remote.AquaCropServer;

public class AquaCrop {

	public static final String VERSION = "3.1"; // AquaCrop Version 3.1plus (January 2011)

	public static void main(String[] args) {
		// something quick for now
		if (args.length > 0) {
			if (args[0].equals("-t")) {
				String host = args[1];
				int port = Integer.parseInt(args[2]);
				
				System.out.println("Testing AquaCrop server at " + host + ":" + port);
				
				AquaCropClient client = new AquaCropClient(host, port);
				Project project = SampleData.getProject();
				
				try {
					System.out.print("Sending project... ");
					client.send(project);
					System.out.println("OK");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					project.getCropCharacteristics().setNumPlants(-1);
					System.out.print("Sending invalid project... ");
					client.send(project);
				}
				catch (AquaCropException e) {
					System.out.println("OK");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("All done!");
				
				System.exit(0);
			}
		}
		
		// else try start server
		AquaCropServer.main(args);
	}

}
