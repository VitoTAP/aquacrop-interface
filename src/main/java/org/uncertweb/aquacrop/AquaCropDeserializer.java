package org.uncertweb.aquacrop;

import org.uncertweb.aquacrop.data.Output;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class AquaCropDeserializer {

	public AquaCropDeserializer() {

	}

	public Output deserialize(Reader reader) throws FileNotFoundException, IOException {
		BufferedReader bufReader = new BufferedReader(reader);
		bufReader.readLine(); // skip first line containing aquacrop version, creation date
		bufReader.readLine(); // skip following empty line
		bufReader.readLine(); // skip column headings
		bufReader.readLine(); // skip column headings units

		String line;
		while ((line = bufReader.readLine()) != null && line.length() > 0) { // could be blank line at bottom
			// split on whitespace
			String[] tokens = line.trim().split("\\s+");

			// totals is all we want
			if (tokens[0].startsWith("Tot")) {
				// parse results
				int year = Integer.parseInt(tokens[3]);
				double rain = Double.parseDouble(tokens[4]);
				double eto = Double.parseDouble(tokens[5]);
				double gdd = Double.parseDouble(tokens[6]);
				double co2 = Double.parseDouble(tokens[7]);
				double irri = Double.parseDouble(tokens[8]);
				double infilt = Double.parseDouble(tokens[9]);
				double runOff = Double.parseDouble(tokens[10]);

				double drain = Double.parseDouble(tokens[11]);

				double e = Double.parseDouble(tokens[13]);
				double eEx = Double.parseDouble(tokens[14]);
				double tr = Double.parseDouble(tokens[15]);
				double trTrx = Double.parseDouble(tokens[16]);

				//double brW = Double.parseDouble(tokens[16]);
				double brWsf = Double.parseDouble(tokens[17]);
				double cycleDays  = Double.parseDouble(tokens[21]);
				double tempStr = Double.parseDouble(tokens[24]);
				double bioMass = Double.parseDouble(tokens[27]);
				//Brelative
				double hi = Double.parseDouble(tokens[29]);
				double yield = Double.parseDouble(tokens[30]);
				double wPetB = Double.parseDouble(tokens[31]);


				// and return
				return new Output(year, rain, eto, gdd, co2, irri, infilt,runOff, e, eEx, tr, trTrx, drain, bioMass, 0., brWsf, wPetB, hi, yield, 0.,cycleDays,tempStr);
			}
		}

		return null;
	}

}
