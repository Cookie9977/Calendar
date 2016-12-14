package window;

import java.awt.BorderLayout;

import logic.TimeLogic;
import main.Storage;
import pane.AddButtonsPane;

public class WindowModifications {
	private TimeLogic logic;
	protected MonthView monthView, newMonthView;
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
		logic = new TimeLogic();
		logic.nextMonth();
		window.cContent.remove(monthView);
		if (Storage.oldMonthView != null) {
			window.cContent.remove(Storage.oldMonthView);
		}
		newMonthView = new MonthView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				thisMonth = logic.getMonth();
				lastDayOfMonth = logic.lastDayMonth();
				dayOfWeekMonth = justering = logic.firstDayMonth();
				lastDayLastMonth = logic.lastDayLastMonth();
				daysOfMonth = logic.getDays("" + thisMonth);
				year = logic.getYear();
			}
		};
		window.cContent.add(newMonthView);
		Storage.oldMonthView = newMonthView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void previousMonth() {
		logic = new TimeLogic();
		logic.previousMonth();
		window.cContent.remove(monthView);
		if (Storage.oldMonthView != null) {
			window.cContent.remove(Storage.oldMonthView);
		}
		newMonthView = new MonthView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				thisMonth = logic.getMonth();
				lastDayOfMonth = logic.lastDayMonth();
				dayOfWeekMonth = justering = logic.firstDayMonth();
				lastDayLastMonth = logic.lastDayLastMonth();
				daysOfMonth = logic.getDays("" + thisMonth);
				year = logic.getYear();
			}
		};
		window.cContent.add(newMonthView);
		Storage.oldMonthView = newMonthView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void nextWeek() {
		logic = new TimeLogic();
		logic.nextWeek();
		window.cContent.remove(weekView);
		if (Storage.oldWeekView != null) {
			window.cContent.remove(Storage.oldWeekView);
		}
		newWeekView = new WeekView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		window.cContent.add(newWeekView);
		Storage.oldWeekView = newWeekView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void previousWeek() {
		logic = new TimeLogic();
		logic.previousWeek();
		window.cContent.remove(weekView);
		if (Storage.oldWeekView != null) {
			window.cContent.remove(Storage.oldWeekView);
		}
		newWeekView = new WeekView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		window.cContent.add(newWeekView);
		Storage.oldWeekView = newWeekView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void nextDay() {
		logic = new TimeLogic();
		logic.nextDay();
		window.cContent.remove(dayView);
		if (Storage.oldDayView != null) {
			window.cContent.remove(Storage.oldDayView);
		}
		newDayView = new DayView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				day = logic.getDay();
			}
		};
		window.cContent.add(dayView);
		Storage.oldDayView = newDayView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void previousDay() {
		logic = new TimeLogic();
		logic.previousDay();
		window.cContent.remove(dayView);
		if (Storage.oldDayView != null) {
			window.cContent.remove(Storage.oldDayView);
		}
		newDayView = new DayView() {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				day = logic.getDay();
			}
		};
		window.cContent.add(dayView);
		Storage.oldDayView = newDayView;
		window.cContent.revalidate();
		window.repaint();
	}

	public void showMonthView() {
		window.cContent.remove(dayView);
		if (Storage.oldDayView != null) {
			window.cContent.remove(Storage.oldDayView);
		}
		window.cContent.remove(weekView);
		if (Storage.oldWeekView != null) {
			window.cContent.remove(Storage.oldWeekView);
		}
		window.cContent.add(monthView);
		window.view.setText("Månad");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showWeekView() {
		window.cContent.remove(dayView);
		if (Storage.oldDayView != null) {
			window.cContent.remove(Storage.oldDayView);
		}
		window.cContent.remove(monthView);
		if (Storage.oldMonthView != null) {
			window.cContent.remove(Storage.oldMonthView);
		}
		window.cContent.add(weekView);
		window.view.setText("Vecka");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showDayView() {
		window.cContent.remove(monthView);
		if (Storage.oldMonthView != null) {
			window.cContent.remove(Storage.oldMonthView);
		}
		window.cContent.remove(weekView);
		if (Storage.oldWeekView != null) {
			window.cContent.remove(Storage.oldWeekView);
		}
		window.cContent.add(dayView);
		window.view.setText("Dag");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showLoggedinView() {
		upcomingEvent = new UpcomingEvent();
		window.menyBar.remove(loginView);
		window.menyBar.remove(registerView);
		window.menyBar.add(upcomingEvent, BorderLayout.NORTH);
		window.menyBar.add(addButtons, BorderLayout.SOUTH);
		window.revalidate();
		window.repaint();
	}

}
