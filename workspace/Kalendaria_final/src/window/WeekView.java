package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.TimeLogic;

public class WeekView extends JPanel {

	private JLabel veckaLabel;
	private TimeLogic thisWeek;

	private static final long serialVersionUID = -1542039657044981535L;

	public WeekView() {

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
		thisWeek = new TimeLogic();
		veckaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		veckaLabel.setText("V." + thisWeek.getWeek());
		veckaLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		vecka.add(veckaLabel);

		// West line, timestamps on the left
		JPanel westLine = new JPanel();
		westLine.setSize(new Dimension(70, 504));
		westLine.setLayout(new GridLayout(1, 1));
		add(westLine, BorderLayout.WEST);

		// dagarpanelen i top line
		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(630, 96));
		topLine.add(theDays, BorderLayout.CENTER);

		// Labels veckodagarna i headernamepanelen
		days = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setPreferredSize(new Dimension(90, 96));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dagLabel[k].setHorizontalAlignment(SwingConstants.CENTER);
			dagLabel[k].setFont(new Font("SansSerif", Font.PLAIN, 20));
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
		for (int ii = 0; ii < 24; ii++) {
			timeLabel[ii] = new JLabel();
			if (ii < 10) {
				timeLabel[ii].setText("0" + ii + ":00");
			} else {
				timeLabel[ii].setText(ii + ":00");
			}
			timeLabel[ii].setPreferredSize(new Dimension(70, 21));
			timeLabel[ii].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			timeLabel[ii].setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel[ii].setFont(new Font("SansSerif", Font.PLAIN, 10));
			timeView.add(timeLabel[ii]);
		}

		// Individuella dag kolumner
		for (int i = 0; i < dagLabel.length; i++) {
			// Här ska datum sättas in från en funktion
			// TODO Kalender funktioner ska hit.
			JPanel currentDay = new JPanel();
			currentDay.setPreferredSize(new Dimension(90, 504));
			currentDay.setBackground(new Color(0, 0, 0, 0));
			currentDay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			currentDay.setFont(new Font("SansSerif", Font.PLAIN, 20));
			currentDay.setLayout(new GridLayout(24, 1, 0, 2));

			// Dagarnas egna celler
			for (int j = 0; j < 24; j++) {
				JLabel dayTime = new JLabel();
				dayTime.setText("c:" + j);
				dayTime.setPreferredSize(new Dimension(90, 21));
				dayTime.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				dayTime.setHorizontalAlignment(SwingConstants.CENTER);
				dayTime.setFont(new Font("SansSerif", Font.PLAIN, 10));
				currentDay.add(dayTime);
			}
			containDays.add(currentDay);
		}

		setBackground(Color.WHITE);

	}

	@SuppressWarnings("unused")
	private int getXStart(Dimension screenSize, int width) {
		int x = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return x;
	}

	@SuppressWarnings("unused")
	private int getYStart(Dimension screenSize, int height) {
		int y = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return y;
	}

	//
}
