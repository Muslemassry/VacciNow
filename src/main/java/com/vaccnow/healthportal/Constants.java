package com.vaccnow.healthportal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaccnow.healthportal.utils.Util;

public class Constants {
	
	
	
	public static String APPLICATION_STATUS_APPLIED = "A";
	public static String APPLICATION_STATUS_AVAILABLE = "V";
	public static String APPLICATION_STATUS_CONFIRMED = "C";
	
	public static String PAYMENT_METHOD_CASH = "C";
	public static String PAYMENT_METHOD_CARD = "B"; // B for bank :)
	public static String PAYMENT_METHOD_FAWRY = "F";
	
	public static List<Date> TIMES_SLOTS;
	
	static {
		Date start = Util.atStartOfDay(new Date());
		Date end = Util.atEndOfDay(new Date());
		List<Date> slices = new ArrayList();
		var count = 0;

		while (end.getTime() >= start.getTime()) {
		  start = new Date(start.getTime() + (15 * 60 * 1000));
		  slices.add(start);

		}
		
		TIMES_SLOTS = slices;
	}
}
