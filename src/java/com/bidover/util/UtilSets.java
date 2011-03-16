package com.bidover.util;

import java.text.DecimalFormat;
import java.util.Locale;


public class UtilSets {
	
	public double getBidInc(double bid) {
		double inc = 100;
			 if(bid < 10) inc = .5;
		else if(bid < 100) inc = 1.0;
		else if(bid < 200) inc = 2.5;			 
		else if(bid < 500) inc = 5.0;
		else if(bid < 1000) inc = 10.0;
		else if(bid < 2500) inc = 25.0;
		else if(bid < 5000) inc = 50.0;

		return inc;
	}
	
	public boolean isInt(String inputString) {
		try {
		    Integer.parseInt(inputString);
		}
		catch(NumberFormatException err) {
			return false;
		}
		return true;
	}
	
	public boolean isDouble(String inputString) {
		try {
		    Double.parseDouble(inputString);
		}
		catch(NumberFormatException err) {
			return false;
		}
		return true;
	}

	public String getNumFormated(double fi) {
                Locale.setDefault(Locale.US);
                DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(fi);
	}
	
}
