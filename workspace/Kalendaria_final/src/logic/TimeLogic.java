package logic;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeLogic {
	private ArrayList<String> temp, tamp;
	private Calendar cal = new GregorianCalendar();

	// Retunerar dagar i månad, in = sträng på månadens namn.
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
		cal.set(cal.get(Calendar.YEAR), monthValue, cal.get(Calendar.DAY_OF_MONTH));
		int nrDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < nrDays; i++) {
			temp.add(String.valueOf(i + 1));
		}
		return temp;
	}

	// returnerar dagar i vecka, in = sträng på veckonummer,
	public ArrayList<String> getWeekDays(int Week) {
		temp = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/d MMM");
		cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_YEAR, Week);
		for (int i = 1; i < 8; i++) {
			cal.set(Calendar.DAY_OF_WEEK, i + 1);
			// System.out.println(sdf.format(cal.getTime())+" Siffran är: "+i);
			temp.add(String.valueOf(sdf.format(cal.getTime())));
		}
		return temp;
	}

	public int firstDayMonth() {
		cal.set(cal.get(Calendar.YEAR), getCurrentMonth(), 1);
		return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	public int lastDayMonth() {
		cal.set(cal.get(Calendar.YEAR), getCurrentMonth(), 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	public ArrayList<String> getWeekday() {
		temp = new ArrayList<String>();
		tamp = new ArrayList<String>();
		cal = new GregorianCalendar();
		int weekDay = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		String[] weekDayName = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		for (int i = 0; i < 7; i++) {
			temp.add(String.valueOf(weekDay + i));
			tamp.add(weekDayName[i]);

		}
		return temp;
	}

	// returnerar månadernas namn.

	public ArrayList<String> getMonths() {
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		String[] months = dfc.getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			temp.add(months[i]);
		}
		return temp;
	}

	// Hämtar ut året plus 20 framåt(för planeirng)
	public ArrayList<String> getYear() {
		temp = new ArrayList<String>();
		cal = new GregorianCalendar();
		int thisYear = cal.get(Calendar.YEAR);

		for (int i = 0; i < 21; i++) {
			temp.add(String.valueOf(thisYear + i));
		}

		return temp;
	}

	// returnerar den aktuella månaden.
	public int getCurrentMonth() {
		int currentMonth = cal.get(Calendar.MONTH);
		return currentMonth;
	}

	// returnerar den aktuella veckan.
	public int getWeek() {
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
	
	
	public String parseOutYear(String time){
		String[] parts = time.split("-");
		return parts[0];
	}
	
	public String parseOutMonth(String time){
		String[] parts = time.split("-");
		return parts[1];
	}
	
	public String parseOutDay(String time){
		String[] parts = time.split("-| ");
		return parts[2];
	}
	
	public String parseOutHour(String time){
		String[] parts = time.split(":| ");
		return parts[1];
	}
	
	public String parseOutMinute(String time){
		String[] parts = time.split(":");
		return parts[1];
	}

}
