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

		setPreferredSize(new Dimension(700, 500));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(700, 90));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		JPanel westLine = new JPanel();
		westLine.setSize(new Dimension(80, 500));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		veckaLabel = new JLabel();
		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(100, 90));
		thisWeek = new TimeLogic();
		veckaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		veckaLabel.setText("Vecka:" + thisWeek.getWeek());
		veckaLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		vecka.setLayout(new GridLayout(0, 1));
		vecka.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topLine.add(vecka, BorderLayout.WEST);
		vecka.add(veckaLabel);

		JLabel[] timeLabel = new JLabel[24];
		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(100, 500));
		timeView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timeView.setLayout(new GridLayout(24, 1));
		for (int ii = 0; ii < 24; ii++) {
			timeLabel[ii] = new JLabel();
			if (ii < 10) {
				timeLabel[ii].setText("0" + ii + ":00");
			} else {
				timeLabel[ii].setText(ii + ":00");
			}
			timeLabel[ii].setPreferredSize(new Dimension(100, 20));
			timeLabel[ii].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			timeLabel[ii].setHorizontalAlignment(SwingConstants.CENTER);
			timeLabel[ii].setFont(new Font("SansSerif", Font.PLAIN, 18));
			timeView.add(timeLabel[ii]);
		}

		westLine.add(timeView);

		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(700, 90));
		topLine.add(theDays, BorderLayout.EAST);

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setPreferredSize(new Dimension(150, 160));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dagLabel[k].setHorizontalAlignment(SwingConstants.CENTER);
			dagLabel[k].setFont(new Font("SansSerif", Font.PLAIN, 20));
			theDays.add(dagLabel[k]);
		}

		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(700, 500));
		containDays.setBackground(Color.green);
		containDays.setLayout(new GridLayout(1, 7));
		add(containDays, BorderLayout.EAST);

		for (int i = 0; i < dagLabel.length; i++) {

			// Här ska datum sättas in från en funktion
			JLabel label = new JLabel();
			label.setText("DINDAG");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(70, 160));
			label.setBackground(Color.CYAN);
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setFont(new Font("SansSerif", Font.PLAIN, 20));
			containDays.add(label);
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
