package org.uncertweb.aquacrop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.uncertweb.aquacrop.data.Output;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.test.TestData;
import org.uncertweb.aquacrop.test.TestEnvironment;

public class AquaCropInterfaceTest {
	
	@Rule
    public ExpectedException exception = ExpectedException.none();

	private AquaCropInterface iface;
	private static Output normalProjectOutput;
	
	@Before
	public void before() {
		TestEnvironment env = TestEnvironment.getInstance();
		iface = new AquaCropInterface(env.getBasePath(), env.getPrefixCommand(), env.getBasePathOverride());
	}
	
	@Test
	public void runReturnsOutput() throws AquaCropException {
		Output output = runNormalProject();
		assertNotNull(output);
	}
	
	@Test
	public void runReturnsRain() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(403.0, output.getRain(), 0.0);
	}
	
	@Test
	public void runReturnsEto() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(407.7, output.getEto(), 0.0);	
	}
	
	@Test
	public void runReturnsGdd() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(1274.9, output.getGdd(), 0.0);
	}
	 
	@Test
	public void runReturnsCo2() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(385.6, output.getCo2(), 0.0);
	}
	
	@Test
	public void runReturnsIrri() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(0.0, output.getIrri(), 0.0);
	}
	 
	@Test
	public void runReturnsInfilt() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(403.0, output.getInfilt(), 0.0);
	}
	
	@Test
	public void runReturnsE() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(203.7, output.getE(), 0.0); 
	}
	
	@Test
	public void runReturnseEx() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(90, output.geteEx(), 0.0); 
	}

	@Test
	public void runReturnsTr() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(175.3, output.getTr(), 0.0);
	}
	
	@Test
	public void runReturnstTrTrx() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(80, output.getTrTrx(), 0.0);
	}
	
	@Test
	public void runReturnsDrain() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(106.3, output.getDrain(), 0.0); 
	}
	 
	@Test
	public void runReturnsBioMass() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(4.948, output.getBioMass(), 0.0);       
	}
	
	@Test
	public void runReturnsBrW() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(71, output.getBrW(), 0.0);       
	}
	
	@Test
	public void runReturnsBrWsf() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(71, output.getBrWsf(), 0.0);
	}

	@Test
	public void runReturnswPetB() throws AquaCropException {
		Output output = runNormalProject();
		assertEquals(1.31, output.getwPetB(), 0.0);
	}
	
	@Test
	public void runReturnsHi() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(-9.9, output.getHi(), 0.0);
	}
	
	@Test
	public void runReturnsYield() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(0.0, output.getYield(), 0.0);
	}

	@Test
	public void runReturnswPetY() throws AquaCropException {
		Output output = runNormalProject();
	    assertEquals(0.0, output.getwPetY(), 0.0);
	}
	
	@Test
	public void runExceptionForInvalidProject() throws AquaCropException {
		// get invalid project
		Project project = TestData.getProject();
		project.getCropCharacteristics().setNumPlants(-1); // invalid
		
		// run
		exception.expect(AquaCropException.class);
		iface.run(project);		
	}
	
	private Output runNormalProject() throws AquaCropException {
		if (normalProjectOutput == null) {
			Project project = TestData.getProject();
			normalProjectOutput = iface.run(project);
		}
		return normalProjectOutput;
	}
	
}
