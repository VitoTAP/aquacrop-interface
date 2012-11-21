package org.uncertweb.aquacrop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.uncertweb.aquacrop.data.ClimateCharacteristics;
import org.uncertweb.aquacrop.data.Co2Measurements;
import org.uncertweb.aquacrop.data.CropCharacteristics;
import org.uncertweb.aquacrop.data.EvapotranspirationMeasurements;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.data.RainfallMeasurements;
import org.uncertweb.aquacrop.data.SoilCharacteristics;
import org.uncertweb.aquacrop.data.TemperatureMeasurements;

public class AquaCropSerializer {
	
	private File listDir;
	private File dataDir;	
	private File dataDirOverride;
	private String outputFilename;	
	
	public AquaCropSerializer(String outputFilename, String basePath) {
		File baseDir = new File(basePath);
		this.listDir = new File(baseDir, "ACsaV31plus" + File.separator + "LIST");
		this.dataDir = new File(baseDir, "AquaCrop" + File.separator + "DATA");
		this.outputFilename = outputFilename;
		
		// init velocity		
		Properties props = new Properties();
		props.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(props);
	}
	
	public AquaCropSerializer(String filename, String basePath, String basePathOverride) {
		this(filename, basePath);
		if (basePathOverride != null) {
			this.dataDirOverride = new File(new File(basePathOverride), "AquaCrop" + File.separator + "DATA");
		}
	}

	public void serialize(Project project) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(listDir, outputFilename + ".PRO"));
		
		// write other files first
		serialize(project.getCropCharacteristics());
		serialize(project.getSoilCharacteristics());
		serialize(project.getClimateCharacteristics());
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("aquaCropVersion", AquaCrop.VERSION);
		context.put("basePath", (dataDirOverride != null ? dataDirOverride.getAbsolutePath() : dataDir.getAbsolutePath()));
		context.put("filename", outputFilename);
		context.put("project", project);
		context.put("simPeriodStart", AquaCropUtilities.convertDateToInt(project.getSimulationPeriodStart()));
		context.put("simPeriodEnd", AquaCropUtilities.convertDateToInt(project.getSimulationPeriodEnd()));
		context.put("cropPeriodStart", AquaCropUtilities.convertDateToInt(project.getCroppingPeriodStart())); 
		context.put("cropPeriodEnd", AquaCropUtilities.convertDateToInt(project.getCroppingPeriodEnd()));
		context.put("antecedentAdjust", (project.isAntecedentAdjust() ? "1" : "0"));
		Template template = getTemplate("project");
		template.merge(context, writer);
		
		// all done
		writer.close();
	}

	public void serialize(RainfallMeasurements rainfallMeasurements) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".PLU"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("rainfallMeasurements", rainfallMeasurements);
		Template template = getTemplate("rainfallMeasurements");
		template.merge(context, writer);
		
		// all done
		writer.close();	
	}

	public void serialize(ClimateCharacteristics cliCharacteristics) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".CLI"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("aquaCropVersion", AquaCrop.VERSION);
		context.put("filename", outputFilename);
		Template template = getTemplate("climateCharacteristics");
		template.merge(context, writer);
				
		// now write them
		serialize(cliCharacteristics.getTemperature());
		serialize(cliCharacteristics.getCo2());
		serialize(cliCharacteristics.getEto());
		serialize(cliCharacteristics.getRainfall());
		
		// all done
		writer.close();
	}
	
	public void serialize(TemperatureMeasurements tmpMeasurements) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".TMP"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("temperatureMeasurements", tmpMeasurements);
		Template template = getTemplate("temperatureMeasurements");
		template.merge(context, writer);
				
		// all done
		writer.close();	
	}

	public void serialize(SoilCharacteristics soilCharacteristics) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".SOL"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("aquaCropVersion", AquaCrop.VERSION);
		context.put("soilCharacteristics", soilCharacteristics);
		Template template = getTemplate("soilCharacteristics");
		template.merge(context, writer);
		
		// all done
		writer.close();	
	}

	public void serialize(Co2Measurements co2Measurements) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".CO2"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("co2Measurements", co2Measurements);
		Template template = getTemplate("co2Measurements");
		template.merge(context, writer);
		
		// all done
		writer.close();
	}
	
	public void serialize(CropCharacteristics cropCharacteristics) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".CRO"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("aquaCropVersion", AquaCrop.VERSION);
		context.put("cropCharacteristics", cropCharacteristics);
		context.put("sown", (cropCharacteristics.isSown() ? 1 : 0));
		context.put("depletionFactorsAdjusted", (cropCharacteristics.isDepletionFactorsAdjusted() ? 1 : 0));
		Template template = getTemplate("cropCharacteristics");
		template.merge(context, writer);
		
		// all done
		writer.close();
	}
	
	public void serialize(EvapotranspirationMeasurements etMeasurements) throws IOException {
		// create file
		FileWriter writer = new FileWriter(new File(dataDir, outputFilename + ".ETO"));
		
		// write
		VelocityContext context = new VelocityContext();
		context.put("etMeasurements", etMeasurements);
		Template template = getTemplate("evapotranspirationMeasurements");
		template.merge(context, writer);
		
		// all done
		writer.close();	
	}
	
	private Template getTemplate(String name) {
		Template template = null;
		try {
			template = Velocity.getTemplate("templates/" + name + ".vm", "UTF-8");
		}
		catch (ResourceNotFoundException e) {
		   // couldn't find the template
			e.printStackTrace();
		}
		catch (ParseErrorException e) {
		   // syntax error: problem parsing the template
			e.printStackTrace();
		}
		catch (MethodInvocationException e) {
		  // something invoked in the template
		  // threw an exception
			e.printStackTrace();
		}
		return template;
	}
	
}
