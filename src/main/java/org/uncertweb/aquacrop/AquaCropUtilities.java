package org.uncertweb.aquacrop;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class AquaCropUtilities {

	/**
	 * The method is valid from 1901 to 2099 only (time range in AquaCrop).
	 * 
	 * http://www.fao.org/nr/water/docs/AquaCropPlugInV31plus.pdf
	 * 
	 * @param date
	 * @return
	 */
	public static int convertDateToInt(LocalDate date) {
		// 1. Subtract 1901 from the year 
		int y = date.getYear() - 1901;

		// 2. Multiply by 365.25 
		double y2 = y * 365.25;

		// 3. According to the month add: 
		//		- January : 0 
		//		- February : 31 
		//		- March : 59.25 
		//		- April : 90.25 
		//		- May : 120.25 
		//		- June : 151.25 
		//		- July : 181.25 
		//		- August : 212.25 
		//		- September : 243.25 
		//		- October : 273.25 
		//		- November : 304.25 
		//		- December : 334.25 
		double m = 0;
		switch (date.getMonthOfYear()) { // 1-12
		case 2:
			m = 31;
			break;
		case 3:
			m = 59.25;
			break;
		case 4:
			m = 90.25;
			break;
		case 5:
			m = 120.25;
			break;
		case 6:
			m = 151.25;
			break;
		case 7:
			m = 181.25;
			break;
		case 8:
			m = 212.25;
			break;
		case 9:
			m = 243.25;
			break;
		case 10:
			m = 273.25;
			break;
		case 11:
			m = 304.25;
			break;
		case 12:
			m = 334.25;
			break;
		}

		// 4. Add the number of the day within the month 
		double d = y2 + m + date.getDayOfMonth();

		// 5. Take the integer 
		return (int) d;
	}
	
	public static String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		return formatter.print(date);
	}
	

}
