package org.uncertweb.aquacrop.data;

import java.io.Serializable;

public class CropCharacteristics implements Serializable {
	
	private static final long serialVersionUID = -2388071923372171201L;
	
	private int cropType = 2; // fruit/grain producing
	private boolean sown = true; // crop is sown
	private int cropCycle = 0; // determination of crop cycle: by growing degree-days
	private boolean depletionFactorsAdjusted = true; // soil water depletion factors (p) are adjusted by ETo
	
	private double baseTemperature; // Base	temperature	(degC)	below	which	crop	development	does	not	progress
	private double upperTemperature; // Upper	temperature	(degC)	above	which	crop	development	no	longer	increases	with	an	increase	in	temperature	
	private int cropCycleLength; // Total	length	of	crop	cycle	in	growing	degree-days
	private double canopyDepletionFactorUpper; // Soil	water	depletion	factor	for	canopy	expansion	(p-exp)	-	Upper	threshold					
	private double canopyDepletionFactorLower; // Soil	water	depletion	factor	for	canopy	expansion	(p-exp)	-	Lower	threshold					
	private double canopyShapeFactor; // Shape	factor	for	water	stress	coefficient	for	canopy	expansion	(0.0	=	straight	line)
	private double stomatalDepletionFraction; // Soil	water	depletion	fraction	for	stomatal	control	(p	-	sto)	-	Upper	threshold			
	private double stomatalShapeFactor; // Shape	factor	for	water	stress	coefficient	for	stomatal	control	(0.0	=	straight	line)			
	private double senescenceDepletionFactorUpper; // Soil	water	depletion	factor	for	canopy	senescence	(p	-	sen)	-	Upper	threshold			
	private double senescenceShapeFactor; // Shape	factor	for	water	stress	coefficient	for	canopy	senescence	(0.0	=	straight	line)			
	private int senescenceThreshold; // Sum(ETo)	during	stress	period	to	be	exceeded	before	senescence	is	triggered					
	private double pollinationDepletionFactorUpper; //	Soil	water	depletion	factor	for	pollination	(p	-	pol)	-	Upper	threshold				
	private int aerationAnaerobioticPoint; // Vol%	for	Anaerobiotic	point	(*	(SAT	-	[vol%])	at	which	deficient	aeration	occurs	*)		
	private int soilFertilityStress; // Soil	fertility	stress	at	calibration	(%)										
	private int canopyResponseShapeFactor; // Shape	factor	for	the	response	of	canopy	expansion	for	limited	soil	fertility				
	private int maximumCanopyResponseShapeFactor; // Shape	factor	for	the	response	of	maximum	canopy	cover	for	limited	soil	fertility			
	private int waterProductivityShapeFactor; // Shape	factor	for	the	response	of	crop	Water	Productivity	for	limited	soil	fertility			
	private int canopyDeclineShapeFactor; // Shape	factor	for	the	response	of	decline	of	canopy	cover	for	limited	soil	fertility		
	private int pollinationFailMinTemp; // Minimum	air	temperature	below	which	pollination	starts	to	fail	(cold	stress)	(degC)
	private int pollinationFailMaxTemp; // Maximum	air	temperature	above	which	pollination	starts	to	fail	(heat	stress)	(degC)				
	private double biomassMinTemp; // Minimum	growing	degrees	required	for	full	biomass	production	(degC	-	day)					
	private double canopyCompleteCoefficient; // Crop	coefficient	when	canopy	is	complete	but	prior	to	senescence	(Kcb,x)					
	private double cropCoefficientDecline; // Decline	of	crop	coefficient	(%/day)	as	a	result	of	ageing,	nitrogen	deficiency,	etc.			
	private double minRootingDepth; // Minimum	effective	rooting	depth	(m)											
	private double maxRootingDepth; // Maximum	effective	rooting	depth	(m)											
	private int rootZoneShapeFactor; // Shape	factor	describing	root	zone	expansion										
	private double maxRootWaterExtractionTop; // Maximum	root	water	extraction	(m3water/m3soil.day)	in	top	quarter	of	root	zone					
	private double maxRootWaterExtractionBottom; // Maximum	root	water	extraction	(m3water/m3soil.day)	in	bottom	quarter	of	root	zone					
	private int canopyEffect; // Effect	of	canopy	cover	in	reducing	soil	evaporation	in	late	season	stage				
	private double seedlingCoverage; // Soil	surface	covered	by	an	individual	seedling	at	90	%	emergence	(cm2)				
	private int numPlants; // Number	of	plants	per	hectare											
	private double canopyGrowthCoefficient; // Canopy	growth	coefficient	(CGC):	Increase	in	canopy	cover	(fraction	soil	cover	per	day)
	
	private int canopyCoefficientDecrease = -9; // Maximum	decrease	of	Canopy	Growth	Coefficient	in	and	between	seasons	-	Not	Applicable			
	private int canopyCoefficientSeasons = -9; //	Number	of	seasons	at	which	maximum	decrease	of	Canopy	Growth	Coefficient	is	reached	-	Not	Applicable
	private int canopyCoefficientShapeFactor = -9; // Shape	factor	for	decrease	Canopy	Growth	Coefficient	-	Not	Applicable						
	
	private double maxCanopyCover; // Maximum	canopy	cover	(CCx)	in	fraction	soil	cover								
	private double canopyDeclineCoefficient; // Canopy	decline	coefficient	(CDC):	Decrease	in	canopy	cover	(in	fraction	per	day)				
	private int sowingToEmergence; // Calendar	Days:	from	sowing	to	emergence										
	private int sowingToMaxRooting; // Calendar	Days:	from	sowing	to	maximum	rooting	depth								
	private int sowingToSenescence; // Calendar	Days:	from	sowing	to	start	senescence									
	private int sowingToMaturity; // Calendar	Days:	from	sowing	to	maturity	(length	of	crop	cycle)						
	private int sowingToFlowering; // Calendar	Days:	from	sowing	to	flowering										
	private int floweringLength; // Length	of	the	flowering	stage	(days)		
	
	private int cropDeterminancy = 0; // Crop	determinancy	unlinked	with	flowering											
	private int potentialFruitsExcess = 100; // Excess	of	potential	fruits	(%)			
	
	private int harvestIndexDays; // Building	up	of	Harvest	Index	starting	at	flowering	(days)							
	private double waterProductivity; // Water	Productivity	normalized	for	ETo	and	CO2	(WP*)	(gram/m2)				
	
	private int yieldWaterProductivity = 100; // Water	Productivity	normalized	for	ETo	and	CO2	during	yield	formation	(as	%	WP*)			
	private int referenceHarvestIndex = 100; // Reference	Harvest	Index	(HIo)	(%)											
	
	private int waterStressIncrease; // Possible	increase	(%)	of	HI	due	to	water	stress	before	flowering					
	private double positiveHiImpactCoefficient;  // Coefficient	describing	positive	impact	on	HI	of	restricted	vegetative	growth	during	yield	formation			
	private double negativeHiImpactCoefficient; // Coefficient	describing	negative	impact	on	HI	of	stomatal	closure	during	yield	formation				
	private int hiMaxIncrease; // Allowable	maximum	increase	(%)	of	specified	HI				
	
	private int gdSowingToEmergence = 180; // GDDays:	from	sowing	to	emergence											
	private int gdSowingToMaxRooting = 1349; // GDDays:	from	sowing	to	maximum	rooting	depth									
	private int gdSowingToSenescence = 1324; // GDDays:	from	sowing	to	start	senescence										
	private int gdSowingToMaturity = 2413; // GDDays:	from	sowing	to	maturity	(length	of	crop	cycle)							
	private int gdSowingToFlowering = 1330; // GDDays:	from	sowing	to	flowering											
	private int gdFloweringStageLength = 150; // Length	of	the	flowering	stage	(growing	degree	days)	
	
	private double canopyCoverIncrease; // CGC	for	GGDays:	Increase	in	canopy	cover	(in	fraction	soil	cover	per	growing-degree	day)		
	private double canopyCoverDecrease; // CDC	for	GGDays:	Decrease	in	canopy	cover	(in	fraction	per	growing-degree	day)	
	
	private int gdHarvestIndexBuildUp = 900; // GDDays:	building-up	of	Harvest	Index	during	yield	formation	
	
	public CropCharacteristics() {
		
	}

	public int getCropType() {
		return cropType;
	}

	public boolean isSown() {
		return sown;
	}

	public int getCropCycle() {
		return cropCycle;
	}

	public boolean isDepletionFactorsAdjusted() {
		return depletionFactorsAdjusted;
	}

	public double getBaseTemperature() {
		return baseTemperature;
	}

	public double getUpperTemperature() {
		return upperTemperature;
	}

	public int getCropCycleLength() {
		return cropCycleLength;
	}

	public double getCanopyDepletionFactorUpper() {
		return canopyDepletionFactorUpper;
	}

	public double getCanopyDepletionFactorLower() {
		return canopyDepletionFactorLower;
	}

	public double getCanopyShapeFactor() {
		return canopyShapeFactor;
	}

	public double getStomatalDepletionFraction() {
		return stomatalDepletionFraction;
	}

	public double getStomatalShapeFactor() {
		return stomatalShapeFactor;
	}

	public double getSenescenceDepletionFactorUpper() {
		return senescenceDepletionFactorUpper;
	}

	public double getSenescenceShapeFactor() {
		return senescenceShapeFactor;
	}

	public int getSenescenceThreshold() {
		return senescenceThreshold;
	}

	public double getPollinationDepletionFactorUpper() {
		return pollinationDepletionFactorUpper;
	}

	public int getAerationAnaerobioticPoint() {
		return aerationAnaerobioticPoint;
	}

	public int getSoilFertilityStress() {
		return soilFertilityStress;
	}

	public int getCanopyResponseShapeFactor() {
		return canopyResponseShapeFactor;
	}

	public int getMaximumCanopyResponseShapeFactor() {
		return maximumCanopyResponseShapeFactor;
	}

	public int getWaterProductivityShapeFactor() {
		return waterProductivityShapeFactor;
	}

	public int getCanopyDeclineShapeFactor() {
		return canopyDeclineShapeFactor;
	}

	public int getPollinationFailMinTemp() {
		return pollinationFailMinTemp;
	}

	public int getPollinationFailMaxTemp() {
		return pollinationFailMaxTemp;
	}

	public double getBiomassMinTemp() {
		return biomassMinTemp;
	}

	public double getCanopyCompleteCoefficient() {
		return canopyCompleteCoefficient;
	}

	public double getCropCoefficientDecline() {
		return cropCoefficientDecline;
	}

	public double getMinRootingDepth() {
		return minRootingDepth;
	}

	public double getMaxRootingDepth() {
		return maxRootingDepth;
	}

	public int getRootZoneShapeFactor() {
		return rootZoneShapeFactor;
	}

	public double getMaxRootWaterExtractionTop() {
		return maxRootWaterExtractionTop;
	}

	public double getMaxRootWaterExtractionBottom() {
		return maxRootWaterExtractionBottom;
	}

	public int getCanopyEffect() {
		return canopyEffect;
	}

	public double getSeedlingCoverage() {
		return seedlingCoverage;
	}

	public int getNumPlants() {
		return numPlants;
	}

	public double getCanopyGrowthCoefficient() {
		return canopyGrowthCoefficient;
	}

	public int getCanopyCoefficientDecrease() {
		return canopyCoefficientDecrease;
	}

	public int getCanopyCoefficientSeasons() {
		return canopyCoefficientSeasons;
	}

	public int getCanopyCoefficientShapeFactor() {
		return canopyCoefficientShapeFactor;
	}

	public double getMaxCanopyCover() {
		return maxCanopyCover;
	}

	public double getCanopyDeclineCoefficient() {
		return canopyDeclineCoefficient;
	}

	public int getSowingToEmergence() {
		return sowingToEmergence;
	}

	public int getSowingToMaxRooting() {
		return sowingToMaxRooting;
	}

	public int getSowingToSenescence() {
		return sowingToSenescence;
	}

	public int getSowingToMaturity() {
		return sowingToMaturity;
	}

	public int getSowingToFlowering() {
		return sowingToFlowering;
	}

	public int getFloweringLength() {
		return floweringLength;
	}

	public int getCropDeterminancy() {
		return cropDeterminancy;
	}

	public int getPotentialFruitsExcess() {
		return potentialFruitsExcess;
	}

	public int getHarvestIndexDays() {
		return harvestIndexDays;
	}

	public double getWaterProductivity() {
		return waterProductivity;
	}

	public int getYieldWaterProductivity() {
		return yieldWaterProductivity;
	}

	public int getReferenceHarvestIndex() {
		return referenceHarvestIndex;
	}

	public int getWaterStressIncrease() {
		return waterStressIncrease;
	}

	public double getPositiveHiImpactCoefficient() {
		return positiveHiImpactCoefficient;
	}

	public double getNegativeHiImpactCoefficient() {
		return negativeHiImpactCoefficient;
	}

	public int getHiMaxIncrease() {
		return hiMaxIncrease;
	}

	public int getGdSowingToEmergence() {
		return gdSowingToEmergence;
	}

	public int getGdSowingToMaxRooting() {
		return gdSowingToMaxRooting;
	}

	public int getGdSowingToSenescence() {
		return gdSowingToSenescence;
	}

	public int getGdSowingToMaturity() {
		return gdSowingToMaturity;
	}

	public int getGdSowingToFlowering() {
		return gdSowingToFlowering;
	}

	public int getGdFloweringStageLength() {
		return gdFloweringStageLength;
	}

	public double getCanopyCoverIncrease() {
		return canopyCoverIncrease;
	}

	public double getCanopyCoverDecrease() {
		return canopyCoverDecrease;
	}
	
	public void setCropDeterminancy(int cropDeterminancy) {
		this.cropDeterminancy = cropDeterminancy;
	}

	public int getGdHarvestIndexBuildUp() {
		return gdHarvestIndexBuildUp;
	}

	public void setBaseTemperature(double baseTemperature) {
		this.baseTemperature = baseTemperature;
	}

	public void setUpperTemperature(double upperTemperature) {
		this.upperTemperature = upperTemperature;
	}

	public void setCropCycleLength(int cropCycleLength) {
		this.cropCycleLength = cropCycleLength;
	}

	public void setCanopyDepletionFactorUpper(double canopyDepletionFactorUpper) {
		this.canopyDepletionFactorUpper = canopyDepletionFactorUpper;
	}

	public void setCanopyDepletionFactorLower(double canopyDepletionFactorLower) {
		this.canopyDepletionFactorLower = canopyDepletionFactorLower;
	}

	public void setCanopyShapeFactor(double canopyShapeFactor) {
		this.canopyShapeFactor = canopyShapeFactor;
	}

	public void setStomatalDepletionFraction(double stomatalDepletionFraction) {
		this.stomatalDepletionFraction = stomatalDepletionFraction;
	}

	public void setStomatalShapeFactor(double stomatalShapeFactor) {
		this.stomatalShapeFactor = stomatalShapeFactor;
	}

	public void setSenescenceDepletionFactorUpper(double senescenceDepletionFactorUpper) {
		this.senescenceDepletionFactorUpper = senescenceDepletionFactorUpper;
	}

	public void setSenescenceShapeFactor(double senescenceShapeFactor) {
		this.senescenceShapeFactor = senescenceShapeFactor;
	}

	public void setSenescenceThreshold(int senescenceThreshold) {
		this.senescenceThreshold = senescenceThreshold;
	}

	public void setPollinationDepletionFactorUpper(double pollinationDepletionFactorUpper) {
		this.pollinationDepletionFactorUpper = pollinationDepletionFactorUpper;
	}

	public void setAerationAnaerobioticPoint(int aerationAnaerobioticPoint) {
		this.aerationAnaerobioticPoint = aerationAnaerobioticPoint;
	}

	public void setSoilFertilityStress(int soilFertilityStress) {
		this.soilFertilityStress = soilFertilityStress;
	}

	public void setCanopyResponseShapeFactor(int canopyResponseShapeFactor) {
		this.canopyResponseShapeFactor = canopyResponseShapeFactor;
	}

	public void setMaximumCanopyResponseShapeFactor(int maximumCanopyResponseShapeFactor) {
		this.maximumCanopyResponseShapeFactor = maximumCanopyResponseShapeFactor;
	}

	public void setWaterProductivityShapeFactor(int waterProductivityShapeFactor) {
		this.waterProductivityShapeFactor = waterProductivityShapeFactor;
	}

	public void setCanopyDeclineShapeFactor(int canopyDeclineShapeFactor) {
		this.canopyDeclineShapeFactor = canopyDeclineShapeFactor;
	}

	public void setPollinationFailMinTemp(int pollinationFailMinTemp) {
		this.pollinationFailMinTemp = pollinationFailMinTemp;
	}

	public void setPollinationFailMaxTemp(int pollinationFailMaxTemp) {
		this.pollinationFailMaxTemp = pollinationFailMaxTemp;
	}

	public void setBiomassMinTemp(double biomassMinTemp) {
		this.biomassMinTemp = biomassMinTemp;
	}

	public void setCanopyCompleteCoefficient(double canopyCompleteCoefficient) {
		this.canopyCompleteCoefficient = canopyCompleteCoefficient;
	}

	public void setCropCoefficientDecline(double cropCoefficientDecline) {
		this.cropCoefficientDecline = cropCoefficientDecline;
	}

	public void setMinRootingDepth(double minRootingDepth) {
		this.minRootingDepth = minRootingDepth;
	}

	public void setMaxRootingDepth(double maxRootingDepth) {
		this.maxRootingDepth = maxRootingDepth;
	}

	public void setRootZoneShapeFactor(int rootZoneShapeFactor) {
		this.rootZoneShapeFactor = rootZoneShapeFactor;
	}

	public void setMaxRootWaterExtractionTop(double maxRootWaterExtractionTop) {
		this.maxRootWaterExtractionTop = maxRootWaterExtractionTop;
	}

	public void setMaxRootWaterExtractionBottom(double maxRootWaterExtractionBottom) {
		this.maxRootWaterExtractionBottom = maxRootWaterExtractionBottom;
	}

	public void setCanopyEffect(int canopyEffect) {
		this.canopyEffect = canopyEffect;
	}

	public void setSeedlingCoverage(double seedlingCoverage) {
		this.seedlingCoverage = seedlingCoverage;
	}

	public void setNumPlants(int numPlants) {
		this.numPlants = numPlants;
	}

	public void setCanopyGrowthCoefficient(double canopyGrowthCoefficient) {
		this.canopyGrowthCoefficient = canopyGrowthCoefficient;
	}

	public void setMaxCanopyCover(double maxCanopyCover) {
		this.maxCanopyCover = maxCanopyCover;
	}

	public void setCanopyDeclineCoefficient(double canopyDeclineCoefficient) {
		this.canopyDeclineCoefficient = canopyDeclineCoefficient;
	}

	public void setSowingToEmergence(int sowingToEmergence) {
		this.sowingToEmergence = sowingToEmergence;
	}

	public void setSowingToMaxRooting(int sowingToMaxRooting) {
		this.sowingToMaxRooting = sowingToMaxRooting;
	}

	public void setSowingToSenescence(int sowingToSenescence) {
		this.sowingToSenescence = sowingToSenescence;
	}

	public void setSowingToMaturity(int sowingToMaturity) {
		this.sowingToMaturity = sowingToMaturity;
	}

	public void setSowingToFlowering(int sowingToFlowering) {
		this.sowingToFlowering = sowingToFlowering;
	}

	public void setFloweringLength(int floweringLength) {
		this.floweringLength = floweringLength;
	}

	public void setHarvestIndexDays(int harvestIndexDays) {
		this.harvestIndexDays = harvestIndexDays;
	}

	public void setWaterProductivity(double waterProductivity) {
		this.waterProductivity = waterProductivity;
	}

	public void setReferenceHarvestIndex(int referenceHarvestIndex) {
		this.referenceHarvestIndex = referenceHarvestIndex;
	}

	public void setWaterStressIncrease(int waterStressIncrease) {
		this.waterStressIncrease = waterStressIncrease;
	}

	public void setPositiveHiImpactCoefficient(double positiveHiImpactCoefficient) {
		this.positiveHiImpactCoefficient = positiveHiImpactCoefficient;
	}

	public void setNegativeHiImpactCoefficient(double negativeHiImpactCoefficient) {
		this.negativeHiImpactCoefficient = negativeHiImpactCoefficient;
	}

	public void setHiMaxIncrease(int hiMaxIncrease) {
		this.hiMaxIncrease = hiMaxIncrease;
	}

	public void setCropType(int cropType) {
		this.cropType = cropType;
	}

	public void setSown(boolean sown) {
		this.sown = sown;
	}

	public void setCropCycle(int cropCycle) {
		this.cropCycle = cropCycle;
	}

	public void setDepletionFactorsAdjusted(boolean depletionFactorsAdjusted) {
		this.depletionFactorsAdjusted = depletionFactorsAdjusted;
	}

	public void setCanopyCoefficientDecrease(int canopyCoefficientDecrease) {
		this.canopyCoefficientDecrease = canopyCoefficientDecrease;
	}

	public void setCanopyCoefficientSeasons(int canopyCoefficientSeasons) {
		this.canopyCoefficientSeasons = canopyCoefficientSeasons;
	}

	public void setCanopyCoefficientShapeFactor(int canopyCoefficientShapeFactor) {
		this.canopyCoefficientShapeFactor = canopyCoefficientShapeFactor;
	}

	public void setPotentialFruitsExcess(int potentialFruitsExcess) {
		this.potentialFruitsExcess = potentialFruitsExcess;
	}

	public void setYieldWaterProductivity(int yieldWaterProductivity) {
		this.yieldWaterProductivity = yieldWaterProductivity;
	}

	public void setGdSowingToEmergence(int gdSowingToEmergence) {
		this.gdSowingToEmergence = gdSowingToEmergence;
	}

	public void setGdSowingToMaxRooting(int gdSowingToMaxRooting) {
		this.gdSowingToMaxRooting = gdSowingToMaxRooting;
	}

	public void setGdSowingToSenescence(int gdSowingToSenescence) {
		this.gdSowingToSenescence = gdSowingToSenescence;
	}

	public void setGdSowingToMaturity(int gdSowingToMaturity) {
		this.gdSowingToMaturity = gdSowingToMaturity;
	}

	public void setGdSowingToFlowering(int gdSowingToFlowering) {
		this.gdSowingToFlowering = gdSowingToFlowering;
	}

	public void setGdFloweringStageLength(int gdFloweringStageLength) {
		this.gdFloweringStageLength = gdFloweringStageLength;
	}

	public void setGdHarvestIndexBuildUp(int gdHarvestIndexBuildUp) {
		this.gdHarvestIndexBuildUp = gdHarvestIndexBuildUp;
	}

	public void setCanopyCoverIncrease(double canopyCoverIncrease) {
		this.canopyCoverIncrease = canopyCoverIncrease;
	}

	public void setCanopyCoverDecrease(double canopyCoverDecrease) {
		this.canopyCoverDecrease = canopyCoverDecrease;
	}
	
}
