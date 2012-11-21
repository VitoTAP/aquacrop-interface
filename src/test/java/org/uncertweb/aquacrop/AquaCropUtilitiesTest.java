package org.uncertweb.aquacrop;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

public class AquaCropUtilitiesTest {

	/**
	 * Test using example from AquaCrop documentation
	 * (http://www.fao.org/nr/water/docs/AquaCropPlugInV31plus.pdf).	 
	 */
	@Test
	public void convertDateToInt() {
		LocalDate date = new LocalDate(1982, 8, 24);
		Assert.assertEquals(29821, AquaCropUtilities.convertDateToInt(date));
	}
	
	@Test
	public void formatDate() {
		LocalDate date = new LocalDate(1982, 8, 8);
		String formatted = AquaCropUtilities.formatDate(date);
		Assert.assertEquals("08-08-1982", formatted);
	}
	
}
