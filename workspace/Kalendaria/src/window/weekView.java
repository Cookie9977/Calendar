package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WeekView extends JPanel {
	private JLabel veckaLabel;

	public WeekView() {

		setPreferredSize(new Dimension(700, 600));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(700, 90));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		JPanel westLine = new JPanel();
		westLine.setSize(new Dimension(80, 600));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		veckaLabel = new JLabel();
		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(80, 90));
		veckaLabel.setText("Vecka: 50");
		vecka.setLayout(new GridLayout(0, 1));
		vecka.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		topLine.add(vecka, BorderLayout.WEST);
		vecka.add(veckaLabel);

		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(80, 500));
		timeView.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

		westLine.add(timeView);

		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(619, 90));
		topLine.add(theDays, BorderLayout.EAST);

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setPreferredSize(new Dimension(150, 160));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dagLabel[k].setFont(new Font("SansSerif", Font.BOLD, 20));
			theDays.add(dagLabel[k]);
		}

		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(620, 500));
		containDays.setBackground(Color.green);
		containDays.setLayout(new GridLayout(1, 7));
		add(containDays, BorderLayout.EAST);

		for (int i = 0; i < dagLabel.length; i++) {

			// Här ska datum sättas in från en funktion
			JLabel label = new JLabel();
			label.setText("DINDAG");
			label.setPreferredSize(new Dimension(71, 160));
			label.setBackground(Color.CYAN);
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setFont(new Font("SansSerif", Font.BOLD, 20));
			containDays.add(label);
		}

		setBackground(Color.WHITE);

	}

	private int getXStart(Dimension screenSize, int width) {
		int x = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return x;
	}

	private int getYStart(Dimension screenSize, int height) {
		int y = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return y;
	}

	//
}
