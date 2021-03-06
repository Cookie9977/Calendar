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

	// f�r att byta mellan vyer
	public WindowModifications(MonthView monthView, WeekView weekView, DayView dayView, Window window) {
		this.monthView = monthView;
		this.weekView = weekView;
		this.dayView = dayView;
		this.window = window;

	}

	// f�r att byta inloggad vs oinloggad
	public WindowModifications(LoginUser loginView, RegisterUser registerView, AddButtonsPane addButtons,
			Window window) {
		this.window = window;
		// this.upcomingEvent = upcomingEvent;
		this.loginView = loginView;
		this.registerView = registerView;
		this.addButtons = addButtons;
	}

	// f�r att byta mellan dagsyer
	public WindowModifications(DayView dayView, Window window) {
		this.dayView = dayView;
		this.window = window;
	}

	// f�r att byta mellan veckovyer
	public WindowModifications(WeekView weekView, Window window) {
		this.weekView = weekView;
		this.window = window;
	}

	// f�r att byta mellan m�ndadsvyer
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
		newMonthView = new MonthView(window) {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				thisMonth = logic.getMonth();
				lastDayOfMonth = logic.lastDayMonth();
				dayOfWeekMonth = justering = logic.firstDayMonth();
				lastDayLastMonth = logic.lastDayLastMonth();
				daysOfMonth = logic.getDays("" + thisMonth);
				firstWeek = logic.getFirstWeekOfMonth(thisMonth);
				year = logic.getYear();
			}
		};
		window.cContent.add(newMonthView,BorderLayout.CENTER);
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
		newMonthView = new MonthView(window) {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				thisMonth = logic.getMonth();
				lastDayOfMonth = logic.lastDayMonth();
				dayOfWeekMonth = justering = logic.firstDayMonth();
				lastDayLastMonth = logic.lastDayLastMonth();
				daysOfMonth = logic.getDays("" + thisMonth);
				firstWeek = logic.getFirstWeekOfMonth(thisMonth);
				year = logic.getYear();
			}
		};
		window.cContent.add(newMonthView,BorderLayout.CENTER);
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
		newWeekView = new WeekView(window) {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		window.cContent.add(newWeekView,BorderLayout.CENTER);
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
		newWeekView = new WeekView(window) {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				week = logic.getWeek();
				days = logic.getWeekDays();
			}
		};
		window.cContent.add(newWeekView,BorderLayout.CENTER);
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

		newDayView = new DayView(window) {
			private static final long serialVersionUID = -8969378343897882526L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				day = logic.getDay();
				dayName = logic.getDayName();
			}
		};
		window.cContent.add(newDayView,BorderLayout.CENTER);
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
		newDayView = new DayView(window) {
			private static final long serialVersionUID = 4658993524466783899L;

			@Override
			public void init() {
				year = logic.getYear();
				month = logic.getMonth();
				day = logic.getDay();
				dayName = logic.getDayName();
			}
		};
		window.cContent.add(newDayView,BorderLayout.CENTER);
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
		window.cContent.add(monthView,BorderLayout.CENTER);
		window.view.setText("M�nad");
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
		window.cContent.add(weekView,BorderLayout.CENTER);
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
		window.cContent.add(dayView ,BorderLayout.CENTER);
		window.view.setText("Dag");
		window.cContent.revalidate();
		window.repaint();
	}

	public void showLoggedinView() {
		upcomingEvent = new UpcomingEvent(window);
		window.menyBar.remove(loginView);
		window.menyBar.remove(registerView);
		window.menyBar.add(upcomingEvent, BorderLayout.NORTH);
		window.menyBar.add(window.addButtons, BorderLayout.SOUTH);
		window.revalidate();
		window.repaint();
	}

	public void updateUpcomingEvents() {
		upcomingEvent = new UpcomingEvent(window);
		addButtons = new AddButtonsPane(window);
		window.menyBar.removeAll();
		window.menyBar.add(upcomingEvent, BorderLayout.NORTH);
		window.menyBar.add(addButtons, BorderLayout.SOUTH);
		window.revalidate();
		window.repaint();
	}

	// Startar en ny instans av addbuttons f�r att uppdatera antalet
	// f�rfr�gnignar.
	public void updateButtonPane() {
		addButtons = new AddButtonsPane(window);
		upcomingEvent = new UpcomingEvent(window);
		window.menyBar.removeAll();
		window.menyBar.add(upcomingEvent, BorderLayout.NORTH);
		window.menyBar.add(addButtons, BorderLayout.SOUTH);
		window.revalidate();
		window.repaint();
	}

}
