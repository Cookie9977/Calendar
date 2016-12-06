package logic;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeLogic {
	private ArrayList<String> temp;
	private Calendar cal;

	public ArrayList<String> getDays(String month) {
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		int monthValue = 0;
		String[] months = dfc.getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			if (months[i].equals(month)) {
				monthValue = i;
				break;
			}
		}
		cal = new GregorianCalendar();
		cal.set(cal.get(Calendar.YEAR), monthValue, cal.get(Calendar.DAY_OF_MONTH));
		int nrDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < nrDays; i++) {
			temp.add(String.valueOf(i + 1));
		}
		return temp;
	}
	
	public ArrayList<String> getMonths() {
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		String[] months = dfc.getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			temp.add(months[i]);
		}
		return temp;
	}

	public ArrayList<String> getYear() {
		temp = new ArrayList<String>();
		cal = new GregorianCalendar();
		int thisYear = cal.get(Calendar.YEAR);

		for (int i = 0; i < 21; i++) {
			temp.add(String.valueOf(thisYear + i));
		}

		return temp;
	}
	
	public int getWeek(){
		cal = new GregorianCalendar();
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
	// get a double between 0 and 24 only whole and halfs allowed outpust a Stinrg in HH:mm format
	public String doubleToTime(double input){
		double fraction = input % 1;
		double whole = input - fraction;
		String time;
		
		if (whole < 10) {
			time = "0"+(int)whole;
		} else {
			time = String.valueOf((int)whole);
		}
		time += ":";
		
		if (fraction != 0) {
			time += "30";
		} else {
			time += "00";
		}		
		return time;
	}
}
