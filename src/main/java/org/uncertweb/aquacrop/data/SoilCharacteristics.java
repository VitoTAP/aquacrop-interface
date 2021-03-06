package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class SoilCharacteristics implements Serializable {
	
	private static final long serialVersionUID = 4098454278343937985L;
	
	private int curveNumber = 72;
	private int evaporableWater; // Readily	evaporable	water	from	top	layer	(mm)	
	private Collection<SoilHorizon> horizons;
	private int restrictiveSoilLayerDepth = -9; // Depth (m) of restrictive soil layer inhibiting root zone expansion - None
	
	public SoilCharacteristics() {
		this.horizons = new ArrayList<SoilHorizon>();
	}
	
	public void setEvaporableWater(int evaporableWater) {
		this.evaporableWater = evaporableWater;
	}
	
	public void addSoilHorizon(SoilHorizon horizon) {
		horizons.add(horizon);
	}
	
	public void addSoilHorizon(String description, double thickness, double pwp, double fc, double sat, double kSat) {
		horizons.add(new SoilHorizon(description, thickness, pwp, fc, sat, kSat));
	}
	
	public Collection<SoilHorizon> getSoilHorizons() {
		return horizons;
	}
	
	public int getCurveNumber() {
		return curveNumber;
	}
	
	/**
	 * Readily evaporable water from top layer (mm).
	 * 
	 * @return
	 */
	public int getEvaporableWater() {
		return evaporableWater;
	}
	
	/**
	 * Depth (m) of restrictive soil layer inhibiting root zone expansion.
	 * 
	 * @return
	 */
	public int getRestrictiveSoilLayerDepth() {
		return restrictiveSoilLayerDepth;
	}
	
	public static class SoilHorizon implements Serializable {
		
		private static final long serialVersionUID = -3578882836751152658L;
		
		private String description;
		private double thickness;
		private double pwp;
		private double fc;
		private double sat;
		private double kSat;
		private double cra;
		private double crb;
		
		public SoilHorizon(String description, double thickness, double pwp, double fc, double sat, double kSat) {
			super();
			this.description = description;
			this.thickness = thickness;
			this.pwp = pwp;
			this.fc = fc;
			this.sat = sat;
			this.kSat = kSat;
		}

		public String getDescription() {
			return description;
		}

		/**
		 * Thickness (m).
		 * 
		 * @return
		 */
		public double getThickness() {
			return thickness;
		}

		public double getPwp() {
			return pwp;
		}

		public double getFc() {
			return fc;
		}

		public double getSat() {
			return sat;
		}

		public double getkSat() {
			return kSat;
		}

		public double getCra() {
			return cra;
		}

		public void setCra(double cra) {
			this.cra = cra;
		}

		public double getCrb() {
			return crb;
		}

		public void setCrb(double crb) {
			this.crb = crb;
		}
	}
	
}
