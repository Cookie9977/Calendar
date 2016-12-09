package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.SwingUtilities;

import window.Window;

public class Main {
	public static int id;

	public static Calendar cal = new GregorianCalendar(), calIns = Calendar.getInstance();
	public final static JavaDB db = new JavaDB("192.168.1.203", "Test", "qwerty123", "calendar");

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				new Window();
				// TimeLogic logic = new TimeLogic();
				// logic.printTime();
				// logic.nextWeek();
				// System.out.println("***************************************");
				// logic.printTime();
				// System.out.println("*********************************");
				// ArrayList<String> days = logic.getWeekDays();
				// for (int i = 0; i < days.size(); i++) {
				// System.out.println(days.get(i));
				// }
				// int i = logic.getdayOfWeek();
				// System.out.println(i);
			}
		});
	}
}
