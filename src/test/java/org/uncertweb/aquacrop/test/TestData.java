package org.uncertweb.aquacrop.test;

import org.joda.time.LocalDate;
import org.uncertweb.aquacrop.data.ClimateCharacteristics;
import org.uncertweb.aquacrop.data.Co2Measurements;
import org.uncertweb.aquacrop.data.CropCharacteristics;
import org.uncertweb.aquacrop.data.EvapotranspirationMeasurements;
import org.uncertweb.aquacrop.data.Frequency;
import org.uncertweb.aquacrop.data.Project;
import org.uncertweb.aquacrop.data.RainfallMeasurements;
import org.uncertweb.aquacrop.data.SoilCharacteristics;
import org.uncertweb.aquacrop.data.TemperatureMeasurements;

public class TestData {

	public static Project getProject() {

		// crop characteristics
		CropCharacteristics cropCharacteristics = TestData.getCropCharacteristics();

		// soil data
		SoilCharacteristics soilCharacteristics = TestData.getSoilCharacteristics();

		// climate data
		ClimateCharacteristics climateCharacteristics = TestData.getClimateCharacteristics();

		// set files
		Project project = new Project(climateCharacteristics, cropCharacteristics, soilCharacteristics);
		project.setTitle("a test project");
		project.setEvaporationCoefficient(1.1);
		project.setThreshold(5);
		project.setRootStartDepth(70);
		project.setRootMax(5);
		project.setRootShapeFactor(-6);
		project.setSoilWaterContent(20);
		project.setSoilAdjustmentFactor(1.0);
		project.setDeficientAerationDays(3);
		project.setSenescenceFactorExponent(1.0);
		project.setSenescenceDecrease(12);
		project.setSoilProfileDepth(30);
		project.setConsideredSoilProfileDepth(0.3);

		project.setCroppingPeriodStart(new LocalDate(2010, 10, 1));
		project.setCroppingPeriodEnd(new LocalDate(2011, 4, 25));
		project.setSimulationPeriodStart(new LocalDate(2010, 10, 1));
		project.setSimulationPeriodEnd(new LocalDate(2011, 4, 25));

		return project;
	}

	public static CropCharacteristics getCropCharacteristics() {
		CropCharacteristics cropCharacteristics = new CropCharacteristics();
		cropCharacteristics.setBaseTemperature(0);						
		cropCharacteristics.setUpperTemperature(26);
		cropCharacteristics.setCropCycleLength(3100);								
		cropCharacteristics.setCanopyDepletionFactorUpper(0.2);					
		cropCharacteristics.setCanopyDepletionFactorLower(0.65);					
		cropCharacteristics.setCanopyShapeFactor(5);			
		cropCharacteristics.setStomatalDepletionFraction(0.65);			
		cropCharacteristics.setStomatalShapeFactor(2.5);			
		cropCharacteristics.setSenescenceDepletionFactorUpper(0.7);			
		cropCharacteristics.setSenescenceShapeFactor(2.5);			
		cropCharacteristics.setSenescenceThreshold(0);					
		cropCharacteristics.setPollinationDepletionFactorUpper(0.85);				
		cropCharacteristics.setAerationAnaerobioticPoint(5);		
		cropCharacteristics.setSoilFertilityStress(50);										
		cropCharacteristics.setCanopyResponseShapeFactor(25);				
		cropCharacteristics.setMaximumCanopyResponseShapeFactor(25);			
		cropCharacteristics.setWaterProductivityShapeFactor(25);			
		cropCharacteristics.setCanopyDeclineShapeFactor(25);		
		cropCharacteristics.setPollinationFailMinTemp(5);				
		cropCharacteristics.setPollinationFailMaxTemp(35);				
		cropCharacteristics.setBiomassMinTemp(14);					
		cropCharacteristics.setCanopyCompleteCoefficient(1.1);					
		cropCharacteristics.setCropCoefficientDecline(0.15);			
		cropCharacteristics.setMinRootingDepth(0.3);											
		cropCharacteristics.setMaxRootingDepth(1.5);											
		cropCharacteristics.setRootZoneShapeFactor(15);										
		cropCharacteristics.setMaxRootWaterExtractionTop(0.028);					
		cropCharacteristics.setMaxRootWaterExtractionBottom(0.008);					
		cropCharacteristics.setCanopyEffect(50);				
		cropCharacteristics.setSeedlingCoverage(1.5);				
		cropCharacteristics.setNumPlants(2750000);											
		cropCharacteristics.setCanopyGrowthCoefficient(0.02955);			
		cropCharacteristics.setMaxCanopyCover(0.99);								
		cropCharacteristics.setCanopyDeclineCoefficient(0.064);				
		cropCharacteristics.setSowingToEmergence(17);										
		cropCharacteristics.setSowingToMaxRooting(218);								
		cropCharacteristics.setSowingToSenescence(302);									
		cropCharacteristics.setSowingToMaturity(326);						
		cropCharacteristics.setSowingToFlowering(254);		
		cropCharacteristics.setFloweringLength(11);	
		cropCharacteristics.setCropDeterminancy(1);
		cropCharacteristics.setReferenceHarvestIndex(50);
		cropCharacteristics.setGdSowingToEmergence(180);										
		cropCharacteristics.setGdSowingToMaxRooting(1451);								
		cropCharacteristics.setGdSowingToSenescence(2700);									
		cropCharacteristics.setGdSowingToMaturity(3100);
		cropCharacteristics.setGdSowingToFlowering(1950);
		cropCharacteristics.setGdFloweringStageLength(150);
		cropCharacteristics.setHarvestIndexDays(69);											
		cropCharacteristics.setWaterProductivity(15);																		
		cropCharacteristics.setWaterStressIncrease(5);					
		cropCharacteristics.setPositiveHiImpactCoefficient(10);			
		cropCharacteristics.setNegativeHiImpactCoefficient(7);				
		cropCharacteristics.setHiMaxIncrease(15);									
		cropCharacteristics.setCanopyCoverIncrease(0.004875);		
		cropCharacteristics.setCanopyCoverDecrease(0.004);
		cropCharacteristics.setGdHarvestIndexBuildUp(1100);
		return cropCharacteristics;
	}

	public static ClimateCharacteristics getClimateCharacteristics() {
		Co2Measurements co2Measurements = TestData.getCo2Measurements();
		EvapotranspirationMeasurements etMeasurements = TestData.getEvapotranspirationMeasurements();
		RainfallMeasurements rainMeasurements = TestData.getRainfallMeasurements();
		TemperatureMeasurements temperatureMeasurements = TestData.getTemperatureMeasurements();
		return new ClimateCharacteristics(rainMeasurements, etMeasurements, temperatureMeasurements, co2Measurements);
	}

	public static EvapotranspirationMeasurements getEvapotranspirationMeasurements() {
		EvapotranspirationMeasurements etMeasurements = new EvapotranspirationMeasurements(Frequency.Monthly, new LocalDate(2010, 10, 1));
		etMeasurements.addMeasurement(1.7096196723394828);
		etMeasurements.addMeasurement(2.638994122787746);
		etMeasurements.addMeasurement(2.023086316173484);
		etMeasurements.addMeasurement(0.26892653849271575);
		etMeasurements.addMeasurement(2.9956416430214032);
		etMeasurements.addMeasurement(3.406684345369567);
		etMeasurements.addMeasurement(0.7300740080467039);
		etMeasurements.addMeasurement(3.976752385055087);
		etMeasurements.addMeasurement(2.6549879851629705);
		etMeasurements.addMeasurement(3.0139295695848887);
		etMeasurements.addMeasurement(2.9930839281061146);
		etMeasurements.addMeasurement(3.2143320569004694);
		return etMeasurements;
	}

	public static RainfallMeasurements getRainfallMeasurements() {
		RainfallMeasurements measurements = new RainfallMeasurements(Frequency.Monthly, new LocalDate(2010, 10, 1));
		measurements.addMeasurement(95.2082);
		measurements.addMeasurement(96.2005);
		measurements.addMeasurement(37.5696);
		measurements.addMeasurement(80.8003);
		measurements.addMeasurement(39.738);
		measurements.addMeasurement(40.5688);
		measurements.addMeasurement(15.2445);
		measurements.addMeasurement(30.1283);
		measurements.addMeasurement(55.7843);
		measurements.addMeasurement(53.2885);
		measurements.addMeasurement(17.7116);
		measurements.addMeasurement(59.9832);
		return measurements;
	}

	public static Co2Measurements getCo2Measurements() {
		Co2Measurements measurements = new Co2Measurements();
		for (int year = 2000; year <= 3000; year++) {
			measurements.addMeasurement(year, 385.6);
		}
		return measurements;
	}

	public static TemperatureMeasurements getTemperatureMeasurements() {
		TemperatureMeasurements temperature = new TemperatureMeasurements(Frequency.Monthly, new LocalDate(2010, 10, 1));
		temperature.addMeasurement(4.986, 11.1358);
		temperature.addMeasurement(3.8317, 9.8577);
		temperature.addMeasurement(2.2424, 7.92);
		temperature.addMeasurement(0.3555, 6.8702);
		temperature.addMeasurement(1.902, 8.8972);
		temperature.addMeasurement(-0.097, 10.7114);
		temperature.addMeasurement(5.5985, 13.661);
		temperature.addMeasurement(6.4377, 17.1899);		
		temperature.addMeasurement(10.6154, 22.156);
		temperature.addMeasurement(11.9956, 20.6794);
		temperature.addMeasurement(11.8125, 22.177);
		temperature.addMeasurement(8.6726, 16.6998);
		return temperature;
	}

	public static SoilCharacteristics getSoilCharacteristics() {
		SoilCharacteristics soilCharacteristics = new SoilCharacteristics();
		soilCharacteristics.setEvaporableWater(11);
		soilCharacteristics.addSoilHorizon("706ARA", 0.341806, 19.096985, 22.9097, 48.99665, 87.6254);
		soilCharacteristics.addSoilHorizon("706ARE", 0.3839464, 19.34783, 20.10034, 37.157195, 468.3947);
		soilCharacteristics.addSoilHorizon("706ARBtg", 0.25, 19.3, 36.7, 48.5, 70);
		soilCharacteristics.addSoilHorizon("706ARBC", 0.75, 16.7, 30.6, 41.5, 33.4);
		return soilCharacteristics;
	}

}
