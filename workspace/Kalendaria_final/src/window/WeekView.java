package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logic.TimeLogic;
import main.ClickListener;

public class WeekView extends JPanel {

	private JLabel veckaLabel;
	private TimeLogic TimeLogic;
	protected Color Invisible = new Color(0, 0, 0, 0);

	private static final long serialVersionUID = -1542039657044981535L;

	public WeekView() {
		TimeLogic = new logic.TimeLogic();
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
		veckaLabel.setText("V." + TimeLogic.getWeek()[0]);
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
		ArrayList<String> days;
		//
		int weekDaysParam[] = TimeLogic.getWeek();
		days = TimeLogic.getWeekDays(weekDaysParam[0],weekDaysParam[1]);
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
				dayTime.setText("");
				dayTime.setPreferredSize(new Dimension(90, 21));
				dayTime.setHorizontalAlignment(SwingConstants.CENTER);
				dayTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
				dayTimeP.add(dayTime);
				currentDay.add(dayTimeP);
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
