package main;

import javax.swing.SwingUtilities;

import window.Window;

public class Main {
	public static int id = 3;
	public final static JavaDB db = new JavaDB("192.168.216.95", "Test", "qwerty123", "calendar");

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
		// EventPane pane = new EventPane();
		// int test = JOptionPane.showConfirmDialog(null, pane, "test",
		// JOptionPane.OK_CANCEL_OPTION);
		// if (test == JOptionPane.OK_OPTION) {
		// EventLogic.checkEvent(pane);
		// }
	}
}
