package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import event.Event;
import event.EventList;
import logic.TimeLogic;
import main.ClickListener;
import main.Storage;

public class DayView extends JPanel {
	private static final long serialVersionUID = -5546841496999182019L;
	private JPanel calendar,header;
	private JPanel[][] holders;
	private JPanel[] layoutHolders;
	private TimeLogic logic;
	private JScrollPane scroll;
	private JLabel[] timeLabels;
	private String SQL;
	private ArrayList<Event> event;
	private JLabel[] eventLabels;
	private JLabel dayNumber;
	protected int day, month, year;
	protected String dayName;

	public DayView(Window windowVal) {
		logic = new TimeLogic();
		init();
		calendar = new JPanel();
		scroll = new JScrollPane();
		header = new JPanel();
		dayNumber = new JLabel();
		
		setLayout(new BorderLayout());
		header.setLayout(new BorderLayout());
		if (Storage.id != 0) {
			SQL = "SELECT event.id, event.title, event.description, event.location, event.start, event.end, category.name, event_link.owner  FROM `event_link` LEFT JOIN event ON event_link.event_id = event.id INNER JOIN category ON event.category = category.id WHERE (event_link.owner = 1 OR event_link.accepted = 1) AND event_link.user_id = "
					+ Storage.id + " AND DATE_FORMAT(event.start, '%Y%m%d') = " + year + "" + month + "" + day;
			Object[][] data = Storage.db.getData(SQL);
			try {
				event = new EventList(data);
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
		
		dayNumber.setText(dayName);
		dayNumber.setFont(new Font("SansSerif", Font.BOLD, 20));
		dayNumber.setHorizontalAlignment(JLabel.CENTER);
		dayNumber.setVerticalAlignment(JLabel.CENTER);
		
		header.add(dayNumber, BorderLayout.CENTER);
		
		scroll.setViewportView(calendar);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(620, 600));
		scroll.getVerticalScrollBar().setUnitIncrement(10);

		calendar.setLayout(new BorderLayout());
		calendar.setAutoscrolls(true);

		holders = new JPanel[48][2];
		layoutHolders = new JPanel[2];
		timeLabels = new JLabel[48];
		eventLabels = new JLabel[48];

		for (int i = 0; i < timeLabels.length; i++) {
			timeLabels[i] = new JLabel(logic.doubleToTime((double) i / 2));
			timeLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			timeLabels[i].setVerticalAlignment(SwingConstants.CENTER);
			timeLabels[i].setForeground(new Color(245,245,245));
			
			eventLabels[i] = new JLabel();
			eventLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			eventLabels[i].setVerticalAlignment(SwingConstants.CENTER);
		}

		for (int i = 0; i < layoutHolders.length; i++) {
			layoutHolders[i] = new JPanel();
			layoutHolders[i].setLayout(new GridLayout(48, 1));
		}

		calendar.add(layoutHolders[0], BorderLayout.WEST);
		calendar.add(layoutHolders[1], BorderLayout.CENTER);

		for (int i = 0; i < holders.length; i++) {
			for (int j = 0; j < holders[i].length; j++) {
				holders[i][j] = new JPanel();
				holders[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
				if (j == 0) {
					holders[i][j].setPreferredSize(new Dimension(40, 20));
					holders[i][j].add(timeLabels[i]);
					holders[i][j].setBackground(new Color(204, 51, 145));
					layoutHolders[0].add(holders[i][j]);
				} else {
					holders[i][j].setPreferredSize(new Dimension(20, 20));
					layoutHolders[1].add(holders[i][j]);
					try {
						addEvent(i, j, eventLabels[i], holders[i][j]);
					} catch (Exception e) {
					}
					holders[i][j].add(eventLabels[i]);
					// System.out.println(eventLabels[i].getText());
					holders[i][j].addMouseListener(new ClickListener(holders[i][j], windowVal));
				}

			}
		}
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	public void init() {
		year = logic.getCurrentYear();
		month = logic.getCurrentMonth();
		day = logic.getCurrentDay();
		dayName = logic.getCurrentDayName();
	}

	private void addEvent(int i, int j, JLabel eventLabel, JPanel panel) {
		for (int k = 0; k < event.size(); k++) {
			int tabelNumber = panelNumber(day, i);

			int sqlStartNumber = sqlNumber(logic.parseOutDay(event.get(k).getTimeStart()),
					logic.parseOutHour(event.get(k).getTimeStart()), logic.parseOutMinute(event.get(k).getTimeStart()));

			int sqlEndNumber = sqlNumber(logic.parseOutDay(event.get(k).getTimeEnd()),
					logic.parseOutHour(event.get(k).getTimeEnd()), logic.parseOutMinute(event.get(k).getTimeEnd()));

			if (tabelNumber >= sqlStartNumber && tabelNumber <= sqlEndNumber) {
				eventLabel.setText("" + event.get(k).getTitle());
			}
		}
	}

	private int panelNumber(int day, int i) {
		String number;
		if (day < 10) {
			number = "0" + day;
		} else {
			number = "" + day;
		}
		if ((int) Math.ceil(i / 2) < 10) {
			number += "0" + (int) Math.ceil(i / 2);
		} else {
			number += "" + (int) Math.ceil(i / 2);
		}
		if (i % 2 == 0) {
			number += "00";
		} else {
			number += "30";
		}
		return Integer.parseInt(number);
	}

	private int sqlNumber(String day, String hour, String minutes) {
		int number = Integer.parseInt(day + "" + hour + minutes);
		return number;
	}

}
