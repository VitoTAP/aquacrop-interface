package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int year;
	private double rain; // mm
	private double eto; // mm
	private double gdd; // degC/day
	private double co2; // ppm
	private double irri; // mm
	private double infilt; // mm
	private double runOff;
	private double e; // mm
	private double  eEx; // %
	private double tr; // mm
	private double trTrx; // %
	private double drain; // mm
	private double bioMass; // ton/ha
	private double  brW; // %
	private double  brWsf; // %
	private double wPetB; // kg/m3
	private double  hi; // %
	private double yield; // ton/ha
	private double wPetY; // kg/m3
	private double cycleDays;
	private double tempStr;

	public Output(int year,double rain, double eto, double gdd, double co2, double irri, double infilt, double runOff, double e, double eEx, double tr,
				  double trTrx, double drain, double bioMass, double brW, double brWsf, double wPetB, double hi, double yield, double wPetY,  double cycleDays, double tempStr) {
		this.year = year;
		this.rain = rain;
		this.eto = eto;
		this.gdd = gdd;
		this.co2 = co2;
		this.irri = irri;
		this.infilt = infilt;
		this.runOff = runOff;
		this.e = e;
		this.eEx = eEx;
		this.tr = tr;
		this.trTrx = trTrx;
		this.drain = drain;
		this.bioMass = bioMass;
		this.brW = brW;
		this.brWsf = brWsf;
		this.wPetB = wPetB;
		this.hi = hi;
		this.yield = yield;
		this.wPetY = wPetY;
		this.cycleDays = cycleDays;
		this.tempStr = tempStr;
	}

	public double getRain() {
		return rain;
	}
	
	public double getEto() {
		return eto;
	}
	
	public double getGdd() {
		return gdd;
	}
	
	public double getCo2() {
		return co2;
	}
	
	public double getIrri() {
		return irri;
	}
	
	public double getInfilt() {
		return infilt;
	}
	
	public double getE() {
		return e;
	}
	
	public double  geteEx() {
		return eEx;
	}
	
	public double getTr() {
		return tr;
	}
	
	public double getTrTrx() {
		return trTrx;
	}
	
	public double getDrain() {
		return drain;
	}
	
	public double getBioMass() {
		return bioMass;
	}
	
	public double getBrW() {
		return brW;
	}
	
	public double  getBrWsf() {
		return brWsf;
	}
	
	public double getwPetB() {
		return wPetB;
	}
	
	public double getHi() {
		return hi;
	}
	
	public double getYield() {
		return yield;
	}
	
	public double getwPetY() {
		return wPetY;
	}

	public int getYear() {
		return year;
	}

	public double getRunOff() {
		return runOff;
	}

	public double getCycleDays() {
		return cycleDays;
	}

	public double getTempStr() {
		return tempStr;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Field field : this.getClass().getDeclaredFields()) {
			int modifiers = field.getModifiers();
			if (Modifier.isPrivate(modifiers) && !Modifier.isStatic(modifiers)) {
				String value;
				try {
					value = String.valueOf(field.getDouble(this));
				}
				catch (IllegalAccessException e) {
					value = "?";
				}
				sb.append(field.getName() + "=" + value + ", ");
			}
		}
		return sb.delete(sb.length() - 2, sb.length()).toString();
	}
	
}
