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

import logic.TimeLogic;

public class MonthView extends JPanel {

	private static final long serialVersionUID = 6304391601622162482L;
	private JTable tableMonth;
	private pane.TimePane TimePane;
	private TimeLogic TimeLogic;

	public MonthView() {
		TimePane = new pane.TimePane();
		TimeLogic = new logic.TimeLogic();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(600, 50));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(50, 90));
		JLabel veckaLabel = new JLabel();
		veckaLabel.setText("Veckor");
		vecka.add(veckaLabel);
		topLine.add(vecka, BorderLayout.WEST);

		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(550, 90));
		topLine.add(theDays, BorderLayout.EAST);

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			// dagLabel[k].setPreferredSize(new Dimension(0, 50));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			theDays.add(dagLabel[k]);
		}
		JPanel westLine = new JPanel();
		// westLine.setSize(new Dimension(60, 400));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(54, 500));
		timeView.setLayout(new GridLayout(6, 1));
		westLine.add(timeView);

		for (int i = 0; i < 6; i++) {
			JLabel veckan = new JLabel();
			veckan.setText("v.");
			veckan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			timeView.add(veckan);

		}

		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(600, 500));
		containDays.setBackground(Color.green);
		containDays.setBorder(BorderFactory.createLineBorder(Color.RED));
		containDays.setLayout(new GridLayout(1, 1));
		add(containDays, BorderLayout.CENTER);

		tableMonth = new JTable(6, 7);
		tableMonth.setTableHeader(null);
		tableMonth.setRowHeight(92);
		tableMonth.getColumnModel().getColumn(0).setCellRenderer(new TabellRenderare());
		tableMonth.setCellSelectionEnabled(true);
		tableMonth.setEnabled(false);
		int monthColumn;

		containDays.add(tableMonth);

		int thisMonth = TimeLogic.getCurrentMonth();
		System.out.println("Månad: " + thisMonth);
		int firstDayOfMonth = TimeLogic.currentFirstDayMonth();
		System.out.println("first day: " + firstDayOfMonth);
		int lastDayOfMonth = TimeLogic.currentLastDayMonth();
		System.out.println("last day " + lastDayOfMonth);
		int dayOfWeek = TimeLogic.currentDayOfWeek();
		System.out.println("day of week: " + dayOfWeek);
		int firstDayNextMonth = 1;
		System.out.println("f next month " + firstDayNextMonth);
		int lastDayLastMonth = TimeLogic.currentLastDayLastMonth();
		System.out.println("l next month: " + lastDayLastMonth);
		int justering = dayOfWeek;
		System.out.println("adjustment: " + justering);
		// int justering = TimeLogic.justering();
		ArrayList<String> daysOfMonth = TimeLogic.getDays("" + thisMonth);
		int lopNummer = 1;

		for (int j = 0; j < tableMonth.getRowCount(); j++) {
			for (int i = 0; i < tableMonth.getColumnCount(); i++) {
				tableMonth.getColumnModel().getColumn(j + 1).setCellRenderer(new TabellRenderare());

				if (lopNummer > 1 && lopNummer < lastDayOfMonth + 1) {
					tableMonth.setValueAt(lopNummer, j, i);

					// tableMonth.getRowCount().getRow(i).setCellRenderer(new
					// TabellRenderare());
					lopNummer++;

				} else if (j == 0 && dayOfWeek == i) {
					tableMonth.setValueAt(lopNummer, j, i);
					// tableMonth.getColumnModel().getColumn(j).setCellRenderer(new
					// TabellRenderare());
					// System.out.println(i);
					// System.out.println(j);
					lopNummer++;

				} else if (lopNummer > lastDayOfMonth) {
					tableMonth.setValueAt(firstDayNextMonth++, j, i);
					// tableMonth.getColumnModel().getColumn(j).setCellRenderer(new
					// TabellRenderare());
				}

				else if (lopNummer < lastDayLastMonth) {
					tableMonth.setValueAt(lastDayLastMonth - justering + 1, j, (dayOfWeek - justering));
					// tableMonth.getColumnModel().getColumn(j).setCellRenderer(new
					// TabellRenderare());
					justering--;
					monthColumn = i;
					System.out.println("MonthCol = " + monthColumn);
				}
			}
		}
	}

}
