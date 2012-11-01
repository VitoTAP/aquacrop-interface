package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class RainfallMeasurements implements Serializable {
	
	private static final long serialVersionUID = -8312683446337977397L;
	
	private Frequency frequency;
	private LocalDate firstDay;
	private List<Double> measurements;
	
	public RainfallMeasurements(Frequency frequency, LocalDate firstDay) {
		this.frequency = frequency;
		this.firstDay = firstDay;
		measurements = new ArrayList<Double>();
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	
	public LocalDate getFirstDay() {
		return firstDay;
	}
	
	public void addMeasurement(double measurement) {
		measurements.add(measurement);
	}
	
	public List<Double> getMeasurements() {
		return measurements;
	}
	
}
