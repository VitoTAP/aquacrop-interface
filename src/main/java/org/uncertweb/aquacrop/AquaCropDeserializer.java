package org.uncertweb.aquacrop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.uncertweb.aquacrop.data.Output;

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
				double rain = Double.parseDouble(tokens[4]);
				double eto = Double.parseDouble(tokens[5]);
				double gdd = Double.parseDouble(tokens[6]);
				double co2 = Double.parseDouble(tokens[7]);
				double irri = Double.parseDouble(tokens[8]);
				double infilt = Double.parseDouble(tokens[9]);
				double e = Double.parseDouble(tokens[13]);
				double eEx = Double.parseDouble(tokens[14]);
				double tr = Double.parseDouble(tokens[15]);
				double trTrx = Double.parseDouble(tokens[16]);
				double drain = Double.parseDouble(tokens[11]);
				double bioMass = Double.parseDouble(tokens[27]);
				double brW = Double.parseDouble(tokens[16]);
				double brWsf = Double.parseDouble(tokens[17]);
				double wPetB = Double.parseDouble(tokens[31]);
				double hi = Double.parseDouble(tokens[29]);
				double yield = Double.parseDouble(tokens[30]);
				double wPetY = Double.parseDouble(tokens[21]);

				// and return
				return new Output(rain, eto, gdd, co2, irri, infilt, e, eEx, tr, trTrx, drain, bioMass, brW, brWsf, wPetB, hi, yield, wPetY);
			}
		}

		return null;
	}

}
