package window;

public class WindowModifications {
	protected monthView monthView;
	protected WeekView weekView;
	protected DayView dayView;
	protected Window window;
	protected RegisterUser registerView;
	protected LoginUser loginView;

	public WindowModifications(monthView monthView, WeekView weekView, DayView dayView, Window window) {
		this.monthView = monthView;
		this.weekView = weekView;
		this.dayView = dayView;
		this.window = window;
	}

	public WindowModifications(LoginUser loginView, RegisterUser registerView, Window window) {
		this.window = window;
		this.loginView = loginView;
		this.registerView = registerView;
	}

	public void showMonthView() {
		window.calendar.remove(dayView);
		window.calendar.remove(weekView);
		window.calendar.add(monthView);
		window.calendar.revalidate();
		window.pack();
	}

	public void showWeekView() {
		window.calendar.remove(dayView);
		window.calendar.remove(monthView);
		window.calendar.add(weekView);
		window.calendar.revalidate();
		window.pack();
	}

	public void showDayView() {
		window.calendar.remove(monthView);
		window.calendar.remove(weekView);
		window.calendar.add(dayView);
		window.calendar.revalidate();
		window.pack();
	}

	public void showLoggedinView() {
		window.navBar.remove(loginView);
		window.calendar.remove(registerView);
		window.revalidate();
		window.pack();
		System.err.println(1);
	}
	
}
