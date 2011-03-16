package com.bidover.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateSets {

	private static final int MILLIS_IN_MIN = 60000;
	private static final int MILLIS_IN_HOUR = 3600000;
	private static final int MILLIS_IN_DAY = 86400000;
/*	private static final int[] TIME_FIELDS = {
	  Calendar.MILLISECOND,
	  Calendar.SECOND,
	  Calendar.MINUTE,
	  Calendar.HOUR,
	  Calendar.HOUR_OF_DAY,
	  Calendar.AM_PM
	};
	
	private static void reset(Calendar c) {
		for(int i : TIME_FIELDS) c.clear(i);
	}
*/	
    public String convDateToString(Date date, String pattern) {
    	final DateFormat dtFormatter = new SimpleDateFormat(pattern);
        return dtFormatter.format(date);
	}
    
    public String convMillsToDate(long mills, String pattern) {
    	final DateFormat dtFormatter = new SimpleDateFormat(pattern);
        return dtFormatter.format(new Date(mills));
	}
    
    public long setExpirationTime(int offsetLocal, int offsetServer, int duration) {
    	Calendar c = Calendar.getInstance();
    	long mst= System.currentTimeMillis();
    	long mlt = getLocalTime(offsetLocal, offsetServer);
    	long dif = mlt - mst;
    	c.setTimeInMillis(mlt);
 //   	reset(c);
 //   	for(int i : TIME_FIELDS) c.clear(i);
 //   	c.add(Calendar.MINUTE, duration);
    	c.add(Calendar.DAY_OF_YEAR, duration);
    	return c.getTimeInMillis() - dif;
    }
    
    public String getTimeLeft(long dif) {
//    	long dif = mills - System.currentTimeMillis();
    	String timeleft = "";
    	if (dif > 0) {
    		if (dif < MILLIS_IN_HOUR) {
    			int m = (int)dif/MILLIS_IN_MIN;
    			int s = (int)(dif - m*MILLIS_IN_MIN)/1000;
    			timeleft = m+"m "+s+"s";
    		}
    		else if (dif < MILLIS_IN_DAY) {
    			int h = (int)dif/MILLIS_IN_HOUR;
    			int m = (int)(dif - h*MILLIS_IN_HOUR)/MILLIS_IN_MIN;
    			timeleft = h+"h "+m+"m";
    		}
    		else {
    			int d = (int)dif/MILLIS_IN_DAY;
    			int h = (int)(dif - d*MILLIS_IN_DAY)/MILLIS_IN_HOUR;
    			int m = (int)(dif - ((int)dif/MILLIS_IN_HOUR)*MILLIS_IN_HOUR)/MILLIS_IN_MIN;
    			timeleft = d+"d "+h+"h "+m+"m";
    		}
    	}
        return timeleft;
	}
    
//	static final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
//    static final DateFormat timeFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//    static final DateFormat dtMySQLFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    static final DateFormat dateRegFormatter = new SimpleDateFormat("MMM dd, yyyy");
//    static final DateFormat dateUniFormatter = new SimpleDateFormat("dd MMM yyyy");
//    static final DateFormat timeCutFormatter = new SimpleDateFormat("HH:mm");
//    static final DateFormat datetimeFormatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");
	
	public boolean dateExpire(Date date) {
		return date.before(new Date());
	}
	
	public int getShift(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, days);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		if (day_of_week == 2) return 1;
//		if (day_of_week == 7) return 2;
		else return 0;
	}
	
	public int getDays(Date regDate) {
		Calendar curCalendar = Calendar.getInstance(); 
		curCalendar.setTime(new Date());
		
		Calendar regCalendar = Calendar.getInstance(); 
		regCalendar.setTime(regDate);
		long diff = curCalendar.getTimeInMillis() - regCalendar.getTimeInMillis();
//		long seconds = diff/1000; 
//		long minutes = seconds/60; 
//		long hours = minutes/60; 
//		long days = hours/24; 
		
		return (int)diff/MILLIS_IN_DAY;
	}
	
//	public long convTimeZone(int timeA, int offsetA, int offsetB) {
//		int timeB = timeA - offsetA + offsetB;
//		return timeB*MILLIS_IN_MIN;
//	}
	
	public long getLocalTime(int offsetLocal, int offsetServer) {
		return System.currentTimeMillis() - (offsetLocal - offsetServer)*MILLIS_IN_MIN;
	}

	public long convDateToMillis(Date date) {
		Calendar cldr = Calendar.getInstance(); 
		cldr.setTime(date);
		return cldr.getTimeInMillis();
	}
	
	public Date convMillisToDate(long ms) {
		return new Date(ms);
	}
	
}

