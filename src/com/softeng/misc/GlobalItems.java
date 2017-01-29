package com.softeng.misc;

import java.util.Calendar;
import java.util.Date;

public final class GlobalItems {
	// This class holds all generic 'public static' methods and variables.

	public static User currentUser;
	public static final String[] HOTEL_NAMES = { "Athens", "Crete", "Mykonos", "Thessaloniki", "Patra" };

	private GlobalItems() {} // Cannot be instantiated.

	public static int diffInDays(Date d1, Date d2) {

		// This method returns the difference in days between 2 date objects.

		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
			return Math.abs(c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR)) + 1;
		else {
			if (c2.get(Calendar.YEAR) > c1.get(Calendar.YEAR)) {
				// Swap
				Calendar temp = c1;
				c1 = c2;
				c2 = temp;
			}
			int extraDays = 0;

			int dayOneOriginalYearDays = c1.get(Calendar.DAY_OF_YEAR);

			while (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
				c1.add(Calendar.YEAR, -1);
				// getActualMaximum() important for leap years.
				extraDays += c1.getActualMaximum(Calendar.DAY_OF_YEAR);
			}

			return extraDays - c2.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays + 1;
		}
	}
}
