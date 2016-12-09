package window;

import logic.TimeLogic;
import pane.AddButtonsPane;

public class WindowModifications {
	private TimeLogic logic;
	protected MonthView monthView;
	protected WeekView weekView, newWeekView;
	protected DayView dayView, newDayView;
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

	public void nextMonth() {

	}

	public void previousMonth() {

	}

	public void nextWeek() {
		logic = new TimeLogic();
		logic.nextWeek();
		// System.out.println(logic.getDay());
		window.cContent.remove(weekView);
		newWeekView = new WeekView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		weekView.veckaLabel.setText("v."+Integer.toString(logic.getWeek()));
		weekView = newWeekView;
		window.cContent.add(weekView);
		window.cContent.revalidate();
		window.navBar.revalidate();
		window.repaint();
	}

	public void previousWeek() {
		logic = new TimeLogic();
		logic.previousWeek();
		// System.out.println(logic.getDay());
		window.cContent.remove(weekView);
		newWeekView = new WeekView() {
			private static final long serialVersionUID = -6093004429952979249L;

			@Override
			public void init() {
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		window.cContent.add(weekView);
		window.cContent.revalidate();
		window.navBar.revalidate();
		window.repaint();
	}

	public void nextDay() {
		logic = new TimeLogic();
		logic.nextDay();
		// System.out.println(logic.getDay());
		window.cContent.remove(dayView);
		newDayView = new DayView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				day = logic.getDay();
			}
		};
		window.cContent.add(dayView);
		window.cContent.revalidate();
		window.navBar.revalidate();
		window.repaint();
	}

	public void previousDay() {
		logic = new TimeLogic();
		logic.previousDay();
		// System.out.println(logic.getDay());
		window.cContent.remove(dayView);
		newDayView = new DayView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				day = logic.getDay();
			}
		};
		window.cContent.add(dayView);
		window.cContent.revalidate();
		window.navBar.revalidate();
		window.repaint();
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
