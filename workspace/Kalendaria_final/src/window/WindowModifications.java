package window;

import pane.AddButtonsPane;

public class WindowModifications {
	protected MonthView monthView;
	protected WeekView weekView;
	protected DayView dayView;
	protected Window window;
	protected RegisterUser registerView;
	protected LoginUser loginView;
	protected AddButtonsPane addButtons;
	protected UpcomingEvent upcomingEvent;

	// för att byta mellan vyer
	public WindowModifications(MonthView monthView, WeekView weekView, DayView dayView, Window window) {
		this.monthView = monthView;
		this.weekView = weekView;
		this.dayView = dayView;
		this.window = window;

	}

	// för att byta inloggad vs oinloggad
	public WindowModifications(LoginUser loginView, RegisterUser registerView, AddButtonsPane addButtons,
			UpcomingEvent upcomingEvent, Window window) {
		this.window = window;
		this.upcomingEvent = upcomingEvent;
		this.loginView = loginView;
		this.registerView = registerView;
		this.addButtons = addButtons;
	}

	// för att byta mellan dagsyer
	public WindowModifications(DayView dayView, Window window) {
		this.dayView = dayView;
		this.window = window;
	}

	// för att byta mellan veckovyer
	public WindowModifications(WeekView weekView, Window window) {
		this.weekView = weekView;
		this.window = window;
	}

	// för att byta mellan måndadsvyer
	public WindowModifications(MonthView monthView, Window window) {
		this.monthView = monthView;
		this.window = window;
	}
	
	public void nextMonth(){
		
	}
	
	public void previousMonth(){
		
	}
	
	public void nextWeek(){
		
	}
	
	public void previousWeek(){
		
	}
	
	public void nextDay(){
		
	}
	
	public void previousDay(){
		
	}

	public void showMonthView() {
		window.cContent.remove(dayView);
		window.cContent.remove(weekView);
		window.cContent.add(monthView);
		window.view.setText("Månad");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showWeekView() {
		window.cContent.remove(dayView);
		window.cContent.remove(monthView);
		window.cContent.add(weekView);
		window.view.setText("Vecka");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showDayView() {
		window.cContent.remove(monthView);
		window.cContent.remove(weekView);
		window.cContent.add(dayView);
		window.view.setText("Dag");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showLoggedinView() {
		window.menyBar.remove(loginView);
		window.menyBar.remove(registerView);
		window.menyBar.add(upcomingEvent);
		window.menyBar.add(addButtons);
		window.revalidate();
		window.repaint();
	}

}
