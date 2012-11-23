package org.uncertweb.aquacrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.uncertweb.aquacrop.data.ClimateCharacteristics;
import org.uncertweb.aquacrop.data.Co2Measurements;
import org.uncertweb.aquacrop.data.CropCharacteristics;
import org.uncertweb.aquacrop.data.EvapotranspirationMeasurements;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.data.RainfallMeasurements;
import org.uncertweb.aquacrop.data.SoilCharacteristics;
import org.uncertweb.aquacrop.data.TemperatureMeasurements;
import org.uncertweb.aquacrop.data.sample.SampleData;

public class AquaCropSerializerTest {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	private File expectedDir;
	private File actualDir;	
	private String outputFilename = "project";	
	private AquaCropSerializer serializer;
	
	@Before
	public void setUp() throws IOException, URISyntaxException {
		expectedDir = new File(this.getClass().getClassLoader().getResource("expected").toURI());
		actualDir = folder.newFolder("actual");
		serializer = new AquaCropSerializer(outputFilename, actualDir.getPath(), "C:\\FAO\\AquaCrop\\DATA");
	}

	@Test
	public void temperatureMeasurements() throws IOException {
		TemperatureMeasurements measurements = SampleData.getTemperatureMeasurements();
		serializer.serialize(measurements);
		testFile("TMP");
	}
	
	@Test
	public void soilCharacteristics() throws IOException {
		SoilCharacteristics soil = SampleData.getSoilCharacteristics();
		serializer.serialize(soil);
		testFile("SOL");
	}
	
	@Test
	public void co2Measurements() throws IOException {
		Co2Measurements measurements = SampleData.getCo2Measurements();
		serializer.serialize(measurements);
		testFile("CO2");
	}
	
	@Test
	public void rainfallMeasurements() throws IOException {
		RainfallMeasurements measurements = SampleData.getRainfallMeasurements();
		serializer.serialize(measurements);
		testFile("PLU");
	}
	
	@Test
	public void climateCharacteristics() throws IOException {
		ClimateCharacteristics characteristics = SampleData.getClimateCharacteristics();
		serializer.serialize(characteristics);
		testFile("CLI");
		testFile("TMP");
		testFile("CO2");
		testFile("PLU");
		testFile("ETO");
	}
	
	@Test
	public void cropCharacteristics() throws IOException {	
		CropCharacteristics characteristics = SampleData.getCropCharacteristics();
		serializer.serialize(characteristics);
		testFile("CRO");
	}
	
	@Test
	public void evapotranspirationMeasurements() throws IOException {
		EvapotranspirationMeasurements measurements = SampleData.getEvapotranspirationMeasurements();
		serializer.serialize(measurements);
		testFile("ETO");
	}
	
	@Test
	public void project() throws IOException {
		Project project = SampleData.getProject();
		serializer.serialize(project);
		testFile("PRO");
		testFile("CLI");
		testFile("TMP");
		testFile("CO2");
		testFile("PLU");
		testFile("ETO");
		testFile("SOL");
		testFile("CRO");
	}
	
	@Test
	public void projectNoOverride() throws IOException {
		// serialize
		AquaCropSerializer acs = new AquaCropSerializer(outputFilename, actualDir.getPath());
		Project project = SampleData.getProject();
		acs.serialize(project);
		
		// read for comparison
		String expected = readFile(expectedDir, outputFilename + ".PRO");
		String actual = readFile(actualDir, outputFilename + ".PRO");		
		Scanner expectedScanner = new Scanner(expected);
		Scanner actualScanner = new Scanner(actual);
		
		// compare with override
		while (expectedScanner.hasNext()) {
			String expectedLine = expectedScanner.nextLine();
			String actualLine = actualScanner.nextLine();
			if (!expectedLine.equals(actualLine)) {
				// must be \ for windows
				expectedLine = new File(actualDir, "AquaCrop" + File.separator + "DATA").getAbsolutePath() + "\\";
			}
			Assert.assertEquals(expectedLine, actualLine);
		}
	}

	private String readFile(File dir, String filename) throws IOException {
		File file = new File(dir, filename);
		FileInputStream stream = new FileInputStream(file);
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			return Charset.forName("UTF-8").decode(bb).toString();
		}
		finally {
			stream.close();
		}
	}
	
	private void testFile(String extension) throws IOException {
		String expected = readFile(expectedDir, outputFilename + "." + extension);
		String actual = readFile(actualDir, outputFilename + "." + extension);
		Assert.assertEquals(expected, actual);
	}

}
