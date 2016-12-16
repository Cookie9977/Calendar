package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import window.DayView;
import window.MonthView;
import window.WeekView;

public class Storage {
	public static WeekView oldWeekView;
	public static DayView oldDayView;
	public static MonthView oldMonthView;
	public static int id;
	public static Calendar cal = new GregorianCalendar(), calIns = Calendar.getInstance();
	public final static JavaDB db = new JavaDB("din mamma", "Test", "qwerty123", "calendar");
}
