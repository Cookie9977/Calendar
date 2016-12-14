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
	public static int id = 3;
	public static Calendar cal = new GregorianCalendar(), calIns = Calendar.getInstance();
	public final static JavaDB db = new JavaDB("192.168.216.160", "Test", "qwerty123", "calendar");
}
