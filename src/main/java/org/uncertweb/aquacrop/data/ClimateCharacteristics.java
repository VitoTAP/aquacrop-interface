package org.uncertweb.aquacrop.data;

import java.io.Serializable;

public class ClimateCharacteristics implements Serializable {

	private RainfallMeasurements rainfall; //rainfall
	private EvapotranspirationMeasurements eto;	//eto
	private TemperatureMeasurements temperature; //temp
	private Co2Measurements co2; //atmospheric co2
	
	public ClimateCharacteristics(RainfallMeasurements rainfall, EvapotranspirationMeasurements eto, TemperatureMeasurements temperature, Co2Measurements co2) {
		this.rainfall = rainfall;
		this.eto = eto;
		this.temperature = temperature;
		this.co2 = co2;
	}

	public RainfallMeasurements getRainfall() {
		return rainfall;
	}

	public EvapotranspirationMeasurements getEto() {
		return eto;
	}

	public TemperatureMeasurements getTemperature() {
		return temperature;
	}

	public Co2Measurements getCo2() {
		return co2;
	}
	
}
