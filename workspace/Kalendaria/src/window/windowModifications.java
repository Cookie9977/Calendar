package window;

public class windowModifications {
	protected monthView monthView;
	protected weekView weekView;
	protected dayView dayView;
	protected Window window;

	public windowModifications(monthView monthView, weekView weekView, dayView dayView, Window window) {
		this.monthView = monthView;
		this.weekView = weekView;
		this.dayView = dayView;
		this.window = window;
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

}