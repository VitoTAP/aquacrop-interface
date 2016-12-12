package org.uncertweb.aquacrop;

import org.uncertweb.aquacrop.data.DailyOutput;
import org.uncertweb.aquacrop.data.Output;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class AquaCropDeserializer {

	public AquaCropDeserializer() {

	}

	public List<DailyOutput> deserializeDailyResult(Reader reader) throws IOException {
		BufferedReader bufReader = new BufferedReader(reader);
		bufReader.readLine(); // skip first line containing aquacrop version, creation date
		bufReader.readLine(); // skip following empty line
		bufReader.readLine(); // skip column headings
		bufReader.readLine(); // skip column headings units
		return bufReader.lines().map(AquaCropDeserializer::parseLine).collect(Collectors.toList());
	}

	private static DailyOutput parseLine(String line) {
		String[] tokens = line.trim().split("\\s+");
		//Day Month  Year   DAP Stage   WC(1.20)   Rain     Irri   Surf   Infilt   RO    Drain       CR    Zgwt       Ex       E    E/Ex     Trx       Tr  Tr/Trx    ETx      ET   ET/ETx
		return new DailyOutput(
				Short.parseShort(tokens[0]),
				Short.parseShort(tokens[1]),
				Integer.parseInt(tokens[2]),
				Integer.parseInt(tokens[3]),
				Integer.parseInt(tokens[4]),
				Double.parseDouble(tokens[5]),
				Double.parseDouble(tokens[6]),
				Double.parseDouble(tokens[7]),
				Double.parseDouble(tokens[8]),
				Double.parseDouble(tokens[9]),
				Double.parseDouble(tokens[10]),
				Double.parseDouble(tokens[11]),
				Double.parseDouble(tokens[12]),
				Double.parseDouble(tokens[13]),
				Double.parseDouble(tokens[14]),
				Double.parseDouble(tokens[15]),
				Double.parseDouble(tokens[16]),
				Double.parseDouble(tokens[17]),
				Double.parseDouble(tokens[18]),
				Double.parseDouble(tokens[19]),
				Double.parseDouble(tokens[20]),
				Double.parseDouble(tokens[21]),
				Double.parseDouble(tokens[22])
		);
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
