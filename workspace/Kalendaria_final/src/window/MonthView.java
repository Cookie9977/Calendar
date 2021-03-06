package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import event.Event;
import event.EventList;
import logic.TimeLogic;
import main.ClickListener;
import main.Storage;

public class MonthView extends JPanel {
	private static final long serialVersionUID = 6304391601622162482L;
	private JTable tableMonth;
	private TimeLogic timeLogic;
	protected ArrayList<String> daysOfMonth;
	private String SQL;
	private ArrayList<Event> event;
	private Event temp;
	protected int thisMonth, firstDayOfMonth, lastDayOfMonth, dayOfWeekMonth, firstDayNextMonth, lastDayLastMonth,
			justering, lopNummer, year, firstWeek;

	public int FmonthColumn, LmonthRow, LmonthColumn;
	private JLabel eventArea;

	public MonthView(Window windowVal) {
		timeLogic = new logic.TimeLogic();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(770, 720));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(772, 60));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);
		init();
		if (Storage.id != 0) {
			SQL = "SELECT event.id, event.title, event.description, event.location, event.start, event.end, category.name, event_link.owner  FROM `event_link` LEFT JOIN event ON event_link.event_id = event.id INNER JOIN category ON event.category = category.id WHERE (event_link.owner = 1 OR event_link.accepted = 1) AND event_link.user_id = "
					+ Storage.id
					+ " AND DATE_FORMAT(event.start, '%Y%m') =  DATE_FORMAT(event.end, '%Y%m') AND DATE_FORMAT(event.end, '%Y%m') = "
					+ year + "" + thisMonth;
			Object[][] data = Storage.db.getData(SQL);
			try {
				event = new EventList(data);
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
		JLabel[] dagLabel = new JLabel[8];
		String[] days;
		days = new String[] { "  Vecka", "  M�ndag", "  Tisdag", "  Onsdag", "  Torsdag", "  Fredag", "  L�rdag",
				"  S�ndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 8));
		theDays.setBackground(new Color(204, 51, 145));
		theDays.setPreferredSize(new Dimension(770, 86));
		topLine.add(theDays, BorderLayout.CENTER);
		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setForeground(new Color(245, 245, 245));
			dagLabel[k].setPreferredSize(new Dimension(96, 94));
			// dagLabel[k].setBorder(BorderFactory.createLineBorder(new
			// Color(196, 56, 110)));
			// dagLabel[k].setPreferredSize(new Dimension(0, 50));

			dagLabel[k].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, (new Color(50, 50, 50))));
			theDays.add(dagLabel[k]);
		}
		JPanel westLine = new JPanel();
		// westLine.setSize(new Dimension(60, 400));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		JPanel timeView = new JPanel();
		timeView.setBackground(new Color(204, 51, 145));
		timeView.setPreferredSize(new Dimension(97, 640));
		timeView.setLayout(new GridLayout(6, 1));
		westLine.add(timeView);
		// int[] weeks = timeLogic.getWeek();
		int newyear = 1;
		for (int i = 0; i < 6; i++) {
			JLabel veckan = new JLabel();
			veckan.setForeground(new Color(245, 245, 245));
			if (firstWeek + i <= 52) {
				veckan.setText("  v." + String.valueOf(firstWeek + i));
			} else {
				veckan.setText("  v." + String.valueOf(newyear));
				newyear++;
			}
			veckan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (new Color(50, 50, 50))));
			timeView.add(veckan);

		}

		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(770, 645));

		containDays.setLayout(new GridLayout(1, 1));
		add(containDays, BorderLayout.CENTER);

		tableMonth = new JTable(6, 7) {
			private static final long serialVersionUID = -812993975526070097L;

			public boolean isCellEditable(int aRow, int aColumn) {
				return false;
			}
		};
		tableMonth.setTableHeader(null);
		tableMonth.setRowHeight(105);
		tableMonth.getColumnModel().getColumn(0).setCellRenderer(new TabellRenderare(this));
		tableMonth.setCellSelectionEnabled(true);
		tableMonth.addMouseListener(new ClickListener(this, windowVal));
		containDays.add(tableMonth);
		lopNummer = firstDayNextMonth = 1;

		for (int j = 0; j < tableMonth.getRowCount(); j++) {
			for (int i = 0; i < tableMonth.getColumnCount(); i++) {
				tableMonth.getColumnModel().getColumn(j + 1).setCellRenderer(new TabellRenderare(this));
				if (lopNummer > 1 && lopNummer < lastDayOfMonth + 1) {

					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;

				} else if (j == 0 && dayOfWeekMonth == i) {

					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;

				} else if (lopNummer > lastDayOfMonth) {
					if (firstDayNextMonth == 1) {
						LmonthColumn = i;
						LmonthRow = j;
						System.out.println("Column " + i + "   Rad " + j);
					}
					tableMonth.setValueAt(firstDayNextMonth++, j, i);

				}

				else if (lopNummer < lastDayLastMonth) {
					tableMonth.setValueAt(lastDayLastMonth - justering + 1, j, (dayOfWeekMonth - justering));
					justering--;
					FmonthColumn = i;
				}
				try {
					for (int k = 0; k < event.size(); k++) {
						if (Integer.parseInt(timeLogic.parseOutDay(event.get(k).getTimeStart())) == lopNummer) {
							temp = event.get(k);

							System.out.println(lopNummer);
							String text = temp.getTitle();
							System.out.println(text);
							JTextField eventTitle = new JTextField();
							eventTitle.setText(text);
							eventArea = new JLabel();
							eventArea.setText(text);
							eventArea.setPreferredSize(new Dimension(2, 2));
							eventArea.setSize(10, 90);

							tableMonth.setValueAt(lopNummer + " " + eventArea.getText(), j, i);

							// TODO fixa datum
						}
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public void init() {
		thisMonth = timeLogic.getCurrentMonth();
		// System.out.println("denna m�naden: "+ thisMonth);
		lastDayOfMonth = timeLogic.currentLastDayMonth();
		// System.out.println("Sista dagen: "+ lastDayOfMonth);
		dayOfWeekMonth = justering = timeLogic.currentFirstDayOfMonth();
		// System.out.println("Dag i veckan: "+ dayOfWeekMonth);
		lastDayLastMonth = timeLogic.currentLastDayLastMonth();
		// System.out.println("F�rra m�nadens sista dag; "+ lastDayLastMonth);
		daysOfMonth = timeLogic.getDays("" + thisMonth);
		// System.out.println("days of month; "+ daysOfMonth);
		// System.out.println("**********************************");
		firstWeek = timeLogic.getFirstWeekOfMonth(thisMonth);
		year = timeLogic.getCurrentYear();

	}
}
