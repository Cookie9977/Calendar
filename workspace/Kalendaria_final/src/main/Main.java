package main;

import javax.swing.SwingUtilities;

import pane.RequestPanel;
import window.Window;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				

//				new RequestPanel();
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
				new Window();
			}
		});
	}
}
