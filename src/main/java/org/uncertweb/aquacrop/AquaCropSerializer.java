package org.uncertweb.aquacrop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;

import org.uncertweb.aquacrop.data.ClimateCharacteristics;
import org.uncertweb.aquacrop.data.Co2Measurements;
import org.uncertweb.aquacrop.data.CropCharacteristics;
import org.uncertweb.aquacrop.data.EvapotranspirationMeasurements;
import org.uncertweb.aquacrop.data.Frequency;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.data.RainfallMeasurements;
import org.uncertweb.aquacrop.data.SoilCharacteristics;
import org.uncertweb.aquacrop.data.SoilCharacteristics.SoilHorizon;
import org.uncertweb.aquacrop.data.TemperatureMeasurements;
import org.uncertweb.aquacrop.data.TemperatureMeasurements.TemperatureMeasurement;

public class AquaCropSerializer {

	public static void serializeProject(Project project, String basePath, String basePathOverride, String runId) throws IOException {
		// create project file
		File projectFile = new File(basePath, "ACsaV31plus/LIST/" + runId + ".PRO");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		writer.write(project.getTitle() + "\r\n");
		writer.write(AquaCrop.VERSION + "\r\n");

		// period
		writer.write(AquaCropUtilities.convertDateToInt(project.getSimulationPeriodStart()) + "\r\n");
		writer.write(AquaCropUtilities.convertDateToInt(project.getSimulationPeriodEnd()) + "\r\n");
		writer.write(AquaCropUtilities.convertDateToInt(project.getCroppingPeriodStart()) + "\r\n");
		writer.write(AquaCropUtilities.convertDateToInt(project.getCroppingPeriodEnd()) + "\r\n");

		// parameters
		writer.write(project.getEvaporationDeclineFactor() + "\r\n");
		writer.write(project.getEvaporationCoefficient() + "\r\n");
		writer.write(project.getThreshold() + "\r\n");
		writer.write(project.getRootStartDepth() + "\r\n");
		writer.write(project.getRootMax() + "\r\n");
		writer.write(project.getRootShapeFactor() + "\r\n");
		writer.write(project.getSoilWaterContent() + "\r\n");
		writer.write(project.getSoilAdjustmentFactor() + "\r\n");
		writer.write(project.getDeficientAerationDays() + "\r\n");
		writer.write(project.getSenescenceFactorExponent() + "\r\n");
		writer.write(project.getSenescenceDecrease() + "\r\n");
		writer.write(project.getSoilProfileDepth() + "\r\n");
		writer.write(project.getConsideredSoilProfileDepth() + "\r\n");
		writer.write((project.isAntecedentAdjust() ? "1" : "0") + "\r\n");
		writer.write(project.getDefaultMinTemperature() + "\r\n");
		writer.write(project.getDefaultMaxTemperature() + "\r\n");
		writer.write(project.getDefaultGrowingMethod() + "\r\n");			

		// files
		String bp = basePath;
		if (basePathOverride != null) {
			bp = basePathOverride;
		}
		AquaCropSerializer.serializeClimateCharacteristics(project.getClimateCharacteristics(), basePath, runId);
		AquaCropSerializer.writeFileSection("-- 1. Climate (CLI) file", new File(bp, "AquaCrop/DATA/" + runId + ".CLI"), writer);
		AquaCropSerializer.writeFileSection("1.1 Temperature (TMP) file", new File(bp, "AquaCrop/DATA/" + runId + ".TMP"), writer);
		AquaCropSerializer.writeFileSection("1.2 Reference ET (ETo) file", new File(bp, "AquaCrop/DATA/" + runId + ".ETO"), writer);
		AquaCropSerializer.writeFileSection("1.3 Rain (PLU) file", new File(bp, "AquaCrop/DATA/" + runId + ".PLU"), writer);
		AquaCropSerializer.writeFileSection("1.4 Atmospheric CO2 (CO2) file", new File(bp, "AquaCrop/DATA/" + runId + ".CO2"), writer);
		
		AquaCropSerializer.serializeCropCharacteristics(project.getCropCharacteristics(), basePath, runId);		
		AquaCropSerializer.writeFileSection("-- 2. Crop (CRO) file", new File(bp, "AquaCrop/DATA/" + runId + ".CRO"), writer);
		
		AquaCropSerializer.writeFileSection("-- 3. Irrigation (IRR) file", null, writer);
		AquaCropSerializer.writeFileSection("-- 4. Management (MAN) file", null, writer);
		
		AquaCropSerializer.serializeSoilCharacteristics(project.getSoilCharacteristics(), basePath, runId);
		AquaCropSerializer.writeFileSection("-- 5. Soil (SOL) file", new File(bp, "AquaCrop/DATA/" + runId + ".SOL"), writer);
		
		AquaCropSerializer.writeFileSection("-- 6. Initial conditions (SW0) file", null, writer);		
		AquaCropSerializer.writeFileSection("-- 7. Off-season conditions (OFF) file", null, writer);
		
		// all done
		writer.close();
	}
	
	private static void serializeSoilCharacteristics(SoilCharacteristics soilCharacteristics, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".SOL");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		writer.write("Soil characteristics\r\n");
		writer.write(AquaCrop.VERSION + "\r\n");
		writer.write(soilCharacteristics.getCurveNumber() + "\r\n");
		writer.write(soilCharacteristics.getEvaporableWater() + "\r\n");
		writer.write(soilCharacteristics.getSoilHorizons().size() + "\r\n");
		writer.write(soilCharacteristics.getRestrictiveSoilLayerDepth() + "\r\n");
		writer.write("Thickness\tSat\tFC\tWP\tKsat\tdescription\r\n");
		writer.write("---(m)-\t----(vol %)-----\t(mm/day)\t---------------------------------\r\n");
		
		// measurements
		for (SoilHorizon horizon : soilCharacteristics.getSoilHorizons()) {
			writer.write(horizon.getThickness() + "\t" + horizon.getSat() + "\t" + horizon.getFc() + "\t" + horizon.getPwp() + "\t" + horizon.getkSat() + "\t" + horizon.getDescription() + "\r\n");
		}
		
		// all done
		writer.close();	
	}

	private static void serializePrecipitationMeasurements(RainfallMeasurements pluMeasurements, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".PLU");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		Frequency frequency = pluMeasurements.getFrequency();
		writer.write("Precipitation measurements" + "\r\n");
		writer.write((frequency.ordinal() + 1) + "\r\n");		
		writer.write(pluMeasurements.getFirstDay().getDayOfMonth() + "\r\n");
		writer.write(pluMeasurements.getFirstDay().getMonthOfYear() + "\r\n");
		writer.write(pluMeasurements.getFirstDay().getYear() + "\r\n");		
		writer.write("\r\n");
		writer.write("Total Rain (mm)\r\n");													
		writer.write("=======================\r\n");
		
		// measurements
		for (double measurement : pluMeasurements.getMeasurements()) {
			writer.write(measurement + "\r\n");
		}
		
		// all done
		writer.close();	
	}

	private static void serializeClimateCharacteristics(ClimateCharacteristics cliCharacteristics, String basePath, String runId) throws IOException {
		// create project file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".CLI");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		writer.write("Climate files index" + "\r\n");
		writer.write(AquaCrop.VERSION + "\r\n");
		
		// files
		writer.write(runId + ".TMP" + "\r\n");
		writer.write(runId + ".ETO" + "\r\n");
		writer.write(runId + ".PLU" + "\r\n");
		writer.write(runId + ".CO2" + "\r\n");
		
		// now write them
		AquaCropSerializer.serializeTemperatureMeasurements(cliCharacteristics.getTemperature(), basePath, runId);
		AquaCropSerializer.serializeCo2Measurements(cliCharacteristics.getCo2(), basePath, runId);
		AquaCropSerializer.serializeEvapotranspirationMeasurements(cliCharacteristics.getEto(), basePath, runId);
		AquaCropSerializer.serializePrecipitationMeasurements(cliCharacteristics.getRainfall(), basePath, runId);
		
		// all done
		writer.close();
	}
	
	private static void serializeTemperatureMeasurements(TemperatureMeasurements tmpMeasurements, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".TMP");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		Frequency frequency = tmpMeasurements.getFrequency();
		writer.write("Temperature measurements" + "\r\n");
		writer.write((frequency.ordinal() + 1) + "\r\n");		
		writer.write(tmpMeasurements.getFirstDay().getDayOfMonth() + "\r\n");
		writer.write(tmpMeasurements.getFirstDay().getMonthOfYear() + "\r\n");
		writer.write(tmpMeasurements.getFirstDay().getYear() + "\r\n");		
		writer.write("\r\n");
		writer.write("Tmin (C)\tTMax (C)\r\n");													
		writer.write("=======================\r\n");
		
		// measurements
		for (TemperatureMeasurement measurement : tmpMeasurements.getMeasurements()) {
			writer.write(measurement.getMinimum() + "\t" + measurement.getMaximum() + "\r\n");
		}
		
		// all done
		writer.close();	
	}

	private static void serializeCo2Measurements(Co2Measurements co2Measurements, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".CO2");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		writer.write("CO2 measurements" + "\r\n");
		writer.write("Year	CO2	(ppm	by	volume)" + "\r\n");
		writer.write("=======================" + "\r\n");
		
		// measurements
		for (int year : co2Measurements.getYears()) {
			writer.write(year + "\t" + co2Measurements.getMeasurement(year) + "\r\n");
		}
		
		// all done
		writer.close();
	}
	
	private static void serializeCropCharacteristics(CropCharacteristics cropCharacteristics, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".CRO");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		writer.write("Crop characteristics" + "\r\n");
		writer.write(AquaCrop.VERSION + "\r\n");
		writer.write("1" + "\r\n");												
				
		// parameters
		for (Field field : cropCharacteristics.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object o = field.get(cropCharacteristics);
				// special case for boolean
				if (o instanceof Boolean) {
					writer.write(((Boolean) o ? "1" : "0") + "\r\n");
				}
				else {
					writer.write(o + "\r\n");
				}
				field.setAccessible(false);
			}
			catch (IllegalAccessException e) {
				// ignore
			}
		}
		
		// all done
		writer.close();
	}
	
	private static void serializeEvapotranspirationMeasurements(EvapotranspirationMeasurements etMeasurements, String basePath, String runId) throws IOException {
		// create file
		File projectFile = new File(basePath, "AquaCrop/DATA/" + runId + ".ETO");
		FileWriter writer = new FileWriter(projectFile);
		
		// header
		Frequency frequency = etMeasurements.getFrequency();
		writer.write("Evapotranspiration measurements" + "\r\n");
		writer.write((frequency.ordinal() + 1) + "\r\n");		
		writer.write(etMeasurements.getFirstDay().getDayOfMonth() + "\r\n");
		writer.write(etMeasurements.getFirstDay().getMonthOfYear() + "\r\n");
		writer.write(etMeasurements.getFirstDay().getYear() + "\r\n");		
		writer.write("\r\n");
		writer.write("Average ETo (mm/day)\r\n");													
		writer.write("=======================\r\n");
		
		// measurements
		for (double measurement : etMeasurements.getMeasurements()) {
			writer.write(measurement + "\r\n");
		}
		
		// all done
		writer.close();		
	}

	private static void writeFileSection(String name, File file, Writer writer) throws IOException {
		writer.write(name + "\r\n");
		if (file == null) {
			writer.write("(None)" + "\r\n");
			writer.write("(None)" + "\r\n");
		}
		else {
			String filename = file.getPath().substring(file.getPath().lastIndexOf("\\") + 1);
			writer.write(filename + "\r\n");
			String dir = file.getPath().substring(0, file.getPath().lastIndexOf("\\") + 1);
			writer.write(dir + "\r\n");
		}
	}
	
}
