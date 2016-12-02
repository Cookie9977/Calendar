package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pane.AddButtonsPane;
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
	}
}
