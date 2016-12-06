package logic;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeLogic {
	private ArrayList<String> temp;
	private ArrayList<String> tamp;
	private Calendar cal;

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
		cal = new GregorianCalendar();
		cal.set(cal.get(Calendar.YEAR), monthValue, cal.get(Calendar.DAY_OF_MONTH));
		int nrDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < nrDays; i++) {
			temp.add(String.valueOf(i + 1));
		}
		return temp;
	}
	//returnerar dagar i vecka, in = sträng på veckonummer,
	public ArrayList<String>  getWeekDays (int Week){
		temp = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE/d MMM");
		cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_YEAR, Week);
		for(int i = 1; i < 8;i++){
			cal.set(Calendar.DAY_OF_WEEK,i+1 );			
//			System.out.println(sdf.format(cal.getTime())+" Siffran är: "+i);
			temp.add(String.valueOf(sdf.format(cal.getTime())));
		}
		return temp;
	}

	
	public int firstDayMonth(){
		Calendar cal1;
		cal1 = new GregorianCalendar();
		cal1.set(cal1.get(Calendar.YEAR), getCurrentMonth(), 1);
		return cal1.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	
	public int lastDayMonth(){
		Calendar cal1;
		cal1 = new GregorianCalendar();
		cal1.set(cal1.get(Calendar.YEAR), getCurrentMonth(), 1);
		return cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		
		}
	
	public ArrayList<String> getWeekday() {
		temp = new ArrayList<String>();
		tamp = new ArrayList<String>();
		cal = new GregorianCalendar();
		int weekDay = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		String[] weekDayName = new String[]{"Måndag","Tisdag","Onsdag","Torsdag","Fredag","Lördag","Söndag"};
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

	//returnerar den aktuella månaden.
	public int getCurrentMonth(){
		cal = new GregorianCalendar();
		int currentMonth = cal.get(Calendar.MONTH);
		return currentMonth;
	}


	// returnerar den aktuella veckan.
	public int getWeek() {

		cal = new GregorianCalendar();
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
}
