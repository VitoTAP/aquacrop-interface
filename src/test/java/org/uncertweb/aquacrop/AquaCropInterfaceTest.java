package org.uncertweb.aquacrop;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.uncertweb.aquacrop.data.Output;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.test.TestData;
import org.uncertweb.aquacrop.test.TestEnvironment;

public class AquaCropInterfaceTest {

	private AquaCropInterface iface;
	
	@Before
	public void before() {
		TestEnvironment env = TestEnvironment.getInstance();
		iface = new AquaCropInterface(env.getBasePath(), env.getPrefixCommand(), env.getBasePathOverride());
	}
	
	@Test
	public void runReturnsOutput() throws AquaCropException {
		Project project = TestData.getProject();
		Output output = iface.run(project);
		Assert.assertNotNull(output);
	}

//	
//	public void remoteSend() throws AquaCropException, AquaCropRemoteException, IOException, InterruptedException {
//		// normal send
//		Output output = runProjectRemotely();
//		assertNotNull(output);
//	}
//	
//	public void remoteStress() throws AquaCropException, AquaCropRemoteException, IOException, InterruptedException {
//		for (int i = 0; i < 2000; i++) {
//			System.out.println(i + 1 + ": " + runProjectRemotely().toString());
//			Thread.sleep(1000 * 1); // 1 second rest
//		}
//	}
//	
//	public void remoteFailure() throws AquaCropException, AquaCropRemoteException, IOException {
//		// get existing value
//		int numPlants = project.getCropCharacteristics().getNumPlants();
//		
//		// set to dodgy one
//		project.getCropCharacteristics().setNumPlants(-1);
//		
//		// try execute
//		Output dodgyOutput = null;
//		try {
//			dodgyOutput = runProjectRemotely();
//		}
//		catch (AquaCropException e) {
//			// should be null
//			assertNull(dodgyOutput);
//			
//			// back to existing
//			project.getCropCharacteristics().setNumPlants(numPlants);
//			
//			// should be fine
//			Output fineOutput = runProjectRemotely();
//			assertNotNull(fineOutput);
//		}
//	}
//	
//	private Output runProjectRemotely() throws AquaCropException, AquaCropRemoteException, IOException {
//		AquaCropClient client = new AquaCropClient("cs-lightning", 44446);
//		return client.send(project);
//	}
	
	/*
	public void testSerializeProject() throws IOException {
		FileWriter writer = new FileWriter(new File("src/test/resources/TestWrite.PRO"));
		AquaCropRunner.serializeProject(project, writer);
		writer.close();
	}

	public void testDeserializeOutput() throws IOException {
		File outputFile = new File("src/test/resources/TestPRO.OUT");
		Output output = AquaCropRunner.deserializeOutput(new FileReader(outputFile));
		System.out.println(output.toString());
	}*/

//	public void testRunner() throws IOException {
//		try {
//			AquaCropRunner runner = new AquaCropRunner("C:\\FAO");
//			Output output = runner.run(project);
//			System.out.println(output.toString());
//		}
//		catch (AquaCropException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	

}
