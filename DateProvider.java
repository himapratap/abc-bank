package com.abc.utilities;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateProvider {

	public static DateTime currentDate() {
		DateTime dt = new DateTime();
		return dt;

	}
	
	public static int getDaysBetween(DateTime d1, DateTime d2){
		if( d1 != null && d2 != null){
			return Days.daysBetween(d1.withTimeAtStartOfDay() , d2.withTimeAtStartOfDay() ).getDays() + 1 ;

		}
		
		return 0;
 	}

	 
	public static void main(String[] args) {
         System.out.println(getDaysBetween(new DateTime(2012, 2, 3, 0, 0, 0, 0), new DateTime(2012, 2, 7, 0, 0, 0, 0)));
	}
}
