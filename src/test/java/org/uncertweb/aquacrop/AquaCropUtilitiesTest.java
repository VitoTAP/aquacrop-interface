package org.uncertweb.aquacrop;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

public class AquaCropUtilitiesTest {

	/**
	 * Using example (from http://www.fao.org/nr/water/docs/AquaCropPlugInV31plus.pdf)
	 * 
	 * For 24 August 1982:
	 * 	1. Subtract 1901 from the year 1982 - 1901 = 81 
	 * 	2. Multiply by 365.25 81 x 365.25 = 29585.25
	 * 	3. Add 212.25 for August 29585.25 + 212.25 = 29797.5
	 * 	4. Add the number of the day 29797.5 + 24 = 29821.5
	 * 	5. Take the integer  29821
	 * 
	 */
	@Test
	public void testConvertDateToInt() {
		LocalDate date = new LocalDate(1982, 8, 24);
		Assert.assertEquals(29821, AquaCropUtilities.convertDateToInt(date));
	}
	
}
