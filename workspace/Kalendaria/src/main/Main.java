package main;

import javax.swing.JOptionPane;

import window.EventPane;
import window.Window;

public class Main {
	public final static JavaDB db = new JavaDB("192.168.216.168", "Test", "qwerty123", "calendar");
	public static void main(String[] args) {
		new Window();
		//EventPane pane = new EventPane();
		//int test = JOptionPane.showConfirmDialog(null, pane, "test", JOptionPane.OK_CANCEL_OPTION);
		//if (test == JOptionPane.OK_OPTION) {
			
		//}
	}

}
