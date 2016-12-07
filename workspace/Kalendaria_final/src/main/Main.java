package main;

import javax.swing.SwingUtilities;

import window.Window;

public class Main {
	public static int id;

	public final static JavaDB db = new JavaDB("192.168.216.134", "Test", "qwerty123", "calendar");

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
	}
}
