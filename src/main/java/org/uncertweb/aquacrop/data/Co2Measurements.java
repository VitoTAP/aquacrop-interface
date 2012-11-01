package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Co2Measurements implements Serializable {
	
	private static final long serialVersionUID = 4537930072502959840L;
	
	private LinkedHashMap<Integer, Double> measurements;

	public Co2Measurements() {
		measurements = new LinkedHashMap<Integer, Double>();
	}
	
	/**
	 * ppm by volume
	 * 
	 */	
	public void addMeasurement(int year, double co2) {
		measurements.put(year, co2);
	}
	
	public double getMeasurement(int year) {
		return measurements.get(year);
	}
	
	public Set<Integer> getYears() {
		return measurements.keySet();
	}
	
	public List<Double> getMeasurements() {
		return new ArrayList<Double>(measurements.values());
	}
	
}
