package window;

import javax.swing.JLabel;

import pane.AddButtonsPane;

public class WindowModifications {
	protected MonthView monthView;
	protected WeekView weekView;
	protected DayView dayView;
	protected Window window;
	protected RegisterUser registerView;
	protected LoginUser loginView;
	protected AddButtonsPane addButtons;

	public WindowModifications(MonthView monthView, WeekView weekView, DayView dayView, Window window) {
		this.monthView = monthView;
		this.weekView = weekView;
		this.dayView = dayView;
		this.window = window;

	}

	public WindowModifications(LoginUser loginView, RegisterUser registerView, AddButtonsPane addButtons, Window window) {
		this.window = window;
		this.loginView = loginView;
		this.registerView = registerView;
		this.addButtons = addButtons;
	}

	public void showMonthView() {
		window.calendar.remove(dayView);
		window.calendar.remove(weekView);
		window.calendar.add(monthView);
		window.view.setText("Månad");
		window.calendar.revalidate();
		window.repaint();
	}

	public void showWeekView() {
		window.calendar.remove(dayView);
		window.calendar.remove(monthView);
		window.calendar.add(weekView);
		window.view.setText("Vecka");
		window.calendar.revalidate();
		window.repaint();
	}

	public void showDayView() {
		window.calendar.remove(monthView);
		window.calendar.remove(weekView);
		window.calendar.add(dayView);
		window.view.setText("Dag");
		window.calendar.revalidate();
		window.repaint();
	}

	public void showLoggedinView() {
		window.navBar.remove(loginView);
		window.navBar.remove(registerView);
		window.navBar.add(addButtons);
		window.revalidate();
		window.repaint();
	}

}
