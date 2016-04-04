package org.uncertweb.aquacrop.data;

import java.io.Serializable;
import java.nio.file.Path;

import org.joda.time.LocalDate;

/**
 * 
 * @author Richard Jones
 *
 */

public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;

	private LocalDate simulationPeriodStart; // First day of simulation period
	private LocalDate simulationPeriodEnd; // Last day of simulation period
	private LocalDate croppingPeriodStart; // First day of cropping period 
	private LocalDate croppingPeriodEnd; // Last day of cropping period 

	private int evaporationDeclineFactor = 4; // Evaporation decline factor for stage II
	private double evaporationCoefficient; // Ke(x) Soil evaporation coefficient for fully wet and non-shaded soil surface
	private int threshold; // Threshold for green CC below which HI can no longer increase (% cover)
	private int rootStartDepth; // Starting depth of root zone expansion curve (% of Zmin)
	private double rootMax; // Maximum allowable root zone expansion (fixed at 5 cm/day)
	private int rootShapeFactor; // Shape factor for effect water stress on root zone expansion
	private int soilWaterContent; // Required soil water content in top soil for germination (% TAW)
	private double soilAdjustmentFactor; // Adjustment factor for FAO-adjustment soil water depletion (p) by ETo
	private int deficientAerationDays; // Number of days after which deficient aeration is fully effective
	private double senescenceFactorExponent; // Exponent of senescence factor adjusting drop in photosynthetic activity of dying crop
	private int senescenceDecrease; // Decrease of p(sen) once early canopy senescence is triggered (% of p(sen))
	private int soilProfileDepth; // Depth [cm] of soil profile affected by water extraction by soil evaporation
	private double consideredSoilProfileDepth; // Considered depth (m) of soil profile for calculation of mean soil water content for CN adjustment
	private boolean antecedentAdjust = true; // CN is adjusted to Antecedent Moisture Class
	private double defaultMinTemperature = 12.0; // Default minimum temperature (degC) if no temperature file is specified
	private double defaultMaxTemperature = 28.0; // Default maximum temperature (degC) if no temperature file is specified
	private int defaultGrowingMethod = 3; // Default method for the calculation of growing degree days
	
	private ClimateCharacteristics climateCharacteristics;
	private CropCharacteristics cropCharacteristics;
	private SoilCharacteristics soilCharacteristics;

	private Path cropCharacteristicsFile = null;
	private Path soilCharacteristicsFile = null;

	public Project(ClimateCharacteristics climateCharacteristics, CropCharacteristics cropCharacteristics, SoilCharacteristics soilCharacteristics) {
		this.climateCharacteristics = climateCharacteristics;
		this.cropCharacteristics = cropCharacteristics;
		this.soilCharacteristics = soilCharacteristics;
	}
	
	public ClimateCharacteristics getClimateCharacteristics() {
		return climateCharacteristics;
	}	
	
	public CropCharacteristics getCropCharacteristics() {
		return cropCharacteristics;
	}	
	
	public SoilCharacteristics getSoilCharacteristics() {
		return soilCharacteristics;
	}

	public void setCropCharacteristicsFile(Path cropCharacteristicsFile) {
		this.cropCharacteristicsFile = cropCharacteristicsFile;
	}

	public Path getCropCharacteristicsFile() {
		return cropCharacteristicsFile;
	}

	public void setSoilCharacteristicsFile(Path soilCharacteristicsFile) {
		this.soilCharacteristicsFile = soilCharacteristicsFile;
	}

	public Path getSoilCharacteristicsFile() {
		return soilCharacteristicsFile;
	}

	/**
	 * AUTO GENERATED BELOW!
	 * @return
	 */
	
	public String getTitle() {
		return title;
	}

	public LocalDate getSimulationPeriodStart() {
		return simulationPeriodStart;
	}

	public LocalDate getSimulationPeriodEnd() {
		return simulationPeriodEnd;
	}

	public LocalDate getCroppingPeriodStart() {
		return croppingPeriodStart;
	}

	public LocalDate getCroppingPeriodEnd() {
		return croppingPeriodEnd;
	}

	public int getEvaporationDeclineFactor() {
		return evaporationDeclineFactor;
	}

	public double getEvaporationCoefficient() {
		return evaporationCoefficient;
	}

	public int getThreshold() {
		return threshold;
	}

	public int getRootStartDepth() {
		return rootStartDepth;
	}

	public double getRootMax() {
		return rootMax;
	}

	public int getRootShapeFactor() {
		return rootShapeFactor;
	}

	public int getSoilWaterContent() {
		return soilWaterContent;
	}

	public double getSoilAdjustmentFactor() {
		return soilAdjustmentFactor;
	}

	public int getDeficientAerationDays() {
		return deficientAerationDays;
	}

	public double getSenescenceFactorExponent() {
		return senescenceFactorExponent;
	}

	public int getSenescenceDecrease() {
		return senescenceDecrease;
	}

	public int getSoilProfileDepth() {
		return soilProfileDepth;
	}

	public double getConsideredSoilProfileDepth() {
		return consideredSoilProfileDepth;
	}

	public boolean isAntecedentAdjust() {
		return antecedentAdjust;
	}

	public double getDefaultMinTemperature() {
		return defaultMinTemperature;
	}

	public double getDefaultMaxTemperature() {
		return defaultMaxTemperature;
	}

	public int getDefaultGrowingMethod() {
		return defaultGrowingMethod;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSimulationPeriodStart(LocalDate simulationPeriodStart) {
		this.simulationPeriodStart = simulationPeriodStart;
	}

	public void setSimulationPeriodEnd(LocalDate simulationPeriodEnd) {
		this.simulationPeriodEnd = simulationPeriodEnd;
	}

	public void setCroppingPeriodStart(LocalDate croppingPeriodStart) {
		this.croppingPeriodStart = croppingPeriodStart;
	}

	public void setCroppingPeriodEnd(LocalDate croppingPeriodEnd) {
		this.croppingPeriodEnd = croppingPeriodEnd;
	}

	public void setEvaporationCoefficient(double evaporationCoefficient) {
		this.evaporationCoefficient = evaporationCoefficient;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setRootStartDepth(int rootStartDepth) {
		this.rootStartDepth = rootStartDepth;
	}

	public void setRootMax(double rootMax) {
		this.rootMax = rootMax;
	}

	public void setRootShapeFactor(int rootShapeFactor) {
		this.rootShapeFactor = rootShapeFactor;
	}

	public void setSoilWaterContent(int soilWaterContent) {
		this.soilWaterContent = soilWaterContent;
	}

	public void setSoilAdjustmentFactor(double soilAdjustmentFactor) {
		this.soilAdjustmentFactor = soilAdjustmentFactor;
	}

	public void setDeficientAerationDays(int deficientAerationDays) {
		this.deficientAerationDays = deficientAerationDays;
	}

	public void setSenescenceFactorExponent(double senescenceFactorExponent) {
		this.senescenceFactorExponent = senescenceFactorExponent;
	}

	public void setSenescenceDecrease(int senescenceDecrease) {
		this.senescenceDecrease = senescenceDecrease;
	}

	public void setSoilProfileDepth(int soilProfileDepth) {
		this.soilProfileDepth = soilProfileDepth;
	}

	public void setConsideredSoilProfileDepth(double consideredSoilProfileDepth) {
		this.consideredSoilProfileDepth = consideredSoilProfileDepth;
	}

}
