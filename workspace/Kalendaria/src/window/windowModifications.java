package window;

import javax.swing.JOptionPane;

public class windowModifications {
	protected Window window;

	public windowModifications(Window window) {
		this.window = window;
	}

	public void showMonthView() {
		JOptionPane.showMessageDialog(null, " yatta, månad");
		window.calendar.removeAll();
		window.pack();
	}

	public void showWeekView() {
		JOptionPane.showMessageDialog(null, " yatta, vecka");
		window.calendar.removeAll();
		window.pack();
	}

	public void showDayView() {
		JOptionPane.showMessageDialog(null, " yatta, dag");
		window.calendar.removeAll();
		window.pack();
	}
}
