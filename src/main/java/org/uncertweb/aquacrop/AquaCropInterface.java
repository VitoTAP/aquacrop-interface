package org.uncertweb.aquacrop;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.aquacrop.data.Output;
import org.uncertweb.aquacrop.data.Project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Okay...
 * 
 * The AquaCrop plug-in program has no UI, but creates an application window when it runs
 * (containing nothing, disappears upon program termination).
 * 
 * Therefore running the plug-in program with Wine results in the following:
 *  - Application tried to create a window, but no driver could be loaded.
 *  - Make sure that your X server is running and that $DISPLAY is set correctly.
 * 
 * As we want to run the program behind a web service interface, on a server somewhere, which
 * may or may not have X server running or a display, this poses an issue!
 * 
 * The solution is Xvfb.
 * 
 * The xvfb-run script creates...
 *  
 * 
 * 
 * @author Richard Jones
 *
 */
public class AquaCropInterface {
	
	private final Logger logger = LoggerFactory.getLogger(AquaCropInterface.class);

	private String basePath;
	private String prefixCommand;
	private String basePathOverride;
	
	private boolean keepFiles;

	public AquaCropInterface(String basePath, boolean keepFiles) {
		this(basePath, null, null, keepFiles);
	}
	
	public AquaCropInterface(String basePath, String prefixCommand, String basePathOverride, boolean keepFiles) {
		this.basePath = basePath;
		this.prefixCommand = prefixCommand;
		this.basePathOverride = basePathOverride;
		this.keepFiles = keepFiles;
	}

	public File runWithoutParsing(Project project)throws AquaCropException {
		// check given params are sensible
		preRunChecks();
		// generate an id
		final String runId = String.valueOf(System.currentTimeMillis());

		// generate run folder
		File runDir = new File(basePath, "aquacrop_" + runId);

		setupExecutable(runDir);

		// serialize project and run

		try {
			File outputFile = runProject(project, runDir);
		}catch (IOException e) {
			// will be from creating the project file, or reading the output file
			String message = "Error reading input/output files: " + e.getMessage();
			throw new AquaCropException(message, e);
		}
		return runDir;
	}

	public Output run(Project project) throws AquaCropException {
		// check given params are sensible
		preRunChecks();
		// generate an id
		final String runId = String.valueOf(System.currentTimeMillis());

		// generate run folder
		File runDir = new File(basePath, "aquacrop_" + runId);

		setupExecutable(runDir);

		// serialize project and run
		Output output = null;
		try {
			File outputFile = runProject(project, runDir);


			// parse output
			logger.debug("Process finished, parsing output...");
			FileReader reader = new FileReader(outputFile);
			output = new AquaCropDeserializer().deserialize(reader);
			reader.close();

			
			if (output == null) {
				// must be a problem running AquaCrop, but unfortunately it only gives error messages in dialogs!
				String message = "Couldn't parse empty AquaCrop output, parameters may be invalid.";
				throw new AquaCropException(message);
			}
			else {

				logger.debug("Parsed output successfully.");
				return output;
			}
		}
		catch (IOException e) {
			// will be from creating the project file, or reading the output file
			String message = "Error reading input/output files: " + e.getMessage();
			throw new AquaCropException(message, e);
		}
		finally {
			// clean up input files
			try {
				if (keepFiles) {
					/*moveFileFrom(runPath, "projectPROseason.OUT", "ACsaV31plus/OUTP/");
					moveFileFrom(runPath, "defaultPROseason.OUT", "ACsaV31plus/OUTP/");
					moveFileFrom(runPath, "ListProjectsLoaded.OUT", "ACsaV31plus/OUTP/");
					moveFileFrom(runPath, "project.PRO", "ACsaV31plus/LIST/");
					moveFileFrom(runPath, "project.CLI", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.CRO", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.PLU", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.TMP", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.SOL", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.CO2", "AquaCrop/DATA/");
					moveFileFrom(runPath, "project.ETO", "AquaCrop/DATA/");
					FileUtils.deleteDirectory(new File(runDir, "AquaCrop"));
					FileUtils.deleteDirectory(new File(runDir, "ACsaV31plus"));*/
					if (output == null) {
						runDir.renameTo(new File(basePath, "aquacrop_" + runId + "_failed"));
					}
				}
				else {
					FileUtils.deleteDirectory(runDir);
				}
			}
			catch (IOException e) {
				logger.warn("Couldn't remove output directory.", e);
			}
		}
	}

	private void setupExecutable(File runDir) throws AquaCropException {
		try {
			FileUtils.copyDirectoryToDirectory(new File(basePath, "ACsaV31plus"), runDir);
			FileUtils.copyDirectoryToDirectory(new File(basePath, "AquaCrop"), runDir);
		}
		catch (IOException e) {
			throw new AquaCropException("Couldn't create run directory.", e);
		}
	}

	private File runProject(Project project, File runDir) throws IOException, AquaCropException {
		String runPath = runDir.getAbsolutePath();
		// this will create all data files
		if (basePathOverride != null) {
            basePathOverride = basePathOverride + "\\" + runDir.getName() + "\\AquaCrop\\DATA";
        }
		logger.debug("Serializing project...");
		AquaCropSerializer serializer = new AquaCropSerializer("project", runPath, basePathOverride);
		serializer.serialize(project);

		// move files
		// PRO to ACsaV31plus/LIST/
		// everything else to AquaCrop/DATA/
		moveFileTo(runPath, "project.PRO", "ACsaV31plus/LIST/");
		moveFileTo(runPath, "project.CLI", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.CRO", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.PLU", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.TMP", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.SOL", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.CO2", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.ETO", "AquaCrop/DATA/");
		moveFileTo(runPath, "project.IRR", "AquaCrop/DATA/");

		// get runtime
		logger.debug("Getting runtime...");
		Runtime runtime = Runtime.getRuntime();

		// for monitoring and reading
		File outputFile = new File(runPath, "ACsaV31plus/OUTP/projectPROseason.OUT");

		// run program
		try {
            // start aquacrop process
            logger.debug("Starting process...");
            Process process = runtime.exec((prefixCommand != null ? prefixCommand + " " : "") + runPath + "/ACsaV31plus/ACsaV31plus.exe");

            // wait for process
            // we could be waiting forever if no output is produced
            logger.debug("Waiting for process to end...");
            boolean done = false;
            while (!done) {
                if (outputFile.exists()) {
                    done = true;
                    Thread.sleep(2000); // wait for write
                    // potential for exit command here
                    // xwd -display :1 -root -out image.xwd
                    process.destroy(); // force required if error
                }
                else {
                    Thread.sleep(500);
                }
            }
            process.waitFor();
        }
        catch (IOException e) {
            throw new AquaCropException("Couldn't run AquaCrop: " + e.getMessage(), e);
        }
        catch (InterruptedException e) {
            throw new AquaCropException("Couldn't run AquaCrop: " + e.getMessage(), e);
        }
		return outputFile;
	}

	private void preRunChecks() throws AquaCropException {
		// check given params are sensible
		boolean basePathExists = new File(basePath).exists();
		if (!basePathExists) {
			throw new AquaCropException("Can't find AquaCrop executables at " + basePath + ".");
		}
	}
	
	private void moveFileFrom(String path, String filename, String source) {
		File file = new File(new File(path, source), filename);
	 	file.renameTo(new File(new File(path), filename));
	}
	
	private void moveFileTo(String path, String filename, String dest) {
		File file = new File(new File(path), filename);
	 	file.renameTo(new File(new File(path, dest), filename));
	}

}
