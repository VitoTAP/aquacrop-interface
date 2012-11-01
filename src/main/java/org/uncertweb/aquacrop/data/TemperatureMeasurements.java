package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class TemperatureMeasurements implements Serializable {

	private static final long serialVersionUID = -1976427767705906586L;
	
	private Frequency frequency;
	private LocalDate firstDay;
	private List<TemperatureMeasurement> measurements;
	
	public TemperatureMeasurements(Frequency frequency, LocalDate firstDay) {
		this.frequency = frequency;
		this.firstDay = firstDay;
		measurements = new ArrayList<TemperatureMeasurement>();
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	
	public LocalDate getFirstDay() {
		return firstDay;
	}
	
	public void addMeasurement(double minimum, double maximum) {
		measurements.add(new TemperatureMeasurement(minimum, maximum));
	}
	
	public void addMeasurement(TemperatureMeasurement measurement) {
		measurements.add(measurement);
	}
	
	public List<TemperatureMeasurement> getMeasurements() {
		return measurements;
	}	

	public class TemperatureMeasurement implements Serializable {
		
		private static final long serialVersionUID = 2880741391657561766L;
		
		private double minimum;
		private double maximum;
		
		public TemperatureMeasurement(double minimum, double maximum) {
			this.minimum = minimum;
			this.maximum = maximum;
		}

		public double getMinimum() {
			return minimum;
		}

		public double getMaximum() {
			return maximum;
		}
		
	}

}
