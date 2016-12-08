package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import logic.TimeLogic;
import main.ClickListener;

public class DayView extends JPanel {
	private static final long serialVersionUID = -5546841496999182019L;
	private JPanel calendar;
	private JPanel[][] holders;
	private JPanel[] layoutHolders;
	private TimeLogic logic;
	private JScrollPane scroll;
	private JLabel[] timeLabels;
	protected JLabel[] eventLabels;
	protected int day;
	// private JPanel hoursMinutes;
	// private JPanel[][] dayPanel;
	// private JTable timeTable;

	public DayView() {
		calendar = new JPanel();
		logic = new TimeLogic();
		scroll = new JScrollPane();
		init();

		// TODO visa dagens datum med event

		scroll.setViewportView(calendar);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

			eventLabels[i] = new JLabel("");
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
					layoutHolders[0].add(holders[i][j]);
				} else {
					holders[i][j].setPreferredSize(new Dimension(560, 20));
					layoutHolders[1].add(holders[i][j]);
					holders[i][j].addMouseListener(new ClickListener(holders[i][j]));
				}

			}
		}
		add(scroll);
	}
	public void init(){
		day = logic.getCurrentDay();
	}
}
