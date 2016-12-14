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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import event.Event;
import event.EventList;
import logic.TimeLogic;
import main.ClickListener;
import main.Storage;

public class WeekView extends JPanel {

	private TimeLogic timeLogic;
	private String SQL;
	private ArrayList<Event> event;
	private Event temp;
	protected JLabel veckaLabel;
	protected Color Invisible = new Color(0, 0, 0, 0);
	protected int week, month, year;
	ArrayList<String> days;

	private static final long serialVersionUID = -1542039657044981535L;

	public WeekView() {
		timeLogic = new logic.TimeLogic();
		init();
		// for (int i = 0; i < days.size(); i++) {
		// System.out.println(days.get(i));
		// }
		// System.out.println(week);
		if (Storage.id != 0) {
			SQL = "SELECT event.id, event.title, event.description, event.location, event.start, event.end, category.name, event_link.owner  FROM `event_link` LEFT JOIN event ON event_link.event_id = event.id INNER JOIN category ON event.category = category.id WHERE (event_link.owner = 1 OR event_link.accepted = 1) AND event_link.user_id = "
					+ Storage.id + " AND DATE_FORMAT(event.start, '%Y%m%d') >= " + year + "" + month
					+ parseDay(days.get(0)) + " AND DATE_FORMAT(event.end, '%Y%m%d') <= " + year + "" + month
					+ parseDay(days.get(days.size() - 1));
			Object[][] data = Storage.db.getData(SQL);
			try {
				event = new EventList(data);
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
		// general window
		setPreferredSize(new Dimension(700, 600));
		setLayout(new BorderLayout());

		// Top line(header names)
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(700, 96));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		// Vecko panel
		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(70, 96));
		vecka.setLayout(new GridLayout(0, 1));
		vecka.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topLine.add(vecka, BorderLayout.WEST);

		// Aktuell vecka som label i vecko panel
		veckaLabel = new JLabel();
		veckaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		veckaLabel.setText("V." + week);
		veckaLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		vecka.add(veckaLabel);

		// West line, timestamps on the left
		JPanel westLine = new JPanel();
		westLine.setSize(new Dimension(70, 504));
		westLine.setLayout(new GridLayout(1, 1));
		add(westLine, BorderLayout.WEST);

		// dagarpanelen i top line
		JLabel[] dagLabel = new JLabel[7];
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(630, 96));
		topLine.add(theDays, BorderLayout.CENTER);

		// Labels veckodagarna i headernamepanelen

		//

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			String tempVal = days.get(k);
			String[] tempArray = tempVal.split("\\/");
			// System.out.println(tempArray[0]+" + "+tempArray[1]);
			dagLabel[k].setText("<html><p>" + tempArray[0] + "</p><p> " + tempArray[1] + "</p></html>");
			dagLabel[k].setPreferredSize(new Dimension(90, 96));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dagLabel[k].setHorizontalAlignment(SwingConstants.CENTER);
			dagLabel[k].setFont(new Font("SansSerif", Font.PLAIN, 18));
			theDays.add(dagLabel[k]);
		}

		// Dag kolumnerna, under dagarpanelen
		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(630, 504));
		containDays.setBackground(Color.green);
		containDays.setLayout(new GridLayout(1, 7));
		add(containDays, BorderLayout.CENTER);

		// TimeView, Finns under vecka label i westLine
		JLabel[] timeLabel = new JLabel[24];
		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(70, 504));
		timeView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timeView.setLayout(new GridLayout(24, 1, 0, 2));
		westLine.add(timeView);

		// Time label, tiderna i west line som labels.
		for (int i = 0; i < 24; i++) {
			timeLabel[i] = new JLabel();
			if (i < 10) {
				timeLabel[i].setText("0" + i + ":00");
			} else {
				timeLabel[i].setText(i + ":00");
			}
			timeLabel[i].setPreferredSize(new Dimension(70, 21));
			timeLabel[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			timeLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel[i].setFont(new Font("SansSerif", Font.PLAIN, 10));
			timeView.add(timeLabel[i]);
		}

		// Individuella dag kolumner
		for (int i = 0; i < dagLabel.length; i++) {
			// Här ska datum sättas in från en funktion
			// TODO Kalender funktioner ska hit.
			JPanel currentDay = new JPanel();
			currentDay.setPreferredSize(new Dimension(90, 504));
			currentDay.setBackground(new Color(238, 238, 238));
			currentDay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			currentDay.setFont(new Font("SansSerif", Font.PLAIN, 20));
			currentDay.setLayout(new GridLayout(24, 1, 0, 2));

			// Dagarnas egna celler
			for (int j = 0; j < 24; j++) {
				// Paneler för labels att ligga i, behövs för mouselistener
				JPanel dayTimeP = new JPanel();
				dayTimeP.setPreferredSize(new Dimension(90, 21));
				dayTimeP.setBackground(Invisible);
				dayTimeP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				dayTimeP.setLayout(new GridLayout(1, 1));
				dayTimeP.addMouseListener(new ClickListener(dayTimeP));

				// Labels för dags cellerna.
				JLabel dayTime = new JLabel();
				// dayTime.setText("");
				dayTime.setPreferredSize(new Dimension(90, 21));
				dayTime.setHorizontalAlignment(SwingConstants.CENTER);
				dayTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
				try {
					addEvent(i, j, dayTime);

				} catch (Exception e) {}
				dayTimeP.add(dayTime);
				currentDay.add(dayTimeP);
			}
			containDays.add(currentDay);
		}

		setBackground(Color.WHITE);

	}

	public void init() {
		year = timeLogic.getCurrentYear();
		month = timeLogic.getCurrentMonth();
		week = timeLogic.getCurrentWeek();
		days = timeLogic.getCurrentWeekDays();

	}

	private void addEvent(int i, int j, JLabel label) {
		// j = hour
		// i = day index
		for (int k = 0; k < event.size(); k++) {
			int tabelNumber = panelNumber(parseDay(days.get(i)), String.valueOf(j));
			int sqlStartNumber = sqlNumber(timeLogic.parseOutDay(event.get(k).getTimeStart()),
					timeLogic.parseOutHour(event.get(k).getTimeStart()));
			int sqlEndNumber = sqlNumber(timeLogic.parseOutDay(event.get(k).getTimeEnd()),
					timeLogic.parseOutHour(event.get(k).getTimeEnd()));
			if (tabelNumber >= sqlStartNumber && tabelNumber <= sqlEndNumber) {
				label.setText(event.get(k).getTitle());
			}
		}
	}

	private int panelNumber(String day, String hour) {
		if (Integer.parseInt(hour) < 10) {
			hour = "0" + hour;
		}
		if (Integer.parseInt(day) < 10) {
			day = "0" + day;
		}
		int number = Integer.parseInt(day + "" + hour);
		// System.out.println("total nummer: " + number);
		return number;
	}

	private int sqlNumber(String day, String hour) {
		int number = Integer.parseInt(day + "" + hour);
		// System.out.println("total nummer: " + number);
		return number;
	}

	private String parseDay(String date) {
		return date.replaceAll("[^0-9]", "");
	}
	//
}
