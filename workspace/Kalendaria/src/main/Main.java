package main;

import javax.swing.JOptionPane;

import Pane.EventPane;
import window.Window;

public class Main {
	public static int id;

	public final static JavaDB db = new JavaDB("192.168.216.95", "Test", "qwerty123", "calendar");
	public static void main(String[] args) {
		new Window();
		EventPane pane = new EventPane();
		//int test = JOptionPane.showConfirmDialog(null, pane, "test", JOptionPane.OK_CANCEL_OPTION);
		
	}

}
