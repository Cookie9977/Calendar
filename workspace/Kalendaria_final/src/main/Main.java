package main;

import javax.swing.SwingUtilities;

import pane.RequestPanel;

public class Main {
	public static int id;


	public final static JavaDB db = new JavaDB("192.168.216.112", "Test", "qwerty123", "calendar");

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				new Window();
				new RequestPanel();
				//TimeLogic logic = new TimeLogic();
//				logic.printTime();
//				logic.nextWeek();
//				System.out.println("***************************************");
//				logic.printTime();
//				System.out.println("*********************************");
//				ArrayList<String> days = logic.getWeekDays();
//				for (int i = 0; i < days.size(); i++) {
//					System.out.println(days.get(i));
//				}
				//int i = logic.getdayOfWeek();
				//System.out.println(i);
			}
		});
	}
}
