package logic;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeLogic {
	private ArrayList<String> temp;
	private Calendar cal = new GregorianCalendar();
	public Calendar calIns = Calendar.getInstance(), tempCal;

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
	public ArrayList<String> getCurrentWeekDays() {
		temp = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/d MMM");
		String dateString;
		Date date;
		int year = getCurrentYear();
		int month = getCurrentMonth();
		int start = cal.get(Calendar.DAY_OF_WEEK) + 1;
		for (int i = start; i < start + 7; i++) {
			dateString = String.format("%d-%d-%d", year, month, i);
			try {
				date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
				temp.add(sdf.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public ArrayList<String> getWeekday() {
		temp = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			temp.add(String.valueOf(i));
		}
		return temp;
	}

	public int currentFirstDayMonth() {
		tempCal = Calendar.getInstance();
		tempCal.set(cal.get(Calendar.YEAR), getCurrentMonth(), 1);
		return tempCal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	public int currentLastDayMonth() {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int currentLastDayLastMonth() {
		tempCal = new GregorianCalendar();
		tempCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1);
		return tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int currentDayOfWeek() {
		return cal.get(Calendar.DAY_OF_WEEK) - 2;
	}

	public int firstDayMonth() {
		tempCal = Calendar.getInstance();
		tempCal.set(calIns.get(Calendar.YEAR), getMonth(), 1);
		return tempCal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	public int lastDayMonth() {
		return calIns.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int lastDayLastMonth() {
		tempCal = new GregorianCalendar();
		tempCal.set(calIns.get(Calendar.YEAR), calIns.get(Calendar.MONTH) - 1, 1);
		return tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int dayOfWeek() {
		return calIns.get(Calendar.DAY_OF_WEEK) - 2;
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
	public ArrayList<String> getYears() {
		temp = new ArrayList<String>();

		int thisYear = cal.get(Calendar.YEAR);

		for (int i = 0; i < 21; i++) {
			temp.add(String.valueOf(thisYear + i));
		}

		return temp;
	}

	public ArrayList<String> getWeekDays() {
		temp = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/d MMM");
		String dateString;
		Date date;
		int year = getYear();
		int month = getMonth();
		int start = calIns.get(Calendar.DAY_OF_WEEK) + 1;
		for (int i = start; i < start + 7; i++) {
			dateString = String.format("%d-%d-%d", year, month, i);
			try {
				date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
				temp.add(sdf.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public int getCurrentYear() {
		return cal.get(Calendar.YEAR);
	}

	public int getCurrentMonth() {
		return cal.get(Calendar.MONTH) + 1;
	}

	public int getCurrentWeek() {
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public int getCurrentDay() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public void nextYear() {
		calIns.set(Calendar.YEAR, calIns.get(Calendar.YEAR) + 1);
	}

	public void nextMonth() {
		calIns.set(Calendar.MONTH, calIns.get(Calendar.MONTH) + 1);

	}

	public void nextWeek() {
		calIns.set(Calendar.DAY_OF_MONTH, calIns.get(Calendar.DAY_OF_MONTH) + 60);
	}

	public void nextDay() {
		calIns.set(Calendar.DAY_OF_MONTH, calIns.get(Calendar.DAY_OF_MONTH) + 1);
	}

	public int getYear() {
		return calIns.get(Calendar.YEAR);
	}

	public int getMonth() {
		return (calIns.get(Calendar.MONTH) + 1);
	}

	public int getWeek() {
		return calIns.get(Calendar.WEEK_OF_YEAR);
	}

	public int getDay() {
		return calIns.get(Calendar.DAY_OF_MONTH);
	}

	public void printTime() {
		System.out.println("År: " + getYear());
		System.out.println("Månad: " + getMonth());
		System.out.println("Vecka: " + getWeek());
		System.out.println("Dag: " + getDay());
	}

	// get a double between 0 and 24 only whole and halfs allowed output a
	// String in HH:mm format
	public String doubleToTime(double input) {
		double fraction = input % 1;
		double whole = input - fraction;
		String time;

		if (whole < 10) {
			time = "0" + (int) whole;
		} else {
			time = String.valueOf((int) whole);
		}
		time += ":";

		if (fraction != 0) {
			time += "30";
		} else {
			time += "00";
		}
		return time;
	}

	// all paresOutX takes String YYYY-mm-dd hh:mm:ss and outputs x part of
	// String
	public String parseOutYear(String time) {
		String[] parts = time.split("-");
		return parts[0];
	}

	public String parseOutMonth(String time) {
		String[] parts = time.split("-");
		return parts[1];
	}

	public String parseOutDay(String time) {
		String[] parts = time.split("-| ");
		return parts[2];
	}

	public String parseOutHour(String time) {
		String[] parts = time.split(":| ");
		return parts[1];
	}

	public String parseOutMinute(String time) {
		String[] parts = time.split(":");
		return parts[1];
	}
}
