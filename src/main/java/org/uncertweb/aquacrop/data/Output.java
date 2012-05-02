package org.uncertweb.aquacrop.data;

import java.io.Serializable;

public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double rain; // mm
	private double eto; // mm
	private double gdd; // degC/day
	private double co2; // ppm
	private double irri; // mm
	private double infilt; // mm
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
	
	public Output(double rain, double eto, double gdd, double co2, double irri, double infilt, double e, double  eEx, double tr,
		double trTrx, double drain, double bioMass, double  brW, double  brWsf, double wPetB, double  hi, double yield, double wPetY) {
		this.rain = rain;
		this.eto = eto;
		this.gdd = gdd;
		this.co2 = co2;
		this.irri = irri;
		this.infilt = infilt;
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
	
	public double  getBrW() {
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
	
}
