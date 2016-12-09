package logic;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import main.Storage;

public class TimeLogic {
	private ArrayList<String> temp;
	public Calendar tempCal;

	// Retunerar dagar i månad, in = sträng på månadens namn.
	public ArrayList<String> getDays(String month) {
		tempCal = Calendar.getInstance();
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
		tempCal.set(Storage.cal.get(Calendar.YEAR), monthValue, Storage.cal.get(Calendar.DAY_OF_MONTH));
		int nrDays = Storage.cal.getActualMaximum(Calendar.DAY_OF_MONTH);
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
		int start = getCurrentDay() - Storage.cal.get(Calendar.DAY_OF_WEEK) + 2;
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

	public int currentLastDayMonth() {
		return Storage.cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int currentLastDayLastMonth() {
		tempCal = new GregorianCalendar();
		tempCal.set(Storage.cal.get(Calendar.YEAR), Storage.cal.get(Calendar.MONTH) - 1, 1);
		return tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int currentFirstDayOfMonth() {
		tempCal = new GregorianCalendar();
		tempCal.set(Storage.cal.get(Calendar.YEAR), Storage.cal.get(Calendar.MONTH), 1);
		int day = tempCal.get(Calendar.DAY_OF_WEEK) - 2;
		if (day == -1) {
			day = 6;
		}
		return day;
	}

	public int firstDayMonth() {
		tempCal = Calendar.getInstance();
		tempCal.set(Storage.calIns.get(Calendar.YEAR), Storage.calIns.get(Calendar.MONTH), 1);
		int day = tempCal.get(Calendar.DAY_OF_WEEK) - 2;
		if (day == -1) {
			day = 6;
		}
		return day;
	}

	public int lastDayMonth() {
		return Storage.calIns.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int lastDayLastMonth() {
		tempCal = new GregorianCalendar();
		tempCal.set(Storage.calIns.get(Calendar.YEAR), Storage.calIns.get(Calendar.MONTH) - 1, 1);
		return tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int dayOfWeek() {
		tempCal = new GregorianCalendar();
		tempCal.set(Storage.calIns.get(Calendar.YEAR), Storage.calIns.get(Calendar.MONTH), 1);
		int day = tempCal.get(Calendar.DAY_OF_WEEK) - 2;
		if (day == -1) {
			day = 6;
		}
		return day;
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

		int thisYear = Storage.cal.get(Calendar.YEAR);

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
		int start = getDay() - Storage.calIns.get(Calendar.DAY_OF_WEEK) + 2;
		System.out.println(start);
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
		return Storage.cal.get(Calendar.YEAR);
	}

	public int getCurrentMonth() {
		return Storage.cal.get(Calendar.MONTH) + 1;
	}

	public int getCurrentWeek() {
		return Storage.cal.get(Calendar.WEEK_OF_YEAR);
	}

	public int getCurrentDay() {
		return Storage.cal.get(Calendar.DAY_OF_MONTH);
	}

	public int getYear() {
		return Storage.calIns.get(Calendar.YEAR);
	}

	public int getMonth() {
		return (Storage.calIns.get(Calendar.MONTH) + 1);
	}

	public int getWeek() {
		return Storage.calIns.get(Calendar.WEEK_OF_YEAR);
	}

	public int getDay() {
		return Storage.calIns.get(Calendar.DAY_OF_MONTH);
	}

	public void nextYear() {
		Storage.calIns.set(Calendar.YEAR, Storage.calIns.get(Calendar.YEAR) + 1);
	}

	public void nextMonth() {
		Storage.calIns.set(Calendar.MONTH, Storage.calIns.get(Calendar.MONTH) + 1);

	}

	public void nextWeek() {
		Storage.calIns.set(Calendar.DAY_OF_MONTH, Storage.calIns.get(Calendar.DAY_OF_MONTH) + 7);
	}

	public void nextDay() {
		Storage.calIns.set(Calendar.DAY_OF_MONTH, Storage.calIns.get(Calendar.DAY_OF_MONTH) + 1);
	}

	public void previousYear() {
		Storage.calIns.set(Calendar.YEAR, Storage.calIns.get(Calendar.YEAR) - 1);
	}

	public void previousMonth() {
		Storage.calIns.set(Calendar.MONTH, Storage.calIns.get(Calendar.MONTH) - 1);

	}

	public void previousWeek() {
		Storage.calIns.set(Calendar.DAY_OF_MONTH, Storage.calIns.get(Calendar.DAY_OF_MONTH) - 7);
	}

	public void previousDay() {
		Storage.calIns.set(Calendar.DAY_OF_MONTH, Storage.calIns.get(Calendar.DAY_OF_MONTH) - 1);
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
